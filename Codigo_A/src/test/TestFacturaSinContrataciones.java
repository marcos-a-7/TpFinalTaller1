package test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import interfaces.I_Contratable;
import mediospagos.PagoCheque;
import personas.Fisica;
import personas.Juridica;
import personas.Persona;
import servicios.DomicilioCasa;
import servicios.Factura;
import servicios.Internet100;

public class TestFacturaSinContrataciones {

	@Test
	public void testConstructorPersonaNula() {
		Fisica persona = null;
		Factura factura = new Factura(persona);
		ArrayList<I_Contratable> arrayList = factura.getListaContrataciones();
		double totalConP = factura.getTotalConP();
		double totalSinP = factura.getTotalSinP();
		Assert.assertEquals("La persona no fue seteada correctamente",persona,factura.getPersona());
		Assert.assertEquals("La lista de contrataciones no fue inicializada correctamente",new ArrayList<I_Contratable>(), arrayList);
		Assert.assertEquals("El totalSinP no se inicializa correctamente",0,totalSinP,0.01);
		Assert.assertEquals("El totalConP no se inicializa correctamente",0,totalConP,0.01);
	}
	
	@Test
	public void testConstructorPersonaNoNula() {
		Fisica persona = new Fisica("Jorge",6);
		Factura factura = new Factura(persona);
		ArrayList<I_Contratable> arrayList = factura.getListaContrataciones();
		double totalConP = factura.getTotalConP();
		double totalSinP = factura.getTotalSinP();
		Assert.assertEquals("La persona no fue seteada correctamente",persona,factura.getPersona());
		Assert.assertEquals("La lista de contrataciones no fue inicializada correctamente",new ArrayList<I_Contratable>(), arrayList);
		Assert.assertEquals("El totalSinP no se inicializa correctamente",0,totalSinP,0.01);
		Assert.assertEquals("El totalConP no se inicializa correctamente",0,totalConP,0.01);
	}		

	@Test
	public void testNuevaContratacion() {
		Factura factura = new Factura(new Fisica("Jorge",6));
		I_Contratable contratable = new Internet100(new DomicilioCasa("nombre de la calle",100));
		factura.nuevaContratacion(contratable);
		Assert.assertEquals("No se contrata el servicio esperado", true,factura.getListaContrataciones().contains(contratable));
	}


	@Test
	public void testPrecioFinal() {
		Persona persona = new Fisica("Jorge",6);
		Factura factura = new Factura(persona);
		int cantidad = factura.getListaContrataciones().size();
		factura.precioFinal(persona, new PagoCheque());
		if (cantidad == factura.getListaContrataciones().size())
			Assert.fail("No se agrega una contratacion a la lista");
	}

	@Test
	public void testClonePosible() {
		Fisica persona = new Fisica("Jorge",6); 
		Factura factura = new Factura(persona);
		try {
			@SuppressWarnings("unused")
			Factura clonFactura = (Factura) factura.clone();
		} catch (CloneNotSupportedException e) {
			Assert.fail("No deberia lanzar excepcion de tipo CloneNotSupportedException");
		}
	}
	
	@Test
	public void testCloneImposible() {
		Juridica persona = new Juridica("Jorge",6); 
		Factura factura = new Factura(persona);
		try {
			@SuppressWarnings("unused")
			Factura clonFactura = (Factura) factura.clone();
			Assert.fail("Deberia lanzar excepcion de tipo CloneNotSupportedException");
		} catch (CloneNotSupportedException e) {
			
		}
		
	}

	@Test
	public void testListarContrataciones() {
		Assert.assertEquals("No lista bien la lista de contrataciones","",new Factura(new Fisica("Jorge",6)).listarContrataciones());
	}

	@Test
	public void testActualizaPrecio() {
		Factura factura = new Factura(new Fisica("Jorge",6));
		factura.actualizaPrecio();
		if (factura.getTotalConP() != 0)
			Assert.fail("No se actualiza el precio correctamente");
	}

}
