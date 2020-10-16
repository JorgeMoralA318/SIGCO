/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import DAO.CargoDAO;
import DAO.Cargo_salarioDAO;
import DAO.PersonalDAO;
import VO.CargoVO;
import VO.Cargo_salarioVO;
import VO.PersonalVO;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Helena
 */
public class cargoPersonalForm extends javax.swing.JFrame {

    Cargo_salarioVO vo = new Cargo_salarioVO();
    Cargo_salarioDAO dao = new Cargo_salarioDAO();
    mostrarFecha fecha = new mostrarFecha();

    /**
     * Creates new form departamentoVista
     */
    public cargoPersonalForm() {
        initComponents();
        lblfecha.setText(fecha.mostrarFecha());
        obtener_codigo();
    }

    //metodo para capturar y enviar datos
    void guardar() {
        Calendar cal;
        int d, m, a;
        String codigo = txtcodigo.getText();
        String fechaIngreso;
        cal = jdcfechaingreso.getCalendar();
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;
        fechaIngreso = (new Date(a, m, d)).toString();
        String codPersonal = txtpersonal.getText();
        String codCargo = txtcargo.getText();
        String salario = txtsalario.getText();
        String comVenta = txtcomVenta.getText();
        String comCobro = txtcomCobro.getText();

        vo.setCodigo(codigo);
        vo.setCodCargo(codCargo);
        vo.setCodPersonal(codPersonal);
        vo.setComCobro(Integer.parseInt(comCobro));
        vo.setComVenta(Integer.parseInt(comVenta));
        vo.setSalario(salario);
        vo.setFecha_alta(fechaIngreso);

        dao.Agregar_Cargo_salarioVO(vo);
    }

    void actualizar() {
        Calendar cal;
        int d, m, a;
        String codigo = txtcodigo.getText();
        String fechaIngreso;
        cal = jdcfechaingreso.getCalendar();
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;
        fechaIngreso = (new Date(a, m, d)).toString();
        String codPersonal = txtpersonal.getText();
        String codCargo = txtcargo.getText();
        String salario = txtsalario.getText();
        String comVenta = txtcomVenta.getText();
        String comCobro = txtcomCobro.getText();

        vo.setCodigo(codigo);
        vo.setCodCargo(codCargo);
        vo.setCodPersonal(codPersonal);
        vo.setComCobro(Integer.parseInt(comCobro));
        vo.setComVenta(Integer.parseInt(comVenta));
        vo.setSalario(salario);
        vo.setFecha_alta(fechaIngreso);

        dao.Modificar_Cargo_salarioVO(vo);
    }

