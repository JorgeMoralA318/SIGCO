package DAO;

import Conexion.Conectar;
import VO.ZonaVO;
import ds.desktop.notify.DesktopNotify;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ZonaDAO {

    public String obtener_id() {
        Conectar cn = null;
        java.sql.Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String id = "";
        String sql = "SELECT IF(COUNT(*) IS NULL,'1',COUNT(*) +1) AS codigo FROM zona; ";
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
    public ArrayList<ZonaVO> Listar_ZonaVO(String codigo) {
        ArrayList<ZonaVO> list = new ArrayList<ZonaVO>();
        Conectar conec = new Conectar();
        String sql = "SELECT * FROM zona  WHERE codigo = ?;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                ZonaVO vo = new ZonaVO();
                vo.setCodigo(rs.getString(1));
                vo.setZona(rs.getString(2));
                vo.setCodigociudad(rs.getString(3));
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
            } catch (SQLException ex) {
            }
        }
        return list;
    }


    /*Metodo agregar*/
    public void Agregar_ZonaVO(ZonaVO vo) {
        Conectar conec = new Conectar();
        String sql = "INSERT INTO zona (codigo, zona, codCiudad) VALUES(?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            ps.setString(2, vo.getZona());
            ps.setString(3, vo.getCodigociudad());
            int r = ps.executeUpdate();
            if (r > 0) {
                DesktopNotify.showDesktopMessage("Mensaje", "Registrado Exitosamente", 1, 5000);
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
    public void Modificar_ZonaVO(ZonaVO vo) {
        Conectar conec = new Conectar();
        String sql = "UPDATE zona SET zona = ?, codCiudad = ? WHERE codigo = ?;";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);

            ps.setString(1, vo.getZona());
            ps.setString(2, vo.getCodigociudad());
            ps.setString(3, vo.getCodigo());
            int r = ps.executeUpdate();
            if (r > 0) {
                DesktopNotify.showDesktopMessage("Mensaje", "Actualizado Exitosamente", 1, 5000);
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
    public void Eliminar_ZonaVO(ZonaVO vo) {
        Conectar conec = new Conectar();
        String sql = "DELETE FROM zona WHERE codigo = ?;";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            int r= ps.executeUpdate();
           if (r>0){
                DesktopNotify.showDesktopMessage("Mensaje", "Registro eliminado", 1, 5000);
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
