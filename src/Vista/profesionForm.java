/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import DAO.ProfesionDAO;
import VO.ProfesionVO;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Helena
 */
public class profesionForm extends javax.swing.JFrame {

    ProfesionVO vo = new ProfesionVO();
    ProfesionDAO dao = new ProfesionDAO();

    /**
     * Creates new form departamentoVista
     */
    public profesionForm() {
        initComponents();
        mostrarFechaHora();
        obtener_codigo();
    }

    private void mostrarFechaHora() {
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
                español = "Vienes";
                break;
            case 7:
                español = "Sábado";
                break;
        }

        lblfecha.setText(español + " : * " + dia + "-" + mes + "-" + año + " *");
    }

    //metodo para capturar y enviar datos
    void guardar() {
        String codigo = txtcodigo.getText();
        String profesion= txtprofesion.getText();

        vo.setCodigo(codigo);
        vo.setProfesion(profesion);

        dao.Agregar_ProfesionVO(vo);

    }

    void actualizar() {
        String codigo = txtcodigo.getText();
        String profesion = txtprofesion.getText();

        vo.setCodigo(codigo);
        vo.setProfesion(profesion);

        dao.Modificar_ProfesionVO(vo);

    }

    //metodo paara cerrar formulario actual y abrir principal
    void cambiar_form() {
        profesionVista v = new profesionVista();
        v.setResizable(false);
        v.setLocationRelativeTo(null);
        v.setTitle("Profesion");
        v.setVisible(true);
        this.dispose();
    }

    //metodo para validar campos
    private Boolean validar() {
        if (txtcodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llene los campos requeridos");
            txtcodigo.requestFocus();
            txtcodigo.setBackground(Color.YELLOW);
            return false;
        } else if (txtprofesion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llene los campos requeridos");
            txtprofesion.requestFocus();
            txtprofesion.setBackground(Color.YELLOW);
            return false;

        } else {
            return true;
        }
    }

    void vaciar() {
        txtcodigo.setText("");
        txtprofesion.setText("");
    }

    private void obtener_codigo() {
        dao = new ProfesionDAO();
        txtcodigo.setText("PR-" + dao.obtener_id());
        txtcodigo.requestFocus();
    }

    public void listarCodigo(String codigo) {
        ArrayList<ProfesionVO> list = dao.Listar_ProfesionVO(codigo);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[1];
                vo = list.get(i);
                //almacenamos valores en el vector fila
                fila[0] = vo.getProfesion();
                txtprofesion.setText((String) fila[0]);
                lblaction.setText("Update");

            }
        } else {
            if (txtcodigo.getText().length() > 0) {
                lblaction.setText("Add");
                txtprofesion.setText("");
            }

        }

    }

    void eliminar() {
        if (txtcodigo.getText().length() > 0) {
            String codigo = txtcodigo.getText();
            vo.setCodigo(codigo);
            dao.Eliminar_ProfesionVO(vo);
            cambiar_form();
        } else {
            JOptionPane.showMessageDialog(null, "Nada que eliminar");
            txtcodigo.requestFocus();
        }

    }

    private void subir(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtprofesion.requestFocus();
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

        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        lblfecha = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        lblaction = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        lblejecucion = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtcodigo = new javax.swing.JTextField();
        b_guardar = new javax.swing.JButton();
        b_guardarynuevo = new javax.swing.JButton();
        b_cancelar = new javax.swing.JButton();
        b_eliminar = new javax.swing.JButton();
        txtprofesion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        m_guardar = new javax.swing.JMenuItem();
        m_salir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jToolBar1.setBackground(javax.swing.UIManager.getDefaults().getColor("InternalFrame.inactiveTitleBackground"));
        jToolBar1.setRollover(true);

        lblfecha.setText("jLabel1");
        jToolBar1.add(lblfecha);
        jToolBar1.add(jSeparator1);

        jLabel1.setText("Sistema de Gestión Comercial  :::SIGCO:::");
        jToolBar1.add(jLabel1);
        jToolBar1.add(jSeparator2);

        lblaction.setText("Add");
        jToolBar1.add(lblaction);
        jToolBar1.add(jSeparator3);
        jToolBar1.add(lblejecucion);

        jLabel2.setText("Profesión :");

        txtcodigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcodigoFocusLost(evt);
            }
        });
        txtcodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcodigoActionPerformed(evt);
            }
        });
        txtcodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcodigoKeyReleased(evt);
            }
        });

        b_guardar.setText("Guardar");
        b_guardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_guardarActionPerformed(evt);
            }
        });
        b_guardar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                b_guardarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                b_guardarKeyReleased(evt);
            }
        });

        b_guardarynuevo.setText("Guardar y Nuevo");
        b_guardarynuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_guardarynuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_guardarynuevoActionPerformed(evt);
            }
        });
        b_guardarynuevo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                b_guardarynuevoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                b_guardarynuevoKeyReleased(evt);
            }
        });

        b_cancelar.setText("Cancelar");
        b_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cancelarActionPerformed(evt);
            }
        });
        b_cancelar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                b_cancelarKeyReleased(evt);
            }
        });

        b_eliminar.setText("Eliminar");
        b_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_eliminarActionPerformed(evt);
            }
        });
        b_eliminar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                b_eliminarKeyReleased(evt);
            }
        });

        txtprofesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtprofesionActionPerformed(evt);
            }
        });
        txtprofesion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtprofesionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtprofesionKeyReleased(evt);
            }
        });

        jLabel4.setText("Código :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtprofesion, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(b_guardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_guardarynuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_eliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_cancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtprofesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_guardar)
                    .addComponent(b_guardarynuevo)
                    .addComponent(b_cancelar)
                    .addComponent(b_eliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenu1.setBorder(null);
        jMenu1.setText("Archivo");

        m_guardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK));
        m_guardar.setText("Guardar");
        m_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_guardarActionPerformed(evt);
            }
        });
        jMenu1.add(m_guardar);

        m_salir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        m_salir.setText("Salir");
        m_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_salirActionPerformed(evt);
            }
        });
        jMenu1.add(m_salir);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void m_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_guardarActionPerformed
        // TODO add your handling code here:
        if (validar() == true) {
            switch (lblaction.getText()) {
                case "Add":
                    guardar();

                case "Update":
                    actualizar();
                    break;

            }
            cambiar_form();
        }
    }//GEN-LAST:event_m_guardarActionPerformed

    private void b_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_guardarActionPerformed
        // TODO add your handling code here:
        if (validar() == true) {
            switch (lblaction.getText()) {
                case "Add":
                    guardar();
                    break;

                case "Update":
                    actualizar();
                    break;

            }
            cambiar_form();
        }


    }//GEN-LAST:event_b_guardarActionPerformed


    private void b_guardarynuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_guardarynuevoActionPerformed
        // TODO add your handling code here:
        if (validar() == true) {
            switch (lblaction.getText()) {
                case "Add":
                    guardar();
                    break;

                case "Update":
                    if (validar() == true) {
                        actualizar();
                        break;

                    }
            }
            vaciar();
            obtener_codigo();
        }
    }//GEN-LAST:event_b_guardarynuevoActionPerformed

    private void m_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_salirActionPerformed
        // TODO add your handling code here:
        cambiar_form();
    }//GEN-LAST:event_m_salirActionPerformed

    private void b_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cancelarActionPerformed
        // TODO add your handling code here:
        cambiar_form();
    }//GEN-LAST:event_b_cancelarActionPerformed

    private void txtcodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodigoActionPerformed
        // TODO add your handling code here:
        txtprofesion.requestFocus();
    }//GEN-LAST:event_txtcodigoActionPerformed

    private void txtcodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoKeyReleased
        // TODO add your handling code here:
        if (txtcodigo.getText().length() >= 1) {
            txtcodigo.setBackground(Color.white);
        } else {
            txtcodigo.setBackground(Color.yellow);
        }
    }//GEN-LAST:event_txtcodigoKeyReleased

    private void txtcodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcodigoFocusLost
        // TODO add your handling code here:
        listarCodigo(txtcodigo.getText());
    }//GEN-LAST:event_txtcodigoFocusLost

    private void b_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_eliminarActionPerformed
        // TODO add your handling code here:
        int mensaje = JOptionPane.showConfirmDialog(null, "Estas Seguro que deseas Eliminar?", "Atención", JOptionPane.YES_NO_OPTION);
        if (mensaje == JOptionPane.YES_OPTION) {
            eliminar();
        }
    }//GEN-LAST:event_b_eliminarActionPerformed

    private void b_guardarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_guardarKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_b_guardarKeyPressed

    private void b_guardarynuevoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_guardarynuevoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_guardarynuevoKeyPressed

    private void b_guardarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_guardarKeyReleased
        // TODO add your handling code here:
        subir(evt);
    }//GEN-LAST:event_b_guardarKeyReleased

    private void b_guardarynuevoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_guardarynuevoKeyReleased
        // TODO add your handling code here:
        subir(evt);
    }//GEN-LAST:event_b_guardarynuevoKeyReleased

    private void b_eliminarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_eliminarKeyReleased
        // TODO add your handling code here:
        subir(evt);
    }//GEN-LAST:event_b_eliminarKeyReleased

    private void b_cancelarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_cancelarKeyReleased
        // TODO add your handling code here:
        subir(evt);
    }//GEN-LAST:event_b_cancelarKeyReleased

    private void txtprofesionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprofesionKeyReleased
        // TODO add your handling code here:
        if (txtprofesion.getText().length() >= 4) {
            txtprofesion.setBackground(Color.white);
        } else {
            txtprofesion.setBackground(Color.yellow);
        }
    }//GEN-LAST:event_txtprofesionKeyReleased

    private void txtprofesionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprofesionKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtcodigo.requestFocus();

        }
    }//GEN-LAST:event_txtprofesionKeyPressed

    private void txtprofesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprofesionActionPerformed
        // TODO add your handling code here:
        b_guardar.requestFocus();
    }//GEN-LAST:event_txtprofesionActionPerformed

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
            java.util.logging.Logger.getLogger(profesionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(profesionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(profesionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(profesionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new profesionForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_cancelar;
    private javax.swing.JButton b_eliminar;
    private javax.swing.JButton b_guardar;
    private javax.swing.JButton b_guardarynuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    public static javax.swing.JLabel lblaction;
    public static javax.swing.JLabel lblejecucion;
    private javax.swing.JLabel lblfecha;
    private javax.swing.JMenuItem m_guardar;
    private javax.swing.JMenuItem m_salir;
    public static javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtprofesion;
    // End of variables declaration//GEN-END:variables
}
