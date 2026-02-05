package vista;

/**
 * Esta clase contiene metodos que imprimen mensajes meramente informativos.
 */
public class MostrarMsg {

	/**
	 * Imprime la pantalla de bienvenida de la maquina de venta de entradas del cine
	 */
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
	
	/**
	 * Esta pantalla muestra la bienvenida al cliente de forma personalizada cuando este
	 * inicia sesion
	 * @param nombreCliente - nombre del cliente para personalizar un poco el mensaje
	 */
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
	
	/**
	 * Despedida generica empleada en la pantalla de apagado de la maquina y tambien
	 * en el final del proceso de compra.
	 */
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
	
	/**
	 * Este metodo recibe el nombre del usuario para ofrecer una despedida personalizada
	 * cuando este termina el proceso de compra.
	 * @param nombreCliente - el nombre que se usa en el mensaje para eprsonalizar este.
	 */
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
	 * Este metodo imprime un mensaje de error segun se indique por parametros
	 * Contiene un array de Strings con los siguientes mensajes de error: </br>
	 * <ol start = "0">
	 * <li>Error de conexion con la BBDD</li>
	 * <li>Error al procesar los datos</li>
	 * <li>Sesiones agotadas ese dia</li>
	 * <li>Formato incorrecto</li>
	 * <li>Usuario NO existe</li>
	 * <li>Usuario YA existe</li>
	 * <li>Usuario/pw incorrectos</li>
	 * <li>no llega a 8 caracteres</li>
	 * <li>opcion incorrecta</li>
	 * <li>no hay fecha disponible</li>
	 * <li>formato demasiado largo</li>
	 * <li>entrada de datos vacia</li>
	 * <li>carrito vacio</li>
	 * <li>sin cartelera disponible</li>
	 * <li>debe ser minimo una persona</li>
	 * <li>excede del aforo disponible</li>
	 * <li>no encuentra el archivo con datos de conexion con BBDD</li>
	 * <li>error al intentar leer fichero</li>
	 * </ol>
	 * 
	 * @param num -  ID del error a mostrar
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
				"no hay fecha disponible para esta pelicula",//9
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
	
	/**
	 * Muestra una linea que indica que se esta en el proceso de iniciar sesion
	 */
	public static void mensajeSignIn() {
		
		String msg = 
				"\n\n======================= Inicio de sesion obligatorio =======================\n";
		System.out.println(msg);
	}
	
	/**
	 * Muestra una linea que indica que se esta en el proceso de crear un nuevo usuario
	 */
	public static void mensajeSignUp() {
		
		String msg = 
				"\n\n======================= Creación de un nuevo usuario =======================\n";
		System.out.println(msg);
	}
	
	/**
	 * Este metodo muestra un mensaje de la operacion que se realiza, segun indica el valor recibido por parametros.
	 * @param indice - indice del mensaje a mostrar
	 */
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
