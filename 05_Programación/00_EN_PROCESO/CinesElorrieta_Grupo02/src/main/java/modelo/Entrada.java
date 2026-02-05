package modelo;

/**
 * Esta clase genera y gestiona un objeto con la informacion relevante de una entrada de cine creada en un proceso de compra.
 */
public class Entrada {

	int		numPersonas;
	double	importe;
	Sesion	sesionEntrada = new Sesion();

	
	@Override // toString para utilizado al imprimir la factura
	public String toString() {
		return """
				%-20s %25s
				%-20s %25s
				%-20s %24s€
				%-20s %25s
				%-20s %25s
				""".formatted("Película:", sesionEntrada.getPelicula().getNombrePeli(), 
						"Cant. Personas:", String.valueOf(numPersonas),
						"Importe:", String.format("%.2f", importe), 
						"Fecha de la sesión:", sesionEntrada.getFecSesion(),
						"Hora inicio:", sesionEntrada.getHoraInicio()

		);

	}

	public Entrada() {
		
	}
	
	/**
	 * Este metodo imprime en la terminal una entrada existente en el carrito con todos sus datos.
	 */
	public void mostrarEntrada() {
		
		String	tituloPeli, fecha, hora;
		int		sala;
		double	precio;
		
		tituloPeli = sesionEntrada.getPelicula().getNombrePeli();
		fecha = sesionEntrada.getFecSesion();
		hora = sesionEntrada.getHoraInicio();
		sala = sesionEntrada.getSala().getNumSala();
		precio = sesionEntrada.getPrecio();
		importe = numPersonas * precio;
		
		String entrada = """
					~~~ Película: 	%s
					~~~ Día:	%s
					~~~ Hora: 	%sh
					~~~ Sala: 	%d
					
					~~~ Nº de personas ·     ·     ·     ·    %d
					~~~ Precio de la sesión  ·     ·     ·    %.2f€
					
					-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
					
					~~~ Importe total de entrada   ·     ·    %.2f€
					
				~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				""".formatted(tituloPeli, fecha, hora.substring(0, 5), sala, numPersonas, precio, importe);
		
		System.out.println(entrada);
	}
	

	public int getNumPersonas() {
		return numPersonas;
	}

	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}

	public Sesion getSesionEntrada() {
		return sesionEntrada;
	}

	public void setSesionEntrada(Sesion sesionEntrada) {
		this.sesionEntrada = sesionEntrada;
	}

	public void setImporte() {
		importe = sesionEntrada.getPrecio() * numPersonas;
	}
	
	public double getImporte() {
		return (importe);
	}
}
