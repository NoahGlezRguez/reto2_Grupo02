package modelo;

public class Cliente {

	String	dni;
	String	email;
	String	nomCliente;
	String	apellidos;
	String	contraseña;
	int		comprasRealizadas;//esto se borraria? o es trampita? ocupa memoria de forma inutil? resultado de una consulta?
	
	public Cliente() {
		
	}

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
	
	/*------------------------------------------------------------------------------*/
}
