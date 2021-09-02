package com.app.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IJumboImageDAO;
import com.app.dao.IJumboProductDAO;
import com.app.dao.ILogEjecucionDAO;
import com.app.entity.LogEjecucion;
import com.app.mapper.ResultMapper;
import com.app.service.ILogEjecucionService;

@Transactional
@Service
public class LogEjecucionServiceImpl implements ILogEjecucionService{

	
	@Autowired
	private ILogEjecucionDAO logEjecucionDAO;
	
	@Autowired
	private IJumboImageDAO imageJumboDAO;
	
	@Autowired
	private IJumboProductDAO productoDAO;
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	

//	@Override
//	public LogEjecucion crearNuevoRegistroDeEjecucion(long clienteId, String clienteDesc) {
//		Optional<LogEjecucion> optionalLog = logEjecucionDAO.findById(clienteId);
//		LogEjecucion myLog;
//		if(optionalLog.get() == null) {
//			myLog = new LogEjecucion();
//			myLog.setIdCliente(clienteId);
//			myLog.setCliente(clienteDesc);
//			
//		}else {
//			myLog = optionalLog.get();
//		}
//		myLog.setCantImagenes(0);
//		myLog.setCantProductos(0);
//		myLog.setFechaEjecucion(this.getTime());
//
//		this.logEjecucionDAO.save(myLog);
//		return  myLog;
//	}
	
	@Override
	public LogEjecucion actualizarLogDeEjecucion(long clienteId, String clienteDesc, ResultMapper result) {
		Optional<LogEjecucion> optionalLog = logEjecucionDAO.findById(clienteId);
		LogEjecucion myLog;
		if(!optionalLog.isPresent()) {
			myLog = new LogEjecucion();
			myLog.setIdCliente(clienteId);
			myLog.setCliente(clienteDesc);
		}else {
			myLog = optionalLog.get();
		}
		myLog.setFechaEjecucion(this.getTime());
		myLog.setCantImagenes(result.getCantImagenes());
		myLog.setCantProductos(result.getCantProductos());;
			
		this.logEjecucionDAO.save(myLog);
			
		return  myLog;
	}
	
	@Override
	public LogEjecucion buscarLog(long clienteId, String clienteDesc) {
		Optional<LogEjecucion> optionalLog = logEjecucionDAO.findById(clienteId);
		LogEjecucion myLog;
		if(!optionalLog.isPresent()) {
			myLog = new LogEjecucion();
			myLog.setIdCliente(clienteId);
			myLog.setCliente(clienteDesc);
			myLog.setCantImagenes(0);
			myLog.setCantProductos(0);
			myLog.setFechaEjecucion(this.getTime());
			logEjecucionDAO.save(myLog);
		}else {
			myLog = optionalLog.get();
		}
		return myLog;
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
