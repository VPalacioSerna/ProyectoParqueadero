package proyectoparqueadero;
//vale
import java.util.Scanner;

public class Administrador {

  // Atributos
  private String usuario = "usuario123";
  private String clave = "clave123";
  protected boolean estadoUsuario = true;
  protected String[] validacion = new String[2]; // Este lo cambie a un atributo de la clase, en vez de ser una variable
  protected int controlRepsUsuario = 0; // Este lo mismo, cambio de variable a atributo
  public Scanner sc = new Scanner(System.in);

  // Getters para Control usuario y setUsuario, para el estdo
  public String getUsuario() {
    return usuario;
  }

  public String getClave() {
    return clave;
  }

  public boolean getEstadoUsuario() {
    return estadoUsuario;
  }

  public void setEstadoUsuario(boolean estadoUsuario) {
    this.estadoUsuario = estadoUsuario;
  }

  // Metodos
  public void menuOpciones() {

    System.out.print("Bienvenido al Sistema de parqueo ..." + "\n");

    do {
      if (controlRepsUsuario == 0) {
        System.out.print("Ingrese usuario: ");
        validacion[0] = sc.next();
        System.out.print("Ingrese clave: ");
        validacion[1] = sc.next();
      }

      if (validacion[0].equals(this.getUsuario()) && validacion[1].equals(this.getClave())) {
        controlRepsUsuario = 1;
        System.out.print("\n\t--Menú--\n"
            + "1. Agregar vehiculo --- (Digite 1)\n"
            + "2. Quitar vehiculo --- (Digite 2)\n"
            + "3. Modificar información vehiculo registrado --- (Digite 3)\n"
            + "4. Consulta espacios con vehiculos --- (Digite 4)\n"
            + "5. Consulta espacios totales vacios u ocupados --- (Digite 5)\n"
            + "6. Subir información de vehiculos a archivos planos --- (Digite 6)\n"
            + "7. Cargar información de vehiculos --- (Digite 7)\n"
            + "8. Generación factura --- (Digite 8)\n"
            + "9. Consulta facturas canceladas --- (Digite 9)\n"
            + "10. Consulta facturas pendientes --- (Digite 10)\n"
            + "11. Subir información de facturas a archivos planos --- (Digite 11)\n"
            + "12. Cargar información de facturas --- (Digite 12)\n"
            + "Cerrar --- (Digite 0)\n"
            + "Opción: ");
        // vale: hice cambio para validar la entrada antes de leerla
        while (!sc.hasNextInt()) {
          System.out.println("Por favor, ingrese un número válido.");
          sc.next(); // descarta la entrada incorrecta
        }
        int opcion = sc.nextInt();
        /*
         * Se agregaron nuevas opciones para el menú, y se "optimizo"
         */

        switch (opcion) {
          case 1:
            System.out.print("\n¿Qué tipo vehiculo desea agregar (Carro(1) - Moto(2))?: ");
            Vehiculo.agregarVehiculo(sc.nextInt());
            break;

          case 2:
            System.out.print("\n¿Qué tipo de vehiculo desea eliminar (Carro(1) - Moto(2))?: ");
            Vehiculo.quitarVehiculo(sc.nextInt());
            break;

          case 3:
            System.out.print("\n¿Qué tipo de vehiculo desea modificar (Carro(1) - Moto(2))?: ");
            Vehiculo.modificarVehiculo(sc.nextInt());
            break;

          case 4:
            Vehiculo.mostrarVehiculos();
            break;

          case 5:
            System.out.print("\nEspacios ocupados(1) --- Espacios vacios(2): ");
            System.out.print(Vehiculo.contabilizacionVehiculos(sc.nextInt()));
            break;

          case 6:
            System.out.print("\n¿Qué tipo de vehiculos desea subir al archivo (Carros(1) - Motos(2))?: ");
            Vehiculo.guardarVehiculosEnArchivo(sc.nextInt());
            break;

          case 7:
            System.out.print("\n¿Qué tipo de vehiculos desea cargar al sistema (Carros(1) - Motos(2))?: ");
            Vehiculo.cargarInformacionVehiculos(sc.nextInt());
            break;

          case 8:
            break;
          case 9:
            break;
          case 10:
            break;
          case 11:
            break;
          case 12:
            break;

          case 0:
            this.setEstadoUsuario(false);
            System.out.println("\n" + "Hasta luego ...");
            break;
          default:
            System.out.println("\n" + "Opcion fuera del rango." + "\n"
                + "Intente de nuevo");
            break;
        }
      } else {
        System.out.println("\n" + "Se ha equivocado." + "\n" + "intente de nuevo ..." + "\n");
        sc.nextLine();
      }
    } while (this.getEstadoUsuario());
  }
}
