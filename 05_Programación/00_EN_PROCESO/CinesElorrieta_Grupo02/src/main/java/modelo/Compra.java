package modelo;

import java.util.ArrayList;

public class Compra {

	private int					idCompra;
	private String				fechaCompra;
	private boolean				compraEnApp = true;
	private double				precioCompra;
	private double				descuento;
	private double				importeTotal;
	private ArrayList<Entrada>	entradas = new ArrayList<>();
	private Cliente				comprador = new Cliente();
	
	public Compra() {
		
	}
	
	public void agregarEntrada(Entrada nuevaEntrada) {
		entradas.add(nuevaEntrada);
	}
	
	public void eliminarEntrada(int idEntrada) {
		for (int i = 0; i < entradas.size(); i++) {
			if(entradas.get(i).getIdEntrada() == idEntrada) {
				entradas.remove(i);
			}
		}
	}
	
	public void cancelarCompra() {
		entradas.clear();
	}
	

	

	
	
	/*¿¿QUE METODOS HACEN FALTA AQUI??*/
	
	/*-----------------------GETTERS Y SETTERS--------------------------------------*/
	
	//revisar cuáles sobran para borrarlos
	
	

	
	public void mostrarCesta() {
		
		String	cabecera = """
				~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				~~~~~~~~~~~~~	Cesta de compra actual       ~~~~~~~~~~~~~
				~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				""";
		
		String	entradasCesta = "";
		
		String	precioCompra = """
					~ Precio total de las entradas (I.V.A. incluido): %.2f€
				""".formatted(calcularPrecioDeCompra());
		
		for (int i = 0; i < entradas.size(); i++) {
			entradasCesta += entradas.mostrarEntrada();
		}
		
		String	descuento = """
					~ Descuento aplicado: %d%%
				""".formatted(calcularDescuento());
		
		String importeFinal = """
				~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
					~ Precio final (descuento aplicado + I.V.A. incluido): %.2f€
				~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				""".formatted(calcularImporteFinal());
		
		System.out.println(cabecera + entradasCesta + precioCompra + descuento + importeFinal);
	}

	private double calcularImporteFinal() {
		
		return (calcularPrecioDeCompra() * calcularDescuento());
	}
	
	private double calcularDescuento() {
		
		double				descuento = 0;
		int					diversidadPelis = 0;
		ArrayList<String> 	titulos = new ArrayList<>();
		
		if (!entradas.isEmpty()) {
			for (int i = 0; i < entradas.size(); i++) {
				if (i == 0) {
					titulos.add(entradas.get(i).getSesionEntrada().getPelicula().nombrePeli);
					i++;
					diversidadPelis++;
				}
				if (!titulos.contains(entradas.get(i).getSesionEntrada().getPelicula().nombrePeli)) {
					diversidadPelis++;
				}
			}	
		}
		if (diversidadPelis >= 3)
			descuento = 3;
		else if (diversidadPelis == 2)
			descuento = 2;
		else
			descuento = 0;
		setDescuento(descuento);
		return (descuento);
	}
	
	private double calcularPrecioDeCompra() {
		double precioTotal = 0;
		
		if (!entradas.isEmpty()) {
			for (int i = 0; i < entradas.size(); i++) {
				precioTotal += calcularPrecioDeEntrada(entradas.get(i));
			}
		}
		setPrecioCompra(precioTotal);
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
	
	/*------------------------------------------------------------------------------*/
}
