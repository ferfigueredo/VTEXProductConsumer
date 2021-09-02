package com.app.processor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.entity.JumboProduct;
import com.app.entity.builder.ProductBuilder;
import com.app.helper.CategoryFormatHelper;
import com.app.mapper.CategoriaMapper;
import com.app.mapper.ProductMapper;
import com.app.mapper.ResultMapper;
import com.app.proxy.ProductProxy;
import com.app.service.IJumboImageService;
import com.app.service.IJumboProductService;

@Service
public class JumboProcessor  {

	@Value( "${jumbo.base.url}" )
	private String clientVtexUrl;
	
	@Value( "${productos.base.url}" )
	private String productosVtexUrl;
	
	@Autowired
	private ProductProxy proxy;
	
	@Autowired
	private IJumboProductService productService;
	
	@Autowired
	private IJumboImageService iImageJumboService;
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	@Deprecated
	public ResultMapper procesarProductos() {
		
		ResultMapper result = new ResultMapper();
		
		/** Se borran todos los productos e imagenes de la DB **/
		iImageJumboService.deleteAll();
		productService.deleteAll();
		
		int contadorLlamadas = 0;
		String urlCategorias = "";
		/** Llamo al proxy para que me devuelva las categorias **/
		CategoriaMapper[] categoriaMapperArray = this.proxy.getCategorias(urlCategorias);
		contadorLlamadas++;

		/** obtuve listado de categorias y subcategorias. **/
		List<CategoriaMapper> categorias = new ArrayList<CategoriaMapper>(Arrays.asList(categoriaMapperArray));
		
		/** Se invoca al helper de Categorias para que nos devuelva las categorias en el formato que acepta VTEX
		 *  Formato: categoriaPadre/categoriaHija/categoriaHijaDeHija **/
		List<String> categoriasIds = CategoryFormatHelper.categoriasToStringList(categorias);
		result.setCantCategorias(categoriasIds.size());
		
		/**
		 * Se itera sobre las categorias para ir haciendo las peticiones de
		 * productos. Por limitaciones del servicio hay que pedir de a 50 productos
		 * maximo.
		 **/
		List<ProductMapper> todosLosProd = new ArrayList<ProductMapper>();

		int from;
		int to;
		for (String categoria : categoriasIds) {
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

			/** TODO sacar esto para produccion o pruebas de Stress */
			if (todosLosProd.size() > 10) {
				break;
			}
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
		}
		contadorLlamadas++;
		result.setInvocacionesAServicios(contadorLlamadas);
		
		try {
			int productoProcesados = procesarProductosYGuardar(todosLosProd);
			result.setCantProductos(productoProcesados);
			result.setCantImagenes(iImageJumboService.getCountOfImages());
		} catch (Exception e) {
			result.getErrores().add("Falló al procesar los productos Jumbo. Error "+e.getMessage());
		}
		
		result.getMensajes().add("Se procesaron correctamente los productos Jumbo. Total rductos procesados "+result.getCantProductos());
		return result;
	}
	
    /**
     * Procesa la lista de ProducMapper, convierte cada elemento a un product entity para
     * depues guardarlo en la DB
     * @param todosLosProd
     * @return int value numero que indica la cantidad de productos procesados
     */
	private int procesarProductosYGuardar(List<ProductMapper> todosLosProd) {

		/** Se pasan los ObjetosMapper a Objetos Entity **/
		Map<String, JumboProduct> mapProductosJumbo = new HashMap<String, JumboProduct>();
		for (ProductMapper productVtex : todosLosProd) {
			JumboProduct product = (JumboProduct) ProductBuilder.generateJumboProduct(productVtex);
			mapProductosJumbo.put(product.getEan(), product);
		}

		System.out.println("*** Se inicia proceso de guardado en DB ***");
		productService.saveAll(new ArrayList<JumboProduct>(mapProductosJumbo.values()));
		System.out.println("*** Fin proceso de guardado en DB. Se guardaron "+mapProductosJumbo.size()+" productos de Jumbo ***");
		
		return mapProductosJumbo.size();
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
