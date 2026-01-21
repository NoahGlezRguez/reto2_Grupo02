package vista;

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
        
        System.out.print(a);
        
	}
	
	public static void errores(int error) {
		/*
		 * codigos de error y su significado:
		 *
		    0 Ningún dato introducido, por favor inténtelo de nuevo
			1 Solo se permiten hasta 8 caracteres, por favor inténtelo de nuevo
			2 Inserte solamente letras, por favor
			3 Inserte un número entero positivo, por favor
			4 Inserte solamente caracteres alfanuméricos, por favor
			5 Inserte un nº decimal positivo, por favor
			
			7 Usuario y/o contraseña incorrectos, por favor inténtelo de nuevo
			8 Opción incorrecta, por favor inténtelo de nuevo
			
		
			13 El nombre que intenta asignar no tiene letras, pruebe con otro, por favor
			
			
			
		 * 
		 * */
	}
	
	
}
