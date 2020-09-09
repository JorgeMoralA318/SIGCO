/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import DAO.EmpresaDAO;
import DAO.SucursalDAO;
import VO.EmpresaVO;
import VO.SucursalVO;
import static Vista.barrioForm.txtciudad;
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
public class sucursalForm extends javax.swing.JFrame {

    SucursalVO vo = new SucursalVO();
    SucursalDAO dao = new SucursalDAO();

    /**
     * Creates new form departamentoVista
     */
    public sucursalForm() {
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
        String sucursal = txtsucursal.getText();
        String ruc = txtruc.getText();
        String telefono = txttelefono.getText();
        String direccion = txtdireccion.getText();
        String empresa = txtcodempresa.getText();

        vo.setCodigo(codigo);
        vo.setSucursal(sucursal);
        vo.setRuc(ruc);
        vo.setDireccion(direccion);
        vo.setTelefono(telefono);
        vo.setCodEmpresa(empresa);

        dao.Agregar_SucursalVO(vo);

    }

    void actualizar() {
        String codigo = txtcodigo.getText();
        String sucursal = txtsucursal.getText();
        String ruc = txtruc.getText();
        String telefono = txttelefono.getText();
        String direccion = txtdireccion.getText();
        String empresa = txtcodempresa.getText();

        vo.setCodigo(codigo);
        vo.setSucursal(sucursal);
        vo.setRuc(ruc);
        vo.setDireccion(direccion);
        vo.setTelefono(telefono);
        vo.setCodEmpresa(empresa);

        dao.Modificar_SucursalVO(vo);

    }

