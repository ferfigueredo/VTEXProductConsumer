package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.LogEjecucion;

@Repository
public interface ILogEjecucionDAO extends CrudRepository<LogEjecucion, Long>{

	
	@Query("SELECT log FROM LogEjecucion log WHERE log.idCliente = ?1 ORDER BY id")
	public List<LogEjecucion>findAllByCliente(long idCliente);
	
	
}
