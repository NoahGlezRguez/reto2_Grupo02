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
		Cliente		comprador = null;
		Entrada		nuevaEntrada = null;
		
		String		operaciones[] = {"Agregar entrada al carrito", "Eliminar entrada del carrito", "Consultar carrito", "Terminar compra y pagar", "Cancelar compra y salir"};
		boolean		finalizarCompra = false;
		int			opc = 0;
		
		while (!finalizarCompra) {
			
			opc = Menu.opciones("Operaciones disponibles", operaciones, "Seleccione la operación que desea realizar");
				
			switch (opc) {
					
			case 0:
				nuevaEntrada = comprarEntradas(compra);
				if (nuevaEntrada != null) {
					nuevaEntrada.setImporte();
					compra.agregarEntrada(nuevaEntrada);
				}
				break;
				
			case 1:
				if (compra.getEntradas().size() > 0) 
					quitarEntradaDelCarrito(compra);
				else 
					MostrarMsg.errores(12);
				break;
				
			case 2:	
				if (compra.getEntradas().size() > 0) {
					compra.mostrarCesta();
				}
				else
					MostrarMsg.errores(12);
				break;
				
			case 3:
				if (compra.getEntradas().size() > 0) {
					comprador = finDeCompra(compra);
					if (comprador != null) {
						compra.setComprador(comprador);
						finalizarCompra = true;
					}
					else
						finalizarCompra = true;
				}
				else
					MostrarMsg.errores(12);
				break;
			case 4:
				if (comprador != null)
					MostrarMsg.despedida(comprador.getNomCliente());
				else
					MostrarMsg.despedida();
				finalizarCompra = true;
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
			if (!ValidarTipoEntrada.checkNum(entrada)) {
				MostrarMsg.errores(3);
				esCorrecto = false;
			}
			else {
				opc = Integer.parseInt(entrada);
				if ((opc < 1 || opc > compra.getEntradas().size()) && opc != -1) {
					MostrarMsg.errores(8);
					esCorrecto = false;
				}
				else if (opc == -1)
					esCorrecto = true;
				else
					compra.eliminarEntrada(opc - 1);
			}
		} while (!esCorrecto);
	}
	
	
	private static Cliente finDeCompra(Compra compra) {
		
		Cliente cliente = null;
		int		intentos = 3;
		
		if (Menu.siNo("¿Tiene ya una cuenta de usuario registrada en nuestro cine?") == 0) {
			do {
				System.out.println("\t- Intentos de inicio de sesión restantes = %d".formatted(intentos));
				cliente = ValidarLogin.iniciarSesion();
				if (cliente == null)
					intentos--;
				else
					intentos = 0;
			} while (intentos > 0);
			
		}
		if (cliente == null) {			
			if (Menu.siNo("¿Desea crearse una cuenta de usuario?") == 0) 
				cliente = ValidarLogin.crearCuenta();
		}
		
		if (cliente != null) {
			if (Menu.siNo("¿Desea pagar (solo con Bitcoins)?") == 0) {
				System.out.println("\n\t~~~ Pago realizado correctamente :) ~~~\n");
				MostrarMsg.operacionRealizada(2);
				compra.setComprador(cliente);
				compra.guardarCompraEnBD();
				if (Menu.siNo("¿Desea obtener una factura de su compra?") == 0)
					compra.generarFactura();
				MostrarMsg.despedida(cliente.getNomCliente());
			}
		}	
		else 
			System.out.println("\n\t~~~ Compra cancelada, reiniciando sistema... :) ~~~\n");

		return (cliente);
	}
	
	/**
	 * Este método gestiona el flujo del programa cuando el usuario selecciona en el menú previo "Comprar entradas".
	 * Muestra los diferentes menús, recoge las selecciones realizadas por el usuario y si se cancela o se termina el proceso,
	 * regresa al menú anterior. 
	 * 
	 * @return false en caso de que haya terminado adecuadamente, y true si se ha cancelado el proceso de compra en algún punto.
	 */
	private static Entrada comprarEntradas(Compra compra) {
		
		Entrada		nuevaEntrada = null;
		
		Pelicula		peliculaElegida = null;
		String		fechaElegida = null;
		Sesion		sesionElegida =  null;
		int			numPersonas = 0;
		
		int			faseDeCompra = 1;
		
		while (faseDeCompra > 0 && faseDeCompra < 5) {
			switch (faseDeCompra) {
				case 1:
					peliculaElegida = new Pelicula();
					peliculaElegida = elegirPelicula();
					if (peliculaElegida == null) 
						faseDeCompra--;
					else 
						faseDeCompra++;
					break;
				case 2:
					fechaElegida = elegirFecha(peliculaElegida);
					if (fechaElegida == null) {
						faseDeCompra--;
						sesionElegida = null;
						nuevaEntrada = null;
					}
					else
						faseDeCompra++;
					break;
				case 3:
					sesionElegida =  new Sesion();
					sesionElegida = elegirSesion(peliculaElegida, fechaElegida, compra);
					if (sesionElegida == null)
						faseDeCompra--;
					else {
						nuevaEntrada = new Entrada();
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

		cartelera = ConsultarDatosBD.consultarCartelera();
		
		if (cartelera.size() > 0) {
			indiceEnCartelera = ValidarTipoEntrada.opcionCorrecta("\n\t·····> Introduzca el nº de la película: ", cartelera); //lo que introduce el usuario, ya validado
			//si no quiere volver atras:
			if (indiceEnCartelera != -1) {
				idPeliElegida = cartelera.get(indiceEnCartelera);
				peliculaElegida = ObtenerDatosBD.consultarPeliculaElegida(idPeliElegida);
			}
		}
		else
			MostrarMsg.errores(13);
		
		return (peliculaElegida); //sera null si puso la opcion de volver atras
	}
	
	//el proceso de ver fechas posibles para la pelicula elegida, elegir una y guardar los datos de esa fecha en un string
	private static String elegirFecha(Pelicula peliculaElegida) {
		
		ArrayList<String>	fechas = new ArrayList<>();
		String				fechaElegida = null;
		int					seleccionIndice = 0;
		
		fechas = ConsultarDatosBD.consultarFechas(peliculaElegida);
		seleccionIndice = ValidarTipoEntrada.opcionCorrecta("\n\t·····> Introduzca el nº de la fecha que le interesa: ", fechas);
		
		if (seleccionIndice != -1) 
			fechaElegida = fechas.get(seleccionIndice); 		
				
		return (fechaElegida); //sera null si desea volver atras
	}
	
	private static Sesion elegirSesion(Pelicula peliculaElegida, String fechaElegida, Compra compra) {
		
		ArrayList<Integer>	idSesiones = new ArrayList<>();
		int					indiceSesionElegida = 0, idSesionElegida = 0;
		Sesion				sesionElegida = null;
		
		idSesiones = ConsultarDatosBD.consultarSesionesConAforoDisponible(peliculaElegida, fechaElegida, compra);
		if (!idSesiones.isEmpty()) {
			indiceSesionElegida = ValidarTipoEntrada.opcionCorrecta("\n\t·····> Introduzca el nº de la sesión que le interesa: ", idSesiones);
			
			if (indiceSesionElegida != -1) {
				idSesionElegida = idSesiones.get(indiceSesionElegida);
				sesionElegida = ObtenerDatosBD.consultarSesionElegida(idSesionElegida);
				sesionElegida.setPelicula(peliculaElegida);
				sesionElegida.setSala(ObtenerDatosBD.consultarSala(sesionElegida.getIdSesion()));
				sesionElegida.setAforoDisponible(ConsultarDatosBD.consultarAforo(idSesionElegida, compra));
			}
		}
		return (sesionElegida);
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
			entrada = Main.teclado.nextLine().trim();
			
			if (ValidarTipoEntrada.checkNum(entrada)) {
				numPersonas = Integer.parseInt(entrada);
				if (numPersonas < 1 && numPersonas != -1 ) {
					MostrarMsg.errores(14);
					esCorrecto = false;
				}
				else if (numPersonas > sesionElegida.getAforoDisponible()) {
					MostrarMsg.errores(15);
					esCorrecto = false;
				}
			}
			else {
				esCorrecto = false;
				MostrarMsg.errores(3);
			}
		} while (!esCorrecto);
			
		return (numPersonas);
	}
	
}
