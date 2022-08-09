package Recursos;

import java.io.Serializable;

public class Usuario implements Serializable{

	private static final long serialVersionUID = -979238108120415427L;
	private String tipo;
	private String username;
	private String password;
	
	public Usuario(String tipo, String username, String password) {
		super();
		this.tipo = tipo;
		this.username = username;
		this.password = password;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
