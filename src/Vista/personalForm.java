/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import DAO.BarrioDAO;
import DAO.CiudadDAO;
import DAO.DepartamentoDAO;
import DAO.PaisDAO;
import DAO.PersonalDAO;
import DAO.ProfesionDAO;
import DAO.SucursalDAO;
import VO.BarrioVO;
import VO.CiudadVO;
import VO.DepartamentoVO;
import VO.PaisVO;
import VO.PersonalVO;
import VO.ProfesionVO;
import VO.SucursalVO;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Helena
 */
public class personalForm extends javax.swing.JFrame {

    PersonalVO vo = new PersonalVO();
    PersonalDAO dao = new PersonalDAO();

    /**
     * Creates new form departamentoVista
     */
    public personalForm() {
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
        Calendar cal;
        int d, m, a;
        String codigo = txtcodigo.getText();
        String fechaIngreso, fechaNac;
        cal = jdcfechaingreso.getCalendar();
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;
        fechaIngreso = (new Date(a, m, d)).toString();
        String ruc = txtrucci.getText();
        String nombre = txtnombre.getText();
        String apellido = txtapellido.getText();
        cal = jdcfechanac.getCalendar();
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;
        fechaNac = (new Date(a, m, d)).toString();
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

        vo.setCodigo(codigo);
        vo.setFechaIngreso(fechaIngreso);
        vo.setCiRuc(ruc);
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
        vo.setCasaNro(nrocasa);
        vo.setTelefono(telefono);
        vo.setEmail(email);
        vo.setTelefono(telefono);
        vo.setReferencia(referencia);

        dao.Agregar_PersonalVO(vo);
    }

    void actualizar() {
        Calendar cal;
        int d, m, a;
        String codigo = txtcodigo.getText();
        String fechaIngreso, fechaNac;
        cal = jdcfechaingreso.getCalendar();
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;
        fechaIngreso = (new Date(a, m, d)).toString();
        String ruc = txtrucci.getText();
        String nombre = txtnombre.getText();
        String apellido = txtapellido.getText();
        cal = jdcfechanac.getCalendar();
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH);
        a = cal.get(Calendar.YEAR) - 1900;
        fechaNac = (new Date(a, m, d)).toString();
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

        vo.setCodigo(codigo);
        vo.setFechaIngreso(fechaIngreso);
        vo.setCiRuc(ruc);
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
        vo.setCasaNro(nrocasa);
        vo.setTelefono(telefono);
        vo.setEmail(email);
        vo.setTelefono(telefono);
        vo.setReferencia(referencia);

