package controlador;

import java.util.Collection;
import java.util.Arrays;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ValidarTipoEntradaLetrasYEspacioTest {
	private String valor;
	private boolean resultadoEsperado;
	
	public ValidarTipoEntradaLetrasYEspacioTest(String valor, boolean resultadoEsperado) {
		this.valor = valor;
		this.resultadoEsperado = resultadoEsperado;
	}
	
	@Parameters
	public static Collection<Object[]> listadeNumerosSimbolos (){
		return Arrays.asList(new Object[][]{
			{"111",   false},
			{"212121",   false}, 
			{"",  false},
			{".,.,,--,",  false},
			{"983298328´(·(·(",  false},
			{"\r", false}, 
			{"😆😆",  false},
			{"			",  false},
			{"1ñ2",  false},
			{null,  false},
			{"""
					
					1
					""",  false}
		});
	};
	
	@Test
	public void testCheckSoloLetrasYEspacio() {
		boolean resultado = ValidarTipoEntrada.checkSoloLetras(valor);
		assertEquals(resultadoEsperado, resultado);
	}
}
