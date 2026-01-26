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
		String cadena = "";
		boolean action = true;
		int opc = 0;
		
		mensajeSignUp();
		
		Cliente nuevo = new Cliente(action);
		
		do {
			System.out.println("Está seguro de añadir el usuario con los siguientes datos: \n");
			nuevo.toString();
			System.out.println("1.Si\n2.No");
			cadena = teclado.nextLine();
			
			if(ValidarTipoEntrada.checkNum(cadena)){
				
				opc = Integer.parseInt(cadena);
				
				if(opc == 1) {
					
					ConsultarBD.InsertarNuevoUsuario(nuevo);
					
				}
				
				else if(opc == 2){
					
					System.out.println("operación cancelada");
				}
				
				else {
					
					action = false;
				}
				
			}
			
			else {
				
				action = false;
				System.out.println("formato no válido ");
			}
		
		}while(!action);
		
		
		
		
	}

}
