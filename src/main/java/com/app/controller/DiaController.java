package com.app.controller;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.DiaImage;
import com.app.entity.DiaProduct;
import com.app.entity.LogEjecucion;
import com.app.mapper.LibertadConstants;
import com.app.mapper.ResultMapper;
import com.app.processor.DiaCategoriasThreadsExecutor;
import com.app.service.IDiaImageService;
import com.app.service.IDiaProductService;
import com.app.service.ILogEjecucionService;

@RestController
@RequestMapping("/DiaOnline")
public class DiaController {
	
	@Autowired
	private IDiaProductService productService;
	
	@Autowired
	private IDiaImageService imageService;
	
	@Autowired
	private DiaCategoriasThreadsExecutor categoriasThreadsProcessor;
	
	@Autowired
	private ILogEjecucionService logEjecutionService;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(DiaController.class);
	
	@GetMapping("/procesar")
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<ResultMapper> getProductos() throws InterruptedException, ExecutionException {
		
		LOGGER.info("Se inicia el proceso de obtencion de productos Dia");
		ResultMapper result = categoriasThreadsProcessor.procesarProductosFuture2();
		LogEjecucion log = logEjecutionService.actualizarLogDeEjecucion(LibertadConstants.DIAONLINE_ID, LibertadConstants.DIAONLINE_DESC, result);
		
		result.setFechaUltimaActualizacion(log.getFechaEjecucion());
		return new ResponseEntity<ResultMapper>(result, HttpStatus.OK);

	}
	
	@GetMapping("/deleteAll")
	ResponseEntity<ResultMapper> deleteProductos() {

		imageService.deleteAll();
		productService.deleteAll();
		ResultMapper result = new ResultMapper();
		LogEjecucion log = logEjecutionService.actualizarLogDeEjecucion(LibertadConstants.DIAONLINE_ID, LibertadConstants.DIAONLINE_DESC, result);
		
		return new ResponseEntity<ResultMapper>(result, HttpStatus.OK);

	}

	@GetMapping("/downloadFile/{productEan}/{imgOrder}")
	public ResponseEntity<?> downloadFile(@PathVariable String productEan, @PathVariable int imgOrder) {
		// Load file from database

		DiaProduct prod = productService.findByEAN(productEan);

		if (prod != null && !prod.getImages().isEmpty()) {

			if (imgOrder <= prod.getImages().size()) {
				DiaImage img = prod.getImages().get(imgOrder - 1);;

				return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + img.getImageName() + "\"")
						.body(new ByteArrayResource(img.getProductImage()));
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

}