    //metodo paara cerrar formulario actual y abrir principal
    void cambiar_form() {
        cargoPersonalVista v = new cargoPersonalVista();
        v.setResizable(false);
        v.setLocationRelativeTo(null);
        v.setTitle("Cargo Salario");
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
        } else if (txtpersonal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llene los campos requeridos");
            txtpersonal.requestFocus();
            txtpersonal.setBackground(Color.YELLOW);
            return false;

        } else if (txtcargo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llene los campos requeridos");
            txtcargo.requestFocus();
            txtcargo.setBackground(Color.YELLOW);
            return false;

        } else if (txtsalario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llene los campos requeridos");
            txtsalario.requestFocus();
            txtsalario.setBackground(Color.YELLOW);
            return false;

        } else {
            return true;
        }
    }

    void vaciar() throws ParseException {
        txtcodigo.setText("");
        txtpersonal.setText("");
        txtcargo.setText("");
        txtsalario.setText("");
        txtnomPersonal.setText("");
        txtnomCargo.setText("");
        String hoy = LocalDate.now().toString();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = (Date) formato.parse(hoy);
        jdcfechaingreso.setDate(fechaDate);
    }

    private void obtener_codigo() {
        txtcodigo.setText(cod());
        txtcodigo.requestFocus();
    }

    private String cod() {
        dao = new Cargo_salarioDAO();
        String resultado;
        int contador = dao.obtener_id().length();
        String cero = null;
        switch (contador) {
            case 1:
                cero = "0000";
                break;
            case 2:
                cero = "000";
                break;
            case 3:
                cero = "00";
                break;
            case 4:
                cero = "0";
                break;
            case 5:
                cero = "";
                break;
        }
        resultado = cero + dao.obtener_id();
        return resultado;
    }

    void listarCodigo(String codigo){
        ArrayList<Cargo_salarioVO> list = dao.Listar_Cargo_salarioVO(codigo);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[6];
                vo = list.get(i);
                //almacenamos valores en el vector fila
                fila[0] = vo.getFecha_alta();
                fila[1] = vo.getCodPersonal();
                fila[2] = vo.getCodCargo();
                fila[3] = vo.getSalario();
                fila[4] = vo.getComVenta();
                fila[5] = vo.getComCobro();
                int año, mes, dia;
                String fecha_alta = (String) fila[0];
                String[] partes = fecha_alta.split("-");
                año = Integer.parseInt(partes[0]);
                mes = Integer.parseInt(partes[1]);
                dia = Integer.parseInt(partes[2]);
                jdcfechaingreso.setDate(new Date(año - 1900, mes - 1, dia));
                txtpersonal.setText((String) fila[1]);
                txtcargo.setText((String) fila[2]);
                txtsalario.setText(fila[3].toString());
                txtcomVenta.setText(fila[4].toString());
                txtcomCobro.setText(fila[5].toString());

                lblaction.setText("Update");
                listar_personal(txtpersonal.getText());
                listar_cargo(txtcargo.getText());
              

            }
        } else {
            if (txtcodigo.getText().length() > 0) {
                lblaction.setText("Add");
            }

        }

    }

    void eliminar() {
        if (txtcodigo.getText().length() > 0) {
            String cod = txtcodigo.getText();
            vo.setCodigo(cod);
            dao.Eliminar_Cargo_salarioVO(vo);
            cambiar_form();
        } else {
            JOptionPane.showMessageDialog(null, "Nada que eliminar");
            txtcodigo.requestFocus();
        }

    }

    private void subir(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtcomCobro.requestFocus();

        }
    }

    //Listar Cargo
    void listar_cargo(String codigo) {
        CargoVO cvo;
        CargoDAO cdao = new CargoDAO();
        ArrayList<CargoVO> list = cdao.Listar_CargoVO(codigo);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[1];
                cvo = list.get(i);
                //almacenamos valores en el vector fila
                fila[0] = cvo.getCargo();
                txtnomCargo.setText((String) fila[0]);
                txtsalario.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un codigo válido");
            txtnomCargo.setText("");
            txtcargo.setText("");
            txtcargo.requestFocus();
        }
    }

    //Listar Pais
    void listar_personal(String codigo) {
        PersonalVO pvo;
        PersonalDAO pdao = new PersonalDAO();
        ArrayList<PersonalVO> list = pdao.Listar_PersonalVO(codigo);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[2];
                pvo = list.get(i);
                //almacenamos valores en el vector fila
                fila[0] = pvo.getNombre();
                fila[1] = pvo.getApellido();
                txtnomPersonal.setText((String) fila[0] + " " + fila[1]);
                txtcargo.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un codigo válido");
            txtnomPersonal.setText("");
            txtpersonal.setText("");
            txtpersonal.requestFocus();
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
        txtcodigo = new javax.swing.JTextField();
        b_guardar = new javax.swing.JButton();
        b_guardarynuevo = new javax.swing.JButton();
        b_cancelar = new javax.swing.JButton();
        b_eliminar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jdcfechaingreso = new com.toedter.calendar.JDateChooser();
        txtpersonal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtnomPersonal = new javax.swing.JTextField();
        txtnomCargo = new javax.swing.JTextField();
        txtcargo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtsalario = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtcomVenta = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtcomCobro = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        m_guardar = new javax.swing.JMenuItem();
        m_salir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(247, 249, 249));

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

        b_guardar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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

        b_guardarynuevo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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

        b_cancelar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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

        b_eliminar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Código :");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Fecha Alta:");

        jdcfechaingreso.setDateFormatString("dd-MM-y");
        jdcfechaingreso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jdcfechaingresoKeyPressed(evt);
            }
        });

        txtpersonal.setToolTipText("");
        txtpersonal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtpersonalFocusLost(evt);
            }
        });
        txtpersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpersonalActionPerformed(evt);
            }
        });
        txtpersonal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpersonalKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Personal :");

        txtnomPersonal.setEditable(false);
        txtnomPersonal.setBackground(new java.awt.Color(250, 250, 250));
        txtnomPersonal.setToolTipText("");

        txtnomCargo.setEditable(false);
        txtnomCargo.setBackground(new java.awt.Color(250, 250, 250));
        txtnomCargo.setToolTipText("");

        txtcargo.setToolTipText("");
        txtcargo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcargoFocusLost(evt);
            }
        });
        txtcargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcargoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcargoKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Cargo :");

        txtsalario.setToolTipText("");
        txtsalario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsalarioActionPerformed(evt);
            }
        });
        txtsalario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsalarioKeyPressed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("Salario Fijo :");

        txtcomVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcomVenta.setText("0");
        txtcomVenta.setToolTipText("");
        txtcomVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcomVentaKeyPressed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel17.setText("% Comisión Venta :");

        jLabel18.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel18.setText("% Comisión Cobro :");

        txtcomCobro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcomCobro.setText("0");
        txtcomCobro.setToolTipText("");
        txtcomCobro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcomCobroKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel16)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(txtcargo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtnomCargo))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(txtpersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtnomPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jdcfechaingreso, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsalario, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtcomCobro, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtcomVenta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(69, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(b_guardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_guardarynuevo)
                .addGap(6, 6, 6)
                .addComponent(b_eliminar)
                .addGap(6, 6, 6)
                .addComponent(b_cancelar)
                .addGap(100, 100, 100))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcfechaingreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnomPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnomCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcomVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcomCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b_guardarynuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
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
        if (validar()) {
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
            try {
                vaciar();
            } catch (ParseException ex) {
                Logger.getLogger(cargoPersonalForm.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        jdcfechaingreso.transferFocus();
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

    private void jdcfechaingresoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jdcfechaingresoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jdcfechaingresoKeyPressed

    private void txtpersonalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpersonalKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            jdcfechaingreso.requestFocusInWindow();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtpersonal.getText().length() == 0) {
                personalVista dp = new personalVista();
                dp.setResizable(false);
                dp.setLocationRelativeTo(null);
                dp.lblejecucion.setText("cargo_salario");
                dp.setVisible(true);
            } else {
                listar_personal(txtpersonal.getText());
            }
        }
    }//GEN-LAST:event_txtpersonalKeyPressed

    private void txtpersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpersonalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpersonalActionPerformed

    private void txtcargoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcargoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtpersonal.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtcargo.getText().length() == 0) {
                cargoVista dp = new cargoVista();
                dp.setResizable(false);
                dp.setLocationRelativeTo(null);
                dp.lblejecucion.setText("cargo_salario");
                dp.setVisible(true);
            } else {
                listar_cargo(txtcargo.getText());
            }
        }
    }//GEN-LAST:event_txtcargoKeyPressed

    private void txtpersonalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpersonalFocusLost
        // TODO add your handling code here:
        if (txtpersonal.getText().length() > 0)
            listar_personal(txtpersonal.getText());
    }//GEN-LAST:event_txtpersonalFocusLost

    private void txtcargoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcargoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcargoKeyReleased

    private void txtsalarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsalarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsalarioActionPerformed

    private void txtsalarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsalarioKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtcargo.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtcomVenta.requestFocus();
        }
    }//GEN-LAST:event_txtsalarioKeyPressed

    private void txtcomVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcomVentaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtsalario.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtcomCobro.requestFocus();
        }
    }//GEN-LAST:event_txtcomVentaKeyPressed

    private void txtcomCobroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcomCobroKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtcomVenta.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            b_guardar.requestFocus();
        }
    }//GEN-LAST:event_txtcomCobroKeyPressed

    private void txtcargoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcargoFocusLost
        // TODO add your handling code here:
        if (txtcargo.getText().length() > 0)
            listar_cargo(txtcargo.getText());
    }//GEN-LAST:event_txtcargoFocusLost

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
            java.util.logging.Logger.getLogger(cargoPersonalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new cargoPersonalForm().setVisible(true);
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private com.toedter.calendar.JDateChooser jdcfechaingreso;
    public static javax.swing.JLabel lblaction;
    public static javax.swing.JLabel lblejecucion;
    private javax.swing.JLabel lblfecha;
    private javax.swing.JMenuItem m_guardar;
    private javax.swing.JMenuItem m_salir;
    public static javax.swing.JTextField txtcargo;
    public static javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtcomCobro;
    private javax.swing.JTextField txtcomVenta;
    private javax.swing.JTextField txtnomCargo;
    private javax.swing.JTextField txtnomPersonal;
    public static javax.swing.JTextField txtpersonal;
    private javax.swing.JTextField txtsalario;
    // End of variables declaration//GEN-END:variables

    //varibales para enviar datos   
}
