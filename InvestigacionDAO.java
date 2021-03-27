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

import estructuraTP.modelo.Investigacion;

public class InvestigacionDAO {
	
	String timezone = "&serverTimezone=UTC";
	String password="";
	
	public ArrayList<Investigacion> todasLasInvestigaciones()	{
		ArrayList<Investigacion> todasLasInvestigaciones = new ArrayList<Investigacion>();
		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false"+timezone;
		Connection conexion = null;
		try { 
			conexion = DriverManager.getConnection(url, "root", password);
			CategoriaDAO cDAO=new CategoriaDAO();
			String sql = "SELECT `id_investigacion`,`categoria_investigacion`,`tema`,`palabra_clave`,`titulo`, `epigrafe`, `contenido`, `fecha` FROM `investigacion`";
			Statement stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id_investigacion");
				String categoria = cDAO.recuperarNombreCategoria(rs.getInt("categoria_investigacion"));
				String tema = rs.getString("tema");
				String palabra_clave = rs.getString("palabra_clave");
				String titulo = rs.getString("titulo");
				String epigrafe = rs.getString("epigrafe");
				String contenido = rs.getString("contenido");
				LocalDate fecha = (rs.getDate("fecha").toLocalDate());
				
				Investigacion unaInvestigacion = new Investigacion(id,categoria,tema,palabra_clave, titulo, epigrafe, contenido, fecha);
				todasLasInvestigaciones.add(unaInvestigacion);
				
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
		return todasLasInvestigaciones;
	}
	
	public ArrayList<Investigacion> Investigaciones()	{
		ArrayList<Investigacion> Investigaciones = new ArrayList<Investigacion>();
		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false"+timezone;
		Connection conexion = null;
		try { 
			conexion = DriverManager.getConnection(url, "root", password);
			String sql = "SELECT `titulo`, `epigrafe`, `contenido`, `fecha` FROM `investigacion`";
			Statement stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				
				String titulo = rs.getString("titulo");
				String epigrafe = rs.getString("epigrafe");
				String contenido = rs.getString("contenido");
				LocalDate fecha = (rs.getDate("fecha").toLocalDate());
				
				Investigacion unaInvestigacion = new Investigacion(titulo, epigrafe, contenido, fecha);
				Investigaciones.add(unaInvestigacion);
				
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
		return Investigaciones;
	}
	
	public void insercion(Investigacion i) {

		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false"+timezone;

		Connection conexion = null;

		try {
			conexion = DriverManager.getConnection(url, "root", password);
			CategoriaDAO cDAO=new CategoriaDAO();
			eliminacion(i);
			String sql = "INSERT INTO `investigacion`(`categoria_investigacion`,`tema`,`palabra_clave`,`titulo`,`epigrafe`,`contenido`,`fecha`) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1,cDAO.recuperarIdCategoria(i.getCategoria()));
			ps.setString(2, i.getTema());
			ps.setString(3, i.getPalabraClave());
			ps.setString(4, i.getTitulo());
			ps.setString(5, i.getEpigrafe());
			ps.setString(6, i.getContenido());
			ps.setDate(7, Date.valueOf(i.getFecha()));

			int cantidadDeRegistrosafectados = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void eliminacion(Investigacion i) {

		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false"+timezone;

		Connection conexion = null;

		try {
			conexion = DriverManager.getConnection(url, "root", password);
			String sql = "DELETE FROM `investigacion` WHERE `id_investigacion`= ? ";

			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, i.getId());

			int cantidadDeRegistrosafectados = ps.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	public ArrayList<Investigacion> buscar(String tem) {
		ArrayList<Investigacion> investigaciones = new ArrayList<Investigacion>();
		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false"+timezone;
		Connection conexion = null;

		try {
			conexion = DriverManager.getConnection(url, "root", password);
			CategoriaDAO cDAO=new CategoriaDAO();
			String sql = "SELECT * FROM `investigacion` WHERE `tema` LIKE ?";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, "%" + tem + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id=rs.getInt("id_investigacion");
				String categoria = cDAO.recuperarNombreCategoria( rs.getInt("categoria_investigacion"));
				String tema = rs.getString("tema");
				String palabra_clave = rs.getString("palabra_clave");
				String titulo = rs.getString("titulo");
				String epigrafe = rs.getString("epigrafe");
				String contenido = rs.getString("contenido");
				LocalDate fecha = rs.getDate("fecha").toLocalDate();
				Investigacion unaInvestigacion = new Investigacion(id,categoria, tema, palabra_clave, titulo, epigrafe,contenido, fecha);
				investigaciones.add(unaInvestigacion);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return investigaciones;

	}
}