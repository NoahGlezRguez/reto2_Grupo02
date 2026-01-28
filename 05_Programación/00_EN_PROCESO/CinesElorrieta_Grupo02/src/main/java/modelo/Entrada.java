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

	public Entrada() {
		
	}
	
	public void mostrarEntrada() {
		String entrada = """
				~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
					~~~ CIE (Código de identificación de entrada):
						- %d
					~~~ Película: %s
					~~~ Día: %s
					~~~ Hora: %s
					~~~ Sala: %s
					
					~~~ Nº de personas______________________%d
					~~~ Precio de la sesión_________________%.2f€
					~~~ Importe total de entrada____________%.2f€
				~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				""".formatted();
		System.out.println(entrada);
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
