package proyectoparqueadero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Carro extends Vehiculo {

  // Atributos
  private int numeroDePuertas;// Atributo nuevo para diferenciar de moto
  private String tipoDeVehiculo;// Atributo nuevo para diferenciar de moto
  static boolean[] espaciosPisoCarro = new boolean[50]; // matriz para verificar espacios ocupados(true) y
                                                        // desocupados(false)
  static Carro[] espaciosCarro = new Carro[50]; // matriz de informacion de las motos
  private static Scanner sc = new Scanner(System.in);

  // Cpnstructores
  public Carro() {}
  
  public Carro(String color, String marca, String placa, int tiempoInicial, int numeroDePuertas, String tipoDeVehiculo) {
    super(color, marca, placa, tiempoInicial);
    this.numeroDePuertas = numeroDePuertas;
    this.tipoDeVehiculo = tipoDeVehiculo;
  }

  // Getters y setters de atributos propios de Carro
  public int getNumeroDePuertas() {
    return numeroDePuertas;
  }

  public void setNumeroDePuertas(int numeroDePuertas) {
    this.numeroDePuertas = numeroDePuertas;
  }

  public String getTipoDeVehiculo() {
    return tipoDeVehiculo;
  }

  public void setTipoDeVehiculo(String tipoDeVehiculo) {
    this.tipoDeVehiculo = tipoDeVehiculo;
  }

  // Métodos
  public static Carro agregarCarro() {

    int numeroEstacionamientoParaRegistro = 0;
    Carro carro = new Carro();

    // vale: Selección de color
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
              case 1: color = "NEGRO"; 
              colorValido = true;
              break;
              case 2: color = "BLANCO"; 
              colorValido = true;
              break;
              case 3: color = "GRIS"; 
              colorValido = true;
              break;
              case 4: color = "ROJO"; 
              colorValido = true;
              break;
              case 5: color = "AZUL"; 
              colorValido = true;
              break;
        case 0: 
          System.out.print("Ingrese el color: ");
          color = sc.next().toUpperCase();
          colorValido = true;
          break;
              default: System.out.println("Opción inválida. Intente de nuevo.");
          }
      } 
      carro.setColor(color);
    // System.out.print("\nIngrese color: ");
    // carro.setColor(sc.next());

    // vale: Selección de marca
    System.out.println("Seleccione la marca:");
    System.out.println("1. CHEVROLET" + "\n2. RENAULT" + "\n3. MAZDA" + "\n4. TOYOTA" + "\n5. KIA" + "\n0. Otro: ");
    String marca = "";
        boolean marcaValida = false;
        while (!marcaValida) {
          System.out.print("Opción: ");
          int opcionMarca = sc.nextInt();
          sc.nextLine(); // Limpia el buffer
          switch (opcionMarca) {
            case 1: marca = "CHEVROLET"; marcaValida = true; break;
            case 2: marca = "RENAULT"; marcaValida = true; break;
            case 3: marca = "MAZDA"; marcaValida = true; break;
            case 4: marca = "TOYOTA"; marcaValida = true; break;
            case 5: marca = "KIA"; marcaValida = true; break;
            case 0:
                System.out.print("Ingrese la marca: ");
                marca = sc.nextLine().toUpperCase();
                marcaValida = true;
                break;
            default:
                System.out.println("Opción inválida. Intente de nuevo.");
        }
    }
    carro.setMarca(marca);
    // System.out.print("Ingrese marca: ");
    // carro.setMarca(sc.next());

    // vale: Selección de número de puertas
    System.out.println("Seleccione el número de puertas:");
    System.out.println("1. 2 puertas");
    System.out.println("2. 4 puertas");
    System.out.println("3. 6 puertas");
    int opcionPuertas;
    int numPuertas = 0;
    do {
      System.out.print("Opción: ");
      opcionPuertas = sc.nextInt();
      switch (opcionPuertas) {
        case 1:
          numPuertas = 2;
          break;
        case 2:
          numPuertas = 4;
          break;
        case 3:
          numPuertas = 6;
          break;
        default:
          System.out.println("Opción inválida. Intente de nuevo.");
      }
    } while (numPuertas == 0);
    carro.setNumeroDePuertas(numPuertas);
    // System.out.print("Ingrese número de puertas: ");
    // carro.setNumeroDePuertas(sc.nextInt());

    System.out.print("Ingrese tipo de vehiculo: ");
    carro.setTipoDeVehiculo(sc.next());

    // vale: hice cambios para validar la placa
    String placa;
    do {
      System.out.print("Ingrese la placa (3 letras y 3 números, ej: ABC123): ");
      placa = sc.next();
      if (!validarPlacaCarro(placa)) {
        System.out.println("Placa inválida. Intente de nuevo.");
      }
    } while (!validarPlacaCarro(placa));
    carro.setPlaca(placa);
    
    
    //Maicol: Agregué la validación de carros
    int puesto = 0;
    boolean puestoValido = false;
    while (!puestoValido) {
      System.out.print("¿En qué puesto se estacionó? (1-50): ");
      if (sc.hasNextInt()) {
          puesto = sc.nextInt();
          sc.nextLine(); 
          if (puesto >= 1 && puesto <= 50) {
              puesto = validaPuestoCarro(puesto);
              puestoValido = true;
          } else {
              System.out.println("Número de puesto inválido. Intente de nuevo.");
          }
      } else {
        System.out.println("Entrada inválida. Debe ingresar un número.");
          sc.nextLine(); 
      }
    }
    carro.setNumeroEstacionamiento(String.valueOf(puesto));
    /*System.out.print("Ingrese el # del puesto donde se estaciono: ");
    carro.setNumeroEstacionamiento(sc.next());/**/

    numeroEstacionamientoParaRegistro = Integer.valueOf(carro.getNumeroEstacionamiento());

    espaciosPisoCarro[numeroEstacionamientoParaRegistro] = true;
    espaciosCarro[numeroEstacionamientoParaRegistro] = carro;
    System.out.println();

    return carro;
  }

  public static Carro quitarVehiculoCarro(String placa) {
    Carro carro = new Carro();
    
    for (int i = 0; i < espaciosCarro.length; i++) {
      if (espaciosPisoCarro[i] != false) {
        if (espaciosCarro[i].getPlaca().equals(placa)) {
          carro = espaciosCarro[i];
          espaciosCarro[i] = null;
          espaciosPisoCarro[i] = false;
          System.out.println("\nSe ha eliminado con exito !!");
        } else {
          continue;
        }
      }
    }
    return carro;
  }

  //vale: hice cambios para modificar la info
  public static Carro modificarInformacionCarro(String placa) {
    Carro carro = new Carro();
    
    for (int i = 0; i < espaciosCarro.length; i++) {
        if (espaciosPisoCarro[i] != false) {
            if (placa.equals(espaciosCarro[i].getPlaca())) {
                // Selección de color
                System.out.println("Seleccione el color:");
                System.out.println("1. NEGRO" + "\n2. BLANCO" + "\n3. GRIS" + "\n4. ROJO" + "\n5. AZUL" + "\n0. Otro: ");
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
                        default:
                            System.out.println("Opción inválida. Intente de nuevo.");
                    }
                }
                espaciosCarro[i].setColor(color);

                // Selección de marca
                System.out.println("Seleccione la marca:");
                System.out.println("1. CHEVROLET" + "\n2. RENAULT" + "\n3. MAZDA" + "\n4. TOYOTA" + "\n5. KIA" + "\n0. Otro: ");
                String marca = "";
                boolean marcaValida = false;
                while (!marcaValida) {
                    System.out.print("Opción: ");
                    int opcionMarca = sc.nextInt();
                    sc.nextLine();
                    switch (opcionMarca) {
                        case 1: marca = "CHEVROLET"; marcaValida = true; break;
                        case 2: marca = "RENAULT"; marcaValida = true; break;
                        case 3: marca = "MAZDA"; marcaValida = true; break;
                        case 4: marca = "TOYOTA"; marcaValida = true; break;
                        case 5: marca = "KIA"; marcaValida = true; break;
                        case 0:
                            System.out.print("Ingrese la marca: ");
                            marca = sc.nextLine().toUpperCase();
                            marcaValida = true;
                            break;
                        default:
                            System.out.println("Opción inválida. Intente de nuevo.");
                    }
                }
                espaciosCarro[i].setMarca(marca);

                // Selección de número de puertas
                System.out.println("Seleccione el número de puertas:");
                System.out.println("1. 2 puertas");
                System.out.println("2. 4 puertas");
                System.out.println("3. 6 puertas");
                int opcionPuertas;
                int numPuertas = 0;
                do {
                    System.out.print("Opción: ");
                    opcionPuertas = sc.nextInt();
                    switch (opcionPuertas) {
                        case 1: numPuertas = 2; break;
                        case 2: numPuertas = 4; break;
                        case 3: numPuertas = 6; break;
                        default: System.out.println("Opción inválida. Intente de nuevo.");
                    }
                } while (numPuertas == 0);
                espaciosCarro[i].setNumeroDePuertas(numPuertas);

                System.out.print("Ingrese tipo de vehiculo: ");
                espaciosCarro[i].setTipoDeVehiculo(sc.next());

                // Validar placa                
                do {
                    System.out.print("Ingrese la placa (3 letras y 3 números, ej: ABC123): ");
                    placa = sc.next();
                    if (!validarPlacaCarro(placa)) {
                        System.out.println("Placa inválida. Intente de nuevo.");
                    }
                } while (!validarPlacaCarro(placa));
                espaciosCarro[i].setPlaca(placa);

              carro = espaciosCarro[i];
            }
        }
    }
    System.out.println();
    return carro;
}

  public static void guardarVehiculoEnArchivo(String direccionArchivo) {
    String lineaDeInfoCarro = "";
    String contenidoArchivo = "";
    try {
      FileWriter paraTraerArchivo = new FileWriter(direccionArchivo);
      BufferedWriter escritor = new BufferedWriter(paraTraerArchivo);
      for (int i = 0; i < espaciosCarro.length; i++) {
        if (espaciosPisoCarro[i] != false) {
          String color = espaciosCarro[i].getColor();
          String marca = espaciosCarro[i].getMarca();
          int numeroDePuertas = espaciosCarro[i].getNumeroDePuertas();
          String tipoDeVehiculo = espaciosCarro[i].getTipoDeVehiculo();
          String placa = espaciosCarro[i].getPlaca();
          String numeroEstacionamiento = espaciosCarro[i].getNumeroEstacionamiento();
          lineaDeInfoCarro = color + "," + marca + "," + numeroDePuertas + "," + tipoDeVehiculo + "," + placa + ","
              + numeroEstacionamiento + "\n";
          contenidoArchivo = contenidoArchivo + lineaDeInfoCarro;
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
        int numeroDePuertas = Integer.parseInt(contenido[2]);
        String tipoDeVehiculo = contenido[3];
        String placa = contenido[4];
        String numeroEstacionamiento = contenido[5];
        Carro carro = new Carro(color, marca, placa, tiempoInicial, numeroDePuertas, tipoDeVehiculo);
        carro.setNumeroEstacionamiento(numeroEstacionamiento);

        espaciosCarro[Integer.parseInt(numeroEstacionamiento)] = carro;
        espaciosPisoCarro[Integer.parseInt(numeroEstacionamiento)] = true;
        lineaDeInformacion = lector.readLine();
      }
      System.out.print("\nInformación almacenada correctamente.");
      lector.close();
      paraTraerArchivo.close();
    } catch (IOException e) {
      System.out.print("\nError de lectura");
    }
  }

  public String toStringCarro() {
		return color + "," + marca + "," + placa + "," + numeroDePuertas +"," + tipoDeVehiculo 
				+ "," + numeroEstacionamiento;
	}
  //Metodo para validar si el puesto está ocupado o no
  public static int validaPuestoCarro(int numeroEstacionamiento){
      while(espaciosCarro[numeroEstacionamiento] != null){
          System.out.println("El espacio ya está ocupado, ingrese otro espacio");
          int nuevoPuesto = 0;
          boolean puestoValido = false;
          while (!puestoValido) {
            System.out.print("¿En qué puesto se estacionó? (1-50): ");
            if (sc.hasNextInt()) {
                nuevoPuesto = sc.nextInt();
                sc.nextLine(); 
            if (nuevoPuesto >= 1 && nuevoPuesto <= 50) {
              if (nuevoPuesto >= 1 && nuevoPuesto <= 50) {
                    if (espaciosCarro[nuevoPuesto] == null) {
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
