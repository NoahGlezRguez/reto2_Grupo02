package controlador;

import java.sql.*;
import java.util.ArrayList;

import modelo.*;
import vista.Menu;

public class ConsultarBD {

	private static String	rutaBD = "jdbc:mysql://10.5.6.116:3307/cine_elorrieta";//ajustar a la ruta real 

	private static String	user = "dam_v";
	private static String	pw = "Elorrieta00-";
//	private static String	rutaBD = "jdbc:mysql://127.0.0.1:3306/cine_elorrieta";
//	private static String	user = "DAM_v";
//	private static String	pw = "";
	
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

	//consultar y mostrar cartelera sin volcar los datos en memoria, solo se guardan los idPelis
	public static ArrayList<Integer> consultarCartelera() { 
		
		String					consulta = 	"select distinct p.idpeli, p.NomPeli, p.duracion, g.nomgen "
										+ "from sesion s "
										+ "join pelicula p on s.IDPeli = p.IDPeli "
										+ "join genero g on p.IDgen = g.idgen "
										+ "where fec >= current_timestamp()";
		int						numOpcion = 1;
		ArrayList<Integer>		idPelis = new ArrayList<>();

		Connection	conexion = null;
		Statement	sentencia = null;
		ResultSet	result = null;
		try {
			conexion = ConsultarBD.conectarConBD();
			sentencia = conexion.createStatement();
			result = sentencia.executeQuery(consulta);

			while (result.next()) {
				idPelis.add(result.getInt("IDPeli"));
				Menu.cartelera(numOpcion, result.getString("NomPeli"), result.getString("NomGen"), result.getInt("Duracion"));
				numOpcion++;
			}
			Menu.msgVolverAtras();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				if (result != null)
						result.close();
				if (sentencia != null)
					sentencia.close();
				if (conexion != null)
					conexion.close();
			
			} catch (SQLException e){
				e.printStackTrace();
			}
			
		}	
		return (idPelis);
	}
	
	//para volcar en memoria los datos de la pelicula seleccionada
	public static Pelicula consultarPeliculaElegida(int idPeliElegida) { 
		
		String		consulta = 	"select p.NomPeli, p.duracion, g.nomgen "
								+ "from pelicula p "
								+ "where p.IDPeli = ? ";
		Pelicula		peliculaElegida = new Pelicula();

		Connection	conexion = null;
		PreparedStatement	sentencia = null;
		ResultSet	result = null;
		
		try {
			conexion = ConsultarBD.conectarConBD();
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
			
		} finally {
			
			try {
				if (result != null)
						result.close();
				if (sentencia != null)
					sentencia.close();
				if (conexion != null)
					conexion.close();
			
			} catch (SQLException e){
				e.printStackTrace();
			}
			
		}	
		return (peliculaElegida);
	}
	
	//consultar, mostrar y guardar fechas de la feli seleccionada
	public static ArrayList<String> consultarFechas(Pelicula peliculaElegida) { 
		
		ArrayList<String>	fechas = new ArrayList<>();
		String				consulta = "select distinct fec"
									+ " from sesion "
									+ " where fec >= current_timestamp() and IDPeli = ?";
		
		int					numOpcion = 1;
		Connection 			conexion = null;
		PreparedStatement	sentencia = null;
		ResultSet 			result = null;
		
		
		try {	
			conexion = ConsultarBD.conectarConBD();
			sentencia = conexion.prepareStatement(consulta);
			sentencia.setInt(1, peliculaElegida.getIdPeli());
			
			result = sentencia.executeQuery();
			
			Menu.cabeceraMenu(2, peliculaElegida.getNombrePeli(), null);
			
			while (result.next()) {
				fechas.add(result.getString("fec"));
				Menu.fecha(numOpcion, peliculaElegida.getNombrePeli(), result.getString("fec"));
				numOpcion++;
			}
			Menu.msgVolverAtras();
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				if (result != null)
						result.close();
				if (sentencia != null)
					sentencia.close();
				if (conexion != null)
					conexion.close();
			
			} catch (SQLException e){
				e.printStackTrace();
			}
		}		
		return (fechas);
	}
	
	//consultar, mostrar (y guardar solo los idSesiones) una sesion de una pelicula determinada un dia determinado
	public static ArrayList<Integer> consultarSesiones(Pelicula peliculaElegida, String fechaElegida){//esto tiene tela... hay que volcar datos en objeto

		ArrayList<Integer>	sesionesPelicula = new ArrayList<>();
		
		String	consulta = "select idsesion, fec, hora_ini, hora_fin, precio, numsala, idpeli "
							+ "from sesion "
							+ "where idpeli = ? and fec = ?"; //ajustar consulta
		int					numOpcion = 1;
		
		Connection 			conexion = null;
		PreparedStatement	sentencia = null;
		ResultSet 			result = null;
		
		try {
			conexion = ConsultarBD.conectarConBD();
			sentencia = conexion.prepareStatement(consulta);
			
			sentencia.setInt(1, peliculaElegida.getIdPeli());
			sentencia.setString(2, fechaElegida);
			
			result = sentencia.executeQuery();

			Menu.cabeceraMenu(3, peliculaElegida.getNombrePeli(), fechaElegida);
			
			while (result.next()) {
				sesionesPelicula.add(result.getInt("IDSesion"));
				Menu.sesion(numOpcion, peliculaElegida.getNombrePeli(), fechaElegida, result.getString("hora_ini"), result.getInt("Numsala"), result.getDouble("precio"));
				numOpcion++;
			}
			Menu.msgVolverAtras();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				if (result != null)
						result.close();
				if (sentencia != null)
					sentencia.close();
				if (conexion != null)
					conexion.close();
			
			} catch (SQLException e){
				e.printStackTrace();
			}
			
		}
		return (sesionesPelicula);
	}

	//consultar y volcar datos de una sesion en concreto
	public static Sesion consultarSesionElegida(int idSesionElegida) {
		String		consulta = 	"select * "
								+ "from sesion "
								+ "where IDSesion = ? ";
		Sesion		sesionElegida = new Sesion();
		
		Connection			conexion = null;
		PreparedStatement	sentencia = null;
		ResultSet			result = null;
		
		try {
			conexion = ConsultarBD.conectarConBD();
			sentencia = conexion.prepareStatement(consulta);
			
			sentencia.setInt(1, idSesionElegida);
			result = sentencia.executeQuery();
			
			result.next();
			
			sesionElegida.setIdSesion(idSesionElegida);
			sesionElegida.setFecSesion(result.getString("fec"));
			sesionElegida.setHoraInicio(result.getString("hora_ini"));
			sesionElegida.setHoraFin(result.getString("hora_fin"));
			sesionElegida.setPrecio(result.getDouble("precio"));
				//sesionElegida.setAforoDisponible(result.getInt("")); //calcular aforo...
			sesionElegida.setSalaSesion(result.getInt("NumSala"));
			sesionElegida.setPeliculaSesion(result.getInt("IDPeli"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		
		} finally {
		
			try {
				if (result != null)
						result.close();
				if (sentencia != null)
					sentencia.close();
				if (conexion != null)
					conexion.close();
				
			} catch (SQLException e){
				e.printStackTrace();
			}
		
		}	
		return (sesionElegida);
	}
	
}
