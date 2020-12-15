package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import mediospagos.PagoEfectivo;

public class TestPagoEfectivo {

	@Test
	public void porcentajeFisica() {
		PagoEfectivo pagoEfectivo = new PagoEfectivo();
		double porcentajeFisica = pagoEfectivo.porcentajeFisica();
		Assert.assertEquals(0.8, porcentajeFisica, 0);
	}
	
	@Test
	public void porcentajeJuridica() {
		PagoEfectivo pagoEfectivo = new PagoEfectivo();
		double porcentajeJuridica = pagoEfectivo.porcentajeJuridica();
		Assert.assertEquals(0.9, porcentajeJuridica, 0);
	}

}
