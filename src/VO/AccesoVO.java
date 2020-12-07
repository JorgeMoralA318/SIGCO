package VO;

public class AccesoVO {

/*Todo los atributos*/
    int idAcceso;
    String codRol;
    String codModulo;
    String codVentana;
    int acceso;
    int nuevo;
    int actualizar;
    int borrar;

public AccesoVO(){}

/*Todo los codigos get*/
    public int getIdAcceso(){
        return idAcceso;
    }
    public String getCodRol(){
        return codRol;
    }
    public String getCodModulo(){
        return codModulo;
    }
    public String getCodVentana(){
        return codVentana;
    }
    public int getAcceso(){
        return acceso;
    }
    public int getNuevo(){
        return nuevo;
    }
    public int getActualizar(){
        return actualizar;
    }
    public int getBorrar(){
        return borrar;
    }


/*Todo los codigos set*/
    public void setIdAcceso(int idAcceso){
        this.idAcceso = idAcceso;
    }
    public void setCodRol(String codRol){
        this.codRol = codRol;
    }
    public void setCodModulo(String codModulo){
        this.codModulo = codModulo;
    }
    public void setCodVentana(String codVentana){
        this.codVentana = codVentana;
    }
    public void setAcceso(int acceso){
        this.acceso = acceso;
    }
    public void setNuevo(int nuevo){
        this.nuevo = nuevo;
    }
    public void setActualizar(int actualizar){
        this.actualizar = actualizar;
    }
    public void setBorrar(int borrar){
        this.borrar = borrar;
    }

}
