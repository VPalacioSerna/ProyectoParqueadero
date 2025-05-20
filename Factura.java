package proyectoparqueadero;

import java.util.Date;

public class Factura {

  // Atributos
  private int contadorDeTiempo;
  protected double total;
  private double fraccionPorTiempo;
  protected String codigoParaPagar;
  public Date fecha = new Date();

  // Constructores
  public Factura() {
  }

  public Factura(int contadorDeTiempo, double total, double fraccionPorTiempo, String codigoParaPagar, Date fecha) {
    super();
    this.contadorDeTiempo = contadorDeTiempo;
    this.total = total;
    this.fraccionPorTiempo = fraccionPorTiempo;
    this.codigoParaPagar = codigoParaPagar;
    this.fecha = fecha;
  }

  // Getters y setters
  public int getContadorDeTiempo() {
    return contadorDeTiempo;
  }

  public void setContadorDeTiempo(int contadorDeTiempo) {
    this.contadorDeTiempo = contadorDeTiempo;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public double getFraccionPorTiempo() {
    return fraccionPorTiempo;
  }

  public void setFraccionPorTiempo(double fraccionPorTiempo) {
    this.fraccionPorTiempo = fraccionPorTiempo;
  }

  public String getCodigoParaPagar() {
    return codigoParaPagar;
  }

  public void setCodigoParaPagar(String codigoParaPagar) {
    this.codigoParaPagar = codigoParaPagar;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

}
