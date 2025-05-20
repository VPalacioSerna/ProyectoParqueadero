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
  public static Scanner sc = new Scanner(System.in);

  // Constructores
  public Vehiculo() {
  }

  public Vehiculo(String color, String marca, String placa) {
    this.color = color;
    this.marca = marca;
    this.placa = placa;
  }

  // Getter y setters
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

  // vale: Método para validar placa: 3 letras seguidas de 3 números
  // Para carro: 3 letras + 3 números, solo mayúsculas (ej: ABC123)
  public static boolean validarPlacaCarro(String placa) {
    return placa.matches("^[A-Z]{3}\\d{3}$");
  }

  // Para moto: 3 letras + 2 números + 1 letra, solo mayúsculas (ej: ABC12D)
  public static boolean validarPlacaMoto(String placa) {
    return placa.matches("^[A-Z]{3}\\d{2}[A-Z]$");
  }

  // Métodos
  /*
   * Acá cambie la forma en la que estaba implementado el metodo, pienso que seria
   * bueno añadir algun tipo de factor que diferencie los
   * carros de las motos, como para que no parezca que tienen exactamente los
   * mismos atributos, porque entonces no tendria mucho sentido el cambio que hice
   */
  public static void agregarVehiculo(int tipoVehiculo) {
    int numeroEstacionamientoParaRegistro = 0;

    switch (tipoVehiculo) {
      case 1:
        // Para carro
        Carro.agregarCarro();
        break;
      case 2:
        Moto.agregarMoto();
        // Para moto
        break;
    }
    System.out.println("Se ha registrado con exito !!");
  }

  /*
   * Acá nuevamente, en quitar vehiculo hice lo mismo, poniendo el proceso en cada
   * clase espesifica. Recalco que, tanto carro, como moto, las clases, tienen
   * vectores
   * o matrices abjetuales de diferentes dimensiones. Y cada una es del mismo tipo
   * de la clase.
   * 
   * Como los vectores objetuales son estaticos, fijos, es decir siempre
   * utilizaremos el mismo, por eso los metodos no reciben como parametro los
   * vectores,
   * y por eso mismo tampoco me devuelven algo
   */

  public static void quitarVehiculo(int tipoVehiculo) {

    System.out.print("\nIngrese la placa del vehiculo: ");
    String placa = sc.next();

    switch (tipoVehiculo) {
      case 1:
        // Para carro
        Carro.quitarVehiculoCarro(placa);
        break;
      case 2:
        // Para moto
        Moto.quitarVehiculoMoto(placa);
        break;
    }
  }

  public static void modificarVehiculo(int tipoVehiculo) {

    System.out.print("\nIngrese el numero de estacionamiento: ");
    int puesto = sc.nextInt();

    switch (tipoVehiculo) {
      case 1:
        // Para carro
        Carro.modificarInformacionCarro(puesto);
        break;
      case 2:
        // Para moto
        Moto.modificarInformacionMoto(puesto);
        break;
    }
    System.out.println("El vehiculo se ha modificado con exito !!\n");
  }

  public static void mostrarVehiculos() {
    System.out.print("\n\tVehiculos");
    System.out.print("\nCarros\n");
    for (int i = 0; i < Carro.espaciosPisoCarro.length; i++) {
      if (Carro.espaciosPisoCarro[i] != false) {
        System.out.print("Marca: " + Carro.espaciosCarro[i].getMarca().toUpperCase() + "\n");
        System.out.print("Color: " + Carro.espaciosCarro[i].getColor().toUpperCase() + "\n");
        System.out.print("Número puertas: " + Carro.espaciosCarro[i].getNumeroDePuertas() + "\n");
        System.out.print("Tipo: " + Carro.espaciosCarro[i].getTipoDeVehiculo().toUpperCase() + "\n");
        System.out.print("Placa: " + Carro.espaciosCarro[i].getPlaca().toUpperCase() + "\n");
        System.out.print(
            "Estacionado en el puesto: " + Carro.espaciosCarro[i].getNumeroEstacionamiento().toUpperCase() + "\n");
        System.out.println();
      } else {
        continue;
      }
    }
    System.out.print("\nMotos\n");
    for (int i = 0; i < Moto.espaciosPisoMoto.length; i++) {
      if (Moto.espaciosPisoMoto[i] != false) {
        System.out.print("Marca: " + Moto.espaciosMoto[i].getMarca().toUpperCase() + "\n");
        System.out.print("Color: " + Moto.espaciosMoto[i].getColor().toUpperCase() + "\n");
        System.out.print("Cilindraje: " + Moto.espaciosMoto[i].getCilindraje() + "\n");
        System.out.print("Placa: " + Moto.espaciosMoto[i].getPlaca().toUpperCase() + "\n");
        System.out
            .print("Estacionado en el puesto: " + Moto.espaciosMoto[i].getNumeroEstacionamiento().toUpperCase() + "\n");
        System.out.println();
      } else {
        continue;
      }
    }
  }

  public static String contabilizacionVehiculos(int opcion) {

    int contadorEspaciosVacios = 0, contadorEspaciosOcupados = 0;
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
      respuesta = "La cantidad de espacios ocupados es de: " + contadorEspaciosOcupados;
    } else {
      respuesta = "\nLa cantidad de espacios vacios es de: " + contadorEspaciosVacios;
    }

    return respuesta + "\n";
  }

  public static void guardarVehiculosEnArchivo(int tipoVehiculo) {

    String direccionArchivoPlano = "";

    switch (tipoVehiculo) {
      case 1:
        // Carro
        direccionArchivoPlano = "C://Users/USUARIO/Desktop/Vehiculos-Carros.txt/";
        Carro.guardarVehiculoEnArchivo(direccionArchivoPlano);
        break;

      case 2:
        // Moto
        direccionArchivoPlano = "C:/Users/USUARIO/Desktop/Vehiculos-Motos.txt";
        Moto.guardarVehiculoEnArchivo(direccionArchivoPlano);
        break;
    }
  }

  public static void cargarInformacionVehiculos(int tipoVehiculo) {

    String direccionArchivoPlano = "";

    switch (tipoVehiculo) {
      case 1:
        // Carro
        direccionArchivoPlano = "C://Users/USUARIO/Desktop/Vehiculos-Carros.txt/";
        Carro.cargarInformacion(direccionArchivoPlano);
        break;

      case 2:
        // Moto
        direccionArchivoPlano = "C:/Users/USUARIO/Desktop/Vehiculos-Motos.txt";
        Moto.cargarInformacion(direccionArchivoPlano);
        break;
    }
  }
}
