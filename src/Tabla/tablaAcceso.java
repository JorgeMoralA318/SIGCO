
package Tabla;

import Conexion.Conectar;
import Vista.rolForm;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class tablaAcceso {

    rolForm vista;
    DefaultTableModel modelo;
    PreparedStatement ps = null;
    String[] titulosColumnas = {"ID","COD.ROL","MODULO","VENTANA","ACCESO","NUEVO","MODIFICAR","BORRAR"};
    //matriz donde se almacena los datos de cada celda de la tabla
    String info[][] = {};
    private final boolean[] editable = {false,false,false,false,true,true,true,true};

    private void ancho_columnas() {
        int[] anchos = {50,70,120,120,70,70,70,70};
        for (int i = 0; i < vista.tbAcceso.getColumnCount(); i++) {
            vista.tbAcceso.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        vista.tbAcceso.setAutoResizeMode(vista.tbAcceso.AUTO_RESIZE_OFF);
    }

//controlador de la tabla, opcionalmente se puede colocar dentro del formulario o en una nueva clase
    public void mostrar(String codModulo,String codRol) {

        modelo = new DefaultTableModel(info, titulosColumnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return editable[column];
            }
        };

        vista.tbAcceso.setModel(modelo);
        ancho_columnas();
        //ejecuta una consulta a la BD
        buscar(codModulo,codRol);
    }

    public void buscar(String codModulo,String codRol) {
        Conectar conec = new Conectar();
        String simbolo = "###,###.##";
        DecimalFormat df = new DecimalFormat(simbolo);
            

        String sql = "SELECT a.idAcceso,a.codRol,b.modulo,c.ventana,a.acceso,a.nuevo,a.actualizar,a.borrar\n" +
"FROM acceso a INNER JOIN modulo b ON a.codModulo=b.codigo\n" +
"INNER JOIN ventana c ON a.codVentana = c.codigo WHERE a.codModulo LIKE ? AND a.codRol = ?;";

        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, "%" + codModulo + "%");
            ps.setString(2,codRol);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String id = rs.getString(1);
                String codrol = rs.getString(2);
                String modulo = rs.getString(3);
                String ventana = rs.getString(4);
                Boolean acceso=rs.getBoolean(5);
                Boolean nuevo=rs.getBoolean(6);
                Boolean actualizar = rs.getBoolean(7);
                Boolean borrar= rs.getBoolean(8);
                

                //crea un vector donde  está la informacion (se crea una fila)
                Object[] datos = {id,codrol,modulo,ventana,acceso,nuevo,actualizar,borrar};
                //al modelo de la tabla le agrega una fila
                //con los datos que están en datos
                
                modelo.addRow(datos);
            }

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
        }

    }

}

