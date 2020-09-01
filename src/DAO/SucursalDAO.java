package DAO;

import Conexion.Conectar;
import VO.SucursalVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class SucursalDAO{
    
    public String obtener_id() {
        Conectar cn = null;
        java.sql.Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String id = "";
        String sql = "SELECT IF(COUNT(*) IS NULL,'1',COUNT(*) +1) AS codigo FROM sucursal; ";
        try {
            cn = new Conectar();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getString(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        cn.desconectar();
        return id;

    }

/*Metodo listar*/
    public ArrayList<SucursalVO> Listar_SucursalVO(String codigo){
        ArrayList<SucursalVO> list = new ArrayList<SucursalVO>();
        Conectar conec = new Conectar();
        String sql = "SELECT * FROM sucursal WHERE codigo = ?;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            while(rs.next()){
                SucursalVO vo = new SucursalVO();
                vo.setCodigo(rs.getString(1));
                vo.setRuc(rs.getString(2));
                vo.setSucursal(rs.getString(3));
                vo.setDireccion(rs.getString(4));
                vo.setTelefono(rs.getString(5));
                vo.setCodEmpresa(rs.getString(6));
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
    public void Agregar_SucursalVO(SucursalVO vo){
        Conectar conec = new Conectar();
        String sql = "INSERT INTO sucursal (codigo, ruc, sucursal, direccion, Telefono, codEmpresa) VALUES(?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            ps.setString(2, vo.getRuc());
            ps.setString(3, vo.getSucursal());
            ps.setString(4, vo.getDireccion());
            ps.setString(5, vo.getTelefono());
            ps.setString(6, vo.getCodEmpresa());
            ps.executeUpdate();
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
    public void Modificar_SucursalVO(SucursalVO vo){
        Conectar conec = new Conectar();
        String sql = "UPDATE sucursal SET ruc = ?, sucursal = ?, direccion = ?, Telefono = ?, codEmpresa = ? WHERE codigo = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            ps.setString(2, vo.getRuc());
            ps.setString(3, vo.getSucursal());
            ps.setString(4, vo.getDireccion());
            ps.setString(5, vo.getTelefono());
            ps.setString(6, vo.getCodEmpresa());
            ps.setString(7, vo.getCodigo());
            ps.executeUpdate();
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
    public void Eliminar_SucursalVO(SucursalVO vo){
        Conectar conec = new Conectar();
        String sql = "DELETE FROM sucursal WHERE codigo = ?;";
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
            }catch(SQLException ex){}
        }
    }


}
