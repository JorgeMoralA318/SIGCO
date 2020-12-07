/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.time.LocalDate;
import java.util.Calendar;

/**
 *
 * @author Helena
 */
public class mostrarFecha {
    
    
    public static String mostrarFecha(){
        String fecha ;
        //mostrar fecha del sistema
        LocalDate date = LocalDate.now();
        int dia = date.getDayOfMonth();
        int mes = date.getMonthValue();
        int año = date.getYear();
        Calendar c = Calendar.getInstance();
        int sem = c.get(Calendar.DAY_OF_WEEK);
        String español = "";
        switch (sem) {
            case 1:
                español = "domingo";
                break;
            case 2:
                español = "Lunes";
                break;
            case 3:
                español = "Martes";
                break;
            case 4:
                español = "Miercoles";
                break;
            case 5:
                español = "Jueves";
                break;
            case 6:
                español = "Viernes";
                break;
            case 7:
                español = "Sábado";
                break;
        }
        fecha =español + " : * " + dia + "-" + mes + "-" + año + " *";
        return fecha;
    }
    
}
