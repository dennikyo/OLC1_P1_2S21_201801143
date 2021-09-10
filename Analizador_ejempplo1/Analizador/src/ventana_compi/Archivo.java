/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventana_compi;

import java.util.LinkedList;

/**
 *
 * @author 50235
 */
public class Archivo {
    String nombre_archivo = "";
    LinkedList<String> variables;
    LinkedList<String> comentarios;
    LinkedList<Errores> lista_errores;
    LinkedList<Metodos> funciones;
    LinkedList<Clase> clases;
    
    public Archivo(String nombre_archivo, LinkedList<String> variables, LinkedList<String> comentarios, LinkedList<Errores> lista_errores, LinkedList<Metodos> funciones, LinkedList<Clase> clases){
        this.nombre_archivo = nombre_archivo;
        this.variables = variables;
        this.comentarios = comentarios;
        this.lista_errores = lista_errores;
        this.funciones = funciones;
        this.clases = clases;
}
}
