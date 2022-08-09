package Recursos;

import java.io.Serializable;
import java.util.ArrayList;

public class Empresa implements Serializable{

	private static final long serialVersionUID = -7775555408612274704L;
	private String idEmpresa;
	private String NombreEmpresa;
	private String categoria;
	private String DireccionEmpresa;
	private String telefonoEmpresa;
	private String municipio;
	private ArrayList<SolicitudEmpresa> solicitudes;
	
	public Empresa(String idEmpresa, String nombreEmpresa, String categoria, String direccionEmpresa,
			String telefonoEmpresa, String municipio) {
		super();
		this.idEmpresa = idEmpresa;
		NombreEmpresa = nombreEmpresa;
		this.categoria = categoria;
		DireccionEmpresa = direccionEmpresa;
		this.telefonoEmpresa = telefonoEmpresa;
		this.municipio = municipio;
		this.solicitudes = new ArrayList<>();
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNombreEmpresa() {
		return NombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		NombreEmpresa = nombreEmpresa;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDireccionEmpresa() {
		return DireccionEmpresa;
	}

	public void setDireccionEmpresa(String direccionEmpresa) {
		DireccionEmpresa = direccionEmpresa;
	}

	public String getTelefonoEmpresa() {
		return telefonoEmpresa;
	}

	public void setTelefonoEmpresa(String telefonoEmpresa) {
		this.telefonoEmpresa = telefonoEmpresa;
	}

	public ArrayList<SolicitudEmpresa> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(ArrayList<SolicitudEmpresa> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
}
