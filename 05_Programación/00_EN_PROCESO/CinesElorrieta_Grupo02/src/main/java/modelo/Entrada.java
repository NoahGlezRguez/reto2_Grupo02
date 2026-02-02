package modelo;

public class Entrada {

	int		numPersonas;
	double	importe;
	Sesion	sesionEntrada = new Sesion();

	
	@Override //toString para utilizado al imprimir la factura 
	public String toString() {
		return 
				"""
				Pelicula:           %15s
				Cant. Personas:     %15s
				Importe:            %15s
				Fecha:              %15s
				Hora inicio:        %15s
				""".formatted(
						sesionEntrada.getPelicula().getNombrePeli(),
						String.valueOf(numPersonas),
						String.valueOf(importe),
						sesionEntrada.getFecSesion(),
						sesionEntrada.getHoraInicio()
						
						);
		
	}

	public Entrada() {
		
	}
	
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

}
