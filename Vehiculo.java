
package proyectoparqueadero;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Vehiculo {

  // Atributos
  protected String color;
  protected String marca;
  protected String placa;
  protected String numeroEstacionamiento;
  private Factura factura;
  protected static int tiempoInicial = Tiempo.hora();
  public static Scanner sc = new Scanner(System.in);

  // Constructores
  public Vehiculo() {
  }
  public Vehiculo(String color, String marca, String placa, int tiempoInicial) {
    this.tiempoInicial = tiempoInicial;
    this.color = color;
    this.marca = marca;
    this.placa = placa;
  }

  // Getter y setters

    public static int getTiempoInicial() {
        return tiempoInicial;
    }

    public void setTiempoInicial(int tiempoInicial) {
        this.tiempoInicial = tiempoInicial;
    }
  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public String getPlaca() {
    return placa;
  }

  public void setPlaca(String placa) {
    this.placa = placa;
  }

  public String getNumeroEstacionamiento() {
    return numeroEstacionamiento;
  }

  public void setNumeroEstacionamiento(String numeroEstacionamiento) {
    this.numeroEstacionamiento = numeroEstacionamiento;
  }

  public Factura getFactura(){
    return factura;
  }

  // Método para validar placa: 3 letras seguidas de 3 números
  // Para carro: 3 letras + 3 números, solo mayúsculas (ej: ABC123)
  /*
    Significado simbolico de el metodo matches:
    ^ = inicio
    $ = fin
    {3} = 3 veces repetidas
    \\d = cualquier digito del 0 al 9
  */
  public static boolean validarPlacaCarro(String placa) {
    return placa.matches("^[A-Z]{3}\\d{3}$");
  }

  // Para moto: 3 letras + 2 números + 1 letra, solo mayúsculas (ej: ABC12D)
  public static boolean validarPlacaMoto(String placa) {
    return placa.matches("^[A-Z]{3}\\d{2}[A-Z]$");
  }

  public static void mostrarVehiculos() {
		System.out.print("\n\tVehiculos");
		System.out.print("\nCarros\n");
		for (int i = 0; i < Carro.espaciosPisoCarro.length; i++) {
			if(Carro.espaciosPisoCarro[i]!=false){
					System.out.print("Marca: "+Carro.espaciosCarro[i].getMarca()+"\n");
					System.out.print("Color: "+Carro.espaciosCarro[i].getColor()+"\n");
					System.out.print("Número puertas: "+Carro.espaciosCarro[i].getNumeroDePuertas()+"\n");
					System.out.print("Tipo: "+Carro.espaciosCarro[i].getTipoDeVehiculo()+"\n");
					System.out.print("Placa: "+Carro.espaciosCarro[i].getPlaca()+"\n");
					System.out.print("Estacionado en el puesto: "+Carro.espaciosCarro[i].getNumeroEstacionamiento()+"\n");
					System.out.println();
			}else {
				continue;
			}	
		}
		System.out.print("\nMotos\n");
		for (int i = 0; i < Moto.espaciosPisoMoto.length; i++) {
			if(Moto.espaciosPisoMoto[i]!=false){
					System.out.print("Marca: "+Moto.espaciosMoto[i].getMarca()+"\n");
					System.out.print("Color: "+Moto.espaciosMoto[i].getColor()+"\n");
					System.out.print("Cilindraje: "+Moto.espaciosMoto[i].getCilindraje()+"cc"+"\n");
					System.out.print("Placa: "+Moto.espaciosMoto[i].getPlaca()+"\n");
					System.out.print("Estacionado en el puesto: "+Moto.espaciosMoto[i].getNumeroEstacionamiento()+"\n");
					System.out.println();
			}else {
				continue;
			}	
		}
	}
	
	public static void mostrarVehiculoPorPlaca() {
		System.out.print("Ingrese placa: ");
		String placa = sc.next();
		
		for (int i = 0; i < Carro.espaciosPisoCarro.length; i++) {
			if(Carro.espaciosPisoCarro[i]!=false){
				if(Carro.espaciosCarro[i].getPlaca().equals(placa)) {
					System.out.print("Marca: "+Carro.espaciosCarro[i].getMarca()+"\n");
					System.out.print("Color: "+Carro.espaciosCarro[i].getColor()+"\n");
					System.out.print("Número puertas: "+Carro.espaciosCarro[i].getNumeroDePuertas()+"\n");
					System.out.print("Tipo: "+Carro.espaciosCarro[i].getTipoDeVehiculo()+"\n");
					System.out.print("Placa: "+Carro.espaciosCarro[i].getPlaca()+"\n");
					System.out.print("Estacionado en el puesto: "+Carro.espaciosCarro[i].getNumeroEstacionamiento()+"\n");
					System.out.println();
				}
			}else {
				continue;
			}	
		}
		for (int i = 0; i < Moto.espaciosPisoMoto.length; i++) {
			if(Moto.espaciosPisoMoto[i]!=false){
				if(Moto.espaciosMoto[i].getPlaca().equals(placa)) {
					System.out.print("Marca: "+Moto.espaciosMoto[i].getMarca()+"\n");
					System.out.print("Color: "+Moto.espaciosMoto[i].getColor()+"\n");
					System.out.print("Cilindraje: "+Moto.espaciosMoto[i].getCilindraje()+"\n");
					System.out.print("Placa: "+Moto.espaciosMoto[i].getPlaca()+"\n");
					System.out.print("Estacionado en el puesto: "+Moto.espaciosMoto[i].getNumeroEstacionamiento()+"\n");
					System.out.println();
				}
			}else {
				continue;
			}	
		}
	}
	
	public static String contabilizacionVehiculos(int opcion) {

		int contadorEspaciosVacios =0, contadorEspaciosOcupados=0;
		String respuesta = "";
		
		for (int i = 0; i < Carro.espaciosPisoCarro.length; i++) {
			if (Carro.espaciosPisoCarro[i] != false) {
				contadorEspaciosOcupados++;
			} else {
				contadorEspaciosVacios++;
			}
		}
		
		for (int i = 0; i < Moto.espaciosPisoMoto.length; i++) {
			if (Moto.espaciosPisoMoto[i] != false) {
				contadorEspaciosOcupados++;
			} else {
				contadorEspaciosVacios++;
			}
		}
		
		if (opcion == 1) {
			respuesta = "La cantidad de espacios ocupados es de: "+contadorEspaciosOcupados;
		}else {
			respuesta = "\nLa cantidad de espacios vacios es de: "+contadorEspaciosVacios;
		}
		
		return respuesta+"\n";
	}

	@Override
	public String toString() {
		return color + "," + marca + "," + placa + "," + numeroEstacionamiento;
	}
}
