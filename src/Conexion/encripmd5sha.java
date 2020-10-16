/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Helena
 */
public class encripmd5sha {
    
    public static void main(String[] args) {
        
        String pass= "admin";
        
        String encriptado = DigestUtils.sha1Hex(pass);
        
        System.out.println("texto normal : "+pass + "   encriptado con sha1Hex : "+ encriptado);
        System.out.println(encriptado.length());
    }
    
}
