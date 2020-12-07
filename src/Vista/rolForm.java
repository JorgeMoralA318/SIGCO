/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import DAO.AccesoDAO;
import DAO.RolDAO;
import Tabla.tablaAcceso;
import Tabla.tablaModulo;
import VO.AccesoVO;
import VO.RolVO;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Helena
 */
public class rolForm extends javax.swing.JFrame {

    RolVO vo;
    RolDAO dao;
    AccesoDAO ad;
    AccesoVO av;
    tablaAcceso tabla;
    tablaModulo tm;

    /**
     * Creates new form departamentoVista
     */
    public rolForm() {
        initComponents();
        lblfecha.setText(mostrarFecha.mostrarFecha());
        obtener_codigo();
    }

    //metodo para capturar y enviar datos
    void guardar() {
        vo = new RolVO();
        dao = new RolDAO();
        String codigo = txtcodigo.getText();
        String nivel = txtNivel.getText();

        vo.setCodigo(codigo);
        vo.setRol(nivel);

        dao.Agregar_RolVO(vo);

    }

    void actualizar() {
        dao = new RolDAO();
        String codigo = txtcodigo.getText();
        String nivel = txtNivel.getText();

        vo.setCodigo(codigo);
        vo.setRol(nivel);
        dao.Modificar_RolVO(vo);

    }

