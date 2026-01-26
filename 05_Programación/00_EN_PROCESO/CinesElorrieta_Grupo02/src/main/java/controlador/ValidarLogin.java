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
	
	/**
	 * este método valida la existencia del usuario en la 
	 * DB y de ser correcto devuelve un objeto cliente con los datos rellenados
	 *  
	 * @return <ul> <li>objeto cliente con datos</li> <li><b>null</b> si no existe</li> </ul>
	 */
	private static Cliente iniciarSesion(){
		
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
		
		return iniciado;
		
	}
	
	/**
	 * 
	 * este método llama a otro para crear un usuario nuevo y una vez 
	 * validado lo envía a la base de datos
	 */
	private static void crearCuenta(){
		
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

//	public static boolean validar(String usuario, String password) {
//		Connection conexion = ConsultarBD.conectarConBD();
//		Statement sentencia = null;
//		ResultSet r = null;
//		boolean valido = false;
//
//		try {
//			sentencia = conexion.createStatement();
//			r = sentencia.executeQuery("select NomCli, userpassword from cliente;");
//
//			while (r.next()) {
//				String u = r.getString("NomCli");
//				String p = r.getString("userpassword");
//
//				if (u.equals(usuario) && p.equals(password)) {
//					valido = true;
//					break;
//				}
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		try {
//			conexion.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return valido;
//	}
}
