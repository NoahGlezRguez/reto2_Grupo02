package controlador;

import java.util.ArrayList;

import modelo.*;
import vista.*;

/**
 * Esta clase contiene las distintas operaciones dentro del proceso de compra. Es el corazón del programa.
 */
public class OperacionesCompra {
	
	public static void realizarCompra() {
		
		Compra 		compra = new Compra();
		Cliente		comprador = new Cliente();
		Entrada		nuevaEntrada = new Entrada();
		
		String		operaciones[] = {"Agregar entrada al carrito", "Eliminar entrada del carrito", "Consultar carrito", "Terminar compra y pagar", "Cancelar compra y salir"};
		boolean		finalizarCompra = false;
		int			opc = 0;
		
		while (!finalizarCompra) {
			
			opc = Menu.opciones("Operaciones disponibles", operaciones, "Seleccione la operación que desea realizar");
				
			switch (opc) {
					
			case 0:
				nuevaEntrada = comprarEntradas(compra);
				if (nuevaEntrada != null)
					compra.agregarEntrada(nuevaEntrada);
				break;
				
			case 1:
				if (compra.getEntradas().size() > 0) 
					quitarEntradaDelCarrito(compra);
				else 
					System.out.println("\n\t - - - >>> Ahora mismo su cesta está vacía");
				break;
				
			case 2:	
				if (compra.getEntradas().size() > 0) {
					compra.mostrarCesta();
				}
				else
					System.out.println("\n\t - - - >>> Ahora mismo su cesta está vacía");
				break;
				
			case 3:
				if (compra.getEntradas().size() > 0) {
					pago(compra, comprador);
					finalizarCompra = true;
				}
				else
					System.out.println("\n\t - - - >>> Ahora mismo su cesta está vacía");
				break;
			case 4:
				compra.cancelarCompra();
				compra = null;
				finalizarCompra = true;
				MostrarMsg.despedida();
				break;
			}
		}
	}
	
	//revisar y refactorizar esto
	private static void quitarEntradaDelCarrito(Compra compra) {

		boolean esCorrecto;
		String 	entrada;
		int		opc = -2;

		do {
			esCorrecto = true;
			Menu.msgVolverAtras();
			System.out.println("\n\t- Indique el nº de entrada que desea eliminar del carrito: ");
			entrada = Main.teclado.nextLine().trim();
			if (!ValidarTipoEntrada.checkSoloNumeroEntero(entrada)) {
				MostrarMsg.errores(-1);//indicar un error adecuado, no este
				esCorrecto = false;
			}
			else {
				opc = Integer.parseInt(entrada);
				if ((opc < 1 || opc > compra.getEntradas().size()) && opc != -1) {
					MostrarMsg.errores(-1);//indicar un error adecuado, no este
					esCorrecto = false;
				}
				else if (opc == -1)
					esCorrecto = true;
				else
					compra.eliminarEntrada(opc - 1);
			}
		} while (!esCorrecto);
	}
	
	
	private static void pago(Compra compra, Cliente comprador) {
		
		Cliente cliente = null;
		
		boolean	pagoRealizado = false;
		if (Menu.siNo("¿Tiene ya una cuenta de usuario registrada en nuestro cine?") == 0) {
			cliente = new Cliente();
			cliente = ValidarLogin.iniciarSesion(); //ver que ocurre si te equivocas varias veces (posible bucle infinito)
													//si es una equivocacion, dar varios intentos o mantener en este menu de iniciar sesion
													// con opcion de volver atras
		}
		if (cliente == null) {
			if (Menu.siNo("¿Desea crearse una cuenta de usuario?") == 0) {
				cliente = ValidarLogin.crearCuenta();
				if (cliente != null) {
					if (Menu.siNo("¿Desea pagar (solo mediante ContactLess)?") == 0) 
						pagoRealizado = true;
				}
			}
		}
		else {
			if (Menu.siNo("¿Desea pagar (solo mediante ContactLess)?") == 0) 
				pagoRealizado = true;
		}	
		if (pagoRealizado) {
			comprador = cliente;
			System.out.println("\n\t~~~ Pago realizado correctamente :) ~~~\n");
			compra.guardarCompraEnBD();
			if (Menu.siNo("¿Desea obtener una factura de su compra?") == 0)
				compra.generarFactura();
		}
		else
			System.out.println("\n\t~~~ Compra cancelada, reiniciando sistema... :) ~~~\n");
	
	}
	
