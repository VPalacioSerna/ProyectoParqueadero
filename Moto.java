
package proyectoparqueadero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Moto extends Vehiculo {

  // Atributos de moto
  protected int cilindraje; // Atributo propio
  static boolean[] espaciosPisoMoto = new boolean[40]; // matriz para verificar espacios ocupados(true) y
                                                       // desocupados(false)
  static Moto[] espaciosMoto = new Moto[40]; // matriz de informacion de las motos
  private static Scanner sc = new Scanner(System.in);

  // Constructor
  public Moto() {}

  public Moto(String color, String marca, String placa, int cilindraje) {
    super(color, marca, placa);
    this.cilindraje = cilindraje;
  }

  // Getters y setters propios de moto
  public int getCilindraje() {
    return cilindraje;
  }

  public void setCilindraje(int cilindraje) {
    this.cilindraje = cilindraje;
  }

  // Métodos
  public static Moto agregarMoto() {

    int numeroEstacionamientoParaRegistro = 0;
    Moto moto = new Moto();

    //vale: Selección de color
    System.out.println("Seleccione el color:");
    System.out.println("1. NEGRO" + "\n2. BLANCO" + "\n3. GRIS" + "\n4. ROJO" + "\n5. AZUL"
               + "\n0. Otro: ");
    String color = "";
    boolean colorValido = false;
    while (!colorValido) {
      System.out.print("Opción: ");
      int opcionColor = sc.nextInt();
      sc.nextLine();
      switch (opcionColor) {
              case 1: color = "NEGRO"; colorValido = true; break;
              case 2: color = "BLANCO"; colorValido = true; break;
              case 3: color = "GRIS"; colorValido = true; break;
              case 4: color = "ROJO"; colorValido = true; break;
              case 5: color = "AZUL"; colorValido = true; break;
        case 0: 
          System.out.print("Ingrese el color: ");
          color = sc.nextLine().toUpperCase();
          colorValido = true;
          break;
              default: System.out.println("Opción inválida. Intente de nuevo.");
          }
      } 
      moto.setColor(color);
    //System.out.print("\nIngrese color: ");
    //moto.setColor(sc.next());

    // vale: Selección de marca
    System.out.println("Seleccione la marca:");
    System.out.println("1. YAMAHA" + "\n2. HONDA" + "\n3. AKT" + "\n4. BMW" + "\n5. HUSQVARNA" + "\n0. Otro: ");
    String marca = "";
        boolean marcaValida = false;
        while (!marcaValida) {
          System.out.print("Opción: ");
          int opcionMarca = sc.nextInt();
          sc.nextLine(); // Limpia el buffer
          switch (opcionMarca) {
            case 1: marca = "YAMAHA"; marcaValida = true; break;
            case 2: marca = "HONDA"; marcaValida = true; break;
            case 3: marca = "AKT"; marcaValida = true; break;
            case 4: marca = "BMW"; marcaValida = true; break;
            case 5: marca = "HUSKVARNA"; marcaValida = true; break;
            case 0:
                System.out.print("Ingrese la marca: ");
                marca = sc.nextLine().toUpperCase();
                marcaValida = true;
                break;
            default:
                System.out.println("Opción inválida. Intente de nuevo.");
        }
    }
    moto.setMarca(marca);
    //System.out.print("Ingrese marca: ");
    //moto.setMarca(sc.next());

    //vale: hice cambios para validar el cilindraje
    int cilindraje;
    do {
        System.out.print("Ingrese cilindraje ");
        cilindraje = sc.nextInt();
        if (cilindraje < 80 || cilindraje > 2500) {
            System.out.println("Cilindraje inválido. Intente de nuevo.");
        }
    } while (cilindraje < 100 || cilindraje > 2500);
    moto.setCilindraje(cilindraje);
    //System.out.print("Ingrese cilindraje: ");
    //moto.setCilindraje(sc.nextInt());

    // vale: hice cambios para validar la placa
    String placa;
    do {
      System.out.print("Ingrese la placa de la moto (3 letras, 2 números y 1 letra, solo mayúsculas, ej: ABC12D): ");
      placa = sc.next();
      if (!validarPlacaMoto(placa)) {
        System.out.println("Placa inválida. Intente de nuevo.");
      }
    } while (!validarPlacaMoto(placa));
    moto.setPlaca(placa);

    //vale: hice cambios para validar el puesto
    //Buñuelito: Añadí el método validaPuesto
    int puesto = 0;
    boolean puestoValido = false;
    while (!puestoValido) {
      System.out.print("¿En qué puesto se estacionó? (1-50): ");
      if (sc.hasNextInt()) {
          puesto = sc.nextInt();
          sc.nextLine(); // Limpia el buffer
          if (puesto >= 1 && puesto <= 50) {
              puesto = validaPuestoMoto(puesto);
              puestoValido = true;
          } else {
              System.out.println("Número de puesto inválido. Intente de nuevo.");
          }
      } else {
        System.out.println("Entrada inválida. Debe ingresar un número.");
          sc.nextLine(); // Descarta la entrada incorrecta
      }
    }
    moto.setNumeroEstacionamiento(String.valueOf(puesto));
    //System.out.print("Ingrese el # del puesto donde se estaciono: ");
    //moto.setNumeroEstacionamiento(sc.next());

    numeroEstacionamientoParaRegistro = Integer.valueOf(moto.getNumeroEstacionamiento());

    
    espaciosPisoMoto[numeroEstacionamientoParaRegistro] = true;
    espaciosMoto[numeroEstacionamientoParaRegistro] = moto;
    System.out.println();

    return moto;
  }

  public static Moto quitarVehiculoMoto(String placa) {
		Moto moto = new Moto();
		
		for (int i = 0; i < espaciosMoto.length; i++) {
			if(espaciosPisoMoto[i] != false) {
				if (espaciosMoto[i].getPlaca().equals(placa)) {
					moto = espaciosMoto[i];
					espaciosMoto[i] = null;
					espaciosPisoMoto[i] = false;
				} else {
					continue;
				}
			}
		}
		return moto;
	}

  public static Moto modificarInformacionMoto(String placa) {
		Moto moto = new Moto();
		
		for(int i =0; i<espaciosMoto.length; i++) {
			if(espaciosPisoMoto[i]!=false) {
				if(placa.equals(espaciosMoto[i].getPlaca())) {
					System.out.print("\nIngrese color: ");
					espaciosMoto[i].setColor(sc.next().toUpperCase());

					System.out.print("Ingrese marca: ");
					espaciosMoto[i].setMarca(sc.next().toUpperCase());
					
					System.out.print("Ingrese cilindraje: ");
					espaciosMoto[i].setCilindraje(sc.nextInt());

					System.out.print("Ingrese la placa: ");
					espaciosMoto[i].setPlaca(sc.next().toUpperCase());
					
					moto = espaciosMoto[i];
				}
			}
		}
		System.out.println();
		return moto;
	}

  public static void guardarVehiculoEnArchivo(String direccionArchivo) {
    String lineaDeInfoMoto = "";
    String contenidoArchivo = "";
    try {
      FileWriter paraTraerArchivo = new FileWriter(direccionArchivo);
      BufferedWriter escritor = new BufferedWriter(paraTraerArchivo);
      for (int i = 0; i < espaciosMoto.length; i++) {
        if (espaciosPisoMoto[i] != false) {
          String color = espaciosMoto[i].getColor();
          String marca = espaciosMoto[i].getMarca();
          int cilindraje = espaciosMoto[i].getCilindraje();
          String placa = espaciosMoto[i].getPlaca();
          String numeroEstacionamiento = espaciosMoto[i].getNumeroEstacionamiento();
          lineaDeInfoMoto = color + "," + marca + "," + cilindraje + "," + placa + "," + numeroEstacionamiento + "\n";
          contenidoArchivo = contenidoArchivo + lineaDeInfoMoto;
        }
      }
      escritor.write(contenidoArchivo);
      System.out.print("\nInformación almacenada correctamente.");
      escritor.close();
      paraTraerArchivo.close();
    } catch (IOException e) {
      System.out.print("Error al guardar los vehiculos");
    }
  }

  public static void cargarInformacion(String direccionArchivo) {
    try {
      FileReader paraTraerArchivo = new FileReader(direccionArchivo);
      BufferedReader lector = new BufferedReader(paraTraerArchivo);
      String lineaDeInformacion = lector.readLine();
      while (lineaDeInformacion != null) {
        String contenido[] = lineaDeInformacion.split(",");

        String color = contenido[0];
        String marca = contenido[1];
        int cilindraje = Integer.parseInt(contenido[2]);
        String placa = contenido[3];
        String numeroEstacionamiento = contenido[4];
        Moto moto = new Moto(color, marca, placa, cilindraje);
        moto.setNumeroEstacionamiento(numeroEstacionamiento);

        espaciosMoto[Integer.parseInt(numeroEstacionamiento)] = moto;
        espaciosPisoMoto[Integer.parseInt(numeroEstacionamiento)] = true;
        lineaDeInformacion = lector.readLine();
      }
      System.out.print("\nInformación almacenada correctamente.");
      lector.close();
      paraTraerArchivo.close();
    } catch (IOException e) {
      System.out.print("\nError de lectura");
    }
  }
public String toStringMoto() {
		return color + "," + marca + "," + placa + "," + cilindraje + "," + numeroEstacionamiento;
	}

//Buñuelito: Añadí el metodo para validar motos
public static int validaPuestoMoto(int numeroEstacionamiento){
 while(espaciosMoto[numeroEstacionamiento] != null){
          System.out.println("El espacio ya está ocupado, ingrese otro espacio");
          int nuevoPuesto = 0;
          boolean puestoValido = false;
          while (!puestoValido) {
            System.out.print("¿En qué puesto se estacionó? (1-100): ");
            if (sc.hasNextInt()) {
                nuevoPuesto = sc.nextInt();
                sc.nextLine(); 
            if (nuevoPuesto >= 1 && nuevoPuesto <= 100) {
              if (nuevoPuesto >= 1 && nuevoPuesto <= 100) {
                    if (espaciosMoto[nuevoPuesto] == null) {
                        numeroEstacionamiento = nuevoPuesto;
                        puestoValido = true;
                    } else {
                        System.out.println("Ese puesto también está ocupado. Intente otro.");
                    }
            }else {
              System.out.println("Número de puesto inválido. Intente de nuevo.");
            }
            }else {
              System.out.println("Entrada inválida. Debe ingresar un número.");
              sc.nextLine(); 
        }
      }
    }
  }  
      return numeroEstacionamiento;
} 
}
