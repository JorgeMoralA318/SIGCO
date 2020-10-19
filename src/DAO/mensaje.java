/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javax.swing.JOptionPane;

/**
 *
 * @author Helena
 */
public class mensaje {

    String add = "Registro Guardado";
    String update = "Registro Actualizado";
    String delete = "Registro Eliminado";
    String titulo = "Sigco";

    public void guardar() {
        JOptionPane.showMessageDialog(null, this.add, this.titulo, 2);
    }

    public void actualizar() {
        JOptionPane.showMessageDialog(null, this.update, this.titulo, 2);
    }

    public void eliminar() {
        JOptionPane.showMessageDialog(null, this.delete, this.titulo, 2);
    }

}
