package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import agregado.ContratableFactory;
import agregado.DecoratorAgregado;
import excepciones.DomicilioInvalidoException;
import excepciones.ServicioInternetInvalidoException;
import interfaces.I_Contratable;
import servicios.Domicilio;
import servicios.DomicilioCasa;

public class TestContratableFactory
{

	@Test
	public void nuevoServicioConServicios() 
	{
		Domicilio domicilio = new DomicilioCasa("9 de julio", 500);

		try
		{
			I_Contratable contratable = ContratableFactory.nuevoServicio("Internet100", 1, 1, 1, domicilio);
			int cantCelulares = 0, cantFijo = 0, cantTV = 0;
			Assert.assertEquals("El domicilio no coincide", domicilio, contratable.getDomicilio());
			while (contratable.isCelular() || contratable.isTelefono() || contratable.isTV_Cable())
			{
				DecoratorAgregado decorador = (DecoratorAgregado) contratable;
				if (contratable.isCelular())
				{
					cantCelulares = decorador.getCantLineas();
				} else if (contratable.isTelefono())
				{
					cantFijo = decorador.getCantLineas();
				} else if (contratable.isTV_Cable())
				{
					cantTV = decorador.getCantLineas();
				}
				contratable = decorador.getContratable();
			}
			if (cantCelulares != 1)
			{
				fail("La cantidad de celulares deberia ser 1");
			}
			if (cantFijo != 1)
			{
				fail("La cantidad de fijo deberia ser 1");
			}
			if (cantTV != 1)
			{
				fail("La cantidad de TV deberia ser 1");
			}
			if (!contratable.isInternet100())
			{
				fail("El contratable deberia ser un internet100");
			}

		} catch (ServicioInternetInvalidoException e)
		{
			fail("El internet no deberia ser invalido");
		} catch (DomicilioInvalidoException e)
		{
			fail("El domicilio no deberia ser invalido");
		}
	}

	@Test
	public void nuevoServicioSinAgregados()
	{
		Domicilio domicilio = new DomicilioCasa("9 de julio", 500);

		try
		{
			I_Contratable contratable = ContratableFactory.nuevoServicio("Internet500", -1, -1, -1, domicilio);
			Assert.assertEquals("El domicilio no coincide", domicilio, contratable.getDomicilio());
			if (!contratable.isInternet500())
			{
				fail("El contratable deberia ser un internet500");
			}

		} catch (ServicioInternetInvalidoException e)
		{
			fail("El internet no deberia ser invalido");
		} catch (DomicilioInvalidoException e)
		{
			fail("El domicilio no deberia ser invalido");
		}
	}

	@Test
	public void nuevoServicioSinAgregadosValorLimite() // completar
	{
		Domicilio domicilio = new DomicilioCasa("9 de julio", 500);
		try
		{
			I_Contratable contratable = ContratableFactory.nuevoServicio("Internet100", 0, 0, 0, domicilio);
			Assert.assertEquals("El domicilio no coincide", domicilio, contratable.getDomicilio());
			if (!contratable.isInternet100())
			{
				fail("El contratable deberia ser un internet100");
			}

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
		try
		{
			ContratableFactory.nuevoServicio("Internet100", 1, 1, 1, null);

			fail("Deberia lanzar una excepcion del tipo DomicilioInvalidoException");
		} catch (ServicioInternetInvalidoException e)
		{
			fail("Deberia lanzar una excepcion del tipo DomicilioInvalidoException");
		} catch (DomicilioInvalidoException e)
		{

		}
	}

	@Test
	public void nuevoServicioConInternetInvalido()
	{
		Domicilio domicilio = new DomicilioCasa("9 de julio", 500);
		try
		{
			ContratableFactory.nuevoServicio("Internet300", -1, -1, -1, domicilio);

			fail("Deberia lanzar una excepcion del tipo ServicioInternetInvalidoException");
		} catch (ServicioInternetInvalidoException e)
		{

		} catch (DomicilioInvalidoException e)
		{
			fail("Deberia lanzar una excepcion del tipo ServicioInternetInvalidoException");
		}
	}
}
