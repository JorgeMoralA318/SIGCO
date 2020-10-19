package DAO;

import Conexion.Conectar;
import VO.ClienteVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class ClienteDAO{
    
    mensaje aviso = new mensaje();
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
            ps.setString(1,codigo);
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
                vo.setEstadoCivil(rs.getObject(8));
                vo.setTelefono(rs.getString(9));
                vo.setCodSucursal(rs.getString(10));
                vo.setCodProfesion(rs.getString(11));
                vo.setEmail(rs.getString(12));
                vo.setCodPais(rs.getString(13));
                vo.setCodDepartamento(rs.getString(14));
                vo.setCodCiudad(rs.getString(15));
                vo.setCodBarrio(rs.getString(16));
                vo.setNroCasa(rs.getString(17));
                vo.setCodCobrador(rs.getString(18));
                vo.setCodVendedor(rs.getString(19));
                vo.setLimiteCredito(rs.getObject(20));
                vo.setIsdeuda(rs.getInt(21));
                vo.setIsSolicitud(rs.getInt(22));
                vo.setObs(rs.getObject(23));
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
            }catch(Exception ex){}
        }
        return list;
    }

/*Metodo agregar*/
    public void Agregar_ClienteVO(ClienteVO vo){
        Conectar conec = new Conectar();
        String sql = "INSERT INTO cliente (codigo, ruc_ci, nombre, apellido,"+
                " fechaNac, sexo,estadoCivil ,telefono, codSucursal, codProfesion,"+
                " email, codPais, codDepartamento, codCiudad, codBarrio, nroCasa, codCobrador, codVendedor, limiteCredito,  obs) VALUES(?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            ps.setString(2, vo.getRuc_ci());
            ps.setString(3, vo.getNombre());
            ps.setString(4, vo.getApellido());
            ps.setString(5, vo.getFechaNac());
            ps.setObject(6, vo.getSexo());
            ps.setObject(7 , vo.getEstadoCivil());
            ps.setString(8, vo.getTelefono());
            ps.setString(9, vo.getCodSucursal());
            ps.setString(10, vo.getCodProfesion());
            ps.setString(11, vo.getEmail());
            ps.setString(12, vo.getCodPais());
            ps.setString(13, vo.getCodDepartamento());
            ps.setString(14, vo.getCodCiudad());
            ps.setString(15, vo.getCodBarrio());
            ps.setString(16, vo.getNroCasa());
            ps.setString(17, vo.getCodCobrador());
            ps.setString(18, vo.getCodVendedor());
            ps.setObject(19, vo.getLimiteCredito());
            ps.setObject(20, vo.getObs());
            int r = ps.executeUpdate();
            if (r > 0) {
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
    public void Modificar_ClienteVO(ClienteVO vo){
        Conectar conec = new Conectar();
        String sql = "UPDATE cliente SET ruc_ci = ?, nombre = ?, apellido = ?, fechaNac = ?, sexo = ?,estadoCivil=?, telefono = ?, codSucursal = ?, codProfesion = ?, email = ?, codPais = ?, codDepartamento = ?, codCiudad = ?, codBarrio = ?, nroCasa = ?, codCobrador = ?, codVendedor = ?, limiteCredito = ?, obs = ? WHERE codigo = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            
            ps.setString(1, vo.getRuc_ci());
            ps.setString(2, vo.getNombre());
            ps.setString(3, vo.getApellido());
            ps.setString(4, vo.getFechaNac());
            ps.setObject(5, vo.getSexo());
            ps.setObject(6, vo.getEstadoCivil());
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
            ps.setObject(19, vo.getObs());
            ps.setString(20, vo.getCodigo());
            int r = ps.executeUpdate();
            if (r > 0) {
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
    public void Eliminar_ClienteVO(ClienteVO vo){
        Conectar conec = new Conectar();
        String sql = "DELETE FROM cliente WHERE codigo = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            int r = ps.executeUpdate();
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
