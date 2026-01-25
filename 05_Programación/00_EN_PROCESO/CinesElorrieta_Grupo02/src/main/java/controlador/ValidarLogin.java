package controlador;

import java.util.Scanner;
import modelo.*;

public class ValidarLogin {
	
	private static Scanner teclado = new Scanner(System.in);
	
	public static void main (String [] args) {
		
		
		//iniciarSesion();
		crearCuenta();
	}
	
	private static void mensajeSignIn() {
		
		String msg = 
				"===================================\n\tInicio de sesion\n===================================";
		System.out.println(msg);
	}
	
	private static void mensajeSignUp() {
		
		String msg = 
				"===================================\n\tCrear nueva cuenta\n===================================";
		System.out.println(msg);
	}
	
	
	private static void iniciarSesion(){
		
		String dni = "";
		String contraseña = "";
		
		mensajeSignIn();
		
		System.out.println("\tIntroduzca DNI:");
		dni = teclado.nextLine();
		
		System.out.println("\n\tIntroduzca contraseña");
		contraseña = teclado.nextLine();
		
		Cliente iniciado = new Cliente();
		
		iniciado = ConsultarBD.Consultarlogin(dni, contraseña);
		
		
		if( iniciado!= null) {
			System.out.println("\n===================================");
			System.out.println("\tBienvenide "+iniciado.getNomCliente());
			System.out.println("===================================");
		}
		
		else{
			
			System.out.println("\tEl usuario no existe");
		}
		
	}
	
	private static void crearCuenta(){
		
		String dni = "";
		String contraseña = "";
		boolean action = true;
		
		mensajeSignUp();
		
		Cliente nuevo = new Cliente(action);
		
		ConsultarBD.InsertarNuevoUsuario(nuevo);
		
		
		
		
	}

}
