package DAO;

import Conexion.Conectar;
import VO.ProfesionVO;
import ds.desktop.notify.DesktopNotify;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProfesionDAO {

    //Obtener codigo por defecto
    public String obtener_id() {
        Conectar cn = null;
        java.sql.Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String id = "";
        String sql = "SELECT IF(COUNT(*) IS NULL,'1',COUNT(*) +1) AS codigo FROM Profesion; ";
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
    public ArrayList<ProfesionVO> Listar_ProfesionVO(String codigo) {
        ArrayList<ProfesionVO> list = new ArrayList<ProfesionVO>();
        Conectar conec = new Conectar();
        String sql = "SELECT * FROM profesion WHERE codigo = ?;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProfesionVO vo = new ProfesionVO();
                vo.setCodigo(rs.getString(1));
                vo.setProfesion(rs.getString(2));
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
    public void Agregar_ProfesionVO(ProfesionVO vo) {
        Conectar conec = new Conectar();
        String sql = "INSERT INTO profesion (codigo, Profesion) VALUES(?, ?);";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            ps.setString(2, vo.getProfesion());
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
    public void Modificar_ProfesionVO(ProfesionVO vo) {
        Conectar conec = new Conectar();
        String sql = "UPDATE profesion SET Profesion = ? WHERE codigo = ?;";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);

            ps.setString(1, vo.getProfesion());
            ps.setString(2, vo.getCodigo());
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
    public void Eliminar_ProfesionVO(ProfesionVO vo) {
        Conectar conec = new Conectar();
        String sql = "DELETE FROM profesion WHERE codigo = ?;";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodigo());
            int r = ps.executeUpdate();
            if (r > 0) {
                DesktopNotify.showDesktopMessage("Mensaje", " Registro Eliminado", 1, 5000);
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
