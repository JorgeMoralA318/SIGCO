package VO;

public class UsuarioVO {

/*Todo los atributos*/
    String codigo;
    String codPersonal;
    String codRol;
    String Login;
    String Clave;

public UsuarioVO(){}

/*Todo los codigos get*/
    public String getCodigo(){
        return codigo;
    }
    public String getCodPersonal(){
        return codPersonal;
    }
    public String getCodRol(){
        return codRol;
    }
    public String getLogin(){
        return Login;
    }
    public String getClave(){
        return Clave;
    }


/*Todo los codigos set*/
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    public void setCodPersonal(String codPersonal){
        this.codPersonal = codPersonal;
    }
    public void setCodRol(String codRol){
        this.codRol = codRol;
    }
    public void setLogin(String Login){
        this.Login = Login;
    }
    public void setClave(String Clave){
        this.Clave = Clave;
    }

}
