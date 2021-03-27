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
import estructuraTP.modelo.Explicacion;

public class ExplicacionDAO {

	String timezone = "&serverTimezone=UTC";
	String password="";

	public ArrayList<Explicacion> Explicaciones() {
		ArrayList<Explicacion> Explicaciones = new ArrayList<Explicacion>();
		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false" + timezone;
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, "root", password);
			String sql = "SELECT `titulo`, `epigrafe`, `contenido`, `fecha` FROM `explicacion`";
			Statement stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {

				String titulo = rs.getString("titulo");
				String epigrafe = rs.getString("epigrafe");
				String contenido = rs.getString("contenido");
				LocalDate fecha = (rs.getDate("fecha").toLocalDate());

				Explicacion unaExplicacion = new Explicacion(titulo, epigrafe, contenido, fecha);
				Explicaciones.add(unaExplicacion);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conexion != null)
					conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Explicaciones;
	}

	public void insert(Explicacion e) {
		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false" + timezone;
		Connection c = null;
		try {
			c = DriverManager.getConnection(url, "root", password);
			delete(e.getId());
			String sql = "INSERT INTO `explicacion`(`titulo`,`epigrafe`,`contenido`,`fecha`) VALUES (?,?,?,?)";
			PreparedStatement prepareStatement = c.prepareStatement(sql);
			prepareStatement.setString(1, e.gettitulo());
			prepareStatement.setString(2, e.getepigrafe());
			prepareStatement.setString(3, e.getcontenido());
			prepareStatement.setDate(4, Date.valueOf(e.getfecha()));
			int cantidadDeRegistrosafectados = prepareStatement.executeUpdate();
		} catch (SQLException k) {
			k.printStackTrace();
		} finally {
			try {
				if (c != null) {
					c.close();
				}

			} catch (SQLException k) {
				k.printStackTrace();
			}
		}
	}

	public int conseguirIdExplicacion(Explicacion ex) {
		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false" + timezone;
		Connection c = null;
		int id=0;
		try {
			c = DriverManager.getConnection(url, "root", password);
			String sql = "SELECT `id_explicacion` FROM `explicacion` WHERE titulo=? AND epigrafe=? AND contenido=? AND fecha=? ORDER BY id_explicacion DESC";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, ex.gettitulo());
			ps.setString(2, ex.getepigrafe());
			ps.setString(3, ex.getcontenido());
			ps.setDate(4, Date.valueOf(ex.getfecha()));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
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
		return id;
	}

	public void insertchequeo_explicacion(int idExp, ArrayList<Chequeo> chequeos) {
		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false" + timezone;
		Connection c = null;
		try {
			c = DriverManager.getConnection(url, "root", password);
			for (Chequeo ch : chequeos) {
				String sql = "INSERT INTO `chequeo_explicacion`(`chequeo`,`explicacion`) VALUES (?,?)";
				PreparedStatement prepareStatement = c.prepareStatement(sql);
				
				prepareStatement.setInt(1, ch.getId());
				prepareStatement.setInt(2, idExp);
				int cantidadDeRegistrosafectados = prepareStatement.executeUpdate();
			}
		} catch (SQLException k) {
			k.printStackTrace();
		} finally {
			try {
				if (c != null) {
					c.close();
				}

			} catch (SQLException k) {
				k.printStackTrace();
			}
		}
	}

	public ArrayList<Explicacion> todes() {
		ArrayList<Explicacion> explicaciones = new ArrayList<Explicacion>();
		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false" + timezone;
		Connection c = null;
		try {
			c = DriverManager.getConnection(url, "root", password);
			String sql = "SELECT `id_explicacion`,`titulo`,`epigrafe`,`contenido`,`fecha` FROM `explicacion`";
			Statement stnt = c.createStatement();
			ResultSet rs = stnt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt(1);
				String Titulo = rs.getString(2);
				String Epigrafe = rs.getString(3);
				String Contenido = rs.getString(4);
				LocalDate Fecha = rs.getDate(5).toLocalDate();
				Explicacion exp = new Explicacion(id, Titulo, Epigrafe, Contenido, Fecha);
				explicaciones.add(exp);
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
		return explicaciones;
	}

	public void delete(int id) {

		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false" + timezone;
		Connection c = null;
		try {
			c = DriverManager.getConnection(url, "root", password);
			String sql = "DELETE FROM `explicacion` WHERE  `id_explicacion` =?";
			PreparedStatement prepareStatement = c.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			int cantidadDeRegistrosafectados = prepareStatement.executeUpdate();

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

}
