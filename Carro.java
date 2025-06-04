package proyectoparqueadero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Carro extends Vehiculo {

  // Atributos
  private String parqueadero[][]=new String[7][12];
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

    // Selección de color
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

    // Selección de marca
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

    //  hice cambios para validar la placa
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
      Carro Juli = new Carro();
      Juli.ParqueaderoGrafico();
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

  //hice cambios para modificar la info
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
	
  //Metodo para validar si el puesto está ocupado o no
  public static int validaPuestoCarro(int numeroEstacionamiento){
      while(espaciosCarro[numeroEstacionamiento] != null){
          System.out.println("El espacio ya está ocupado, ingrese otro espacio");
          int nuevoPuesto = 0;
          boolean puestoValido = false;
          while (!puestoValido) {
         Carro Juli = new Carro();
         Juli.ParqueaderoGrafico();
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
  public void ParqueaderoGrafico(){
      // DTK: Marcación de limites.
      for (int i = 0; i < 7; i++) {
          if(i == 0)
              parqueadero[i][i] = "+";
          else{
              if(i == 6){
                parqueadero [i][0] = "+";
              }
              else
                parqueadero[i][0] = "|";      
          }
      }
  
      for (int i = 1; i < 12; i++) {
          if(i == 11){
              parqueadero [0][i] = "+";
              parqueadero [6][i] = "+";
          }
          else
              parqueadero [0][i] = "==";
          for (int j = 1; j < 11; j++) {
              parqueadero [6][j] = "==";
          }
          for (int j = 1; j < 6; j++) {
              parqueadero [j][11] = "|";
          }
        }
      int vecaux[]= new int[50];
      int aux0 = 1;
      for (int i = 0; i < 50; i++) {
          vecaux[i]= aux0;
          aux0++;
      }
      //DTK: Enumeración de parqueaderos
      String Conteitor = "";
      int K = 0;
      for (int i = 1; i < 6; i++) {
          for (int j = 1; j < 11; j++) {
              if(K<9){
              parqueadero[i][j] = "0"+String.valueOf(vecaux[K]);
              K++;
              }else{
              parqueadero[i][j] = String.valueOf(vecaux[K]);
              K++; 
              }
          }
          
      }
      //DTK: Impresión
      String acum="";
      
      for (int i = 0; i < 7; i++) {
          for (int j = 0; j < 12; j++) {
              acum+=parqueadero[i][j]+"         ";
            }
          acum+="\n";
        }
      System.out.println(acum);
    }
	
  public String toStringCarro() {
		return color + "," + marca + "," + placa + "," + numeroDePuertas +"," + tipoDeVehiculo 
				+ "," + numeroEstacionamiento;
	}
      
     
}
      
          
      
  

