/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sigco;

import Vista.barrioVista;
import Vista.ciudadVista;
import Vista.departamentoVista;
import Vista.empresaVista;
import Vista.paisVista;
import Vista.profesionVista;
import Vista.sucursalVista;
import Vista.zonaVista;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Helena
 */
public class SIGCO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

            Principal f = new Principal();
            f.setTitle("Menu Principal  - Prueba Unitaria");
            f.setLocationRelativeTo(null);
            f.setResizable(false);
            f.setVisible(true);

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            System.out.println(e.getMessage());
        }

    }

}
