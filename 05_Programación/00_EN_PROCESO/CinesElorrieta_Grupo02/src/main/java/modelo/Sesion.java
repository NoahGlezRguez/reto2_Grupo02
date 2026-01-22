package modelo;

public class Sesion {

	int			idSesion;
	String		fecSesion;
	String		horaInicio;
	String		horaFin;
	double		precio;	
	int			aforoDisponible;
	Sala			salaSesion;
	Pelicula		peliculaSesion;

	
	
	
	
	/*¿¿QUE METODOS HACEN FALTA AQUI??*/
	
	/*-----------------------GETTERS Y SETTERS--------------------------------------*/
	
	public Sesion(int idSesion, String fecSesion, String horaInicio, String horaFin, double precio, int aforoDisponible,
			Sala salaSesion, Pelicula peliculaSesion) {
		this.idSesion = idSesion;
		this.fecSesion = fecSesion;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
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
	public Sala getSalaSesion() {
		return salaSesion;
	}
	public void setSalaSesion(Sala salaSesion) {
		this.salaSesion = salaSesion;
	}
	public Pelicula getPeliculaSesion() {
		return peliculaSesion;
	}
	public void setPeliculaSesion(Pelicula peliculaSesion) {
		this.peliculaSesion = peliculaSesion;
	}
	
	/*------------------------------------------------------------------------------*/
}
