package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import agregado.Celular;
import agregado.Telefono;
import interfaces.I_Contratable;
import servicios.DomicilioCasa;
import servicios.Internet100;

public class TestTelefono
{

	@Test
	public void constructorConContratable()
	{
		DomicilioCasa domicilio = new DomicilioCasa("9 de julio", 500);
		I_Contratable contratable = new Internet100(domicilio);
		Telefono telefono = new Telefono(1, contratable);

		Assert.assertEquals("La cantidad de lineas no coincide con la ingresada", 1, telefono.getCantLineas());
		Assert.assertEquals("El contratable no coincide con el engresado", contratable, telefono.getContratable());
	}

	@Test
	public void constructorConContratableNull()
	{

		Telefono telefono = new Telefono(-5, null);

		Assert.assertEquals("La cantidad de lineas no coincide con la ingresada", -5, telefono.getCantLineas());
		Assert.assertEquals("El contratable no coincide con el engresado", null, telefono.getContratable());
	}

	@Test
	public void getDomicilioConContratableNoNull()
	{
		DomicilioCasa domicilio = new DomicilioCasa("9 de julio", 500);

		Telefono telefono = new Telefono(5, new Internet100(domicilio));
		Assert.assertEquals("El domicilio no coincide con el ingresado", domicilio, telefono.getDomicilio());

	}

	@Test
	public void getDomicilioConContratableNull()
	{
		Telefono telefono = new Telefono(5, null);
		Assert.assertEquals("El domicilio no coincide con el ingresado", null, telefono.getDomicilio());

	}

	@Test
	public void getIDConContratableNoNull()
	{
		Telefono telefono = new Telefono(5, new Internet100(new DomicilioCasa("9 de julio 500", 500), 1));
		Assert.assertEquals("El ID no coincide con el ingresado", 1, telefono.getID());
	}

	@Test
	public void getIDConContratableNull()
	{
		Telefono telefono = new Telefono(5, null);
		Assert.assertEquals("El ID no coincide con el ingresado", 1, telefono.getID());
	}

	@Test
	public void getPrecioConContratableNoNull()
	{
		Telefono telefono = new Telefono(2, new Internet100(new DomicilioCasa("9 de julio", 500), 1));
		Assert.assertEquals("El precio no coincide con la salida esperada", 1250, telefono.getPrecio(), 0);
	}

	@Test
	public void getPrecioConContratableNull()
	{
		Telefono telefono = new Telefono(2, null);
		Assert.assertEquals("El precio no coincide con la salida esperada", 400, telefono.getPrecio(), 0);
	}

	@Test
	public void setCantLineas()
	{
		Telefono telefono = new Telefono(12, null);
		
		telefono.setCantLineas(15);
		Assert.assertEquals("La cantidad de lineas no coincede con la esperada", 15, telefono.getCantLineas());
	}
	
	
	@Test
	public void toStringConContratableNoNull()
	{
		Telefono telefono = new Telefono(2, new Internet100(new DomicilioCasa("9 de julio", 500), 1));
		Assert.assertEquals("El string no coincide con la salida esperada",
				"Domicilio: 9 de julio 500 SERVICIO== INTERNET100: $850  + TELEFONO: $200 x 2", telefono.toString());
	}

	@Test
	public void toStringConContratableNull()
	{
		Telefono telefono = new Telefono(5, null);
		Assert.assertEquals("El string no coincide con la salida esperada", "TELEFONO: $200 x 2", telefono.toString());
	}

	@Test
	public void cloneConContratableNull()
	{

		Telefono telefono = new Telefono(5, null);
		Telefono telefonoClone = (Telefono) telefono.clone();

		Assert.assertEquals("El contratable no coincide con el del objeto clonado", telefonoClone.getContratable(),
				telefono.getContratable());
		Assert.assertEquals("La cantidad de lineas no coincide con la del objeto clonado",
				telefonoClone.getCantLineas(), telefono.getCantLineas());

	}

	@Test
	public void cloneConContratableNoNull()
	{

		Telefono telefono = new Telefono(5, new Internet100(new DomicilioCasa("9 de julio", 500)));
		Telefono telefonoClone = (Telefono) telefono.clone();

		Assert.assertEquals("El contratable no coincide con el del objeto clonado", telefonoClone.getContratable(),
				telefono.getContratable());
		Assert.assertEquals("El domicilio no coincide con el del objeto clonado", telefonoClone.getDomicilio(),
				telefono.getDomicilio());
		Assert.assertEquals("La cantidad de lineas no coincide con la del objeto clonado",
				telefonoClone.getCantLineas(), telefono.getCantLineas());

	}

}
