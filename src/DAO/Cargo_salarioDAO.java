package DAO;

import Conexion.Conectar;
import VO.Cargo_salarioVO;
import ds.desktop.notify.DesktopNotify;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Cargo_salarioDAO{
    
    //metodo para obtener codigo por defecto
    public String obtener_id() {
        Conectar cn = null;
        java.sql.Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String id = "";
        String sql = "SELECT IF(COUNT(*) IS NULL,'1',COUNT(*) +1) AS codigo FROM cargo_salario; ";
        try {
            cn = new Conectar();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getString(1);
            }
        } catch ( SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        cn.desconectar();
        return id;
        
    }

/*Metodo listar*/
    public ArrayList<Cargo_salarioVO> Listar_Cargo_salarioVO(String codigo){
        ArrayList<Cargo_salarioVO> list = new ArrayList<Cargo_salarioVO>();
        Conectar conec = new Conectar();
        String sql = "SELECT * FROM cargo_salario WHERE codigo = ?;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            while(rs.next()){
                Cargo_salarioVO vo = new Cargo_salarioVO();
                vo.setCodigo(rs.getString(1));
                vo.setCodCargo(rs.getString(2));
                vo.setCodPersonal(rs.getString(3));
                vo.setFecha_alta(rs.getString(4));
                vo.setSalario(rs.getObject(5));
                vo.setFecha_baja(rs.getString(6));
                vo.setCodMotivo(rs.getString(7));
                vo.setComVenta(rs.getInt(8));
                vo.setComCobro(rs.getInt(9));
                vo.setEstado(rs.getObject(10));
                list.add(vo);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                rs.close();
                conec.desconectar();
            }catch(SQLException ex){}
        }
        return list;
    }


/*Metodo agregar*/
    public void Agregar_Cargo_salarioVO(Cargo_salarioVO vo){
        Conectar conec = new Conectar();
        String sql = "INSERT INTO cargo_salario (codigo, codCargo, codPersonal, fecha_alta, salario,comVenta, comCobro) VALUES(?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            ps.setString(2, vo.getCodCargo());
            ps.setString(3, vo.getCodPersonal());
            ps.setString(4, vo.getFecha_alta());
            ps.setObject(5, vo.getSalario());
            ps.setInt(6, vo.getComVenta());
            ps.setInt(7, vo.getComCobro());
            int r = ps.executeUpdate();
            if (r > 0) {
                DesktopNotify.showDesktopMessage("Mensaje", "Registrado Exitosamente", 1, 5000);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                conec.desconectar();
            }catch(SQLException ex){}
        }
    }


/*Metodo Modificar*/
    public void Modificar_Cargo_salarioVO(Cargo_salarioVO vo){
        Conectar conec = new Conectar();
        String sql = "UPDATE cargo_salario SET codCargo = ?, codPersonal = ?, fecha_alta = ?, salario = ?, comVenta = ?, comCobro = ? WHERE codigo = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
          
            ps.setString(1, vo.getCodCargo());
            ps.setString(2, vo.getCodPersonal());
            ps.setString(3, vo.getFecha_alta());
            ps.setObject(4, vo.getSalario());
            ps.setInt(5, vo.getComVenta());
            ps.setInt(6, vo.getComCobro());
            ps.setString(7, vo.getCodigo());
            int r = ps.executeUpdate();
            if (r > 0) {
                DesktopNotify.showDesktopMessage("Mensaje", "Actualizado Exitosamente", 1, 5000);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                conec.desconectar();
            }catch(SQLException ex){}
        }
    }


/*Metodo Eliminar*/
    public void Eliminar_Cargo_salarioVO(Cargo_salarioVO vo){
        Conectar conec = new Conectar();
        String sql = "DELETE FROM cargo_salario WHERE codigo = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            int r = ps.executeUpdate();
            if (r>0){
                DesktopNotify.showDesktopMessage("Mensaje", " Registro Eliminado", 1, 5000);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                conec.desconectar();
            }catch(SQLException ex){}
        }
    }


}
