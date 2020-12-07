package Tabla;


import Conexion.Conectar;
import Vista.usuarioVista;
import java.sql.*;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class tablaUsuario{

   usuarioVista vista;
    DefaultTableModel modelo;
    PreparedStatement ps = null;
    String[] titulosColumnas = {"Codigo","Nombres y Apellidos","Rol","Usuario"};
    //matriz donde se almacena los datos de cada celda de la tabla
    String info[][] = {};
    private final boolean[] editable = {false,false,false,false};

    private void ancho_columnas() {
        int[] anchos = {70, 200,100,100};
        for (int i = 0; i < vista.tabla.getColumnCount(); i++) {
            vista.tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        vista.tabla.setAutoResizeMode(vista.tabla.AUTO_RESIZE_OFF);
    }

//controlador de la tabla, opcionalmente se puede colocar dentro del formulario o en una nueva clase
    public void mostrar(String buscar, String parametro) {

        modelo = new DefaultTableModel(info, titulosColumnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return editable[column];
            }
        };

        vista.tabla.setModel(modelo);
        ancho_columnas();
        //ejecuta una consulta a la BD
        buscar(buscar,parametro);
    }

    public void buscar(String buscar, String parametro) {
        Conectar conec = new Conectar();
        String simbolo = "###,###.##";
        DecimalFormat df = new DecimalFormat(simbolo);
            

        String sql ="SELECT c.codigo,CONCAT(a.nombre,\" \",a.apellido),b.rol,c.Login \n" +
"FROM personal a INNER JOIN usuario c ON a.codigo = c.codPersonal \n" +
"INNER JOIN rol b ON c.codRol = b.codigo WHERE a." + parametro +" LIKE ?";

        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, "%" + buscar + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String ruc = rs.getString(1);
                String nombre = rs.getString(2);
                String rol= rs.getString(3);
                String login = rs.getString(4) ;

                //crea un vector donde  está la informacion (se crea una fila)
                Object[] datos = {ruc,nombre,rol,login};
                //al modelo de la tabla le agrega una fila
                //con los datos que están en datos
                modelo.addRow(datos);
            }

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
        }

    }

}
