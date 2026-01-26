package modelo;
import controlador.*;

import java.util.Scanner;


public class Cliente {
	
	private static Scanner teclado = new Scanner(System.in);

	String	dni;
	String	email;
	String	nomCliente;
	String	apellidos;
	String	contraseña;
	int		comprasRealizadas;//esto se borraria? o es trampita? ocupa memoria de forma inutil? resultado de una consulta?
	
	
	
	
	/**
	 * <b>Constructor vacío<b>
	 * 
	 */
	public Cliente() {
		
	}


	/** <b>Constructor pidiento datos<b>
	 * <br>
	 * <p>este método recibe un boolean de parámetro para diferenciarlo del constructor vacío,
	 * llama a todos los métodos que piden datos y te los settea en el objeto en cuestión.</p>
	 * 
	 */
	public Cliente(boolean pedirDatos) {
	
		this.dni = pedirDni();
		this.email = pedirEmail();
		this.nomCliente = pedirNombre();
		this.apellidos = pedirApellido();
		this.contraseña = pedirContraseña();
		
	}
	

	/**
	 * <b>Constructor con parámetros</b> <br>
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
		
		if(cadena.length() != 9 || Character.isLetter(cadena.charAt(8)) == false) {
			
			valido = false;
			System.out.println("\nError, formato no válido ");
		}
		
		else if(ValidarTipoEntrada.checkNum(cadena.substring(0, 7)) == false) {
			
			valido = false;
			System.out.println("\nError, formato no válido ");
		}
		
		try {
			
			int num = Integer.parseInt(cadena.substring(0, 7));
			
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
	 * este método pide el DNI del cliente y valida el formato <br>
	 * 
	 * @return String DNI
	 */
	public String pedirDni(){
		
		String cadena = "";
		do {
			System.out.println("Introduzca DNI: ");
			cadena = teclado.nextLine();
			
			
		}while(validarDni(cadena) == false || ConsultarBD.validarExistencia(cadena, "dni") == false);
		
		return cadena;
	}
	
	/**
	 * este método pide el nombre y valida que sean solo letras
	 * 
	 * @return String nombre de cliente
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
			
			else if(cadena.length()>20) {
				valid = false;
				System.out.println("\nError, el nombre no puede contener más de 20 caracteres.");
			}
			
		}while(!valid);
		
		return cadena;
	}
	
	/**
	 * Este método pide el Email y valida el formato
	 * @return String Email
	 */
	public String pedirEmail(){
		
		String cadena = "";
		boolean valid = true;
		
		do {
			
			valid = true;
			System.out.println("\nIntroduzca el correo electrónico");
			cadena = teclado.nextLine();
			
			if(cadena.indexOf('@') != cadena.lastIndexOf('@') || cadena.charAt(0) == '@' || cadena.contains("@") == false || cadena.contains(".") == false) {
				
				System.out.println("\nPor favor introduzca un correo electrónico válido");
				valid = false;
			}
			
			else if(cadena.isBlank()) {
				
				System.out.println("/nEl correo electrónico no puede estar en blanco");
				valid = false;
				
			}
			
			else if(ConsultarBD.validarExistencia(cadena, "mail")==false) {
				
				valid = false;
			}
			
			else if(cadena.length()>100) {
				
				valid = false;
				System.out.println("el correo electrónico no puede tener más de 100 caracteres");
			}
			
			else if(cadena.substring(cadena.indexOf('@'), cadena.length()).indexOf('.') != cadena.substring(cadena.indexOf('@'), cadena.length()).lastIndexOf('.')){
				
				valid = false;
				System.out.println("\nPor favor introduzca un correo electrónico válido");
				
			}
			
			
		}while(!valid);
		
		
		
		return cadena;
		
	}
	
	@Override
	public String toString() {
		
		return "DNI: " + dni + "\n Email: " + email + "\n Nombre: " + nomCliente + "\n apellido: " + apellidos
				+ "\n contraseña: " + contraseña;
	}


	/**
	 * Este método pide aún apellido y lo valida
	 * @return String apellido validado
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
			
			else if(cadena.length()>20) {
				
				valid = false;
				System.out.println("\n El apellido no puede tener más de 20 caracteres");
			}
			
		}while(!valid);
		
		return cadena;
	}
	
	
	/**
	 * Este método pide la contraseña al usuario y la valida
	 * @return String contraseña
	 */
	public String  pedirContraseña(){

		//falta validar que sea de un largo en específico
		String cadena = "";
		boolean valid = true;
		
		do {
			
			valid = true;
			System.out.println("\nintroduzca la contraseña: ");
			cadena = teclado.nextLine();
			
			if(cadena.isEmpty()) {
				
				valid = false;
				System.out.println("\nFormáto no válido");

			}
			
			else if(cadena.length() > 254) {
				
				valid = false;
				System.out.println("\nLa contraseña es demasiado larga");
			}
			
			else if(cadena.length() < 8) {
				valid = false;
				System.out.println("\nLa contraseña debe contener al menos 8 caracteres");
			}
			
		}while(!valid);

		return cadena;
		
	}
	
	
	/*------------------------------------------------------------------------------*/
}
