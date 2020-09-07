
package DAO;

import Conexion.Conectar;
import java.sql.*;
import VO.NivelVO;
import ds.desktop.notify.DesktopNotify;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class NivelDAO {
    // metodo para obtener codigo por defecto
    public String obtener_id() {
            Conectar cn = null;
            Connection con;
            PreparedStatement ps;
            ResultSet rs;
            String id = "";
            String sql = "SELECT IF(COUNT(*) IS NULL,'1',COUNT(*) +1) AS codigo FROM nivel; ";
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
    public ArrayList<NivelVO> Listar_NivelVO(String codigo){
        ArrayList<NivelVO> list = new ArrayList<NivelVO>();
        Conectar conec = new Conectar();
        String sql = "SELECT * FROM nivel WHERE codigo = ?;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1,codigo);
            rs = ps.executeQuery();
            while(rs.next()){
                NivelVO vo = new NivelVO();
                vo.setCodigo(rs.getString(1));
                vo.setNivel(rs.getString(2));
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
            }catch(SQLException ex){ex.getMessage();}
        }
        return list;
    }
    
    /*Metodo agregar*/
    public void Agregar_NivelVO(NivelVO vo){
        Conectar conec = new Conectar();
        String sql = "INSERT INTO nivel (codigo, nivel) VALUES(?, ?);";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            ps.setString(2, vo.getNivel());
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
    public void Modificar_NivelVO(NivelVO vo){
        Conectar conec = new Conectar();
        String sql = "UPDATE nivel SET nivel = ? WHERE codigo = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            
            ps.setString(1, vo.getNivel());
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
    public void Eliminar_NivelVO(NivelVO vo){
        Conectar conec = new Conectar();
        String sql = "DELETE FROM nivel WHERE codigo = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            int r =ps.executeUpdate();
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
