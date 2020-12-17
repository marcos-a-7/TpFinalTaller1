package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import mediospagos.PagoCheque;
import mediospagos.PagoEfectivo;
import modelo.Sistema;
import personas.Fisica;
import personas.Juridica;
import personas.Persona;
import servicios.DomicilioCasa;

public class TestSistemaEsenarioParaListarFacturas
{
	Sistema sistema = Sistema.getInstancia();

	@Before
	public void setUp()
	{
		Persona persona = new Fisica("Flor", 15);
		sistema.agregarFacturas(persona);
		sistema.agregarServicio("Flor", "Internet100", 0, 0, 0, new DomicilioCasa("9 de julio", 500));
		sistema.abonar("Flor", new PagoEfectivo());

		Persona persona1 = new Juridica("Julieta", 15);
		sistema.agregarFacturas(persona1);
		sistema.agregarServicio("Julieta", "Internet100", 0, 0, 0, new DomicilioCasa("3 de febrero", 500));
		sistema.abonar("Julieta", new PagoCheque());
	}

	@Test
	public void testListarFacturas()
	{
		Sistema sistema = Sistema.getInstancia();
		System.out.println("------------" + sistema.listarFacturas());
		Assert.assertEquals("No se realiza correctamente el listar facturas",
				"FACTURAS:\n" + "Persona fisica Nombre= Flor DNI=15\n" + "Lista de contrataciones: \n"
						+ "ID: 1 Domicilio: 9 de julio 500 SERVICIO== INTERNET100: $850 \n" + "\n" + "\n"
						+ "--> PRECIO TOTAL SIN DESCUENTO: 850.0\n" + "\n" + "\n"
						+ "--> PRECIO TOTAL CON DESCUENTO: 680.0\n" + "\n"
						+ "Persona Juridica Nombre= Julieta CUIT=15\n" + "Lista de contrataciones: \n"
						+ "ID: 2 Domicilio: 3 de febrero 500 SERVICIO== INTERNET100: $850 \n" + "\n" + "\n"
						+ "--> PRECIO TOTAL: 977.4999999999999\n" + "\n",
				sistema.listarFacturas());
	}

}
