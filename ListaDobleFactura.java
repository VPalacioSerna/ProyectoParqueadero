
package proyectoparqueadero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ListaDobleFactura {

	// Atributos
	Nodo cabeza;
	Nodo cola;
	private static int contador = 0;
	protected static Scanner sc = new Scanner(System.in);

	// Constructor
	public ListaDobleFactura() {
		this.cabeza = null;
		this.cola = null;
	}

	// Métodos
	public void agregarFacturaLista(Factura factura) {
		Nodo auxiliar = new Nodo(factura);
		if (vacio()) {
			cabeza = auxiliar;
			cola = auxiliar;
			contador++;
		} else {
			cola.siguiente = auxiliar;
			auxiliar.anterior = cola;
			cola = auxiliar;
			contador++;
		}
		System.out.print("\nCon: " + contador);
	}

	public void cancelarFactura(String placa, int tipoVehiculo) {

		Nodo paraBuscar;

		/*
		 * 1. Nodos para cargar los datos y ya. 2. Buscar la estructurta de datos que
		 * permita recorrer solo con la cabeza, es decir; solo con un puntero. Desde la
		 * cabeza o desde la cola 3. Mirar cual es la estructura de Nodos que genera el
		 * error, porque no todas las veces sucede es decir identificar como sucede el
		 * error. 4. Verificar cuando se cargue la informacion de las archivos .dat, con
		 * respecto a la generacion de facturas y guardado de ellas en la lista
		 */

		switch (tipoVehiculo) {
		case 1:
			paraBuscar = cabeza;
			while (paraBuscar != null) {
				if (vacio()) {
					System.out.println("\nNo hay facturas para cancelar\n");
				} else {
					if (paraBuscar.factura != null) {
						if (cabeza.factura.carro != null && cabeza.factura.carro.getPlaca().equalsIgnoreCase(placa)
								&& contador != 1) {
							cabeza = cabeza.siguiente;
							contador--;
							break;
						} else if (cola.factura.carro != null && cola.factura.carro.getPlaca().equalsIgnoreCase(placa)
								&& contador != 1) {
							cola = cola.anterior;
							contador--;
							break;
						} else if (paraBuscar.factura.carro != null
								&& paraBuscar.factura.carro.getPlaca().equalsIgnoreCase(placa) && contador != 1) {
							paraBuscar.anterior.siguiente = paraBuscar.siguiente;
							paraBuscar.siguiente.anterior = paraBuscar.anterior;
							contador--;
							break;
						} else if (contador == 1) {
							cabeza = null;
							cola = null;
							contador = 0;
							break;
						}
					}
					paraBuscar = paraBuscar.siguiente;
				}
			}
			break;
		case 2:
			paraBuscar = cabeza;
			while (paraBuscar != null) {
				if (vacio()) {
					System.out.println("\nNo hay facturas para cancelar\n");
				} else {
					if (paraBuscar.factura != null) {
						if (cabeza.factura.moto != null && cabeza.factura.moto.getPlaca().equalsIgnoreCase(placa)
								&& contador != 1) {
							cabeza = cabeza.siguiente;
							cabeza.anterior = null;
							contador--;
							break;
						} else if (cola.factura.moto != null && cola.factura.moto.getPlaca().equalsIgnoreCase(placa)
								&& contador != 1) {
							cola = cola.anterior;
							cola.siguiente = null;
							contador--;
							break;
						} else if (paraBuscar.factura.moto != null
								&& paraBuscar.factura.moto.getPlaca().equalsIgnoreCase(placa) && contador != 1) {
							paraBuscar.anterior.siguiente = paraBuscar.siguiente;
							paraBuscar.siguiente.anterior = paraBuscar.anterior;
							contador--;
							break;
						} else if (contador == 1) {
							cabeza = null;
							cola = null;
							contador = 0;
							break;
						}
					}
					paraBuscar = paraBuscar.siguiente;
				}
			}
			break;
		}

	}

	public void actualizarFactura(Factura factura) {
		Nodo paraBuscar = cabeza;
		Nodo paraActualizar = new Nodo(factura);

		while (paraBuscar != null) {
			if (vacio()) {
				System.out.println("\nNo hay facturas para actualizar ...");
			} else {
				if (paraBuscar.factura == paraActualizar.factura) {
					paraBuscar = paraActualizar;
					break;
				}
			}
			paraBuscar = paraBuscar.siguiente;
		}
	}

	public void guardarFacturasPendientesEnArchivo() {
		System.out.print("\n¿Qué tipo vehiculo desea agregar (Carro(1) - Moto(2))?: ");
		int tipoVehiculo = sc.nextInt();
		Nodo paraBuscar = cabeza;
		String direccionArchivoPlano = "";
		String lineaDeInfo = "";
		String contenidoArchivo = "";
		FileWriter paraTraerArchivo;
		BufferedWriter escritor;

		switch (tipoVehiculo) {
		case 1:
			direccionArchivoPlano = "FacturasPendientesCarros.dat";
			try {
				paraTraerArchivo = new FileWriter(direccionArchivoPlano, true);
				escritor = new BufferedWriter(paraTraerArchivo);
				while (paraBuscar != null) {
					if (paraBuscar.factura.carro != null) {
						String infoCarro = paraBuscar.factura.carro.toStringCarro();
						// String numeroEstacionamiento =
						// paraBuscar.factura.carro.getNumeroEstacionamiento();
						double valor = paraBuscar.factura.getValor();
						lineaDeInfo = infoCarro + "," + valor + "\n";
						contenidoArchivo = contenidoArchivo + lineaDeInfo;
					}
					paraBuscar = paraBuscar.siguiente;
				}
				escritor.write(contenidoArchivo);
				System.out.print("\nInformación guardada correctamente.");
				escritor.close();
				paraTraerArchivo.close();
			} catch (IOException e) {
				System.out.print("\nError al guardar los vehiculos");
			}
			break;

		case 2:
			direccionArchivoPlano = "FacturasPendientesMotos.dat";
			try {
				paraTraerArchivo = new FileWriter(direccionArchivoPlano, true);
				escritor = new BufferedWriter(paraTraerArchivo);
				while (paraBuscar != null) {
					if (paraBuscar.factura.moto != null) {
						String infoMoto = paraBuscar.factura.moto.toStringMoto();
						// String numeroEstacionamiento =
						// paraBuscar.factura.moto.getNumeroEstacionamiento();
						double valor = paraBuscar.factura.getValor();
						lineaDeInfo = infoMoto + "," + valor + "\n";
						contenidoArchivo = contenidoArchivo + lineaDeInfo;
					}
					paraBuscar = paraBuscar.siguiente;
				}
				escritor.write(contenidoArchivo);
				System.out.print("\nInformación guardada correctamente.");
				escritor.close();
				paraTraerArchivo.close();
			} catch (IOException e) {
				System.out.print("\nError al guardar los vehiculos");
			}
			break;
		}
	}

	public static void cargarFacturasPendientes() {
		System.out.print("\n¿Qué tipo vehiculo desea agregar (Carro(1) - Moto(2))?: ");
		int tipoVehiculo = sc.nextInt();
		String direccionArchivoPlano = "";
		String lineaDeInfo = "";
		String contenidoArchivo = "";
		FileReader paraTraerArchivo;
		BufferedReader lector;

		ListaDobleFactura ldf = new ListaDobleFactura();

		switch (tipoVehiculo) {
		case 1:
			direccionArchivoPlano = "FacturasPendientesCarros.dat";
			try {
				paraTraerArchivo = new FileReader(direccionArchivoPlano);
				lector = new BufferedReader(paraTraerArchivo);
				String lineaDeInformacion = lector.readLine();
				while (lineaDeInformacion != null) {
					String contenido[] = lineaDeInformacion.split(",");

					Carro carro = new Carro(contenido[0], contenido[1], contenido[2], Integer.parseInt(contenido[3]),
							Integer.parseInt(contenido[4]), contenido[5]);

					String numeroEstacionamiento = contenido[6];
					carro.setNumeroEstacionamiento(numeroEstacionamiento);
					carro.espaciosCarro[Integer.parseInt(numeroEstacionamiento)] = carro;
					carro.espaciosPisoCarro[Integer.parseInt(numeroEstacionamiento)] = true;

					double valor = Integer.parseInt(contenido[7]);
					int hora = Integer.parseInt(contenido[3]);

					Factura factura = new Factura(carro, valor, hora);

					ldf.agregarFacturaLista(factura);
					lineaDeInformacion = lector.readLine();
				}
				System.out.print("\nInformación recuperada correctamente.");
				lector.close();
				paraTraerArchivo.close();
			} catch (IOException e) {
				System.out.print("\nError al guardar los vehiculos");
			}
			break;

		case 2:
			direccionArchivoPlano = "FacturasPendientesMotos.dat";
			try {
				paraTraerArchivo = new FileReader(direccionArchivoPlano);
				lector = new BufferedReader(paraTraerArchivo);
				String lineaDeInformacion = lector.readLine();
				while (lineaDeInformacion != null) {
					String contenido[] = lineaDeInformacion.split(",");

					Moto moto = new Moto(contenido[0], contenido[1], contenido[2], Integer.parseInt(contenido[3]),
							Integer.parseInt(contenido[4]));

					String numeroEstacionamiento = contenido[5];
					moto.setNumeroEstacionamiento(numeroEstacionamiento);
					moto.espaciosMoto[Integer.parseInt(numeroEstacionamiento)] = moto;
					moto.espaciosPisoMoto[Integer.parseInt(numeroEstacionamiento)] = true;

					double valor = Integer.parseInt(contenido[6]);
					double hora = Integer.parseInt(contenido[3]);

					Factura factura = new Factura(moto, hora, valor);

					ldf.agregarFacturaLista(factura);
					lineaDeInformacion = lector.readLine();
				}
				System.out.print("\nInformación recuperada correctamente.");
				lector.close();
				paraTraerArchivo.close();
			} catch (IOException e) {
				System.out.print("\nError al guardar los vehiculos");
			}
			break;
		}

	}

	public static void guardarFacturasCanceladas() {
		String direccionArchivoPlano = "Facturascanceladas.dat";
		String lineaDeInfo = "";
		String contenidoArchivo = "";
		try {
			FileWriter paraTraerArchivo = new FileWriter(direccionArchivoPlano, true);
			BufferedWriter escritor = new BufferedWriter(paraTraerArchivo);
			for (int i = 0; i < Factura.facturasCanceladas.size(); i++) {
				String info = Factura.facturasCanceladas.get(i).vehiculo.toString();
				double valor = Factura.facturasCanceladas.get(i).getValor();
				lineaDeInfo = info + "," + valor + "\n";
				contenidoArchivo = contenidoArchivo + lineaDeInfo;
			}
			escritor.write(contenidoArchivo);
			System.out.print("\nInformación guardada correctamente.");
			escritor.close();
			paraTraerArchivo.close();
		} catch (IOException e) {
			System.out.print("\nError al guardar los vehiculos");
		}
	}

	public static void cargarFacturasCanceladas() {
		String direccionArchivoPlano = "Facturascanceladas.dat";
		try {
			FileReader paraTraerArchivo = new FileReader(direccionArchivoPlano);
			BufferedReader lector = new BufferedReader(paraTraerArchivo);
			String lineaDeInformacion = lector.readLine();
			while (lineaDeInformacion != null) {
				String contenido[] = lineaDeInformacion.split(",");

				Vehiculo vehiculo = new Vehiculo(contenido[0], contenido[1], contenido[2], contenido[3]);
				double valor = Double.parseDouble(contenido[4]);

				Factura factura = new Factura(vehiculo, valor);
				Factura.facturasCanceladas.add(factura);
				lineaDeInformacion = lector.readLine();
			}
			System.out.print("\nInformación recuperada correctamente.");
			lector.close();
			paraTraerArchivo.close();
		} catch (IOException e) {
			System.out.print("\nError de lectura");
		}
	}

	public static boolean vacio() {
		return contador == 0;
	}

	public void mostrarFacturasPendientes() {
		if (vacio()) {
			System.out.print("\nNo hay Facturas pendientes");
		} else {
			System.out.print("\nFacturas pendientes (Carros y motos)\n");

			// Mostrar carros
			System.out.print("\nCarros\n");
			Nodo nodoActual = cabeza;
			while (nodoActual != null) {
				if (nodoActual.factura.carro != null) {
					System.out.print("Placa: " + nodoActual.factura.carro.getPlaca() + "\n" + "Número estacionamiento: "
							+ nodoActual.factura.carro.getNumeroEstacionamiento() + "\n" + "Valor (momento consulta): "
							+ nodoActual.factura.getValor() + "\n");
				}
				nodoActual = nodoActual.siguiente;
			}

			// Mostrar motos
			System.out.print("\nMotos\n");
			nodoActual = cabeza;
			while (nodoActual != null) {
				if (nodoActual.factura.moto != null) {
					System.out.print("Placa: " + nodoActual.factura.moto.getPlaca() + "\n" + "Número estacionamiento: "
							+ nodoActual.factura.moto.getNumeroEstacionamiento() + "\n" + "Valor (momento consulta): "
							+ nodoActual.factura.getValor() + "\n");
				}
				nodoActual = nodoActual.siguiente;
			}
		}

		System.out.println("\n");
	}
}
