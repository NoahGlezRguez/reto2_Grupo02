
package modelo;

public class Pelicula {

	int		idPeli;
	String	nombrePeli;
	int		duracion;
	String	genero;
	
	public Pelicula() {
		
	}
	
	public int getIdPeli() {
		return idPeli;
	}

	public void setIdPeli(int idPeli) {
		this.idPeli = idPeli;
	}

	public String getNombrePeli() {
		return nombrePeli;
	}

	public void setNombrePeli(String nombrePeli) {
		this.nombrePeli = nombrePeli;
	}


	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

}
