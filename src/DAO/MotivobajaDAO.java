package DAO;

import Conexion.Conectar;
import VO.MotivobajaVO;
import ds.desktop.notify.DesktopNotify;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class MotivobajaDAO{
    
    
    
     // metodo para obtener codigo por defecto
    public String obtener_id() {
            Conectar cn = null;
            Connection con;
            PreparedStatement ps;
            ResultSet rs;
            String id = "";
            String sql = "SELECT IF(COUNT(*) IS NULL,'1',COUNT(*) +1) AS codigo FROM motivoBaja; ";
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
    public ArrayList<MotivobajaVO> Listar_MotivobajaVO(String codigo){
        ArrayList<MotivobajaVO> list = new ArrayList<MotivobajaVO>();
        Conectar conec = new Conectar();
        String sql = "SELECT * FROM motivobaja WHERE codigo = ?;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1,codigo);
            rs = ps.executeQuery();
            while(rs.next()){
                MotivobajaVO vo = new MotivobajaVO();
                vo.setCodigo(rs.getString(1));
                vo.setMotivo(rs.getString(2));
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
    public void Agregar_MotivobajaVO(MotivobajaVO vo){
        Conectar conec = new Conectar();
        String sql = "INSERT INTO motivobaja (codigo, motivo) VALUES(?, ?);";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            ps.setString(2, vo.getMotivo());
            int r =ps.executeUpdate();
            if (r>0){
                DesktopNotify.showDesktopMessage("Mensaje", "Registrado Exitosamente", 1, 3000);
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
    public void Modificar_MotivobajaVO(MotivobajaVO vo){
        Conectar conec = new Conectar();
        String sql = "UPDATE motivobaja SET motivo = ? WHERE codigo = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            
            ps.setString(1, vo.getMotivo());
            ps.setString(2, vo.getCodigo());
           int r =ps.executeUpdate();
            if (r>0){
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
    public void Eliminar_MotivobajaVO(MotivobajaVO vo){
        Conectar conec = new Conectar();
        String sql = "DELETE FROM motivobaja WHERE codigo = ?;";
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
