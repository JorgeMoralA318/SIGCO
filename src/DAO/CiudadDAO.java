package DAO;

import Conexion.Conectar;
import VO.CiudadVO;
import ds.desktop.notify.DesktopNotify;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class CiudadDAO{
    
    
    
    public String obtener_id() {
        Conectar cn = null;
        java.sql.Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String id = "";
        String sql = "SELECT IF(COUNT(*) IS NULL,'1',COUNT(*) +1) AS codigo FROM ciudad; ";
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
    public ArrayList<CiudadVO> Listar_CiudadVO(String codigo){
        ArrayList<CiudadVO> list = new ArrayList<>();
        Conectar conec = new Conectar();
        String sql = "SELECT * FROM ciudad WHERE codigociudad=?;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1,codigo);
            rs = ps.executeQuery();
            while(rs.next()){
                CiudadVO vo = new CiudadVO();
                vo.setCodigo(rs.getString(1));
                vo.setCiudad(rs.getString(2));
                vo.setCodDepartamento(rs.getString(3));
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
    public void Agregar_CiudadVO(CiudadVO vo){
        Conectar conec = new Conectar();
        String sql = "INSERT INTO ciudad (codigociudad,ciudad,codigodpto) VALUES(?, ?, ?);";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            ps.setString(2, vo.getCiudad());
            ps.setString(3, vo.getCodDepartamento());
           
            int r =ps.executeUpdate();
            if (r>0){
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
            }catch(SQLException ex){System.out.println(ex.getMessage());}
        }
    }


/*Metodo Modificar*/
    public void Modificar_CiudadVO(CiudadVO vo){
        Conectar conec = new Conectar();
        String sql = "UPDATE ciudad SET ciudad = ?, codigodpto = ? WHERE codigociudad = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            
            ps.setString(1, vo.getCiudad());
            ps.setString(2, vo.getCodDepartamento());
            ps.setString(3, vo.getCodigo());
            int r =ps.executeUpdate();
            if (r>0){
                DesktopNotify.showDesktopMessage("Mensaje", "Actualizado Exitosamente", 1, 5000);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }finally{
            try{
                ps.close();
                conec.desconectar();
            }catch(SQLException ex){JOptionPane.showMessageDialog(null, ex);}
        }
    }


/*Metodo Eliminar*/
    public void Eliminar_CiudadVO(CiudadVO vo){
        Conectar conec = new Conectar();
        String sql = "DELETE FROM ciudad WHERE codigociudad = ?;";
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
