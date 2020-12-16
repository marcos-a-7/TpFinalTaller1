package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import agregado.Celular;
import interfaces.I_Contratable;
import servicios.DomicilioCasa;
import servicios.Internet100;

public class TestCelular
{

	@Test
	public void constructorConContratable()
	{
		DomicilioCasa domicilio = new DomicilioCasa("9 de julio", 500);
		I_Contratable contratable = new Internet100(domicilio);
		Celular celular = new Celular(1, contratable);

		Assert.assertEquals("La cantidad de lineas no coincide con la ingresada", 1, celular.getCantLineas());
		Assert.assertEquals("El contratable no coincide con el engresado", contratable, celular.getContratable());
	}

	@Test
	public void constructorConContratableNull()
	{

		Celular celular = new Celular(-5, null);

		Assert.assertEquals("La cantidad de lineas no coincide con la ingresada", -5, celular.getCantLineas());
		Assert.assertEquals("El contratable no coincide con el engresado", null, celular.getContratable());
	}

	@Test
	public void getDomicilioConContratableNoNull()
	{
		DomicilioCasa domicilio = new DomicilioCasa("9 de julio", 500);

		Celular celular = new Celular(5, new Internet100(domicilio));
		Assert.assertEquals("El domicilio no coincide con el ingresado", domicilio, celular.getDomicilio());

	}

	@Test
	public void getDomicilioConContratableNull()
	{
		Celular celular = new Celular(5, null);
		Assert.assertEquals("El domicilio no coincide con el ingresado", null, celular.getDomicilio());

	}

	@Test
	public void getIDConContratableNoNull()
	{
		Celular celular = new Celular(5, new Internet100(new DomicilioCasa("9 de julio 500", 500), 1));
		Assert.assertEquals("El ID no coincide con el ingresado", 1, celular.getID());
	}

	@Test
	public void getIDConContratableNull()
	{
		Celular celular = new Celular(5, null);
		Assert.assertEquals("El ID no coincide con el ingresado", 1, celular.getID());
	}

	@Test
	public void getPrecioConContratableNoNull()
	{
		Celular celular = new Celular(2, new Internet100(new DomicilioCasa("9 de julio", 500), 1));
		Assert.assertEquals("El precio no coincide con la salida esperada", 1450, celular.getPrecio(), 0);
	}

	@Test
	public void getPrecioConContratableNull()
	{
		Celular celular = new Celular(2, null);
		Assert.assertEquals("El precio no coincide con la salida esperada", 600, celular.getPrecio(), 0);
	}
	
	@Test
	public void toStringConContratableNoNull()
	{
		Celular celular = new Celular(2, new Internet100(new DomicilioCasa("9 de julio", 500), 1));
		Assert.assertEquals("El string no coincide con la salida esperada", "Domicilio: 9 de julio 500 SERVICIO== INTERNET100: $850  + CELULAR: $300 x 2", celular.toString());
	}

	@Test
	public void toStringConContratableNull()
	{
		Celular celular = new Celular(5, null);
		Assert.assertEquals("El string no coincide con la salida esperada", "CELULAR: $300 x 2", celular.toString());
	}

	@Test
	public void cloneConContratableNull()
	{

		Celular celular = new Celular(5, null);
		Celular celularClone = (Celular) celular.clone();

		Assert.assertEquals("El contratable no coincide con el del objeto clonado", celularClone.getContratable(),
				celular.getContratable());
		Assert.assertEquals("La cantidad de lineas no coincide con la del objeto clonado", celularClone.getCantLineas(),
				celular.getCantLineas());

	}

	@Test
	public void cloneConContratableNoNull()
	{

		Celular celular = new Celular(5, new Internet100(new DomicilioCasa("9 de julio", 500)));
		Celular celularClone = (Celular) celular.clone();

		Assert.assertEquals("El contratable no coincide con el del objeto clonado", celularClone.getContratable(),
				celular.getContratable());
		Assert.assertEquals("El domicilio no coincide con el del objeto clonado", celularClone.getDomicilio(),
				celular.getDomicilio());
		Assert.assertEquals("La cantidad de lineas no coincide con la del objeto clonado", celularClone.getCantLineas(),
				celular.getCantLineas());

	}

}
