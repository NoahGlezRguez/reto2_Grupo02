package controlador;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import modelo.*;
import vista.*;

public class OperacionesBD {

	/*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*
	 * conectar con la bbdd
	 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
	
	
	/**
	 * <p>este método lee los datos de el fichero <b>ipConfig.txt</b>
	 * y los guarda en un array de la siguiente forma:</p></br>
	 * <ol start="0">
	 * <li>jdbc:mysql://ip:puerto/DatabaseName</li>
	 * <li>user</li>
	 * <li>password</li>
	 * </ol>
	 * 
	 * para modificar los datos se debe actualizar solo el contenido que está
	 * después de '='</br>
	 *  
	 * @return <b>String array </b></br>se utilizarán los index del array tal como
	 * se indica en la lista
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
				
			}catch(Exception e) {
				
				//rellenar
			}
		}
		
		
		return confi;
		
	}


	
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

	/*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*
	 * consulta de datos
	 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
	
	
	
	// consultar y mostrar cartelera sin volcar los datos en memoria, solo se
	// guardan los idPelis
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
			conexion = OperacionesBD.conectarConBD();
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



	// consultar, mostrar y guardar fechas de la feli seleccionada
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
			conexion = OperacionesBD.conectarConBD();
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
			conexion = OperacionesBD.conectarConBD();
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

		for (int i = 0; i < sesionesPelicula.size(); i++) {
			if (consultarAforo(sesionesPelicula.get(i), compra) > 0) {
				sesionesConAforo.add(sesionesPelicula.get(i));
			}
		}

		if (sesionesConAforo.size() > 0) {
			Menu.cabeceraMenu(3, peliculaElegida.getNombrePeli(), fechaElegida, null);

			try {
				conexion = OperacionesBD.conectarConBD();
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

	public static int consultarAforo(int idSesion, Compra compra) {

		int aforoSala, aforoCompras, aforoCesta, aforoDisponible;

		aforoSala = conocerAforoSala(idSesion);
		aforoCompras = conocerAforoCompras(idSesion);
		aforoCesta = compra.conocerAforoCesta(idSesion);

		aforoDisponible = aforoSala - aforoCompras - aforoCesta;

		return (aforoDisponible);
	}

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
			conexion = OperacionesBD.conectarConBD();
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
			conexion = OperacionesBD.conectarConBD();
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
					select ?
					from cliente
					where ? = ?
				""";

		try {

			conexion = conectarConBD();
			sentencia = conexion.prepareStatement(consulta);
			
			sentencia.setString(1, atributo);
			sentencia.setString(2, atributo);
			sentencia.setString(3, cadena);
			
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

	


		
	/*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*
	 * obtencion de datos
	 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
	
	// para volcar en memoria los datos de la pelicula seleccionada
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
			conexion = OperacionesBD.conectarConBD();
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
	 * 
	 * @return este método consulta el login
	 * @param credenciales del usuario
	 * @return <b>Objeto cliente</b> <br>
	 *         <b>not null</b> el cliente existe y te devuelve el objeto con sus
	 *         datos <br>
	 *         <b>null </b> settea el objeto a null
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

			conexion = conectarConBD();
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
	
	// consultar y volcar datos de una sesion en concreto
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
			conexion = OperacionesBD.conectarConBD();
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
			conexion = OperacionesBD.conectarConBD();
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
			conexion = OperacionesBD.conectarConBD();
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
	
	/*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*
	 * insersiones
	 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
	
	public static void insertarEntradasEnBD(ArrayList<Entrada> entradas, int idCompra) {
		
		Connection conexion = null;
		PreparedStatement sentencia = null;
		int filasAfectadas = 0;
		
		String consulta = """
					insert into entrada (CantPersonas, importe, idSesion, idCompra)
					values(?, ?, ?, ?)
				""";
		try {
			conexion = OperacionesBD.conectarConBD();
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
	
	public static void insertarCompraEnBD(String plataforma, double descuento,
									double total, String dni) {
		Connection conexion = null;
		PreparedStatement sentencia = null;
		int filasAfectadas = 0;
		
		String consulta = """
				insert into compra (plataforma, descuento, total, dni)
				values(?, ?, ?, ?)
				""";
		try {
			conexion = conectarConBD();
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

			conexion = conectarConBD();
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
