package modelo;
import java.io.*;
import java.util.ArrayList;

import controlador.*;
import vista.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Esta clase genera y maneja un objeto para el proceso de compra de entradas del cine. Almacena las entradas, 
 * los datos del cliente, los importes o descuentos...
 */
public class Compra {

	private int					idCompra;
	private String				tipoCompra = "app";
	private double				precioCompra;
	private double				porcenDescuento;
	private double				descuento;
	private double				importeTotal;
	private ArrayList<Entrada>	entradas = new ArrayList<>();
	private Cliente				comprador = new Cliente();
	
	public Compra() {
		
	}
	
	/**
	 * Añade una entrada nueva al carrito
	 * @param nuevaEntrada - entrada nueva que se ha generado
	 */
	public void agregarEntrada(Entrada nuevaEntrada) {
		entradas.add(nuevaEntrada);
		MostrarMsg.operacionRealizada(0);
	}
	
	/**
	 * Elimina una entrada que existe dentro del carrito.
	 * @param indiceEntrada - indicador de cuál es la entrada que debe borrarse
	 */
	public void eliminarEntrada(int indiceEntrada) {
		
		if (indiceEntrada >= 0 && indiceEntrada < entradas.size()) {
			entradas.remove(indiceEntrada);
			MostrarMsg.operacionRealizada(1);
		}
		else
			MostrarMsg.errores(8);
	}
	
	/**
	 * Consulta el aforo que se ha ido ocupando en las entradas existentes dentro del carrito.
	 * @param idSesion - sesion para la que se consulta
	 * @return aforo ocupado de dicha sesion respecto al carrito
	 */
	public int conocerAforoCesta(int idSesion) {
		int aforoCesta = 0;

		for (int i = 0; i < entradas.size(); i++) {
			if (entradas.get(i).getSesionEntrada().getIdSesion() == idSesion)
				aforoCesta += entradas.get(i).getNumPersonas();
		}
		return (aforoCesta);
	}

	/**
	 * Calcula inmediatamente los valores por si hubiera posibles cambios,
	 * aplica los descuentos pertinentes tras analizar la diversidad de peliculas que hay
	 * en el carrito y finalmente guarda en la base de datos los valores de la compra, 
	 * posteriormente consulta cual es el id que le ha asigando esta, y con este valor, 
	 * se guardan las entradas con el id de la compra asociado en la base de datos.
	 */
	public void guardarCompraEnBD() {
		
		precioCompra = calcularPrecioDeCompra();
		porcenDescuento = calcularPorcenDescuento();
		descuento = precioCompra * porcenDescuento;
		importeTotal = precioCompra - descuento;
		
		InsertarDatosBD.insertarCompra(tipoCompra, descuento, importeTotal, comprador.getDni());
		idCompra = ObtenerDatosBD.consultarCompraRealizada();
		InsertarDatosBD.insertarEntradas(entradas, idCompra);
	}

	/**
	 * Este metodo calcula inmediatamente los valores economicos del importe total o el descuento,
	 * para mostrar el estado del carrito de forma fiable y actualizada.
	 * Ademas, muestra la cesta/carrito con formato.
	 */
	public void mostrarCesta() {
		
		String 	cabecera, valores;
		
		precioCompra = calcularPrecioDeCompra();
		porcenDescuento = calcularPorcenDescuento();
		descuento = precioCompra * porcenDescuento;
		importeTotal = precioCompra - descuento;
		
		cabecera = """
				~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				~~~~~~~~~~~~~	Cesta de compra actual       ~~~~~~~~~~~~~
				~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				""";
		
		valores = """				
					~ Precio de las entradas ·     ·     ·    %8.2f€
					
					~ Descuento aplicable por promoción  ·    %8.2f%%
					
				~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
					~ Coste final de su compra(con I.V.A. incluido): 	 
					
				        %-4.2f - %-4.2f  = ·     ·     ·     ·  %8.2f€ 
				~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				
				""".formatted(precioCompra, porcenDescuento * 100, precioCompra, descuento, importeTotal);
		
		
		System.out.println(cabecera);
		
		for (int i = 0; i < entradas.size(); i++) {
			System.out.println("""
					~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
					~~~ Nº de entrada:	%d
					""".formatted(i + 1));
			entradas.get(i).mostrarEntrada();
		}
		
		System.out.println(valores);
	}

