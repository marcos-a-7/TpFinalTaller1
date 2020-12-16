package test;



import org.junit.Assert;
import org.junit.Test;

import interfaces.I_Pago;
import mediospagos.PagoCheque;
import personas.Fisica;

public class TestFisica {

	@Test
	public void testConstructorNombreNull() {
		Fisica persona = new Fisica(null,6);
		String nombre = persona.getNombre();
		int DNI = persona.getDNI();
		Assert.assertEquals("El nombre no ha sido registrado correctamente",null, nombre);
		Assert.assertEquals("El cuit no ha sido registrado correctamente",6, DNI);
	}
	
	@Test
	public void testConstructorDNINegativo() {
		Fisica persona = new Fisica("Jorge",-1);
		String nombre = persona.getNombre();
		int DNI = persona.getDNI();
		Assert.assertEquals("El nombre no ha sido registrado correctamente","Jorge", nombre);
		Assert.assertEquals("El cuit no ha sido registrado correctamente",-1, DNI);
	}
	
	@Test
	public void testConstructorNombreVacio() {
		Fisica persona = new Fisica("",6);
		String nombre = persona.getNombre();
		int DNI = persona.getDNI();
		Assert.assertEquals("El nombre no ha sido registrado correctamente","", nombre);
		Assert.assertEquals("El cuit no ha sido registrado correctamente",6, DNI);
	}
	
	@Test
	public void testConstructorDatosCorrectos() {
		Fisica persona = new Fisica("Jorge",6);
		String nombre = persona.getNombre();
		int DNI = persona.getDNI();
		Assert.assertEquals("El nombre no ha sido registrado correctamente","Jorge", nombre);
		Assert.assertEquals("El cuit no ha sido registrado correctamente",6, DNI);
	}

	@Test
	public void testSetNombre() {
		Fisica persona = new Fisica("Jorge",6);
		persona.setNombre("NuevoJorge");
		Assert.assertEquals("El nombre no fue seteado correctamente", "NuevoJorge", persona.getNombre());
	}
	
	@Test
	public void testSetDNI() {
		Fisica persona = new Fisica("Jorge",6);
		persona.setDNI(15);
		Assert.assertEquals("El dni no fue seteado correctamente", 15, persona.getDNI());
	}
	
	@Test
	public void testClone() {
		Fisica persona = new Fisica("Jorge", 6);
		@SuppressWarnings("unused")
		Fisica clonado = (Fisica) persona.clone();
	}
	
	@Test
	public void testAplicarPorcentaje() {
		Fisica persona = new Fisica("Jorge", 6);
		I_Pago pago = new PagoCheque();
		double total = 100;
		Assert.assertEquals("El total no fue calculado correctamente", 110, persona.aplicarPorcentaje(pago, total),
				0.01);
	}
	
	@Test
	public void testToString() {
		Fisica persona = new Fisica("Jorge", 500);
		persona.toString();
		Assert.assertEquals("El toString no muestra los datos correctamente", "Persona fisica Nombre= Jorge DNI=500",
				persona.toString());
	}
	
}
