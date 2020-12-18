package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mediospagos.PagoCheque;
import modelo.Sistema;
import personas.Fisica;
import personas.Persona;

import org.junit.Assert;
import org.junit.Before;

import servicios.Domicilio;
import servicios.DomicilioCasa;

public class TestSistema
{

	Sistema sistema = null;

	@Before
	public void setUp()
	{
		sistema = Sistema.getNewInstanceTest();
	}

	@Test
	public void testGetInstancia()
	{
		Sistema sistema = Sistema.getInstancia();
		Assert.assertEquals("El sistema no se creo correctamente", sistema, Sistema.getInstancia());
	}

	@Test
	public void testAgregarFacturas()
	{

		Persona persona = new Fisica("Jorge", 50314328);
		sistema.agregarFacturas(persona);
		Assert.assertEquals("No se cargo correctamente la persona",
				"Persona fisica Nombre= Jorge DNI=50314328\n\n-->PRECIO TOTAL: 0.0\n\n",
				sistema.listarFactura("Jorge"));
		sistema.agregarFacturas(persona);
		fail("Nunca se lanza la excepcion PersonaExistenteException");

	}

	@Test
	public void testAgregarServicioCaso1()
	{

		Persona persona = new Fisica("Juan", 1515);
		sistema.agregarFacturas(persona);
		Domicilio domicilio = new DomicilioCasa("Colon", 1500);
		sistema.agregarServicio("Juan", null, 0, 0, 0, domicilio);

		fail("Nunca lanza las excepciones correspondientes segun la documentacion");
	}

	@Test
	public void testAgregarServicioCaso2()
	{

		Persona persona = new Fisica("Juan", 1515);
		sistema.agregarFacturas(persona);
		Domicilio domicilio = new DomicilioCasa("Colon", 1500);

		sistema.agregarServicio("Juan", "", 0, 0, 0, domicilio);

		fail("Nunca lanza las excepciones correspondientes segun la documentacion");
	}

	@Test
	public void testAgregarServicioCaso3()
	{

		Persona persona = new Fisica("Juan", 1515);
		sistema.agregarFacturas(persona);
		Domicilio domicilio = new DomicilioCasa("Colon", 1500);

		sistema.agregarServicio("Juan", "internete", 0, 0, 0, domicilio);

		fail("Nunca lanza las excepciones correspondientes segun la documentacion");
	}

	@Test
	public void testAgregarServicioCaso4()
	{

		Persona persona = new Fisica("Juan", 1515);
		sistema.agregarFacturas(persona);
		Domicilio domicilio = new DomicilioCasa("Colon", 1500);

		sistema.agregarServicio("Juan", "Internet100", 0, 0, 0, domicilio);
		Assert.assertEquals("No se cargo correctamente la persona", "Persona fisica Nombre= Juan DNI=1515\n"
				+ "ID: 3 Domicilio: Colon 1500 SERVICIO== INTERNET100: $850 \n\n" + "-->PRECIO TOTAL: 850.0\n\n",
				sistema.listarFactura("Juan"));

	}

	@Test
	public void testAgregarServicioCaso5()
	{

		Persona persona = new Fisica("Juan", 1515);
		sistema.agregarFacturas(persona);
		Domicilio domicilio = new DomicilioCasa("Colon", 1500);

		sistema.agregarServicio("Juan", "Internet100", 0, 0, 0, domicilio);

		fail("Nunca lanza las excepciones correspondientes segun la documentacion");
	}

	@Test
	public void testModificarAgregado()
	{

		Persona persona = new Fisica("Pedro", 1010);
		sistema.agregarFacturas(persona);
		Domicilio domicilio = new DomicilioCasa("Colon", 1500);
		sistema.agregarServicio("Pedro", "Internet100", 0, 0, 0, domicilio);
		sistema.modificarAgregado("Pedro", "Las Heras", "CAMBIAR", "Internet500");
		Assert.assertEquals("No se cargo correctamente la persona", "Persona fisica Nombre= Pedro DNI=1010\n"
				+ "ID: 7 Domicilio: Colon 1500 SERVICIO== INTERNET500: $1000 \n\n" + "-->PRECIO TOTAL: 1000.0\n\n",
				sistema.listarFactura("Pedro"));
	}

