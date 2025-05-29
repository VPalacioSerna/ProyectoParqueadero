package ProyectoEstructurasDeDatos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Tiempo {

	public String dia (){  
        LocalDate fecha = LocalDate.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yy");
        String dia = fecha.format(formatoFecha);
        return (dia);
    }
	
    public static int hora (){
        LocalTime hora = LocalTime.now();      
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        // es la variable para guardar la hora como tal
        String tiempo = hora.format(formatoHora);
        // la hora es formato HH:mm:ss y para identificar cada parte se usa : como separlo,guardarlo en el vector y luego convertirlo a entero 
        String [] partes = tiempo.split(":");
        //("hora: "+partes[0]);
        //("minuto: "+partes[1]);
        //("segundos: "+partes[2]);
        
        int h1= Integer.parseInt(partes[0]);
        int m1= Integer.parseInt(partes[1]);
        int s1= Integer.parseInt(partes[2]);
        
        return ((h1*60)+m1+(s1/60));       
    }
    
    public static int obtenerHoraSalida() {
		return Tiempo.hora();
	}
}
