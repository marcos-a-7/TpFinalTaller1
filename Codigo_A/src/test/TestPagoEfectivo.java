package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import mediospagos.PagoEfectivo;

public class TestPagoEfectivo {

	@Test
	public void testPorcentajeFisica() {
		PagoEfectivo pagoEfectivo = new PagoEfectivo();
		double porcentajeFisica = pagoEfectivo.porcentajeFisica();
		Assert.assertEquals("El porcentaje para la persona fisica es correcto", 0.8, porcentajeFisica);
	}
	
	@Test
	public void porcentajeJuridica() {
		PagoEfectivo pagoEfectivo = new PagoEfectivo();
		double porcentajeJuridica = pagoEfectivo.porcentajeJuridica();
		Assert.assertEquals("El porcentaje para la persona juridica es correcto", 0.9, porcentajeJuridica);
	}

}
