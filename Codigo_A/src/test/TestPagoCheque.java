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
		assert porcentajeFisica == 1.10 : "El porcentaje para la persona es incorrecto";
	}
	
	@Test
	public void porcentajeJuridica() {
		PagoCheque pagoCheque = new PagoCheque();
		double porcentajeJuridica = pagoCheque.porcentajeJuridica();
		assert porcentajeJuridica == 1.15 : "El porcentaje para la persona es incorrecto";
	}
}
