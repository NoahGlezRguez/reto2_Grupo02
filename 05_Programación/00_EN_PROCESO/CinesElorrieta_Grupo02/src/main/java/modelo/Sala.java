package modelo;

public class Sala {

	int			idCinePadre = 1;
	int			numSala;
	int			aforoSala;
	
	Sala(int idCinePadre, int numSala, int aforoSala){
		
		this.idCinePadre = idCinePadre;
		this.numSala = numSala;
		this.aforoSala = aforoSala;
	}

	public Sala() {
		
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
	
}