	/**
	 * Este metodo calcula cual es el porcentaje de descuento en base a la diversidad de peliculas
	 * existente en el carrito de entradas acumulado.
	 * 
	 * @return - el porcentaje correspondiente a la diversidad existente en el carrito
	 */
	private double calcularPorcenDescuento() {
		
		double				porcentajeDescuento = 0;
		int					diversidadPelis = 0;
		ArrayList<String> 	titulos = new ArrayList<>();
		
		if (!entradas.isEmpty()) {
			for (int i = 0; i < entradas.size(); i++) {
				if (i == 0) {
					titulos.add(entradas.get(i).getSesionEntrada().getPelicula().nombrePeli);
					diversidadPelis++;
				}
				else {
					if ((!titulos.contains(entradas.get(i).getSesionEntrada().getPelicula().nombrePeli))) {
						diversidadPelis++;
						titulos.add(entradas.get(i).getSesionEntrada().getPelicula().nombrePeli);
					}
				}
			}	
		}
		if (diversidadPelis >= 3)
			porcentajeDescuento = 0.3;
		else if (diversidadPelis == 2)
			porcentajeDescuento = 0.2;
		else
			porcentajeDescuento = 0;

		return (porcentajeDescuento);
	}
	
	public double calcularPrecioDeCompra() {
		double precioTotal = 0;
		
		if (!entradas.isEmpty()) {
			for (int i = 0; i < entradas.size(); i++) {
				precioTotal += entradas.get(i).getImporte();
			}
		}
		
		return (precioTotal);
	}
					
	public int getIdCompra() {
		return idCompra;
	}
	
	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public ArrayList<Entrada> getEntradas() {
		return entradas;
	}
	
	
	/**
	 * Este metodo escribe con formato los datos de compra en formato factura en un fichero.
	 */
	public void generarFactura() {
		
		String ruta = "src/main/java/files/factura.txt";
		
		FileWriter fichero = null;
		BufferedWriter buffer = null;
		
		try {
			
			//se pone true para que no borre lo que había en el fichero
			fichero = new FileWriter(ruta, true);
			buffer = new BufferedWriter(fichero);
			buffer.newLine();
			buffer.write(factura());
			buffer.newLine();
			MostrarMsg.operacionRealizada(5);
			
		}catch(IOException e) {
			
			e.printStackTrace();
			
		}finally {
			try {
				if(buffer != null) {
					buffer.close();
				}
				if(fichero != null) {
					fichero.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}


	public void setComprador(Cliente comprador) {
		this.comprador = comprador;
	}
	
	/**
	 * Devuelve la fecha y hora actual en un array de dos valores tipo String con formato.
	 * 
	 * @return devuelve un array con fecha y hora
	 */
	public static String[] tiempoActual() {
		String[] fechaHoraArray = new String[2];
		LocalDateTime fechaHora = LocalDateTime.now();
		fechaHoraArray[0] = fechaHora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		fechaHoraArray[1] = fechaHora.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	    return fechaHoraArray;
	}
	
	
	/**
	 * Se encarga de generar la factura y almacenarla en un string que devuelve
	 * 
	 * @return la factura formateada con los valores de la compra
	 */
	private String factura() {
		String[] fechahora = tiempoActual();
		String formatoEuro = "€%.2f";

		String descu = String.format(formatoEuro, descuento);
		String importe = String.format(formatoEuro, precioCompra);
		String impTotal = String.format(formatoEuro, importeTotal);

		return """
				----------------------------------------------
				%-20s %25d
				
				%-20s %25s
				%-20s %25s
				%-20s %25s
				%-20s %25s
				%-20s %25s

				%s

				%-20s %24s€
				%-20s %24s€

				%-20s %24s€
				----------------------------------------------
				""".formatted("Compra nº:", idCompra,
						"Fecha:", fechahora[0], 
						"Hora:", fechahora[1], 
						"Plataforma:", tipoCompra, 
						"Cliente:", comprador.getNomCliente(), 
						"DNI:", comprador.getDni(),
						recibirEntradas(entradas), 
						"Descuento:", descu, 
						"Importe:", importe, 
						"Total:", impTotal);
	}
	
	/**
	 * Genera una cadena con toda la informacion de las entradas, y la guarda en un solo string.
	 * @param listaEntradas lista de objetos {@link Entrada} con los datos respectivos
	 * @return un String que contiene la informacion de todas las entradas 
	 */
	private static String recibirEntradas(ArrayList<Entrada> listaEntradas) {
		 StringBuilder resultado = new StringBuilder();
		
		for(int i = 0; i < listaEntradas.size(); i++) {
			resultado.append(listaEntradas.get(i).toString());
			
			if (i < listaEntradas.size()-1) {
				resultado.append("\n");
			}			
		}
		
		return resultado.toString();
	}

}
