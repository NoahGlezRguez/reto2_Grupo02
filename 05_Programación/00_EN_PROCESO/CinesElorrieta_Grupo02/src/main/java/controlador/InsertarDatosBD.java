package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Cliente;
import modelo.Entrada;
import vista.MostrarMsg;

public class InsertarDatosBD {

public static void insertarEntradas(ArrayList<Entrada> entradas, int idCompra) {
		
		Connection conexion = null;
		PreparedStatement sentencia = null;
		int filasAfectadas = 0;
		
		String consulta = """
					insert into entrada (CantPersonas, importe, idSesion, idCompra)
					values(?, ?, ?, ?)
				""";
		try {
			conexion = ConsultarDatosBD.conectarConBD();
			sentencia = conexion.prepareStatement(consulta);
			
			for (int i = 0; i < entradas.size(); i++) {
				
				sentencia.setInt(1, entradas.get(i).getNumPersonas());
				sentencia.setDouble(2, entradas.get(i).getNumPersonas() * entradas.get(i).getSesionEntrada().getPrecio());
				sentencia.setInt(3, entradas.get(i).getSesionEntrada().getIdSesion());
				sentencia.setInt(4, idCompra);
				
				filasAfectadas = sentencia.executeUpdate();
			
				if (filasAfectadas < 1) 
					MostrarMsg.errores(1);
			}

		}  catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			System.err.println(e2.getMessage());
		} finally {

			try {
				if (sentencia != null)
					sentencia.close();
				if (conexion != null)
					conexion.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public static void insertarCompra(String plataforma, double descuento,
									double total, String dni) {
		Connection conexion = null;
		PreparedStatement sentencia = null;
		int filasAfectadas = 0;
		
		String consulta = """
				insert into compra (plataforma, descuento, total, dni)
				values(?, ?, ?, ?)
				""";
		try {
			conexion = ConsultarDatosBD.conectarConBD();
			sentencia = conexion.prepareStatement(consulta);
			
			sentencia.setString(1, plataforma);
			sentencia.setDouble(2, descuento);
			sentencia.setDouble(3, total);
			sentencia.setString(4, dni);
			
			filasAfectadas = sentencia.executeUpdate();

			if (filasAfectadas < 1) 
				MostrarMsg.errores(1);

		}  catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			System.err.println(e2.getMessage());
		}  finally {

			try {
				if (sentencia != null)
					sentencia.close();
				if (conexion != null)
					conexion.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
	
	
	/**
	 * este método inserta un nuevo cliente en la BD
	 * 
	 * @param Objeto Cliente
	 * @return boolean <br>
	 *         <b>true</b> inserción correcta <br>
	 *         <b>false </b> error de comunicación
	 */
	public static void InsertarNuevoUsuario(Cliente consultado, String pw) {

		Connection conexion = null;
		PreparedStatement sentencia = null;
		int result = 0;// en la insercion de datos el result es el numero de rows affected
		String dni = consultado.getDni();
		String nom = consultado.getNomCliente();
		String ape = consultado.getApellidos();
		String mail = consultado.getEmail();

		// verificar si funciona así el md5
		String consulta = """
					insert into cliente
					values(?, ?, ?, ?, md5(?))
				""";

		try {

			conexion = ConsultarDatosBD.conectarConBD();
			sentencia = conexion.prepareStatement(consulta);

			sentencia.setString(1, dni);
			sentencia.setString(2, nom);
			sentencia.setString(3, ape);
			sentencia.setString(4, mail);
			sentencia.setString(5, pw);
			
			result = sentencia.executeUpdate();

			if (result < 1) {
				MostrarMsg.errores(1);
				consultado = null;
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			System.err.println(e2.getMessage());
		}  finally {
			
			try {
				if (sentencia != null)
					sentencia.close();
				if (conexion != null)
					conexion.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
