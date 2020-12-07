/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import DAO.BarrioDAO;
import DAO.Cargo_salarioDAO;
import DAO.CiudadDAO;
import DAO.DepartamentoDAO;
import DAO.PaisDAO;
import DAO.ClienteDAO;
import DAO.ProfesionDAO;
import DAO.SucursalDAO;
import VO.BarrioVO;
import VO.CiudadVO;
import VO.DepartamentoVO;
import VO.PaisVO;
import VO.ClienteVO;
import VO.ProfesionVO;
import VO.SucursalVO;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import static sigco.Principal.panelPrincipal;

/**
 *
 * @author Helena
 */
public class clienteForm extends javax.swing.JInternalFrame {

    ClienteVO vo = new ClienteVO();
    ClienteDAO dao = new ClienteDAO();
    mostrarFecha fecha = new mostrarFecha();
    String bandera = "cliente";
    obtenerCodigo cod = new obtenerCodigo();

    /**
     * Creates new form departamentoVista
     */
    public clienteForm() {
        initComponents();
        lblfecha.setText(fecha.mostrarFecha());
        obtenerCodigo();

    }

    //metodo para capturar y enviar datos
    void guardar() {
        Calendar cal = jdcfechanac.getCalendar();
        String codigo = dao.obtener_id();
        String ruc = txtrucci.getText();
        String nombre = txtnombre.getText();
        String apellido = txtapellido.getText();
        int d = cal.get(Calendar.DAY_OF_MONTH);
        int m = cal.get(Calendar.MONTH);
        int a = cal.get(Calendar.YEAR) - 1900;
        String fechaNac = (new Date(a, m, d)).toString();
        String sexo = sexo();
        String estadoCivil = estadoCivil();
        String pais = txtpais.getText();
        String dpto = txtdpto.getText();
        String ciudad = txtciudad.getText();
        String barrio = txtbarrio.getText();
        String sucursal = txtsucursal.getText();
        String profesion = txtprofesion.getText();
        String nrocasa = txtcasa.getText();
        String telefono = txttelefono.getText();
        String email = txtemail.getText();
        String referencia = txtreferencia.getText();
        String vendedor = txtvendedor.getText();
        String cobrador = txtcobrador.getText();
        Object limiteCredito = txtlimiteCredito.getText().replace(".", "");;

        vo.setCodigo(codigo);
        vo.setRuc_ci(ruc);
        vo.setNombre(nombre);
        vo.setApellido(apellido);
        vo.setFechaNac(fechaNac);
        vo.setSexo(sexo);
        vo.setEstadoCivil(estadoCivil);
        vo.setCodPais(pais);
        vo.setCodDepartamento(dpto);
        vo.setCodCiudad(ciudad);
        vo.setCodBarrio(barrio);
        vo.setCodSucursal(sucursal);
        vo.setCodProfesion(profesion);
        vo.setCodCobrador(cobrador);
        vo.setCodVendedor(vendedor);
        vo.setLimiteCredito(limiteCredito);
        vo.setNroCasa(nrocasa);
        vo.setTelefono(telefono);
        vo.setEmail(email);
        vo.setObs(referencia);

        dao.Agregar_ClienteVO(vo);
    }

    void actualizar() {
        Calendar cal = jdcfechanac.getCalendar();;
        String codigo = txtcodigo.getText();
        String ruc = txtrucci.getText();
        String nombre = txtnombre.getText();
        String apellido = txtapellido.getText();
        int d = cal.get(Calendar.DAY_OF_MONTH);
        int m = cal.get(Calendar.MONTH);
        int a = cal.get(Calendar.YEAR) - 1900;
        String fechaNac = (new Date(a, m, d)).toString();
        String sexo = sexo();
        String estadoCivil = estadoCivil();
        String pais = txtpais.getText();
        String dpto = txtdpto.getText();
        String ciudad = txtciudad.getText();
        String barrio = txtbarrio.getText();
        String sucursal = txtsucursal.getText();
        String profesion = txtprofesion.getText();
        String nrocasa = txtcasa.getText();
        String telefono = txttelefono.getText();
        String email = txtemail.getText();
        String referencia = txtreferencia.getText();
        String vendedor = txtvendedor.getText();
        String cobrador = txtcobrador.getText();
        Object limiteCredito = txtlimiteCredito.getText().replace(".", "");
        vo.setCodigo(codigo);
        vo.setRuc_ci(ruc);
        vo.setNombre(nombre);
        vo.setApellido(apellido);
        vo.setFechaNac(fechaNac);
        vo.setSexo(sexo);
        vo.setEstadoCivil(estadoCivil);
        vo.setCodPais(pais);
        vo.setCodDepartamento(dpto);
        vo.setCodCiudad(ciudad);
        vo.setCodBarrio(barrio);
        vo.setCodSucursal(sucursal);
        vo.setCodProfesion(profesion);
        vo.setCodCobrador(cobrador);
        vo.setCodVendedor(vendedor);
        vo.setLimiteCredito(limiteCredito);
        vo.setNroCasa(nrocasa);
        vo.setTelefono(telefono);
        vo.setEmail(email);
        vo.setObs(referencia);

        dao.Modificar_ClienteVO(vo);
    }

