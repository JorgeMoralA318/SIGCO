package DAO;

import Conexion.Conectar;
import VO.UsuarioVO;
import Vista.obtenerCodigo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class UsuarioDAO{
    
     mensaje aviso = new mensaje();
        public String obtener_id() {
        Conectar cn = null;
        java.sql.Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String id = "";
        String sql = "SELECT IF(COUNT(*) IS NULL,'1',COUNT(*) +1) AS codigo FROM usuario; ";
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
    public ArrayList<UsuarioVO> Listar_UsuarioVO(String codigo){
        ArrayList<UsuarioVO> list = new ArrayList<UsuarioVO>();
        Conectar conec = new Conectar();
        String sql = "SELECT * FROM usuario WHERE codigo = ?;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1,codigo);
            rs = ps.executeQuery();
            while(rs.next()){
                UsuarioVO vo = new UsuarioVO();
                vo.setCodigo(rs.getString(1));
                vo.setCodPersonal(rs.getString(2));
                vo.setCodRol(rs.getString(3));
                vo.setLogin(rs.getString(4));
                vo.setClave(rs.getString(5));
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
    public void Agregar_UsuarioVO(UsuarioVO vo){
        Conectar conec = new Conectar();
        String sql = "INSERT INTO usuario (codigo, codPersonal, codRol, Login, Clave) VALUES(?, ?, ?, ?, ?);";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            ps.setString(2, vo.getCodPersonal());
            ps.setString(3, vo.getCodRol());
            ps.setString(4, vo.getLogin());
            ps.setString(5, vo.getClave());
            int r=ps.executeUpdate();
            if(r>0){
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
    public void Modificar_UsuarioVO(UsuarioVO vo){
        Conectar conec = new Conectar();
        String sql = "UPDATE usuario SET codPersonal = ?, codRol = ?, Login = ? WHERE codigo = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            
            ps.setString(1, vo.getCodPersonal());
            ps.setString(2, vo.getCodRol());
            ps.setString(3, vo.getLogin());
            ps.setString(4, vo.getCodigo());
           int r = ps.executeUpdate();
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
    
    //actualizar contraseña
    public void Actualizar_contraseña(UsuarioVO vo){
        Conectar conec = new Conectar();
        String sql = "UPDATE usuario SET clave= ? WHERE codigo = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            
            ps.setString(1, vo.getClave());
            ps.setString(2, vo.getCodigo());
           int r = ps.executeUpdate();
           
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
    public void Eliminar_UsuarioVO(UsuarioVO vo){
        Conectar conec = new Conectar();
        String sql = "DELETE FROM usuario WHERE codigo = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            int r =ps.executeUpdate();
            if(r>0){
                aviso.eliminar();
                
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
