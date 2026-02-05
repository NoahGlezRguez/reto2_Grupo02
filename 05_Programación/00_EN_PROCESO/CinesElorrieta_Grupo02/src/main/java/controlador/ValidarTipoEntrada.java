package controlador;

import java.util.ArrayList;

import vista.*;

/**
	 * Clase que gestiona la verificación de la entrada de datos por teclado según la necesidad.
	 * Tiene métodos para revisar se introduce lo que se pide.
	 */
public class ValidarTipoEntrada {
			
	/**
	 * Valida que la entrada contenga únicamente letras, aunque
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
		else {
			MostrarMsg.errores(3);
			esCorrecto = false;
		}
		return (esCorrecto);
	}

			
	/**
	 * Analiza un string que recibe por parámetro e indica si este solo contiene dígitos y nada más,
	 * y por tanto se puede transformar en una variable int o no.
	 * 
	 * @param entrada - String a validar
	 * @return true si son solo dígitos, false en caso contrario
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
		
		
	//pide un nº de opcion del menu mostrado previamente, valida si es una opcion dentro de las posibles o si es la de volver
	//si es incorrecto, vuelve a pedir el dato
	//cuando es correcto, devuelve la opcion que ha elegido y que es correcta
		
	/**
	 * Este metodo gestiona la seleccion de una opcion mostrada previamente en un menu.
	 * El usuario debe introducir un dato en respuesta de la peiticion, y debe ser una dentro de las
	 * ofrecidas. El metodo recibe y muestra la peticion, y recibe el array de valores para poder 
	 * verificar si la seleccion del usuario esta dentro del rango del tamaño del arraylist.	
	 * @param <TipoDeArrayList> - indica que el arraylist es de cualquier tipo de objeto
	 * @param peticion - lo que se pide al usuario
	 * @param arrayObjetos - el arraylist de el objeto que sea
	 * @return devuelve un entero que es indice de la opcion elegida dentro del arraylist recibido
	 */
	public static <TipoDeArrayList> int opcionCorrecta(String peticion, ArrayList<TipoDeArrayList> arrayObjetos) {
		String				entrada = null;
		int					seleccionIndice = 0;
		boolean				esCorrecto;
		
		do {
			esCorrecto = true;
			System.out.print(peticion);
			entrada = Main.teclado.nextLine().trim();
			
			if (ValidarTipoEntrada.checkNum(entrada)) {
				seleccionIndice = Integer.parseInt(entrada);
				if ((seleccionIndice < 1 || seleccionIndice > arrayObjetos.size()) && (seleccionIndice != -1)) {
					esCorrecto = false;
					MostrarMsg.errores(8);
				}
				else if (seleccionIndice == -1) 
					System.out.println("\n\t...Volviendo atrás..." + "\n".repeat(8));
				else // opcion correcta:
					seleccionIndice--;//se ajusta al indice real, que empezaria en 0 en vez de 1
			}
			else {
				MostrarMsg.errores(8);
				esCorrecto = false;
			}
			
		} while (!esCorrecto);
		
		System.out.println("\n-----------------------------------------------------------------------\n");
		
		return (seleccionIndice);
	}
		
}
