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
        
        System.out.print(a);        
	}
	
	public static void bienvenida(String nombreCliente) {
        
        String a = """
			***********************************************************************
			*   #   ★☆★         										★☆★  #   *
        		     		     	¡Bienvenide %s!         
			*   #   ★☆★         										★☆★  #   *
			***********************************************************************
				        		""".formatted(nombreCliente);
        
        System.out.print(a);        
	}
	
	public static void despedida() {
        
        String a = """
			***********************************************************************
			*                                                                     *
			*   ###############################################################   *
			*   #    		         ¡HASTA LA PRÓXIMA!    	       	          #   *
			*   ###############################################################   *
			*                                                                     *
			*            [🎥]  [🎞️]  [🎬]  [🎞️]  [📽️]  [🎞️]  [🎦]  [📽️]             *
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
			*            [🎥]  [🎞️]  [🎬]  [🎞️]  [📽️]  [🎞️]  [🎦]  [📽️]             *
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
				"en la conexión\n",
				"no se ha podido procesar sus datos\n",
				"No quedan sesiones con aforo el día seleccionado",
				"formato no válido\n",
				"el usuario no existe\n",
				"el usuario ya existe\n",
				"Usuario y/o contraseña incorrectos, por favor inténtelo de nuevo\n",
				"debe introducir al menos 8 caracteres\n",
				"Por favor seleccione un opción válida\n",
				"Por favor introduzca un valor positivo\n",
				"el formato introducido es muy largo\n",
				"no has introducido nada",
				"ahora mismo no hay nada en su carrito",
				"no hay cartelera disponible ahora mismo, lo sentimos"
			};
			
		String msgError = """
		\n\n\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		\t>>>>> Error: %s
		//////////////////////////////////////////////////////////\n\n
		""".formatted(msg[num]);
	
		System.out.println(msgError);

	}
	
	public static void mensajeSignIn() {
		
		String msg = 
				"===================================\n\tInicio de sesion\n===================================";
		System.out.println(msg);
	}
	
	public static void mensajeSignUp() {
		
		String msg = 
				"===================================\n\tCrear nueva cuenta\n===================================";
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
				\n\n··························································
				   - - - -> %s
				····························································\n\n
				""".formatted(msg[indice]);
		
		System.out.println(output);
	}
	
}
