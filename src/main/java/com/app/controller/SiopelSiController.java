package com.app.controller;

import java.util.Arrays;
import java.util.Base64;

import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.mapper.SiopelStatusMapper;

@RestController
@RequestMapping("/Siopel")
public class SiopelSiController {

	@GetMapping("/procesar")
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<SiopelStatusMapper> getProductos() throws InterruptedException, ExecutionException {
		
		SiopelStatusMapper result = getStatusSiopelSI();

		return new ResponseEntity<SiopelStatusMapper>(result, HttpStatus.OK);

	}
	
	private SiopelStatusMapper  getStatusSiopelSI() {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.3");

        
        headers.setBasicAuth("ws", "ws");
        
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<SiopelStatusMapper> respEntity = restTemplate.exchange("http://rx033.cajval.sba.com.ar:8088/api/status", HttpMethod.GET, entity,
				SiopelStatusMapper.class);

		SiopelStatusMapper resp = respEntity.getBody();
		
		return resp;
	}
	
}
