/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventana_compi;

import java.util.LinkedList;

/**
 *
 * @author sandr
 */
public class Clase {
    
    public String id;
    public LinkedList<Metodos> metodos_repetidos;
    public int cant_lineas;
    
    public Clase(String id, LinkedList<Metodos> cont_metodos, int cant_lineas){
        this.id = id;
        this.metodos_repetidos = cont_metodos;
        this.cant_lineas = cant_lineas;
    }
}
