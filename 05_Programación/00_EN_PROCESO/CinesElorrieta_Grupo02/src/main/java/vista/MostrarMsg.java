package vista;
import java.util.ArrayList;

import modelo.*;
public class MostrarMsg {

	public static void bienvenida() {
		        
        String a = """
			***********************************************************************
			*                                                                     *
			*   ###############################################################   *
			*   #                                                             #   *
			*   #   ★☆★         ¡BIENVENIDX A CINES ELORRIETA!	     ★☆★  #   *
			*   #                                                             #   *
			*   #                Donde experimentar nuevos mundos...	  #   *
			*   #              desde la más cómoda de las butacas:.           #   *
			*   #                                                             #   *
			*   ###############################################################   *
			*                                                                     *
			*            [🎥]  [🎞️]  [🎬]  [🎞️]  [📽️]  [🎞️]  [🎦]  [📽️]             *
			*                                                                     *
			*              → Pulsa la tecla Enter para comenzar ←                 *
			*                                                                     *
			***********************************************************************
				        		""";
        
        System.out.print(a);        
	}
	
	
	public static void despedida() {
        
        String a = """
			***********************************************************************
			*                                                                     *
			*   ###############################################################   *
			*   #    		¡HASTA LA PRÓXIMA!     	       	          #   *
			*   ###############################################################   *
			*                                                                     *
			*            [🎥]  [🎞️]  [🎬]  [🎞️]  [📽️]  [🎞️]  [🎦]  [📽️]             *
			*                                                                     *
			***********************************************************************
				        		""";
        
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
	public static String errores(int num) {
		/*
		 * codigos de error y su significado:
		    0 Ningún dato introducido, por favor inténtelo de nuevo
			1 Solo se permiten hasta 8 caracteres, por favor inténtelo de nuevo
			2 Inserte solamente letras, por favor
			3 Inserte un número entero positivo, por favor
			4 Inserte solamente caracteres alfanuméricos, por favor
			5 Inserte un nº decimal positivo, por favor
			7 Usuario y/o contraseña incorrectos, por favor inténtelo de nuevo
			8 Opción incorrecta, por favor inténtelo de nuevo
			13 El nombre que intenta asignar no tiene letras, pruebe con otro, por favor
			
			dejé esto aquí por si lo quereís modificar en el futuro pero personalmente
			creo que "formato no válido" incluye varias opciones
		 * 
		 * */
		
		String linea = "=============================\n";
		
		String [] error = {
				
				linea+"\tError, formato no válido\n"+linea,
				linea+"\tError, el usuario no existe\n"+linea,
				linea+"\tError, el usuario ya existe\n"+linea,
				linea+"\tError en la conexión\n"+linea,
				linea+"\tUsuario y/o contraseña incorrectos, por favor inténtelo de nuevo\n"+linea,
				linea+"\tError, debe introducir al menos 8 caracteres\n"+linea,
				linea+"\tPor favor seleccione un opción válida\n"+linea,
				linea+"\tPor favor introduzca un valor positivo\n"+linea,
				linea+"\tError, el formato introducido es muy largo\n"+linea
				
				};
		
		return error[num];
		
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
	public static String msgBD(int num){
		
		String [] msg = {
			"\t--> Error en la conexión\n",
			"\t--> Error en los datos\n",
			"\t--> Nuevo usuario guardado correctamente",
			"\t--> No quedan sesiones con aforo el día seleccionado",
			"\t--> Se ha realizado la compra satisfactoriamente"
		};
		
		return msg[num];
	}
	/**
	 * este método recibe por parámetros en el siguiente orden:</br>
	 * <ol>
	 * <li>String idcompra</li>
	 * <li>String Fecha</li>
	 * <li>String Plataforma de compra</li>
	 * <li>String nombre del cliente</li>
	 * <li>String DNI</li>
	 * <li>String Descuento</li>
	 * <li>String importe</li>
	 * <li>String total</li>
	 * <li>ArrayList de entradas</li>
	 * </ol>
	 * <p> los rellena en un string con fromato y devulve la
	 * factura a imprimir</p>
	 * @param 8 String + 1 arraylist
	 * @return String con formato
	 */
	public static String factura(String a, String b, String c, String d, String e, String f, String g, String h, ArrayList<Entrada> entrada) {
		
		String hola = "";
		
		for(int i = 0; i<entrada.size(); i++) {
			hola +=  entrada.get(i).toString();
		};
		
		String formato = 
				"""
				------------------------------------
				Compra nº:			%15S
				Fecha:				%15S
				Plataforma:			%15S
				Cliente:			%15S
				DNI:				%15S
				
				%S
				
				
				Descuento:			%15S
				Importe:			%15S
				
				
				Total:				%15S
				-------------------------------------
				""".formatted(a, b, c, d, e, hola, f, g, h) ;
		
		return formato;
	}
	
	
}
