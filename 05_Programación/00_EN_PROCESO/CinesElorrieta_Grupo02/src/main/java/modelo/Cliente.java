package modelo;
import controlador.*;
import java.util.Scanner;
import java.sql.*;

public class Cliente {
	
	private Scanner teclado = new Scanner(System.in);

	String	dni;
	String	email;
	String	nomCliente;
	String	apellidos;
	String	contraseña;
	int		comprasRealizadas;//esto se borraria? o es trampita? ocupa memoria de forma inutil? resultado de una consulta?
	
	
	/**
	 * constructor vacío
	 */
	public Cliente() {
		
	}
	

	/**
	 * Contructor
	 * @param dni
	 * @param email
	 * @param nomCliente
	 * @param apellidos
	 * @param contraseña
	 */
	public Cliente(String dni, String email, String nomCliente, String apellidos, String contraseña) {
		this.dni = dni;
		this.email = email;
		this.nomCliente = nomCliente;
		this.apellidos = apellidos;
		this.contraseña = contraseña;
	}

	
	/*¿¿QUE METODOS HACEN FALTA AQUI??*/
	
	/*-----------------------GETTERS Y SETTERS--------------------------------------*/
	
	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNomCliente() {
		return nomCliente;
	}


	public void setNomCliente(String nomCliente) {
		this.nomCliente = nomCliente;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getContraseña() { //esto se borraria???********************
		return contraseña;
	}


	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	public int getComprasRealizadas() {
		return comprasRealizadas;
	}

	public void setComprasRealizadas(int comprasRealizadas) {//esto se borraria???********************
		this.comprasRealizadas = comprasRealizadas;
	}
	
	
	/**
	 * this method checks if the letter, length and format of the Dni
	 * is right.
	 * 
	 * @return boolean true for right false for wrong 
	 */
	private boolean validarDni(String cadena) {
		
		boolean valido = true;
		
		if(cadena.length() != 9 && Character.isLetter(cadena.charAt(8)) == false) {
			
			valido = false;
			System.out.println("\nError, formato no válido ");
		}
		
		else if(ValidarTipoEntrada.checkNum(cadena.substring(7)) == false) {
			
			valido = false;
			System.out.println("\nError, formato no válido ");
		}
		
		try {
			
			int num = Integer.parseInt(cadena.substring(7));
			
			if(num < 0) {
				
				valido = false;
				System.out.println("\nError, formato no válido ");
			}
			
		}catch(Exception e) {
			
			valido = false;
			System.out.println("\nError, formato no válido ");
		}
		
		return valido;
	}
	
	
	/**
	 * 
	 * @param cadena
	 * @return
	 */
	private boolean validarExistencia(String cadena){
		
		boolean valido = true;
		Connection 	conexion;
		Statement	sentencia = null;
		ResultSet 	result = null;
		String consulta = "select dni from cliente where dni = "+cadena+";";
		
		try {
			conexion = ConsultarBD.conectarConBD();
			sentencia = conexion.createStatement();
			result = sentencia.executeQuery(consulta);
			
			if(result.next()){
				
				valido = false;
				System.out.println("\nError, El usuario ya existe");
			}
			
			result.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return valido;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String pedirDni(){
		
		String cadena = "";
		do {
			System.out.println("Introduzca DNI: ");
			cadena = teclado.nextLine();
			
			
		}while(validarDni(cadena) == false && validarExistencia(cadena) == false);
		
		return cadena;
	}
	
	/**
	 * this method ask for a name to be set in the future
	 * 
	 * @return String username
	 */
	public String pedirNombre(){
		
		String cadena = "";
		boolean valid = true;
		
		do {
			valid = true;
			System.out.println("Introduzca el nombre: ");
			cadena = teclado.nextLine();
			
			if(!ValidarTipoEntrada.checkSoloLetras(cadena)) {
				valid = false;
				System.out.println("\n Error, introduzca un nombre válido");
			}
			
		}while(!valid);
		
		return cadena;
	}
	
	/**
	 * 
	 * @return
	 */
	public String pedirEmail(){
		
		String cadena = "";
		boolean valid = true;
		
		do {
			
			valid = true;
			System.out.println("\nIntroduzca el correo electrónico");
			cadena = teclado.nextLine();
			
			if(cadena.indexOf('@') != cadena.lastIndexOf('@')) {
				
				System.out.println("\nPor favor introduzca un correo electrónico válido");
				valid = false;
			}
			
			else if(cadena.isBlank()) {
				
				System.out.println("/nEl correo electrónico no puede estar en blanco");
				valid = false;
				
			}
			
			
		}while(!valid);
		
		
		
		return cadena;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String pedirApellido(){

		String cadena = "";
		boolean valid = true;
		
		do {
			valid = true;
			System.out.println("Introduzca el Apellido: ");
			cadena = teclado.nextLine();
			
			if(!ValidarTipoEntrada.checkSoloLetras(cadena)) {
				valid = false;
				System.out.println("\n Error, introduzca un nombre válido");
			}
			
		}while(!valid);
		
		return cadena;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String pedirContraseña(){

		String cadena = "";
		boolean valid = true;
		
		do {
			valid = true;
			if(cadena.isEmpty()) {
				
				valid = false;
				System.out.println("\nLa contraseña no puede estar vacía");

			}
		}while(!valid);

		return cadena;
		
	}
	
	
	
	
	
	
	
	/*------------------------------------------------------------------------------*/
}
