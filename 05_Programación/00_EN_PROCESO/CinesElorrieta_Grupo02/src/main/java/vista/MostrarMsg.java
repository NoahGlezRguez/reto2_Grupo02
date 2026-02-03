package vista;

public class MostrarMsg {

	public static void bienvenida() {
		        
        String a = """
			***********************************************************************
			*                                                                     *
			*   ###############################################################   *
			*   #                                                             #   *
			*   #   ★☆★         ¡BIENVENIDX A CINES ELORRIETA!         ★☆★  #   *
			*   #                                                             #   *
			*   #                Donde experimentar nuevos mundos...          #   *
			*   #              desde la más cómoda de las butacas:.           #   *
			*   #                                                             #   *
			*   ###############################################################   *
			*                                                                     *
			*            [🎥]  [🎞️]  [🎬]  [🎞️]  [📽️]  [🎞️]  [🎦]  [📽️]    	      *
			*                                                                     *
			*              → Pulsa la tecla Enter para comenzar ←                 *
			*                                                                     *
			***********************************************************************
				        		""";
        
        System.out.print("\n".repeat(5) + a);        
	}
	
	public static void bienvenida(String nombreCliente) {
        
        String a = """
			\n\n***********************************************************************
			                 ★☆★                  
                                 ¡Bienvenide %s!         
			                 ★☆★         										
			***********************************************************************\n\n
				        		""".formatted(nombreCliente);
        
        System.out.print(a);        
	}
	
	public static void despedida() {
        
        String a = """
			***********************************************************************
			*                                                                     *
			*   ###############################################################   *
			*   #                    ¡HASTA LA PRÓXIMA!                       #   *
			*   ###############################################################   *
			*                                                                     *
			*            [🎥]  [🎞️]  [🎬]  [🎞️]  [📽️]  [🎞️]  [🎦]  [📽️]          *
			*                                                                     *
			***********************************************************************
				        		""";
        
        System.out.print(a + "\n".repeat(10));

	}
	
	
	public static void despedida(String nombreCliente) {
        
        String a = """
			***********************************************************************
			*                                                                     *
			*   ###############################################################   *
                              ¡HASTA LA PRÓXIMA, %s!     	       	 
			*   ###############################################################   *
			*                                                                     *
			*            [🎥]  [🎞️]  [🎬]  [🎞️]  [📽️]  [🎞️]  [🎦]  [📽️]          *
			*                                                                     *
			***********************************************************************
				        		""".formatted(nombreCliente);
        
        System.out.print(a + "\n".repeat(10));

	}
	
	/**
	 * contiene un array de Strings con los siguientes mensajes: </br>
	 * <ol start = "0">
	 * <li>Error, formato no válido</li>
	 * <li>Error, el usuario no existe</li>
	 * <li>Error, el usuario ya existe</li>
	 * <li>Error en la conexión</li>
	 * <li>Usuario y/o contraseña incorrectos, por favor inténtelo de nuevo</li>
	 * <li>Error, debe introducir al menos 8 caracteres</li>
	 * <li>Por favor seleccione un opción válida</li>
	 * <li>Por favor introduzca un valor positivo</li>
	 * <li>Error, el formato introducido es muy largo</li>
	 * <li></li>
	 * </ol>
	 * 
	 * @param num ID del error
	 * @return String mensaje  
	 */
	public static void errores(int num) {
				
		String [] msg = {
				"no se ha podido establecer conexión con la base de datos",//0
				"no se ha podido procesar sus datos, pruebe más tarde...",//1
				"ya no quedan sesiones con aforo el día seleccionado",//2
				"formato no válido",//3
				"el usuario no existe",//4
				"el usuario ya existe",//5
				"usuario y/o contraseña incorrecto/s, prueba otra vez",//6
				"debe introducir mínimo 8 caracteres",//7
				"opción no válida",//8
				"SRDFÑFGLIHDPKFGUHWOERUGHPEAROUGHPQAEORUGHQEPOR",//9
				"el formato introducido es muy largo",//10
				"no has introducido nada",//11
				"ahora mismo no hay nada en su carrito",//12
				"no hay cartelera disponible ahora mismo, lo sentimos",//13
				"debe ser mínimo una persona",//14
				"ha excedido del aforo disponible",//15
				"no se ha encontrado el archivo con la info de conexión con la BBDD",//16
				"ha ocurrido un error al intentar leer el archivo"//17
			};
			
		String msgError = """
		\n\n\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		\tError → %s
		//////////////////////////////////////////////////////////\n\n
		""".formatted(msg[num]);
	
		System.out.println(msgError);

	}
	
	public static void mensajeSignIn() {
		
		String msg = 
				"\n\n======================= Inicio de sesion obligatorio =======================\n";
		System.out.println(msg);
	}
	
	public static void mensajeSignUp() {
		
		String msg = 
				"\n\n======================= Creación de un nuevo usuario =======================\n";
		System.out.println(msg);
	}
	
	/**
	 * este método devuleve un mensaje tipo String 
	 * <ol start = "0">
	 * <li>Error en la conexión</li>
	 * <li>Error en los datos</li>
	 * <li>Nuevo usuario guardado correctamente</li>
	 * <li>No quedan sesiones con aforo el día seleccionado</li>
	 * <li>Se ha realizado la compra satisfactoriamente</li>
	 * <li></li>
	 * </ol>
	 * @param num ID del mensaje 
	 * @return String mensaje 
	 */

	
	
	//^^^^^^^^^CORREGIR LA DOC DE LOS METODOS^^^^^^^^^^^^^^

	public static void operacionRealizada(int indice) {
		String msg[] = {
			"Se ha añadido una entrada a su cesta satisfactoriamente",
			"Se ha eliminado una entrada de su cesta satisfactoriamente",
			"Se ha realizado la compra satisfactoriamente",
			"Se ha creado la nueva cuenta satisfactoriamente",
			"Se ha iniciado sesión satisfactoriamente",	
			"Se ha generado su factura satisfactoriamente"
				
		};
		
		String	output = """
				\n\n····························································
				   - - - -> %s
				····························································\n\n
				""".formatted(msg[indice]);
		
		System.out.println(output);
	}
	
}
