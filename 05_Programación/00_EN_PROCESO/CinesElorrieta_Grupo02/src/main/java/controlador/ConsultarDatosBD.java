package controlador;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import modelo.*;
import vista.*;

/**
 * Esta clase tiene los métodos necesarios para consultar datos de la base de datos del cine, incluido
 * el que realiza la conexión con la base de datos, necesaria para realizar las consultas. 
 */
public class ConsultarDatosBD {

	/*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*
	 * conectar con la bbdd
	 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
	
	/**
	 * Este método llama al método datos para obtener la información necesaria para
	 * realizar una conexión con la base de datos del cine. Si hubiera algún error
	 * en el proceso, lo mostrará en la terminal.
	 * @return Devuelve la conexión si se ha realizado con éxito, null en caso contrario.
	 */
	public static Connection conectarConBD() {
		Connection	conexion = null;
		String data[] = datos();
		
		try {									
			conexion = DriverManager.getConnection(data[0], data[1], data[2]);
		} catch (SQLException excpsql) {
			MostrarMsg.errores(0);
			System.out.println("SQLException: " + excpsql.getMessage());
			System.out.println("SQLState: " + excpsql.getSQLState());
			System.out.println("VendorError: " + excpsql.getErrorCode());
		}
		return (conexion);
	}
	
	/**
	 * <p>Este método lee los datos del fichero <b>ipConfig.txt</b>
	 * y los guarda en un array de la siguiente forma:</p></br>
	 * <ol start="0">
	 * <li>jdbc:mysql://ip:puerto/DatabaseName</li>
	 * <li>user</li>
	 * <li>password</li>
	 * </ol>
	 * 
	 * Para modificar los datos se debe actualizar solo el contenido que está
	 * después de '=' en ese fichero. El formato de estos datos es esencial
	 * para que funcione, no debería contener nada más, ni un salto de línea
	 * extra. De contener algo más, no podrá realizarse la conexión.</br>
	 *  
	 * @return <b>String array </b> contiene la ruta, el usuario y la password
	 * necesarios para la conexión con la base de datos
	 */
	private static String [] datos(){
		
		String confi[] = new String[3];
		FileReader reader = null;
		BufferedReader buffer = null;
		String ruta = "src/main/java/files/ipConfig.txt";
		int cont = 0;
		
		try {
			
			reader = new FileReader(ruta);
			buffer = new BufferedReader(reader);
			String datos = "";
			
			while((datos = buffer.readLine()) != null) {				
				confi[cont] = datos.substring(datos.indexOf('=')+1);
				cont++;
			}

		}catch(FileNotFoundException e) {
			
			MostrarMsg.errores(16);
			
		} catch (IOException  e2){
			MostrarMsg.errores(17);
		}
		
		finally {			
			try {				
				buffer.close();				
			}catch(IOException e) {
				MostrarMsg.errores(17);
			}
		}
		return confi;	
	}

