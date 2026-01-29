package modelo;

public class Entrada {

	int		idEntrada;
	int		numPersonas;
	double	importe;
	Sesion	sesionEntrada = new Sesion();

	public Entrada(int idEntrada, int numPersonas, Sesion sesionEntrada) {
		this.idEntrada = idEntrada;
		this.numPersonas = numPersonas;
		//this.importe = importe;//calcular con un metodo
		this.sesionEntrada = sesionEntrada;
	}

	@Override //toString para utilizado al imprimir la factura 
	public String toString() {
		return "Entrada nº:\t" + idEntrada 
				+"\nCant. Personas:\t" + numPersonas 
				+"\nimporte:\t" + importe
				+"\nPelicula:\t"+ sesionEntrada.getPeliculaSesion() 
				+"\nHora inicio:\t" + sesionEntrada.getHoraInicio() 
				+"\nHora fin:\t"+ sesionEntrada.getHoraFin();
	}

	public Entrada() {
		
	}
	
	/*¿¿QUE METODOS HACEN FALTA AQUI??*/
	
	/*-----------------------GETTERS Y SETTERS--------------------------------------*/
	

	//para importe no hay seter, ya que se calcula
	
	public int getIdEntrada() {
		return idEntrada;
	}

	public void setIdEntrada(int idEntrada) {
		this.idEntrada = idEntrada;
	}

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
