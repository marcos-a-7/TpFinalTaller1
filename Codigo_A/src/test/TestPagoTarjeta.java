package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import mediospagos.PagoTarjeta;

public class TestPagoTarjeta {

	@Test
	public void porcentajeFisica() {
		PagoTarjeta pagoTarjeta = new PagoTarjeta();
		double porcentajeFisica = pagoTarjeta.porcentajeFisica();
		Assert.assertEquals(1, porcentajeFisica, 0.01);
	}
	
	@Test
	public void porcentajeJuridica() {
		PagoTarjeta pagoTarjeta = new PagoTarjeta();
		double porcentajeJuridica = pagoTarjeta.porcentajeJuridica();
		Assert.assertEquals(1.20, porcentajeJuridica, 0.01);
	}

}
