package VO;

public class Cargo_salarioVO {

/*Todo los atributos*/
    String codigo;
    String codCargo;
    String codPersonal;
    String fecha_alta;
    Object salario;
    String fecha_baja;
    String codMotivo;
    int comVenta;
    int comCobro;
    Object estado;

public Cargo_salarioVO(){}

/*Todo los codigos get*/
    public String getCodigo(){
        return codigo;
    }
    public String getCodCargo(){
        return codCargo;
    }
    public String getCodPersonal(){
        return codPersonal;
    }
    public String getFecha_alta(){
        return fecha_alta;
    }
    public Object getSalario(){
        return salario;
    }
    public String getFecha_baja(){
        return fecha_baja;
    }
    public String getCodMotivo(){
        return codMotivo;
    }
    public int getComVenta(){
        return comVenta;
    }
    public int getComCobro(){
        return comCobro;
    }
    public Object getEstado(){
        return estado;
    }


/*Todo los codigos set*/
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    public void setCodCargo(String codCargo){
        this.codCargo = codCargo;
    }
    public void setCodPersonal(String codPersonal){
        this.codPersonal = codPersonal;
    }
    public void setFecha_alta(String fecha_alta){
        this.fecha_alta = fecha_alta;
    }
    public void setSalario(Object salario){
        this.salario = salario;
    }
    public void setFecha_baja(String fecha_baja){
        this.fecha_baja = fecha_baja;
    }
    public void setCodMotivo(String codMotivo){
        this.codMotivo = codMotivo;
    }
    public void setComVenta(int comVenta){
        this.comVenta = comVenta;
    }
    public void setComCobro(int comCobro){
        this.comCobro = comCobro;
    }
    public void setEstado(Object estado){
        this.estado = estado;
    }

}
