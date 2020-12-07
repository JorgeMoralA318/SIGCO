package DAO;

import Conexion.Conectar;
import VO.AccesoVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AccesoDAO {
    
    //metodo obtener acceso
    public String obtener_acceso(String codigo_ventana, String codigo_rol) {
        Conectar cn = null;
        java.sql.Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String id = "";
        String sql = "SELECT acceso FROM acceso WHERE codVentana= ? AND codRol = ?; ";
        try {
            cn = new Conectar();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, codigo_ventana);
            ps.setString(2, codigo_rol);
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
    public ArrayList<AccesoVO> Listar_AccesoVO() {
        ArrayList<AccesoVO> list = new ArrayList<AccesoVO>();
        Conectar conec = new Conectar();
        String sql = "SELECT * FROM acceso;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                AccesoVO vo = new AccesoVO();
                vo.setIdAcceso(rs.getInt(1));
                vo.setCodRol(rs.getString(2));
                vo.setCodModulo(rs.getString(3));
                vo.setCodVentana(rs.getString(4));
                vo.setAcceso(rs.getInt(5));
                vo.setNuevo(rs.getInt(6));
                vo.setActualizar(rs.getInt(7));
                vo.setBorrar(rs.getInt(8));
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
    public void Agregar_AccesoVO(AccesoVO vo) {
        Conectar conec = new Conectar();
        String sql = "INSERT INTO acceso (codRol, codModulo, codVentana) VALUES(?, ?, ?);";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);

            ps.setString(1, vo.getCodRol());
            ps.setString(2, vo.getCodModulo());
            ps.setString(3, vo.getCodVentana());
            ps.executeUpdate();
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

    public void insertarAcceso(String codRol) {
        Conectar conec = new Conectar();
        PreparedStatement ps;

        String sql = "SELECT codModulo,codigo FROM sigcodb.ventana;";

        try {
            ps = conec.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String codRole = codRol;
                String codModulo = rs.getString(1);
                String codVentana = rs.getString(2);

                AccesoVO vo = new AccesoVO();
                vo.setCodRol(codRole);
                vo.setCodModulo(codModulo);
                vo.setCodVentana(codVentana);
                Agregar_AccesoVO(vo);

            }

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
        }

    }


    /*Metodo Modificar*/
    public void Modificar_AccesoVO(int id, String campo, int valor) {
        Conectar conec = new Conectar();
        String sql = "UPDATE acceso SET " + campo + "=" + valor + " WHERE idAcceso =" + id + ";";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            int r = ps.executeUpdate();
            if (r > 0) {
              //  System.out.println("actualizado");
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

    public void Modificar_porRol(String campo, int valor, String codRol) {
        Conectar conec = new Conectar();
        String sql = "UPDATE acceso SET " + campo + "=" + valor + " WHERE codRol =" + codRol + ";";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            int r = ps.executeUpdate();
            if (r > 0) {
             //   System.out.println("actualizado");
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

    public void Modificar_porRol_modulo(String campo, int valor, String codRol, String codModulo) {
        Conectar conec = new Conectar();
        String sql = "UPDATE acceso SET " + campo + "=" + valor + " WHERE (codRol =" + codRol + ")AND (codModulo=" + codModulo + ");";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            int r = ps.executeUpdate();
            if (r > 0) {
                //System.out.println("actualizado");
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
    public void Eliminar_AccesoVO(AccesoVO vo) {
        Conectar conec = new Conectar();
        String sql = "DELETE FROM acceso WHERE codRol = ?;";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getCodRol());
            ps.executeUpdate();
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
