package estructuraTP.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import estructuraTP.modelo.Sugerencia;

public class SugerenciaDAO {
	
	String timezone = "&serverTimezone=UTC";
	String password="";
	
	public void delete(int id) {

		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false"+timezone;
		Connection c = null;
		try {
			c = DriverManager.getConnection(url, "root", password);

			String sql = "DELETE FROM repositorio WHERE `id_repositorio`=?";
			PreparedStatement pS = c.prepareStatement(sql);
			pS.setInt(1, id);
			int cantRegAfectados = pS.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Sugerencia> mostrarTodo() {
		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false"+timezone;
		Connection c = null;
		ArrayList<Sugerencia> Sugerencias = new ArrayList<Sugerencia>();
		try {
			c = DriverManager.getConnection(url, "root", password);
			String sql = "SELECT `id_repositorio`,`medio`,`fecha`,`enlace`,`frase` FROM repositorio";
			Statement ps = c.createStatement();
			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {

				int id = rs.getInt("id_repositorio");
				String frase = rs.getString("frase");
				String medio = rs.getString("medio");
				String enlace = rs.getString("enlace");
				LocalDate fecha = (rs.getDate("fecha").toLocalDate());
				
				Sugerencia s = new Sugerencia(id,frase,medio,enlace,fecha);
				Sugerencias.add(s);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Sugerencias;
	}
	
	public void eliminarSugerencia(Sugerencia s) {
		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false"+timezone;
		Connection conexion = null;
		try { 
			conexion = DriverManager.getConnection(url, "root", password);
			
			String sql = "DELETE FROM `repositorio` WHERE `id_repositorio` = ?";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, s.getId());
			
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

}
