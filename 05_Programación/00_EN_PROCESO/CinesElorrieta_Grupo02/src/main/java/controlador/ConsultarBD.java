package controlador;

import java.sql.*;
import modelo.*;

public class ConsultarBD {

	private static String	rutaBD = "jdbc:mysql://localhost:33060/cine_elorrieta";//ajustar a la ruta real 
	private static String	user = "DAM_v";
	private static String	pw = "Elorrieta9753$";
	
	public static Connection getConect() {
		
		Connection	conexion = null;
		
		try {
			if (conexion == null || conexion.isClosed())
				conexion = DriverManager.getConnection(rutaBD, user, pw);
		
		} catch(SQLException excpsql) {
			System.out.println("Error, no se pudo realizar la conexión con la base de datos.\n");
			System.out.println("SQLException: " + excpsql.getMessage());
	        System.out.println("SQLState: " + excpsql.getSQLState());
	        System.out.println("VendorError: " + excpsql.getErrorCode());
		}
		return(conexion);
	}

	public static Pelicula[] consultarCartelera() { 
		
		Pelicula 	cartelera[] = null;
		int			numPelis = 0;
		String		consulta = "select distinct pelicula.idpeli, NomPeli, duracion, nomgen\r\n"
								+ "from sesion join pelicula on sesion.IDPeli = pelicula.IDPeli join genero on pelicula.IDgen = genero.idgen\r\n"
								+ "where fec >= current_timestamp()";
		Statement	sentencia = null;
		ResultSet 	result = null;
		
		Connection conexion = ConsultarBD.getConect();
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
		String		consulta = "select fec"
								+ " from sesion join pelicula on sesion.IDPeli = pelicula.IDPeli\r\n"
								+ " where fec >= current_timestamp() and pelicula.IDPeli = " + peliculaElegida.getIdPeli();
		Statement	sentencia = null;
		ResultSet 	result = null;
		
		Connection conexion = ConsultarBD.getConect();
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
			System.out.println("Error de conexión con la base de datos al consultar la cartelera...");
		return (fechas);
	}
	
	
	private static int contarTuplas(String consulta, Connection conexion) {
		
		int			numTuplas = 0;
		Statement	sentencia = null;
		ResultSet 	result = null;
		
		try {
			sentencia = conexion.createStatement();
			result = sentencia.executeQuery(consulta);
			while(result.next())
				numTuplas++;
		} catch (SQLException e) {
			e.printStackTrace();
			numTuplas = -1;
		}
		return (numTuplas);
		
	}
	
	public static Sesion[] consultarSesiones(Pelicula peliculaElegida, String fechaElegida){//esto tiene tela... hay que volcar datos en objeto

		
		int		numSesiones = 0;
		Sesion	sesionesPelicula[] = null;
		int		sesiones_ids[] = null;
		String	consulta = "Select hora_ini from Sesion where IDPeli = " + peliculaElegida.getIdPeli(); //ajustar consulta
		
		Connection 	conexion = ConsultarBD.getConect();
		Statement	sentencia = null;
		ResultSet 	result = null;
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
					sesiones_ids[i] = Integer.parseInt(result.getString("IDSesion"));
			}
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
		return (sesionesPelicula);
	}
}
