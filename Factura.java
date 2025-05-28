package proyectoparqueadero;

import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

public class Factura {

	// Atributos
	protected Carro carro;
	protected Moto moto;
	protected Vehiculo vehiculo;
	private static double valor;
	protected static ListaDobleFactura ldf = new ListaDobleFactura();
	public static Scanner sc = new Scanner(System.in);
	protected static ArrayList<Factura> facturasCanceladas = new ArrayList<>();

	// Constructores
	public Factura() {
	}

	public Factura(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Factura(Vehiculo vehiculo, double valor) {
		super();
		this.vehiculo = vehiculo;
		this.valor = valor;
	}

	public Factura(Carro carro, double valor, double hora) {
		super();
		this.carro = carro;
		this.valor = valor;
	}

	public Factura(Moto moto, double valor, double hora) {
		super();
		this.moto = moto;
		this.valor = valor;
	}

	public static double obtenerValor(int tipoVehiculo, int horaInicial) {

		double valor = 0;

		switch (tipoVehiculo) {
		case 1:
			// Carro --- Fraccion 4000
			System.out.println("Tiempo Inicial: " + horaInicial);
			System.out.println("Tiempo final: " + Tiempo.obtenerHoraSalida());

			valor = 4000 * (((Tiempo.obtenerHoraSalida() - horaInicial) * 30) / 60);
			break;
		case 2:
			// Moto --- Fraccion 2500
			valor = 2500 * (((Tiempo.obtenerHoraSalida() - horaInicial) * 30) / 60);
			break;
		}
		return valor;
	}

	public double getValor() {
		return valor;
	}

	public static void generarFactura() {
		System.out.print("\n¿Qué tipo vehiculo desea agregar (Carro(1) - Moto(2))?: ");
		int tipoVehiculo = sc.nextInt();
		double valor = 0;
		double hora = Tiempo.hora();
		Factura factura = new Factura();

		switch (tipoVehiculo) {
		case 1:
			Carro carroAuxiliar = Carro.agregarCarro();
			factura = new Factura(carroAuxiliar, valor, hora);
			ldf.agregarFacturaLista(factura);
			break;

		case 2:
			Moto motoAuxiliar = Moto.agregarMoto();
			factura = new Factura(motoAuxiliar, valor, hora);
			ldf.agregarFacturaLista(factura);
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
			Carro carroAuxiliar = Carro.quitarVehiculoCarro(sc.next().toUpperCase());
			ldf.cancelarFactura(carroAuxiliar.getPlaca(), tipoVehiculo);
			break;

		case 2:
			System.out.print("\nIngrese placa: ");
			Moto motoAuxiliar = Moto.quitarVehiculoMoto(sc.next().toUpperCase());
			ldf.cancelarFactura(motoAuxiliar.getPlaca(), tipoVehiculo);
			break;
		}
		System.out.print("\nFactura cancelada correctamente !\n");
	}

	public static void actualizarInformacion() {
		System.out.print("\n¿Qué tipo vehiculo desea actualizar (Carro(1) - Moto(2))?: ");
		int tipoVehiculo = sc.nextInt();
		double valor = 0;
		double hora = 0;
		Factura factura = new Factura();

		switch (tipoVehiculo) {
		case 1:
			System.out.print("\nIngrese placa: ");
			Carro carroAuxiliar = Carro.modificarInformacionCarro(sc.next().toUpperCase());
			valor = Factura.obtenerValor(tipoVehiculo, carroAuxiliar.getTiempoInicial());
			hora = Tiempo.hora();

			factura = new Factura(carroAuxiliar, valor, hora);

			ldf.actualizarFactura(factura);
			break;

		case 2:
			System.out.print("\nIngrese placa: ");
			Moto motoAuxiliar = Moto.modificarInformacionMoto(sc.next().toUpperCase());
			valor = Factura.obtenerValor(tipoVehiculo, motoAuxiliar.getTiempoInicial());
			hora = Tiempo.hora();

			factura = new Factura(motoAuxiliar, valor, hora);

			ldf.actualizarFactura(factura);
			break;
		}
	}

	public static void mostrarFacturasCanceladas() {
		if (Factura.facturasCanceladas.isEmpty()) {
			System.out.print("\nNo hay facturas canceladas\n");
		} else {
			System.out.print("\nFacturas Canceladas (Carros y motos)\n");
			for (int i = 0; i < Factura.facturasCanceladas.size(); i++) {
				System.out.print("Placa: " + Factura.facturasCanceladas.get(i).vehiculo.getPlaca() + "\n" // Tiene un
																											// error,
																											// apunta a
																											// nulo
						+ "Lugar donde se había estacionado: "
						+ Factura.facturasCanceladas.get(i).vehiculo.getNumeroEstacionamiento() + "\n"
						+ "Valor que pagó: " + Factura.facturasCanceladas.get(i).getValor());
			}
		}
	}

	public static void mostrarFacturasPendientes() {
		ldf.mostrarFacturasPendientes();
	}

	public static void guardarInformacionFacturasPendientes() {
		ListaDobleFactura ldf = new ListaDobleFactura();
		ldf.guardarFacturasPendientesEnArchivo();
	}
}
