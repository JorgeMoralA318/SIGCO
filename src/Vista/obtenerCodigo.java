/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

/**
 *
 * @author Helena
 */
public  class obtenerCodigo {
     public static String codigoDefecto(String dao_obtenerId,int longitudId) {
        String resultado;
        String cero = null;
        switch (longitudId) {
            case 1:
                cero = "0000";
                break;
            case 2:
                cero = "000";
                break;
            case 3:
                cero = "00";
                break;
            case 4:
                cero = "0";
                break;
            case 5:
                cero = "";
                break;
        }
        resultado = cero + dao_obtenerId;
        return resultado;
    }
    
}
