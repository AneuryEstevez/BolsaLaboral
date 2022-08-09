package Recursos;

import java.io.Serializable;
import java.util.ArrayList;

public class SolicitudEmpresa implements Serializable{
	
 private static final long serialVersionUID = -7486385924307993644L;
 private int idSolicitud;
 private Empresa miEmpresa;
 private boolean estado;
 private int cantidad;
 private int experienciaSolicitud;
 private float sueldoMaximoSolicitud;
 private String TipoTrabajoSolicitud;
 private String lincenciaConducirSolicitud;
 private String TipoEmpleado;
 private String tituloEmpleado;
 private int edadMinima;
 private ArrayList<String> idiomasSolicitud;
 public static int generadorId;
 
 public SolicitudEmpresa(Empresa miEmpresa, int cantidad, int experienciaSolicitud,
		float sueldoMaximoSolicitud, String tipoTrabajoSolicitud, String lincenciaConducirSolicitud,
		String tipoEmpleado, String tituloEmpleado, int edadMinima, ArrayList<String> idiomasSolicitud) {
	super();
//	this.idSolicitud = "SE-"+SolicitudEmpresa.generadorId;
	this.miEmpresa = miEmpresa;
	this.setEstado(true);
	this.cantidad = cantidad;
	this.experienciaSolicitud = experienciaSolicitud;
	this.sueldoMaximoSolicitud = sueldoMaximoSolicitud;
	this.TipoTrabajoSolicitud = tipoTrabajoSolicitud;
	this.lincenciaConducirSolicitud = lincenciaConducirSolicitud;
	this.TipoEmpleado = tipoEmpleado;
	this.tituloEmpleado = tituloEmpleado;
	this.edadMinima= edadMinima;
	this.idiomasSolicitud = idiomasSolicitud;
//	SolicitudEmpresa.generadorId++;
}

 public int getIdSolicitud() {
	return idSolicitud;
 }
 public void setIdSolicitud(int idSolicitud) {
	this.idSolicitud = idSolicitud;
 }
 public Empresa getMiEmpresa() {
	return miEmpresa;
 }
 public void setMiEmpresa(Empresa miEmpresa) {
	this.miEmpresa = miEmpresa;
 }
 public boolean isEstado() {
	return estado;
}

 public void setEstado(boolean estado) {
	this.estado = estado;
 }

 public int getCantidad() {
	return cantidad;
 }
 public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
 }
 public int getExperienciaSolicitud() {
	return experienciaSolicitud;
 }
 public void setExperienciaSolicitud(int experienciaSolicitud) {
	this.experienciaSolicitud = experienciaSolicitud;
 }
 public float getSueldoMaximoSolicitud() {
	return sueldoMaximoSolicitud;
 }
 public void setSueldoMaximoSolicitud(float sueldoMaximoSolicitud) {
	this.sueldoMaximoSolicitud = sueldoMaximoSolicitud;
 }
 public String getTipoTrabajoSolicitud() {
	return TipoTrabajoSolicitud;
 }
 public void setTipoTrabajoSolicitud(String tipoTrabajoSolicitud) {
	TipoTrabajoSolicitud = tipoTrabajoSolicitud;
 }
 public String getLincenciaConducirSolicitud() {
	return lincenciaConducirSolicitud;
 }
 public void setLincenciaConducirSolicitud(String lincenciaConducirSolicitud) {
	this.lincenciaConducirSolicitud = lincenciaConducirSolicitud;
 }
 public String getTipoEmpleado() {
	return TipoEmpleado;
 }
 public void setTipoEmpleado(String tipoEmpleado) {
	TipoEmpleado = tipoEmpleado;
 }
 public String getTituloEmpleado() {
	return tituloEmpleado;
 }
 public void setTituloEmpleado(String tituloEmpleado) {
	this.tituloEmpleado = tituloEmpleado;
 }
 public ArrayList<String> getIdiomasSolicitud() {
	return idiomasSolicitud;
 }
 public void setIdiomasSolicitud(ArrayList<String> idiomasSolicitud) {
	this.idiomasSolicitud = idiomasSolicitud;
 }
 public int getEdadMinima() {
	return edadMinima;
 }
 public void setEdadMinima(int edadMinima) {
	this.edadMinima = edadMinima;
 }

}

