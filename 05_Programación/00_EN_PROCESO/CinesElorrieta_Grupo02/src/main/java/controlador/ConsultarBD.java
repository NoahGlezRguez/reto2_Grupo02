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

	public static Pelicula[] consultarCartelera() { 
		
		ArrayList<Pelicula> 	cartelera = new ArrayList<>();
		int					numPelis = 0;
		String				consulta = "select distinct pelicula.idpeli, NomPeli, duracion, nomgen"
									+ "from sesion join pelicula on sesion.IDPeli = pelicula.IDPeli join genero on pelicula.IDgen = genero.idgen"
									+ "where fec >= current_timestamp()";
		Statement	sentencia = null;
		ResultSet 	result = null;
		
		Connection conexion = ConsultarBD.conectarConBD();
		if (conexion != null) {
			try {
				sentencia = conexion.createStatement();
				result = sentencia.executeQuery(consulta);
				while(result.next())
					numPelis++;
				cartelera = new Pelicula[numPelis];
				result = sentencia.executeQuery(consulta);
				for(int i = 0; i < numPelis; i++)
				{
					cartelera[i] = new Pelicula();
					if (result.next()) {
						cartelera[i].setIdPeli(Integer.parseInt(result.getString("IDPeli")));
						cartelera[i].setNombrePeli(result.getString("NomPeli"));
						cartelera[i].setDuracion(Integer.parseInt(result.getString("Duracion")));
						cartelera[i].setGenero(result.getString("NomGen"));
					}
				}
				result.close();
				sentencia.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else
			System.out.println("Error de conexión con la base de datos al consultar la cartelera...");
		return (cartelera);
	}
	

	public static String[] consultarFechas(Pelicula peliculaElegida) { 
		
		String		fechas[] = null;
		int			numDias = 0;
		String		consulta = "select distinct fec"
								+ " from sesion join pelicula on sesion.IDPeli = pelicula.IDPeli\r\n"
								+ " where fec >= current_timestamp() and pelicula.IDPeli = " + peliculaElegida.getIdPeli();
		Statement	sentencia = null;
		ResultSet 	result = null;
		
		Connection conexion = ConsultarBD.conectarConBD();
		if (conexion != null) {
			numDias = contarTuplas(consulta, conexion);
			
			if (numDias > 0) {
				fechas = new String[numDias];
			
				try {
					sentencia = conexion.createStatement();
					result = sentencia.executeQuery(consulta);
					result = sentencia.executeQuery(consulta);
					
					for(int i = 0; i < numDias; i++) {
						fechas[i] = new String();
						if (result.next()) {
							fechas[i] = result.getString("fec");
						}
					}
					
					result.close();
					sentencia.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
			
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else
			System.out.println("Error de conexión con la base de datos al consultar las fechas...");
		return (fechas);
	}
	
	public static Sesion[] consultarSesiones(Pelicula peliculaElegida, String fechaElegida){//esto tiene tela... hay que volcar datos en objeto

		
		int		numSesiones = 0;
		Sesion	sesionesPelicula[] = null;
		int		sesiones_ids[] = null;
		String	consulta = "select sesion.hora_ini, sesion.numsala, sesion.precio from sesion join pelicula on sesion.idpeli = pelicula.idpeli where pelicula.idpeli = " + peliculaElegida.getIdPeli()
							+ " and sesion.fec = '" + fechaElegida +"'"; //ajustar consulta
		
		Connection 	conexion = ConsultarBD.conectarConBD();
		Statement	sentencia = null;
		ResultSet 	result = null;
		if (conexion != null) {
			try {
				sentencia = conexion.createStatement();
				result = sentencia.executeQuery(consulta);
				while(result.next())
					numSesiones++;
				sesiones_ids = new int[numSesiones];
				result = sentencia.executeQuery(consulta);
				for(int i = 0; i < numSesiones; i++)
				{
					if (result.next())
						sesiones_ids[i] = result.getInt("IDSesion");
				}
				
				result.close();
				sentencia.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			for (int i = 0; i < numSesiones; i++) {
				System.out.printf("\t- item %d -> id de sesion = %d.\n", i, sesiones_ids[i]);
			}
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else
			System.out.println("Error de conexión con la base de datos al consultar las sesiones...");

		return (sesionesPelicula);
	}
	
	//borrar este metodo pronto
//	private static int contarTuplas(String consulta, Connection conexion) {
//		
//		int			numTuplas = 0;
//		Statement	sentencia = null;
//		ResultSet 	result = null;
//		
//		try {
//			sentencia = conexion.createStatement();
//			result = sentencia.executeQuery(consulta);
//			while(result.next())
//				numTuplas++;
//			
//			result.close();
//			sentencia.close();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			numTuplas = -1;
//		}
//		return (numTuplas);
//		
//	}
}
