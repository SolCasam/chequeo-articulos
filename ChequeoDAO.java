package estructuraTP.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;


import estructuraTP.modelo.Chequeo;

public class ChequeoDAO {
	
	String timezone = "&serverTimezone=UTC";
	String password="";
	
	public ArrayList<Chequeo> todosLosChequeos()	{
		ArrayList<Chequeo> Chequeos = new ArrayList<Chequeo>();
		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false"+timezone;
		Connection conexion = null;
		try { 
			conexion = DriverManager.getConnection(url, "root", password);
			CategoriaDAO cDAO=new CategoriaDAO();
			String sql = "SELECT `id_chequeo`, `frase`, `fecha`, `medio`, `enlace`, `palabra_clave`, `verificacion_chequeo`, `categoria_chequeo` FROM `chequeo`";
			Statement stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				
				int id = rs.getInt("id_chequeo");
				String frase = rs.getString("frase");
				LocalDate fecha = (rs.getDate("fecha").toLocalDate());
				String medio = rs.getString("medio");
				String enlace = rs.getString("enlace");
				String palabraClave = rs.getString("palabra_clave");
				String verificacion = recuperarNombreVerificacion(rs.getInt("verificacion_chequeo"));
				String categoria = cDAO.recuperarNombreCategoria(rs.getInt("categoria_chequeo"));
				
				Chequeo unChequeo = new Chequeo(id, frase, fecha, medio, enlace, palabraClave, verificacion, categoria);
				Chequeos.add(unChequeo);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(conexion != null)
					conexion.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return Chequeos;
	}

	public ArrayList<Chequeo> todosLosChequeosxCategoria(String cat)	{
		ArrayList<Chequeo> Chequeos = new ArrayList<Chequeo>();
		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false"+timezone;
		Connection conexion = null;
		try { 
			conexion = DriverManager.getConnection(url, "root", password);
			CategoriaDAO cDAO=new CategoriaDAO();
			String sql = "SELECT `id_chequeo`, `frase`, `fecha`, `medio`, `enlace`, `palabra_clave`, `verificacion_chequeo`, `categoria_chequeo` FROM `chequeo` WHERE categoria_chequeo=?";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1,cDAO.recuperarIdCategoria(cat));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id_chequeo");
				String frase = rs.getString("frase");
				LocalDate fecha = (rs.getDate("fecha").toLocalDate());
				String medio = rs.getString("medio");
				String enlace = rs.getString("enlace");
				String palabraClave = rs.getString("palabra_clave");
				String verificacion = recuperarNombreVerificacion(rs.getInt("verificacion_chequeo"));
				String categoria = cDAO.recuperarNombreCategoria(rs.getInt("categoria_chequeo"));
				
				Chequeo unChequeo = new Chequeo(id, frase, fecha, medio, enlace, palabraClave, verificacion, categoria);
				Chequeos.add(unChequeo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(conexion != null)
					conexion.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return Chequeos;
	}
	
	/**
	 * @param c Un chequeo
	 * @apiNote Registra un chequeo en la BDDR
	 */
	public void registrarChequeo(Chequeo c) {
		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false"+timezone;
		Connection conexion = null;
		try { 
			conexion = DriverManager.getConnection(url, "root", password);
			CategoriaDAO cDAO=new CategoriaDAO();
			eliminarChequeo(c);
			String sql = "INSERT INTO `chequeo` (`frase`, `fecha`, `medio`, `enlace`, `palabra_clave`, `verificacion_chequeo`, `categoria_chequeo`)"
					+ "VALUES(?,?,?,?,?,?,?)";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, c.getFrase());
			ps.setDate(2, Date.valueOf(c.getFecha()));
			ps.setString(3, c.getMedio());
			ps.setString(4, c.getEnlace());
			ps.setString(5, c.getPalabraClave());
			ps.setInt(6, recuperarIdVerificacion(c));
			ps.setInt(7, cDAO.recuperarIdCategoria(c.getCategoria()));
			
			int regAfect=ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(conexion != null)
					conexion.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public String recuperarNombreVerificacion(int id) {
		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false"+timezone;
		Connection conexion = null;
		String nombreVerificacion = "";
		try { 
			conexion = DriverManager.getConnection(url, "root", password);
			String sql = "SELECT `nombre_verificacion` FROM `verificacion` WHERE `id_verificacion` = ?";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				nombreVerificacion=rs.getString(1);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(conexion != null)
					conexion.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return nombreVerificacion;
	}
	
	public int recuperarIdVerificacion(Chequeo c) {
		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false"+timezone;
		Connection conexion = null;
		int idVerificacion=0;
		try { 
			conexion = DriverManager.getConnection(url, "root", password);
			String sql = "SELECT `id_verificacion` FROM `verificacion` WHERE `nombre_verificacion` = ?";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, c.getVerificacion());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				idVerificacion=rs.getInt(1);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(conexion != null)
					conexion.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return idVerificacion;
	}
	
	
	
	public void eliminarChequeo(Chequeo c) {
		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false"+timezone;
		Connection conexion = null;
		try { 
			conexion = DriverManager.getConnection(url, "root", password);
			
			String sql = "DELETE FROM `chequeo` WHERE `id_chequeo` = ?";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, c.getId());
			
			int regAfect=ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(conexion != null)
					conexion.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public ArrayList<Chequeo> buscarChequeos(String palClave)	{
		ArrayList<Chequeo> Chequeos = new ArrayList<Chequeo>();
		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false"+timezone;
		Connection conexion = null;
		try { 
			conexion = DriverManager.getConnection(url, "root", password);
			CategoriaDAO cDAO=new CategoriaDAO();
			String sql = "SELECT * FROM `chequeo` WHERE `palabra_clave` LIKE ?";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, "%" + palClave + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				int id = rs.getInt("id_chequeo");
				String frase = rs.getString("frase");
				LocalDate fecha = (rs.getDate("fecha").toLocalDate());
				String medio = rs.getString("medio");
				String enlace = rs.getString("enlace");
				String palabraClave = rs.getString("palabra_clave");
				String verificacion = recuperarNombreVerificacion(Integer.parseInt(rs.getString("verificacion_chequeo")));
				String categoria = cDAO.recuperarNombreCategoria(rs.getInt("categoria_chequeo"));
				Chequeo unChequeo = new Chequeo(id, frase, fecha, medio, enlace, palabraClave, verificacion, categoria);
				Chequeos.add(unChequeo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(conexion != null)
					conexion.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return Chequeos;
	}
}