    //obtener sexo
    private String sexo() {
        String sex = null;
        if (jrbmasculino.isSelected()) {
            sex = "M";
        } else if (jrbfemenino.isSelected()) {
            sex = "F";
        }
        return sex;
    }

    //obtener estado civil
    private String estadoCivil() {
        String estado = null;
        if (jrdsoltero.isSelected()) {
            estado = "S";
        } else if (jrdcasado.isSelected()) {
            estado = "C";
        } else if (jrdseparado.isSelected()) {
            estado = "D";
        } else if (jrdviudo.isSelected()) {
            estado = "V";
        }
        return estado;
    }

    //metodo paara cerrar formulario actual y abrir principal
    void cambiar_form() {
        clienteVista v = new clienteVista();
        llamarJInternalFrame.llamarFormulario(v, panelPrincipal, "Datos Cliente Cliente", false);
        this.dispose();
    }

    //metodo para validar campos
    private Boolean validar() {
        if (txtcodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llene los campos requeridos");
            txtcodigo.requestFocus();
            txtcodigo.setBackground(Color.YELLOW);
            return false;
        } else if (txtrucci.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llene los campos requeridos");
            txtrucci.requestFocus();
            txtrucci.setBackground(Color.YELLOW);
            return false;

        } else {
            return true;
        }
    }

    void vaciar() {
        txtcodigo.setText("");
        txtrucci.setText("");
    }

    final void obtenerCodigo() {
        txtcodigo.setText(dao.obtener_id());
    }

