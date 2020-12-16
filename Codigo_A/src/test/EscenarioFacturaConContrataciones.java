package test;

import personas.Fisica;
import servicios.DomicilioCasa;
import servicios.Factura;
import servicios.Internet100;
import servicios.Internet500;

public class EscenarioFacturaConContrataciones {

	private Fisica persona = new Fisica("Jorge",6);
	private Factura factura = new Factura(persona);
	private DomicilioCasa domicilio = new DomicilioCasa("calle6",105);
	private Internet100 internet100 = new Internet100(domicilio);
	{
		factura.nuevaContratacion(new Internet500(new DomicilioCasa("calle4",103)));
		factura.nuevaContratacion(new Internet100(new DomicilioCasa("calle5",104)));
		factura.nuevaContratacion(internet100);
	}
	
	public Factura getFactura() {
		return factura;
	}
	
	public Internet100 getServicio() {
		return internet100;
	}
	
	public DomicilioCasa getDomicilio() {
		return domicilio;
	}
	
	
	
}
