package test;



import org.junit.Test;
import org.junit.Assert;
import servicios.Domicilio;
import servicios.DomicilioCasa;
import servicios.Internet100;


public class testInternet100 {

	/**
	 * Testeo el constructor con un parametro
	 */
	@Test
	public void testPrimerConstructor() {
		Domicilio domicilio= new DomicilioCasa("Colon",1500);
		Internet100 internet= new Internet100(domicilio);
		Assert.assertEquals("El domicilio no ha sido registrado correctamente",domicilio,internet.getDomicilio());
	}
	
	/**
	 * Testeo el constructor con dos parametros
	 */
	@Test
	public void testSegundoConstructor() {
		Domicilio domicilio= new DomicilioCasa("Colon",1500);
		int servicioID=6;
		Internet100 internet= new Internet100(domicilio,servicioID);
		Assert.assertEquals("El servicioID no ha sido registrado correctamente",servicioID,internet.getID());
	}
	
	/**
	 * Testeo el metodo getPrecio
	 */
	@Test
	public void testGetPrecio() {
		Domicilio domicilio= new DomicilioCasa("Colon",1500);
		Internet100 internet= new Internet100(domicilio);
		Assert.assertEquals("Devuelve numero erroneo",850,internet.getPrecio(),0.01);
	}
	
	/**
	 * Testeo el metodo toString
	 */
	@Test
	public void testToString() {
		Domicilio domicilio= new DomicilioCasa("Colon",1500);
		Internet100 internet= new Internet100(domicilio);
		Assert.assertEquals("Devuelve string erroneo","Domicilio: Colon 1500 SERVICIO== INTERNET100: $850 ",internet.toString());
	}

	/**
	 * Testeo el metodo isInternet100
	 */
	@Test
	public void testIsInternet100() {
		Domicilio domicilio= new DomicilioCasa("Colon",1500);
		Internet100 internet= new Internet100(domicilio);
		Assert.assertEquals("Devuelve boolean incorrecto",true,internet.isInternet100());
	}
	
	/**
	 * Testeo el metodo isInternet500
	 */
	@Test
	public void testIsInternet500() {
		Domicilio domicilio= new DomicilioCasa("Colon",1500);
		Internet100 internet= new Internet100(domicilio);
		Assert.assertEquals("Devuelve boolean incorrecto",false,internet.isInternet500());
	}
	
	
	
	/**
	 * Testeo el metodo isCelular
	 */
	@Test
	public void testIsCelular() {
		Domicilio domicilio= new DomicilioCasa("Colon",1500);
		Internet100 internet= new Internet100(domicilio);
		Assert.assertEquals("Devuelve boolean incorrecto",false,internet.isCelular());
	}
	
	
	/**
	 * Testeo el metodo isTelefono
	 */
	@Test
	public void testIsTelefono() {
		Domicilio domicilio= new DomicilioCasa("Colon",1500);
		Internet100 internet= new Internet100(domicilio);
		Assert.assertEquals("Devuelve boolean incorrecto",false,internet.isTelefono());
	}
	
	/**
	 * Testeo el metodo isTV_Cable
	 */
	@Test
	public void testIsTVCABLE() {
		Domicilio domicilio= new DomicilioCasa("Colon",1500);
		Internet100 internet= new Internet100(domicilio);
		Assert.assertEquals("Devuelve boolean incorrecto",false,internet.isTV_Cable());
	}
	
	
	
}
