package controlador;

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
		Pelicula	peliculaElegida = null;
		Sesion		sesionElegida = null;
		boolean		seCancelaLaCompra = false, volverALaCartelera = false, volverAFechas = false, volverASesiones = false;
		int			numPersonas = 0;
		//entrada 	cestacompra[] = null;
		
		
		do {
			peliculaElegida = elegirPelicula();
			
			if (peliculaElegida == null)
				seCancelaLaCompra = true;
		 	else {
		 		do{
			 		fechaElegida = elegirFecha(peliculaElegida);
			 		
			 		if (fechaElegida.equals("1")) 
			 			volverALaCartelera = true;
			 		else {
				 		do {
				 			sesionElegida = elegirSesion(peliculaElegida, fechaElegida);	
					 		
				 			if (sesionElegida == null)
				 				volverAFechas = true;
				 			else {
				 				do {
				 					numPersonas = Menu.pedirNumPersonas();
				 					if (numPersonas < 0)
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
	
	private static Pelicula elegirPelicula() {
		
		Pelicula cartelera[] = null;
		Pelicula peliculaElegida = null;
		
		cartelera = ConsultarBD.consultarCartelera();
		
		if (cartelera != null) 
			peliculaElegida = Menu.cartelera(cartelera);
		
		return (peliculaElegida);
		
	}
	
	private static String elegirFecha(Pelicula peliculaElegida) {
		
		String		fechas[] = null;
		String		fechaElegida = null;
		
		fechas = ConsultarBD.consultarFechas(peliculaElegida);
		fechaElegida = Menu.fechas(fechas, peliculaElegida);
		
		return (fechaElegida);
	}
	
	private static Sesion elegirSesion(Pelicula peliculaElegida, String fechaElegida) {
		
		Sesion		sesiones[] = null;
		Sesion		sesionElegida = null;
		
		sesiones = ConsultarBD.consultarSesiones(peliculaElegida, fechaElegida);
		if (sesiones != null)
			sesionElegida = Menu.sesiones(sesiones, fechaElegida, peliculaElegida);
		
		return (sesionElegida);
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
