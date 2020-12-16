package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import servicios.DomicilioCasa;

public class TestDomicilioCasa
{

	@Test
	public void constructorCalleNoNull()
	{
		DomicilioCasa casa = new DomicilioCasa("Las Heras", -1);
		Assert.assertEquals("La calle no se guardo correctamente", "Las Heras", casa.getCalle());
		Assert.assertEquals("La altura no se guardo correctamente", -1, casa.getAltura());
	}

	@Test
	public void constructorCalleNull()
	{
		DomicilioCasa casa = new DomicilioCasa(null, 1);
		Assert.assertEquals("La calle no se guardo correctamente", null, casa.getCalle());
		Assert.assertEquals("La altura no se guardo correctamente", 1, casa.getAltura());
	}

	@Test
	public void setCalle()
	{
		DomicilioCasa casa = new DomicilioCasa("9 de julio", 1);
		casa.setCalle("Las Heras");
		Assert.assertEquals("La calle no se guardo correctamente", "Las Heras", casa.getCalle());

		casa.setCalle(null);
		Assert.assertEquals("La calle no se guardo correctamente", null, casa.getCalle());

	}

	@Test
	public void setAltura()
	{
		DomicilioCasa casa = new DomicilioCasa("9 de julio", 1);
		casa.setAltura(5000);
		Assert.assertEquals("La altura no se guardo correctamente", 5000, casa.getAltura());

	}

	@Test
	public void toStringCalleNull()
	{
		DomicilioCasa casa = new DomicilioCasa(null, 5000);
		Assert.assertEquals("La salida no coincide con la esperada", "Domicilio: null 5000", casa.toString());

	}

	@Test
	public void toStringCalleNoNull()
	{
		DomicilioCasa casa = new DomicilioCasa("Las Heras", 5000);
		Assert.assertEquals("La salida no coincide con la esperada", "Domicilio: Las Heras 5000", casa.toString());

	}

	@Test
	public void getDireccionCalleNull()
	{
		DomicilioCasa casa = new DomicilioCasa(null, 5000);
		Assert.assertEquals("La salida no coincide con la esperada", "null 5000", casa.getDireccion());

	}

	@Test
	public void getDirecionCalleNoNull()
	{
		DomicilioCasa casa = new DomicilioCasa("Las Heras", 5000);
		Assert.assertEquals("La salida no coincide con la esperada", "Las Heras 5000", casa.getDireccion());

	}
	
}