    void listarCodigo(String codigo) {
        String simbolo = "###,###.##";
        DecimalFormat df = new DecimalFormat(simbolo);
        ArrayList<ClienteVO> list = dao.Listar_ClienteVO(codigo);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[19];
                vo = list.get(i);
                //almacenamos valores en el vector fila
                fila[0] = vo.getRuc_ci();
                fila[1] = vo.getNombre();
                fila[2] = vo.getApellido();
                fila[3] = vo.getFechaNac();
                fila[4] = vo.getSexo();
                fila[5] = vo.getEstadoCivil();
                fila[6] = vo.getTelefono();
                fila[7] = vo.getCodSucursal();
                fila[8] = vo.getCodProfesion();
                fila[9] = vo.getEmail();
                fila[10] = vo.getCodPais();
                fila[11] = vo.getCodDepartamento();
                fila[12] = vo.getCodCiudad();
                fila[13] = vo.getCodBarrio();
                fila[14] = vo.getNroCasa();
                fila[15] = vo.getCodCobrador();
                fila[16] = vo.getCodVendedor();
                fila[17] = vo.getLimiteCredito();
                fila[18] = vo.getObs();
                int año, mes, dia;

                txtrucci.setText((String) fila[0]);
                txtnombre.setText((String) fila[1]);
                txtapellido.setText((String) fila[2]);
                String Fechanac = ((String) fila[3]);
                String[] partes2 = Fechanac.split("-");
                año = Integer.parseInt(partes2[0]);
                mes = Integer.parseInt(partes2[1]);
                dia = Integer.parseInt(partes2[2]);
                jdcfechanac.setDate(new Date(año - 1900, mes - 1, dia));
                String sexo = (String) fila[4];
                if (sexo.equals("F")) {
                    jrbfemenino.setSelected(true);
                } else if (sexo.equals("M")) {
                    jrbmasculino.setSelected(true);
                }
                String ec = (String) fila[5];
                switch (ec) {
                    case "S":
                        jrdsoltero.setSelected(true);
                        break;
                    case "C":
                        jrdcasado.setSelected(true);
                        break;
                    case "D":
                        jrdseparado.setSelected(true);
                        break;
                    case "V":
                        jrdviudo.setSelected(true);
                        break;
                    default:
                        break;
                }
                txttelefono.setText((String) fila[6]);
                txtsucursal.setText((String) fila[7]);
                txtprofesion.setText((String) fila[8]);
                txtemail.setText((String) fila[9]);
                txtpais.setText((String) fila[10]);
                txtdpto.setText((String) fila[11]);
                txtciudad.setText((String) fila[12]);
                txtbarrio.setText((String) fila[13]);
                txtcasa.setText((String) fila[14]);
                txtcobrador.setText((String) fila[15]);
                txtvendedor.setText((String) fila[16]);
                txtlimiteCredito.setText(df.format(fila[17]));
                txtreferencia.setText((String) fila[18]);

                lblaction.setText("Update");
                listarpais(txtpais.getText());
                listardpto(txtdpto.getText());
                listarciudad(txtciudad.getText());
                listarbarrio(txtbarrio.getText());
                listarsucursal(txtsucursal.getText());
                listarprofesion(txtprofesion.getText());
                listar_cobrador(txtcobrador.getName());
                listar_vendedor(txtvendedor.getText());

            }
        } else {
            if (txtcodigo.getText().length() > 0) {
                lblaction.setText("Add");
                txtrucci.setText("");
            }

        }

    }

    void eliminar() {
        if (txtcodigo.getText().length() > 0) {
            String codigo = txtcodigo.getText();
            vo.setCodigo(codigo);
            dao.Eliminar_ClienteVO(vo);
            cambiar_form();
        } else {
            JOptionPane.showMessageDialog(null, "Nada que eliminar");
            txtcodigo.requestFocus();
        }

    }

    private void subir(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtreferencia.requestFocus();

        }
    }

    //Listar Departamento
    void listardpto(String codigo) {
        DepartamentoVO dvo;
        DepartamentoDAO pdao = new DepartamentoDAO();
        ArrayList<DepartamentoVO> list = pdao.Listar_DepartamentoVO(codigo);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[1];
                dvo = list.get(i);
                //almacenamos valores en el vector fila
                fila[0] = dvo.getDepartamento();
                txtnombredpto.setText((String) fila[0]);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un codigo válido");
            txtnombredpto.setText("");
            txtdpto.setText("");
        }
    }

    //Listar Ciudad
    void listarciudad(String codigo) {
        CiudadVO cvo;
        CiudadDAO cdao = new CiudadDAO();
        ArrayList<CiudadVO> list = cdao.Listar_CiudadVO(codigo);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[1];
                cvo = list.get(i);
                //almacenamos valores en el vector fila
                fila[0] = cvo.getCiudad();
                txtnombreciudad.setText((String) fila[0]);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un codigo válido");
            txtnombreciudad.setText("");
            txtciudad.setText("");
        }
    }

    //Listar Barrio
    void listarbarrio(String codigo) {
        BarrioVO bvo;
        BarrioDAO bdao = new BarrioDAO();
        ArrayList<BarrioVO> list = bdao.Listar_BarrioVO(codigo);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[1];
                bvo = list.get(i);
                //almacenamos valores en el vector fila
                fila[0] = bvo.getBarrio();
                txtnombrebarrio.setText((String) fila[0]);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un codigo válido");
            txtnombrebarrio.setText("");
            txtbarrio.setText("");
            txtbarrio.requestFocus();
        }
    }

    //Listar Empresa
    void listarsucursal(String codigo) {
        SucursalVO svo;
        SucursalDAO sdao = new SucursalDAO();
        ArrayList<SucursalVO> list = sdao.Listar_SucursalVO(codigo);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[1];
                svo = list.get(i);
                //almacenamos valores en el vector fila
                fila[0] = svo.getSucursal();
                txtnombresucursal.setText((String) fila[0]);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un codigo válido");
            txtnombresucursal.setText("");
            txtsucursal.setText("");
        }
    }

    //Listar Profesion
    void listarprofesion(String codigo) {
        ProfesionVO provo;
        ProfesionDAO prodao = new ProfesionDAO();
        ArrayList<ProfesionVO> list = prodao.Listar_ProfesionVO(codigo);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[1];
                provo = list.get(i);
                //almacenamos valores en el vector fila
                fila[0] = provo.getProfesion();
                txtnombreprofesion.setText((String) fila[0]);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un codigo válido");
            txtnombreprofesion.setText("");
            txtprofesion.setText("");
        }
    }

    //Listar Pais
    void listarpais(String codigo) {
        PaisVO pvo;
        PaisDAO pdao = new PaisDAO();
        ArrayList<PaisVO> list = pdao.Listar_PaisVO(codigo);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[1];
                pvo = list.get(i);
                //almacenamos valores en el vector fila
                fila[0] = pvo.getPais();
                txtnombrepais.setText((String) fila[0]);

            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un codigo válido");
            txtnombrepais.setText("");
            txtpais.setText("");
        }

    }

    void listar_cobrador(String buscar) {
        Cargo_salarioDAO data = new Cargo_salarioDAO();
        String nombre = data.mostrar_nomEmp(txtcobrador.getText());
        txtnomcobrador.setText(nombre);
    }

    void listar_vendedor(String buscar) {
        Cargo_salarioDAO data = new Cargo_salarioDAO();
        String nombre = data.mostrar_nomEmp(txtvendedor.getText());
        txtnomvendedor.setText(nombre);
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
        txtrucci = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtapellido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jdcfechanac = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jrbmasculino = new javax.swing.JRadioButton();
        jrbfemenino = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jrdsoltero = new javax.swing.JRadioButton();
        jrdcasado = new javax.swing.JRadioButton();
        jrdseparado = new javax.swing.JRadioButton();
        jrdviudo = new javax.swing.JRadioButton();
        txtpais = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtnombrepais = new javax.swing.JTextField();
        txtnombredpto = new javax.swing.JTextField();
        txtdpto = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtnombreciudad = new javax.swing.JTextField();
        txtciudad = new javax.swing.JTextField();
        txtnombrebarrio = new javax.swing.JTextField();
        txtbarrio = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtsucursal = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtnombresucursal = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtprofesion = new javax.swing.JTextField();
        txtnombreprofesion = new javax.swing.JTextField();
        txtcasa = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtreferencia = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtvendedor = new javax.swing.JTextField();
        txtnomvendedor = new javax.swing.JTextField();
        txtcobrador = new javax.swing.JTextField();
        txtnomcobrador = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtlimiteCredito = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        m_guardar = new javax.swing.JMenuItem();
        m_salir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jToolBar1.setBackground(new java.awt.Color(255, 102, 0));
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

        jLabel2.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel2.setText("RUC - CI :");

        txtcodigo.setBackground(new java.awt.Color(230, 230, 230));
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

        txtrucci.setBackground(new java.awt.Color(230, 230, 230));
        txtrucci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrucciActionPerformed(evt);
            }
        });
        txtrucci.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrucciKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtrucciKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel4.setText("Código :");

        txtnombre.setBackground(new java.awt.Color(230, 230, 230));
        txtnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreActionPerformed(evt);
            }
        });
        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnombreKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel3.setText("Nombres :");

        txtapellido.setBackground(new java.awt.Color(230, 230, 230));
        txtapellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtapellidoActionPerformed(evt);
            }
        });
        txtapellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtapellidoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtapellidoKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel6.setText("Apellidos :");

        jdcfechanac.setBackground(new java.awt.Color(230, 230, 230));
        jdcfechanac.setDateFormatString("dd-MM-y");
        jdcfechanac.setOpaque(false);
        jdcfechanac.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jdcfechanacKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel7.setText("Fecha de Nac. :");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel8.setText("Sexo :");

        jrbmasculino.setBackground(new java.awt.Color(230, 230, 230));
        groupSexo.add(jrbmasculino);
        jrbmasculino.setText("Masculino");

        jrbfemenino.setBackground(new java.awt.Color(230, 230, 230));
        groupSexo.add(jrbfemenino);
        jrbfemenino.setText("Femenino");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel9.setText("Estado Civil :");

        jrdsoltero.setBackground(new java.awt.Color(230, 230, 230));
        groupEstadoCivil.add(jrdsoltero);
        jrdsoltero.setText("Soltero");

        jrdcasado.setBackground(new java.awt.Color(230, 230, 230));
        groupEstadoCivil.add(jrdcasado);
        jrdcasado.setText("Casado");

        jrdseparado.setBackground(new java.awt.Color(230, 230, 230));
        groupEstadoCivil.add(jrdseparado);
        jrdseparado.setText("Separado");

        jrdviudo.setBackground(new java.awt.Color(230, 230, 230));
        groupEstadoCivil.add(jrdviudo);
        jrdviudo.setText("Viudo");

        txtpais.setBackground(new java.awt.Color(230, 230, 230));
        txtpais.setToolTipText("");
        txtpais.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtpaisFocusLost(evt);
            }
        });
        txtpais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpaisActionPerformed(evt);
            }
        });
        txtpais.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpaisKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel10.setText("Pais :");

        txtnombrepais.setBackground(new java.awt.Color(230, 230, 230));
        txtnombrepais.setToolTipText("");
        txtnombrepais.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnombrepais.setEnabled(false);

        txtnombredpto.setBackground(new java.awt.Color(230, 230, 230));
        txtnombredpto.setToolTipText("");
        txtnombredpto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnombredpto.setEnabled(false);

        txtdpto.setBackground(new java.awt.Color(230, 230, 230));
        txtdpto.setToolTipText("");
        txtdpto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtdptoFocusLost(evt);
            }
        });
        txtdpto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdptoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdptoKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel11.setText("Departamento :");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel12.setText("Ciudad :");

        txtnombreciudad.setBackground(new java.awt.Color(230, 230, 230));
        txtnombreciudad.setToolTipText("");
        txtnombreciudad.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnombreciudad.setEnabled(false);

        txtciudad.setBackground(new java.awt.Color(230, 230, 230));
        txtciudad.setToolTipText("");
        txtciudad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtciudadFocusLost(evt);
            }
        });
        txtciudad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtciudadKeyPressed(evt);
            }
        });

        txtnombrebarrio.setBackground(new java.awt.Color(230, 230, 230));
        txtnombrebarrio.setToolTipText("");
        txtnombrebarrio.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnombrebarrio.setEnabled(false);

        txtbarrio.setBackground(new java.awt.Color(230, 230, 230));
        txtbarrio.setToolTipText("");
        txtbarrio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtbarrioFocusLost(evt);
            }
        });
        txtbarrio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbarrioKeyPressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel13.setText("Barrio :");

        txtsucursal.setBackground(new java.awt.Color(230, 230, 230));
        txtsucursal.setToolTipText("");
        txtsucursal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtsucursalFocusLost(evt);
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
        });

        jLabel14.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel14.setText("Sucursal :");

        txtnombresucursal.setBackground(new java.awt.Color(230, 230, 230));
        txtnombresucursal.setToolTipText("");
        txtnombresucursal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnombresucursal.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel15.setText("Profesión :");

        txtprofesion.setBackground(new java.awt.Color(230, 230, 230));
        txtprofesion.setToolTipText("");
        txtprofesion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtprofesionFocusLost(evt);
            }
        });
        txtprofesion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtprofesionKeyPressed(evt);
            }
        });

        txtnombreprofesion.setBackground(new java.awt.Color(230, 230, 230));
        txtnombreprofesion.setToolTipText("");
        txtnombreprofesion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnombreprofesion.setEnabled(false);

        txtcasa.setBackground(new java.awt.Color(230, 230, 230));
        txtcasa.setToolTipText("");
        txtcasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcasaActionPerformed(evt);
            }
        });
        txtcasa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcasaKeyPressed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel16.setText("Casa Número :");

        txttelefono.setBackground(new java.awt.Color(230, 230, 230));
        txttelefono.setToolTipText("");
        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttelefonoKeyPressed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel17.setText("Número de Teléfono :");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel18.setText("Correo Electrónico :");

        txtemail.setBackground(new java.awt.Color(230, 230, 230));
        txtemail.setToolTipText("");
        txtemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtemailKeyPressed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel19.setText("Observación :");

        txtreferencia.setBackground(new java.awt.Color(230, 230, 230));
        txtreferencia.setToolTipText("");
        txtreferencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtreferenciaKeyPressed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel20.setText("Vendedor :");

        txtvendedor.setBackground(new java.awt.Color(230, 230, 230));
        txtvendedor.setToolTipText("");
        txtvendedor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtvendedorFocusLost(evt);
            }
        });
        txtvendedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtvendedorKeyPressed(evt);
            }
        });

        txtnomvendedor.setBackground(new java.awt.Color(230, 230, 230));
        txtnomvendedor.setToolTipText("");
        txtnomvendedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnomvendedor.setEnabled(false);

        txtcobrador.setBackground(new java.awt.Color(230, 230, 230));
        txtcobrador.setToolTipText("");
        txtcobrador.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcobradorFocusLost(evt);
            }
        });
        txtcobrador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcobradorKeyPressed(evt);
            }
        });

        txtnomcobrador.setBackground(new java.awt.Color(230, 230, 230));
        txtnomcobrador.setToolTipText("");
        txtnomcobrador.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnomcobrador.setEnabled(false);
        txtnomcobrador.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtnomcobradorFocusLost(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel21.setText("Cobrador :");

        txtlimiteCredito.setBackground(new java.awt.Color(230, 230, 230));
        txtlimiteCredito.setToolTipText("");
        txtlimiteCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtlimiteCreditoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtlimiteCreditoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtlimiteCreditoKeyTyped(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLabel22.setText("Limite Crédito :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jLabel9))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)))
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jrdsoltero)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jrdcasado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jrdseparado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jrdviudo))
                    .addComponent(txtapellido, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcfechanac, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jrbmasculino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jrbfemenino))
                    .addComponent(txtrucci, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(txtprofesion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtnombreprofesion))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(txtsucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtnombresucursal))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(txtbarrio, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtnombrebarrio))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(txtciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtnombreciudad))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(txtdpto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtnombredpto))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(txtpais, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtnombrepais, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(txtvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtnomvendedor))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(txtcobrador, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtnomcobrador)))
                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcasa, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtreferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtlimiteCredito, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txttelefono, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtrucci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtapellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcfechanac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbmasculino)
                    .addComponent(jrbfemenino)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrdsoltero)
                    .addComponent(jrdcasado)
                    .addComponent(jrdseparado)
                    .addComponent(jrdviudo)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnombrepais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdpto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnombredpto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnombreciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbarrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnombrebarrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnombresucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtprofesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnombreprofesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnomvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcobrador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnomcobrador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtlimiteCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtreferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(24, 24, 24)
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
                    int opcion = JOptionPane.showConfirmDialog(null, "Deseas Registrar Referencia para el Cliente ?", "Mensaje", JOptionPane.YES_NO_OPTION);
                    if (opcion == 0) {
                        referenciaCliente rf = new referenciaCliente();
                        rf.setTitle("Referencia Cliente");
                        rf.setResizable(false);
                        rf.setLocationRelativeTo(null);
                        rf.setVisible(true);
                        rf.txtCliente.setText(txtcodigo.getText());
                        String nombre = txtnombre.getText(); String apellido =  " " + txtapellido.getText();
                        rf.txtNomCliente.setText(nombre + apellido);
                        rf.mostrar(txtcodigo.getText());
                        this.dispose();
                    }else{
                        cambiar_form();
                    }
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

    private void txtrucciKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrucciKeyReleased
        // TODO add your handling code here:
        if (txtrucci.getText().length() >= 4) {
            txtrucci.setBackground(Color.white);
        } else {
            txtrucci.setBackground(Color.yellow);
        }
    }//GEN-LAST:event_txtrucciKeyReleased

    private void txtrucciKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrucciKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
