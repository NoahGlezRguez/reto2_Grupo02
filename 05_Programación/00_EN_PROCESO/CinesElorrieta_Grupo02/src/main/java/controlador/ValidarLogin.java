package controlador;

import java.sql.*;

public class ValidarLogin {

	public static boolean validar(String usuario, String password) {
		Connection conexion = ConsultarBD.conectarConBD();
		Statement sentencia = null;
		ResultSet r = null;
		boolean valido = false;

		try {
			sentencia = conexion.createStatement();
			r = sentencia.executeQuery("select NomCli, userpassword from cliente;");

			while (r.next()) {
				String u = r.getString("NomCli");
				String p = r.getString("userpassword");

				if (u.equals(usuario) && p.equals(password)) {
					valido = true;
					break;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return valido;
	}
}
