package modelo;
import controlador.*;
import vista.MostrarMsg;

/**
 * Esta clase genera un objeto cliente con varios atributos y varios metodos utilizados con este
 */
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
	 * Analiza si el formato de dni es correcto, sin verificar si la letra es la que pertenece.
	 * Solamente cubre los digitos y una letra. Si la letra no fuera la correspondiente pero lo demas
	 * si que estuviera correcto, devolvera true igualmente.
	 * 
	 * @return true si es correcto, false en caso contrario.
	 */
	public boolean validarDni(String cadena) {
		
		boolean valido = true;
		int 	num = -1;
		
		if(cadena.length() != 9 || Character.isLetter(cadena.charAt(8)) == false) 			
			valido = false;

		if (valido) {	
			try {
				
				num = Integer.parseInt(cadena.substring(0, 7));
				
				if(num < 0) 					
					valido = false;
	
			}catch(Exception e) {
				valido = false;
			}
		}
		
		if (!valido)
			MostrarMsg.errores(3);
		
		return valido;
	}
	
	
	
	
	/**
	 * Este método pide el DNI del cliente y valida el formato y que no existe ya en la base de datos.
	 * Mientras no sea correcto o este duplicado, lo vuelve a pedir.
	 * 
	 * @return String DNI 
	 */
	private String pedirDni(){
		
		String cadena = "";
		do {
			System.out.print("Introduzca DNI: ");
			cadena = Main.teclado.nextLine();
			
			
		}while(validarDni(cadena) == false || ConsultarDatosBD.validarExistencia(cadena, "dni") == false);
		
		return cadena;
	}
	
	/**
	 * Este método pide el nombre y valida que sean solo letras. Hasta que el formato no sea
	 * el correcto, volverá a solicitar que se introduzcan los datos.
	 * 
	 * @return String nombre de cliente
	 */
	private String pedirNombre(){
		
		String cadena = "";
		boolean valid = true;
		
		do {
			valid = true;
			System.out.print("Introduzca el nombre: ");
			cadena = Main.teclado.nextLine().trim();
			
			if(!ValidarTipoEntrada.checkSoloLetras(cadena))
				valid = false;

			else if (cadena.length() > 20) 
				valid = false;

			if (!valid)
				MostrarMsg.errores(3);
		}while(!valid);
		
		return cadena;
	}
	
	/**
	 * Este método pide el Email y verifica que sea correcto. Hasta que el formato no sea
	 * el correcto, volverá a solicitar que se introduzcan los datos.
	 * @return String Email
	 */
	private String pedirEmail(){
		
		String cadena = "";
		boolean valid = true;
		
		do {
			
			valid = true;
			System.out.print("\nIntroduzca el correo electrónico: ");
			cadena = Main.teclado.nextLine().trim();
			
			if(cadena.isBlank() || cadena.length() > 100 ) 
				valid = false;	
			
			else if(cadena.indexOf('@') != cadena.lastIndexOf('@') || cadena.charAt(0) == '@' || cadena.contains("@") == false || cadena.contains(".") == false) 
				valid = false;

			else if(ConsultarDatosBD.validarExistencia(cadena, "mail") == false) 			
				valid = false;

			else if(cadena.substring(cadena.indexOf('@'), cadena.length()).indexOf('.') != cadena.substring(cadena.indexOf('@'), cadena.length()).lastIndexOf('.'))				
				valid = false;

			if (!valid)
				MostrarMsg.errores(3);
			
		}while(!valid);

		return cadena;
		
	}
	
	@Override
	public String toString() {
		
		return "\t- DNI: " + dni + "\n\t- Email: " + email + "\n\t- Nombre: "
				+ nomCliente + "\n\t- Apellido(s): " + apellidos + "\n\t- Contraseña: ********\n";
	}


	/**
	 * Este método pide aún apellido y lo valida. Hasta que el formato no sea
	 * el correcto, volverá a solicitar que se introduzcan los datos.
	 * @return String apellido validado
	 */
	private String pedirApellido(){

		String cadena = "";
		boolean valid = true;
		
		do {
			valid = true;
			System.out.print("Introduzca el Apellido: ");
			cadena = Main.teclado.nextLine().trim();
			
			if(!ValidarTipoEntrada.checkSoloLetras(cadena)) {
				valid = false;
				MostrarMsg.errores(3);
			}
			
			else if(cadena.length()>20) {
				
				valid = false;
				MostrarMsg.errores(10);
			}
			
		}while(!valid);
		
		return cadena;
	}
	
	
	/**
	 * Este método pide la contraseña al usuario y la valida. Hasta que el formato no sea
	 * el correcto, volverá a solicitar que se introduzcan los datos.
	 * @return String contraseña
	 */
	public String  pedirContraseña(){

		String cadena = "";
		boolean valid = true;
		
		do {
			
			valid = true;
			System.out.print("Introduzca la contraseña: ");
			cadena = Main.teclado.nextLine();
			
			if(cadena.isEmpty()) {
				
				valid = false;
				MostrarMsg.errores(11);

			}
			
			else if(cadena.length() > 254) {
				
				valid = false;
				MostrarMsg.errores(10);
			}
			
			else if(cadena.length() < 8) {
				valid = false;
				MostrarMsg.errores(7);
			}
			
		}while(!valid);

		return cadena;
		
	}

}
