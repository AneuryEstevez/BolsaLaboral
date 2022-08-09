package Recursos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Control implements Serializable{
	
	private static final long serialVersionUID = -2707581395699323652L;
	private ArrayList<Usuario> usuarios;
	private ArrayList<Empresa> empresas;
	private ArrayList<Persona> personas;
	private ArrayList<SolicitudEmpresa> soliEmpresas;
	private ArrayList<SolicitudPersona> soliPersonas;
	private static Control control = null;
	private static Usuario loginUser;
	private static SQLConnection con;

	public Control() {
		usuarios = new ArrayList<>();
		empresas = new ArrayList<>();
		personas = new ArrayList<>();
		soliEmpresas = new ArrayList<>();
		soliPersonas = new ArrayList<>();
		con = new SQLConnection();
	}
	
	public static Control getInstance() {
		if (control == null) {
			control = new Control();
		}
		return control;
	}

	public ArrayList<Usuario> getUsuarios() {
		return con.SelectUsuarios();
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public ArrayList<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(ArrayList<Empresa> empresas) {
		this.empresas = empresas;
	}

	public ArrayList<Persona> getPersonas() {
		return con.SelectPersonas();
	}

	public void setPersonas(ArrayList<Persona> personas) {
		this.personas = personas;
	}

	public ArrayList<SolicitudEmpresa> getSoliEmpresas() {
		return soliEmpresas;
	}

	public void setSoliEmpresas(ArrayList<SolicitudEmpresa> soliEmpresas) {
		this.soliEmpresas = soliEmpresas;
	}

	public ArrayList<SolicitudPersona> getSoliPersonas() {
		return soliPersonas;
	}

	public void setSoliPersonas(ArrayList<SolicitudPersona> soliPersonas) {
		this.soliPersonas = soliPersonas;
	}
	
	public static Usuario getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(Usuario loginUser) {
		Control.loginUser = loginUser;
	}
	
	public SQLConnection getCon() {
		return con;
	}

	public static void setCon(SQLConnection con) {
		Control.con = con;
	}

	public static Control getControl() {
		return control;
	}

	public void setControl(Control control) {
		Control.control = control;
	}

	//Metodo Buscar empresa por ID
	public Empresa BuscarEmpresa(String idEmpresa) {
		for(Empresa e: empresas) {
			if(e.getIdEmpresa().equalsIgnoreCase(idEmpresa)){
				return e;
			}
		}
		return null;
	}
	
	//Metodo registrar nueva empresa
	public void RegistrarEmpresa(Empresa nuevaEmpresa) {
		con.InsertEmpresa(nuevaEmpresa);
	}
	 
	//Registrar una nueva persona
	public void RegistrarPersona(Persona nuevaPersona) {
		personas.add(nuevaPersona);
	}
	
	//Metodo registrar nuevo usuario
		public boolean RegistrarUsuario(Usuario nuevoUsuario) {
			if (con.InsertUsuario(nuevoUsuario)) {
				return true;
			} else {
				return false;
			}
	}
	
	//Metodo para buscar persona por su ID
	public Persona BuscarPersona(String id) {
		for(Persona p: con.SelectPersonas()) {
			if(p.getId().equalsIgnoreCase(id)) {
				return p;
			}
		}
		return null;
	}
	
	public SolicitudPersona BuscarSoliPersona(int id, String cedula) {
		SolicitudPersona sp = con.SelectSoliPersonaById(id, cedula);
		if (sp != null) {
			return sp;
		}
//		for(SolicitudPersona sp: con.SelectSolicitudPersonas()) {
//			if(sp.getId() == id) {
//				return sp;
//			}
//		}
		return null;
	}
	
	public SolicitudEmpresa BuscarSoliEmpresa(int id) {
		SolicitudEmpresa se = con.SelectSoliEmpresaById(id);
		if (se != null) {
			return se;
		}
//		for(SolicitudEmpresa se: soliEmpresas) {
//			if(se.getIdSolicitud().equalsIgnoreCase(id)) {
//				return se;
//			}
//		}
		return null;
	}
	
	//Metodo para generar solicitud de persona
	public void generarSolicitudPersona(String idPerson, SolicitudPersona nuevaSolicitud) {
		Persona p = BuscarPersona(idPerson);
		p.getMisSolicitudes().add(nuevaSolicitud);
		control.soliPersonas.add(nuevaSolicitud);
	}
	
	//Metodo para generar solicitud de empresa
	public void generarSolicitudEmpresa(String idEmpresa, SolicitudEmpresa newSolicitud) {
		Empresa e = BuscarEmpresa(idEmpresa);
		e.getSolicitudes().add(newSolicitud);
		control.soliEmpresas.add(newSolicitud);
	}

	//Metodo para realizar el match de solicitudes
	public SolicitudPersona match(SolicitudEmpresa solicitud) {
		SolicitudPersona person = null;
		int mayor = 0;
		
		for (SolicitudPersona soliPer : getCon().SelectSolicitudPersonas()) {
			if (!soliPer.getPerson().isStatus() && solicitud.isEstado() && checkSimilitud(soliPer, solicitud)) {
				if (soliPer.getEstado().equalsIgnoreCase("Activa") && porcentaje(soliPer, solicitud) >= 70 && porcentaje(soliPer, solicitud) > mayor) {
					person = soliPer;
					mayor = porcentaje(soliPer, solicitud);
				}
			}
		}
		return person;
	}

	public int porcentaje(SolicitudPersona soliPer, SolicitudEmpresa soliEmp) {
		int porciento = 0;
		
		if (soliPer.getExperiencia() >= soliEmp.getExperienciaSolicitud())
			porciento++;
		if (soliPer.getLicenciaConducir().equalsIgnoreCase(soliEmp.getLincenciaConducirSolicitud()))
			porciento++;
		if (soliPer.getSueldoMinimo() <= soliEmp.getSueldoMaximoSolicitud())
			porciento++;
		if (soliPer.getTipoTrabajo().equalsIgnoreCase(soliEmp.getTipoTrabajoSolicitud()))
			porciento++;
		if (Integer.parseInt(soliPer.getPerson().getFecha_nacimiento()) >= soliEmp.getEdadMinima())
			porciento++;
		
		if (soliPer.getPerson().getDireccion().equalsIgnoreCase(soliEmp.getMiEmpresa().getDireccionEmpresa()))
			porciento++;
		else if (soliPer.getMovilidad().equalsIgnoreCase("Si"))
			porciento++;
		
		for (String idioma : soliEmp.getIdiomasSolicitud()) {
			for (String idio : soliPer.getIdiomas()) {
				if (idioma.equalsIgnoreCase(idio)) {
					porciento++;
				}
			}
		}
		
		float size = 6 + soliEmp.getIdiomasSolicitud().size();
		return (int) ((porciento/size)*100);
	}
	
	public boolean checkSimilitud(SolicitudPersona soliPerson, SolicitudEmpresa soliEmpresa) {
		boolean check = false;
		
		if (soliPerson.getPerson() instanceof Tecnico) {
			if (soliEmpresa.getTipoEmpleado().equalsIgnoreCase("Tecnico")) {
				Tecnico t = (Tecnico) soliPerson.getPerson();
				if (soliEmpresa.getTituloEmpleado().equalsIgnoreCase(t.getArea())) {
					check = true;
				}
			}
		} else if (soliPerson.getPerson() instanceof Universitario) {
			if (soliEmpresa.getTipoEmpleado().equalsIgnoreCase("Universitario")) {
				Universitario u = (Universitario) soliPerson.getPerson();
				if (soliEmpresa.getTituloEmpleado().equalsIgnoreCase(u.getTitulo())) {
					check = true;
				}
			}
		}
		
		return check;
	}
	
//	public void changeToDesempleado(Persona person) {
//		if (person.isStatus()) {
//			person.setStatus(false);
//			for (SolicitudPersona solicitud : con.SelectSolicitudesPersona(person)) {
//				if (solicitud.getEstado().equalsIgnoreCase("En espera")) {
//					solicitud.setEstado("Activa");
//				}
//			}
//		}
//	}
	
	public void changeToEmpleado(Persona person) {
		if (!person.isStatus()) {
			con.UpdatePersonaToEmpleado(person);
//			person.setStatus(true);
//			for (SolicitudPersona solicitud : person.getMisSolicitudes()) {
//				if (solicitud.getEstado().equalsIgnoreCase("Activa")) {
//					solicitud.setEstado("En espera");
//				}
//			}
		}
	}
	
//	public boolean confirmLogin(String username, String password) {
//		boolean login = false;
//		this.setUsuarios(conect.getUsuarios());
//		for (Usuario user : usuarios) {
//			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
//				loginUser = user;
//				login = true;
//			}
//		}
//		return login;
//	}
	
	public boolean confirmLogin(String username, String password) {
		boolean login = false;
		Usuario u = con.getLoginUser(username, password);
		if(u != null) {
			setLoginUser(u);
			login = true;
		} else {
			login = false;
		}
		
		return login;
	}
	
	public void completarSolicitud(SolicitudEmpresa se) {
		se.setCantidad(se.getCantidad()-1);
		con.UpdateSoliEmpresa(se);
//		if (se.getCantidad() > 1) {
//			se.setCantidad(se.getCantidad()-1);
//		} else {
//			se.setEstado(false);
//		}
	}
	
	public void regUser(Usuario user) {
		usuarios.add(user);
	}
	
	public Usuario findUsuarioByUsername(String username) {
		for (Usuario user : usuarios) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}
	
	public ArrayList<String> getProvincias() {
		ArrayList<String> p = con.SelectProvincias();
		return p;
	}
	
	public ArrayList<String> getMunicipiosProvincia(String provincia) {
		ArrayList<String> m = con.SelectMunicipiosProvincia(provincia);
		return m;
	}
	
	public ArrayList<String> getCategorias() {
		return con.SelectCategorias();
	}

}
