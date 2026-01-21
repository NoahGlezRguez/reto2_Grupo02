package controlador;

import modelo.*;
import vista.*;

public class OperacionesCompra {

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
