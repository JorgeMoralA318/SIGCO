/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import DAO.PersonalDAO;
import DAO.RolDAO;
import DAO.UsuarioDAO;
import VO.PersonalVO;
import VO.RolVO;
import VO.UsuarioVO;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Helena
 */
public class usuarioForm extends javax.swing.JFrame {

    UsuarioVO vo ;
    UsuarioDAO dao;
    mostrarFecha fecha = new mostrarFecha();
    obtenerCodigo cod = new obtenerCodigo();

    /**
     * Creates new form departamentoVista
     */
    public usuarioForm() {
        initComponents();
        lblfecha.setText(fecha.mostrarFecha());
        obtenerCodigo();

    }

    //metodo para capturar y enviar datos
    void guardar() {
        vo = new UsuarioVO();
        dao = new UsuarioDAO();

        String codigo = txtcodigo.getText();
        String codPersonal = txtEmpleado.getText();
        String codRol = txtRol.getText();
        String login = txtUsuario.getText();
        String clave = txtContraseña.getText();
        String encriptado = DigestUtils.sha1Hex(clave);

        vo.setCodigo(codigo);
        vo.setCodPersonal(codPersonal);
        vo.setCodRol(codRol);
        vo.setLogin(login);
        vo.setClave(encriptado);
        dao.Agregar_UsuarioVO(vo);
    }

    void actualizar() {
        vo = new UsuarioVO();
        dao = new UsuarioDAO();
        String codigo = txtcodigo.getText();
        String codPersonal = txtEmpleado.getText();
        String codRol = txtRol.getText();
        String login = txtUsuario.getText();

        vo.setCodigo(codigo);
        vo.setCodPersonal(codPersonal);
        vo.setCodRol(codRol);
        vo.setLogin(login);
        dao.Modificar_UsuarioVO(vo);

    }

