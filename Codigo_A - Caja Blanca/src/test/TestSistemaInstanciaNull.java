package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import modelo.Sistema;

public class TestSistemaInstanciaNull
{

	@Test
	public void testGetInstancia()
	{
		Sistema sistema = Sistema.getInstancia();
		Assert.assertEquals("El sistema no se instancio", sistema, Sistema.getInstancia());
	}

}
