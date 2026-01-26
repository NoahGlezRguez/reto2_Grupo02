
package modelo;

public class Pelicula {

	int		idPeli;
	String	nombrePeli;
	int		duracion;
	String	genero;
	
	public Pelicula() {
		
	}
	
	public Pelicula(int idPeli, String nombrePeli, int duracion, String genero) {
		this.idPeli = idPeli;
		this.nombrePeli = nombrePeli;
		this.duracion = duracion;
		this.genero = genero;
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


	public int getDuracion() {
		return duracion;
	}


	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	
	/*¿¿QUE METODOS HACEN FALTA AQUI??*/
	
	/*-----------------------GETTERS Y SETTERS--------------------------------------*/
	
	
	
	/*------------------------------------------------------------------------------*/
}
