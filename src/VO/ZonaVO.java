package VO;

public class ZonaVO {

/*Todo los atributos*/
    String codigo;
    String zona;
    String codigociudad;

public ZonaVO(){}

/*Todo los codigos get*/
    public String getCodigo(){
        return codigo;
    }
    public String getZona(){
        return zona;
    }
    public String getCodigociudad(){
        return codigociudad;
    }


/*Todo los codigos set*/
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    public void setZona(String zona){
        this.zona = zona;
    }
    public void setCodigociudad(String codigociudad){
        this.codigociudad = codigociudad;
    }

}
