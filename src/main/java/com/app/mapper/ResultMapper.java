package com.app.mapper;

import java.util.ArrayList;
import java.util.List;

public class ResultMapper {
	
	private long cantProductos;
	private long cantImagenes;
	private long cantCategorias;
	private List<String> mensajes;
	private List<String> errores;
	private long invocacionesAServicios;
	private String fechaUltimaActualizacion;
	
	public ResultMapper() {
		super();
		this.mensajes = new ArrayList<String>();
		this.errores = new ArrayList<>();
		this.cantCategorias = 0;
		this.cantImagenes = 0;
		this.cantProductos = 0;
	}
	
	public long getInvocacionesAServicios() {
		return invocacionesAServicios;
	}
	public void setInvocacionesAServicios(long invocacionesAServicios) {
		this.invocacionesAServicios = invocacionesAServicios;
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
	public long getCantCategorias() {
		return cantCategorias;
	}
	public void setCantCategorias(long cantCategorias) {
		this.cantCategorias = cantCategorias;
	}
	public List<String> getMensajes() {
		return mensajes;
	}
	public void setMensajes(List<String> mensajes) {
		this.mensajes = mensajes;
	}
	public List<String> getErrores() {
		return errores;
	}
	public void setErrores(List<String> errores) {
		this.errores = errores;
	}

	public String getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(String fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}
	public void sumarProductos(long prods) {
		this.cantProductos=this.cantProductos+prods;
	}
	public void sumarImagenes(long img) {
		this.cantImagenes=this.cantImagenes+img;
	}

}
