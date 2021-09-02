package com.app.service;

import com.app.entity.LogEjecucion;
import com.app.mapper.ResultMapper;

public interface ILogEjecucionService {


	LogEjecucion actualizarLogDeEjecucion(long clienteId, String clienteDesc, ResultMapper result);

	LogEjecucion buscarLog(long clienteId, String clienteDesc);
	
}