	/*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*
	 * consulta de datos
	 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
	
	
	/**
	 * Este método conecta con la base de datos y consulta cuáles son las 
	 * películas que tienen sesiones a partir de la fecha actual en adelante.
	 * Además, muestra los datos respectivos de cada película mostrada.
	 * Muestra en bucle y con formato cada película que cumple con esa premisa.
	 * Al mismo tiempo, almacena los ID de las películas mostradas para posteriores
	 * consultas.
	 * @return ArrayList de los ID de las películas disponibles en cartelera
	 */
	public static ArrayList<Integer> consultarCartelera() {

		String consulta = """
				select distinct p.idpeli, p.NomPeli, p.duracion, g.nomgen
				from sesion s
				join pelicula p on s.IDPeli = p.IDPeli
				join genero g on p.IDgen = g.idgen
				where fec >= current_timestamp()""";
		int numOpcion = 1;
		ArrayList<Integer> idPelis = new ArrayList<>();

		Connection conexion = null;
		Statement sentencia = null;
		ResultSet result = null;
		try {
			conexion = ConsultarDatosBD.conectarConBD();
			sentencia = conexion.createStatement();
			result = sentencia.executeQuery(consulta);

			Menu.cabeceraMenu(1, null, null, null);
			while (result.next()) {
				idPelis.add(result.getInt("IDPeli"));
				Menu.cartelera(numOpcion, result.getString("NomPeli"), result.getString("NomGen"),
						result.getInt("Duracion"));
				numOpcion++;
			}

			Menu.msgVolverAtras();

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
		return (idPelis);
	}

	
	/**
	 * Recibe una película, y usando su ID se consulta en la base de datos
	 * cuáles son las fechas disponibles que tengas sesiones de ahora
	 * en adelante. Muestra con formato los resultados y la opcion de volver
	 * atras.
	 * @param peliculaElegida - objeto con los datos de la pelicula elegida por el usuario
	 * @return ArrayList de string donde cada elemento es una fecha disponible de esa
	 * pelicula
	 */
	public static ArrayList<String> consultarFechas(Pelicula peliculaElegida) {

		ArrayList<String> fechas = new ArrayList<>();
		String consulta = """
				select distinct fec
				from sesion
				where fec >= current_timestamp() and IDPeli = ?""";
		int numOpcion = 1;
		Connection conexion = null;
		PreparedStatement sentencia = null;
		ResultSet result = null;

		try {
			conexion = ConsultarDatosBD.conectarConBD();
			sentencia = conexion.prepareStatement(consulta);
			sentencia.setInt(1, peliculaElegida.getIdPeli());

			result = sentencia.executeQuery();

			Menu.cabeceraMenu(2, peliculaElegida.getNombrePeli(), null, null);

			while (result.next()) {
				fechas.add(result.getString("fec"));
				Menu.fecha(numOpcion, peliculaElegida.getNombrePeli(), result.getString("fec"));
				numOpcion++;
			}
			Menu.msgVolverAtras();

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
		return (fechas);
	}

	// consultar, mostrar (y guardar solo los idSesiones) una sesion de una pelicula
	// determinada un dia determinado
	
	/**
	 * Consulta las sesiones disponibles de la pelicula y la fecha
	 * indicadas, devuelve los ID de las sesiones resultantes.
	 * @param peliculaElegida - seleccionada previamente por el usuario de la cartelera
	 * @param fechaElegida - seleccionada previamente por el usuario del listado mostrado
	 * @return ArrayList de todos los ID de las sesiones consultadas que están disponibles
	 */
	public static ArrayList<Integer> consultarSesiones(Pelicula peliculaElegida, String fechaElegida) {// esto tiene

		ArrayList<Integer> sesionesPelicula = new ArrayList<>();

		String consulta = """
				select idsesion, fec, hora_ini, precio, numsala, idpeli
				from sesion
				where idpeli = ? and fec = ?
				order by hora_ini""";

		Connection conexion = null;
		PreparedStatement sentencia = null;
		ResultSet result = null;

		try {
			conexion = ConsultarDatosBD.conectarConBD();
			sentencia = conexion.prepareStatement(consulta);
			sentencia.setInt(1, peliculaElegida.getIdPeli());
			sentencia.setString(2, fechaElegida);
			result = sentencia.executeQuery();

			while (result.next())
				sesionesPelicula.add(result.getInt("idsesion"));

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
		return (sesionesPelicula);
	}

	/**
	 * Consulta qué sesiones tienen aforo de aquellas sesiones que son de la pelicula y
	 * fecha indicadas. Muestra el resultado en pantalla con formato y devuelve un 
	 * arraylist con los id de las sesiones resultantes.
	 * @param peliculaElegida - elegida por el usuario
	 * @param fechaElegida - elegida por el usuario
	 * @param compra - objeto con la informacion del proceso actual de compra
	 * @return id de las sesiones con aforo disponibles ese dia de esa pelicula
	 */
	public static ArrayList<Integer> consultarSesionesConAforoDisponible(Pelicula peliculaElegida,
			String fechaElegida, Compra compra) {

		ArrayList<Integer> sesionesPelicula = new ArrayList<>(), sesionesConAforo = new ArrayList<>();
		String consulta = """
				select fec, hora_ini, precio, numsala, idpeli
				from sesion
				where idsesion = ?""";

		Connection conexion = null;
		PreparedStatement sentencia = null;
		ResultSet result = null;

		// con aforo o sin aforo:
		sesionesPelicula = consultarSesiones(peliculaElegida, fechaElegida);

		//analizar el aforo
		for (int i = 0; i < sesionesPelicula.size(); i++) {
			if (consultarAforo(sesionesPelicula.get(i), compra) > 0) {
				sesionesConAforo.add(sesionesPelicula.get(i));
			}
		}

		if (sesionesConAforo.size() > 0) {
			Menu.cabeceraMenu(3, peliculaElegida.getNombrePeli(), fechaElegida, null);

			try {
				conexion = ConsultarDatosBD.conectarConBD();
				sentencia = conexion.prepareStatement(consulta);

				for (int i = 0; i < sesionesConAforo.size(); i++) {

					sentencia.setInt(1, sesionesConAforo.get(i));
					result = sentencia.executeQuery();
					result.next();

					Menu.sesion(i + 1, peliculaElegida.getNombrePeli(), fechaElegida, result.getString("hora_ini"),
							result.getInt("numsala"), result.getDouble("precio"));
				}
				Menu.msgVolverAtras();

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException e2) {
				System.err.println(e2.getMessage());
			}  
			finally {

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
		} else
			MostrarMsg.errores(2);

		return (sesionesConAforo);
	}

	/**
	 * Analiza el aforo disponible real y actualizado de una sesion concreta
	 * teniendo en cuenta las entradas de la base de datos y las del proceso de compra
	 * @param idSesion - la sesion de la que se consulta el aforo
	 * @param compra - el proceso actual de compra
	 * @return - el aforo disponible resultante
	 */
	public static int consultarAforo(int idSesion, Compra compra) {

		int aforoSala, aforoCompras, aforoCesta, aforoDisponible;

		aforoSala = conocerAforoSala(idSesion);
		aforoCompras = conocerAforoCompras(idSesion);
		aforoCesta = compra.conocerAforoCesta(idSesion);

		aforoDisponible = aforoSala - aforoCompras - aforoCesta;

		return (aforoDisponible);
	}

	/**
	 * consulta en la base de datos cual es el aforo ocupado previamente con otras
	 * compras con entradas para esa sesion y devuelve el resultado
	 * @param idSesion - la sesion de la que analizar el aforo
	 * @return - aforo ocupado previamente de esa sesion
	 */
	public static int conocerAforoCompras(int idSesion) {

		int aforoOcupado = 0;

		String consulta = """
				select sum(cantPersonas)
				from entrada e
				join sesion s on e.IDSesion = s.IDSesion
				where s.idsesion = ?""";
		Connection conexion = null;
		PreparedStatement sentencia = null;
		ResultSet result = null;

		try {
			conexion = ConsultarDatosBD.conectarConBD();
			sentencia = conexion.prepareStatement(consulta);

			sentencia.setInt(1, idSesion);

			result = sentencia.executeQuery();

			result.next();

			aforoOcupado += result.getInt("sum(cantPersonas)");

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
		return (aforoOcupado);
	}

	/**
	 * Consulta cual es el aforo de la sala de la sesion indicada
	 * @param idSesion - sesion de la que analizar el aforo de su sala
	 * @return - aforo de la sala 
	 */
	public static int conocerAforoSala(int idSesion) {

		int aforoSala = -1;
		String consulta = """
				select sa.aforo
				from sala sa
				join sesion se on sa.numsala = se.numsala
				where idsesion = ?""";
		Connection conexion = null;
		PreparedStatement sentencia = null;
		ResultSet result = null;

		try {
			conexion = ConsultarDatosBD.conectarConBD();
			sentencia = conexion.prepareStatement(consulta);

			sentencia.setInt(1, idSesion);

			result = sentencia.executeQuery();

			if (result.next()) {

				aforoSala = result.getInt("aforo");

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
		return (aforoSala);
	}



	
	/**
	 * <b>validador con la DB</b>
	 * 
	 * @param String cadena a validar, String tipo de dato
	 *               <p>
	 *               Este método se conecta con la base de datos y valida que el
	 *               dato introducido no exista para evitar errores en los campos
	 *               "unique"
	 *               </p>
	 * @return boolean true = válido, false = inválido
	 */
	public static boolean validarExistencia(String cadena, String atributo) {

		boolean valido = true;
		Connection conexion = null;
		PreparedStatement sentencia = null;
		ResultSet result = null;
		String consulta = """
					select %s
					from cliente
					where %s = ?
				""".formatted(atributo, atributo);

		try {

			conexion = conectarConBD();
			sentencia = conexion.prepareStatement(consulta);
			
			
			sentencia.setString(1, cadena);
			
			result = sentencia.executeQuery();

			if (result.next()) {

				valido = false;
				MostrarMsg.errores(5);
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

		return valido;

	}

}
