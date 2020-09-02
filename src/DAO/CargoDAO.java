package DAO;

import Conexion.Conectar;
import VO.CargoVO;
import ds.desktop.notify.DesktopNotify;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class CargoDAO{
    
    //metodo para obtener codigo por defecto
    public String obtener_id() {
        Conectar cn = null;
        java.sql.Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String id = "";
        String sql = "SELECT IF(COUNT(*) IS NULL,'1',COUNT(*) +1) AS codigo FROM cargo; ";
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
    public ArrayList<CargoVO> Listar_CargoVO(String codigo){
        ArrayList<CargoVO> list = new ArrayList<CargoVO>();
        Conectar conec = new Conectar();
        String sql = "SELECT * FROM cargo;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1,codigo);
            rs = ps.executeQuery();
            while(rs.next()){
                CargoVO vo = new CargoVO();
                vo.setCodigo(rs.getString(1));
                vo.setCargo(rs.getString(2));
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
    public void Agregar_CargoVO(CargoVO vo){
        Conectar conec = new Conectar();
        String sql = "INSERT INTO cargo (codigo, cargo) VALUES(?, ?);";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            ps.setString(2, vo.getCargo());
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
    public void Modificar_CargoVO(CargoVO vo){
        Conectar conec = new Conectar();
        String sql = "UPDATE cargo SET cargo = ? WHERE codigo = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            
            ps.setString(1, vo.getCargo());
            ps.setString(2, vo.getCodigo());
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
    public void Eliminar_CargoVO(CargoVO vo){
        Conectar conec = new Conectar();
        String sql = "DELETE FROM cargo WHERE codigo = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
           int r= ps.executeUpdate();
           if (r>0){
                DesktopNotify.showDesktopMessage("Mensaje", "Registro eliminado", 1, 5000);
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
