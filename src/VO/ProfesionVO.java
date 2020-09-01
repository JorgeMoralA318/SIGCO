package VO;

public class ProfesionVO {

/*Todo los atributos*/
    String codigo;
    String Profesion;

public ProfesionVO(){}

/*Todo los codigos get*/
    public String getCodigo(){
        return codigo;
    }
    public String getProfesion(){
        return Profesion;
    }


/*Todo los codigos set*/
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    public void setProfesion(String Profesion){
        this.Profesion = Profesion;
    }

}