//            jdcfechaingreso.transferFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtnombre.requestFocus();
        }
    }//GEN-LAST:event_txtrucciKeyPressed

    private void txtrucciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrucciActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtrucciActionPerformed

    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreActionPerformed

    private void txtnombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtrucci.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtapellido.requestFocus();
        }
    }//GEN-LAST:event_txtnombreKeyPressed

    private void txtnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreKeyReleased

    private void txtapellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtapellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtapellidoActionPerformed

    private void txtapellidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellidoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtnombre.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jdcfechanac.transferFocus();
        }
    }//GEN-LAST:event_txtapellidoKeyPressed

    private void txtapellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellidoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtapellidoKeyReleased

    private void txtsucursalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsucursalKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtbarrio.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtsucursal.getText().length() == 0) {
                sucursalVista dp = new sucursalVista();
                dp.setResizable(false);
                dp.setLocationRelativeTo(null);
                dp.lblejecucion.setText(bandera);
                dp.setVisible(true);
            } else {
                listarsucursal(txtsucursal.getText());
            }
        }
    }//GEN-LAST:event_txtsucursalKeyPressed

    private void txtsucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsucursalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsucursalActionPerformed

    private void txtpaisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpaisKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtapellido.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtpais.getText().length() == 0) {
                paisVista dp = new paisVista();
                dp.setResizable(false);
                dp.setLocationRelativeTo(null);
                dp.lblejecucion.setText(bandera);
                dp.setVisible(true);
            } else {
                listarpais(txtpais.getText());
            }
        }
    }//GEN-LAST:event_txtpaisKeyPressed

    private void txtpaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpaisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpaisActionPerformed

    private void txtdptoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdptoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtpais.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtdpto.getText().length() == 0) {
                departamentoVista dp = new departamentoVista();
                dp.setResizable(false);
                dp.setLocationRelativeTo(null);
                dp.lblejecucion.setText(bandera);
                dp.setVisible(true);
            } else {
                listardpto(txtdpto.getText());
            }
        }
    }//GEN-LAST:event_txtdptoKeyPressed

    private void txtciudadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtciudadKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtdpto.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtciudad.getText().length() == 0) {
                ciudadVista dp = new ciudadVista();
                dp.setResizable(false);
                dp.lblejecucion.setText(bandera);
                dp.setVisible(true);
            } else {
                listarciudad(txtciudad.getText());
            }
        }
    }//GEN-LAST:event_txtciudadKeyPressed

    private void txtbarrioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbarrioKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtciudad.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtbarrio.getText().length() == 0) {
                barrioVista dp = new barrioVista();
                dp.setResizable(false);
                dp.setLocationRelativeTo(null);
                dp.lblejecucion.setText(bandera);
                dp.setVisible(true);
            } else {
                listarbarrio(txtbarrio.getText());
            }
        }
    }//GEN-LAST:event_txtbarrioKeyPressed

    private void txtprofesionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprofesionKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtsucursal.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtprofesion.getText().length() == 0) {
                profesionVista dp = new profesionVista();
                dp.setResizable(false);
                dp.setLocationRelativeTo(null);
                dp.lblejecucion.setText(bandera);
                dp.setVisible(true);
            } else {
                listarprofesion(txtprofesion.getText());
            }
        }
    }//GEN-LAST:event_txtprofesionKeyPressed

    private void txtpaisFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpaisFocusLost
        // TODO add your handling code here:
        if (txtpais.getText().length() > 0)
            listarpais(txtpais.getText());
    }//GEN-LAST:event_txtpaisFocusLost

    private void txtdptoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdptoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdptoKeyReleased

    private void txtcasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcasaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcasaActionPerformed

    private void txtcasaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcasaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtprofesion.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txttelefono.requestFocus();
        }
    }//GEN-LAST:event_txtcasaKeyPressed

    private void txttelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtcasa.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtemail.requestFocus();
        }
    }//GEN-LAST:event_txttelefonoKeyPressed

    private void txtemailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txttelefono.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtreferencia.requestFocus();
        }
    }//GEN-LAST:event_txtemailKeyPressed

    private void txtreferenciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtreferenciaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtemail.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            b_guardar.requestFocus();
        }
    }//GEN-LAST:event_txtreferenciaKeyPressed

    private void jdcfechanacKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jdcfechanacKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jdcfechanacKeyPressed

    private void txtvendedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtvendedorKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtvendedor.getText().length() == 0) {
                cargoPersonalVista dp = new cargoPersonalVista();
                dp.setResizable(false);
                dp.setLocationRelativeTo(null);
                dp.lblejecucion.setText("clienteV");
                dp.setVisible(true);
            } else {
                listar_vendedor(txtvendedor.getText());
            }
        }
    }//GEN-LAST:event_txtvendedorKeyPressed

    private void txtcobradorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcobradorKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtcobrador.getText().length() == 0) {
                cargoPersonalVista dp = new cargoPersonalVista();
                dp.setResizable(false);
                dp.setLocationRelativeTo(null);
                dp.lblejecucion.setText("clienteC");
                dp.setVisible(true);
            } else {
                listar_vendedor(txtvendedor.getText());
            }
        }
    }//GEN-LAST:event_txtcobradorKeyPressed

    private void txtlimiteCreditoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtlimiteCreditoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtlimiteCreditoKeyPressed

    private void txtlimiteCreditoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtlimiteCreditoKeyReleased
        // TODO add your handling code here:
        DecimalFormat df = new DecimalFormat("#,###");
        try {
            String texto = txtlimiteCredito.getText();
            if (texto.length() >= 1) {

                txtlimiteCredito.setText(df.format(Integer.valueOf(texto.replace(".", "").replace(",", ""))));

            }
        } catch (NumberFormatException e) {
            e.getMessage();
        }
    }//GEN-LAST:event_txtlimiteCreditoKeyReleased

    private void txtlimiteCreditoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtlimiteCreditoKeyTyped
        // TODO add your handling code here:
        char[] p = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '.'};
        int b = 0;
        for (int i = 0; i <= 10; i++) {
            if (p[i] == evt.getKeyChar()) {
                b = 1;
            }

        }
        if (b == 0) {
            evt.consume();
            getToolkit().beep();
        }

    }//GEN-LAST:event_txtlimiteCreditoKeyTyped

    private void txtnomcobradorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnomcobradorFocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_txtnomcobradorFocusLost

    private void txtcobradorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcobradorFocusLost
        // TODO add your handling code here:
        listar_cobrador(txtcobrador.getText());
    }//GEN-LAST:event_txtcobradorFocusLost

    private void txtvendedorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtvendedorFocusLost
        // TODO add your handling code here:
        listar_vendedor(txtvendedor.getText());
    }//GEN-LAST:event_txtvendedorFocusLost

    private void txtdptoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtdptoFocusLost
        // TODO add your handling code here:
            if (txtdpto.getText().length() > 0)
            listardpto(txtdpto.getText());
    }//GEN-LAST:event_txtdptoFocusLost

    private void txtciudadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtciudadFocusLost
        // TODO add your handling code here:
            if (txtciudad.getText().length() > 0)
            listarciudad(txtciudad.getText());
    }//GEN-LAST:event_txtciudadFocusLost

    private void txtbarrioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtbarrioFocusLost
        // TODO add your handling code here:
            if (txtbarrio.getText().length() > 0)
            listarbarrio(txtbarrio.getText());
    }//GEN-LAST:event_txtbarrioFocusLost

    private void txtsucursalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtsucursalFocusLost
        // TODO add your handling code here:
            if (txtsucursal.getText().length() > 0)
            listarsucursal(txtsucursal.getText());
    }//GEN-LAST:event_txtsucursalFocusLost

    private void txtprofesionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtprofesionFocusLost
        // TODO add your handling code here:
         if (txtprofesion.getText().length() > 0)
            listarprofesion(txtprofesion.getText());
    }//GEN-LAST:event_txtprofesionFocusLost

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
            java.util.logging.Logger.getLogger(clienteForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new clienteForm().setVisible(true);
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
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private com.toedter.calendar.JDateChooser jdcfechanac;
    private javax.swing.JRadioButton jrbfemenino;
    private javax.swing.JRadioButton jrbmasculino;
    private javax.swing.JRadioButton jrdcasado;
    private javax.swing.JRadioButton jrdseparado;
    private javax.swing.JRadioButton jrdsoltero;
    private javax.swing.JRadioButton jrdviudo;
    public static javax.swing.JLabel lblaction;
    public static javax.swing.JLabel lblejecucion;
    private javax.swing.JLabel lblfecha;
    private javax.swing.JMenuItem m_guardar;
    private javax.swing.JMenuItem m_salir;
    private javax.swing.JTextField txtapellido;
    public static javax.swing.JTextField txtbarrio;
    private javax.swing.JTextField txtcasa;
    public static javax.swing.JTextField txtciudad;
    public static javax.swing.JTextField txtcobrador;
    public static javax.swing.JTextField txtcodigo;
    public static javax.swing.JTextField txtdpto;
    private javax.swing.JTextField txtemail;
    public static javax.swing.JTextField txtlimiteCredito;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtnombrebarrio;
    private javax.swing.JTextField txtnombreciudad;
    private javax.swing.JTextField txtnombredpto;
    private javax.swing.JTextField txtnombrepais;
    private javax.swing.JTextField txtnombreprofesion;
    private javax.swing.JTextField txtnombresucursal;
    public static javax.swing.JTextField txtnomcobrador;
    public static javax.swing.JTextField txtnomvendedor;
    public static javax.swing.JTextField txtpais;
    public static javax.swing.JTextField txtprofesion;
    private javax.swing.JTextField txtreferencia;
    private javax.swing.JTextField txtrucci;
    public static javax.swing.JTextField txtsucursal;
    private javax.swing.JTextField txttelefono;
    public static javax.swing.JTextField txtvendedor;
    // End of variables declaration//GEN-END:variables

    //varibales para enviar datos   
}
