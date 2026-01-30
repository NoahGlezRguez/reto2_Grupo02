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
	Sala 		sala = new Sala();
	Pelicula 	pelicula = new Pelicula();
	
	public Sesion() {
		
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

	public void setSalaSesion(int salaSesion) {
		this.salaSesion = salaSesion;
	}
	public int getPeliculaSesion() {
		return peliculaSesion;
	}
	public void setPeliculaSesion(int peliculaSesion) {
		this.peliculaSesion = peliculaSesion;
	}
	
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
	
}
