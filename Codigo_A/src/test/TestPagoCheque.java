package test;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import mediospagos.PagoCheque;


public class TestPagoCheque {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPorcentajeFisica() {
		PagoCheque pagoCheque = new PagoCheque();
		double porcentajeFisica = pagoCheque.porcentajeFisica();
		Assert.assertEquals(1.10, porcentajeFisica);
	}
	
	@Test
	public void porcentajeJuridica() {
		PagoCheque pagoCheque = new PagoCheque();
	}

}
