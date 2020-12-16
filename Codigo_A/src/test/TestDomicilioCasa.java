package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import servicios.DomicilioCasa;

public class TestDomicilioCasa {

	@Test
	public void constructor() {
		DomicilioCasa casa = new DomicilioCasa("San Martin",1000);
		String calle = casa.getCalle();
		int altura = casa.getAltura();
		Assert.assertEquals("La calle no se guardo correctamente", "San Martin", calle);
		Assert.assertEquals("La altura no se guardo correctamente", 1000, altura);
	}

}
