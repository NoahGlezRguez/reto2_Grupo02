package modelo;

import java.io.*;
import java.util.ArrayList;

public class Compra {

	int					idCompra;
	String				fechaCompra;
	boolean				compraEnApp = true;
	double				precioCompra;
	double				descuento;
	double				importeTotal;
	ArrayList<Entrada>	entradas = new ArrayList<>();
	Cliente				comprador = new Cliente();
	
	public Compra() {
		
	}
	
	public int getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}
	public String getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(String fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	public boolean isCompraEnApp() {
		return compraEnApp;
	}
	public void setCompraEnApp(boolean compraEnApp) {
		this.compraEnApp = compraEnApp;
	}
	public double getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	public double getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
	}
	public ArrayList<Entrada> getEntradas() {
		return entradas;
	}
	public void setEntradas(ArrayList<Entrada> entradas) {
		this.entradas = entradas;
	}
	public Cliente getComprador() {
		return comprador;
	}
	public void setComprador(Cliente comprador) {
		this.comprador = comprador;
	}
	
	/**
	 * escribe en un fichero y funciona, se utilizará para la factura
	 * en el reto, tendrá que recibir un objeto compra como parámetro,
	 * consultar con la bd que entradas pertenecen a esa compra, 
	 * obtener los datos y dar formáto a la factura.
	 */
	private void generarFactura() {
		
		String ruta = "";
		
		
		// esto luego se pasará al paquete vista
		String linea = "----------------------------"+
						  "Compra nº"               +String.valueOf(idCompra)+
						  "Fecha:"  				+fechaCompra+
						  "Plataforma:"			  	+compraEnApp+ // plataforma, convertir a String
						  
						  //Entradas? aquí usaré el Entrada.toString
						  //junto con un for 
						  
						  "Descuento"				+String.valueOf(descuento)+
						  "Importe"					+String.valueOf(precioCompra)+
						  "Total"					+String.valueOf(importeTotal)+
						  ""
				;
		
		String hola = "";
		
		for(int i = 0; i<entradas.size(); i++) {
			
			
			hola +=  entradas.get(i).toString();
		};
		// esto luego se pasará al paquete vista
		
		
		
		FileWriter fichero = null;
		
		BufferedWriter buffer= null;
		
		try {
			
			//se pone true para que no borre lo que había en el fichero
			fichero = new FileWriter(ruta, true);
			buffer = new BufferedWriter(fichero);
			buffer.newLine();
			buffer.write("holaaaaa");
			buffer.newLine();
			buffer.write("factura");
			
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
