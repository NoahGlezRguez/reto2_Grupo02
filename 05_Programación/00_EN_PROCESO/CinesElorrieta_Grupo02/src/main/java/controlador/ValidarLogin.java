package controlador;
import modelo.*;
import vista.*;

public class ValidarLogin {

	
	/**
	 * este método pide al cliente que introduzca sus credenciales, valida la existencia del usuario en la 
	 * base de datos y de ser correcto, muestra un mensaje de bienvenida y devuelve un objeto cliente
	 * con los datos rellenados
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
		
		iniciado = ObtenerDatosBD.Consultarlogin(dni, contraseña);
		
		if( iniciado!= null) {
			MostrarMsg.operacionRealizada(4);
			MostrarMsg.bienvenida(iniciado.getNomCliente());
		}

		return (iniciado);
	}
	
	/**
	 * Este método llama a otro para crear un usuario nuevo, pidiendo por pantalla que
	 * se introduzcan los datos asociados al cliente y una vez 
	 * validado lo envía a la base de datos. Da la opcion al cliente de confirmar
	 * si los datos que ha introducido confirma guardarlos, y en caso de indicar que no, se
	 * cancela la compra.
	 * 
	 * @return objeto cliente con los datos intrducidos
	 */
	public static Cliente crearCuenta(){

		MostrarMsg.mensajeSignUp();
		
		Cliente nuevo = new Cliente(true);
	
		String pw = nuevo.pedirContraseña();
		System.out.println("\t- Datos introducidos:");
		System.out.println(nuevo.toString());
		
		if (Menu.siNo("Confirmar y guardar datos") == 0)
			InsertarDatosBD.InsertarNuevoUsuario(nuevo, pw);
		
		else {
			System.out.println("\n\t...Operación cancelada...");
			nuevo = null;
		}
		
		return (nuevo);
	}

}
