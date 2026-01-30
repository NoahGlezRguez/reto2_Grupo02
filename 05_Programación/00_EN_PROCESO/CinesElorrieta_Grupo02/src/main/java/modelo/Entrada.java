package modelo;

public class Entrada {

	int		numPersonas;
	double	importe;
	Sesion	sesionEntrada = new Sesion();

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
					
					~~~ Nº de personas______________________%d
					~~~ Precio de la sesión_________________%.2f€
					
					~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
					~~~ Importe total de entrada____________%.2f€
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
