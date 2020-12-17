package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.Sistema;

public class TestSistemaListaVacia
{

	Sistema sistema = null;

	@Before
	public void setUp()
	{
		sistema = Sistema.getNewInstanceTest();
	}

	@Test
	public void testListarFacturas()
	{
		System.out.println("------------" + sistema.listarFacturas());
		Assert.assertEquals("No se realiza correctamente el listar facturas", "FACTURAS:\n", sistema.listarFacturas());
	}

}
