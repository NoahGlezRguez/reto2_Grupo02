package controlador;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidarTipoEntradaTest {

	@Test
	public void testCheckSoloLetrasTrue() {
		boolean result = ValidarTipoEntrada.checkSoloLetras("nombreUSUARIO");
		assertTrue("Nombreusuario debe ser true", result);
	}
	
	@Test
	public void testCheckSoloLetrasFalse() {
		boolean result = ValidarTipoEntrada.checkSoloLetras("1");
		assertFalse("1 debe ser False porque es numero", result);
	}

	@Test
	public void testCheckSoloAlfanumerico() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckSoloNumeroDecimal() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckSoloNumeroEntero() {
		fail("Not yet implemented");
	}

}
