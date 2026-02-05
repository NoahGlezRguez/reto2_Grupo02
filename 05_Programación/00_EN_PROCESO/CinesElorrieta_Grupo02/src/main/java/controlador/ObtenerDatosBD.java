package controlador;

import java.sql.*;
import modelo.*;
import vista.*;

/**
 * Contiene los métodos necesarios para consultar y obtener datos para
 * almacenarlos en memoria durante la ejecucion del programa.
 */
public class ObtenerDatosBD {

	/**
	 * Guarda los datos en un obejto, de la pelicula seleccionada por el usuario a traves
	 * de una consulta
	 * @param idPeliElegida - id de la pelicula seleccionada
	 * @return - objeto de la pelicula con todos sus datos
	 */
	public static Pelicula consultarPeliculaElegida(int idPeliElegida) {

		String consulta = """
				select p.NomPeli, p.duracion, g.nomgen
				from pelicula p join genero g on p.idgen = g.idgen
				where p.IDPeli = ?""";
		Pelicula peliculaElegida = new Pelicula();

		Connection conexion = null;
		PreparedStatement sentencia = null;
		ResultSet result = null;

		try {
			conexion = ConsultarDatosBD.conectarConBD();
			sentencia = conexion.prepareStatement(consulta);

			sentencia.setInt(1, idPeliElegida);
			result = sentencia.executeQuery();

			result.next();
			peliculaElegida.setIdPeli(idPeliElegida);
			peliculaElegida.setNombrePeli(result.getString("NomPeli"));
			peliculaElegida.setGenero(result.getString("NomGen"));
			peliculaElegida.setDuracion(result.getInt("Duracion"));

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			System.err.println(e2.getMessage());
		} finally {

			try {
				if (result != null)
					result.close();
				if (sentencia != null)
					sentencia.close();
				if (conexion != null)
					conexion.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return (peliculaElegida);
	}
	
	/**
	 * Este método consulta el login y verifica si el usuario consta en la base de datos 
	 * @param credenciales del usuario
	 * @return Objeto cliente con datos si existe, en caso contrario, con valor null
	 */
	public static Cliente Consultarlogin(String dni, String password) {

		Connection conexion = null;
		PreparedStatement sentencia = null;
		ResultSet result = null;
		String consulta = """
					select *
					from cliente
					where dni = ? and userpassword = md5(?)
				""";
		Cliente consultado = new Cliente();

		try {

			conexion = ConsultarDatosBD.conectarConBD();
			sentencia = conexion.prepareStatement(consulta);

			sentencia.setString(1, dni);
			sentencia.setString(2, password);
			
			result = sentencia.executeQuery();

			if (result.next()) {

				consultado.setDni(result.getString("DNI"));
				consultado.setNomCliente(result.getString("nomcli"));
				consultado.setApellidos(result.getString("ape"));
				consultado.setEmail(result.getString("mail"));

			}

			else {
				MostrarMsg.errores(4);
				consultado = null;
			} 
		} catch (SQLException e) {
				e.printStackTrace();
		} catch (NullPointerException e2) {
			System.err.println(e2.getMessage());
		} finally {
			try {
				if (result != null)
					result.close();
				if (sentencia != null)
					sentencia.close();
				if (conexion != null)
					conexion.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return consultado;
	}
	
	/**
	 * Consulta y vuelca en un objeto los datos de la sesion elegida por el usuario
	 * en un objeto sesion
	 * @param idSesionElegida - id de la sesion que se desea consultar
	 * @return objeto sesion con todos los datos asignados
	 */
	public static Sesion consultarSesionElegida(int idSesionElegida) {
		String consulta = """
					select *
					from sesion
					where IDSesion = ?
				""";
		Sesion sesionElegida = new Sesion();

		Connection conexion = null;
		PreparedStatement sentencia = null;
		ResultSet result = null;

		try {
			conexion = ConsultarDatosBD.conectarConBD();
			sentencia = conexion.prepareStatement(consulta);

			sentencia.setInt(1, idSesionElegida);
			result = sentencia.executeQuery();

			result.next();

			sesionElegida.setIdSesion(idSesionElegida);
			sesionElegida.setFecSesion(result.getString("fec"));
			sesionElegida.setHoraInicio(result.getString("hora_ini"));
			sesionElegida.setHoraFin(result.getString("hora_fin"));
			sesionElegida.setPrecio(result.getDouble("precio"));
			sesionElegida.setSalaSesion(result.getInt("NumSala"));
			sesionElegida.setPeliculaSesion(result.getInt("IDPeli"));

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			System.err.println(e2.getMessage());
		} finally {

			try {
				if (result != null)
					result.close();
				if (sentencia != null)
					sentencia.close();
				if (conexion != null)
					conexion.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return (sesionElegida);
	}

	/**
	 * Esta sesion consulta la sala de una sesion en concreto y guarda todos sus valores en
	 * un objeto
	 * @param IdSesion - la sesion de la que se consulta la sala
	 * @return objeto sala - con valores consultados
	 */
	public static Sala consultarSala(int IdSesion) {
		Sala sala = new Sala();
		String consulta = """
					select sa.*
					from sala sa join sesion se on sa.numsala = se.numsala
					where IDSesion = ?
				""";

		Connection conexion = null;
		PreparedStatement sentencia = null;
		ResultSet result = null;
		try {
			conexion = ConsultarDatosBD.conectarConBD();
			sentencia = conexion.prepareStatement(consulta);

			sentencia.setInt(1, IdSesion);
			result = sentencia.executeQuery();

			result.next();

			sala.setNumSala(result.getInt("numSala"));
			sala.setAforoSala(result.getInt("aforo"));

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (NullPointerException e2) {
			System.err.println(e2.getMessage());
		}  finally {

			try {
				if (result != null)
					result.close();
				if (sentencia != null)
					sentencia.close();
				if (conexion != null)
					conexion.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return (sala);
	}

	/**
	 * Este metodo consulta la ultima compra para recoger el id de esta
	 * @return idCompra - el id consultado
	 */
	public static int consultarCompraRealizada() {
		
		int idCompra = -1;
		
		String consulta = """
					select idCompra
					from compra
					order by FecCompra desc
					limit 1
				""";

		Connection conexion = null;
		Statement sentencia = null;
		ResultSet result = null;
		try {
			conexion = ConsultarDatosBD.conectarConBD();
			sentencia = conexion.createStatement();


			result = sentencia.executeQuery(consulta);

			result.next();
			idCompra = result.getInt("IDCompra");

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (NullPointerException e2) {
			System.err.println(e2.getMessage());
		} finally {

			try {
				if (result != null)
					result.close();
				if (sentencia != null)
					sentencia.close();
				if (conexion != null)
					conexion.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return (idCompra);
	}
		
}
