/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sigco;

import java.awt.Frame;


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


            Principal f = new Principal();
            f.setTitle("Menu Principal  - Prueba Unitaria");
            f.setLocationRelativeTo(null);
            f.setExtendedState(Frame.MAXIMIZED_BOTH);
            f.setResizable(false);
            f.setVisible(true);


    }

}
