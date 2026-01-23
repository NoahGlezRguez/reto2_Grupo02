package vista;

import controlador.*;
import modelo.*;

/**
 * Esta clase contiene métodos que muestran en pantalla distintos tipos de menú, con el formato correspondiente
 * y toda la información necesaria.
 */
public class Menu {

	/**  ***FALTA TERMINAR ESTA DOCUMENTACION***     ***    ***  **  * * * * * * * 
	 *
	 * @param titulo
	 * @param opciones
	 * @param peticion
	 * @return
	 */
	public static int opciones(String titulo, String opciones[], String peticion) {
		
		int		seleccion = 0;
		boolean	esCorrecto;
		String	entrada;
		

		do {
			esCorrecto = true;
			System.out.printf("\n\t------> %s <<<<<<------\n\n", titulo);
			
			for (int i = 0; i < opciones.length; i++) 
				System.out.printf("\t\t%d.- %s.\n", i + 1, opciones[i]);
			
			System.out.printf("\n\t·····> %s: ", peticion);
			
			entrada = Main.teclado.nextLine();
			
			if (ValidarTipoEntrada.checkSoloNumeroEntero(entrada)) {
				
				seleccion = Integer.parseInt(entrada);
				
				if ((seleccion < 1) || (seleccion > opciones.length)) {
					//MostrarMensajeDeError.mostrarError(8);
					esCorrecto = false;
				}
			}
			else 
				esCorrecto = false;
					
		} while (!esCorrecto);
		
		System.out.println("\n-----------------------------------------------------------------------\n");
		
		return (seleccion - 1);
	}
	
	//sirve para las decisiones de confirmacion
	public static int siNo(String titulo) {
			
			int		seleccion = 0;
			boolean	esCorrecto;
			String	entrada;
			
			do {
				esCorrecto = true;
				System.out.printf("\n\t------> %s <<<<<<------\n\n", titulo);
				
				System.out.print("\t\t1.- Sí.\n\t\t2.- No.\n");
				
				System.out.printf("\n\t·····> Introduzca su respuesta: ");
				
				entrada = Main.teclado.nextLine();
				
				if (ValidarTipoEntrada.checkSoloNumeroEntero(entrada)) {
					
					seleccion = Integer.parseInt(entrada);
					
					if ((seleccion < 1) || (seleccion > 2)) {
						//MostrarMensajeDeError.mostrarError(8);
						esCorrecto = false;
					}
				}
				else 
					esCorrecto = false;
				
			} while (!esCorrecto);
			
			System.out.println("\n-----------------------------------------------------------------------\n");
			
			return (seleccion - 1);
		}
	
	//muestra una pelicula de la cartelera
	public static void cartelera(int i, String tituloPeli, String genero, int duracion) {
		
		String		pelicula = null;

		pelicula = """
			~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			\tPelícula nº %d:
				\t[🎬]Título:	%s
				\t[🎞️]Género:	%s
				\t[⌛]Duración:	%d minutos
			~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
							""".formatted(i + 1, tituloPeli, genero, duracion);
					System.out.print(pelicula);

	}
	
	//muestra una fecha de una peli de la cartelera
	public static void fecha(int i, String tituloPeli, String fechaOfertada) {
		
		String		fecha = null;
		
		fecha = """				
			\n\tOpción nº %d:\t- Día %s/%s/%s.						
			""".formatted(i + 1, fechaOfertada.substring(8, 10), fechaOfertada.substring(5, 7), fechaOfertada.substring(0, 4));
			System.out.print(fecha);

	}
	
	//muestra los datos de una sesion de una pelicula de un dia determinado
	public static void sesion(int i, String peliculaElegida, String fechaElegida, String duracion, int sala, double precio) {

		String		sesionDisponible = null;
		
		sesionDisponible = """				
		\n\tOpción nº %d:\tHora %sh Sala %d - precio %.2f€.						
		""".formatted(i, duracion.substring(0, 5), sala, precio);
		
		System.out.print(sesionDisponible);

	}
	
	public static int pedirNumPersonas(Sesion sesionElegida) {
		
		int	numPersonas = 0;
		boolean esCorrecto;
		String entrada = "";
		String	peticion = """
				\t\t- ¿Para cuántas personas desea comprar esta entrada?
				\t\t- Aforo actual disponible para esta sesión: %s asientos libres de %s.
				\t\t\t<<<<Para volver atrás, introduzca -1 >>>>
				\t\t- Introduzca su respuesta: """.formatted(sesionElegida.getAforoDisponible(), sesionElegida.getSala().getAforoSala());
		do {
			esCorrecto = true;
			
			System.out.print(peticion);
			entrada = Main.teclado.nextLine();
			if (!ValidarTipoEntrada.checkSoloNumeroEntero(entrada))//si no es un nº entero
				esCorrecto = false;
			else {
				numPersonas = Integer.parseInt(entrada);
				if (numPersonas < 1 && numPersonas != -1) {
					esCorrecto = false;
					System.out.println("Error, debe ser mínimo para una persona");
					numPersonas = 0;
				}
			}
			
		} while(!esCorrecto); //mientras sea incorrecto o no le de a "volver atrás"
		
		return (numPersonas);
	}
	
	public static void cabeceraMenu(int tipoMenu, String tituloPeli, String fecha) {
		
		String	lineaPeli = "\n\t\t[🎬] Película: ", fechaFormateada = null;

		if (fecha != null) 
			fechaFormateada = "%s/%s/%s".formatted(fecha.substring(8, 10), fecha.substring(5, 7), fecha.substring(0, 4));
		
			
		System.out.print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\t\t");
		switch (tipoMenu) {
			case 1:
				System.out.println("Cartelera disponible\n");
				break;
			case 2:
				System.out.println("Fechas disponibles\n" + lineaPeli + tituloPeli);
				break;
			case 3:
				System.out.println("Sesiones disponibles\n" + lineaPeli + tituloPeli + " - día " + fechaFormateada);
				break;	
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	public static void msgVolverAtras() {
		String msg = """
			\n--  ---  ----  ----  ----  ----  ----  ----  ----  ---  --
			\tPARA VOLVER ATRÁS introduzca:	-1.
			\n--  ---  ----  ----  ----  ----  ----  ----  ----  ---  --			
											
			""";
		System.out.println(msg);
	}
	
}
