package VO;

public class ReferenciaVO {

/*Todo los atributos*/
    int codigo;
    String codCliente;
    String ci_ruc;
    String nombre;
    String direccion;
    String contacto;
    Object obs;
    Object tipoRef;

public ReferenciaVO(){}

/*Todo los codigos get*/
    public int getCodigo(){
        return codigo;
    }
    public String getCodCliente(){
        return codCliente;
    }
    public String getCi_ruc(){
        return ci_ruc;
    }
    public String getNombre(){
        return nombre;
    }
    public String getDireccion(){
        return direccion;
    }
    public String getContacto(){
        return contacto;
    }
    public Object getObs(){
        return obs;
    }
    public Object getTipoRef(){
        return tipoRef;
    }


/*Todo los codigos set*/
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
    public void setCodCliente(String codCliente){
        this.codCliente = codCliente;
    }
    public void setCi_ruc(String ci_ruc){
        this.ci_ruc = ci_ruc;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    public void setContacto(String contacto){
        this.contacto = contacto;
    }
    public void setObs(Object obs){
        this.obs = obs;
    }
    public void setTipoRef(Object tipoRef){
        this.tipoRef = tipoRef;
    }

}
