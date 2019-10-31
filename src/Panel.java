//Curso java telefonica @frannav

import java.util.ArrayList;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;

public class Panel extends Usuario {

    //Atributos
    private boolean listaCreada = false;
    private ArrayList <ListaPersonas> listaDinamica = null;
    private int opcion;
    private String historico = "";                            //Aquí guardaré las acciones realizadas por el usuario

    //Constructor
    public Panel() {
        super();
    }

    //Metodos
    public void menu() {
        if (validacionUsuario()) {
            do {
                try {
                    this.opcion =  Integer.parseInt(JOptionPane.showInputDialog(null," 1. Crear ArrayList \n 2. Introducir dato \n 3. Modificar dato \n 4. Eliminar dato \n 5. Buscar dato \n 6. Mostrar datos \n 7. Clonar dato \n 8. Buscar datos duplicados \n 9. Recuento de datos \n 10. Mostrar Pares e Impares \n 11. Salir  \n \n \n 12. Mostrar Historico de acciones sobre el menú " , "Menú", JOptionPane.INFORMATION_MESSAGE));
                    switch (this.opcion) {
                        case 1:
                            if (!this.listaCreada) {
                                crearArray();
                            } else {
                                JOptionPane.showMessageDialog(null,"El array ya ha sido creado previamente.","Información",JOptionPane.INFORMATION_MESSAGE);
                            }
                            historico += "1 Crear ArrayList \n";
                            break;
                        case 11:
                            System.exit(0);
                            break;
                        case 12:
                            mostrarHistorico();
                            historico += "12 Mostrar histórico \n";
                            break;
                        default:
                            compruebaSiListaExiste(this.opcion);
                            break;
                    }
                } catch (NumberFormatException e6){
                    JOptionPane.showMessageDialog(null,"Has introducido un valor en un campo que no le correspondía.","Campo inválido", JOptionPane.ERROR_MESSAGE);
                }
                
            } while (this.opcion != 11);
        } else {
            JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos.","Log In", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void compruebaSiListaExiste(int valorOpcion) {
        if (!this.listaCreada) {
            JOptionPane.showMessageDialog(null,"Crea el array list usando la opción 1.","Información",JOptionPane.INFORMATION_MESSAGE);
        } else {
            switch(valorOpcion) {
                case 2:
                    introducirDato();

                    break;
                case 3:
                    modificarDato();

                    break;
                case 4:
                    if (eliminarDato()) {
                        JOptionPane.showMessageDialog(null,"El dato seleccionado se ha eliminado exitosamente.","Información",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,"No se ha encontrado el dato seleccionado.","Información",JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                case 5:
                    if (!buscarDato()) {
                        JOptionPane.showMessageDialog(null,"¡No se ha encontrado nada!","Información",JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                case 6:
                    mostrarDatos();
                    historico += "6 Dato mostrado en  ArrayList \n";
                    break;
                case 7:
                    if (clonarContacto()) {
                        JOptionPane.showMessageDialog(null,"El dato seleccionado ha sido clonado.","Información",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,"El dato seleccionado no se ha encontrado.","Información",JOptionPane.INFORMATION_MESSAGE);
                    }

                    break;
                case 8:
                    buscarDuplicados();
                    historico += "8 Has buscado un duplicado en  ArrayList \n";
                    break;
                case 9:
                    recuentoDatos();
                    historico += "9 Recuento de datos \n";
                    break;
                case 10:
                    mostrarPares();
                    historico += "10 Mostrar números pares e impares \n";
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"¡No hay tantas opciones!","Información",JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
        }
    }

    private void crearArray() {
            this.listaDinamica = new ArrayList <ListaPersonas> ();
            this.listaCreada = true;
            JOptionPane.showMessageDialog(null,"¡OK! El array list se ha creado satisfactoriamente.","Información",JOptionPane.INFORMATION_MESSAGE);
    }

    private void introducirDato() {
        try {
            int identificador = Integer.parseInt(JOptionPane.showInputDialog(null,"Añade el número:","IDENTIFICADOR",JOptionPane.PLAIN_MESSAGE));
            if (!comprobarSiDatoExiste(identificador,"")) {
                String nombrete = JOptionPane.showInputDialog(null,"Escribe el nombre correspondiente:","NOMBRE",JOptionPane.PLAIN_MESSAGE);
                this.listaDinamica.add(new ListaPersonas(identificador, nombrete));
                historico += "2 Introducir el dato: " + nombrete + " en la lista  \n";
            } else {
                JOptionPane.showMessageDialog(null, "Este identificador ya existe","Información",JOptionPane.INFORMATION_MESSAGE);
            }             
        } catch (InputMismatchException e1) {
            JOptionPane.showMessageDialog(null,"Has introducido algún valor en un campo que no le correspondía.","Error",JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e2) {
            JOptionPane.showMessageDialog(null,"Has introducido un valor en un campo que no le correspondía.","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void modificarDato() {
        try {
            String[] DosOpciones = {"Nombre", "Identificador"};
            int seleccion = JOptionPane.showOptionDialog(null, "¿Quieres modificar un nombre o un identificador?","MODIFICAR DATO", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, DosOpciones, null);
            if (seleccion == 0) {
                String NombreParaBuscar = JOptionPane.showInputDialog(null,"Escriba el nombre que quiere modificar:","NOMBRE",JOptionPane.PLAIN_MESSAGE);
                if (comprobarSiDatoExiste(0, NombreParaBuscar)) {                            
                    int indice = obtenerIndice(0, NombreParaBuscar);
                    String nuevoNombrete = JOptionPane.showInputDialog(null,"Ingrese el nuevo nombre:","NOMBRE",JOptionPane.PLAIN_MESSAGE);
                    this.listaDinamica.get(indice).setNombre(nuevoNombrete);
                    historico += "3 Dato modificado :" + NombreParaBuscar + " y cambiado por : " + nuevoNombrete;
                } else {
                    JOptionPane.showMessageDialog(null,"¡El dato a modificar no existe!");
                }
            } else if (seleccion == 1) {
                int identificadorParaBuscar = Integer.parseInt(JOptionPane.showInputDialog(null,"Escriba el identificador que quiere modificar:","IDENTIFICADOR",JOptionPane.PLAIN_MESSAGE));
                if(comprobarSiDatoExiste(identificadorParaBuscar, "")) {                            
                    int indice = obtenerIndice(identificadorParaBuscar, "");
                    int nuevoIdentificador = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el nuevo identificador para ese nombre:","IDENTIFICADOR",JOptionPane.PLAIN_MESSAGE));
                    this.listaDinamica.get(indice).setIdentificador(nuevoIdentificador);
                    historico += "3 Dato modificado :" + identificadorParaBuscar + " y cambiado por : " + nuevoIdentificador;
                } else {
                    JOptionPane.showMessageDialog(null,"¡El dato a modificar no existe!","Información",JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null,"Solo tenías dos opciones...","Información",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e3) {
            JOptionPane.showMessageDialog(null,"Has introducido un valor en un campo que no le correspondía.","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean comprobarSiDatoExiste(int UnIdentificador, String UnNombre) {
        if (UnIdentificador != 0) {
            for(int indice = 0; indice < this.listaDinamica.size(); indice++) {
                if (this.listaDinamica.get(indice).getIdentificador() == UnIdentificador) {
                    return true;
                }
            }
        } else {
            for(int indice = 0; indice < this.listaDinamica.size(); indice++) {
                if(this.listaDinamica.get(indice).getNombre().equals(UnNombre)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int obtenerIndice(int IdentificadorCualquiera, String NombreCualquiera){
        int SuIndice = 0;
        if (IdentificadorCualquiera != 0) {
            for (int indice = 0 ;indice < this.listaDinamica.size(); indice++) {
                if (this.listaDinamica.get(indice).getIdentificador() == IdentificadorCualquiera){
                    SuIndice = indice;
                    break;
                }
            }
        } else {    
            for (int indice = 0 ; indice < this.listaDinamica.size(); indice++) {
                if (this.listaDinamica.get(indice).getNombre().equals(NombreCualquiera)) {
                    SuIndice = indice;
                    break;
                }
            }
        }
        return SuIndice;
    }

    private boolean eliminarDato() {
        try {
            String[] DosOpciones = {"Nombre", "Identificador"};
            String[] SioNo = {"SI", "NO"};
            int seleccion = JOptionPane.showOptionDialog(null, "¿Quieres eliminarlo a través de un nombre o un identificador?","ELIMINAR DATO", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, DosOpciones, null);
            if (seleccion == 0) {
                String nom = JOptionPane.showInputDialog(null,"Escribe el nombre a eliminar:","NOMBRE",JOptionPane.PLAIN_MESSAGE);
                int confirmacion = JOptionPane.showOptionDialog(null, "¿Estás seguro que quieres eliminarlo? Esta acción no se puede deshacer", "CONFIRMACION", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, SioNo, null);
                if (confirmacion == 0) {
                    for (int indice = 0; indice < this.listaDinamica.size(); indice++) {
                        if (this.listaDinamica.get(indice).getNombre().equals(nom)) {
                            this.listaDinamica.remove(indice);
                            historico += "4 Nombre del dato eliminado :" + nom + " de la lista \n";
                            return true;
                        }
                    }
                }
                else if (confirmacion == 1) {
                    JOptionPane.showMessageDialog(null,"Operacion cancelada","Información",JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            } else if (seleccion == 1) {
                int ident = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el identificador a eliminar:","IDENTIFICADOR",JOptionPane.PLAIN_MESSAGE));
                for (int indice = 0; indice < this.listaDinamica.size(); indice++) {
                    if (this.listaDinamica.get(indice).getIdentificador() == ident) {
                        this.listaDinamica.remove(indice);
                        historico += "4 Identificador del dato eliminado :" + ident + " de la lista \n";
                        return true;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null,"Solo tenías dos opciones...","Información",JOptionPane.INFORMATION_MESSAGE);
            }
            return false;
        } catch (NumberFormatException e4) {
            JOptionPane.showMessageDialog(null,"Has introducido un valor en un campo que no le correspondía.","Error",JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    private boolean buscarDato() {
        try {
            String[] DosOpciones = {"Nombre", "Identificador"};
            int seleccion = JOptionPane.showOptionDialog(null, "¿Quieres buscarlo a través de un nombre o un identificador?","BUSCAR DATO", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, DosOpciones, null);
            if (seleccion == 0) {
                String nom = JOptionPane.showInputDialog(null,"Escribe el nombre a buscar:","NOMBRE",JOptionPane.PLAIN_MESSAGE);
                for (int indice = 0 ; indice < this.listaDinamica.size(); indice++) {
                    if (this.listaDinamica.get(indice).getNombre().equals(nom)) {
                        String mostrarDato = "· Identificador[" + indice + "]: " + this.listaDinamica.get(indice).getIdentificador() + " - Nombre[" + indice + "]: " + this.listaDinamica.get(indice).getNombre() + "; \n";
                        JOptionPane.showMessageDialog(null, mostrarDato);
                        historico += "5 Dato buscado: " + nom + "\n";
                        return true;
                    }                        
                }
            } else if (seleccion == 1) {
                int ident = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el identificador a buscar:","IDENTIFICADOR",JOptionPane.PLAIN_MESSAGE));
                String mostrarDato = "";
                for (int indice = 0 ; indice < this.listaDinamica.size(); indice++) {
                    if (this.listaDinamica.get(indice).getIdentificador() == ident) {
                        mostrarDato += "· Identificador[" + indice + "]: " + this.listaDinamica.get(indice).getIdentificador() + " - Nombre[" + indice + "]: " + this.listaDinamica.get(indice).getNombre() + "; \n";
                        JOptionPane.showMessageDialog(null, mostrarDato);
                        historico += "5 Dato buscado: " + ident + "\n";
                        return true;
                    }                        
                }
            } else {
                JOptionPane.showMessageDialog(null,"Solo tenías dos opciones...","Información",JOptionPane.INFORMATION_MESSAGE);
            }
            return false;
        } catch (NumberFormatException e5) {
            JOptionPane.showMessageDialog(null,"Has introducido un valor en un campo que no le correspondía.","Error",JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    private void mostrarDatos() {
        String TodosLosDatos = "";
        for (int indice = 0 ; indice  < this.listaDinamica.size(); indice++) {
            TodosLosDatos += "· Identificador[" + indice + "]: " + this.listaDinamica.get(indice).getIdentificador() + " - Nombre[" + indice + "]: " + this.listaDinamica.get(indice).getNombre() + "; \n";
        }
        JOptionPane.showMessageDialog(null,TodosLosDatos);
    }

    private boolean clonarContacto() {
        try {
            String[] TwoOptions = {"Nombre", "Identificador"};
            int seleccion = JOptionPane.showOptionDialog(null, "¿Quieres buscar el dato a clonar a través de un nombre o un identificador?","CLONAR", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, TwoOptions, null);
            if (seleccion == 0) {
                String nom = JOptionPane.showInputDialog(null, "Escribe el nombre del dato a clonar:","NOMBRE",JOptionPane.PLAIN_MESSAGE);
                if (comprobarSiDatoExiste(0, nom)) {
                    int indice = obtenerIndice(0, nom);
                    ListaPersonas datoTemporal = new ListaPersonas(this.listaDinamica.get(indice).getIdentificador(), this.listaDinamica.get(indice).getNombre());
                    this.listaDinamica.add(datoTemporal);
                    historico += "7 Dato clonado:" + nom + "en la lista \n";
                    return true;            
                }
            } else if (seleccion == 1) {
                int ident = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el identificador del dato a clonar:","IDENTIFICADOR",JOptionPane.PLAIN_MESSAGE));
                if (comprobarSiDatoExiste(ident, "")) {
                    int indice = obtenerIndice(ident, "");
                    ListaPersonas datoTemporal = new ListaPersonas(this.listaDinamica.get(indice).getIdentificador(), this.listaDinamica.get(indice).getNombre());
                    this.listaDinamica.add(datoTemporal);
                    historico += "7 Dato clonado:" + ident + "en la lista \n";
                    return true;
                }
            } else {
                JOptionPane.showMessageDialog(null,"Solo tenías dos opciones...a ver como te lo explico.","Información",JOptionPane.INFORMATION_MESSAGE);
            }
            return false;
        } catch (NumberFormatException e6) {
            JOptionPane.showMessageDialog(null,"La has cagado metiendo algún valor.","Error",JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    private void buscarDuplicados() {
        int total = 0;
        String repetidos = "";
        for (int indice1 = 0; indice1 < this.listaDinamica.size(); indice1++) {
            for (int indice2 = 0; indice2 < this.listaDinamica.size(); indice2++) {
                if (indice1 != indice2) {
                    if (this.listaDinamica.get(indice1).getIdentificador() == this.listaDinamica.get(indice2).getIdentificador()) {
                        total++;
                        repetidos += "· " + listaDinamica.get(indice1).getIdentificador() + " - " + listaDinamica.get(indice1).getNombre() + "; posicion [" + indice1 + "] \n";
                    }
                } 
            }
        }
        if (total != 0) {
            JOptionPane.showMessageDialog(null,"Se encontró un total de " + total/2 + " dato/s repetido/s: \n" + repetidos,"Resultado",JOptionPane.INFORMATION_MESSAGE);
            int seleccion = JOptionPane.showConfirmDialog(null,"¿Desea eliminar aquellos que estén repetidos?","¿ELIMINAR?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (seleccion == 0) {
                borrarDuplicados();
                JOptionPane.showMessageDialog(null,"Los datos duplicados se han eliminado exitósamente.","Información",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,"La lista sigue intacta.","Información",JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,"No se han encontrado duplicados.","Información",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void borrarDuplicados() {
        for (int indice1 = 0; indice1 < this.listaDinamica.size(); indice1++) {
            for (int indice2 = 0; indice2 < this.listaDinamica.size(); indice2++) {
                if (indice1 != indice2) {
                    if (this.listaDinamica.get(indice1).getIdentificador() == this.listaDinamica.get(indice2).getIdentificador()) {
                        listaDinamica.remove(indice2);
                    }
                } 
            }
        }
    }

    private void recuentoDatos() {
        JOptionPane.showMessageDialog(null,"El número de contactos es " + this.listaDinamica.size() + ".");
    }

    private void mostrarPares() {
        String TodosLosPares = "";
        for (int indice = 0 ; indice  < this.listaDinamica.size(); indice++) {
            if (this.listaDinamica.get(indice).getIdentificador() % 2 == 0) {
                TodosLosPares += "· Identificador[" + indice + "]: " + this.listaDinamica.get(indice).getIdentificador() + " - Nombre[" + indice + "]: " + this.listaDinamica.get(indice).getNombre() + "; PAR \n";
            }
            else {
                TodosLosPares += "· Identificador[" + indice + "]: " + this.listaDinamica.get(indice).getIdentificador() + " - Nombre[" + indice + "]: " + this.listaDinamica.get(indice).getNombre() + "; IMPAR \n";;
            }
        }
        JOptionPane.showMessageDialog(null,TodosLosPares);
    }

    private void mostrarHistorico() {
        JOptionPane.showMessageDialog(null, historico);
    }
}