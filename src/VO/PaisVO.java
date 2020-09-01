package VO;

public class PaisVO {

/*Todo los atributos*/
    String Codigo;
    String Pais;

public PaisVO(){}

/*Todo los codigos get*/
    public String getCodigo(){
        return Codigo;
    }
    public String getPais(){
        return Pais;
    }


/*Todo los codigos set*/
    public void setCodigo(String Codigo){
        this.Codigo = Codigo;
    }
    public void setPais(String Pais){
        this.Pais = Pais;
    }

}
