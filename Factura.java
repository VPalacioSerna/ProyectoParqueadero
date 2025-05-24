package proyectoparqueadero;

import java.util.Date;

public class Factura {

  //Atributos
	protected static Carro carro;
	protected static Moto moto;
	protected static Vehiculo vehiculo;
	private static double valor;
	public double hora;
	public static Scanner sc = new Scanner(System.in);
	static ArrayList<Factura> facturasCanceladas = new ArrayList<>();
	
	//Constructores
	public Factura() {}
	
	public Factura(Vehiculo vehiculo, double valor) {
		super();
		this.vehiculo = vehiculo;
		this.valor = valor;
	}

	public Factura(Carro carro, double valor, double hora) {
		super();
		this.carro = carro;
		this.valor = valor;
		this.hora = hora;
	}
	
	public Factura(Moto moto, double valor, double hora) {
		super();
		this.moto = moto;
		this.valor = valor;
		this.hora = hora;
	}
	
	//Métodos
	public static double obtenerHora() {
		return 0;
	}
	
	public static double obtenerValor(int tipoVehiculo, double horaInicial) {
		
		double valor=0;
	
		switch (tipoVehiculo) {
			case 1:
				//Carro --- Fraccion 4000
				valor = 4000*Factura.obtenerHora();

			case 2:
				//Moto --- Fraccion 2500
				valor = 2500*Factura.obtenerHora();
		}
		return valor;
	}
	
	public static double getValor() {
		return valor;
	}
	
	
	
	public static void generarFactura() {
		System.out.print("\n¿Qué tipo vehiculo desea agregar (Carro(1) - Moto(2))?: ");
		int tipoVehiculo = sc.nextInt();
		double valor = 0;
		double hora = Factura.obtenerHora();
		Factura factura = new Factura();
		
		switch (tipoVehiculo) {
		case 1:
			carro = Carro.agregarCarro();
			factura = new Factura(carro, valor, hora);
			ListaDobleFactura.agregarFacturaLista(factura);
			break;

		case 2:
			moto = Moto.agregarMoto();
			factura = new Factura(moto, valor, hora);
			ListaDobleFactura.agregarFacturaLista(factura);
			break;
		}
		System.out.print("\nVehiculo registrado exitosamente\n");
	}

	public static void pagarFactura() {
		System.out.print("\n¿Qué tipo vehiculo saldrá (Carro(1) - Moto(2))?: ");
		int tipoVehiculo = sc.nextInt();
		
		switch (tipoVehiculo) {
		case 1:
			System.out.print("\nIngrese placa: ");
			carro = Carro.quitarVehiculoCarro(sc.next().toUpperCase());
			ListaDobleFactura.cancelarFactura(carro.getFactura());
			break;

		case 2:
			System.out.print("\nIngrese placa: ");
			moto = Moto.quitarVehiculoMoto(sc.next().toUpperCase());
			ListaDobleFactura.cancelarFactura(moto.getFactura());
			break;
		}
		System.out.print("\nFactura cancelada correctamente !\n");
	}

	public static void actualizarInformacion() {
		System.out.print("\n¿Qué tipo vehiculo desea actualizar (Carro(1) - Moto(2))?: ");
		int tipoVehiculo = sc.nextInt();
		double valor=0;
		double hora=0;
		Factura factura = new Factura();
		
		switch (tipoVehiculo) {
		case 1:
			System.out.print("\nIngrese placa: ");
			carro = Carro.modificarInformacionCarro(sc.next().toUpperCase());
			valor = Factura.obtenerValor(tipoVehiculo, 0);
			hora = Factura.obtenerHora();
			
			factura = new Factura(carro, valor, hora);
			
			ListaDobleFactura.actualizarFactura(factura);
			break;

		case 2:
			System.out.print("\nIngrese placa: ");
			moto = Moto.modificarInformacionMoto(sc.next().toUpperCase());
			valor = Factura.obtenerValor(tipoVehiculo, 0);
			hora = Factura.obtenerHora();
			
			factura = new Factura(moto, valor, hora);
			
			ListaDobleFactura.actualizarFactura(factura);
			break;
		}
	}

	public static void mostrarFacturasCanceladas() {
		if(Factura.facturasCanceladas.isEmpty()) {
			System.out.print("\nNo hay facturas canceladas\n");
		}else {
			System.out.print("\nFacturas Canceladas (Carros y motos)\n");
			for (int i=0; i<Factura.facturasCanceladas.size(); i++) {
					System.out.print("Placa: "+Factura.facturasCanceladas.get(i).vehiculo.getPlaca()+"\n" //Tiene un error, apunta a nulo
							+"Lugar donde se había estacionado: "+Factura.facturasCanceladas.get(i).vehiculo.getNumeroEstacionamiento()+"\n"
							+"Valor que pagó: "+Factura.facturasCanceladas.get(i).getValor());
				}
			}
		}
}
