package vista;

import java.util.ArrayList;

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
	
	//recibe las fechas ya consultadas y la peli en cuestion, las muestra con formato, pide una saleccion, la valida y devuelve la fecha elegida validada
	public static String fechas(ArrayList<String> fechas, Pelicula peliculaElegida) {
		
		String		fecha = null;
		int			seleccionIndice = 0;
		boolean		esCorrecto, volverAtras = false;
		String		entrada;
		String		fechasDisponibles = null;
		
		do {
			esCorrecto = true;
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\t\tFECHAS DISPONIBLES\n\t\t[🎬] Película: "
						+ peliculaElegida.getNombrePeli() + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			for(int i = 0; i < fechas.size(); i++) {
				fechasDisponibles = """				
					\n\tOpción nº %d:\t- Día %s/%s/%s.						
				""".formatted(i + 1, fechas.get(i).substring(8, 10), fechas.get(i).substring(5, 7), fechas.get(i).substring(0, 4));
				System.out.print(fechasDisponibles);
			}
			
			fechasDisponibles = """				
			\n--  ---  ----  ----  ----  ----  ----  ----  ----  ---  --
			\tPARA VOLVER A LA CARTELERA introduzca:	-1.
			\n--  ---  ----  ----  ----  ----  ----  ----  ----  ---  --			
									""";
			System.out.print(fechasDisponibles);
			
			System.out.println("\n\t·····> Seleccione una opción: ");
			
			entrada = Main.teclado.nextLine();
			
			if (ValidarTipoEntrada.checkSoloNumeroEntero(entrada)) {
				seleccionIndice = Integer.parseInt(entrada);
				
				if ((seleccionIndice < 1 || seleccionIndice > fechas.size()) && (seleccionIndice != -1)) {
					esCorrecto = false;
					System.out.println("Error, opcion incorrecta, vuelvalo a intentar");//dar formato de msg de error
				}	
				else if (seleccionIndice == -1) {
					System.out.println("\n\t...Regresando a la cartelera..." + "\n".repeat(15));
					esCorrecto = true;
					volverAtras = true;
				}
			}
			else {
				System.out.println("Error, debe ser un nº de los que se muestran como opcion, intentelo de nuevo");//dar formato de msg de error
				esCorrecto = false;
			}
				
		} while (!esCorrecto && !volverAtras);
		
		if (!volverAtras)
			fecha = fechas.get(seleccionIndice - 1);
		else if(volverAtras)
			fecha = "1";		
		
		return (fecha);

	}
	
	public static Sesion sesiones(ArrayList<Sesion> sesiones, String fechaElegida, Pelicula peliculaElegida) {
		
		Sesion		sesionElegida = null;
		int			seleccionIndice = 0;
		boolean		esCorrecto;
		String		entrada = null, sesionesDisponibles = null;
		
		do {
			esCorrecto = true;
			if (sesiones != null) {
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\t\tSESIONES DISPONIBLES del día "
						+ fechaElegida
						+ "\n\t\t[🎬] Película: "
						+ peliculaElegida.getNombrePeli() + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				for(int i = 0; i < sesiones.size(); i++) {
					sesionesDisponibles = """				
							\n\tOpción nº %d:\tHora %sh Sala %d - precio %.2f€.						
							""".formatted(i + 1, sesiones.get(i).getHoraInicio().substring(0, 5), sesiones.get(i).getSalaSesion(), sesiones.get(i).getPrecio());
					System.out.print(sesionesDisponibles);
				}
				sesionesDisponibles = """				
						\n--  ---  ----  ----  ----  ----  ----  ----  ----  ---  --
						\tPARA VOLVER A LA CARTELERA introduzca:	-1.
						\n--  ---  ----  ----  ----  ----  ----  ----  ----  ---  --			
												""";
				System.out.printf("%s\n\t·····> Introduzca el nº de la sesión: ", sesionesDisponibles);
				
				entrada = Main.teclado.nextLine();
				if (ValidarTipoEntrada.checkSoloNumeroEntero(entrada)) {
					seleccionIndice = Integer.parseInt(entrada);
					if ((seleccionIndice < 1 || seleccionIndice > sesiones.size()) && (seleccionIndice != -1)) {
						esCorrecto = false;
						System.out.println("Error, opcion incorrecta, vuelvalo a intentar");//dar formato de msg de error
					}
					if (seleccionIndice == -1) {
						System.out.println("...Volviendo atrás, a ver fechas..." + "\n".repeat(15));
						esCorrecto = true;
						return (null);
					}
					else {
						
					}
						
				}
				else {
					System.out.println("Error, dato incorrecto");//dar formato de msg de error
					esCorrecto = false;
				}
					
			}
			else {
				System.out.println("Error, no hay cartelera disponible ahora mismo, lo sentimos");//dar formato de msg de error
				esCorrecto = false;
			}
				
		} while (!esCorrecto);
		sesionElegida = sesiones.get(seleccionIndice - 1);
		return (sesionElegida);
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
}
