package VO;

public class ClienteVO {

/*Todo los atributos*/
    String codigo;
    String ruc_ci;
    String nombre;
    String apellido;
    String fechaNac;
    String fechaIng;
    Object sexo;
    String telefono;
    String codSucursal;
    String codProfesion;
    String email;
    String codPais;
    String codDepartamento;
    String codCiudad;
    String codBarrio;
    String nroCasa;
    String codCobrador;
    String codVendedor;
    Object limiteCredito;
    Object estado;
    Object obs;

public ClienteVO(){}

/*Todo los codigos get*/
    public String getCodigo(){
        return codigo;
    }
    public String getRuc_ci(){
        return ruc_ci;
    }
    public String getNombre(){
        return nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public String getFechaNac(){
        return fechaNac;
    }
    public String getFechaIng(){
        return fechaIng;
    }
    public Object getSexo(){
        return sexo;
    }
    public String getTelefono(){
        return telefono;
    }
    public String getCodSucursal(){
        return codSucursal;
    }
    public String getCodProfesion(){
        return codProfesion;
    }
    public String getEmail(){
        return email;
    }
    public String getCodPais(){
        return codPais;
    }
    public String getCodDepartamento(){
        return codDepartamento;
    }
    public String getCodCiudad(){
        return codCiudad;
    }
    public String getCodBarrio(){
        return codBarrio;
    }
    public String getNroCasa(){
        return nroCasa;
    }
    public String getCodCobrador(){
        return codCobrador;
    }
    public String getCodVendedor(){
        return codVendedor;
    }
    public Object getLimiteCredito(){
        return limiteCredito;
    }
    public Object getEstado(){
        return estado;
    }
    public Object getObs(){
        return obs;
    }


/*Todo los codigos set*/
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    public void setRuc_ci(String ruc_ci){
        this.ruc_ci = ruc_ci;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    public void setFechaNac(String fechaNac){
        this.fechaNac = fechaNac;
    }
    public void setFechaIng(String fechaIng){
        this.fechaIng = fechaIng;
    }
    public void setSexo(Object sexo){
        this.sexo = sexo;
    }
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
    public void setCodSucursal(String codSucursal){
        this.codSucursal = codSucursal;
    }
    public void setCodProfesion(String codProfesion){
        this.codProfesion = codProfesion;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setCodPais(String codPais){
        this.codPais = codPais;
    }
    public void setCodDepartamento(String codDepartamento){
        this.codDepartamento = codDepartamento;
    }
    public void setCodCiudad(String codCiudad){
        this.codCiudad = codCiudad;
    }
    public void setCodBarrio(String codBarrio){
        this.codBarrio = codBarrio;
    }
    public void setNroCasa(String nroCasa){
        this.nroCasa = nroCasa;
    }
    public void setCodCobrador(String codCobrador){
        this.codCobrador = codCobrador;
    }
    public void setCodVendedor(String codVendedor){
        this.codVendedor = codVendedor;
    }
    public void setLimiteCredito(Object limiteCredito){
        this.limiteCredito = limiteCredito;
    }
    public void setEstado(Object estado){
        this.estado = estado;
    }
    public void setObs(Object obs){
        this.obs = obs;
    }

}
