package com.app.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="log_ejecucion")
public class LogEjecucion  implements Serializable{
	
	private static final long serialVersionUID = -6256833078113283991L;
	
	@Id
	private long idCliente;
	private String cliente;
	private String fechaEjecucion;
	private long cantProductos;
	private long cantImagenes;
	
	public LogEjecucion() {
		super();
		cantProductos=0;
		cantImagenes=0;
	}
	public LogEjecucion(long idCliente, String cliente, String fechaEjecucion) {
		super();
		this.idCliente=idCliente;
		this.cliente=cliente;
		this.fechaEjecucion=fechaEjecucion;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getFechaEjecucion() {
		return fechaEjecucion;
	}
	public void setFechaEjecucion(String fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}
	public long getCantProductos() {
		return cantProductos;
	}
	public void setCantProductos(long cantProductos) {
		this.cantProductos = cantProductos;
	}
	public long getCantImagenes() {
		return cantImagenes;
	}
	public void setCantImagenes(long cantImagenes) {
		this.cantImagenes = cantImagenes;
	}
	public long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}
	
	

}
