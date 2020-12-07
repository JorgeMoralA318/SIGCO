package Vista;

import DAO.AccesoDAO;
import java.awt.Component;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import sigco.Principal;

public class recorrerMenu {

    //metodo para recorrer componente de un JMenuBar y bloquear JmenuItem   
    public void recorrer(JMenuBar jmbar) {
        String r;
        AccesoDAO d = new AccesoDAO();
        for (Component c1 : jmbar.getComponents()) {
            if (c1.getClass().equals(javax.swing.JMenu.class)) {
                for (Component c2 : ((JMenu) c1).getMenuComponents()) {
                    if (!c2.getClass().equals(javax.swing.JPopupMenu.Separator.class)) {
                        r = d.obtener_acceso(((JMenuItem) c2).getName(), Principal.lblCodRol.getText());
                        System.out.println(r);
                        if (r.equals("0")) {
                            ((JMenuItem) c2).setEnabled(false);
                        }

                    }
                }
            }
        }
    }
}
