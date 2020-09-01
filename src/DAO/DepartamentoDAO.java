package DAO;

import Conexion.Conectar;
import VO.DepartamentoVO;
import ds.desktop.notify.DesktopNotify;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class DepartamentoDAO{
    
//metodo generar codigo
public String obtener_id() {
        Conectar cn = null;
        java.sql.Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String id = "";
        String sql = "SELECT IF(COUNT(*) IS NULL,'1',COUNT(*) +1) AS codigo FROM departamento; ";
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
    public ArrayList<DepartamentoVO> Listar_DepartamentoVO(String codigo){
        ArrayList<DepartamentoVO> list = new ArrayList<>();
        Conectar conec = new Conectar();
        String sql = "SELECT departamento FROM departamento WHERE codigo=?";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1,codigo);
            rs = ps.executeQuery();
            while(rs.next()){
                DepartamentoVO vo = new DepartamentoVO();
                vo.setDepartamento(rs.getString(1));
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
            }catch(SQLException ex){ ex.getMessage();}
        }
        return list;
    }


/*Metodo agregar*/
    public void Agregar_DepartamentoVO(DepartamentoVO vo){
        Conectar conec = new Conectar();
        String sql = "INSERT INTO departamento (codigo, departamento) VALUES(?, ?);";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            ps.setString(2, vo.getDepartamento());
            int r = ps.executeUpdate();
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
            }catch(SQLException ex){}
        }
    }


/*Metodo Modificar*/
    public void Modificar_DepartamentoVO(DepartamentoVO vo){
        Conectar conec = new Conectar();
        String sql = "UPDATE departamento SET departamento = ? WHERE codigo = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
           
            ps.setString(1, vo.getDepartamento());
             ps.setString(2, vo.getCodigo());
           int r= ps.executeUpdate();
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
    public void Eliminar_DepartamentoVO(DepartamentoVO vo){
        Conectar conec = new Conectar();
        String sql = "DELETE FROM departamento WHERE codigo = ?;";
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
