package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectar{

    static String bd = "sigcodb";
    static String login = "root";
    static String password = "12345";
    static  String url = "jdbc:mysql://localhost/"+bd;
    Connection connection = null;

    public Conectar(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url,login,password);
        if (connection!=null){
            System.out.println("Conexion Establecida a la base de datos : "+bd+" OK\n");
        }
        }catch(SQLException | ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public void desconectar(){
        connection = null;
    }


}
