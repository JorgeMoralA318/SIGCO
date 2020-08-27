package VO;

public class CiudadVO {

/*Todo los atributos*/
    String codigo;
    String ciudad;
    String codDepartamento;

public CiudadVO(){}

/*Todo los codigos get*/
    public String getCodigo(){
        return codigo;
    }
    public String getCiudad(){
        return ciudad;
    }
    public String getCodDepartamento(){
        return codDepartamento;
    }


/*Todo los codigos set*/
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    public void setCiudad(String ciudad){
        this.ciudad = ciudad;
    }
    public void setCodDepartamento(String codDepartamento){
        this.codDepartamento = codDepartamento;
    }

}
