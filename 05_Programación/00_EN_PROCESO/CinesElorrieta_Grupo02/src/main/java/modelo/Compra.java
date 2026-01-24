package modelo;

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
	
	
	/*¿¿QUE METODOS HACEN FALTA AQUI??*/
	
	/*-----------------------GETTERS Y SETTERS--------------------------------------*/
	
	
	
	/*------------------------------------------------------------------------------*/
}
