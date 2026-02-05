package modelo;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CompraTest {
	private static Pelicula pelicula1 = null;
	private static Compra nuevaCompra = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		double preciopeli1 = 7.8;
		int cantidadPersonas1 = 37;

		double preciopeli2 = 13.5;
		int cantidadPersonas2 = 3;

		pelicula1 = new Pelicula();
		pelicula1.setNombrePeli("El llanto maldito");

		Pelicula pelicula2 = new Pelicula();

		Sala sala1 = new Sala();
		Sala sala2 = new Sala();

		Sesion sesion1 = new Sesion();
		Sesion sesion2 = new Sesion();

		sesion1.setPrecio(preciopeli1);
		sesion1.setSala(sala1);
		sesion1.setPelicula(pelicula1);

		sesion2.setPrecio(preciopeli2);
		sesion2.setSala(sala2);
		sesion2.setPelicula(pelicula2);

		Entrada entrada1 = new Entrada();
		Entrada entrada2 = new Entrada();

		entrada1.setNumPersonas(cantidadPersonas1);
		entrada1.setSesionEntrada(sesion1);

		entrada2.setNumPersonas(cantidadPersonas2);
		entrada2.setSesionEntrada(sesion2);

		entrada1.setImporte();
		entrada2.setImporte();

		nuevaCompra = new Compra();
		nuevaCompra.agregarEntrada(entrada1);
		nuevaCompra.agregarEntrada(entrada2);

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		pelicula1 = null;
		nuevaCompra = null;
	}

	@Test
	public void testComprobarNombrePeliculaElegida() {
		String nombrePeliculaEsperado = "El llanto maldito";
		String nombrePelicula = pelicula1.nombrePeli;
		assertEquals(nombrePeliculaEsperado, nombrePelicula);
	}

	@Test
	public void testPrecioCompra() {
		double resultadoEsperado = (7.8 * 37) + (13.5 * 3);
		double resultado = nuevaCompra.calcularPrecioDeCompra();
		assertTrue(resultadoEsperado == resultado);
	}

}
