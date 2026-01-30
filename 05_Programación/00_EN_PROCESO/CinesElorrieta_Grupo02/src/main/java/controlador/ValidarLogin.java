package controlador;
import modelo.*;
import vista.*;

public class ValidarLogin {

	
	/**
	 * este método valida la existencia del usuario en la 
	 * DB y de ser correcto devuelve un objeto cliente con los datos rellenados
	 *  
	 * @return <ul> <li>objeto cliente con datos</li> <li><b>null</b> si no existe</li> </ul>
	 */
	public static Cliente iniciarSesion(){
		
		String dni = "";
		String contraseña = "";
		
		MostrarMsg.mensajeSignIn();
		
		System.out.println("\tIntroduzca DNI:");
		dni = Main.teclado.nextLine();
		
		System.out.println("\n\tIntroduzca contraseña");
		contraseña = Main.teclado.nextLine();
		
		Cliente iniciado = new Cliente();
		
		iniciado = ConsultarBD.Consultarlogin(dni, contraseña);
		
		if( iniciado!= null) {
			System.out.println("\n===================================");
			System.out.println("\tBienvenide "+iniciado.getNomCliente());
			System.out.println("===================================");
		}

		return (iniciado);
	}
	
	/**
	 * 
	 * este método llama a otro para crear un usuario nuevo y una vez 
	 * validado lo envía a la base de datos
	 */
	public static Cliente crearCuenta(){

		MostrarMsg.mensajeSignUp();
		
		Cliente nuevo = new Cliente(true);
	
		String pw = nuevo.pedirContraseña();
		System.out.println("\tDatos introducidos:");
		System.out.println(nuevo.toString());
		
		if (Menu.siNo("Confirmar y guardar datos") == 0)
			ConsultarBD.InsertarNuevoUsuario(nuevo, pw);
		
		else {
			System.out.println("\n\t...Operación cancelada...");
			nuevo = null;
		}
		
		return (nuevo);
	}

}
