package Recursos;

import java.io.Serializable;
import java.util.ArrayList;

public class SolicitudPersona implements Serializable{

private static final long serialVersionUID = -1295081919267776218L;
private int id;
private Persona persona;
private String estado;
private int experiencia;
private float sueldoMinimo;
private String tipoTrabajo;
private String licenciaConducir;
private String movilidad;
private ArrayList<String> idiomas;
public static int generadorId = 1;

public SolicitudPersona(Persona persona, int experiencia, float sueldoMinimo, String tipoTrabajo,
		String licenciaConducir, String movilidad, ArrayList<String> idiomas) {
	super();
//	this.id = id;
	this.persona = persona;
	if (!persona.isStatus()) {
		this.setEstado("Activa");
	} else {
		this.setEstado("En espera");
	}
	this.experiencia = experiencia;
	this.sueldoMinimo = sueldoMinimo;
	this.tipoTrabajo = tipoTrabajo;
	this.licenciaConducir = licenciaConducir;
	this.movilidad = movilidad;
	this.idiomas = idiomas;
//	SolicitudPersona.generadorId++;
}

public int getId() {
	return id;
}

public void setId(int i) {
	this.id = i;
}

public Persona getPerson() {
	return persona;
}

public void setPerson(Persona person) {
	this.persona = person;
}

public String getEstado() {
	return estado;
}

public void setEstado(String estado) {
	this.estado = estado;
}

public int getExperiencia() {
	return experiencia;
}

public void setExperiencia(int experiencia) {
	this.experiencia = experiencia;
}

public float getSueldoMinimo() {
	return sueldoMinimo;
}

public void setSueldoMinimo(float sueldoMinimo) {
	this.sueldoMinimo = sueldoMinimo;
}

public String getTipoTrabajo() {
	return tipoTrabajo;
}

public void setTipoTrabajo(String tipoTrabajo) {
	this.tipoTrabajo = tipoTrabajo;
}

public String getLicenciaConducir() {
	return licenciaConducir;
}

public void setLicenciaConducir(String licenciaConducir) {
	this.licenciaConducir = licenciaConducir;
}

public String getMovilidad() {
	return movilidad;
}

public void setMovilidad(String movilidad) {
	this.movilidad = movilidad;
}

public ArrayList<String> getIdiomas() {
	return idiomas;
}

public void setIdiomas(ArrayList<String> idiomas) {
	this.idiomas = idiomas;
}

}
