package test;


import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import mediospagos.PagoCheque;


public class TestPagoCheque {
	
	@Test
	public void testPorcentajeFisica() {
		PagoCheque pagoCheque = new PagoCheque();
		double porcentajeFisica = pagoCheque.porcentajeFisica();
		Assert.assertEquals(1.10, porcentajeFisica, 0);
	}
	
	@Test
	public void porcentajeJuridica() {
		PagoCheque pagoCheque = new PagoCheque();
		double porcentajeJuridica = pagoCheque.porcentajeJuridica();
		Assert.assertEquals(1.15, porcentajeJuridica, 0);
	}
}