    //metodo paara cerrar formulario actual y abrir principal
    void cambiar_form() {
        usuarioVista v = new usuarioVista();
        v.setResizable(false);
        v.setLocationRelativeTo(null);
        v.setTitle("Datos Usuario");
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
        } else if (txtEmpleado.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llene los campos requeridos");
            txtEmpleado.requestFocus();
            txtEmpleado.setBackground(Color.YELLOW);
            return false;

        } else if (txtRol.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llene los campos requeridos");
            txtRol.requestFocus();
            txtRol.setBackground(Color.YELLOW);
            return false;
        } else if (txtUsuario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llene los campos requeridos");
            txtUsuario.requestFocus();
            txtUsuario.setBackground(Color.YELLOW);
            return false;

        } else if (txtContraseña.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llene los campos requeridos");
            txtContraseña.requestFocus();
            txtContraseña.setBackground(Color.YELLOW);
            return false;

        } else {
            return true;
        }
    }

    void vaciar() {
        txtcodigo.setText("");
        txtUsuario.setText("");
        txtEmpleado.setText("");
        txtRol.setText("");
        txtContraseña.setText("");
    }

    final void obtenerCodigo() {
        dao = new UsuarioDAO();
        txtcodigo.setText(dao.obtener_id());
    }

    void listarCodigo(String codigo) {
        dao = new UsuarioDAO();
        ArrayList<UsuarioVO> list = dao.Listar_UsuarioVO(codigo);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[4];
                vo = list.get(i);
                //almacenamos valores en el vector fila
                fila[0] = vo.getCodPersonal();
                fila[1] = vo.getCodRol();
                fila[2] = vo.getLogin();
                fila[3] = vo.getClave();

                txtEmpleado.setText((String) fila[0]);
                txtRol.setText((String) fila[1]);
                txtUsuario.setText((String) fila[2]);
                txtContraseña.setText((String) fila[3]);

                lblaction.setText("Update");
                listar_Empleado(txtEmpleado.getText());
                listar_Rol(txtRol.getText());

            }
        } else {
            if (txtcodigo.getText().length() > 0) {
                lblaction.setText("Add");
                
            }

        }

    }

    void eliminar() {
        if (txtcodigo.getText().length() > 0) {
            String codigo = txtcodigo.getText();
            vo.setCodigo(codigo);
            dao.Eliminar_UsuarioVO(vo);
            cambiar_form();
        } else {
            JOptionPane.showMessageDialog(null, "Nada que eliminar");
            txtcodigo.requestFocus();
        }

    }

   
    void listar_Rol(String codigo) {
        RolVO rvo;
        RolDAO rdao = new RolDAO();
        ArrayList<RolVO> list = rdao.Listar_RolVO(codigo);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[1];
                rvo = list.get(i);
                //almacenamos valores en el vector fila
                fila[0] = rvo.getRol();
                nombreRol.setText((String) fila[0]);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un codigo válido");
            txtRol.setText("");
            txtRol.requestFocus();
        }
    }

    
    void listar_Empleado(String codigo) {
        PersonalVO pvo;
        PersonalDAO pdao = new PersonalDAO();
        ArrayList<PersonalVO> list = pdao.Listar_PersonalVO(codigo);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[1];
                pvo = list.get(i);
                //almacenamos valores en el vector fila
                fila[0] = pvo.getNombre() + " " + pvo.getApellido();
                nombreEmpleado.setText((String) fila[0]);

            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un codigo válido");
            txtEmpleado.setText("");
            txtEmpleado.requestFocus();
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

        groupSexo = new javax.swing.ButtonGroup();
        groupEstadoCivil = new javax.swing.ButtonGroup();
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
        txtUsuario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtEmpleado = new javax.swing.JTextField();
        nombreEmpleado = new javax.swing.JTextField();
        txtRol = new javax.swing.JTextField();
        nombreRol = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        m_guardar = new javax.swing.JMenuItem();
        m_salir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(248, 249, 249));

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

        jLabel2.setText("Usuario :");

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

        b_guardar.setBackground(new java.awt.Color(0, 153, 153));
        b_guardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_guardar.setForeground(new java.awt.Color(255, 255, 255));
        b_guardar.setText("Guardar");
        b_guardar.setBorderPainted(false);
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

        b_guardarynuevo.setBackground(new java.awt.Color(0, 153, 153));
        b_guardarynuevo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_guardarynuevo.setForeground(new java.awt.Color(255, 255, 255));
        b_guardarynuevo.setText("Guardar y Nuevo");
        b_guardarynuevo.setBorderPainted(false);
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

        b_cancelar.setBackground(new java.awt.Color(0, 153, 153));
        b_cancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_cancelar.setForeground(new java.awt.Color(255, 255, 255));
        b_cancelar.setText("Cancelar");
        b_cancelar.setBorderPainted(false);
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

        b_eliminar.setBackground(new java.awt.Color(0, 153, 153));
        b_eliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_eliminar.setForeground(new java.awt.Color(255, 255, 255));
        b_eliminar.setText("Eliminar");
        b_eliminar.setBorderPainted(false);
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

        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyReleased(evt);
            }
        });

        jLabel4.setText("Código :");

        txtContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraseñaActionPerformed(evt);
            }
        });
        txtContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContraseñaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContraseñaKeyReleased(evt);
            }
        });

        jLabel3.setText("Contraseña :");

        jLabel20.setText("Cod. Empleado :");

        txtEmpleado.setToolTipText("");
        txtEmpleado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmpleadoFocusLost(evt);
            }
        });
        txtEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmpleadoKeyPressed(evt);
            }
        });

        nombreEmpleado.setToolTipText("");
        nombreEmpleado.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        nombreEmpleado.setEnabled(false);

        txtRol.setToolTipText("");
        txtRol.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRolFocusLost(evt);
            }
        });
        txtRol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRolKeyPressed(evt);
            }
        });

        nombreRol.setToolTipText("");
        nombreRol.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        nombreRol.setEnabled(false);
        nombreRol.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nombreRolFocusLost(evt);
            }
        });

        jLabel21.setText("Cod. Rol :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(b_guardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_guardarynuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_eliminar)
                        .addGap(6, 6, 6)
                        .addComponent(b_cancelar))
                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEmpleado)
                            .addComponent(txtRol, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombreRol, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtContraseña, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_guardarynuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        if (validar()) {
            switch (lblaction.getText()) {
                case "Add":
                    guardar();
                    cambiar_form();
                    break;

                case "Update":
                    actualizar();
                    cambiar_form();
                    break;

            }

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
            obtenerCodigo();
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

    }//GEN-LAST:event_b_guardarKeyReleased

    private void b_guardarynuevoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_guardarynuevoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_b_guardarynuevoKeyReleased

    private void b_eliminarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_eliminarKeyReleased

    }//GEN-LAST:event_b_eliminarKeyReleased

    private void b_cancelarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_cancelarKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_b_cancelarKeyReleased

    private void txtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyReleased
        // TODO add your handling code here:
        if (txtUsuario.getText().length() >= 4) {
            txtUsuario.setBackground(Color.white);
        } else {
            txtUsuario.setBackground(Color.yellow);
        }
    }//GEN-LAST:event_txtUsuarioKeyReleased

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
//            jdcfechaingreso.transferFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtContraseña.requestFocus();
        }
    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContraseñaActionPerformed

    private void txtContraseñaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseñaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtUsuario.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

        }
    }//GEN-LAST:event_txtContraseñaKeyPressed

    private void txtContraseñaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseñaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContraseñaKeyReleased

    private void txtEmpleadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpleadoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtEmpleado.getText().length() == 0) {
                personalVista dp = new personalVista();
                dp.setResizable(false);
                dp.setLocationRelativeTo(null);
                dp.lblejecucion.setText("d_usuario");
                dp.setVisible(true);
            } else {
                listar_Empleado(txtEmpleado.getText());
                txtRol.requestFocus();
            }
        }
    }//GEN-LAST:event_txtEmpleadoKeyPressed

    private void txtRolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRolKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtRol.getText().length() == 0) {
                rolVista dp = new rolVista();
                dp.setResizable(false);
                dp.setLocationRelativeTo(null);
                dp.lblejecucion.setText("d_usuario");
                dp.setVisible(true);
            } else {
                listar_Rol(txtRol.getText());
                txtUsuario.requestFocus();
            }
        }
    }//GEN-LAST:event_txtRolKeyPressed

    private void nombreRolFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreRolFocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_nombreRolFocusLost

    private void txtRolFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRolFocusLost
        // TODO add your handling code here:
        if(!txtRol.getText().isEmpty())
        listar_Rol(txtRol.getText());

    }//GEN-LAST:event_txtRolFocusLost

    private void txtEmpleadoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmpleadoFocusLost
        // TODO add your handling code here:
        if(!txtEmpleado.getText().isEmpty())
        listar_Empleado(txtEmpleado.getText());

    }//GEN-LAST:event_txtEmpleadoFocusLost

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(usuarioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new usuarioForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_cancelar;
    private javax.swing.JButton b_eliminar;
    private javax.swing.JButton b_guardar;
    private javax.swing.JButton b_guardarynuevo;
    private javax.swing.ButtonGroup groupEstadoCivil;
    private javax.swing.ButtonGroup groupSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
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
    public static javax.swing.JTextField nombreEmpleado;
    public static javax.swing.JTextField nombreRol;
    private javax.swing.JTextField txtContraseña;
    public static javax.swing.JTextField txtEmpleado;
    public static javax.swing.JTextField txtRol;
    private javax.swing.JTextField txtUsuario;
    public static javax.swing.JTextField txtcodigo;
    // End of variables declaration//GEN-END:variables

    //varibales para enviar datos   
}
