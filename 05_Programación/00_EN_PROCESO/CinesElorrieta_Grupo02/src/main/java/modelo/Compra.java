package modelo;
import java.io.*;
import java.util.ArrayList;

import controlador.ConsultarBD;
import vista.MostrarMsg;


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
	
	public void agregarEntrada(Entrada nuevaEntrada) {
		entradas.add(nuevaEntrada);
		MostrarMsg.operacionRealizada(0);
	}
	
	public void eliminarEntrada(int indiceEntrada) {
		
		
		
		for (int i = 0; i < entradas.size(); i++) {
			if(i == indiceEntrada) {
				entradas.remove(i);
				MostrarMsg.operacionRealizada(1);
			}
		}
	}
	
	public int conocerAforoCesta(int idSesion) {
		int aforoCesta = 0;

		for (int i = 0; i < entradas.size(); i++) {
			if (entradas.get(i).getSesionEntrada().getIdSesion() == idSesion)
				aforoCesta += entradas.get(i).getNumPersonas();
		}
		return (aforoCesta);
	}

	public void guardarCompraEnBD() {
		ConsultarBD.insertarCompraEnBD(tipoCompra, descuento, importeTotal, comprador.getDni());
		idCompra = ConsultarBD.consultarCompraRealizada();
		ConsultarBD.insertarEntradasEnBD(entradas, idCompra);
	}

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
	
	private double calcularPrecioDeCompra() {
		double precioTotal = 0;
		
		if (!entradas.isEmpty()) {
			for (int i = 0; i < entradas.size(); i++) {
				precioTotal += calcularPrecioDeEntrada(entradas.get(i));
			}
		}
		
		return (precioTotal);
	}
	
	private double calcularPrecioDeEntrada(Entrada entrada) {
		
		double precioSesion;
		double precioEntrada = 0;
		
		if (entrada != null) {
			precioSesion = entrada.getSesionEntrada().getPrecio();
			precioEntrada = precioSesion * entrada.getNumPersonas();
		}
		
		return (precioEntrada);
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
	private static String factura(String a, String b, String c, String d, String e, String f, String g, String h, ArrayList<Entrada> entrada) {
		
		String hola = "";
		
		for(int i = 0; i<entrada.size(); i++) {
			hola +=  entrada.get(i).toString();
		};
		
		String formato = 
				"""
				------------------------------------
				Compra nº:			%15s
				Fecha:				%15s
				Plataforma:			%15s
				Cliente:			%15s
				DNI:				%15s
				
				%s
				
				
				Descuento:			%15s
				Importe:			%15s
				
				
				Total:				%15s
				-------------------------------------
				""".formatted(a, b, c, d, e, hola, f, g, h) ;
		
		return formato;
	}
	
	
	/**
	 * escribe en un fichero y funciona, se utilizará para la factura
	 * en el reto, tendrá que recibir un objeto compra como parámetro,
	 * consultar con la bd que entradas pertenecen a esa compra, 
	 * obtener los datos y dar formáto a la factura.
	 */
	public void generarFactura() {
		
		String ruta = "src/main/java/files/facturas.txt";

		String mensaje = factura(String.valueOf(idCompra),"fechaCompra", tipoCompra, 
				comprador.getNomCliente(), comprador.getDni(),
				String.valueOf(descuento), String.valueOf(precioCompra), 
				String.valueOf(importeTotal), entradas);
		
		FileWriter fichero = null;
		BufferedWriter buffer = null;
		
		try {
			
			//se pone true para que no borre lo que había en el fichero
			fichero = new FileWriter(ruta, true);
			buffer = new BufferedWriter(fichero);
			buffer.newLine();
			buffer.write(mensaje);
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
}
