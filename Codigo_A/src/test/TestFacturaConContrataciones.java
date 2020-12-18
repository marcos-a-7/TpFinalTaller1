package test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import interfaces.I_Contratable;
import interfaces.I_Pago;
import mediospagos.PagoCheque;
import personas.Fisica;
import personas.Juridica;
import personas.Persona;
import servicios.DomicilioCasa;
import servicios.Factura;
import servicios.Internet100;

public class TestFacturaConContrataciones
{

	private Factura factura;

	@Before
	public void setUp() throws Exception
	{
		EscenarioFacturaConContrataciones escenario = new EscenarioFacturaConContrataciones();
		factura = escenario.getFactura();
	}

	@Test
	public void testConstructorPersonaNula()
	{
		Fisica persona = null;
		Factura factura = new Factura(persona);
		ArrayList<I_Contratable> arrayList = factura.getListaContrataciones();
		double totalConP = factura.getTotalConP();
		double totalSinP = factura.getTotalSinP();
		Assert.assertEquals("La persona no fue seteada correctamente", persona, factura.getPersona());
		Assert.assertEquals("La lista de contrataciones no fue inicializada correctamente",
				new ArrayList<I_Contratable>(), arrayList);
		Assert.assertEquals("El totalSinP no se inicializa correctamente", 0, totalSinP, 0.01);
		Assert.assertEquals("El totalConP no se inicializa correctamente", 0, totalConP, 0.01);
	}

	@Test
	public void testConstructorPersonaNoNula()
	{
		Fisica persona = new Fisica("Jorge", 6);
		Factura factura = new Factura(persona);
		ArrayList<I_Contratable> arrayList = factura.getListaContrataciones();
		double totalConP = factura.getTotalConP();
		double totalSinP = factura.getTotalSinP();
		Assert.assertEquals("La persona no fue seteada correctamente", persona, factura.getPersona());
		Assert.assertEquals("La lista de contrataciones no fue inicializada correctamente",
				new ArrayList<I_Contratable>(), arrayList);
		Assert.assertEquals("El totalSinP no se inicializa correctamente", 0, totalSinP, 0.01);
		Assert.assertEquals("El totalConP no se inicializa correctamente", 0, totalConP, 0.01);
	}

	@Test
	public void testNuevaContratacion()
	{
		I_Contratable contratable = new Internet100(new DomicilioCasa("nombre de la calle", 100));
		this.factura.nuevaContratacion(contratable);
		Assert.assertEquals("No se contrata el servicio esperado", true,
				this.factura.getListaContrataciones().contains(contratable));
	}

	@Test
	public void testbuscaContratacion()
	{
		Assert.assertEquals("No busca bien una contratacion en la lista de contrataciones", 1,
				this.factura.buscaContratacion("calle5 104"));
	}

	@Test
	public void testModificaContratacionPosNegativa()
	{
		int cantidad = this.factura.getListaContrataciones().size();

		this.factura.modificaContratacion(-1, "QUITAR", "TVCABLE");

		Assert.assertEquals("No deberia modificar nada", cantidad, this.factura.getListaContrataciones().size());
	}

	@Test
	public void testModificaContratacion_PosMayorALaCantidadDeContrataciones()
	{
		int cantidad = this.factura.getListaContrataciones().size();

		this.factura.modificaContratacion(32000, "QUITAR", "TVCABLE");

		Assert.assertEquals("No deberia modificar nada", cantidad, this.factura.getListaContrataciones().size());
	}

	@Test
	public void testModificaContratacionAccionNula()
	{
		int cantidad = this.factura.getListaContrataciones().size();
		this.factura.modificaContratacion(3, null, "TVCABLE");

		Assert.assertEquals("No deberia modificar nada", cantidad, this.factura.getListaContrataciones().size());
	}

	@Test
	public void testModificaContratacionAccionVacia()
	{
		int cantidad = this.factura.getListaContrataciones().size();
		this.factura.modificaContratacion(2, "", "TVCABLE");
		if (cantidad == this.factura.getListaContrataciones().size())
			Assert.fail("No se agrega una contratacion a la lista");
	}

	@Test
	public void testModificaContratacionServicioNull()
	{
		int cantidad = this.factura.getListaContrataciones().size();
		this.factura.modificaContratacion(3, "QUITAR", null);

		Assert.assertEquals("No deberia modificar nada", cantidad, this.factura.getListaContrataciones().size());
	}

	@Test
	public void testModificaContratacionServicioVacio()
	{
		int cantidad = this.factura.getListaContrataciones().size();
		this.factura.modificaContratacion(2, "QUITAR", "");
		if (cantidad == this.factura.getListaContrataciones().size())
			Assert.fail("No se agrega una contratacion a la lista");
	}

	@Test
	public void testModificaContratacionCorrecta()
	{
		int cantidad = this.factura.getListaContrataciones().size();
		this.factura.modificaContratacion(2, "QUITAR", "TVCABLE");
		if (cantidad == this.factura.getListaContrataciones().size())
			Assert.fail("No se agrega una contratacion a la lista");
	}

	@Test
	public void testEliminaContratacion()
	{
		int cantidad = this.factura.getListaContrataciones().size();
		this.factura.eliminaContratacion(2);
		if (cantidad == this.factura.getListaContrataciones().size())
			Assert.fail("No se elimina la contratacion de la lista");
	}

	@Test
	public void testPrecioFinal()
	{
		int cantidad = this.factura.getListaContrataciones().size();
		Persona persona = this.factura.getPersona();
		I_Pago tipo = new PagoCheque();
		this.factura.precioFinal(persona, tipo);
		if (cantidad == this.factura.getListaContrataciones().size())
			Assert.fail("No se agrega una contratacion a la lista");
	}

	@Test
	public void testClonePosible()
	{
		Fisica persona = new Fisica("Jorge", 6);
		Factura factura = new Factura(persona);
		try
		{
			@SuppressWarnings("unused")
			Factura clonFactura = (Factura) factura.clone();
		} catch (CloneNotSupportedException e)
		{
			Assert.fail("No deberia lanzar excepcion de tipo CloneNotSupportedException");
		}
	}

	@Test
	public void testCloneImposible()
	{
		Juridica persona = new Juridica("Jorge", 6);
		Factura factura = new Factura(persona);
		try
		{
			@SuppressWarnings("unused")
			Factura clonFactura = (Factura) factura.clone();
			Assert.fail("Deberia lanzar excepcion de tipo CloneNotSupportedException");
		} catch (CloneNotSupportedException e)
		{

		}

	}

	@Test
	public void testListarContrataciones()
	{
		Assert.assertEquals("No se listan correctamente las contrataciones",
				"ID: 27 Domicilio: calle4 103 SERVICIO== INTERNET500: $1000 \n"
						+ "ID: 28 Domicilio: calle5 104 SERVICIO== INTERNET100: $850 \n"
						+ "ID: 26 Domicilio: calle6 105 SERVICIO== INTERNET100: $850 \n",
				this.factura.listarContrataciones());
	}

	@Test
	public void testActualizaPrecio()
	{
		// Como la documentación no es suficiente para testear el método, se tuvo que
		// leer el código
		// aunque esto no sea lo correcto
		this.factura.actualizaPrecio();
		if (this.factura.getTotalConP() == 0)
			Assert.fail("No se actualiza el precio correctamente");
	}

}
