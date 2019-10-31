//Curso java telefonica @frannav

import javax.swing.JOptionPane;


public class Usuario {

    //Atributos
    private String nombreUsuario = "admin";
    private String contraseniaPorDefecto = "admin";
    private String[] listacontrasenia = {"caca","4321","1234"};
    //Constructor
    public Usuario() {
    }

    //Métodos
    public String getNombreUsuario() {
        return this.nombreUsuario;
    }

    public String getContraseniaPorDefecto() {
        return this.contraseniaPorDefecto;
    }

    public void setNombreUsuario(String nombreCualquiera) {
        this.nombreUsuario = nombreCualquiera;
    }

    public void setContraseniaPorDefecto(String contraseniaCualquiera) {
        this.contraseniaPorDefecto = contraseniaCualquiera;
    }

    public boolean validacionUsuario() {
        try {
            String NombreLog = JOptionPane.showInputDialog(null, "Introduce el nombre de usuario:", "Log In", JOptionPane.WARNING_MESSAGE);
            String ContraLog = JOptionPane.showInputDialog(null, "Introduce la contraseña:", "Log In", JOptionPane.WARNING_MESSAGE);
            if (NombreLog.equals(nombreUsuario) && ContraLog.equals(listacontrasenia[0]) || NombreLog.equals(nombreUsuario) && ContraLog.equals(listacontrasenia[1]) || NombreLog.equals(nombreUsuario) && ContraLog.equals(listacontrasenia[2]) || NombreLog.equals(nombreUsuario) && ContraLog.equals(contraseniaPorDefecto) ) {
                return true;
            }
        } catch (NullPointerException e1){
            JOptionPane.showMessageDialog(null, "¡Ha dejado vacío algún campo!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
}