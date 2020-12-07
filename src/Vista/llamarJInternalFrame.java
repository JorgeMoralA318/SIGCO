/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import sigco.Principal;

/**
 *
 * @author Helena
 */
public class llamarJInternalFrame {
    
    public static void llamarFormulario(JInternalFrame miFrame, JDesktopPane miPanel, String titulo, boolean cambiaTamaño) {
        if(frames_abiertos(miFrame)){
            
        }else{
        miFrame.setResizable(cambiaTamaño);
        miFrame.setTitle(titulo);
        miPanel.add(miFrame);
        Dimension desktopsize = miPanel.getSize();
        Dimension FrameSize = miFrame.getSize();
        miFrame.setLocation((desktopsize.width - FrameSize.width) / 2, (desktopsize.height - FrameSize.height) / 2);
        miFrame.setVisible(true);
        miFrame.setIconifiable(true);}}
    


private static boolean frames_abiertos(JInternalFrame f){
    JInternalFrame [] frame = Principal.panelPrincipal.getAllFrames();
    for (int i = 0; i < frame.length; i++) {
        if(f.getClass().isInstance(frame[i])){
            JOptionPane.showMessageDialog(null, "El Programa ya está en Ejecución");
            return true;
           
        }
        
    }
return false;
}

}
