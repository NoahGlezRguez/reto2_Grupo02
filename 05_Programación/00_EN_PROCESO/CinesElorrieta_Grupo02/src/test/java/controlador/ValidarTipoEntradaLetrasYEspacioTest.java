package controlador;

import static org.junit.Assert.*;

import org.junit.Test;


public class ValidarTipoEntradaLetrasYEspacioTest {
 
	/**
	 * Verifica que el metodo {@link controlador.ValidarTipoEntrada#checkSoloLetras(java.lang.String)}
	 *  devuelva {false} cuando el valor introducido como parametro es numero
	 * 
	 */
	@Test
	public void checkSoloLetras_Numeros_Test() {
		String valor = "323242";
		boolean resultadoEsperado = false;
		boolean resultado = ValidarTipoEntrada.checkSoloLetras(valor);
		assertEquals(resultadoEsperado, resultado);
	}
	
	
	/**
	 * Verifica que el metodo {@link controlador.ValidarTipoEntrada#checkSoloLetras(java.lang.String)}.
	 * devuelva {false} cuando el valor introducido son simbolos
	 */
	@Test
	public void checkLetras_Simbolos_Test() {
		String valor = "·)·==)%·=";
		boolean resultadoEsperado = false;
		boolean resultado = ValidarTipoEntrada.checkNum(valor);
		assertEquals(resultadoEsperado, resultado);
	}
	
	/**
	 * Verifica que el metodo {@link controlador.ValidarTipoEntrada#checkSoloLetras(java.lang.String)}.
	 * devuelva {false} cuando el valor introducido sea null
	 */
	@Test
	public void CheckSoloLetras_CapturarExcepcionEspacioBlanco_Test(){
		boolean resultado = ValidarTipoEntrada.checkSoloLetras(null);
		assertFalse(resultado);
	}
}
