package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import mediospagos.PagoTarjeta;

public class TestPagoTarjeta {

	@Test
	public void testPorcentajeFisica() {
		PagoTarjeta pagoTarjeta = new PagoTarjeta();
		double porcentajeFisica = pagoTarjeta.porcentajeFisica();
		Assert.assertEquals("El porcentaje para la persona fisica es correcto", 1, porcentajeFisica);
	}
	
	@Test
	public void porcentajeJuridica() {
		PagoTarjeta pagoTarjeta = new PagoTarjeta();
		double porcentajeJuridica = pagoTarjeta.porcentajeJuridica();
		Assert.assertEquals("El porcentaje para la persona juridica es correcto", 1.20, porcentajeJuridica);
	}

}
