package estructuraTP.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaDAO {

	String timezone = "&serverTimezone=UTC";
	String password="";

	public ArrayList<String> todos() {
		ArrayList<String> todos = new ArrayList<String>();
		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false" + timezone;
		ResultSet rs = null;
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, "root", password);

			String sql = "SELECT `id_categoria`, `nombre_categoria` FROM `categoria`";
			PreparedStatement ps = conexion.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				todos.add(rs.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.toString());

		} finally {
			try {
				if (conexion != null)
					conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return todos;
	}

	public int recuperarIdCategoria(String categoria) {
		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false" + timezone;
		Connection conexion = null;
		int idcategoria = 0;
		try {
			conexion = DriverManager.getConnection(url, "root", password);
			String sql = "SELECT `id_categoria` FROM `categoria` WHERE `nombre_categoria` = ?";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, categoria);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				idcategoria = rs.getInt(1);
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
		return idcategoria;
	}

	public String recuperarNombreCategoria(int id) {
		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false" + timezone;
		Connection conexion = null;
		String cat = "";
		try {
			conexion = DriverManager.getConnection(url, "root", password);
			String sql = "SELECT `nombre_categoria` FROM `categoria` WHERE `id_categoria` = ?";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				cat = rs.getString(1);	
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
		return cat;
	}

	public ArrayList<String> verificacion() {
		ArrayList<String> verificacion = new ArrayList<String>();
		String url = "jdbc:mysql://localhost:3306/tpchequeado?useSSL=false" + timezone;
		ResultSet rs = null;
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, "root", password);

			String sql = "SELECT `nombre_verificacion` FROM `verificacion`";
			PreparedStatement ps = conexion.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				verificacion.add(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.toString());

		} finally {
			try {
				if (conexion != null)
					conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return verificacion;
	}

}
