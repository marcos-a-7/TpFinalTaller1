package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import modelo.Sistema;

public class TestSistemaListaVacia
{

	@Test
	public void testListarFacturas()
	{
		Sistema sistema = Sistema.getInstancia();
		System.out.println("------------" + sistema.listarFacturas());
		Assert.assertEquals("No se realiza correctamente el listar facturas", "FACTURAS:\n", sistema.listarFacturas());
	}

}
