package com.app.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.app.entity.LogEjecucion;
import com.app.mapper.LibertadConstants;
import com.app.service.ILogEjecucionService;

@Controller
public class MainController {

	@Autowired
	private ILogEjecucionService logEjecutionService;
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	
    @RequestMapping("/")
    public String  main(Model model) {
        
//        LogEjecucion logJumbo = logEjecucionService.buscarUltimoLog(LibertadConstants.JUMBO_ID);
    	LogEjecucion logJumbo = new LogEjecucion();
    	logJumbo.setFechaEjecucion("19/03/2020 15:03hs");
    	logJumbo.setCantImagenes(1244);
    	logJumbo.setCantProductos(8421);
  	  	model.addAttribute("logJumbo", logJumbo);
        
        return "welcome2"; //view
    }

	@GetMapping("/datosClientes")
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<List<LogEjecucion>> getDatosClientes() throws InterruptedException, ExecutionException {
		List<LogEjecucion> clientes = new LinkedList<LogEjecucion>();
		
		LogEjecucion logDia = logEjecutionService.buscarLog(LibertadConstants.DIAONLINE_ID, LibertadConstants.DIAONLINE_DESC);
		clientes.add(logDia);
		
		LogEjecucion logFrav = logEjecutionService.buscarLog(LibertadConstants.FRAVEGA_ID, LibertadConstants.FRAVEGA_DESC);
		clientes.add(logFrav);
		
		LogEjecucion logJumbo = logEjecutionService.buscarLog(LibertadConstants.JUMBO_ID, LibertadConstants.JUMBO_DESC);
		clientes.add(logJumbo);
		
		LogEjecucion logWal = logEjecutionService.buscarLog(LibertadConstants.WALMART_ID, LibertadConstants.WALMART_DESC);
		clientes.add(logWal);
	
		
		return new ResponseEntity<List<LogEjecucion>>(clientes, HttpStatus.OK);

	}
	
	
    @GetMapping("/ayuda")
    public String mainWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "") 
			String name, Model model) {

        model.addAttribute("message", name);

        return "welcome"; //view
    }
    
	/**
	 * 
	 * @return String con la fecha y hora actual
	 */
	private String getTime() {
		Date date = new Date();
		return dateFormat.format(date);
	}
}
