package DAO;

import Conexion.Conectar;
import VO.PersonalVO;
import ds.desktop.notify.DesktopNotify;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class PersonalDAO{
    
    
    //metodo para obtener cidigo por defecto
     public String obtener_id() {
        Conectar cn = null;
        java.sql.Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String id = "";
        String sql = "SELECT IF(COUNT(*) IS NULL,'1',COUNT(*) +1) AS codigo FROM Personal; ";
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
      public ArrayList<PersonalVO> Listar_PersonalVO(String codigo){
        ArrayList<PersonalVO> list = new ArrayList<PersonalVO>();
        Conectar conec = new Conectar();
        String sql = "SELECT * FROM personal WHERE codigo = ?;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1,codigo);
            rs = ps.executeQuery();
            while(rs.next()){
                PersonalVO vo = new PersonalVO();
                vo.setCodigo(rs.getString(1));
                vo.setFechaIngreso(rs.getString(2));
                vo.setCiRuc(rs.getString(3));
                vo.setNombre(rs.getString(4));
                vo.setApellido(rs.getString(5));
                vo.setFechaNac(rs.getString(6));
                vo.setSexo(rs.getObject(7));
                vo.setEstadoCivil(rs.getObject(8));
                vo.setCodPais(rs.getString(9));
                vo.setCodDepartamento(rs.getString(10));
                vo.setCodCiudad(rs.getString(11));
                vo.setCodBarrio(rs.getString(12));
                vo.setCasaNro(rs.getString(13));
                vo.setCodSucursal(rs.getString(14));
                vo.setCodProfesion(rs.getString(15));
                vo.setTelefono(rs.getString(16));
                vo.setEmail(rs.getString(17));
                vo.setReferencia(rs.getString(18));
                vo.setEstado(rs.getObject(19));
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
    public void Agregar_PersonalVO(PersonalVO vo){
        Conectar conec = new Conectar();
        String sql = "INSERT INTO personal (codigo, fechaIngreso, ciRuc, nombre, apellido, fechaNac, sexo, estadoCivil, codPais, codDepartamento, codCiudad, codBarrio, casaNro, codSucursal, codProfesion, telefono, email, referencia) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            ps.setString(2, vo.getFechaIngreso());
            ps.setString(3, vo.getCiRuc());
            ps.setString(4, vo.getNombre());
            ps.setString(5, vo.getApellido());
            ps.setString(6, vo.getFechaNac());
            ps.setObject(7, vo.getSexo());
            ps.setObject(8, vo.getEstadoCivil());
            ps.setString(9, vo.getCodPais());
            ps.setString(10, vo.getCodDepartamento());
            ps.setString(11, vo.getCodCiudad());
            ps.setString(12, vo.getCodBarrio());
            ps.setString(13, vo.getCasaNro());
            ps.setString(14, vo.getCodSucursal());
            ps.setString(15, vo.getCodProfesion());
            ps.setString(16, vo.getTelefono());
            ps.setString(17, vo.getEmail());
            ps.setString(18, vo.getReferencia());
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
            }catch(SQLException ex){
            System.out.println(ex);}
        }
    }


/*Metodo Modificar*/
    public void Modificar_PersonalVO(PersonalVO vo){
        Conectar conec = new Conectar();
        String sql = "UPDATE personal SET fechaIngreso = ?, ciRuc = ?, nombre = ?, apellido = ?, fechaNac = ?, sexo = ?, estadoCivil = ?, codPais = ?, codDepartamento = ?, codCiudad = ?, codBarrio = ?, casaNro = ?, codSucursal = ?, codProfesion = ?, telefono = ?, email = ?, referencia = ? WHERE codigo = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
           
            ps.setString(1, vo.getFechaIngreso());
            ps.setString(2, vo.getCiRuc());
            ps.setString(3, vo.getNombre());
            ps.setString(4, vo.getApellido());
            ps.setString(5, vo.getFechaNac());
            ps.setObject(6, vo.getSexo());
            ps.setObject(7, vo.getEstadoCivil());
            ps.setString(8, vo.getCodPais());
            ps.setString(9, vo.getCodDepartamento());
            ps.setString(10, vo.getCodCiudad());
            ps.setString(11, vo.getCodBarrio());
            ps.setString(12, vo.getCasaNro());
            ps.setString(13, vo.getCodSucursal());
            ps.setString(14, vo.getCodProfesion());
            ps.setString(15, vo.getTelefono());
            ps.setString(16, vo.getEmail());
            ps.setString(17, vo.getReferencia());
            ps.setString(18, vo.getCodigo());
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
            }catch(SQLException ex){
                System.out.println(ex);}
        }
    }


/*Metodo Eliminar*/
    public void Eliminar_PersonalVO(PersonalVO vo){
        Conectar conec = new Conectar();
        String sql = "DELETE FROM personal WHERE codigo = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            int r = ps.executeUpdate();
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
            }catch(SQLException ex){System.out.println(ex);}
        }
    }


}
