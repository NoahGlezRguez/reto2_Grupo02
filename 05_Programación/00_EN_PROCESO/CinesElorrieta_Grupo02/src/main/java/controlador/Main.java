package controlador;

import java.util.Scanner;

import vista.*;

/**
 * Clase principal del programa que gestiona el flujo más básico de la venta de entradas del cine.
 */
public class Main {

	public static Scanner 	teclado = new Scanner(System.in);

	/**
	 * Método base, que maneja el bucle y reinicia o cierra el programa cuando se indica.
	 * Solo muestra la pantalla de bienvenida la primera vez o cuando se indica que se reinicia.
	 * Cuando se da a la opcion de salir, ofrece una confirmacion.
	 * Al salir del programa muestra una pantalla de despedida.
	 * @param args
	 */
	public static void main(String[] args) {
		
		int		opc = 0;
		String	operaciones[] = {"Comenzar compra", "Salir y apagar esta máquina"};
		boolean	apagar = false,	reiniciar = true;;
		
		while (!apagar) {
			if (reiniciar) {
				MostrarMsg.bienvenida();
				teclado.nextLine();
				reiniciar = false;
			}
			opc = Menu.opciones("Operaciones disponibles", operaciones, "Seleccione la operación que desea realizar");
			if (opc == 0) {
				OperacionesCompra.realizarCompra();
				//else //(no se si esto sera realmente necesario...)
				//	OperacionesCompra.cerrarCompra(compra);//reset del programa y sus variables que guardaban valores, genera un registro y da opcion a generar factura + despedida personalizada
			}
			else {
				if (Menu.siNo("¿Está segurx de que quiere apagar esta máquina?") == 0)
					apagar = true;
			}
		}
		MostrarMsg.despedida();
	}
}
