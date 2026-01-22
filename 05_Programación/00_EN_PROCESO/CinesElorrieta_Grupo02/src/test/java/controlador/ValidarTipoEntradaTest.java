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
	public void testCheckSoloLetrasBlank() {
		boolean resultBlank = ValidarTipoEntrada.checkSoloLetras(" ");
		assertTrue("El espacio en blanco debe ser true", resultBlank);
	}
	

	@Test
	public void testCheckSoloLetrasNull() {
		boolean resultBlank = ValidarTipoEntrada.checkSoloLetras(null);
		System.out.println(resultBlank);
		assertFalse("null debe ser false", resultBlank);
	}
	

	@Test
	public void testCheckSoloAlfanumerico() {
		fail("Not yet implemented");
	}

	
	
	@Test
	public void testCheckSoloNumeroDecimal() {
		boolean result = ValidarTipoEntrada.checkSoloNumeroDecimal("13.3");
		assertTrue(result);
	}
	
	@Test
	public void testCheckSoloNumeroDecimalNull() {
		boolean expectedResult = false;
		boolean actualResult = ValidarTipoEntrada.checkSoloNumeroDecimal(null);
		assertEquals("null debe ser falso", expectedResult, actualResult);
	}
	
	@Test
	public void testCheckSoloNumeroDecimalBlank() {
		boolean expectedResult = false;
		boolean actualResult = ValidarTipoEntrada.checkSoloNumeroDecimal("");
		assertEquals("empty debe ser falso", expectedResult, actualResult);
	}
	
	@Test
	public void testCheckSoloNumeroDecimalSpace() {
		boolean expectedResult = false;
		boolean actualResult = ValidarTipoEntrada.checkSoloNumeroDecimal(" ");
		assertEquals("blank space debe ser falso", expectedResult, actualResult);
	}
	
	@Test
	public void testCheckSoloNumeroEntero() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testEsEnteroFalse() {
		String[] numeros = {"94834384389439843", "-9889434343434343", ",,.", "43i4jii", null, "	"};
		boolean expectedResult = false;
		boolean result = false;
		
		for (String num : numeros) {
			if (ValidarTipoEntrada.esEntero(num) == true) {
				result = true;
				break;
			}
		}
		
		assertEquals("El resultado debe ser falso", expectedResult, result);
	}
	
	@Test
	public void testEsEnteroTrue() {
		String[] numeros = {"2121", "-3223", "1", "-344343", "444", "8493"};
		boolean expectedResult = true;
		boolean result = true;
		
		for (String num : numeros) {
			if (ValidarTipoEntrada.esEntero(num) == false) {
				result = false;
				break;
			}
		}
		
		assertEquals("El resultado debe ser true", expectedResult, result);
	}

}
