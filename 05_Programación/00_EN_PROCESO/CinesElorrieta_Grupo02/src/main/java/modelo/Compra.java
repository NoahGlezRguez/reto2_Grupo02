package modelo;
import vista.*;
import java.io.*;
import java.util.ArrayList;

import controlador.ConsultarBD;


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
	}
	
	public void eliminarEntrada(int indiceEntrada) {
		for (int i = 0; i < entradas.size(); i++) {
			if(i == indiceEntrada) {
				entradas.remove(i);
				System.out.println("\n\t- Entrada eliminada del carrito satisfactoriamente.");//refactorizar esta linea
			}
		}
	}
	
	public void cancelarCompra() {
		entradas.clear();
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
		//setIDcompra = ConsultarBD.consultarCompraRealizada();
		//ConsultarBD.insertarEntradasEnBD(entradas);
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
					~ Precio de las entradas ·     ·     ·    %.2f€
					
					~ Descuento aplicable por promoción  ·    %.2f%%
					
				~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
					~ Coste final de su compra(con I.V.A. incluido): 	 
					
				        %.2f - %.2f  = ·     ·     ·     ·  %.2f€ 
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
	 * escribe en un fichero y funciona, se utilizará para la factura
	 * en el reto, tendrá que recibir un objeto compra como parámetro,
	 * consultar con la bd que entradas pertenecen a esa compra, 
	 * obtener los datos y dar formáto a la factura.
	 */
	private void generarFactura() {
		
		String ruta = "";
		
		String mensaje = MostrarMsg.factura(String.valueOf(idCompra),"fechaCompra", tipoCompra, 
				comprador.getNomCliente(), comprador.getDni(), 
				String.valueOf(descuento), String.valueOf(precioCompra), 
				String.valueOf(importeTotal), entradas);
		
		FileWriter fichero = null;
		BufferedWriter buffer= null;
		
		try {
			
			//se pone true para que no borre lo que había en el fichero
			fichero = new FileWriter(ruta, true);
			buffer = new BufferedWriter(fichero);
			buffer.newLine();
			buffer.write(mensaje);
			buffer.newLine();
			
			
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

	
	
	/*¿¿QUE METODOS HACEN FALTA AQUI??*/
	
	/*-----------------------GETTERS Y SETTERS--------------------------------------*/
	
	
	
	/*------------------------------------------------------------------------------*/
}