    //metodo para cerra formulario actual y abrir principal
    void cambiar_form() {
        String va  = lblejecucion.getText();
        rolVista v = new rolVista();
        v.setResizable(false);
        v.setLocationRelativeTo(null);
        v.setTitle("Datos Rol de Usuario");
        v.lblejecucion.setText(va);
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
        } else if (txtNivel.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llene los campos requeridos");
            txtNivel.requestFocus();
            txtNivel.setBackground(Color.YELLOW);
            return false;
        } else {
            txtcodigo.setBackground(Color.white);
            txtNivel.setBackground(Color.white);
            return true;
        }
    }

    void vaciar() {
        txtcodigo.setText("");
        txtNivel.setText("");
    }

    private void obtener_codigo() {
        dao = new RolDAO();
        txtcodigo.setText(dao.obtener_id());
    }

    public void listarCodigo(String codigo) {
        ArrayList<RolVO> list = dao.Listar_RolVO(codigo);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[1];
                vo = list.get(i);
                //almacenamos valores en el vector fila
                fila[0] = vo.getRol();
                txtNivel.setText((String) fila[0]);
                lblaction.setText("Update");
                mostrarModulo("");
                mostrarAcceso("", codigo);

            }
        } else {
            if (txtcodigo.getText().length() > 0) {
                lblaction.setText("Add");
                txtNivel.setText("");
                mostrarModulo("");

            }

        }

    }

    private void mostrarAcceso(String codModulo, String codRol) {
        //cargar Jtable tabla
        tabla = new tablaAcceso();
        tabla.mostrar(codModulo, codRol);
        //Centrar Columna los valores de la columna 0
        DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
        centrar.setHorizontalAlignment(SwingConstants.CENTER);
        tbAcceso.getColumnModel().getColumn(0).setCellRenderer(centrar);
        tbAcceso.getColumnModel().getColumn(4).setCellRenderer(centrar);
        tbAcceso.getColumnModel().getColumn(5).setCellRenderer(centrar);
        tbAcceso.getColumnModel().getColumn(6).setCellRenderer(centrar);
        tbAcceso.getColumnModel().getColumn(7).setCellRenderer(centrar);
        addCheckBox(4, tbAcceso);
        addCheckBox(5, tbAcceso);
        addCheckBox(6, tbAcceso);
        addCheckBox(7, tbAcceso);
    }

    private void addCheckBox(int column, JTable table) {
        TableColumn tc = table.getColumnModel().getColumn(column);
        tc.setCellEditor(table.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
    }

    private void mostrarModulo(String buscar) {
        tm = new tablaModulo();
        tm.mostrar(buscar);
    }

    void eliminar() {
        if (txtcodigo.getText().length() > 0) {
            String codigo = txtcodigo.getText();
            vo = new RolVO();
            dao = new RolDAO();
            vo.setCodigo(codigo);
            dao.Eliminar_RolVO(vo);
        } else {
            JOptionPane.showMessageDialog(null, "Nada que eliminar");
            txtcodigo.requestFocus();
        }

    }

    private void subir(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtNivel.requestFocus();
        }
    }

    private void actualizarFila() {
        ad = new AccesoDAO();
        String campo;
        int valor = 0;
        int columna = this.tbAcceso.getSelectedColumn();
        int filaSeleccionada = this.tbAcceso.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "No se selecciono ninguna fila");
        } else {
            DefaultTableModel modelo = (DefaultTableModel) this.tbAcceso.getModel();
            String codigo = (String) modelo.getValueAt(filaSeleccionada, 0);
            Object campo_acceso = modelo.getValueAt(filaSeleccionada, 4);
            Object campo_nuevo = modelo.getValueAt(filaSeleccionada, 5);
            Object campo_actualizar = modelo.getValueAt(filaSeleccionada, 6);
            Object campo_borrar = modelo.getValueAt(filaSeleccionada, 7);
            switch (columna) {
                case 4:
                    //Actualizar campo Acceso
                    campo = "acceso";
                    if (campo_acceso.equals(true)) {
                        valor = 1;
                    } else if (campo_acceso.equals("false")) {
                        valor = 0;
                    }
                    ad.Modificar_AccesoVO(Integer.parseInt(codigo), campo, valor);
                    mostrarAcceso("", txtcodigo.getText());
                    tbAcceso.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
                    break;
                //Actualizar campo Nuevo
                case 5:
                    campo = "nuevo";
                    if (campo_nuevo.equals(true)) {
                        valor = 1;
                    } else if (campo_acceso.equals("false")) {
                        valor = 0;
                    }
                    ad.Modificar_AccesoVO(Integer.parseInt(codigo), campo, valor);
                    mostrarAcceso("", txtcodigo.getText());
                    tbAcceso.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
                    break;
                //Actualizar campo Modificar
                case 6:
                    campo = "actualizar";
                    if (campo_actualizar.equals(true)) {
                        valor = 1;
                    } else if (campo_acceso.equals("false")) {
                        valor = 0;
                    }
                    ad.Modificar_AccesoVO(Integer.parseInt(codigo), campo, valor);
                    mostrarAcceso("", txtcodigo.getText());
                    tbAcceso.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
                    break;
                //Actualizar Campo Borrar
                case 7:
                    campo = "borrar";
                    if (campo_borrar.equals(true)) {
                        valor = 1;
                    } else if (campo_acceso.equals("false")) {
                        valor = 0;
                    }
                    ad.Modificar_AccesoVO(Integer.parseInt(codigo), campo, valor);
                    mostrarAcceso("", txtcodigo.getText());
                    tbAcceso.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
                    break;
            }
        }
    }

    
    private void actualizar_por_columna(JCheckBox jchrol, JRadioButton jrb, String campoTabla) {
        int valor;
        String modulo;
        String rol = txtcodigo.getText();
        ad = new AccesoDAO();
        if (jchrol.isSelected()) {
            if (jrb.isSelected()) {
                valor = 1;
            } else {
                valor = 0;
            }

            ad.Modificar_porRol(campoTabla, valor, rol);
            mostrarAcceso("", rol);
        } else {
            if (jrb.isSelected()) {
                valor = 1;
            } else {
                valor = 0;
            }
            int seleccion = tbModulo.getSelectedRow();
            if (seleccion == -1) {
                JOptionPane.showMessageDialog(null, "Por Favor Seleccione Fila de la Lista Módulos");
            } else {
                modulo = tbModulo.getValueAt(seleccion, 0).toString();
                ad.Modificar_porRol_modulo(campoTabla, valor, rol, modulo);
                mostrarAcceso("", rol);
            }
        }
    }

    private void eliminar_permiso() {
        av = new AccesoVO();
        ad = new AccesoDAO();

        String codRol = txtcodigo.getText();
        av.setCodRol(codRol);
        ad.Eliminar_AccesoVO(av);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoSeleccion = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        lblfecha = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        lblaction = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        lblejecucion = new javax.swing.JLabel();
        b_cancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtcodigo = new javax.swing.JTextField();
        txtNivel = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lbl_mensaje_accion = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbModulo = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbAcceso = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        b_guardar2 = new javax.swing.JButton();
        b_guardar = new javax.swing.JButton();
        rb_acceso = new javax.swing.JRadioButton();
        rb_agregar = new javax.swing.JRadioButton();
        rb_actualizar = new javax.swing.JRadioButton();
        rb_eliminar = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        checkRol = new javax.swing.JCheckBox();
        checkModulo = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
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

        b_cancelar.setBackground(new java.awt.Color(0, 153, 153));
        b_cancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_cancelar.setForeground(new java.awt.Color(255, 255, 255));
        b_cancelar.setText("Salir");
        b_cancelar.setActionCommand("");
        b_cancelar.setBorderPainted(false);
        b_cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        jPanel2.setBackground(new java.awt.Color(248, 249, 249));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Rol", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(204, 204, 204))); // NOI18N

        jLabel2.setText("Nombre :");

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

        txtNivel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNivelFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNivelFocusLost(evt);
            }
        });
        txtNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNivelActionPerformed(evt);
            }
        });
        txtNivel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNivelKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNivelKeyReleased(evt);
            }
        });

        jLabel4.setText("Código :");

        lbl_mensaje_accion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_mensaje_accion.setForeground(new java.awt.Color(255, 102, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_mensaje_accion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(lbl_mensaje_accion))
                .addGap(0, 19, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(248, 249, 249));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Modulos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(204, 204, 204))); // NOI18N

        tbModulo.setSelectionBackground(new java.awt.Color(255, 153, 0));
        tbModulo.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tbModulo.setShowHorizontalLines(false);
        tbModulo.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tbModulo);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(248, 249, 249));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Programas y Acceso", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(204, 204, 204))); // NOI18N

        tbAcceso.setSelectionBackground(new java.awt.Color(255, 153, 0));
        tbAcceso.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tbAcceso.setShowGrid(false);
        tbAcceso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAccesoMouseClicked(evt);
            }
        });
        tbAcceso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbAccesoKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbAcceso);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(248, 249, 249));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(204, 204, 204))); // NOI18N

        b_guardar2.setBackground(new java.awt.Color(0, 153, 153));
        b_guardar2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_guardar2.setForeground(new java.awt.Color(255, 255, 255));
        b_guardar2.setText("Filtrar Todos");
        b_guardar2.setBorderPainted(false);
        b_guardar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_guardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_guardar2ActionPerformed(evt);
            }
        });
        b_guardar2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                b_guardar2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                b_guardar2KeyReleased(evt);
            }
        });

        b_guardar.setBackground(new java.awt.Color(0, 153, 153));
        b_guardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_guardar.setForeground(new java.awt.Color(255, 255, 255));
        b_guardar.setText("Filtro por Módulo");
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

        rb_acceso.setBackground(new java.awt.Color(248, 249, 249));
        rb_acceso.setText("Acceso");
        rb_acceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_accesoActionPerformed(evt);
            }
        });

        rb_agregar.setBackground(new java.awt.Color(248, 249, 249));
        rb_agregar.setText("Agregar");
        rb_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_agregarActionPerformed(evt);
            }
        });

        rb_actualizar.setBackground(new java.awt.Color(248, 249, 249));
        rb_actualizar.setText("Modificarr");
        rb_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_actualizarActionPerformed(evt);
            }
        });

        rb_eliminar.setBackground(new java.awt.Color(248, 249, 249));
        rb_eliminar.setText("Eliminar");
        rb_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_eliminarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Asignación de Permisos");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Filtro de Programas");

        checkRol.setBackground(new java.awt.Color(248, 249, 249));
        grupoSeleccion.add(checkRol);
        checkRol.setSelected(true);
        checkRol.setText("Rol");

        checkModulo.setBackground(new java.awt.Color(248, 249, 249));
        grupoSeleccion.add(checkModulo);
        checkModulo.setText("Módulo");

        jLabel6.setText("Parámetro :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(b_guardar2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(b_guardar)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(checkRol)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(checkModulo)
                            .addGap(2, 2, 2)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rb_acceso)
                            .addComponent(rb_agregar)
                            .addComponent(rb_actualizar)
                            .addComponent(rb_eliminar)
                            .addComponent(jLabel3))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_guardar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(checkRol)
                    .addComponent(checkModulo))
                .addGap(9, 9, 9)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_acceso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rb_agregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rb_actualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rb_eliminar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(b_cancelar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(b_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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


    private void m_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_salirActionPerformed
        // TODO add your handling code here:
        cambiar_form();
    }//GEN-LAST:event_m_salirActionPerformed

    private void txtNivelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNivelKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNivelKeyReleased

    private void txtNivelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNivelKeyPressed
        // este evento ocurre si se presiona la tecla enter
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String accion = lblaction.getText();  //con este variable identificamos la accion si es insert o update.

            switch (accion) { //evaluamos el resultado en una estructura condicional.
                case "Add": //si la accion es add y si los campos no estan vacios; se ejecutaran guardar rol y acceso.
                    if (validar()) {
                        guardar();
                        txtcodigo.setEditable(false); //bloqueamos la edicion del codigo rol.
                        ad = new AccesoDAO();
                        ad.insertarAcceso(txtcodigo.getText());//alamcenamos los permisos(recibe como parametro codigo rol).
                        mostrarAcceso("", txtcodigo.getText());
                    }
                    break;
                case "Update":
                    if (txtNivel.getText().length() > 2) {
                        actualizar();
                        mostrarAcceso("", txtcodigo.getText());
                    }
                    break;
            }
        }
    }//GEN-LAST:event_txtNivelKeyPressed

    private void txtNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNivelActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtNivelActionPerformed

    private void b_cancelarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_cancelarKeyReleased
        // TODO add your handling code here:
        subir(evt);
    }//GEN-LAST:event_b_cancelarKeyReleased

    private void b_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cancelarActionPerformed
        // TODO add your handling code here:
        cambiar_form();
    }//GEN-LAST:event_b_cancelarActionPerformed

    private void b_guardarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_guardarKeyReleased
        // TODO add your handling code here:
        subir(evt);
    }//GEN-LAST:event_b_guardarKeyReleased

    private void b_guardarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_guardarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_guardarKeyPressed

    private void b_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_guardarActionPerformed
        // TODO add your handling code here:
        int fila = tbModulo.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una fila de la lista Modulos");
        } else {
            String codModulo = tbModulo.getValueAt(fila, 0).toString();
            mostrarAcceso(codModulo, txtcodigo.getText());
        }
    }//GEN-LAST:event_b_guardarActionPerformed

    private void txtcodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoKeyReleased
        // TODO add your handling code here:
        if (txtcodigo.getText().length() >= 1) {
            txtcodigo.setBackground(Color.white);
        } else {
            txtcodigo.setBackground(Color.yellow);
        }
    }//GEN-LAST:event_txtcodigoKeyReleased

    private void txtcodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodigoActionPerformed
        // TODO add your handling code here:
        txtNivel.requestFocus();
    }//GEN-LAST:event_txtcodigoActionPerformed

    private void txtcodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcodigoFocusLost
        // TODO add your handling code here:
        listarCodigo(txtcodigo.getText());
    }//GEN-LAST:event_txtcodigoFocusLost

    private void b_guardar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_guardar2ActionPerformed
        // TODO add your handling code here:
        mostrarAcceso("", txtcodigo.getText());
    }//GEN-LAST:event_b_guardar2ActionPerformed

    private void b_guardar2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_guardar2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_guardar2KeyPressed

    private void b_guardar2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b_guardar2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_b_guardar2KeyReleased

    private void rb_accesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_accesoActionPerformed
        actualizar_por_columna(checkRol, rb_acceso, "acceso");

    }//GEN-LAST:event_rb_accesoActionPerformed

    private void tbAccesoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAccesoMouseClicked
        // TODO add your handling code here:
        actualizarFila();
    }//GEN-LAST:event_tbAccesoMouseClicked

    private void tbAccesoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAccesoKeyPressed

    }//GEN-LAST:event_tbAccesoKeyPressed

    private void rb_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_agregarActionPerformed
        // TODO add your handling code here:
        actualizar_por_columna(checkRol, rb_agregar, "nuevo");
    }//GEN-LAST:event_rb_agregarActionPerformed

    private void rb_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_actualizarActionPerformed
        // TODO add your handling code here:
        actualizar_por_columna(checkRol, rb_actualizar, "actualizar");
    }//GEN-LAST:event_rb_actualizarActionPerformed

    private void rb_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_eliminarActionPerformed
        // TODO add your handling code here:
        actualizar_por_columna(checkRol, rb_eliminar, "borrar");
    }//GEN-LAST:event_rb_eliminarActionPerformed

    private void txtNivelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNivelFocusGained
        // TODO add your handling code here:
        String accion = lblaction.getText();
        switch (accion) {
            case "Add":
                lbl_mensaje_accion.setText("Pulse Tecla Enter para Guardar");
                break;
            case "Update":
                lbl_mensaje_accion.setText("Pulse Tecla Enter para Actualizar");
                break;
        }
    }//GEN-LAST:event_txtNivelFocusGained

    private void txtNivelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNivelFocusLost
        // TODO add your handling code here:
        lbl_mensaje_accion.setText("");
    }//GEN-LAST:event_txtNivelFocusLost

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
            java.util.logging.Logger.getLogger(rolForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(rolForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(rolForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(rolForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new rolForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_cancelar;
    private javax.swing.JButton b_guardar;
    private javax.swing.JButton b_guardar2;
    private javax.swing.JCheckBox checkModulo;
    private javax.swing.JCheckBox checkRol;
    private javax.swing.ButtonGroup grupoSeleccion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbl_mensaje_accion;
    public static javax.swing.JLabel lblaction;
    public static javax.swing.JLabel lblejecucion;
    public javax.swing.JLabel lblfecha;
    private javax.swing.JMenuItem m_guardar;
    private javax.swing.JMenuItem m_salir;
    private javax.swing.JRadioButton rb_acceso;
    private javax.swing.JRadioButton rb_actualizar;
    private javax.swing.JRadioButton rb_agregar;
    private javax.swing.JRadioButton rb_eliminar;
    public static javax.swing.JTable tbAcceso;
    public static javax.swing.JTable tbModulo;
    private javax.swing.JTextField txtNivel;
    public static javax.swing.JTextField txtcodigo;
    // End of variables declaration//GEN-END:variables
}
