package controlador;

import vista.*;

/**
 * Clase que gestiona la verificación de la entrada de datos por teclado según
 * la necesidad. Tiene métodos para revisar si solo son letras, o es
 * alfanumérico, un nº decimal o entero, etc.
 */
public class ValidarTipoEntrada {

	/**
	 * Valida que la entrada contenga únicamente letras, permite espacios en blanco
	 * y tabulaciones, pero no números ni símbolos.
	 * 
	 * @param entrada - String a validar
	 * @return true si solo contiene letras (y espacios o tabulaciones), false en
	 *         caso contrario
	 */
	public static boolean checkSoloLetras(String entrada) {

		boolean esCorrecto = true;

		if (entrada != null && !entrada.isBlank()) {
			entrada = entrada.trim();
			for (int i = 0; i < entrada.length(); i++) {
				if (!Character.isLetter(entrada.charAt(i)) && entrada.charAt(i) != ' ' && entrada.charAt(i) != '\t')
					esCorrecto = false;
			}
			if (!esCorrecto)
				MostrarMsg.errores(2);
		} else
			MostrarMsg.errores(0);
		return (esCorrecto);
	}

	/**
	 * Valida que la entrada sea alfanumérica (letras y números, y espacios o
	 * tabulaciones).
	 * 
	 * @param entrada - String a validar introducida por el usuario.
	 * @return true si solo contiene letras, números y espacios o tabulaciones,
	 *         false en caso contrario
	 */
	public static boolean checkSoloAlfanumerico(String entrada) {
		boolean esCorrecto = true;

		if (entrada != null && !entrada.isBlank()) {
			entrada = entrada.trim();
			for (int i = 0; i < entrada.length(); i++) {
				if (!Character.isLetterOrDigit(entrada.charAt(i)) && entrada.charAt(i) != ' '
						&& entrada.charAt(i) != '\t')
					esCorrecto = false;
			}
			if (!esCorrecto)
				MostrarMsg.errores(4);
		} else
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
		boolean hayNums = false;
		int numPuntos = 0;

		if (entrada != null && !entrada.isBlank()) {
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
				} else {
					esCorrecto = false;
					MostrarMsg.errores(5);
					return (esCorrecto);
				}
			}

			if (!hayNums) {
				MostrarMsg.errores(5);
				return (hayNums);
			}
		} else
			MostrarMsg.errores(0);
		return (esCorrecto);
	}

	/**
	 * Comprueba si un caracter es digito
	 * 
	 * @param c valor de entrada
	 * @return return true, si es digito, falso caso contrario
	 */
	private static boolean esDigito(char c) {
		return c >= '0' && c <= '9';
	}

	/**
	 * Comprueba si una cadena solo contiene digitos
	 * 
	 * @param s valor de entrada
	 * @return true si es digito, falso caso contrario
	 */

	private static boolean esDigito(String s) {
		boolean r = true;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!esDigito(c)) {
				r = false;
				break;
			}
		}

		return r;
	}

	/**
	 * Evalua si una cadena ingresada es vacia. Retorna true si es asi, falso en
	 * caso contrario.
	 * 
	 * @param s valor de entrada
	 * @return retorna true si la entrada es vacia o tiene 0 de longitud
	 */
	private static boolean esVacio(String s) {
		return s == null || s.length() == 0;
	}

	private static boolean checkNum(String entrada) {

		boolean esCorrecto = true;
		char c = ' ';

		for (int i = 0; i < entrada.length(); i++) {
			c = entrada.charAt(i);
			if (!Character.isDigit(c)) {
				esCorrecto = false;
				System.out.println("Error, esto no es un numero...");// formatear con un msg de error
			}
		}

		return (esCorrecto);
	}

}
