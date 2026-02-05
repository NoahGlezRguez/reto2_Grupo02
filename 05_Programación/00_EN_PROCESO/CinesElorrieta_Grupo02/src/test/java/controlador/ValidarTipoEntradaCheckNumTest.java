package controlador;

import java.util.Collection;
import java.util.Arrays;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ValidarTipoEntradaCheckNumTest {
	private String valorEvaluado;
	private boolean resultadoEsperado;

	public ValidarTipoEntradaCheckNumTest(String valorEvaluado, boolean resultado) {
		this.valorEvaluado = valorEvaluado;
		this.resultadoEsperado = resultado;
	}

	@Parameters
    public static Collection<Object[]> datos() {
    	return Arrays.asList(new Object[][] {
			{"555", true},
			{"3", true},
			{"-333", true},
			{"1000000000", true},
			{"   123", false},
			{"123   ", false},
			{"  456  ", false},
			{"aaaa", false},
			{"123abc", false},
			{"abc123", false},
			{"12 34", false},
			{"56-78", false},
			{"++89", false},
			{"--90", false},
			{"@", false},
			{"#$%", false},
			{"1,000", false},
			{"1000.0", false},
			{"-1.23", false},
			{"", false},
			{"😆😆😆😆", false},
			{"😆12212123", false},
			{"439430943904304343093409021921092102192109", false},
			{"\n", false},
			{"\r\n", false},
			
			// Estos serian losv alores limite
			{"2147483647", true},  //INTEGER.MAX  
			{"-2147483648", true},   //INTEGER.MIN  
			
			// valores limite +1
			{"2147483648", false}, //INTEGER.MAX +1
			{"-2147483649", false},  //INTEGER.MIN +1
    	});
    }

	@Test
	public void testCheckNum() {
		boolean resultado = ValidarTipoEntrada.checkNum(valorEvaluado);
		assertEquals(resultadoEsperado, resultado);
	}

}
