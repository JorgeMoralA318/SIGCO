package DAO;

import Conexion.Conectar;
import VO.ClienteVO;
import ds.desktop.notify.DesktopNotify;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class ClienteDAO{
    
    
        public String obtener_id() {
        Conectar cn = null;
        java.sql.Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String id = "";
        String sql = "SELECT IF(COUNT(*) IS NULL,'1',COUNT(*) +1) AS codigo FROM cliente; ";
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
    public ArrayList<ClienteVO> Listar_ClienteVO(String codigo){
        ArrayList<ClienteVO> list = new ArrayList<ClienteVO>();
        Conectar conec = new Conectar();
        String sql = "SELECT * FROM cliente WHERE codigo = ?;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            while(rs.next()){
                ClienteVO vo = new ClienteVO();
                vo.setCodigo(rs.getString(1));
                vo.setRuc_ci(rs.getString(2));
                vo.setNombre(rs.getString(3));
                vo.setApellido(rs.getString(4));
                vo.setFechaNac(rs.getString(5));
                vo.setFechaIng(rs.getString(6));
                vo.setSexo(rs.getObject(7));
                vo.setTelefono(rs.getString(8));
                vo.setCodSucursal(rs.getString(9));
                vo.setCodProfesion(rs.getString(10));
                vo.setEmail(rs.getString(11));
                vo.setCodPais(rs.getString(12));
                vo.setCodDepartamento(rs.getString(13));
                vo.setCodCiudad(rs.getString(14));
                vo.setCodBarrio(rs.getString(15));
                vo.setNroCasa(rs.getString(16));
                vo.setCodCobrador(rs.getString(17));
                vo.setCodVendedor(rs.getString(18));
                vo.setLimiteCredito(rs.getObject(19));
                vo.setEstado(rs.getObject(20));
                vo.setObs(rs.getObject(21));
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
    public void Agregar_ClienteVO(ClienteVO vo){
        Conectar conec = new Conectar();
        String sql = "INSERT INTO cliente (codigo, ruc_ci, nombre, apellido, fechaNac, sexo, telefono, codSucursal, codProfesion, email, codPais, codDepartamento, codCiudad, codBarrio, nroCasa, codCobrador, codVendedor, limiteCredito, estado, obs) VALUES(?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            ps.setString(2, vo.getRuc_ci());
            ps.setString(3, vo.getNombre());
            ps.setString(4, vo.getApellido());
            ps.setString(5, vo.getFechaNac());
            ps.setObject(6, vo.getSexo());
            ps.setString(7, vo.getTelefono());
            ps.setString(8, vo.getCodSucursal());
            ps.setString(9, vo.getCodProfesion());
            ps.setString(10, vo.getEmail());
            ps.setString(11, vo.getCodPais());
            ps.setString(12, vo.getCodDepartamento());
            ps.setString(13, vo.getCodCiudad());
            ps.setString(14, vo.getCodBarrio());
            ps.setString(15, vo.getNroCasa());
            ps.setString(16, vo.getCodCobrador());
            ps.setString(17, vo.getCodVendedor());
            ps.setObject(18, vo.getLimiteCredito());
            ps.setObject(19, vo.getEstado());
            ps.setObject(20, vo.getObs());
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
    public void Modificar_ClienteVO(ClienteVO vo){
        Conectar conec = new Conectar();
        String sql = "UPDATE cliente SET ruc_ci = ?, nombre = ?, apellido = ?, fechaNac = ?, sexo = ?, telefono = ?, codSucursal = ?, codProfesion = ?, email = ?, codPais = ?, codDepartamento = ?, codCiudad = ?, codBarrio = ?, nroCasa = ?, codCobrador = ?, codVendedor = ?, limiteCredito = ?, estado = ?, obs = ? WHERE codigo = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            
            ps.setString(1, vo.getRuc_ci());
            ps.setString(2, vo.getNombre());
            ps.setString(3, vo.getApellido());
            ps.setString(4, vo.getFechaNac());
            ps.setObject(5, vo.getSexo());
            ps.setString(6, vo.getTelefono());
            ps.setString(7, vo.getCodSucursal());
            ps.setString(8, vo.getCodProfesion());
            ps.setString(9, vo.getEmail());
            ps.setString(10, vo.getCodPais());
            ps.setString(11, vo.getCodDepartamento());
            ps.setString(12, vo.getCodCiudad());
            ps.setString(13, vo.getCodBarrio());
            ps.setString(14, vo.getNroCasa());
            ps.setString(15, vo.getCodCobrador());
            ps.setString(16, vo.getCodVendedor());
            ps.setObject(17, vo.getLimiteCredito());
            ps.setObject(18, vo.getEstado());
            ps.setObject(19, vo.getObs());
            ps.setString(20, vo.getCodigo());
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
    public void Eliminar_ClienteVO(ClienteVO vo){
        Conectar conec = new Conectar();
        String sql = "DELETE FROM cliente WHERE codigo = ?;";
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
