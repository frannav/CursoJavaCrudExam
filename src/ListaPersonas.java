//Curso java telefonica @frannav

public class ListaPersonas {
    
    //Atributos
    private int identificacion;
    private String Nombre;
    
    //Constructor
    public ListaPersonas(int Numero, String Nombrecillo){
        this.identificacion = Numero;
        this.Nombre  = Nombrecillo;
    }
 
    //Metodos
    public int getIdentificador() {
        return identificacion;
    }
 
    public String getNombre() {
        return Nombre;
    }
    
    public void setNombre(String varTexto) {
        this.Nombre = varTexto;        
    }

    public void setIdentificador(int varEntera) {
        this.identificacion = varEntera;
    }
}