
package proyectoparqueadero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Moto extends Vehiculo {
	
 private String parqueadero[][]=new String[7][12];
  // Atributos de moto
  protected int cilindraje; // Atributo propio
  static boolean[] espaciosPisoMoto = new boolean[40]; // matriz para verificar espacios ocupados(true) y
                                                       // desocupados(false)
  static Moto[] espaciosMoto = new Moto[40]; // matriz de informacion de las motos
  private static Scanner sc = new Scanner(System.in);

  // Constructor
  public Moto() {}

  public Moto(String color, String marca, String placa,int tiempoInicial, int cilindraje) {
    super(color, marca, placa, tiempoInicial);
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

    //Selección de color
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

    // Selección de marca
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

    // cambios para validar el cilindraje
    int cilindraje;
    do {
        System.out.print("Ingrese cilindraje ");
        cilindraje = sc.nextInt();
        if (cilindraje < 80 || cilindraje > 2500) {
            System.out.println("Cilindraje inválido. Intente de nuevo.");
        }
    } while (cilindraje < 100 || cilindraje > 2500);
    moto.setCilindraje(cilindraje);

    // cambios para validar la placa
    String placa;
    do {
      System.out.print("Ingrese la placa de la moto (3 letras, 2 números y 1 letra, solo mayúsculas, ej: ABC12D): ");
      placa = sc.next();
      if (!validarPlacaMoto(placa)) {
        System.out.println("Placa inválida. Intente de nuevo.");
      }
    } while (!validarPlacaMoto(placa));
    moto.setPlaca(placa);

    //cambios para validar el puesto
    //Buñuelito: Añadí el método validaPuesto
    int puesto = 0;
    boolean puestoValido = false;
    while (!puestoValido) {
      Moto Juli = new Moto();
      Juli.ParqueaderoGrafico();
      System.out.print("¿En qué puesto se estacionó? (1-50): ");
      if (sc.hasNextInt()) {
          puesto = sc.nextInt();
          sc.nextLine(); // Limpia el buffer
          if (puesto >= 1 && puesto <= 40) {
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
//Buñuelito: Añadí el metodo para validar motos
public static int validaPuestoMoto(int numeroEstacionamiento){
 while(espaciosMoto[numeroEstacionamiento] != null){
          System.out.println("El espacio ya está ocupado, ingrese otro espacio");
          int nuevoPuesto = 0;
          boolean puestoValido = false;
          while (!puestoValido) {
          Moto Juli = new Moto();
          Juli.ParqueaderoGrafico();
            System.out.print("¿En qué puesto se estacionó? (1-50): ");
            if (sc.hasNextInt()) {
                nuevoPuesto = sc.nextInt();
                sc.nextLine(); 
            if (nuevoPuesto >= 1 && nuevoPuesto <= 40) {
              if (nuevoPuesto >= 1 && nuevoPuesto <= 40) {
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
	
	public String toStringMoto() {
		return color + "," + marca + "," + placa + "," + cilindraje + "," + numeroEstacionamiento;
	}
}
