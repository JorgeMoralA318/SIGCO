package Tabla;

import Conexion.Conectar;
import Vista.cargoPersonalVista;
import java.sql.*;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class tablaCargoPersonal {

    cargoPersonalVista vista;
    DefaultTableModel modelo;
    PreparedStatement ps = null;
    String[] titulosColumnas = {"Código", "fecha Alta", "RUC - CI", "Nombres y Apellidos", "Salario", "Cargo"};
    //matriz donde se almacena los datos de cada celda de la tabla
    String info[][] = {};
    private final boolean[] editable = {false, false, false, false, false, false};

    private void ancho_columnas() {
        int[] anchos = {50, 70, 90, 160, 80, 100};
        for (int i = 0; i < vista.tabla.getColumnCount(); i++) {
            vista.tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        vista.tabla.setAutoResizeMode(vista.tabla.AUTO_RESIZE_OFF);
    }

//controlador de la tabla, opcionalmente se puede colocar dentro del formulario o en una nueva clase
    public void mostrar(String buscar) {

        modelo = new DefaultTableModel(info, titulosColumnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return editable[column];
            }
        };

        vista.tabla.setModel(modelo);
        ancho_columnas();
        //ejecuta una consulta a la BD
        buscar(buscar);
    }

    public void buscar(String buscar) {
        Conectar conec = new Conectar();
        String simbolo = "###,###.##";
        DecimalFormat df = new DecimalFormat(simbolo);

        String sql = "SELECT a.codigo, DATE_FORMAT(a.fecha_alta, \"%d-%m-%Y\") AS fecha_alta,\n"
                + "b.ciRuc,CONCAT(b.nombre,\" \",b.apellido) AS nombre,\n"
                + "a.salario,\n"
                + "c.cargo\n"
                + "FROM cargo_salario a\n"
                + "INNER JOIN personal b ON a.codPersonal = b.codigo\n"
                + "INNER JOIN cargo c ON a.codCargo = c.codigo WHERE b.nombre LIKE ? AND a.estado ='A'";

        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, "%" + buscar + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String codigo = rs.getString(1);
                String fecha_alta = rs.getString(2);
                String ruc = rs.getString(3);
                String nombre = rs.getString(4);
                String salario = df.format(rs.getDouble(5));
                String cargo = rs.getString(6);

                //crea un vector donde  está la informacion (se crea una fila)
                Object[] datos = {codigo, fecha_alta, ruc, nombre, salario, cargo};
                //al modelo de la tabla le agrega una fila
                //con los datos que están en datos
                modelo.addRow(datos);
            }

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
        }

    }

}