	@Test
	public void testAbonar()
	{

		PagoCheque pago = new PagoCheque();

		Persona persona = new Fisica("Cacho", 1515);
		sistema.agregarFacturas(persona);
		Domicilio domicilio = new DomicilioCasa("Colon", 1500);

		sistema.agregarServicio("Cacho", "Internet100", 0, 0, 0, domicilio);
		sistema.abonar("Cacho", pago);
		Assert.assertEquals("No se cargo el abonar correctamente",
				"Persona fisica Nombre= Cacho DNI=1515\n"
						+ "ID: 7 Domicilio: Colon 1500 SERVICIO== INTERNET100: $850 \n\n"
						+ "-->PRECIO TOTAL: 935.0000000000001\n\n",
				sistema.listarFactura("Cacho"));
	}

	@Test
	public void testDuplicarFactura()
	{

		Persona personaJuridica = new Fisica("Marcelo", 50314328);
		Domicilio domicilio = new DomicilioCasa("Colon", 1500);
		sistema.agregarFacturas(personaJuridica);
		sistema.agregarServicio("Marcelo", "Internet100", 0, 0, 0, domicilio);
		sistema.duplicarFactura("Marcelo");
		fail("El metodo no devuelve la nueva instancia de factura y la clase no cuenta con ningun metodo que permita comprobar si la clono correctamente");
	}

	@Test
	public void testEliminarContratacion()
	{
		Sistema sistema = Sistema.getInstancia();
		Persona persona = new Fisica("Rodrigo", 100);
		Domicilio domicilio = new DomicilioCasa("Colon", 1500);
		Domicilio domicilio2 = new DomicilioCasa("Luro", 1500);
		sistema.agregarFacturas(persona);
		sistema.agregarServicio("Rodrigo", "Internet100", 0, 0, 0, domicilio);
		sistema.agregarServicio("Rodrigo", "Internet100", 0, 0, 0, domicilio2);
		sistema.eliminarContratacion("Rodrigo", "Luro 1500");
		Assert.assertEquals("No se elimina correctamente la contratacion", "Persona fisica Nombre= Rodrigo DNI=100\n"
				+ "ID: 2 Domicilio: Colon 1500 SERVICIO== INTERNET100: $850 \n" + "\n" + "-->PRECIO TOTAL: 850.0\n\n",
				sistema.listarFactura("Rodrigo"));

	}

	@Test
	public void testListarFacturaSinContrataciones()
	{

		Persona persona = new Fisica("Marcos", 15);
		sistema.agregarFacturas(persona);
		Assert.assertEquals("No se realiza correctamente el listar factura con persona",
				"Persona fisica Nombre= Marcos DNI=15\n\n" + "-->PRECIO TOTAL: 0.0\n\n",
				sistema.listarFactura("Marcos"));

	}

	@Test
	public void testListarFacturaConContrataciones()
	{

		Persona persona = new Fisica("Matias", 1515);
		sistema.agregarFacturas(persona);
		Domicilio domicilio = new DomicilioCasa("Colon", 1500);

		sistema.agregarServicio("Matias", "Internet100", 0, 0, 0, domicilio);

		Assert.assertEquals("No se realiza correctamente el listar factura con persona",
				"Persona fisica Nombre= Matias DNI=1515\n"
						+ "ID: 6 Domicilio: Colon 1500 SERVICIO== INTERNET100: $850 \n\n"

						+ "-->PRECIO TOTAL: 850.0\n\n",
				sistema.listarFactura("Matias"));

	}

	@Test
	public void testListarFacturas()
	{
		Sistema sistema = Sistema.getInstancia();
		Persona persona = new Fisica("Rodrigo", 100);
		Domicilio domicilio = new DomicilioCasa("Colon", 1500);
		Domicilio domicilio2 = new DomicilioCasa("Luro", 1500);
		sistema.agregarFacturas(persona);
		sistema.agregarServicio("Rodrigo", "Internet100", 0, 0, 0, domicilio);
		sistema.agregarServicio("Rodrigo", "Internet100", 0, 0, 0, domicilio2);

		System.out.println("------------" + sistema.listarFacturas());
		Assert.assertEquals("No se realiza correctamente el listar facturas",
				"FACTURAS:\n" + "Persona fisica Nombre= Rodrigo DNI=100\n" + "Lista de contrataciones: \n"
						+ "ID: 2 Domicilio: Luro 1500 SERVICIO== INTERNET100: $850 \n"
						+ "ID: 9 Domicilio: Colon 1500 SERVICIO== INTERNET100: $850 \n"
						+ "ID: 10 Domicilio: Luro 1500 SERVICIO== INTERNET100: $850 \n" + "\n" + "\n"
						+ "--> PRECIO TOTAL: 850.0\n\n",
				sistema.listarFacturas());
	}

}
