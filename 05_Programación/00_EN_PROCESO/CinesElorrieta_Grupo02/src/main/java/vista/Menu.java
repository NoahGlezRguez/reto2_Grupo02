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
	
	public static Pelicula cartelera(Pelicula cartelera[]) {
		
		String		pelicula = null;
		int			seleccionIndice = 0;
		boolean		esCorrecto, salirYa = false;
		String		entrada;
		Pelicula	seleccionPeli;
		
		do {
			esCorrecto = true;
			if (cartelera != null) {
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\t\tCARTELERA ACTUAL\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				for(int i = 0; i < cartelera.length; i++) {
					pelicula = """
			~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			\tPelícula nº %d:
				\t[🎬]Título:	%s
				\t[🎞️]Género:	%s
				\t[⌛]Duración:	%s minutos
			~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
							""".formatted(i + 1, cartelera[i].getNombrePeli(), cartelera[i].getGenero(), cartelera[i].getDuracion());
					System.out.print(pelicula);
				}
			
				System.out.println("\n\t·····> Introduzca el nº de la película (o -1 para cancelar la compra): ");
				entrada = Main.teclado.nextLine();
				if (ValidarTipoEntrada.checkSoloNumeroEntero(entrada)) {
					seleccionIndice = Integer.parseInt(entrada);
					if ((seleccionIndice < 1 || seleccionIndice > cartelera.length) && (seleccionIndice != -1)) {
						esCorrecto = false;
						System.out.println("Error, opcion incorrecta, vuelvalo a intentar");//dar formato de msg de error
					}
					if (seleccionIndice == -1) {
						System.out.println("\n\t...Cancelando compra..." + "\n".repeat(15));
						esCorrecto = true;
						salirYa = true;
					}
				}
				else
					System.out.println("Error, dato incorrecto");//dar formato de msg de error
			}
			else
				System.out.println("Error, no hay cartelera disponible ahora mismo, lo sentimos");//dar formato de msg de error
				
		} while (!esCorrecto && !salirYa);
		if (!salirYa)
			seleccionPeli = cartelera[seleccionIndice - 1];
		else
			seleccionPeli = null;
		return (seleccionPeli);
	}
	
	//recibe las fechas ya consultadas y la peli en cuestion, las muestra con formato, pide una saleccion, la valida y devuelve la fecha elegida validada
	public static String fechas(String fechas[], Pelicula peliculaElegida) {
		
		String		fecha = null;
		int			seleccionIndice = 0;
		boolean		esCorrecto, volverAtras = false;
		String		entrada;
		String		fechasDisponibles = null;
		
		do {
			esCorrecto = true;
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\t\tFECHAS DISPONIBLES\n\t\t[🎬] Película: "
						+ peliculaElegida.getNombrePeli() + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			for(int i = 0; i < fechas.length; i++) {
				fechasDisponibles = """				
					\n\tOpción nº %d:\t- Día %s/%s/%s.						
				""".formatted(i + 1, fechas[i].substring(8, 10), fechas[i].substring(5, 7), fechas[i].substring(0, 4));
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
				
				if ((seleccionIndice < 1 || seleccionIndice > fechas.length) && (seleccionIndice != -1)) {
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
			fecha = fechas[seleccionIndice - 1];
		else if(volverAtras)
			fecha = "1";		
		
		return (fecha);

	}
	
	
	
//	public static Pelicula sesiones(Sesion sesiones[]) { //falta cambiar el contenido y ajustarlo a las sesiones, esto es un frankestein
//		
//		String		pelicula = null;
//		int			seleccionIndice = 0;
//		boolean		esCorrecto;
//		String		entrada;
//		Pelicula	seleccionPeli;
//		
//		do {
//			esCorrecto = true;
//			if (sesiones != null) {
//				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\t\t\tSESIONES PELÍCULA %s\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//				for(int i = 0; i < sesiones.length; i++) {
//					pelicula = """
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//\tPelícula nº %d:
//	\t[🎬]Título:	%s
//	\t[🎞️]Género:	%s
//	\t[⌛]Duración:	%s minutos
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
//							""".formatted(i + 1, sesiones[i].getNombrePeli(), sesiones[i].getGenero(), sesiones[i].getDuracion());
//					System.out.print(pelicula);
//				}
//			
//				System.out.println("\n\t·····> Introduzca el nº de la película (o -1 para cancelar la compra): ");
//				entrada = Main.teclado.nextLine();
//				if (ValidarTipoEntrada.checkSoloNumeroEntero(entrada)) {
//					seleccionIndice = Integer.parseInt(entrada);
//					if ((seleccionIndice < 1 || seleccionIndice > sesiones.length) && (seleccionIndice != -1)) {
//						esCorrecto = false;
//						System.out.println("Error, opcion incorrecta, vuelvalo a intentar");//dar formato de msg de error
//					}
//					if (seleccionIndice == -1) {
//						System.out.println("Cancelando compra..." + "\n".repeat(15));
//						esCorrecto = true;
//						return (null);
//					}
//				}
//				else
//					System.out.println("Error, dato incorrecto");//dar formato de msg de error
//			}
//			else
//				System.out.println("Error, no hay cartelera disponible ahora mismo, lo sentimos");//dar formato de msg de error
//				
//		} while (!esCorrecto);
//		seleccionPeli = cartelera[seleccionIndice - 1];
//		return (seleccionPeli);
//	}
}
