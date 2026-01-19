package modelo;

public class Cine {

	String	nomCine;
	int		idCine;
	
	Cine (String nomCine, int idCine){
		this.nomCine = nomCine;
		this.idCine = idCine;
	}

	
	/*¿¿QUE METODOS HACEN FALTA AQUI??*/
	
	/*-----------------------GETTERS Y SETTERS--------------------------------------*/
	public String getNomCine() {
		return nomCine;
	}

	public void setNomCine(String nomCine) {
		this.nomCine = nomCine;
	}

	public int getIdCine() {
		return idCine;
	}

	public void setIdCine(int idCine) {
		this.idCine = idCine;
	}
	
	/*------------------------------------------------------------------------------*/
}
