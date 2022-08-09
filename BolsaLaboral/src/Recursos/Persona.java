package Recursos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public abstract class Persona implements Serializable{
	
	private static final long serialVersionUID = -6473711902597862141L;
	protected String id;
	protected boolean status;
	protected String nombre;
	protected String fecha_nacimiento;
	protected String sexo;
	protected String telefono;
	protected String direccion;
	protected String municipio;
	protected ArrayList<SolicitudPersona> misSolicitudes;

public Persona(String id, String nombre, String edad, String sexo, String telefono, String direccion, String municipio) {
	super();
	this.id = id;
	this.status = false;
	this.nombre = nombre;
	this.fecha_nacimiento = edad;
	this.sexo = sexo;
	this.telefono = telefono;
	this.direccion = direccion;
	this.municipio = municipio;
	this.misSolicitudes = new ArrayList<>();
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public boolean isStatus() {
	return status;
}

public void setStatus(boolean status) {
	this.status = status;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getSexo() {
	return sexo;
}

public void setSexo(String sexo) {
	this.sexo = sexo;
}

public String getTelefono() {
	return telefono;
}

public void setTelefono(String telefono) {
	this.telefono = telefono;
}

public String getDireccion() {
	return direccion;
}

public void setDireccion(String direccion) {
	this.direccion = direccion;
}

public String getMunicipio() {
	return municipio;
}

public void setMunicipio(String municipio) {
	this.municipio = municipio;
}

public ArrayList<SolicitudPersona> getMisSolicitudes() {
	return misSolicitudes;
}

public void setMisSolicitudes(ArrayList<SolicitudPersona> misSolicitudes) {
	this.misSolicitudes = misSolicitudes;
}

public String getFecha_nacimiento() {
	return fecha_nacimiento;
}

public void setFecha_nacimiento(String fecha_nacimiento) {
	this.fecha_nacimiento = fecha_nacimiento;
}

}


