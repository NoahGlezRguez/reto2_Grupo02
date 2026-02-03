package controlador;

import vista.*;

/**
	 * Clase que gestiona la verificación de la entrada de datos por teclado según la necesidad.
	 * Tiene métodos para revisar si solo son letras, o es alfanumérico, un nº decimal o entero, etc.
	 */
public class ValidarTipoEntrada {
			
		/**
		 * Valida que la entrada contenga únicamente letras,
		 * permite espacios en blanco y tabulaciones, pero no números ni símbolos.
		 * 
		 * @param entrada - String a validar
		 * @return true si solo contiene letras (y espacios o tabulaciones), false en caso contrario
		 */
		public static boolean checkSoloLetras(String entrada) {
			
			boolean esCorrecto = true;

			if(entrada != null && !entrada.isBlank()) {
				entrada = entrada.trim();
				for (int i = 0; i < entrada.length(); i++) {
					if (!Character.isLetter(entrada.charAt(i)) && entrada.charAt(i) != ' ' && entrada.charAt(i) != '\t') 
						esCorrecto = false;
				}
				if (!esCorrecto)
					MostrarMsg.errores(2);
			}
			else
				MostrarMsg.errores(0);
			return (esCorrecto);
		}
		
		/**
		 * Valida que la entrada sea alfanumérica (letras y números, y espacios o tabulaciones).
		 * @param entrada - String a validar introducida por el usuario.
		 * @return true si solo contiene letras, números y espacios o tabulaciones, false en caso contrario
		 */
		public static boolean checkSoloAlfanumerico(String entrada) {
			boolean esCorrecto = true;

			if(entrada != null && !entrada.isBlank()) {
				entrada = entrada.trim();
				for (int i = 0; i < entrada.length(); i++) {
					if (!Character.isLetterOrDigit(entrada.charAt(i)) && entrada.charAt(i) != ' ' && entrada.charAt(i) != '\t') 
						esCorrecto = false;
				}
				if (!esCorrecto)
					MostrarMsg.errores(4);
			}
			else
				MostrarMsg.errores(0);
			return (esCorrecto);
		}
		
			
		/**
		 * Permite dígitos y un único punto decimal como separador.
		 * 
		 * @param entrada - String a validar
		 * @return true si es un número decimal válido, false en caso contrario
		 */
		public static boolean checkSoloNumeroDecimal(String entrada) {
			
			boolean esCorrecto = true;
			boolean	hayNums = false;
			int		numPuntos = 0;

			if(entrada != null && !entrada.isBlank()) {
				entrada = entrada.trim();
				for (int i = 0; i < entrada.length(); i++) {
					
					if (Character.isDigit(entrada.charAt(i)))
						hayNums = true;
					
					else if (entrada.charAt(i) == '.') {
						
						numPuntos++;
						
						if (numPuntos > 1) {
							esCorrecto = false;
							MostrarMsg.errores(5);
							return (esCorrecto);
						}
					}
					else {
						esCorrecto = false;
						MostrarMsg.errores(5);
						return (esCorrecto);
					}		
				}
				
				if (!hayNums) {
					MostrarMsg.errores(5);
					return (hayNums);
				}
			}
			else
				MostrarMsg.errores(0);
			return (esCorrecto);
		}
			
		/**
		 * Analiza un string que recibe por parámetro e indica si este solo contiene dígitos y nada más.
		 * 
		 * @param entrada - String a validar
		 * @return true si es son solo dígitos, false en caso contrario
		 */
		public static boolean checkNum(String entrada) {
			
			boolean esCorrecto = true;
			
			entrada = entrada.trim();
			
			try {
				
				Integer.parseInt(entrada);
				
			}catch(Exception e) {
				
				esCorrecto = false;
				
			}
			
			return (esCorrecto);
	}
		
}
