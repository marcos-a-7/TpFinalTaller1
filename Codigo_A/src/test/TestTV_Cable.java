package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import agregado.Celular;
import agregado.TV_Cable;
import interfaces.I_Contratable;
import servicios.DomicilioCasa;
import servicios.Internet100;

public class TestTV_Cable
{

	@Test
	public void constructorConContratable()
	{
		DomicilioCasa domicilio = new DomicilioCasa("9 de julio", 500);
		I_Contratable contratable = new Internet100(domicilio);
		TV_Cable cable = new TV_Cable(1, contratable);

		Assert.assertEquals("La cantidad de lineas no coincide con la ingresada", 1, cable.getCantLineas());
		Assert.assertEquals("El contratable no coincide con el engresado", contratable, cable.getContratable());
	}

	@Test
	public void constructorConContratableNull()
	{

		TV_Cable cable = new TV_Cable(-5, null);

		Assert.assertEquals("La cantidad de lineas no coincide con la ingresada", -5, cable.getCantLineas());
		Assert.assertEquals("El contratable no coincide con el engresado", null, cable.getContratable());
	}

	@Test
	public void getDomicilioConContratableNoNull()
	{
		DomicilioCasa domicilio = new DomicilioCasa("9 de julio", 500);

		TV_Cable cable = new TV_Cable(5, new Internet100(domicilio));
		Assert.assertEquals("El domicilio no coincide con el ingresado", domicilio, cable.getDomicilio());

	}

	@Test
	public void getDomicilioConContratableNull()
	{
		TV_Cable cable = new TV_Cable(5, null);
		Assert.assertEquals("El domicilio no coincide con el ingresado", null, cable.getDomicilio());

	}

	@Test
	public void getIDConContratableNoNull()
	{
		TV_Cable cable = new TV_Cable(5, new Internet100(new DomicilioCasa("9 de julio 500", 500), 1));
		Assert.assertEquals("El ID no coincide con el ingresado", 1, cable.getID());
	}

	@Test
	public void getIDConContratableNull()
	{
		TV_Cable cable = new TV_Cable(5, null);
		Assert.assertEquals("El ID no coincide con el ingresado", 1, cable.getID());
	}

	@Test
	public void getPrecioConContratableNoNull()
	{
		TV_Cable cable = new TV_Cable(2, new Internet100(new DomicilioCasa("9 de julio", 500), 1));
		Assert.assertEquals("El precio no coincide con la salida esperada", 1350, cable.getPrecio(), 0);
	}

	@Test
	public void getPrecioConContratableNull()
	{
		TV_Cable cable = new TV_Cable(2, null);
		Assert.assertEquals("El precio no coincide con la salida esperada", 500, cable.getPrecio(), 0);
	}

	@Test
	public void toStringConContratableNoNull()
	{
		TV_Cable cable = new TV_Cable(2, new Internet100(new DomicilioCasa("9 de julio", 500), 1));
		Assert.assertEquals("El string no coincide con la salida esperada",
				"Domicilio: 9 de julio 500 SERVICIO== INTERNET100: $850  + TV_CABLE: $250 x 2", cable.toString());
	}

	@Test
	public void toStringConContratableNull()
	{
		TV_Cable cable = new TV_Cable(5, null);
		Assert.assertEquals("El string no coincide con la salida esperada", "TV_CABLE: $250 x 2", cable.toString());
	}

	@Test
	public void cloneConContratableNull()
	{

		TV_Cable cable = new TV_Cable(5, null);
		TV_Cable cableClone = (TV_Cable) cable.clone();

		Assert.assertEquals("El contratable no coincide con el del objeto clonado", cableClone.getContratable(),
				cable.getContratable());
		Assert.assertEquals("La cantidad de lineas no coincide con la del objeto clonado", cableClone.getCantLineas(),
				cable.getCantLineas());

	}

	@Test
	public void cloneConContratableNoNull()
	{

		TV_Cable cable = new TV_Cable(5, new Internet100(new DomicilioCasa("9 de julio", 500)));
		TV_Cable cableClone = (TV_Cable) cable.clone();

		Assert.assertEquals("El contratable no coincide con el del objeto clonado", cableClone.getContratable(),
				cable.getContratable());
		Assert.assertEquals("El domicilio no coincide con el del objeto clonado", cableClone.getDomicilio(),
				cable.getDomicilio());
		Assert.assertEquals("La cantidad de lineas no coincide con la del objeto clonado", cableClone.getCantLineas(),
				cable.getCantLineas());

	}

}
