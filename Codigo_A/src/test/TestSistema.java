package test;

import static org.junit.Assert.*;

import org.junit.Test;


import mediospagos.PagoCheque;
import modelo.Sistema;
import personas.Fisica;
import personas.Persona;

import org.junit.Assert;
import servicios.Domicilio;
import servicios.DomicilioCasa;


public class TestSistema {

	@Test
	public void testGetInstancia() {
		Sistema sistema = Sistema.getInstancia();
		Assert.assertEquals("El sistema no se creo correctamente", sistema, Sistema.getInstancia());
	}

	@Test
	public void testAgregarFacturas() {
		Sistema sistema = Sistema.getInstancia();
		Persona persona = new Fisica("Jorge", 50314328);
		sistema.agregarFacturas(persona);
		Assert.assertEquals("No se cargo correctamente la persona","Persona fisica Nombre= Jorge DNI=50314328 \n-->PRECIO TOTAL: 0.0",sistema.listarFactura("Jorge"));
		sistema.agregarFacturas(persona);
		fail("Nunca se lanza la excepcion PersonaExistenteException");
		
	}

	
	@Test
	public void testAgregarServicio() {
		Sistema sistema = Sistema.getInstancia();
		Domicilio domicilio= new DomicilioCasa("Colon",1500);
		sistema.agregarServicio("Jorge", null,0,0,0,domicilio);
		sistema.agregarServicio("Jorge", "",0,0,0,domicilio);
		sistema.agregarServicio("Jorge","internete",0,0,0,domicilio);
		sistema.agregarServicio("Jorge","Internet100",0,0,0,null);
		sistema.agregarServicio("Jorge","Internet100",0,0,0,domicilio);	
		Assert.assertEquals("No se cargo correctamente la persona","Persona fisica Nombre= Jorge DNI=50314328\r\n" + 
				"ID: 1 Domicilio: Colon 1500 SERVICIO== INTERNET100: $850 \r\n" + 
				"\r\n" + 
				"-->PRECIO TOTAL: 850.0",sistema.listarFactura("Jorge"));
		fail("Nunca lanza las excepciones correspondientes segun la documentacion, solamente escribe en la consola");
	}

	@Test
	public void testModificarAgregado() {
		Sistema sistema = Sistema.getInstancia();
		Domicilio domicilio= new DomicilioCasa("Colon",1500);
		sistema.agregarServicio("Jorge","Internet100",0,0,0,domicilio);	
		sistema.modificarAgregado("Jorge","Las Heras","CAMBIAR","Internet500");
		Assert.assertEquals("No se cargo correctamente la persona","Persona fisica Nombre= Jorge DNI=50314328\r\n" + 
				"ID: 1 Domicilio: Colon 1500 SERVICIO== INTERNET500: $850 \r\n" + 
				"\r\n" + 
				"-->PRECIO TOTAL: 850.0",sistema.listarFactura("Jorge"));
	}

	@Test
	public void testAbonar() {
		Sistema sistema = Sistema.getInstancia();
		PagoCheque pago= new PagoCheque();
		sistema.abonar("Jorge", pago);
		Assert.assertEquals("No se cargo el abonar correctamente","Persona fisica Nombre= Jorge DNI=50314328\r\n" + 
				"ID: 1 Domicilio: Colon 1500 SERVICIO== INTERNET500: $1000 \r\n" + 
				"ID: 2 Domicilio: Colon 1500 SERVICIO== INTERNET100: $850 \r\n" + 
				"\r\n" + 
				"-->PRECIO TOTAL: 2035.0000000000002",sistema.listarFactura("Jorge"));
	}
	
	@Test
	public void testDuplicarFactura() {
		Sistema sistema = Sistema.getInstancia();
		Persona personaJuridica = new Fisica("Marcelo", 50314328);
		Domicilio domicilio= new DomicilioCasa("Colon",1500);
		sistema.agregarFacturas(personaJuridica);
		sistema.agregarServicio("Marcelo","Internet100",0,0,0,domicilio);	
		sistema.duplicarFactura("Marcelo");
		fail("No tengo manera de comprobar que pudo clonar una factura y tampoco lanza ninguna excepcion como dice la documentacion, todo lo imprime en consola");
	}

	@Test
	public void testEliminarContratacion() {
		Sistema sistema = Sistema.getInstancia();
		Persona persona = new Fisica("Jorge", 50314328);
		Domicilio domicilio= new DomicilioCasa("Colon",1500);
		sistema.agregarFacturas(persona);
		sistema.agregarServicio("Jorge","Internet100",0,0,0,domicilio);	
		sistema.agregarServicio("Jorge","Internet500",0,0,0,domicilio);	
		sistema.eliminarContratacion("Jorge", "Colon");
		Assert.assertEquals("No se elimina correctamente la contratacion","Persona fisica Nombre= Jorge DNI=50314328\r\n" + 
				"ID: 2 Domicilio: Colon 1500 SERVICIO== INTERNET500: $1000 \r\n" + 
				"\r\n" + 
				"-->PRECIO TOTAL: 1000.0",sistema.listarFactura("Jorge"));

	}

	@Test
	public void testListarFactura() {
		Sistema sistema = Sistema.getInstancia();
		Assert.assertEquals("No se realiza correctamente el listar factura con persona","Persona fisica Nombre= Jorge DNI=50314328\r\n" + 
				"ID: 2 Domicilio: Colon 1500 SERVICIO== INTERNET500: $1000 \r\n" + 
				"ID: 3 Domicilio: Colon 1500 SERVICIO== INTERNET100: $850 \r\n" + 
				"ID: 4 Domicilio: Colon 1500 SERVICIO== INTERNET100: $850 \r\n" + 
				"\r\n" + 
				"-->PRECIO TOTAL: 2970.0000000000005",sistema.listarFactura("Jorge"));
		
	}

	@Test
	public void testListarFacturas() {
		Sistema sistema= Sistema.getInstancia();
		System.out.println("------------"+sistema.listarFacturas());
		Assert.assertEquals("No se realiza correctamente el listar facturas","FACTURAS:\r\n" + 
				"Persona fisica Nombre= Marcelo DNI=50314328\r\n" + 
				"Lista de contrataciones: \r\n" + 
				"ID: 4 Domicilio: Colon 1500 SERVICIO== INTERNET100: $850 \r\n" + 
				"\r\n" + 
				"\r\n" + 
				"--> PRECIO TOTAL: 850.0\r\n" + 
				"\r\n" + 
				"Persona fisica Nombre= Jorge DNI=50314328\r\n" + 
				"Lista de contrataciones: \r\n" + 
				"ID: 2 Domicilio: Colon 1500 SERVICIO== INTERNET500: $1000 \r\n" + 
				"ID: 3 Domicilio: Colon 1500 SERVICIO== INTERNET100: $850 \r\n" + 
				"ID: 5 Domicilio: Colon 1500 SERVICIO== INTERNET100: $850 \r\n" + 
				"\r\n" + 
				"\r\n" + 
				"--> PRECIO TOTAL: 2970.0000000000005",sistema.listarFactura("Jorge"));
	}

}
