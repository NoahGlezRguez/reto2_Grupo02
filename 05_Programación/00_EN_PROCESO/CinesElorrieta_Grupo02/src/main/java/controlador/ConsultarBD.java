package controlador;

import java.sql.*;
import java.util.ArrayList;

import modelo.*;
import vista.Menu;

public class ConsultarBD {

	private static String	rutaBD = "jdbc:mysql://10.5.6.196:3307/cine_elorrieta";

	private static String	user = "dam_v";
	private static String	pw = "Elorrieta00-";
//	private static String	rutaBD = "jdbc:mysql://127.0.0.1:3306/cine_elorrieta";
//	private static String	user = "DAM_v";
//	private static String	pw = "";
	
	public static Connection conectarConBD() {
		Connection	conexion = null;
		
		try {
			conexion = DriverManager.getConnection(rutaBD, user, pw);
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
		
		String					consulta = 	"""
									select distinct p.idpeli, p.NomPeli, p.duracion, g.nomgen 
									from sesion s 
									join pelicula p on s.IDPeli = p.IDPeli 
									join genero g on p.IDgen = g.idgen 
									where fec >= current_timestamp()""";
		int						numOpcion = 1;
		ArrayList<Integer>		idPelis = new ArrayList<>();

		Connection	conexion = null;
		Statement	sentencia = null;
		ResultSet	result = null;
		try {
			conexion = ConsultarBD.conectarConBD();
			sentencia = conexion.createStatement();
			result = sentencia.executeQuery(consulta);

			Menu.cabeceraMenu(1, null, null);
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
		
		String		consulta = """
								select p.NomPeli, p.duracion, g.nomgen 
								from pelicula p join genero g on p.idgen = p.idgen
								where p.IDPeli = ?""";
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
		String				consulta = """
									select distinct fec 
									from sesion  
									where fec >= current_timestamp() and IDPeli = ?""";
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
		int					idSesion = 0;
			
		String	consulta = """
							select fec, hora_ini, precio, numsala, idpeli 
							from sesion 
							where idsesion = ?""";

		Connection 			conexion = null;
		PreparedStatement	sentencia = null;
		ResultSet 			result = null;
		
		sesionesPelicula = consultarSesionesConAforoDisponible(peliculaElegida, fechaElegida);
		
		try {
			conexion = ConsultarBD.conectarConBD();
			sentencia = conexion.prepareStatement(consulta);
			
			Menu.cabeceraMenu(3, peliculaElegida.getNombrePeli(), fechaElegida);
			
			for(int i = 0; i < sesionesPelicula.size(); i++) {
				idSesion = sesionesPelicula.get(i);
				
				sentencia.setInt(1, idSesion);
				
				result = sentencia.executeQuery();
				
				result.next();
				
				Menu.sesion(i + 1, peliculaElegida.getNombrePeli(), fechaElegida,
						result.getString("duracion"), result.getInt("numsala"), result.getDouble("precio"));
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

	
	public static ArrayList<Integer> consultarSesionesConAforoDisponible(Pelicula peliculaElegida, String fechaElegida){
		
		ArrayList<Integer>	sesionesPelicula = new ArrayList<>();
		ArrayList<Integer>	sesionesConAforo = new ArrayList<>();
		
		sesionesPelicula = consultarSesiones(peliculaElegida, fechaElegida);

		for (int i = 0; i < sesionesPelicula.size(); i++) {
			if (consultarAforo(sesionesPelicula.get(i)) > 0)
				sesionesConAforo.add(sesionesPelicula.get(i));
		}

		return (sesionesConAforo);
	}
	
	public static int consultarAforo(int idSesion) {
		
		int		aforoSala, aforoCompras, aforoCesta, aforoDisponible;
		
		aforoSala = conocerAforoSala(idSesion);
		aforoCompras = conocerAforoCompras(idSesion);
		aforoCesta = conocerAforoCesta(idSesion);
		
		aforoDisponible = aforoSala - aforoCompras - aforoCesta;
		
		return (aforoDisponible);
	}
	
	public static int	conocerAforoCompras(int idSesion) {
		
		int	aforoOcupado = 0;

		String	consulta = """
				select cantPersonas 
				from entrada 
				where idsesion = ?""";
		Connection 			conexion = null;
		PreparedStatement	sentencia = null;
		ResultSet 			result = null;
		
		try {
			conexion = ConsultarBD.conectarConBD();
			sentencia = conexion.prepareStatement(consulta);
			
			sentencia.setInt(1, idSesion);
			
			result = sentencia.executeQuery();

			while (result.next()) {
				aforoOcupado +=	result.getInt("cantpersonas");
			}
			
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
		return (aforoOcupado);
	}
	
	public static int	conocerAforoSala(int numSala) {

		int		aforoSala = -1;
		String	consulta = """
				select aforo 
				from sala 
				where numsala = ?""";
		Connection 			conexion = null;
		PreparedStatement	sentencia = null;
		ResultSet 			result = null;
		
		try {
			conexion = ConsultarBD.conectarConBD();
			sentencia = conexion.prepareStatement(consulta);
			
			sentencia.setInt(1, numSala);
			
			result = sentencia.executeQuery();

			result.next();
				aforoSala = result.getInt("aforo");		

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
		return (aforoSala);
	}
	
	public static int	conocerAforoCesta(int idSesion) {
		int	aforoCesta = 0;
		
		for(int i = 0; i < OperacionesCompra.entradas.size(); i++) {
			if (OperacionesCompra.entradas.get(i).getSesionEntrada().getIdSesion() == idSesion)
				aforoCesta += OperacionesCompra.entradas.get(i).getNumPersonas();
		}		
		return (aforoCesta);
	}
	
	//consultar y volcar datos de una sesion en concreto
	public static Sesion consultarSesionElegida(int idSesionElegida) {
		String		consulta = 	"""
				select * 
				from sesion 
				where IDSesion = ?""";
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
