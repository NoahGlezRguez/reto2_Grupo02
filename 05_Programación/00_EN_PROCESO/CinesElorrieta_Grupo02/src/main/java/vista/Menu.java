package vista;

import controlador.*;

public class Menu {

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
			
			if (ValidarTipoEntrada.checkSoloNumeroPositivoEntero(entrada)) {
				
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
	
	public static int siNo(String titulo, String peticion) {
			
			int		seleccion = 0;
			boolean	esCorrecto;
			String	entrada;
			
			do {
				esCorrecto = true;
				System.out.printf("\n\t------> %s <<<<<<------\n\n", titulo);
				
				System.out.print("\t\t1.- Sí.\n\t\t2.- No.\n");
				
				System.out.printf("\n\t·····> %s: ", peticion);
				
				entrada = Main.teclado.nextLine();
				
				if (ValidarTipoEntrada.checkSoloNumeroPositivoEntero(entrada)) {
					
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
	
}
