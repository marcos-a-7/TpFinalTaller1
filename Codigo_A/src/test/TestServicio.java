package test;


import org.junit.Assert;
import org.junit.Test;

import servicios.Domicilio;
import servicios.DomicilioCasa;
import servicios.Internet500;
import servicios.Servicio;

public class TestServicio {

	
	
	
	/**
	 * Testeo el constructor con un parametro
	 */
	@Test
	public void testPrimerConstructor() {
		Domicilio domicilio= new DomicilioCasa("Colon",1500);
		Servicio servicio= new Internet500(domicilio);
		Assert.assertEquals("El domicilio no ha sido registrado correctamente",domicilio,servicio.getDomicilio());
	}

	/**
	 * Testeo el constructor con dos parametros
	 */
	@Test
	public void testSegundoConstructor() {
		Domicilio domicilio= new DomicilioCasa("Colon",1500);
		int servicioID=6;
		Servicio servicio= new Internet500(domicilio,servicioID);
		Assert.assertEquals("El servicioID no ha sido registrado correctamente",servicioID,servicio.getID());
	}
	
	/**
	 * Testeo el clonar
	 */
	@Test
	public void testClone() {
		Domicilio domicilio= new DomicilioCasa("Colon",1500);
		Servicio servicio= new Internet500(domicilio);
		Servicio servicioClonado=(Servicio) servicio.clone();
		Assert.assertEquals("El domicilio no ha sido registrado correctamente",domicilio,servicioClonado.getDomicilio());
	}

}
