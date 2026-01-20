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
		Pelicula	cartelera[];
		Pelicula	peliculaElegida = null;
		//Sesion		sesionElegida = null;
		
		
		cartelera = ConsultarBD.consultarCartelera();
		if (cartelera != null) 
			peliculaElegida = Menu.cartelera(cartelera);
		
		if (peliculaElegida == null)
		 	return (true);
	 	else {
	 		System.out.println("Hola, todo ok!!");
	 	}
		return (false);
	}
}
