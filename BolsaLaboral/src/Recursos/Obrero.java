package Recursos;

import java.util.ArrayList;
import java.util.Date;

public class Obrero extends Persona {
	
	private String skills;

	public Obrero(String id, String nombre, String edad, String sexo, String telefono, String direccion,
			String provincia, String skills) {
		super(id, nombre, edad, sexo, telefono, direccion, provincia);
		this.skills = skills;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

}
