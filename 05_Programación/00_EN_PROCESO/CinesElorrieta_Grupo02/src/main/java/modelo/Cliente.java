package modelo;
import controlador.*;
import vista.MostrarMsg;


public class Cliente {
	
	String	dni;
	String	email;
	String	nomCliente;
	String	apellidos;

	
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
		
	}
	
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
			System.out.println(MostrarMsg.errores(0));
		}
		
		else if(ValidarTipoEntrada.checkNum(cadena.substring(0, 7)) == false) {
			
			valido = false;
			System.out.println(MostrarMsg.errores(0));
		}
		
		try {
			
			int num = Integer.parseInt(cadena.substring(0, 7));
			
			if(num < 0) {
				
				valido = false;
				System.out.println(MostrarMsg.errores(0));
			}
			
		}catch(Exception e) {
			
			valido = false;
			System.out.println(MostrarMsg.errores(0));
		}
		
		return valido;
	}
	
	
	
	
	/**
	 * este método pide el DNI del cliente y valida el formato <br>
	 * 
	 * @return String DNI
	 */
	private String pedirDni(){
		
		String cadena = "";
		do {
			System.out.println("Introduzca DNI: ");
			cadena = Main.teclado.nextLine();
			
			
		}while(validarDni(cadena) == false || ConsultarBD.validarExistencia(cadena, "dni") == false);
		
		return cadena;
	}
	
	/**
	 * este método pide el nombre y valida que sean solo letras
	 * 
	 * @return String nombre de cliente
	 */
	private String pedirNombre(){
		
		String cadena = "";
		boolean valid = true;
		
		do {
			valid = true;
			System.out.println("Introduzca el nombre: ");
			cadena = Main.teclado.nextLine();
			
			if(!ValidarTipoEntrada.checkSoloLetras(cadena)) {
				valid = false;
				System.out.println(MostrarMsg.errores(0));
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
	private String pedirEmail(){
		
		String cadena = "";
		boolean valid = true;
		
		do {
			
			valid = true;
			System.out.println("\nIntroduzca el correo electrónico");
			cadena = Main.teclado.nextLine();
			
			if(cadena.indexOf('@') != cadena.lastIndexOf('@') || cadena.charAt(0) == '@' || cadena.contains("@") == false || cadena.contains(".") == false) {
				
				System.out.println(MostrarMsg.errores(0));
				valid = false;
			}
			
			else if(cadena.isBlank()) {
				
				System.out.println(MostrarMsg.errores(0));
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
				System.out.println(MostrarMsg.errores(0));
				
			}
			
			
		}while(!valid);

		return cadena;
		
	}
	
	@Override
	public String toString() {
		
		return "\t- DNI: " + dni + "\n\t- Email: " + email + "\n\t- Nombre: "
				+ nomCliente + "\n\t- Apellido(s): " + apellidos + "\n\t- Contraseña: ********\n";
	}


	/**
	 * Este método pide aún apellido y lo valida
	 * @return String apellido validado
	 */
	private String pedirApellido(){

		String cadena = "";
		boolean valid = true;
		
		do {
			valid = true;
			System.out.println("Introduzca el Apellido: ");
			cadena = Main.teclado.nextLine();
			
			if(!ValidarTipoEntrada.checkSoloLetras(cadena)) {
				valid = false;
				System.out.println(MostrarMsg.errores(0));
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
			cadena = Main.teclado.nextLine();
			
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
