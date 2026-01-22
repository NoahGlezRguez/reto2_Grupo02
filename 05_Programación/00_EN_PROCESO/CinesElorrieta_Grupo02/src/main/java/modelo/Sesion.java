package modelo;

public class Sesion {

	int			idSesion;
	String		fecSesion;
	String		horaInicio;
	String		horaFin;
	double		precio;	
	int			aforoDisponible;
	int			salaSesion;
	int			peliculaSesion;
	Sala 		sala;
	Pelicula 	pelicula;
	
	
	
	
	/*¿¿QUE METODOS HACEN FALTA AQUI??*/
	
	/*-----------------------GETTERS Y SETTERS--------------------------------------*/
	
	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public Sesion() {
		
	}
	
	public Sesion(int idSesion, String fecSesion, String horaInicio, String horaFin, double precio, int aforoDisponible,
			int salaSesion, int peliculaSesion) {
		this.idSesion = idSesion;
		this.fecSesion = fecSesion;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;	//creo que sobra este atributo
		this.precio = precio;
		this.aforoDisponible = aforoDisponible;
		this.salaSesion = salaSesion;
		this.peliculaSesion = peliculaSesion;
	}

	public int getIdSesion() {
		return idSesion;
	}	
	
	public void setIdSesion(int idSesion) {
		this.idSesion = idSesion;
	}
	public String getFecSesion() {
		return fecSesion;
	}
	public void setFecSesion(String fecSesion) {
		this.fecSesion = fecSesion;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getAforoDisponible() {
		return aforoDisponible;
	}
	public void setAforoDisponible(int aforoDisponible) {
		this.aforoDisponible = aforoDisponible;
	}
	public int getSalaSesion() {
		return salaSesion;
	}
	public void setSalaSesion(int salaSesion) {
		this.salaSesion = salaSesion;
	}
	public int getPeliculaSesion() {
		return peliculaSesion;
	}
	public void setPeliculaSesion(int peliculaSesion) {
		this.peliculaSesion = peliculaSesion;
	}
	
	/*------------------------------------------------------------------------------*/
}
