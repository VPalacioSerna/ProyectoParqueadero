package proyectoparqueadero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ListaDobleFactura {

	//Atributos
	private static Nodo cabeza;
	private static Nodo cola;
	private static int contador=0;
	protected static Scanner sc = new Scanner(System.in);
	
	//Constructor
	public ListaDobleFactura() {
		this.cabeza = null;
		this.cola = null;
	}
	
	//Métodos
	public static void agregarFacturaLista(Factura factura) {
		Nodo auxiliar = new Nodo(factura);
		if(vacio()) {
			cabeza = auxiliar;
			cola = auxiliar;
			contador++;
		}else {
			cola.siguiente = auxiliar;
			auxiliar.anterior = cola;
			auxiliar.siguiente = null;
			cola = auxiliar;
			contador++;
		}
	}
	
	public static void cancelarFactura(Factura factura) {
		Nodo paraBuscar = cabeza;
		Nodo auxiliar = new Nodo(factura);
		
		while(paraBuscar!=null) {
			if(vacio()) {
				System.out.println("\nNo hay facturas para cancelar\n");
			
			}else if(cabeza.factura == auxiliar.factura && contador!=1) {
				Factura.facturasCanceladas.add(cabeza.factura);
				cabeza = cabeza.siguiente;
				cabeza.anterior = null;
				contador--;
				break;
			}else if(cola.factura == auxiliar.factura && contador!=1) {
				Factura.facturasCanceladas.add(cola.factura);
				cola = cola.anterior;
				cola.siguiente = null;
				contador--;
				break;
			}else if(paraBuscar.factura == auxiliar.factura && contador!=1) {
				Factura.facturasCanceladas.add(paraBuscar.factura);
				paraBuscar.anterior.siguiente = paraBuscar.siguiente;
				paraBuscar.siguiente.anterior = paraBuscar.anterior;
				contador--;
				break;
			}else if(contador == 1){
				Factura.facturasCanceladas.add(paraBuscar.factura);
				cabeza = null;
				cola = null;
				contador = 0;
				break;
			}
			paraBuscar = paraBuscar.siguiente;
		}
	}
	
	public static void actualizarFactura(Factura factura) {
		Nodo paraBuscar = cabeza;
		Nodo paraActualizar = new Nodo(factura);
		
		while(paraBuscar!=null) {
			if(vacio()) {
				System.out.println("\nNo hay facturas para actualizar ...");
			}else {
				if(paraBuscar.factura == paraActualizar.factura){
					paraBuscar = paraActualizar;
					break;
				}
			}
			paraBuscar = paraBuscar.siguiente;
		}
	}
	
	public static void guardarFacturasPendientesEnArchivo() {
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
				paraTraerArchivo = new FileWriter(direccionArchivoPlano,true);
	            escritor = new BufferedWriter(paraTraerArchivo);
	            while(paraBuscar!=null) {
	            	String infoCarro = paraBuscar.factura.carro.toStringCarro();
	            	String numeroEstacionamiento = paraBuscar.factura.carro.getNumeroEstacionamiento();
	            	int valor = 0;
	            	lineaDeInfo = infoCarro + "," + numeroEstacionamiento + "," + valor +"\n";
	            	contenidoArchivo = contenidoArchivo + lineaDeInfo;
	            	paraBuscar = paraBuscar.siguiente;
	            }
	            escritor.write(contenidoArchivo);
	            System.out.print("\nInformación guardada correctamente.");
	            escritor.close();
	            paraTraerArchivo.close();
			}catch (IOException e) {
				System.out.print("\nError al guardar los vehiculos");
			}
			break;

		case 2:
			direccionArchivoPlano = "FacturasPendientesMotos.dat";
			try {
				paraTraerArchivo = new FileWriter(direccionArchivoPlano,true);
	            escritor = new BufferedWriter(paraTraerArchivo);
	            while(paraBuscar!=null) {
	            	String infoMoto = paraBuscar.factura.moto.toStringMoto();
	            	String numeroEstacionamiento = paraBuscar.factura.moto.getNumeroEstacionamiento();
	            	int valor = 0;
	            	lineaDeInfo = infoMoto + "," + numeroEstacionamiento + "," + valor +"\n";
	            	contenidoArchivo = contenidoArchivo + lineaDeInfo;
	            	paraBuscar = paraBuscar.siguiente;
	            }
	            escritor.write(contenidoArchivo);
	            System.out.print("\nInformación guardada correctamente.");
	            escritor.close();
	            paraTraerArchivo.close();
			}catch (IOException e) {
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
		
		switch (tipoVehiculo) {
		case 1:
			direccionArchivoPlano = "FacturasPendientesCarros.dat";
			try {
				paraTraerArchivo = new FileReader(direccionArchivoPlano);
	            lector = new BufferedReader(paraTraerArchivo);
	            String lineaDeInformacion = lector.readLine();
	            while (lineaDeInformacion != null) {
	                String contenido[] = lineaDeInformacion.split(",");

	                Carro carro = new Carro(contenido[0], contenido[1], contenido[2],
	                		Integer.parseInt(contenido[3]), contenido[4]);
	                
	                String numeroEstacionamiento = contenido[5];
	                carro.setNumeroEstacionamiento(numeroEstacionamiento);
	                carro.espaciosCarro[Integer.parseInt(numeroEstacionamiento)] = carro;
	                carro.espaciosPisoCarro[Integer.parseInt(numeroEstacionamiento)]= true;
	                
	                double hora = Integer.parseInt(contenido[6]);
	                double valor = Integer.parseInt(contenido[7]);
	                
	                Factura factura = new Factura(carro, hora, valor);
	                
	                ListaDobleFactura.agregarFacturaLista(factura);
	                lineaDeInformacion = lector.readLine();
	            }
	            System.out.print("\nInformación recuperada correctamente.");
	            lector.close();
	            paraTraerArchivo.close();
			}catch (IOException e) {
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

	                Moto moto = new Moto(contenido[0], contenido[1], contenido[2],
	                		Integer.parseInt(contenido[3]));
	                
	                String numeroEstacionamiento = contenido[4];
	                moto.setNumeroEstacionamiento(numeroEstacionamiento);
	                moto.espaciosMoto[Integer.parseInt(numeroEstacionamiento)] = moto;
	                moto.espaciosPisoMoto[Integer.parseInt(numeroEstacionamiento)]= true;
	                
	                double hora = Integer.parseInt(contenido[5]);
	                double valor = Integer.parseInt(contenido[6]);
	                
	                Factura factura = new Factura(moto, hora, valor);
	                
	                ListaDobleFactura.agregarFacturaLista(factura);
	                lineaDeInformacion = lector.readLine();
	            }
	            System.out.print("\nInformación recuperada correctamente.");
	            lector.close();
	            paraTraerArchivo.close();
			}catch (IOException e) {
				System.out.print("\nError al guardar los vehiculos");
			}
			break;
		}

	}
	
	public static void guardarFacturasCanceladas() {
		String direccionArchivoPlano = "Facturascanceladas.dat";
		String lineaDeInfo="";
		String contenidoArchivo="";
		try {
			FileWriter paraTraerArchivo = new FileWriter(direccionArchivoPlano,true);
            BufferedWriter escritor = new BufferedWriter(paraTraerArchivo);
            for(int i=0; i<Factura.facturasCanceladas.size(); i++) {
            	String info = Factura.facturasCanceladas.get(i).vehiculo.toString();
            	double valor = Factura.getValor();
            	lineaDeInfo = info + "," + valor + "\n";
            	contenidoArchivo = contenidoArchivo + lineaDeInfo;
            }
            escritor.write(contenidoArchivo);
            System.out.print("\nInformación guardada correctamente.");
            escritor.close();
            paraTraerArchivo.close();
		}catch (IOException e) {
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
                
                String color = contenido[0];
                String marca = contenido[1];
                String placa = contenido[2];
                String numeroEstacionamiento = contenido[3];
                Vehiculo vehiculo = new Vehiculo(color, marca, placa);
                vehiculo.setNumeroEstacionamiento(numeroEstacionamiento);
                double valor = Double.parseDouble(contenido[4]);
                
                Factura factura = new Factura(vehiculo,valor);
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
		return cabeza == null && cola == null;
	}

	public static void mostrarFacturasPendientes() { 
		Nodo paraMostrar = cabeza;
	  
		if(vacio()) { 
			System.out.print("\nNo hay Facturas pendientes");
		}else {
			System.out.print("\nFacturas pendientes (Carros y motos)\n");
			System.out.print("\nCarros\n");
			while(paraMostrar!=null) {
				if (paraMostrar.factura.carro != null) {
					System.out.print("Placa: "+paraMostrar.factura.carro.getPlaca()+"\n"
		  					+"Número estacionamiento: "+paraMostrar.factura.carro.getNumeroEstacionamiento()+"\n"
		  					+"Hora (momento consulta): "+paraMostrar.factura.hora+"\n"); 
		  			paraMostrar = paraMostrar.siguiente;
				}
	  		}
			paraMostrar = cabeza;
			System.out.print("\nMotos\n");
			while(paraMostrar!=null) {
				if(paraMostrar.factura.moto != null) {
					System.out.print("Placa: "+paraMostrar.factura.moto.getPlaca()+"\n"
		  					+"Número estacionamiento: "+paraMostrar.factura.moto.getNumeroEstacionamiento()+"\n"
		  					+"Hora (momento consulta): "+paraMostrar.factura.hora+"\n"); 
		  			paraMostrar = paraMostrar.siguiente;
				}else {
					break;
				}
	  		}
	  	} 
	  	System.out.println("\n"); 
	  }
}