    //metodo paara cerra formulario actual y abrir principalç
    void cambiar_form() {
        sucursalVista v = new sucursalVista();
        v.setResizable(false);
        v.setLocationRelativeTo(null);
        v.setTitle("Sucursal");
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
        } else if (txtruc.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llene los campos requeridos");
            txtruc.requestFocus();
            txtruc.setBackground(Color.YELLOW);
            return false;

        } else if (txtsucursal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llene los campos requeridos");
            txtsucursal.requestFocus();
            txtsucursal.setBackground(Color.YELLOW);
            return false;

        } else if (txtdireccion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llene los campos requeridos");
            txtdireccion.requestFocus();
            txtdireccion.setBackground(Color.YELLOW);
            return false;

        } else if (txttelefono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llene los campos requeridos");
            txttelefono.requestFocus();
            txttelefono.setBackground(Color.YELLOW);
            return false;

        } else if (txtcodempresa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llene los campos requeridos");
            txtcodempresa.requestFocus();
            txtcodempresa.setBackground(Color.YELLOW);
            return false;

        } else {
            return true;
        }
    }

    void vaciar() {
        txtcodigo.setText("");
        txtsucursal.setText("");
        txtruc.setText("");
        txttelefono.setText("");
        txtdireccion.setText("");
        txtcodempresa.setText("");
        txtnomempresa.setText("");
    }

    private void obtener_codigo() {
        dao = new SucursalDAO();
        txtcodigo.setText("SU-" + dao.obtener_id());
        txtcodigo.requestFocus();
    }

    public void listarCodigo(String codigo) {
        ArrayList<SucursalVO> list = dao.Listar_SucursalVO(codigo);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[5];
                vo = list.get(i);
                //almacenamos valores en el vector fila
                fila[0] = vo.getSucursal();
                fila[1] = vo.getRuc();
                fila[2] = vo.getDireccion();
                fila[3] = vo.getTelefono();
                fila[4] = vo.getCodEmpresa();
                
                txtsucursal.setText((String) fila[0]);
                txtruc.setText((String) fila[1]);
                txtdireccion.setText((String) fila[2]);
                txttelefono.setText((String) fila[3]);
                txtcodempresa.setText((String) fila[4]);
                
                lblaction.setText("Update");

            }
        } else {
            if (txtcodigo.getText().length() > 0) {
                lblaction.setText("Add");
                txtsucursal.setText("");
            }

        }

    }

    void eliminar() {
        if (txtcodigo.getText().length() > 0) {
            String codigo = txtcodigo.getText();
            vo.setCodigo(codigo);
            dao.Eliminar_SucursalVO(vo);
            cambiar_form();
        } else {
            JOptionPane.showMessageDialog(null, "Nada que eliminar");
            txtcodigo.requestFocus();
        }

    }

    private void subir(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtcodempresa.requestFocus();
        }
    }
    
    
    
    public void listar_empresa(String codigo) {
       EmpresaDAO da = new EmpresaDAO();
       EmpresaVO v;
        ArrayList<EmpresaVO> list = da.Listar_EmpresaVO(codigo);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[1];
                v = list.get(i);
                //almacenamos valores en el vector fila
                fila[0] = v.getEmpresa();
                txtnomempresa.setText((String) fila[0]);
                b_guardar.requestFocus();

            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un codigo existente");
            txtcodempresa.setText("");
            txtnomempresa.setText("");
            txtcodempresa.requestFocus();
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
        txtsucursal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtruc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        txtcodempresa = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtnomempresa = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        m_guardar = new javax.swing.JMenuItem();
        m_salir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(236, 233, 216));

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

        jLabel2.setText("Sucursal :");

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

        txtsucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsucursalActionPerformed(evt);
            }
        });
        txtsucursal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsucursalKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsucursalKeyReleased(evt);
            }
        });

        jLabel4.setText("Código :");

        txtruc.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtruc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtrucFocusLost(evt);
            }
        });
        txtruc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrucActionPerformed(evt);
            }
        });
        txtruc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrucKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtrucKeyReleased(evt);
            }
        });

        jLabel5.setText("R.U.C :");

        txtdireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdireccionActionPerformed(evt);
            }
        });
        txtdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdireccionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdireccionKeyReleased(evt);
            }
        });

        jLabel3.setText("Dirección :");

        jLabel6.setText("Teléfono :");

        txttelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttelefonoActionPerformed(evt);
            }
        });
        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttelefonoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttelefonoKeyReleased(evt);
            }
        });

        txtcodempresa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcodempresaFocusLost(evt);
            }
        });
        txtcodempresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcodempresaActionPerformed(evt);
            }
        });
        txtcodempresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcodempresaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcodempresaKeyReleased(evt);
            }
        });

        jLabel7.setText("Empresa :");

        txtnomempresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnomempresaActionPerformed(evt);
            }
        });
        txtnomempresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnomempresaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnomempresaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtsucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtcodempresa, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtnomempresa))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
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
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcodempresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtnomempresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_guardar)
                    .addComponent(b_guardarynuevo)
                    .addComponent(b_cancelar)
                    .addComponent(b_eliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
        txtruc.requestFocus();
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

    private void txtsucursalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsucursalKeyReleased
        // TODO add your handling code here:
        if (txtsucursal.getText().length() >= 4) {
            txtsucursal.setBackground(Color.white);
        } else {
            txtsucursal.setBackground(Color.yellow);
        }
    }//GEN-LAST:event_txtsucursalKeyReleased

    private void txtsucursalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsucursalKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtruc.requestFocus();

        }
    }//GEN-LAST:event_txtsucursalKeyPressed

    private void txtsucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsucursalActionPerformed
        // TODO add your handling code here:
        txtdireccion.requestFocus();
    }//GEN-LAST:event_txtsucursalActionPerformed

    private void txtrucFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtrucFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrucFocusLost

    private void txtrucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrucActionPerformed
        // TODO add your handling code here:
        txtsucursal.requestFocus();
    }//GEN-LAST:event_txtrucActionPerformed

    private void txtrucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrucKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrucKeyReleased

    private void txtdireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdireccionActionPerformed
        // TODO add your handling code here:
        txttelefono.requestFocus();
    }//GEN-LAST:event_txtdireccionActionPerformed

    private void txtdireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtsucursal.requestFocus();

        }
    }//GEN-LAST:event_txtdireccionKeyPressed

    private void txtdireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdireccionKeyReleased

    private void txttelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttelefonoActionPerformed
        // TODO add your handling code here:
        txtcodempresa.requestFocus();
    }//GEN-LAST:event_txttelefonoActionPerformed

    private void txttelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtdireccion.requestFocus();

        }
    }//GEN-LAST:event_txttelefonoKeyPressed

    private void txttelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelefonoKeyReleased

    private void txtcodempresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodempresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodempresaActionPerformed

    private void txtcodempresaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodempresaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txttelefono.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtcodempresa.getText().length() == 0) {
                empresaVista dp = new empresaVista();
                dp.setResizable(false);
                dp.setLocationRelativeTo(null);
                dp.lblejecucion.setText("Sucursal");
                dp.setVisible(true);
            }else{
            listar_empresa(txtcodempresa.getText());
            }
        }  
    }//GEN-LAST:event_txtcodempresaKeyPressed

    private void txtcodempresaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodempresaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodempresaKeyReleased

    private void txtnomempresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnomempresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnomempresaActionPerformed

    private void txtnomempresaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnomempresaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnomempresaKeyPressed

    private void txtnomempresaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnomempresaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnomempresaKeyReleased

    private void txtrucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrucKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtcodigo.requestFocus();

        }
    }//GEN-LAST:event_txtrucKeyPressed

    private void txtcodempresaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcodempresaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodempresaFocusLost

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
            java.util.logging.Logger.getLogger(sucursalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sucursalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sucursalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sucursalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new sucursalForm().setVisible(true);
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
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
    public static javax.swing.JTextField txtcodempresa;
    public static javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtnomempresa;
    public static javax.swing.JTextField txtruc;
    private javax.swing.JTextField txtsucursal;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
