package Recursos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class SQLConnection {
	
	private static String server = "192.168.100.118";
	private static String dbname = "BolsaLaboral;";
	private static String username = "aestevez;";
	private static String password = "Eict@2022;";
	
	public static Connection getConnection() {
		Connection con = null;
		
		String connectionUrl =
                "jdbc:sqlserver://"
				+ server
                + ":1433;"
                + "database="+dbname
                + "user="+username
                + "password="+password
                + "encrypt=true;"
                + "trustServerCertificate=true;"
                + "loginTimeout=30;";
		
		try {
			con = DriverManager.getConnection(connectionUrl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public Usuario getLoginUser(String username, String password) {
		PreparedStatement ps;
		ResultSet rs;
		Usuario u = null;
		
		String query = "SELECT * FROM usuario where username = ? and PWDCOMPARE(?, password) = 1";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if (!rs.next()) {
				u = null;
			} else {
				 u = new Usuario(rs.getString(2), rs.getString(3), rs.getString(4));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	
	public boolean DeleteUsuario(String username) {
		PreparedStatement ps;
		boolean res = false;
		
		String query = "DELETE FROM usuario where username = ?";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			ps.setString(1, username);
			ps.execute();
			res = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res = false;
		}
		return res;
	}
	
	public ArrayList<Usuario> SelectUsuarios() {
		PreparedStatement ps;
		ResultSet rs;
		ArrayList<Usuario> users = new ArrayList<>();
		
		String query = "SELECT tipo, username FROM usuario";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Usuario u = new Usuario(rs.getString(1), rs.getString(2), null);
				users.add(u);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
	public boolean InsertUsuario(Usuario u) {
		PreparedStatement ps;
		boolean res = false;
		
		String query = "INSERT INTO usuario (tipo, username, password) values(?, ?, PWDENCRYPT(?))";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			ps.setString(1, u.getTipo());
			ps.setString(2, u.getUsername());
			ps.setString(3, u.getPassword());
			ps.execute();
			res = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res = false;
		}
		return res;
	}
	
	public boolean InsertEmpresa(Empresa m) {
		PreparedStatement ps;
		boolean res = false;
		
		String query = "INSERT INTO empresa values(?, "
				+ "(SELECT id_categoria FROM categoria WHERE nombre_categoria = ?), "
				+ "?, ?, "
				+ "(SELECT id_municipio FROM municipio where nombre_municipio = ?), ?)";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			ps.setString(1, m.getNombreEmpresa());
			ps.setString(2, m.getCategoria());
			ps.setString(3, m.getDireccionEmpresa());
			ps.setString(4, m.getTelefonoEmpresa());
			ps.setString(5, m.getMunicipio());
			ps.setString(6, m.getIdEmpresa());
			res = ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	// Insertar una persona a la base de datos
	public int InsertPersona(Persona p) {
		PreparedStatement ps;
		ResultSet rs;
		int id = 0;
		
		String query = "INSERT INTO persona (cedula, nombre, fecha_nacimiento, sexo, telefono, direccion, id_municipio)"
				+ " values(?, ?, ?, ?, ?, ?, "
				+ "(SELECT id_municipio FROM municipio where nombre_municipio = ?))"
				+ "SELECT SCOPE_IDENTITY()";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			ps.setString(1, p.getId());
			ps.setString(2, p.getNombre());
			ps.setString(3, p.getFecha_nacimiento());
			ps.setString(4, p.getSexo().substring(0, 1));
			ps.setString(5, p.getTelefono());
			ps.setString(6, p.getDireccion());
			ps.setString(7, p.getMunicipio());
			
			rs = ps.executeQuery();
				
			while (rs.next()) {
				id = rs.getInt(1);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public boolean InsertTecnico(Tecnico t) {
		PreparedStatement ps;
		boolean res = false;
		int id = InsertPersona(t);
		
		String query = "INSERT INTO tecnico VALUES(?, ?)";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, t.getArea());
			res = ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public boolean InsertUniversitario(Universitario u) {
		PreparedStatement ps;
		boolean res = false;
		int id = InsertPersona(u);
		
		String query = "INSERT INTO universitario VALUES(?, ?)";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, u.getTitulo());
			res = ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public boolean InsertObrero(Obrero o) {
		PreparedStatement ps;
		boolean res = false;
		int id = InsertPersona(o);
		
		String query = "INSERT INTO obrero VALUES(?, ?)";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, o.getSkills());
			res = ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
//	public ArrayList<Usuario> getUsuarios () {
//		PreparedStatement ps;
//		ResultSet rs;
//		ArrayList<Usuario> users = new ArrayList<>();
//		String query = "SELECT * FROM usuario";
//		
//		try {
//			ps = SQLConnection.getConnection().prepareStatement(query);
//			rs = ps.executeQuery();
//			
//			while (rs.next()) {
//				Usuario u = new Usuario(rs.getString(2), rs.getString(3), null);
//				users.add(u);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return users;
//	}
	
	public ArrayList<String> SelectProvincias () {
		PreparedStatement ps;
		ResultSet rs;
		ArrayList<String> provincias = new ArrayList<>();
		String query = "SELECT nombre_provincia FROM provincia";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String p = rs.getString(1);
				provincias.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return provincias;
	}
	
	public ArrayList<String> SelectMunicipiosProvincia (String nombreProv) {
		PreparedStatement ps;
		ResultSet rs;
		ArrayList<String> municipios = new ArrayList<>();
		String query = "SELECT municipio.nombre_municipio FROM municipio, provincia WHERE provincia.nombre_provincia = ? "
				+ "AND municipio.id_provincia = provincia.id_provincia ORDER BY nombre_municipio";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			ps.setString(1, nombreProv);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String m = rs.getString(1);
				municipios.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return municipios;
	}
	
	public ArrayList<String> SelectCategorias () {
		PreparedStatement ps;
		ResultSet rs;
		ArrayList<String> categorias = new ArrayList<>();
		String query = "SELECT nombre_categoria FROM categoria ORDER BY nombre_categoria";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String c = rs.getString(1);
				categorias.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categorias;
	}
	
	public ArrayList<Tecnico> SelectTecnicos() {
		PreparedStatement ps;
		ResultSet rs;
		ArrayList<Tecnico> tecnicos = new ArrayList<>();
		
		String query = "SELECT cedula, nombre, dbo.calcular_edad(fecha_nacimiento), sexo, telefono, direccion, municipio.nombre_municipio, area, status\n"
				+ "FROM municipio, tecnico, persona\n"
				+ "WHERE municipio.id_municipio = persona.id_municipio \n"
				+ "AND tecnico.id_persona = persona.id_persona";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Tecnico t = new Tecnico(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
				t.setStatus(rs.getBoolean(9));
				tecnicos.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tecnicos;
	}
	
	public ArrayList<Universitario> SelectUniversitarios() {
		PreparedStatement ps;
		ResultSet rs;
		ArrayList<Universitario> universitarios = new ArrayList<>();
		
		String query = "SELECT cedula, nombre, dbo.calcular_edad(fecha_nacimiento), sexo, telefono, direccion, municipio.nombre_municipio, titulo, status\n"
				+ "FROM municipio, universitario, persona\n"
				+ "WHERE municipio.id_municipio = persona.id_municipio \n"
				+ "AND universitario.id_persona = persona.id_persona";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Universitario u = new Universitario(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
				u.setStatus(rs.getBoolean(9));
				universitarios.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return universitarios;
	}
	
	public ArrayList<Obrero> SelectObreros() {
		PreparedStatement ps;
		ResultSet rs;
		ArrayList<Obrero> obreros = new ArrayList<>();
		
		String query = "SELECT cedula, nombre, dbo.calcular_edad(fecha_nacimiento), sexo, telefono, direccion, municipio.nombre_municipio, skill, status\n"
				+ "FROM municipio, obrero, persona\n"
				+ "WHERE municipio.id_municipio = persona.id_municipio \n"
				+ "AND obrero.id_persona = persona.id_persona";

		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Obrero o = new Obrero(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
				o.setStatus(rs.getBoolean(9));
				obreros.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obreros;
	}
	
	public ArrayList<Persona> SelectPersonas() {
		ArrayList<Persona> personas = new ArrayList<>();
		
		if (SelectTecnicos() != null) {
			personas.addAll(SelectTecnicos());
		}
		if (SelectUniversitarios() != null) {
			personas.addAll(SelectUniversitarios());
		}
		if (SelectObreros() != null) {
			personas.addAll(SelectObreros());
		}
		
		return personas;
	}
	
	public ArrayList<Empresa> SelectEmpresas() {
		PreparedStatement ps;
		ResultSet rs;
		ArrayList<Empresa> empresas = new ArrayList<>();
		
		String query = "SELECT rnc, nombre, categoria.nombre_categoria, direccion, telefono, municipio.nombre_municipio\n"
				+ "FROM categoria, municipio, empresa\n"
				+ "WHERE categoria.id_categoria = empresa.id_categoria \n"
				+ "AND municipio.id_municipio = empresa.id_municipio";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Empresa m = new Empresa(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				empresas.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empresas;
	}
	
	
	public String SelectProvincia(String municipio) {
		PreparedStatement ps;
		ResultSet rs;
		String provincia = null;

		String query = "SELECT nombre_provincia FROM provincia, municipio\n"
				+ "WHERE provincia.id_provincia = municipio.id_provincia\n"
				+ "AND municipio.nombre_municipio = ?";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			ps.setString(1, municipio);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				provincia = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return provincia;
	}
	
	public int SelectIdPersona(String cedula) {
		PreparedStatement ps;
		ResultSet rs;
		int id = -1;

		String query = "SELECT id_persona FROM persona WHERE cedula = ?";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			ps.setString(1, cedula);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public void InsertIdiomaSolicitudP(String idioma, int idPersona) {
		PreparedStatement ps;
		
		String query = "INSERT INTO idioma_solicitudp values((SELECT id_idioma FROM idioma WHERE descripcion = ?), ?)";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			ps.setString(1, idioma);
			ps.setInt(2, idPersona);
			
			ps.execute();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void InsertSolicitudPersona(SolicitudPersona sp) {
		PreparedStatement ps;
		ResultSet rs;
		int id = 0;
		
		String query = "INSERT INTO solicitud_persona values(?, ?, ?, ?, ?, ?, ?)"
				+ "SELECT SCOPE_IDENTITY()";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			ps.setInt(1, SelectIdPersona(sp.getPerson().getId()));
			ps.setString(2, sp.getEstado());
			ps.setInt(3, sp.getExperiencia());
			ps.setFloat(4, sp.getSueldoMinimo());
			ps.setString(5, sp.getTipoTrabajo());
			ps.setString(6, sp.getLicenciaConducir());
			ps.setString(7, sp.getMovilidad());
			
			rs = ps.executeQuery();
				
			while (rs.next()) {
				id = rs.getInt(1);
			}
			
			for (String i: sp.getIdiomas()) {
				InsertIdiomaSolicitudP(i, id);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		return id;
	}
	
	public ArrayList<String> SelectIdiomaSolicitudP(int idSoliP) {
		PreparedStatement ps;
		ResultSet rs;
		ArrayList<String> idiomas = new ArrayList<>();
		
		String query = "SELECT idioma.descripcion FROM idioma, idioma_solicitudp\n"
				+"WHERE idioma_solicitudp.id_solicitudp = ?\n"
				+"AND idioma.id_idioma = idioma_solicitudp.id_idioma";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			ps.setInt(1, idSoliP);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				idiomas.add(rs.getString(1));
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return idiomas;
	}
	
	public ArrayList<SolicitudPersona> SelectSolicitudPersonas() {
		PreparedStatement ps;
		ResultSet rs;
		ArrayList<SolicitudPersona> solicitudes = new ArrayList<>();
		
		String query = "SELECT cedula, id_solicitudp, experiencia, sueldo_minimo, tipo_trabajo, licencia_conducir, movilidad, estado\n"
				+ "FROM persona, solicitud_persona\n"
				+ "WHERE persona.id_persona = solicitud_persona.id_persona";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Persona p = BuscarPersona(rs.getString(1));
				ArrayList<String> idiomas = SelectIdiomaSolicitudP(rs.getInt(2));
				SolicitudPersona sp = new SolicitudPersona(p, rs.getInt(3), rs.getFloat(4), rs.getString(5), rs.getString(6), rs.getString(7), idiomas);
				sp.setId(rs.getInt(2));
				sp.setEstado(rs.getString(8));
				solicitudes.add(sp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return solicitudes;
	}
	
	public Persona BuscarPersona(String id) {
		for(Persona p: SelectPersonas()) {
			if(p.getId().equalsIgnoreCase(id)) {
				return p;
			}
		}
		return null;
	}
	
	public Empresa SelectEmpresaRnc(String rnc) {
		PreparedStatement ps;
		ResultSet rs;
		Empresa emp = null;

		String query = "SELECT empresa.rnc, empresa.nombre, categoria.nombre_categoria, empresa.direccion, empresa.telefono, municipio.nombre_municipio \n"
				+ "FROM categoria, municipio, empresa \n"
				+ "WHERE empresa.rnc = ?\n"
				+ "AND categoria.id_categoria = empresa.id_categoria\n"
				+ "AND municipio.id_municipio = empresa.id_municipio";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			ps.setString(1, rnc);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				emp = new Empresa(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}
	
	public int SelectIdEmpresa(String rnc) {
		PreparedStatement ps;
		ResultSet rs;
		int id = -1;

		String query = "SELECT id_empresa FROM empresa WHERE rnc = ?";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			ps.setString(1, rnc);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public void InsertSolicitudEmpresa(SolicitudEmpresa se) {
		PreparedStatement ps;
		ResultSet rs;
		int id = 0;
		
		String query = "INSERT INTO solicitud_empresa(id_empresa, cantidad, experiencia, sueldo_maximo, tipo_trabajo, licencia_conducir, tipo_empleado, titulo_empleado, edad_minima)"
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?)"
				+ "SELECT SCOPE_IDENTITY()";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			ps.setInt(1, SelectIdEmpresa(se.getMiEmpresa().getIdEmpresa()));
			ps.setInt(2, se.getCantidad());
			ps.setInt(3, se.getExperienciaSolicitud());
			ps.setFloat(4, se.getSueldoMaximoSolicitud());
			ps.setString(5, se.getTipoTrabajoSolicitud());
			ps.setString(6, se.getLincenciaConducirSolicitud());
			ps.setString(7, se.getTipoEmpleado());
			ps.setString(8, se.getTituloEmpleado());
			ps.setInt(9, se.getEdadMinima());
			
			rs = ps.executeQuery();
				
			while (rs.next()) {
				id = rs.getInt(1);
			}
			
			for (String i: se.getIdiomasSolicitud()) {
				InsertIdiomaSolicitudE(i, id);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		return id;
	}
	
	public void InsertIdiomaSolicitudE(String idioma, int idSoliE) {
		PreparedStatement ps;
		
		String query = "INSERT INTO idioma_solicitude values((SELECT id_idioma FROM idioma WHERE descripcion = ?), ?)";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			ps.setString(1, idioma);
			ps.setInt(2, idSoliE);
			
			ps.execute();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> SelectIdiomaSolicitudE(int idSoliE) {
		PreparedStatement ps;
		ResultSet rs;
		ArrayList<String> idiomas = new ArrayList<>();
		
		String query = "SELECT idioma.descripcion FROM idioma, idioma_solicitude\n"
				+"WHERE idioma_solicitude.id_solicitude = ?\n"
				+"AND idioma.id_idioma = idioma_solicitude.id_idioma";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			ps.setInt(1, idSoliE);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				idiomas.add(rs.getString(1));
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return idiomas;
	}
	
	public Empresa SelectEmpresaId(int id) {
		PreparedStatement ps;
		ResultSet rs;
		Empresa emp = null;

		String query = "SELECT empresa.rnc, empresa.nombre, categoria.nombre_categoria, empresa.direccion, empresa.telefono, municipio.nombre_municipio \n"
				+ "FROM categoria, municipio, empresa \n"
				+ "WHERE empresa.id_empresa = ?\n"
				+ "AND categoria.id_categoria = empresa.id_categoria\n"
				+ "AND municipio.id_municipio = empresa.id_municipio";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				emp = new Empresa(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}
	
	public ArrayList<SolicitudEmpresa> SelectSolicitudEmpresas() {
		PreparedStatement ps;
		ResultSet rs;
		ArrayList<SolicitudEmpresa> solicitudes = new ArrayList<>();
		
		String query = "SELECT * FROM solicitud_empresa";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Empresa e = SelectEmpresaId(rs.getInt(2));
				ArrayList<String> idiomas = SelectIdiomaSolicitudE(rs.getInt(1));
				SolicitudEmpresa se = new SolicitudEmpresa(e, rs.getInt(4), rs.getInt(5), rs.getFloat(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), idiomas);
				se.setIdSolicitud(rs.getInt(1));
				se.setEstado(rs.getBoolean(3));
				solicitudes.add(se);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return solicitudes;
	}
	
	public SolicitudEmpresa SelectSoliEmpresaById(int id) {
		PreparedStatement ps;
		ResultSet rs;
		SolicitudEmpresa se = null;
		
		String query = "SELECT * FROM solicitud_empresa WHERE id_solicitude = ?";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Empresa e = SelectEmpresaId(rs.getInt(2));
				ArrayList<String> idiomas = SelectIdiomaSolicitudE(rs.getInt(1));
				se = new SolicitudEmpresa(e, rs.getInt(4), rs.getInt(5), rs.getFloat(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), idiomas);
				se.setIdSolicitud(rs.getInt(1));
				se.setEstado(rs.getBoolean(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return se;
	}
	
	public SolicitudPersona SelectSoliPersonaById(int id, String cedula) {
		PreparedStatement ps;
		ResultSet rs;
		SolicitudPersona sp = null;
		
		String query = "SELECT * FROM solicitud_persona WHERE id_solicitudp = ?";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Persona p = BuscarPersona(cedula);
				ArrayList<String> idiomas = SelectIdiomaSolicitudP(rs.getInt(1));
				sp = new SolicitudPersona(p, rs.getInt(4), rs.getFloat(5), rs.getString(6), rs.getString(7), rs.getString(8), idiomas);
				sp.setId(rs.getInt(1));
				sp.setEstado(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sp;
	}
	
	public void UpdateSoliEmpresa(SolicitudEmpresa se) {
		PreparedStatement ps;
		
		String query = "UPDATE solicitud_empresa\n"
				+ "SET cantidad = ?\n"
				+ "WHERE id_solicitude = ?";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
//			int id = SelectIdPersona(p.getId());
			ps.setInt(1, se.getCantidad());
			ps.setInt(2, se.getIdSolicitud());
			ps.execute();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void UpdateSoliPersona(SolicitudPersona sp) {
		PreparedStatement ps;
		
		String query = "UPDATE solicitud_persona\n"
				+ "SET estado = ?\n"
				+ "WHERE id_solicitudp = ?";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
//			int id = SelectIdPersona(p.getId());
			ps.setString(1, sp.getEstado());
			ps.setInt(2, sp.getId());
			ps.execute();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void UpdatePersonaToDesempleado(Persona p) {
		PreparedStatement ps;
		
		String query = "UPDATE persona\n"
				+ "SET status = 0\n"
				+ "WHERE id_persona = ?";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			int id = SelectIdPersona(p.getId());
			ps.setInt(1, id);
			ps.execute();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void UpdatePersonaToEmpleado(Persona p) {
		PreparedStatement ps;
		
		String query = "UPDATE persona\n"
				+ "SET status = 1\n"
				+ "WHERE id_persona = ?";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			int id = SelectIdPersona(p.getId());
			ps.setInt(1, id);
			ps.execute();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void InsertMatchSolicitudes(int idse, int idsp) {
		PreparedStatement ps;
		
		String query = "INSERT INTO match_solicitudes (id_solicitude, id_solicitudp) values(?, ?)";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			ps.setInt(1, idse);
			ps.setInt(2, idsp);
			
			ps.execute();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String[]> SelectMatchSolicitudes() {
		PreparedStatement ps;
		ResultSet rs;
		ArrayList<String[]> matches = new ArrayList<>();
		
		String query = "SELECT empresa.nombre, solicitud_empresa.id_solicitude, persona.nombre, solicitud_persona.id_solicitudp, CONVERT(date, fecha) \n"
				+ "FROM empresa, persona, solicitud_empresa, solicitud_persona, match_solicitudes\n"
				+ "WHERE empresa.id_empresa = solicitud_empresa.id_empresa\n"
				+ "AND persona.id_persona = solicitud_persona.id_persona \n"
				+ "AND solicitud_empresa.id_solicitude = match_solicitudes.id_solicitude\n"
				+ "AND solicitud_persona.id_solicitudp = match_solicitudes.id_solicitudp";
		
		try {
			ps = SQLConnection.getConnection().prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String[] s = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
				matches.add(s);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return matches;
	}
	
}
