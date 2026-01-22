
package controlador;

import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.Test;

public class ValidarLoginTest {

	@Test
	public void testValidarSesionTrue() {
		boolean resultado = ValidarLogin.validar("ana", "1234");
		assertTrue(resultado);
	}

	@Test
	public void testValidarSesionFalse() {
		boolean resultado = ValidarLogin.validar("ana", "12345");
		assertFalse(resultado);
	}

	@Test(expected = SQLException.class)
	public void testSQLException() throws Exception {
		ValidarLogin.validar("889", "cuaqluierdato");
	}

	@Test(expected = NullPointerException.class)
	public void testNullConection() throws Exception {
		ValidarLogin.validar("prueba1", "123");
	}

}
