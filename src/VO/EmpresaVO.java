package VO;

public class EmpresaVO {

/*Todo los atributos*/
    String codigo;
    String empresa;

public EmpresaVO(){}

/*Todo los codigos get*/
    public String getCodigo(){
        return codigo;
    }
    public String getEmpresa(){
        return empresa;
    }


/*Todo los codigos set*/
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    public void setEmpresa(String empresa){
        this.empresa = empresa;
    }

}
