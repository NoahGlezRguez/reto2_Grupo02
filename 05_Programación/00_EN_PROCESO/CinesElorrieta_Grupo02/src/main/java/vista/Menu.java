package vista;

import controlador.*;
import modelo.*;

/**
 * Esta clase contiene métodos que muestran en pantalla distintos tipos de menú, con el formato correspondiente
 * y toda la información necesaria.
 */
public class Menu {

	/**  Este metodo recibe un titulo, las opciones entre las que puede elegir el usuario, y una peticion para 
	 * el usuario para que introduzca una respuesta. Hasta que la seleccion no sea valida, repetira la peticion
	 * con este menu. Al validarse, imprime una linea de separacion.
	 *
	 * @param titulo - informacion sobre las opciones que se muestran
	 * @param opciones - listados de opciones para el usuario
	 * @param peticion - lo que se pide al usuario que introduzca
	 * @return - indice real de la seleccion dentro de las opciones que se recibieron
	 */
	public static int opciones(String titulo, String opciones[], String peticion) {
		
		int		seleccion = -1;
		boolean	esCorrecto;
		String	entrada;
		

		do {
			esCorrecto = true;
			System.out.printf("\n\t------> %s <<<<<<------\n\n", titulo);
			
			for (int i = 0; i < opciones.length; i++) 
				System.out.printf("\t\t%d.- %s.\n", i + 1, opciones[i]);
			
			System.out.printf("\n\t·····> %s: ", peticion);
			
			entrada = Main.teclado.nextLine().trim();
			
			if (ValidarTipoEntrada.checkNum(entrada)) {
				
				seleccion = Integer.parseInt(entrada);
				
				if ((seleccion < 1) || (seleccion > opciones.length)) {
					MostrarMsg.errores(8);
					esCorrecto = false;
				}
				else
					seleccion--;
			}
			else 
				esCorrecto = false;
					
		} while (!esCorrecto);
		
		System.out.println("\n-----------------------------------------------------------------------\n");
		
		return (seleccion);
	}
	
	/**
	 * Este metodo gestiona peticiones de tipo Si o No. Si el usuario introduce una opcion incorrecta,
	 * repite en bucle este menu. Al validarse, imprime una linea de separacion.
	 * @param peticion
	 * @return
	 */
	public static int siNo(String peticion) {
			
		int		seleccion = 0;
		boolean	esCorrecto;
		String	entrada, menu = """
		\n\t------> %s <<<<<<------
		\t\t1.- Sí.\n\t\t2.- No.\n
		\t·····> Introduzca su respuesta:\t""".formatted(peticion);
		
		do {
			esCorrecto = true;
			System.out.print(menu);
			entrada = Main.teclado.nextLine().trim();
			
			if (ValidarTipoEntrada.checkNum(entrada)) {
				
				seleccion = Integer.parseInt(entrada);
				
				if ((seleccion < 1) || (seleccion > 2)) {
					MostrarMsg.errores(8);
					esCorrecto = false;
				}
			}
			else 
				esCorrecto = false;
			
		} while (!esCorrecto);
		
		System.out.println("\n-----------------------------------------------------------------------\n");
		
		return (seleccion - 1);
		}
	
	/**
	 * Este tipo de menu, imprime con formato la informacion de una pelicula.
	 * @param i - numero de pelicula dentro de la cartelera
	 * @param tituloPeli - titulo de la pelicula
	 * @param genero - genero de la pelicula
	 * @param duracion - duracion en minutos de la pelicula
	 */
	public static void cartelera(int i, String tituloPeli, String genero, int duracion) {
		
		String		pelicula = null;

		pelicula = """
			~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			\tPelícula nº %d:
				\t[🎬]Título:	%s
				\t[🎞️]Género:	%s
				\t[⌛]Duración:	%d minutos
			~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
							""".formatted(i, tituloPeli, genero, duracion);
		
		System.out.print(pelicula);
	}
	
