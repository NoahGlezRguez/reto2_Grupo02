package modelo;

import java.util.ArrayList;

import controlador.ConsultarBD;


public class Compra {

	private int					idCompra;
	private String				tipoCompra = "app";
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
		
		String cabecera, precioCompra, descuento, importeFinal;
		
		cabecera = """
				~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				~~~~~~~~~~~~~	Cesta de compra actual       ~~~~~~~~~~~~~
				~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				""";
		
		precioCompra = """
					~ Precio total de las entradas (I.V.A. incluido): %.2f€
				""".formatted(calcularPrecioDeCompra());
		
		descuento = """
					~ Descuento aplicado: %.2f%%
				""".formatted(calcularDescuento() * 100);
		
		importeFinal = """
				~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
					~ Precio final con descuento aplicado: %.2f€
				~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				""".formatted(calcularImporteFinal());
		
		
		System.out.println(cabecera);
		
		for (int i = 0; i < entradas.size(); i++) {
			System.out.println("""
					~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
					~~~ Nº de entrada:	%d
					""".formatted(i + 1));
			entradas.get(i).mostrarEntrada();
		}
		
		
		System.out.println(precioCompra + descuento + importeFinal);
	}

	private double calcularImporteFinal() {
		
		this.descuento = calcularDescuento();
		
		this.precioCompra = calcularPrecioDeCompra();
		
		double importeFinal = precioCompra;
		
		if (descuento > 0) {
			importeFinal = precioCompra - (precioCompra * descuento);
		}
		this.importeTotal = importeFinal;
		
		return (importeFinal);
	}
	
	private double calcularDescuento() {
		
		double				descuento = 0;
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
					}
				}
			}	
		}
		if (diversidadPelis >= 3)
			descuento = 0.3;
		else if (diversidadPelis == 2)
			descuento = 0.2;
		else
			descuento = 0;
		this.descuento = descuento;
		return (descuento);
	}
	
	private double calcularPrecioDeCompra() {
		double precioTotal = 0;
		
		if (!entradas.isEmpty()) {
			for (int i = 0; i < entradas.size(); i++) {
				precioTotal += calcularPrecioDeEntrada(entradas.get(i));
			}
		}
		
		this.precioCompra = precioTotal;
		
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

}
