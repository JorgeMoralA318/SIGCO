package VO;

public class SucursalVO {

/*Todo los atributos*/
    String codigo;
    String ruc;
    String sucursal;
    String direccion;
    String Telefono;
    String codEmpresa;

public SucursalVO(){}

/*Todo los codigos get*/
    public String getCodigo(){
        return codigo;
    }
    public String getRuc(){
        return ruc;
    }
    public String getSucursal(){
        return sucursal;
    }
    public String getDireccion(){
        return direccion;
    }
    public String getTelefono(){
        return Telefono;
    }
    public String getCodEmpresa(){
        return codEmpresa;
    }


/*Todo los codigos set*/
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    public void setRuc(String ruc){
        this.ruc = ruc;
    }
    public void setSucursal(String sucursal){
        this.sucursal = sucursal;
    }
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    public void setTelefono(String Telefono){
        this.Telefono = Telefono;
    }
    public void setCodEmpresa(String codEmpresa){
        this.codEmpresa = codEmpresa;
    }

}
