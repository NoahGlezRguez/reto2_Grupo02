package modelo;

import java.util.Collection;
import java.util.Arrays;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;


import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ClienteValidarDniTest {
	private static Cliente cliente1 = null;
	private String valor;
	private boolean resultadoEsperado;
	
	public ClienteValidarDniTest(String valor, boolean resultadoEsperado) {
		this.valor = valor;
		this.resultadoEsperado = resultadoEsperado;
	}

	@Parameters
	public static Collection<Object[]> numerosDni() {
		return Arrays.asList(new Object[][] { 
				{ "-8898987K", false }, //numero negativo
				{ "12345678K", true }, 
				{ "1234567Z", false }, 
				{ "123456789", false },
				{ "1234A678Z", false }, 
				{ "X12345678Z", false },
				{ "A1234567B", false },
				{ "xzzx1234567a", false }, 
				
				// vacio
				{ "", false }, 
				{ "1234))·30929032", false }, 
				{ "32.32.32-21", false },
				
				//solo letras
				{ "ekwockewpokcewp", false },
				
				// espacio
				{ "    		", false }, 
				{ "\r\n", false }
			});
	}
	
	/**
	 * Inicializa una instancia de la clase cliente
	 * para usarlo en la prueba
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		cliente1 = new Cliente();
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception{
		cliente1 = null;
	}

	/**
	 * Test del metodo validardni usando todos los parametros
	 * declarados en la colecion de objetos numerosDni
	 */
	@Test
	public void testValidarDni() {
		boolean resultado = cliente1.validarDni(valor);
		assertEquals(resultadoEsperado, resultado);
	}
 
}
