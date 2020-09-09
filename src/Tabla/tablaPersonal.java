package Tabla;


import Conexion.Conectar;
import Vista.personalVista;
import java.sql.*;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class tablaPersonal{

   personalVista vista;
    DefaultTableModel modelo;
    PreparedStatement ps = null;
    String[] titulosColumnas = {"Código","RUC - CI","Nombres y Apellidos","Ciudad","Barrio","Nº Teléfono","Email"};
    //matriz donde se almacena los datos de cada celda de la tabla
    String info[][] = {};
    private final boolean[] editable = {false,false,false,false,false,false,false};

    private void ancho_columnas() {
        int[] anchos = {70, 90,200,100,100,100,130};
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
            

        String sql ="SELECT a.codigo,a.ciRuc,CONCAT(a.nombre,\" \",a.apellido) AS nombre,\n" +
"                b.ciudad,c.barrio,a.telefono,a.email FROM Personal a\n" +
"                INNER JOIN Ciudad b ON a.codCiudad = b.codigo\n" +
"                INNER JOIN Barrio c ON a.codBarrio = c.codigo WHERE a.nombre LIKE ? AND a.estado ='A'";

        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, "%" + buscar + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String codigo = rs.getString(1);
                String ruc = rs.getString(2);
                String nombre = rs.getString(3);
                String ciudad = rs.getString(4);
                String barrio = rs.getString(5);
                String telefono= rs.getString(6);
                String email = rs.getString(7) ;

                //crea un vector donde  está la informacion (se crea una fila)
                Object[] datos = {codigo,ruc,nombre,ciudad,barrio,telefono,email};
                //al modelo de la tabla le agrega una fila
                //con los datos que están en datos
                modelo.addRow(datos);
            }

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
        }

    }

}
