package controlador;

import java.sql.*;
import java.util.ArrayList;

import modelo.*;

public class ConsultarBD {

	//private static String	rutaBD = "jdbc:mysql://localhost:33060/cine_elorrieta";//ajustar a la ruta real 
	
	//private static String	user = "dam_v";
	//private static String	pw = "Elorrieta00-";
	private static String	rutaBD = "jdbc:mysql://127.0.0.1:3306/cine_elorrieta";
	private static String	user = "DAM_v";
	private static String	pw = "37E836Zy!!184";
	
	public static Connection conectarConBD() {
		
		Connection	conexion = null;
		
		try {
			conexion = DriverManager.getConnection(rutaBD, user, pw); //como es una variable local aqui su valor siempre se null
		
		} catch(SQLException excpsql) {
			System.out.println("Error, no se pudo realizar la conexión con la base de datos.\n");
			System.out.println("SQLException: " + excpsql.getMessage());
	        System.out.println("SQLState: " + excpsql.getSQLState());
	        System.out.println("VendorError: " + excpsql.getErrorCode());
		}
		return(conexion);
	}

	public static ArrayList<Pelicula> consultarCartelera() { 
		
		ArrayList<Pelicula> 	cartelera = new ArrayList<>();
		Pelicula				peliculaOfertada = null;
		String				consulta = 	"select distinct p.idpeli, p.NomPeli, p.duracion, g.nomgen "
										+ "from sesion s "
										+ "join pelicula p on s.IDPeli = p.IDPeli "
										+ "join genero g on p.IDgen = g.idgen "
										+ "where fec >= current_timestamp()";
		Connection 	conexion;
		Statement	sentencia = null;
		ResultSet 	result = null;
		
		try {
			conexion = ConsultarBD.conectarConBD();
			sentencia = conexion.createStatement();
			result = sentencia.executeQuery(consulta);

			while (result.next()) {
				peliculaOfertada = new Pelicula();
				
				peliculaOfertada.setIdPeli(result.getInt("IDPeli"));
				peliculaOfertada.setNombrePeli(result.getString("NomPeli"));
				peliculaOfertada.setDuracion(result.getInt("Duracion"));
				peliculaOfertada.setGenero(result.getString("NomGen"));
				
				cartelera.add(peliculaOfertada);
			}
			
			result.close();
			sentencia.close();
			conexion.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return (cartelera);
	}
	

	public static ArrayList<String> consultarFechas(Pelicula peliculaElegida) { 
		
		ArrayList<String>	fechas = new ArrayList<>();
		String				consulta = "select distinct fec"
									+ " from sesion "
									+ " where fec >= current_timestamp() and IDPeli = ?"; //como hay que sustituir un atributo, usas preparedStatement y no Statement	
		
		Connection 			conexion;
		PreparedStatement	sentencia = null;
		ResultSet 			result = null;
		
		
		try {	
			conexion = ConsultarBD.conectarConBD();
			sentencia = conexion.prepareStatement(consulta);
			sentencia.setInt(2, peliculaElegida.getIdPeli());//sustituir el ? por el id de la peliElegida
			
			result = sentencia.executeQuery();
			
			while (result.next()) 
				fechas.add(result.getString("fec"));

			result.close();
			sentencia.close();
			conexion.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return (fechas);
	}
	
	public static ArrayList<Sesion> consultarSesiones(Pelicula peliculaElegida, String fechaElegida){//esto tiene tela... hay que volcar datos en objeto

		ArrayList<Sesion>	sesionesPelicula = new ArrayList<>();
		Sesion				sesionDisponible = null;
		
		String	consulta = "select hora_ini, numsala, precio"
							+ "from sesion"
							+ "where idpeli = ? and fec = ?"; //ajustar consulta
		
		Connection 			conexion;
		PreparedStatement	sentencia = null;
		ResultSet 			result = null;
		
		try {
			conexion = ConsultarBD.conectarConBD();
			sentencia = conexion.prepareStatement(consulta);
			
			sentencia.setInt(1, peliculaElegida.getIdPeli());
			sentencia.setString(2, fechaElegida);
			
			result = sentencia.executeQuery();

			while (result.next()) {
				sesionDisponible = new Sesion();
				
				sesionDisponible.setIdSesion(result.getInt("IDSesion"));
				sesionDisponible.setFecSesion(result.getString("fec"));
				sesionDisponible.setHoraInicio(result.getString("hora_ini"));
				sesionDisponible.setHoraFin(result.getString("hora_fin"));
				sesionDisponible.setPrecio(result.getDouble("precio"));
				sesionDisponible.setSalaSesion(result.getInt("Numsala")); 
				sesionDisponible.setPeliculaSesion(result.getInt("IDPeli"));
				
				sesionesPelicula.add(sesionDisponible);
			}
			result.close();
			sentencia.close();
			conexion.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return (sesionesPelicula);
	}

}
