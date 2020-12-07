package DAO;

import Conexion.Conectar;
import VO.ReferenciaVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReferenciaDAO {

    mensaje aviso = new mensaje();

    /*Metodo listar*/
    public ArrayList<ReferenciaVO> Listar_ReferenciaVO() {
        ArrayList<ReferenciaVO> list = new ArrayList<ReferenciaVO>();
        Conectar conec = new Conectar();
        String sql = "SELECT * FROM referencia;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ReferenciaVO vo = new ReferenciaVO();
                vo.setCodigo(rs.getInt(1));
                vo.setCodCliente(rs.getString(2));
                vo.setCi_ruc(rs.getString(3));
                vo.setNombre(rs.getString(4));
                vo.setDireccion(rs.getString(5));
                vo.setContacto(rs.getString(6));
                vo.setObs(rs.getObject(7));
                vo.setTipoRef(rs.getObject(8));
                list.add(vo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
        return list;
    }


    /*Metodo agregar*/
    public void Agregar_ReferenciaVO(ReferenciaVO vo) {
        Conectar conec = new Conectar();
        String sql = "INSERT INTO referencia ( codCliente, ci_ruc, nombre, direccion, contacto, obs, tipoRef) VALUES(?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);

            ps.setString(1, vo.getCodCliente());
            ps.setString(2, vo.getCi_ruc());
            ps.setString(3, vo.getNombre());
            ps.setString(4, vo.getDireccion());
            ps.setString(5, vo.getContacto());
            ps.setObject(6, vo.getObs());
            ps.setObject(7, vo.getTipoRef());
            int r = ps.executeUpdate();
            if (r > 0) {
                aviso.guardar();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                conec.desconectar();
            } catch (SQLException ex) {
            }
        }
    }


    /*Metodo Modificar*/
    public void Modificar_ReferenciaVO(ReferenciaVO vo) {
        Conectar conec = new Conectar();
        String sql = "UPDATE referencia SET codCliente = ?, ci_ruc = ?, nombre = ?, direccion = ?, contacto = ?, obs = ?, tipoRef = ? WHERE codigo = ?;";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);

            ps.setString(1, vo.getCodCliente());
            ps.setString(2, vo.getCi_ruc());
            ps.setString(3, vo.getNombre());
            ps.setString(4, vo.getDireccion());
            ps.setString(5, vo.getContacto());
            ps.setObject(6, vo.getObs());
            ps.setObject(7, vo.getTipoRef());
            ps.setInt(8, vo.getCodigo());
            int r =ps.executeUpdate();
            if (r>0){
            aviso.actualizar();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                conec.desconectar();
            } catch (SQLException ex) {
            }
        }
    }


    /*Metodo Eliminar*/
    public void Eliminar_ReferenciaVO(ReferenciaVO vo) {
        Conectar conec = new Conectar();
        String sql = "DELETE FROM referencia WHERE codigo = ?;";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setInt(1, vo.getCodigo());
            int r =ps.executeUpdate();
            if(r>0){
                aviso.eliminar();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                conec.desconectar();
            } catch (SQLException ex) {
            }
        }
    }

}
