package testSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import controlador.*;
import modelo.*;

@RunWith(Suite.class)
@SuiteClasses({ ValidarTipoEntradaCheckNumTest.class,
	ValidarTipoEntradaLetrasYEspacioTest.class, 
	ClienteValidarDniTest.class,
	CompraTest.class})

public class RunAllTests {

}