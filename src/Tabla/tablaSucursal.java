package Tabla;

import Conexion.Conectar;
import Vista.sucursalVista;
import java.sql.*;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class tablaSucursal {

    sucursalVista vista;
    DefaultTableModel modelo;
    PreparedStatement ps = null;
    String[] titulosColumnas = {"Código", "RUC", "Sucursal", "Dirección", "Teléfono", "Empresa"};
    //matriz donde se almacena los datos de cada celda de la tabla
    String info[][] = {};
    private final boolean[] editable = {false, false, false, false, false, false};

    private void ancho_columnas() {
        int[] anchos = {80, 80, 120, 140, 80, 80};
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

        String sql = "SELECT a.codigo,a.ruc,a.sucursal,a.direccion,a.Telefono, b.empresa\n"
                + "FROM sucursal a INNER JOIN empresa b\n"
                + "ON a.codEmpresa= b.codigo WHERE a.sucursal LIKE ?";

        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, "%" + buscar + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String codigo = rs.getString(1);
                String ruc = rs.getString(2);
                String sucursal = rs.getString(3);
                String direccion = rs.getString(4);
                String telefono = rs.getString(5);
                String empresa = rs.getString(6);

                //crea un vector donde  está la informacion (se crea una fila)
                Object[] datos = {codigo, ruc, sucursal, direccion, telefono, empresa};
                //al modelo de la tabla le agrega una fila
                //con los datos que están en datos
                modelo.addRow(datos);
            }

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
        }

    }

}
