package com.app.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.app.helper.CategoryFormatHelper;
import com.app.mapper.CategoriaMapper;
import com.app.mapper.ResultMapper;
import com.app.proxy.ProductProxy;

@Service
public class JumboCategoriasThreadsExecutor {

	@Value( "${jumbo.base.url}" )
	private String clientVtexUrl;
	
    @Autowired
	private JumboProductsProcessor jumboProductsProcessor;

	@Autowired
	private ProductProxy proxy;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(JumboCategoriasThreadsExecutor.class);
	
	@Deprecated
	public ResultMapper procesarProductos() {
		
		ResultMapper result = new ResultMapper();
		
		/** Llamo al proxy para que me devuelva las categorias **/
		CategoriaMapper[] categoriaMapperArray = this.proxy.getCategorias(clientVtexUrl);

		/** obtuve listado de categorias y subcategorias. **/
		List<CategoriaMapper> categorias = new ArrayList<CategoriaMapper>(Arrays.asList(categoriaMapperArray));
		
		/** Se invoca al helper de Categorias para que nos devuelva las categorias en el formato que acepta VTEX
		 *  Formato: categoriaPadre/categoriaHija/categoriaHijaDeHija **/
		List<String> categoriasIds = CategoryFormatHelper.categoriasToStringList(categorias);
		result.setCantCategorias(categoriasIds.size());
		
		int i = 0;
		for (String categoria : categoriasIds) {
			LOGGER.info("Se dispara proceso para Categoria: "+categoria);
			jumboProductsProcessor.procesarProductosSinRespuesta(categoria);
			i++;
			//TODO borrar esto para Produccion
			if(i>10)break;
		}
		
		return result;
	}
	
	public ResultMapper procesarProductosFuture2() {
		
		ResultMapper result = new ResultMapper();
		
		/** Llamo al proxy para que me devuelva las categorias **/
		CategoriaMapper[] categoriaMapperArray = this.proxy.getCategorias(clientVtexUrl);

		/** obtuve listado de categorias y subcategorias. **/
		List<CategoriaMapper> categorias = new ArrayList<CategoriaMapper>(Arrays.asList(categoriaMapperArray));
		
		/** Se invoca al helper de Categorias para que nos devuelva las categorias en el formato que acepta VTEX
		 *  Formato: categoriaPadre/categoriaHija/categoriaHijaDeHija **/
		List<String> categoriasIds = CategoryFormatHelper.categoriasToStringList(categorias);
		
		result.setCantCategorias(categoriasIds.size());
		
		//TODO Borrar esto para produccion
		List<String> categoriasIdsTemp = categoriasIds.subList(0, 10);

		List<CompletableFuture<ResultMapper>> completableFutures =
				categoriasIdsTemp.stream().map(categoria -> jumboProductsProcessor.procesarProductos(categoria)).collect(Collectors.toList());
		
		CompletableFuture<Void> allFutures = CompletableFuture
		        .allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()]));
		
		//allFutures.join();
		
//		CompletableFuture<List<ResultMapper>> allCompletableFuture = allFutures.thenApply(future -> {
//		    return completableFutures.stream()
//		            .map(completableFuture -> completableFuture.join())
//		            .collect(Collectors.toList());
//		});
		
		
//		List<ResultMapper> resultMapperList;
//		try {
//			resultMapperList = allCompletableFuture.get();
//			
//			for (ResultMapper resultMapper : resultMapperList) {
//				result.sumarProductos(resultMapper.getCantProductos());
//				result.sumarImagenes(resultMapper.getCantImagenes());
//			}
//			
//		} catch (InterruptedException | ExecutionException e) {
//			LOGGER.info("ERROR! Se producjo una excepcion en: allCompletableFuture.get(); Linea 133 - JumboCategoriasThreadsExecutor");
//		}
//		
		return result;
	}
	
}
