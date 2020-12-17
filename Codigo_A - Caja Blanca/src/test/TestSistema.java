package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mediospagos.PagoCheque;
import mediospagos.PagoEfectivo;
import modelo.Sistema;
import personas.Fisica;
import personas.Juridica;
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
		Sistema.getInstancia();
		Sistema sistema = Sistema.getInstancia();
		Assert.assertEquals("El sistema no se creo correctamente", sistema, Sistema.getInstancia());
	}

	@Test
	public void testGetInstanciaNull()
	{
		Sistema sistema = Sistema.getInstancia();
		Assert.assertEquals("El sistema no se creo correctamente", sistema, Sistema.getInstancia());
	}

	@Test
	public void testAgregarFacturasNoRepetida()
	{

		Persona persona = new Fisica("Ezequiel", 1);
		sistema.agregarFacturas(persona);

		Assert.assertEquals("No se cargo correctamente la persona",
				"Persona fisica Nombre= Ezequiel DNI=1\n\n-->PRECIO TOTAL: 0.0\n\n", sistema.listarFactura("Ezequiel"));
	}

	@Test
	public void testAgregarFacturasRepetida()
	{

		Persona persona = new Fisica("Juan", 1);
		sistema.agregarFacturas(persona);

		String listaFacturasOld = sistema.listarFacturas();
		sistema.agregarFacturas(persona);
		Assert.assertEquals("No deberia modificarse ninguna factura en el sistema", listaFacturasOld,
				sistema.listarFacturas());

		fail("Nunca se lanza la excepcion PersonaExistenteException");
	}

	@Test
	public void testAgregarServicioCorrecto()
	{

		Persona persona = new Fisica("Jorge", 1515);
		sistema.agregarFacturas(persona);
		Domicilio domicilio = new DomicilioCasa("Colon", 1500);

		sistema.agregarServicio("Jorge", "Internet100", 0, 0, 0, domicilio);
		Assert.assertEquals("No se cargo correctamente la persona", "Persona fisica Nombre= Jorge DNI=1515\n"
				+ "ID: 1 Domicilio: Colon 1500 SERVICIO== INTERNET100: $850 \n\n" + "-->PRECIO TOTAL: 850.0\n\n",
				sistema.listarFactura("Jorge"));
	}

	@Test
	public void testAgregarServicioConServicioIncorrecto()
	{

		Persona persona = new Fisica("Jorge", 1515);
		sistema.agregarFacturas(persona);
		Domicilio domicilio = new DomicilioCasa("Colon", 1500);

		String facturaOld = sistema.listarFactura("Jorge");
		sistema.agregarServicio("Jorge", "Internet300", 0, 0, 0, domicilio);
		Assert.assertEquals("No deberia modificarse la factura", facturaOld, sistema.listarFactura("Jorge"));

		fail("Nunca lanza las excepciones correspondientes segun la documentacion");
	}

	@Test
	public void testAgregarServicioConDomicilioNull()
	{

		Persona persona = new Fisica("Jorge", 1515);
		sistema.agregarFacturas(persona);

		String facturaOld = sistema.listarFactura("Jorge");
		sistema.agregarServicio("Jorge", "Internet100", 0, 0, 0, null);
		Assert.assertEquals("No deberia modificarse la factura", facturaOld, sistema.listarFactura("Jorge"));

		fail("Nunca lanza las excepciones correspondientes segun la documentacion");
	}

	@Test
	public void testModificarAgregado()
	{

		Persona persona = new Fisica("Rodrigo", 1010);
		sistema.agregarFacturas(persona);
		Domicilio domicilio = new DomicilioCasa("Luro", 1500);
		sistema.agregarServicio("Rodrigo", "Internet500", 0, 0, 0, domicilio);

		sistema.modificarAgregado("Rodrigo", "Luro 1500", "CAMBIAR", "Internet100");
		Assert.assertEquals("No se cargo correctamente la persona",
				"Persona fisica Nombre= Rodrigo DNI=1010\n"
						+ "ID: 5 Domicilio: Luro 1500 SERVICIO== INTERNET100: $850 \n\n" + "-->PRECIO TOTAL: 850.0\n\n",
				sistema.listarFactura("Rodrigo"));
	}

	@Test
	public void testAbonar()
	{

		PagoCheque pago = new PagoCheque();

		Persona persona = new Fisica("Lucas", 1515);
		sistema.agregarFacturas(persona);
		Domicilio domicilio = new DomicilioCasa("Colon", 1500);

		sistema.agregarServicio("Lucas", "Internet100", 0, 0, 0, domicilio);
		sistema.abonar("Lucas", pago);
		Assert.assertEquals("No se cargo el abonar correctamente",
				"Persona fisica Nombre= Lucas DNI=1515\n"
						+ "ID: 8 Domicilio: Colon 1500 SERVICIO== INTERNET100: $850 \n\n"
						+ "-->PRECIO TOTAL: 935.0000000000001\n\n",
				sistema.listarFactura("Lucas"));
	}

	@Test
	public void testDuplicarFacturaFisica()
	{

		Persona persona = new Fisica("Ricardo", 50314328);
		sistema.agregarFacturas(persona);

		sistema.duplicarFactura("Ricardo");
		fail("No hay forma de comprobar que el metodo genera un clone de la factura dado que no lo devuelve solo imprime en consola");
	}

	@Test
	public void testDuplicarFacturaJuridica()
	{

		Persona persona = new Juridica("Pablo", 50314328);
		sistema.agregarFacturas(persona);

		sistema.duplicarFactura("Pablo");
		fail("El metodo no lanza una excepcion CloneNotSupportedException");
	}

	@Test
	public void testEliminarContratacionPersonaConDosContrataciones()
	{

		Persona persona = new Fisica("Matias", 100);
		Domicilio domicilio = new DomicilioCasa("Roca", 100);
		Domicilio domicilio2 = new DomicilioCasa("Malvinas", 200);
		sistema.agregarFacturas(persona);
		sistema.agregarServicio("Matias", "Internet100", 0, 0, 0, domicilio);
		sistema.agregarServicio("Matias", "Internet100", 0, 0, 0, domicilio2);
		sistema.eliminarContratacion("Matias", "Roca 100");
		Assert.assertEquals("No se elimina correctamente la contratacion", "Persona fisica Nombre= Matias DNI=100\n"
				+ "ID: 6 Domicilio: Malvinas 200 SERVICIO== INTERNET100: $850 \n" + "\n" + "-->PRECIO TOTAL: 850.0\n\n",
				sistema.listarFactura("Matias"));

	}

	@Test
	public void testEliminarContratacionPersonaNoExistente()
	{

		String facturasOld = sistema.listarFacturas();
		sistema.eliminarContratacion("Valentina", "Las Heras 500");
		Assert.assertEquals("No deberian cambiar las facturas del sistema", facturasOld, sistema.listarFacturas());

	}

	@Test
	public void testEliminarContratacionPersonaSinContrataciones()
	{
		sistema.agregarFacturas(new Fisica("Demian", 10));

		String facturasOld = sistema.listarFacturas();
		sistema.eliminarContratacion("Demian", "Las Heras 500");
		Assert.assertEquals("No deberian cambiar las facturas del sistema", facturasOld, sistema.listarFacturas());

	}

	@Test
	public void testEliminarContratacionPersonaConUnaContratacion()
	{
		sistema.agregarFacturas(new Fisica("Marcos", 10));
		sistema.agregarServicio("Marcos", "Internet100", 0, 0, 0, new DomicilioCasa("9 de julio", 500));
		String facturasOld = sistema.listarFacturas();

		sistema.eliminarContratacion("Marcos", "9 de julio 500");
		Assert.assertNotEquals("Deberia eliminarse una factura", facturasOld, sistema.listarFacturas());

	}

	@Test
	public void testListarFacturaConDescuento()
	{
		Sistema sistema = Sistema.getInstancia();
		Persona persona = new Fisica("Luciano", 15);
		sistema.agregarFacturas(persona);
		sistema.agregarServicio("Luciano", "Internet100", 0, 0, 0, new DomicilioCasa("9 de julio", 500));
		sistema.abonar("Luciano", new PagoEfectivo());

		Assert.assertEquals("No se realiza correctamente el listar factura",
				"Persona fisica Nombre= Luciano DNI=15\n"
						+ "ID: 3 Domicilio: 9 de julio 500 SERVICIO== INTERNET100: $850 \n" + "\n"
						+ "-->PRECIO TOTAL SIN DESCUENTO: 850.0\n" + "\n" + "\n"
						+ "-->PRECIO TOTAL CON DESCUENTO: 680.0\n" + "\n",
				sistema.listarFactura("Luciano"));
	}

	@Test
	public void testListarFacturaSinDescuento()
	{
		Sistema sistema = Sistema.getInstancia();
		Persona persona = new Juridica("Julian", 15);
		sistema.agregarFacturas(persona);
		sistema.agregarServicio("Julian", "Internet100", 0, 0, 0, new DomicilioCasa("9 de julio", 500));
		sistema.abonar("Julian", new PagoCheque());
		Assert.assertEquals("No se realiza correctamente el listar factura con persona",
				"Persona Juridica Nombre= Julian CUIT=15\n"
						+ "ID: 2 Domicilio: 9 de julio 500 SERVICIO== INTERNET100: $850 \n" + "\n"
						+ "-->PRECIO TOTAL: 977.4999999999999\n" + "\n",
				sistema.listarFactura("Julian"));
	}

}
