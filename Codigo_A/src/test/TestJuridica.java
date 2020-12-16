package test;

import org.junit.Assert;
import org.junit.Test;

import interfaces.I_Pago;
import mediospagos.PagoCheque;
import personas.Juridica;

public class TestJuridica {

	@Test
	public void testConstructorNombreNull() {
		Juridica persona = new Juridica(null, 6);
		String nombre = persona.getNombre();
		int cuit = persona.getCUIT();
		Assert.assertEquals("El nombre no ha sido registrado correctamente", null, nombre);
		Assert.assertEquals("El cuit no ha sido registrado correctamente", 6, cuit);
	}

	@Test
	public void testConstructorCuitNegativo() {
		Juridica persona = new Juridica("Jorge", -1);
		String nombre = persona.getNombre();
		int cuit = persona.getCUIT();
		Assert.assertEquals("El nombre no ha sido registrado correctamente", "Jorge", nombre);
		Assert.assertEquals("El cuit no ha sido registrado correctamente", -1, cuit);
	}

	@Test
	public void testConstructorNombreVacio() {
		Juridica persona = new Juridica("", 6);
		String nombre = persona.getNombre();
		int cuit = persona.getCUIT();
		Assert.assertEquals("El nombre no ha sido registrado correctamente", "", nombre);
		Assert.assertEquals("El cuit no ha sido registrado correctamente", 6, cuit);
	}

	@Test
	public void testConstructorDatosCorrectos() {
		Juridica persona = new Juridica("Jorge", 6);
		String nombre = persona.getNombre();
		int cuit = persona.getCUIT();
		Assert.assertEquals("El nombre no ha sido registrado correctamente", "Jorge", nombre);
		Assert.assertEquals("El cuit no ha sido registrado correctamente", 6, cuit);
	}

	@Test
	public void testSetNombre() {
		Juridica persona = new Juridica("Jorge",6);
		persona.setNombre("NuevoJorge");
		Assert.assertEquals("El nombre no ha sido seteado correctamente", "NuevoJorge", persona.getNombre());
	}
	
	@Test
	public void testSetCUIT() {
		Juridica persona = new Juridica("Jorge", 6);
		persona.setCUIT(15);
		Assert.assertEquals("El cuit no ha sido seteado correctamente", 15, persona.getCUIT());
	}

	@Test
	public void testClone() {
		Juridica persona = new Juridica("Jorge", 6);
		try {
			@SuppressWarnings("unused")
			Juridica clonado = (Juridica) persona.clone();
			Assert.fail("Deberia lanzarse una excepcion de tipo CloneNotSupportedException");
		} catch (CloneNotSupportedException e) {

		}
	}

	@Test
	public void testAplicarPorcentaje() {
		Juridica persona = new Juridica("Jorge", 6);
		I_Pago pago = new PagoCheque();
		double total = 100;
		Assert.assertEquals("El total no fue calculado correctamente", 115, persona.aplicarPorcentaje(pago, total),
				0.01);
	}

	@Test
	public void testToString() {
		Juridica persona = new Juridica("Jorge", 500);
		Assert.assertEquals("El toString no muestra los datos correctamente", "Persona Juridica Nombre= Jorge CUIT=500",
				persona.toString());
	}

}