	/**
	 * Este método gestiona el flujo del programa cuando el usuario selecciona en el menú previo "Comprar entradas".
	 * Muestra los diferentes menús, recoge las selecciones realizadas por el usuario y si se cancela o se termina el proceso,
	 * regresa al menú anterior. 
	 * 
	 * @return false en caso de que haya terminado adecuadamente, y true si se ha cancelado el proceso de compra en algún punto.
	 */
	public static Entrada comprarEntradas(Compra compra) {
		
		Entrada		nuevaEntrada = new Entrada();
		
		Pelicula		peliculaElegida = new Pelicula();
		String		fechaElegida = null;
		Sesion		sesionElegida =  new Sesion();
		int			numPersonas = 0;
		
		int			faseDeCompra = 1;
		
		while (faseDeCompra > 0 && faseDeCompra < 5) {
			switch (faseDeCompra) {
				case 1:
					peliculaElegida = elegirPelicula();
					if (peliculaElegida == null)
						faseDeCompra--;
					else 
						faseDeCompra++;
					break;
				case 2:
					fechaElegida = elegirFecha(peliculaElegida);
					if (fechaElegida == null)
						faseDeCompra--;
					else
						faseDeCompra++;
					break;
				case 3:
					sesionElegida = elegirSesion(peliculaElegida, fechaElegida, compra);
					if (sesionElegida == null)
						faseDeCompra--;
					else {
						faseDeCompra++;
						nuevaEntrada.setSesionEntrada(sesionElegida);
					}
					break;
				case 4:
					numPersonas = elegirNumPersonas(sesionElegida, compra);
					if (numPersonas < 1) 
						faseDeCompra--;
					else {
						nuevaEntrada.setNumPersonas(numPersonas);
						//aqui hay que hacer mas cosas
						if (Menu.siNo("¿Confirma añadir esta entrada?\n\t\t...Al elegir 'No', se perderán los datos seleccionados...") == 1)
							nuevaEntrada = null;	
						faseDeCompra++;
					}
					break;
			}
		}
		return (nuevaEntrada);
	}
	
	//el proceso de ver cartelera, elegir y peli y guardar los datos de la seleccion en un objeto pelicula
	private static Pelicula elegirPelicula() {
		
		ArrayList<Integer> 	cartelera = new ArrayList<>();
		int					idPeliElegida = -1, indiceEnCartelera = -1;
		Pelicula 			peliculaElegida = null;

		cartelera = ConsultarBD.consultarCartelera();
		
		if (cartelera.size() > 0) {
			indiceEnCartelera = opcionCorrecta("\n\t·····> Introduzca el nº de la película: ", cartelera); //lo que introduce el usuario, ya validado
			//si no quiere volver atras:
			if (indiceEnCartelera != -1) {
				idPeliElegida = cartelera.get(indiceEnCartelera);
				peliculaElegida = ConsultarBD.consultarPeliculaElegida(idPeliElegida);
			}
		}
		else
			System.out.println("Error, no hay cartelera disponible ahora mismo, lo sentimos");//dar formato de msg de error
		
		return (peliculaElegida); //sera null si puso la opcion de volver atras
	}
	
	//el proceso de ver fechas posibles para la pelicula elegida, elegir una y guardar los datos de esa fecha en un string
	private static String elegirFecha(Pelicula peliculaElegida) {
		
		ArrayList<String>	fechas = new ArrayList<>();
		String				fechaElegida = null;
		int					seleccionIndice = 0;
		
		fechas = ConsultarBD.consultarFechas(peliculaElegida);
		seleccionIndice = opcionCorrecta("\n\t·····> Introduzca el nº de la fecha que le interesa: ", fechas);
		
		if (seleccionIndice != -1) 
			fechaElegida = fechas.get(seleccionIndice); 		
				
		return (fechaElegida); //sera null si desea volver atras
	}
	
