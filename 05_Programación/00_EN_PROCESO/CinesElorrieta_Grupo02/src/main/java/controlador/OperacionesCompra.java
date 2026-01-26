package controlador;

import java.util.ArrayList;

import modelo.*;
import vista.*;

/**
 * Esta clase contiene las distintas operaciones dentro del proceso de compra. Es el corazón del programa.
 */
public class OperacionesCompra {

	/**
	 * Este método gestiona el flujo del programa cuando el usuario selecciona en el menú previo "Comprar entradas".
	 * Muestra los diferentes menús, recoge las selecciones realizadas por el usuario y si se cancela o se termina el proceso,
	 * regresa al menú anterior. 
	 * 
	 * @return false en caso de que haya terminado adecuadamente, y true si se ha cancelado el proceso de compra en algún punto.
	 */
	public static boolean comprarEntradas() {
		
		
		String		fechaElegida = null;
		Pelicula		peliculaElegida = null;
		Sesion		sesionElegida = null;
		boolean		seCancelaLaCompra = false, volverALaCartelera = false, volverAFechas = false, volverASesiones = false;
		int			numPersonas = 0;
		//entrada 	cestacompra[] = null;
		
		
		do {
			peliculaElegida = elegirPelicula();
			
			if (peliculaElegida == null) //si cliente pulsa "volver atrás"
				seCancelaLaCompra = true;
		 	else {
		 		do{
			 		fechaElegida = elegirFecha(peliculaElegida);
			 		
			 		if (fechaElegida.equals("1")) //si cliente pulsa "volver atrás"
			 			volverALaCartelera = true;
			 		else {
				 		do {
				 			sesionElegida = elegirSesion(peliculaElegida, fechaElegida);	
					 		
				 			if (sesionElegida == null)//si cliente pulsa "volver atrás"
				 				volverAFechas = true;
				 			else {
				 				do {
				 					numPersonas = Menu.pedirNumPersonas(sesionElegida);
				 					if (numPersonas < 0)//si cliente pulsa "volver atrás"
				 						volverASesiones = true;
				 					else {
				 						/*
				 						 * pedir confirmacion de añadir entrada
				 						 * 	- confirma: 
				 						 * 		- iniciar sesion de usuario
				 						 * 		- usuario correcto:
				 						 * 			- añadir entrada
				 						 * 			- menu de que mas hacer etc
				 						 * 		- usuario incorrecto: intentar de nuevo.
				 						 * 		- usuario incorrecto de nuevo, agota intentos: cancelar la compra del todo y reiniciar programa desde cero
				 						 *  - no confirma = volver atras
				 						 *  
				 						 * */
				 					}
				 						
				 				} while (!volverASesiones);
				 				volverASesiones = false;
				 				numPersonas = 0;
			 				
				 			}
				 			//else
				 				
			//		 		if (!seCancelaLaCompra) {
			//		 			sesiones = ConsultarBD.consultarSesiones(peliculaElegida, fechaElegida);
			//		 			sesionElegida = Menu.sesiones(sesiones, peliculaElegida, fechaElegida);
			//		 		}
				 		} while(!volverAFechas);
				 		
				 		volverAFechas = false;
			 		}
			 			
		 		} while(!volverALaCartelera);
		 		
		 		volverALaCartelera = false;
		 	}
		} while (!seCancelaLaCompra);
		
		return (seCancelaLaCompra);
	}
	
	//el proceso de ver cartelera, elegir y peli y guardar los datos de la seleccion en un objeto pelicula
	private static Pelicula elegirPelicula() {
		
		ArrayList<Integer> 	cartelera = new ArrayList<>();
		int					idPeliElegida = -1, indiceEnCartelera = -1;
		Pelicula 			peliculaElegida = null;

		cartelera = ConsultarBD.consultarCartelera();
		
		if (cartelera.size() > 0) {
			indiceEnCartelera = opcionCorrecta("\n\t·····> Introduzca el nº de la película: ", cartelera); //lo que introduce el usuario, ya validado
			//si no quiere volver atras:
			if (indiceEnCartelera != -1) {
				idPeliElegida = cartelera.get(indiceEnCartelera);
				peliculaElegida = ConsultarBD.consultarPeliculaElegida(idPeliElegida);
			}
		}
		else
			System.out.println("Error, no hay cartelera disponible ahora mismo, lo sentimos");//dar formato de msg de error
		
		return (peliculaElegida); //sera null si puso la opcion de volver atras
	}
	
	//el proceso de ver fechas posibles para la pelicula elegida, elegir una y guardar los datos de esa fecha en un string
	private static String elegirFecha(Pelicula peliculaElegida) {
		
		ArrayList<String>	fechas = new ArrayList<>();
		String				fechaElegida = null;
		int					seleccionIndice = 0;
		
		fechas = ConsultarBD.consultarFechas(peliculaElegida);
		seleccionIndice = opcionCorrecta("\n\t·····> Introduzca el nº de la fecha que le interesa: ", fechas);
		
		if (seleccionIndice != -1) 
			fechaElegida = fechas.get(seleccionIndice); 		
				
		return (fechaElegida); //sera null si desea volver atras
	}
	
	private static Sesion elegirSesion(Pelicula peliculaElegida, String fechaElegida) {
		
		ArrayList<Integer>	idSesiones = new ArrayList<>();
		int					indiceSesionElegida = 0, idSesionElegida = 0;
		Sesion				sesionElegida = null;
		
		idSesiones = ConsultarBD.consultarSesiones(peliculaElegida, fechaElegida);
		
		indiceSesionElegida = opcionCorrecta("\n\t·····> Introduzca el nº de la sesión que le interesa: ", idSesiones);
		
		idSesionElegida = idSesiones.indexOf(indiceSesionElegida);
		
		sesionElegida = ConsultarBD.consultarSesionElegida(idSesionElegida);
		
		return (sesionElegida);
	}
	
	
	//pide un nº de opcion del menu mostrado previamente, valida si es una opcion dentro de las posibles o si es la de volver
	//si es incorrecto, vuelve a pedir el dato
	//cuando es correcto, devuelve la opcion que ha elegido y que es correcta
	private static <TipoDeArrayList> int opcionCorrecta(String peticion, ArrayList<TipoDeArrayList> arrayObjetos) {
		String				entrada = null;
		int					seleccionIndice = 0;
		boolean				esCorrecto;
		
		do {
			esCorrecto = true;
			System.out.println();
			entrada = Main.teclado.nextLine();
			
			if (ValidarTipoEntrada.checkSoloNumeroEntero(entrada)) {
				seleccionIndice = Integer.parseInt(entrada);
				if ((seleccionIndice < 1 || seleccionIndice > arrayObjetos.size()) && (seleccionIndice != -1)) {
					esCorrecto = false;
					System.out.println("Error, opcion incorrecta, vuelvalo a intentar");//dar formato de msg de error
				}
				else if (seleccionIndice == -1) 
					System.out.println("\n\t...Volviendo atrás..." + "\n".repeat(8));
				else // opcion correcta:
					seleccionIndice--;//se ajusta al indice real, que empezaria en 0 en vez de 1
			}
			else
				System.out.println("Error, dato incorrecto");//dar formato de msg de error
			
		} while (!esCorrecto);
		
		return (seleccionIndice);
	}
	
//	private static Sesion elegirSesion(Pelicula peliculaElegida) {
//		
//		Sesion	sesionElegida = null;
//		Sesion	sesionesDisponibles[] = null;
//		
//		sesionesDisponibles = ConsultarBD.consultarSesiones(peliculaElegida);
//		MostrarDatos.
//		
//		
//	}
	
}
