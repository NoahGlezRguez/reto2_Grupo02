package modelo;

public class Sala {

	int			idCinePadre;
	int			numSala;
	int			aforoSala;
	
	Sala(int idCinePadre, int numSala, int aforoSala){
		
		this.idCinePadre = idCinePadre;
		this.numSala = numSala;
		this.aforoSala = aforoSala;
	}


	
	/*¿¿QUE METODOS HACEN FALTA AQUI??*/
	
	/*-----------------------GETTERS Y SETTERS--------------------------------------*/
	
	public int getIdCinePadre() {
		return idCinePadre;
	}

	public void setIdCinePadre(int idCinePadre) {
		this.idCinePadre = idCinePadre;
	}

	public int getNumSala() {
		return numSala;
	}

	public void setNumSala(int numSala) {
		this.numSala = numSala;
	}

	public int getAforoSala() {
		return aforoSala;
	}

	public void setAforoSala(int aforoSala) {
		this.aforoSala = aforoSala;
	}	
	
	/*------------------------------------------------------------------------------*/
}
