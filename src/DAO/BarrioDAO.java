package DAO;

import Conexion.Conectar;
import VO.BarrioVO;
import Vista.obtenerCodigo;
import ds.desktop.notify.DesktopNotify;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class BarrioDAO{
    
   mensaje aviso = new mensaje(); 
    
    public String obtener_id() {
        Conectar cn = null;
        java.sql.Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String id = "";
        String sql = "SELECT IF(COUNT(*) IS NULL,'1',COUNT(*) +1) AS codigo FROM barrio; ";
        try {
            cn = new Conectar();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getString(1);
                id = obtenerCodigo.codigoDefecto(id, id.length());
            }
        } catch ( SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        cn.desconectar();
        return id;
        
    } 

/*Metodo listar*/
    public ArrayList<BarrioVO> Listar_BarrioVO(String codigo){
        ArrayList<BarrioVO> list = new ArrayList<BarrioVO>();
        Conectar conec = new Conectar();
        String sql = "SELECT * FROM barrio WHERE codigo = ?;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1,codigo);
            rs = ps.executeQuery();
            while(rs.next()){
                BarrioVO vo = new BarrioVO();
                vo.setCodigo(rs.getString(1));
                vo.setBarrio(rs.getString(2));
                vo.setCodciudad(rs.getString(3));
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
    public void Agregar_BarrioVO(BarrioVO vo){
        Conectar conec = new Conectar();
        String sql = "INSERT INTO barrio (codigo, barrio, codCiudad) VALUES(?, ?, ?);";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            ps.setString(2, vo.getBarrio());
            ps.setString(3, vo.getCodciudad());
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
    public void Modificar_BarrioVO(BarrioVO vo){
        Conectar conec = new Conectar();
        String sql = "UPDATE barrio SET barrio = ?, codCiudad = ? WHERE codigo = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            
            ps.setString(1, vo.getBarrio());
            ps.setString(2, vo.getCodciudad());
            ps.setString(3, vo.getCodigo());
           int r =ps.executeUpdate();
            if (r>0){
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
    public void Eliminar_BarrioVO(BarrioVO vo){
        Conectar conec = new Conectar();
        String sql = "DELETE FROM barrio WHERE codigo = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            int r =ps.executeUpdate();
            if (r>0){
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
