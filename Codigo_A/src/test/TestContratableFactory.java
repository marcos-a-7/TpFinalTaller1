package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import agregado.ContratableFactory;
import excepciones.DomicilioInvalidoException;
import excepciones.ServicioInternetInvalidoException;
import interfaces.I_Contratable;
import servicios.Domicilio;
import servicios.DomicilioCasa;
import servicios.Internet100;

public class TestContratableFactory
{

	@Test
	public void nuevoServicioConServicios()
	{
		Domicilio domicilio = new DomicilioCasa("9 de julio", 500);

		try
		{
			I_Contratable contratable = ContratableFactory.nuevoServicio("Internet100", 1, 1, 1, domicilio);
			Assert.assertEquals("El domicilio no coincide", domicilio, contratable.getDomicilio()); // intesteable si
																									// carga
																									// correctamente el
																									// tipo internet y
																									// la cantidad de
																									// los servicios

		} catch (ServicioInternetInvalidoException e)
		{
			fail("El internet no deberia ser invalido");
		} catch (DomicilioInvalidoException e)
		{
			fail("El domicilio no deberia ser invalido");
		}
	}
	
	@Test
	public void nuevoServicioConDomicilioInvalido()
	{
		Domicilio domicilio = new DomicilioCasa("9 de julio", 500);

		try
		{
			I_Contratable contratable = ContratableFactory.nuevoServicio("Internet100", 1, 1, 1, null );
	
			fail("Deberia lanzar una excepcion del tipo DomicilioInvalidoException");
		} catch (ServicioInternetInvalidoException e)
		{
			fail("Deberia lanzar una excepcion del tipo DomicilioInvalidoException");
		} catch (DomicilioInvalidoException e)
		{
			
		}
	}

}
