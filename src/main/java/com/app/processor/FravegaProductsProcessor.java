package com.app.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.app.entity.FravegaProduct;
import com.app.entity.builder.ProductBuilder;
import com.app.mapper.LibertadConstants;
import com.app.mapper.ProductMapper;
import com.app.mapper.ResultMapper;
import com.app.proxy.ProductProxy;
import com.app.service.IFravegaProductService;


@Service
public class FravegaProductsProcessor {

	@Value( "${fravega.base.url}" )
	private String clientVtexUrl;
	
	@Value( "${productos.base.url}" )
	private String productosVtexUrl;
	
	@Autowired
	private ProductProxy proxy;
	
	@Autowired
	private IFravegaProductService productService;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(FravegaProductsProcessor.class);
	 

	@Async
	public CompletableFuture<ResultMapper>  procesarProductos(String categoria) {
		
		ResultMapper result = new ResultMapper();
		
		int contadorLlamadas = 0;
		
		/**
		 * Se itera sobre las categorias para ir haciendo las peticiones de
		 * productos. Por limitaciones del servicio hay que pedir de a 50 productos
		 * maximo.
		 **/
		List<ProductMapper> todosLosProd = new ArrayList<ProductMapper>();

		int from;
		int to;
		from = 0;
		to = 49;
		/** hago una primer llamada para obtener productos. **/
		ProductMapper[] prodMapperArray= proxy.getProductos(armarURL(categoria, from, to));
		List<ProductMapper> prods =  new ArrayList<ProductMapper>(Arrays.asList(prodMapperArray));
		contadorLlamadas++;

		/**
		 * Si el resultado no es vacio y la cantidad es igual a 50, vuelvo a llamar al
		 * servicio par ver si hay mas resultados. Si es menor a 50 quier decir que no hay más.
		 **/
		boolean hayMasProds = true;
		if (prods.isEmpty() || prods.size() < 50)
			hayMasProds = false;

		while (hayMasProds) {
			from += 50;
			to += 50;
			ProductMapper[] prodMapperArrayTemp= proxy.getProductos(armarURL(categoria, from, to));
			List<ProductMapper> prodsTemp =  new ArrayList<ProductMapper>(Arrays.asList(prodMapperArrayTemp));
			contadorLlamadas++;
			if (prodsTemp.isEmpty() || prodsTemp.size() < 50)
				hayMasProds = false;
			prods.addAll(prodsTemp);
		}
		todosLosProd.addAll(prods);
	
		result.setInvocacionesAServicios(contadorLlamadas);

		LOGGER.info(LibertadConstants.FRAVEGA_DESC + " Se obtuvieron "+todosLosProd.size()+" produtos con "+contadorLlamadas+" invocaciones al WS para la categoria "+categoria);
		try {
			procesarProductosYGuardar(todosLosProd, result);
		} catch (Exception e) {
			//result.getErrores().add("Falló al procesar los productos Dia de la categoria <<"+categoria+">>. Error "+e.getMessage());
			LOGGER.error("Falló al procesar los productos Dia de la categoria <<"+categoria+">>. Error "+e.getMessage());
		}
		
		result.getMensajes().add(LibertadConstants.FRAVEGA_DESC + " Se procesaron correctamente los productos de la categoria "+categoria);
		return CompletableFuture.completedFuture(result);
	}
	
    /**
     * Procesa la lista de ProducMapper, convierte cada elemento a un product entity para
     * depues guardarlo en la DB
     * @param todosLosProd
     * @return int value numero que indica la cantidad de productos procesados
     */
	private void procesarProductosYGuardar(List<ProductMapper> todosLosProd, ResultMapper resultMapper) {

		/** Se pasan los ObjetosMapper a Objetos Entity **/
		Map<String, FravegaProduct> mapProductosDia = new HashMap<String, FravegaProduct>();
		for (ProductMapper productVtex : todosLosProd) {
			FravegaProduct product =  ProductBuilder.generateFravegaProduct(productVtex);
			resultMapper.sumarImagenes(product.getImages().size());
			mapProductosDia.put(product.getEan(), product);
		}
		resultMapper.setCantProductos(mapProductosDia.size());
		LOGGER.info("Se procesaron y guardaron "+todosLosProd.size()+" productos");
		productService.saveAll(new ArrayList<FravegaProduct>(mapProductosDia.values()));
		
	}
	
	/**
	 * Metodo privado que ara la url para invocar la consulta d productos por categoria
	 * @param categoria
	 * @param desde
	 * @param hasta
	 * @return
	 */
	private String armarURL(String categoria, int desde, int hasta) {

		return clientVtexUrl + productosVtexUrl+ "fq=C:" + categoria + "&"
				+ "O=OrderByPriceASC" + "&" + "_from=" + desde + "&" + "_to=" + hasta;

	}
	
}

