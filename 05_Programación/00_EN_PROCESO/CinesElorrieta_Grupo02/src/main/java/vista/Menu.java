package vista;

import controlador.*;
import modelo.Pelicula;

/**
 * Esta clase contiene métodos que muestran en pantalla distintos tipos de menú, con el formato correspondiente
 * y toda la información necesaria.
 */
public class Menu {

	/**
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
		boolean		esCorrecto;
		String		entrada;
		Pelicula	seleccionPeli;
		
		do {
			esCorrecto = true;
			if (cartelera != null) {
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\t\t\tCARTELERA ACTUAL\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
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
						System.out.println("Cancelando compra..." + "\n".repeat(15));
						esCorrecto = true;
						return (null);
					}
				}
				else
					System.out.println("Error, dato incorrecto");//dar formato de msg de error
			}
			else
				System.out.println("Error, no hay cartelera disponible ahora mismo, lo sentimos");//dar formato de msg de error
				
		} while (!esCorrecto);
		seleccionPeli = cartelera[seleccionIndice - 1];
		return (seleccionPeli);
	}
}
