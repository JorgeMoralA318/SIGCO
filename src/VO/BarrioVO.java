package VO;

public class BarrioVO {

/*Todo los atributos*/
    String codigo;
    String barrio;
    String codciudad;

public BarrioVO(){}

/*Todo los codigos get*/
    public String getCodigo(){
        return codigo;
    }
    public String getBarrio(){
        return barrio;
    }
    public String getCodciudad(){
        return codciudad;
    }


/*Todo los codigos set*/
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    public void setBarrio(String barrio){
        this.barrio = barrio;
    }
    public void setCodciudad(String codciudad){
        this.codciudad = codciudad;
    }

}
