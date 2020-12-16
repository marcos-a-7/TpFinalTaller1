package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import servicios.DomicilioDepto;

public class TestDomicilioDepto
{

	@Test
	public void constructorCaso1()
	{
		DomicilioDepto casa = new DomicilioDepto(null, -1, -1, null);
		Assert.assertEquals("La calle no se guardo correctamente", null, casa.getCalle());
		Assert.assertEquals("La altura no se guardo correctamente", -1, casa.getAltura());
		Assert.assertEquals("El piso no se guardo correctamente", -1, casa.getPiso());
		Assert.assertEquals("El departamento no se guardo correctamente", null, casa.getDepartamento());
	}

	@Test
	public void constructorCaso2()
	{
		DomicilioDepto casa = new DomicilioDepto("", 2, 2, "");
		Assert.assertEquals("La calle no se guardo correctamente", "", casa.getCalle());
		Assert.assertEquals("La altura no se guardo correctamente", 2, casa.getAltura());
		Assert.assertEquals("El piso no se guardo correctamente", 2, casa.getPiso());
		Assert.assertEquals("El departamento no se guardo correctamente", "", casa.getDepartamento());
	}

	@Test
	public void constructorCaso3()
	{
		DomicilioDepto casa = new DomicilioDepto("Las Heras", 200, 2, "C");
		Assert.assertEquals("La calle no se guardo correctamente", "Las Heras", casa.getCalle());
		Assert.assertEquals("La altura no se guardo correctamente", 200, casa.getAltura());
		Assert.assertEquals("El piso no se guardo correctamente", 2, casa.getPiso());
		Assert.assertEquals("El departamento no se guardo correctamente", "C", casa.getDepartamento());
	}

	@Test
	public void setPiso()
	{
		DomicilioDepto casa = new DomicilioDepto("Las Heras", 200, 7, "C");
		casa.setPiso(1);
		Assert.assertEquals("El piso no se guardo correctamente", 1, casa.getPiso());

	}

	@Test
	public void setDepartamento()
	{
		DomicilioDepto casa = new DomicilioDepto("Las Heras", 200, 7, "J");
		casa.setDepartamento("C");
		Assert.assertEquals("El Departamento no se guardo correctamente", "C", casa.getDepartamento());

		casa.setDepartamento(null);
		Assert.assertEquals("El Departamento no se guardo correctamente", null, casa.getDepartamento());

	}

	@Test
	public void toStringCalleNull()
	{
		DomicilioDepto casa = new DomicilioDepto("Las Heras", 5000, 1, null);
		Assert.assertEquals("La salida no coincide con la esperada",
				"Domicilio: Las Heras 5000 PISO: 1 DEPARTAMENTO: null", casa.toString());

	}

	@Test
	public void toStringCalleNoNull()
	{
		DomicilioDepto casa = new DomicilioDepto("Las Heras", 5000, 1, "C");
		Assert.assertEquals("La salida no coincide con la esperada",
				"Domicilio: Las Heras 5000 PISO: 1 DEPARTAMENTO: C ", casa.toString());

	}

}
