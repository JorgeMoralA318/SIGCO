package DAO;

import Conexion.Conectar;
import VO.RolVO;
import Vista.obtenerCodigo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class RolDAO{
    
    mensaje aviso = new mensaje();
    public String obtener_id() {
        Conectar cn = null;
        java.sql.Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String id = "";
        String sql = "SELECT IF(COUNT(*) IS NULL,'1',COUNT(*) +1) AS codigo FROM rol; ";
        obtenerCodigo obcodigo = new obtenerCodigo();
        try {
            cn = new Conectar();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getString(1);
                id = obcodigo.codigoDefecto(id, id.length());
                
            }
        } catch ( SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        cn.desconectar();
        return id;
        
    }

/*Metodo listar*/
    public ArrayList<RolVO> Listar_RolVO(String codigo){
        ArrayList<RolVO> list = new ArrayList<RolVO>();
        Conectar conec = new Conectar();
        String sql = "SELECT * FROM rol WHERE codigo = ?;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1,codigo);
            rs = ps.executeQuery();
            while(rs.next()){
                RolVO vo = new RolVO();
                vo.setCodigo(rs.getString(1));
                vo.setRol(rs.getString(2));
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
    public void Agregar_RolVO(RolVO vo){
        Conectar conec = new Conectar();
        String sql = "INSERT INTO rol (codigo, rol) VALUES(?, ?);";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            ps.setString(2, vo.getRol());
            int r =ps.executeUpdate();
            if (r>0){
            aviso.guardar();
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
    public void Modificar_RolVO(RolVO vo){
        Conectar conec = new Conectar();
        String sql = "UPDATE rol SET rol = ? WHERE codigo = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            
            ps.setString(1, vo.getRol());
            ps.setString(2, vo.getCodigo());
            int r =ps.executeUpdate();
            if(r>0){
                aviso.actualizar();
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
    public void Eliminar_RolVO(RolVO vo){
        Conectar conec = new Conectar();
        String sql = "DELETE FROM rol WHERE codigo = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                conec.desconectar();
            }catch(Exception ex){}
        }
    }


}
