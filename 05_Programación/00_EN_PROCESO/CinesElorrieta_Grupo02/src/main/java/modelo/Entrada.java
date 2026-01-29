package modelo;

public class Entrada {

	int		numPersonas;
	double	importe;
	Sesion	sesionEntrada = new Sesion();

	public Entrada(int numPersonas, Sesion sesionEntrada) {
		this.numPersonas = numPersonas;
		//this.importe = importe;//calcular con un metodo
		this.sesionEntrada = sesionEntrada;
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
					~~~ Hora: 	%s
					~~~ Sala: 	%d
					
					~~~ Nº de personas______________________%d
					~~~ Precio de la sesión_________________%.2f€
					
					~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
					~~~ Importe total de entrada____________%.2f€
				~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				""".formatted(tituloPeli, fecha, hora, sala, numPersonas, precio, importe);
		System.out.println(entrada);
	}
	
	
	/*¿¿QUE METODOS HACEN FALTA AQUI??*/
	
	/*-----------------------GETTERS Y SETTERS--------------------------------------*/
	

	//para importe no hay seter, ya que se calcula
	
	public int getNumPersonas() {
		return numPersonas;
	}

	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}

	public double getImporte() {
		return importe;
	}

	public Sesion getSesionEntrada() {
		return sesionEntrada;
	}

	public void setSesionEntrada(Sesion sesionEntrada) {
		this.sesionEntrada = sesionEntrada;
	}
		
	
	/*------------------------------------------------------------------------------*/
}
