package com.app.proxy;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.entity.LibertadImage;
import com.app.mapper.CategoriaMapper;
import com.app.mapper.ProductMapper;

@Service
public class ProductProxy {
	
	@Value( "${categorias.base.url}" )
	private String categoriasVtexUrl;
	
	public CategoriaMapper[]  getCategorias(String clientVtexUrl) {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.3");

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<CategoriaMapper[]> respEntity = restTemplate.exchange(clientVtexUrl+categoriasVtexUrl, HttpMethod.GET, entity,
				CategoriaMapper[].class);

		CategoriaMapper[] resp = respEntity.getBody();
		
		return resp;
	}
	
	public ProductMapper[] getProductos(String url) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.3");

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		ResponseEntity<ProductMapper[]> respEntity = restTemplate.exchange(url, HttpMethod.GET, entity,
				ProductMapper[].class);

		ProductMapper[] resp = respEntity.getBody();

		
		return resp;
	}

	/**
	 * Recibe un objeto imagen y su funcion es descargar la imagen de la url que trae el objeto y la carga en el mismo
	 * @param img
	 */
	public static void loadPicture(LibertadImage img) {
        try {
        	BufferedImage image =null;
        	URL url = new URL(img.getImageRemoteUrl());
        	image = ImageIO.read(url);
        	 
//        	String fileNameLocation = "C:\\dev\\jumboFiles\\"+imgJumbo.getImageName()+".jpg";
//        	
//        	ImageIO.write(image, "jpg", new File(fileNameLocation));
//        	
//			File file = new File(fileNameLocation);
//			byte[] picInBytes = new byte[(int) file.length()];
//			FileInputStream fileInputStream = new FileInputStream(file);
//			fileInputStream.read(picInBytes);
//			fileInputStream.close();
//			imgJumbo.setProductImage(picInBytes);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			img.setProductImage(imageInByte);
			
			/**TODO hay que borrar el archivo del disco **/
        }catch (Exception e) {
        	img.setProductImage(null);
		}
	}
}
