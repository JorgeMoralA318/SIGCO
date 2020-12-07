/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Conexion.Conectar;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import sigco.Principal;
import javax.swing.JOptionPane;
import java.sql.*;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Helena
 */
public class login extends javax.swing.JInternalFrame{


    public login() {
        initComponents();
        lblfecha.setText(mostrarFecha.mostrarFecha());
    }

//hacer consulta en la bd
    private void acceder(String Alias, String Contraseña) {
        Conectar conec = new Conectar();
        int resultado = 0;
        String sql = "SELECT * FROM usuario  WHERE Login=? AND Clave=? ;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, Alias);
            ps.setString(2, Contraseña);
            rs = ps.executeQuery();
            if (rs.next()) {
                resultado = 1;
                if (resultado == 1) {

                    Principal.JMenuBar.setVisible(true);
                    Principal.b_salir.setVisible(false);
                    Principal.b_iniciar.setVisible(false);
                    Principal.b_cerrar.setVisible(true);
                    Principal.b_perfil.setVisible(true);
                    listar(txtUsuario.getText(), DigestUtils.sha1Hex(new String(txtContraseña.getPassword())));
                    this.dispose();
                    JOptionPane.showMessageDialog(null, "Bienvenido al sistema");
                    Principal.recorre_menu();
                    
                    

                }
            } else {
                JOptionPane.showMessageDialog(null, "Error al comprobar los datos, Por favor Intente de Nuevo");
                txtUsuario.setText("");
                txtContraseña.setText("");
                txtUsuario.requestFocus();
            }
        } catch (SQLException | HeadlessException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                conec.desconectar();
            } catch (SQLException ex) {
            }
        }
    }

     //funcion para recorrer menu
    

    //metodo para listar Usuario y Empleado 
    private void listar(String alias, String contraseña) {
        Conectar conec = new Conectar();
        PreparedStatement ps = null;
        String sql = "SELECT CONCAT(a.nombre, \" \" , a.apellido),b.codigo,c.codigo,c.rol\n"
                + "FROM personal a INNER JOIN usuario b ON a.codigo = b.codPersonal \n"
                + "INNER JOIN rol c ON b.codRol = c.codigo  WHERE login =? AND clave =?";

        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, alias);
            ps.setString(2, contraseña);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String codEmpleado = rs.getString(1);
                String nombre = rs.getString(2);
                String codRol = rs.getString(3);
                String rol = rs.getString(4);

                //crea un vector donde  está la informacion (se crea una fila)
                String[] resultado = {codEmpleado, codRol, nombre, rol};
                Principal.lblEmpleado.setText(resultado[0]);
                Principal.lblRol.setText(resultado[3]);
                Principal.lblCodUsuario.setText(resultado[2]);
                Principal.lblCodRol.setText(resultado[1]);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
    private void iniciar_(){
        if((!txtUsuario.getText().isEmpty()) && txtContraseña.getPassword().length>0){
        String usuario = txtUsuario.getText();
        String contraseña = new String(txtContraseña.getPassword());
        String encriptado = DigestUtils.sha1Hex(contraseña);
        acceder(usuario, encriptado);}
        else{
            JOptionPane.showMessageDialog(null, "campo usuario y contraseña no puede ser nulo");
            txtUsuario.requestFocus();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        lblfecha = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        lblprograma = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        lblejecucion = new javax.swing.JLabel();
        b_ingresar = new javax.swing.JButton();
        b_cancelar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtContraseña = new javax.swing.JPasswordField();

        jButton3.setText("Nuevo");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(248, 249, 249));

        jToolBar1.setBackground(java.awt.SystemColor.inactiveCaption);
        jToolBar1.setRollover(true);

        lblfecha.setText("jLabel1");
        jToolBar1.add(lblfecha);
        jToolBar1.add(jSeparator1);

        jLabel1.setText("SIGCO");
        jToolBar1.add(jLabel1);
        jToolBar1.add(jSeparator2);

        lblprograma.setText("Login");
        jToolBar1.add(lblprograma);
        jToolBar1.add(jSeparator3);
        jToolBar1.add(lblejecucion);
        lblejecucion.getAccessibleContext().setAccessibleName("lblejecucion");

        b_ingresar.setBackground(new java.awt.Color(0, 153, 153));
        b_ingresar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_ingresar.setForeground(new java.awt.Color(255, 255, 255));
        b_ingresar.setText("Ingresar");
        b_ingresar.setBorderPainted(false);
        b_ingresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_ingresarActionPerformed(evt);
            }
        });
        b_ingresar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                b_ingresarKeyPressed(evt);
            }
        });

        b_cancelar.setBackground(new java.awt.Color(0, 153, 153));
        b_cancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_cancelar.setForeground(new java.awt.Color(255, 255, 255));
        b_cancelar.setText("Cancelar");
        b_cancelar.setBorderPainted(false);
        b_cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cancelarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Usuario :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Contraseña :");

        txtUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        txtContraseña.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtContraseña, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(b_ingresar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_cancelar)
                .addGap(111, 111, 111))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContraseña, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jLabel4))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_ingresar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_b_cancelarActionPerformed

    private void b_ingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_ingresarActionPerformed
        iniciar_();

    }//GEN-LAST:event_b_ingresarActionPerformed

    private void b_ingresarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_ingresarKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            iniciar_();
        }
    }//GEN-LAST:event_b_ingresarKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_cancelar;
    private javax.swing.JButton b_ingresar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    public static javax.swing.JLabel lblejecucion;
    private javax.swing.JLabel lblfecha;
    private javax.swing.JLabel lblprograma;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}