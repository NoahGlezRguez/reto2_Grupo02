package controlador;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidarTipoEntradaTest {

	@Test
	public void testCheckSoloLetrasTrue() {
		boolean expectedResult = true;
		boolean actualResult = ValidarTipoEntrada.checkSoloLetras("nombreUSUARIO");
		assertTrue(expectedResult == actualResult);
	}
	
	@Test
	public void testCheckSoloLetrasFalse() {
		boolean expectedResult = false;
		boolean actualResult = ValidarTipoEntrada.checkSoloLetras(null);
		assertTrue(expectedResult == actualResult);
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
