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

	public static void id_pelis_consulta() { //metodo de prueba
		
		int	pelis_ids[] = null;
		int	numPelis = 0;
		
		Connection conexion = ConsultarBD.getConect();
		Statement	sentencia = null;
		ResultSet 	result = null;
		try {
			sentencia = conexion.createStatement();
			result = sentencia.executeQuery("Select * from Pelicula"); //ajustar a lo que necesitemos
			while(result.next())
				numPelis++;
			pelis_ids = new int[numPelis];
			result = sentencia.executeQuery("Select * from Pelicula");
			for(int i = 0; i < numPelis; i++)
			{
				if (result.next())
					pelis_ids[i] = Integer.parseInt(result.getString("IDPeli"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < numPelis; i++) {
			System.out.printf("\t- item %d -> id de peli = %d.\n", i, pelis_ids[i]);
		}
		try {
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Pelicula[] consultarCartelera() { //metodo de prueba
		
		Pelicula cartelera[] = null;
		int	numPelis = 0;
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
}