	private static Sesion elegirSesion(Pelicula peliculaElegida, String fechaElegida, Compra compra) {
		
		ArrayList<Integer>	idSesiones = new ArrayList<>();
		int					indiceSesionElegida = 0, idSesionElegida = 0;
		Sesion				sesionElegida = null;
		
		idSesiones = ConsultarBD.consultarSesionesConAforoDisponible(peliculaElegida, fechaElegida, compra);
		if (!idSesiones.isEmpty()) {
			indiceSesionElegida = opcionCorrecta("\n\t·····> Introduzca el nº de la sesión que le interesa: ", idSesiones);
			
			if (indiceSesionElegida != -1) {
				idSesionElegida = idSesiones.get(indiceSesionElegida);
				sesionElegida = ConsultarBD.consultarSesionElegida(idSesionElegida);
				sesionElegida.setPelicula(peliculaElegida);
				sesionElegida.setSala(ConsultarBD.consultarSala(sesionElegida.getIdSesion()));
				sesionElegida.setAforoDisponible(ConsultarBD.consultarAforo(idSesionElegida, compra));
			}
		}
		return (sesionElegida);
	}
	
	
	//pide un nº de opcion del menu mostrado previamente, valida si es una opcion dentro de las posibles o si es la de volver
	//si es incorrecto, vuelve a pedir el dato
	//cuando es correcto, devuelve la opcion que ha elegido y que es correcta
	private static <TipoDeArrayList> int opcionCorrecta(String peticion, ArrayList<TipoDeArrayList> arrayObjetos) {
		String				entrada = null;
		int					seleccionIndice = 0;
		boolean				esCorrecto;
		
		do {
			esCorrecto = true;
			System.out.println(peticion);
			entrada = Main.teclado.nextLine().trim();
			
			if (ValidarTipoEntrada.checkSoloNumeroEntero(entrada)) {
				seleccionIndice = Integer.parseInt(entrada);
				if ((seleccionIndice < 1 || seleccionIndice > arrayObjetos.size()) && (seleccionIndice != -1)) {
					esCorrecto = false;
					System.out.println("Error, opcion incorrecta, vuelvalo a intentar");//dar formato de msg de error
				}
				else if (seleccionIndice == -1) 
					System.out.println("\n\t...Volviendo atrás..." + "\n".repeat(8));
				else // opcion correcta:
					seleccionIndice--;//se ajusta al indice real, que empezaria en 0 en vez de 1
			}
			else {
				System.out.println("Error, dato incorrecto");//dar formato de msg de error
				esCorrecto = false;
			}
			
		} while (!esCorrecto);
		
		return (seleccionIndice);
	}
	
	/**
	 * 
	 * @param sesionElegida
	 * @return
	 */
	private static int elegirNumPersonas(Sesion sesionElegida, Compra compra) {
		
		int		numPersonas = 0;
		boolean esCorrecto;
		String 	entrada = "";
		
		Menu.cabeceraMenu(4, sesionElegida.getPelicula().getNombrePeli(), sesionElegida.getFecSesion(), sesionElegida.getHoraInicio().substring(0, 5));
		
		do {
			esCorrecto = true;
			Menu.pedirNumPersonas(sesionElegida, compra);
			entrada = Main.teclado.nextLine();
			if (ValidarTipoEntrada.checkSoloNumeroEntero(entrada)) {
				numPersonas = Integer.parseInt(entrada);
				if (numPersonas < 1 && numPersonas != -1 ) {
					System.out.println("Error, debe ser mínimo una persona, por favor");
					esCorrecto = false;
				}
				else if (numPersonas > sesionElegida.getAforoDisponible()) {
					System.out.println("Error, ha seleccionado más personas que asientos disponibles tiene esta sesión...");
					esCorrecto = false;
				}
			}
			
		} while (!esCorrecto);
			
		return (numPersonas);
	}
	
}