	/**
	 * Este menu muestra en pantalla la linea de fecha disponible para una pelicula.
	 * @param i - numero de fecha posible
	 * @param tituloPeli - titulo de la pelicula en cuestion
	 * @param fechaOfertada - fecha con formato original
	 */
	public static void fecha(int i, String tituloPeli, String fechaOfertada) {
		
		String		fecha = null;
		
		fecha = """				
			\n\tOpción nº %d:\t→ Día %s/%s/%s						
			""".formatted(i, fechaOfertada.substring(8, 10), fechaOfertada.substring(5, 7), fechaOfertada.substring(0, 4));
			
		System.out.print(fecha);
	}
	
	/**
	 * Este menu muestra en pantalla la informacion de una sesion disponible de una pelicula.
	 * @param i - numero de sesion disponible
	 * @param peliculaElegida - objeto de la pelicula con su informacion respectiva
	 * @param fechaElegida - fecha de esa sesion
	 * @param duracion - duracion de la pelicula
	 * @param sala - sala donde se ofrece
	 * @param precio - precio de la sesion
	 */
	public static void sesion(int i, String peliculaElegida, String fechaElegida, String duracion, int sala, double precio) {

		String		sesionDisponible = null;
		
		sesionDisponible = """				
		\n\tOpción nº %d:\tHora %sh Sala %d - Precio %.2f€						
		""".formatted(i, duracion.substring(0, 5), sala, precio);
		
		System.out.print(sesionDisponible);
	}
	
	/**
	 * Este menu muetsra en pantalla la información actualizada del aforo disponible de esa sesion y la peticion
	 * al cliente de que introduzca una seleccion.
	 * @param sesionElegida
	 * @param compra
	 */
	public static void pedirNumPersonas(Sesion sesionElegida, Compra compra) { 
		
		String	info = """
				\tAforo actual disponible para esta sesión:
				\t  → %s asientos libres de %s
				""".formatted(sesionElegida.getAforoDisponible(), sesionElegida.getSala().getAforoSala());
		
		sesionElegida.setAforoDisponible(ConsultarDatosBD.consultarAforo(sesionElegida.getIdSesion(), compra));
		
		System.out.print(info);
		msgVolverAtras();
		System.out.print("\t·····> Introduzca el nº de personas: ");
			
	}
	
	/**
	 * Este menu recibe informacion, y un indicador de qué cabecera imprimir para otros menus.
	 * @param tipoMenu - indicador del tipo de cabecera
	 * @param tituloPeli - pelicula indicada por el cliente
	 * @param fecha - fecha de la sesion elegida
	 * @param horaSesion - hora de la sesion elegida
	 */
	public static void cabeceraMenu(int tipoMenu, String tituloPeli, String fecha, String horaSesion) {
		
		String	lineaPeli = "\n\t[🎬] Película → ", fechaFormateada = null;

		if (fecha != null) 
			fechaFormateada = "%s/%s/%s".formatted(fecha.substring(8, 10), fecha.substring(5, 7), fecha.substring(0, 4));
		
			
		System.out.print("\n\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\t\t");
		switch (tipoMenu) {
			case 1:
				System.out.println("Cartelera disponible\n");
				break;
			case 2:
				System.out.println("Fechas disponibles\n" + lineaPeli + tituloPeli);
				break;
			case 3:
				System.out.println("Sesiones disponibles\n" + lineaPeli + tituloPeli + " - "+ fechaFormateada);
				break;
			case 4:
				System.out.println("""
						RESERVA DE PLAZAS
						%s %s
						\t\t%s a las %sh
						""".formatted(lineaPeli, tituloPeli, fechaFormateada, horaSesion));
				break;
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	/**
	 * Este metodo muestra un mensaje por pantalla en casi todas las pantallas del proceso de compra para
	 * indicar al cliente que podria volver facilmente a la pantalla anterior.
	 */
	public static void msgVolverAtras() {
		String msg = """
			\n\n\n\n--  ---  ----  ----  ----  ----  ----  ----  ----  ---  --
			\tPara VOLVER ATRÁS introduzca:	-1
			--  ---  ----  ----  ----  ----  ----  ----  ----  ---  --	
			""";
		System.out.println(msg);
	}
	
}
