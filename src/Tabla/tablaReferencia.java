package Tabla;


import Conexion.Conectar;
import Vista.referenciaCliente;
import java.sql.*;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class tablaReferencia{

    referenciaCliente vista;
    DefaultTableModel modelo;
    PreparedStatement ps = null;
    String[] titulosColumnas = {"Código","RUC - CI","Nombres y Apellidos","Direccion","Contacto","Obs","Tipo"};
    //matriz donde se almacena los datos de cada celda de la tabla
    String info[][] = {};
    private final boolean[] editable = {false,false,false,false,false,false,false};

    private void ancho_columnas() {
        int[] anchos = {50, 90,200,100,100,100,70};
        for (int i = 0; i < vista.tabla.getColumnCount(); i++) {
            vista.tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        vista.tabla.setAutoResizeMode(vista.tabla.AUTO_RESIZE_OFF);
    }

//controlador de la tabla, opcionalmente se puede colocar dentro del formulario o en una nueva clase
    public void mostrar(String codigo) {

        modelo = new DefaultTableModel(info, titulosColumnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return editable[column];
            }
        };

        vista.tabla.setModel(modelo);
        ancho_columnas();
        //ejecuta una consulta a la BD
        buscar(codigo);
    }

    public void buscar(String codigo) {
        Conectar conec = new Conectar();
        String simbolo = "###,###.##";
        DecimalFormat df = new DecimalFormat(simbolo);
            

        String sql ="SELECT codigo,ci_ruc,nombre,direccion,contacto,obs,tipoRef FROM referencia WHERE codCliente =?";

        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1,codigo );
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String cod = rs.getString(1);
                String ruc = rs.getString(2);
                String nombre = rs.getString(3);
                String direccion = rs.getString(4);
                String contacto = rs.getString(5);
                String obs= rs.getString(6);
                String tipo = rs.getString(7) ;

                //crea un vector donde  está la informacion (se crea una fila)
                Object[] datos = {cod,ruc,nombre,direccion,contacto,obs,tipo};
                //al modelo de la tabla le agrega una fila
                //con los datos que están en datos
                modelo.addRow(datos);
            }

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
        }

    }

}
