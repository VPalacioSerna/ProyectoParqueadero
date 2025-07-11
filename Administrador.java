
package proyectoparqueadero;

import java.util.Scanner;

public class Administrador {

	//Atributos
	private String usuario = "usuario123";
	private String clave = "clave123";
	protected boolean estadoUsuario = true;
	protected String [] validacion = new String [2]; //Este lo cambie a un atributo de la clase, en vez de ser una variable
	protected int controlRepsUsuario = 0; //Este lo mismo, cambio de variable a atributo
	public Scanner sc = new Scanner(System.in);
	
	//Getters para Control usuario y setUsuario, para el estdo
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
	
	//Metodos
	public void menuOpciones() {

		System.out.print("Bienvenido al Sistema de parqueo ..."+"\n");
		
		do {
			if (controlRepsUsuario == 0){
				System.out.print("Ingrese usuario: ");
				validacion[0] = sc.next();
				System.out.print("Ingrese clave: ");
				validacion[1] = sc.next();
			}
			
			if (validacion[0].equals(this.getUsuario()) && validacion[1].equals(this.getClave())) {
				controlRepsUsuario = 1;
				System.out.print("\n\t--Menú--\n"
						+"1. Agregar vehiculo --- (Digite 1)\n"
						+"2. Pago factura --- (Digite 2)\n"
						+"3. Modificar información vehiculo registrado --- (Digite 3)\n"
						+"4. Consultas --- (Digite 4)\n"
						+"5. Opciones Archivos planos --- (Digite 5)\n"
						+"6. Cerrar --- (Digite 0)\n"
						+"Opción: ");
				int opcion = sc.nextInt();
				int opcionParaSubmenus = 0;
				/*
				 * Se "optimizo" el menú
				 */
				
				switch (opcion) {
				case 1:
					Factura.generarFactura();
					break;
					
				case 2:
					Factura.pagarFactura();
					break;
					
				case 3:
					Factura.actualizarInformacion();
					break;
					
				case 4:
					do {
						System.out.print("\n\t--Menú para Consultas/Mostrar--\n"
								+"1. Espacios vacios u ocupados --- (Digite 1)\n"
								+"2. Información de un vehiculo en especifico --- (Digite 2)\n"
								+"3. Información de todos los vehiculos --- (Digite 3)\n"
								+"4. Consulta facturas pendientes --- (Digite 4)\n"
								+"5. Consulta facturas canceladas --- (Digite 5)\n"
								+"0. Volver al menú anterior --- (Digite 0)\n"
								+"Opción: ");
						opcionParaSubmenus = sc.nextInt();
						
						switch (opcionParaSubmenus) {
							case 1:
								System.out.print("\nEspacios ocupados(1) --- Espacios vacios(2): ");
								System.out.print(Vehiculo.contabilizacionVehiculos(sc.nextInt()));
								break;

							case 2:
								Vehiculo.mostrarVehiculoPorPlaca();
								break;
						
							case 3:
								Vehiculo.mostrarVehiculos();
								break;
						
							case 4:
								Factura.mostrarFacturasPendientes();
								break;
						
							case 5:
								Factura.mostrarFacturasCanceladas();
								break;
								
							case 0:
								opcionParaSubmenus = 0;
								break;
						
							default:
								System.out.print("\nOpcion fuera de rango, vuelva a intentar ...\n");
								break;
						}
					
					}while(opcionParaSubmenus!=0);
					break;
					
				case 5:
					do {
						System.out.print("\n\t--Menú para funciones con archivos planos--\n"
								+"1. Guardar información de facturas pendientes --- (Digite 3)\n"
								+"2. Cargar información de facturas pendientes--- (digite 4)\n"
								+"0. Volver al menú anterior --- (Digite 0)\n"
								+"Opción: ");
						opcionParaSubmenus = sc.nextInt();
						
						switch (opcionParaSubmenus) {
							case 1:
								Factura.guardarInformacionFacturasPendientes();
								System.out.print("\nFacturas registradas correctamente\n");
								break;
							case 2:
								ListaDobleFactura.cargarFacturasPendientes();
								System.out.print("\nFacturas cargadas exitosamente\n");
								break;
							case 0:
								opcionParaSubmenus =0;
								break;
							default:
								System.out.print("\nOpcion fuera de rango, vuelva a intentar ...\n");
								break;
						}
					}while(opcionParaSubmenus!=0);
					break;

				case 0:
					this.setEstadoUsuario(false);
					System.out.println("\nHasta luego ...");
					break;
					
				default:
					System.out.println("\nOpcion fuera del rango.\n"
							+"Intente de nuevo");
					break;
				}
			}else{
				System.out.println("\nSe ha equivocado."+"\n"+"intente de nuevo ...\n");
				sc.nextLine();
			}
		}while(this.getEstadoUsuario());	
	}
}
