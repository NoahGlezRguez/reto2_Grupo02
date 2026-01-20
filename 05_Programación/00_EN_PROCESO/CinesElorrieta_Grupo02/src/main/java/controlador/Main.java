package controlador;

import java.util.Scanner;

import vista.Menu;
import vista.MostrarMsg;


public class Main {

	public static String 	entrada = "";
	public static Scanner 	teclado = new Scanner(System.in);

	public static void main(String[] args) {
		
		int		opc = 0;
		String	operaciones[] = {"Comprar entradas", "Salir y apagar esta máquina"};
		boolean	apagar = false;
		
		while (!apagar) {
			MostrarMsg.bienvenida();
			teclado.nextLine();
			opc = Menu.opciones("Operaciones disponibles", operaciones, "Seleccione la operación que desea realizar");
			if (opc == 0)
				OperacionesCompra.comprarEntradas();
			else {
				if (Menu.siNo("¿Está segurx de que quiere apagar esta máquina?") == 0)
					apagar = true;
			}
				
		}
		MostrarMsg.despedida();
	}
}