        dao.Modificar_PersonalVO(vo);
    }

    //obtener sexo
    private String sexo() {
        String sexo = null;
        if (jrbmasculino.isSelected()) {
            sexo = "M";
        } else if (jrbfemenino.isSelected()) {
            sexo = "F";
        }
        return sexo;
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
        personalVista v = new personalVista();
        v.setResizable(false);
        v.setLocationRelativeTo(null);
        v.setTitle("Personal");
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

    private void obtener_codigo() {
        dao = new PersonalDAO();
        txtcodigo.setText("PE-" + dao.obtener_id());
        txtcodigo.requestFocus();
    }

    void listarCodigo(String codigo) {
        ArrayList<PersonalVO> list = dao.Listar_PersonalVO(codigo);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[17];
                vo = list.get(i);
                //almacenamos valores en el vector fila
                fila[0] = vo.getFechaIngreso();
                fila[1] = vo.getCiRuc();
                fila[2] = vo.getNombre();
                fila[3] = vo.getApellido();
                fila[4] = vo.getFechaNac();
                fila[5] = vo.getSexo();
                fila[6] = vo.getEstadoCivil();
                fila[7] = vo.getCodPais();
                fila[8] = vo.getCodDepartamento();
                fila[9] = vo.getCodCiudad();
                fila[10] = vo.getCodBarrio();
                fila[11] = vo.getCodSucursal();
                fila[12] = vo.getCodProfesion();
                fila[13] = vo.getCasaNro();
                fila[14] = vo.getTelefono();
                fila[15] = vo.getEmail();
                fila[16] = vo.getReferencia();
                int año, mes, dia;
                String fechaIngreso = (String) fila[0];
                String[] partes = fechaIngreso.split("-");
                año = Integer.parseInt(partes[0]);
                mes = Integer.parseInt(partes[1]);
                dia = Integer.parseInt(partes[2]);
                jdcfechaingreso.setDate(new Date(año - 1900, mes - 1, dia));
                txtrucci.setText((String) fila[1]);
                txtnombre.setText((String) fila[2]);
                txtapellido.setText((String) fila[3]);
                String Fechanac = ((String) fila[4]);
                String[] partes2 = Fechanac.split("-");
                año = Integer.parseInt(partes2[0]);
                mes = Integer.parseInt(partes2[1]);
                dia = Integer.parseInt(partes2[2]);
                jdcfechanac.setDate(new Date(año - 1900, mes - 1, dia));
                String sexo = (String) fila[5];
                if (sexo.equals("F")) {
                    jrbfemenino.setSelected(true);
                } else if (sexo.equals("M")) {
                    jrbmasculino.setSelected(true);
                }
                String ec = (String) fila[6];
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
                txtpais.setText((String) fila[7]);
                txtdpto.setText((String) fila[8]);
                txtciudad.setText((String) fila[9]);
                txtbarrio.setText((String) fila[10]);
                txtsucursal.setText((String) fila[11]);
                txtprofesion.setText((String) fila[12]);
                txtcasa.setText((String) fila[13]);
                txttelefono.setText((String) fila[14]);
                txtemail.setText((String) fila[15]);
                txtreferencia.setText((String) fila[16]);

                lblaction.setText("Update");
                listarpais(txtpais.getText());
                listardpto(txtdpto.getText());
                listarciudad(txtciudad.getText());
                listarbarrio(txtbarrio.getText());
                listarsucursal(txtsucursal.getText());
                listarprofesion(txtprofesion.getText());
                txtrucci.requestFocus();

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
            dao.Eliminar_PersonalVO(vo);
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
                txtciudad.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un codigo válido");
            txtnombredpto.setText("");
            txtdpto.setText("");
            txtdpto.requestFocus();
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
                txtbarrio.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un codigo válido");
            txtnombreciudad.setText("");
            txtciudad.setText("");
            txtciudad.requestFocus();
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
                txtsucursal.requestFocus();
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
                txtprofesion.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un codigo válido");
            txtnombresucursal.setText("");
            txtsucursal.setText("");
            txtsucursal.requestFocus();
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
                txtcasa.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un codigo válido");
            txtnombreprofesion.setText("");
            txtprofesion.setText("");
            txtprofesion.requestFocus();
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
                txtdpto.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un codigo válido");
            txtnombrepais.setText("");
            txtpais.setText("");
            txtpais.requestFocus();
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
        txtrucci = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jdcfechaingreso = new com.toedter.calendar.JDateChooser();
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
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        m_guardar = new javax.swing.JMenuItem();
        m_salir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(233, 236, 216));

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

        jLabel2.setText("RUC - CI :");

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

        jLabel4.setText("Código :");

        jLabel5.setText("Fecha de Ingreso :");

        jdcfechaingreso.setDateFormatString("dd-MM-y");
        jdcfechaingreso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jdcfechaingresoKeyPressed(evt);
            }
        });

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

        jLabel3.setText("Nombres :");

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

        jLabel6.setText("Apellidos :");

        jdcfechanac.setDateFormatString("dd-MM-y");
        jdcfechanac.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jdcfechanacKeyPressed(evt);
            }
        });

        jLabel7.setText("Fecha de Nac. :");

        jLabel8.setText("Sexo :");

        groupSexo.add(jrbmasculino);
        jrbmasculino.setText("Masculino");

        groupSexo.add(jrbfemenino);
        jrbfemenino.setText("Femenino");

        jLabel9.setText("Estado Civil :");

        groupEstadoCivil.add(jrdsoltero);
        jrdsoltero.setText("Soltero");

        groupEstadoCivil.add(jrdcasado);
        jrdcasado.setText("Casado");

        groupEstadoCivil.add(jrdseparado);
        jrdseparado.setText("Separado");

        groupEstadoCivil.add(jrdviudo);
        jrdviudo.setText("Viudo");

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

        jLabel10.setText("Pais :");

        txtnombrepais.setEditable(false);
        txtnombrepais.setBackground(new java.awt.Color(250, 250, 250));
        txtnombrepais.setToolTipText("");

        txtnombredpto.setEditable(false);
        txtnombredpto.setBackground(new java.awt.Color(250, 250, 250));
        txtnombredpto.setToolTipText("");

        txtdpto.setToolTipText("");
        txtdpto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdptoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdptoKeyReleased(evt);
            }
        });

        jLabel11.setText("Departamento :");

        jLabel12.setText("Ciudad :");

        txtnombreciudad.setEditable(false);
        txtnombreciudad.setBackground(new java.awt.Color(250, 250, 250));
        txtnombreciudad.setToolTipText("");

        txtciudad.setToolTipText("");
        txtciudad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtciudadKeyPressed(evt);
            }
        });

        txtnombrebarrio.setEditable(false);
        txtnombrebarrio.setBackground(new java.awt.Color(250, 250, 250));
        txtnombrebarrio.setToolTipText("");

        txtbarrio.setToolTipText("");
        txtbarrio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbarrioKeyPressed(evt);
            }
        });

        jLabel13.setText("Barrio :");

        txtsucursal.setToolTipText("");
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

        jLabel14.setText("Sucursal :");

        txtnombresucursal.setEditable(false);
        txtnombresucursal.setBackground(new java.awt.Color(250, 250, 250));
        txtnombresucursal.setToolTipText("");

        jLabel15.setText("Profesión :");

        txtprofesion.setToolTipText("");
        txtprofesion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtprofesionKeyPressed(evt);
            }
        });

        txtnombreprofesion.setEditable(false);
        txtnombreprofesion.setBackground(new java.awt.Color(250, 250, 250));
        txtnombreprofesion.setToolTipText("");

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

        jLabel16.setText("Casa Número :");

        txttelefono.setToolTipText("");
        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttelefonoKeyPressed(evt);
            }
        });

        jLabel17.setText("Número de Teléfono :");

        jLabel18.setText("Correo Electrónico :");

        txtemail.setToolTipText("");
        txtemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtemailKeyPressed(evt);
            }
        });

        jLabel19.setText("Referencia :");

        txtreferencia.setToolTipText("");
        txtreferencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtreferenciaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jLabel9))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
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
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jrdsoltero)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jrdcasado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jrdseparado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jrdviudo))
                    .addComponent(txtcasa, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtreferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtapellido, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcfechanac, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jrbmasculino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jrbfemenino))
                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtrucci, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcfechaingreso, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(txtnombrepais, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(b_guardar)
                .addGap(6, 6, 6)
                .addComponent(b_guardarynuevo)
                .addGap(6, 6, 6)
                .addComponent(b_eliminar)
                .addGap(6, 6, 6)
                .addComponent(b_cancelar))
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcfechaingreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
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
                    .addComponent(txtcasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtreferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b_guardar)
                    .addComponent(b_guardarynuevo)
                    .addComponent(b_eliminar)
                    .addComponent(b_cancelar))
                .addGap(11, 11, 11)
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
        if (validar() ) {
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
            jdcfechaingreso.transferFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER){
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

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER){
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

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER){
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
                dp.lblejecucion.setText("con_per");
                dp.setVisible(true);
            } else {
                listarsucursal(txtsucursal.getText());
            }
        }
    }//GEN-LAST:event_txtsucursalKeyPressed

    private void txtsucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsucursalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsucursalActionPerformed

    private void jdcfechaingresoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jdcfechaingresoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jdcfechaingresoKeyPressed

    private void txtpaisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpaisKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtapellido.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtpais.getText().length() == 0) {
                paisVista dp = new paisVista();
                dp.setResizable(false);
                dp.setLocationRelativeTo(null);
                dp.lblejecucion.setText("con_per");
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
                dp.lblejecucion.setText("con_per");
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
                dp.lblejecucion.setText("con_per");
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
                dp.lblejecucion.setText("con_per");
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
                dp.lblejecucion.setText("con_per");
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

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            txttelefono.requestFocus();
        }
    }//GEN-LAST:event_txtcasaKeyPressed

    private void txttelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtcasa.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            txtemail.requestFocus();
        }
    }//GEN-LAST:event_txttelefonoKeyPressed

    private void txtemailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txttelefono.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            txtreferencia.requestFocus();
        }
    }//GEN-LAST:event_txtemailKeyPressed

    private void txtreferenciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtreferenciaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtemail.requestFocus();

        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            b_guardar.requestFocus();
        }
    }//GEN-LAST:event_txtreferenciaKeyPressed

    private void jdcfechanacKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jdcfechanacKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jdcfechanacKeyPressed

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
            java.util.logging.Logger.getLogger(personalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new personalForm().setVisible(true);
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
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
    private com.toedter.calendar.JDateChooser jdcfechaingreso;
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
    public static javax.swing.JTextField txtcodigo;
    public static javax.swing.JTextField txtdpto;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtnombrebarrio;
    private javax.swing.JTextField txtnombreciudad;
    private javax.swing.JTextField txtnombredpto;
    private javax.swing.JTextField txtnombrepais;
    private javax.swing.JTextField txtnombreprofesion;
    private javax.swing.JTextField txtnombresucursal;
    public static javax.swing.JTextField txtpais;
    public static javax.swing.JTextField txtprofesion;
    private javax.swing.JTextField txtreferencia;
    private javax.swing.JTextField txtrucci;
    public static javax.swing.JTextField txtsucursal;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables

    //varibales para enviar datos   
}
