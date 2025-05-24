package proyectoparqueadero;

public class Nodo {

	Nodo siguiente;
	Nodo anterior;
	Factura factura = new Factura();
	
	public Nodo(Factura factura) {
		this.factura = factura;
	}
}
