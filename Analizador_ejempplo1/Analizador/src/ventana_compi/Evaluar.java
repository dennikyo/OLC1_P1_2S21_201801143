/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventana_compi;

/**
 *
 * @author Denisse :V
 */
public class Evaluar {
    private int valor, linea, colum;

    public Evaluar(int valor, int linea, int colum){
        this.valor = valor;
        this.linea = linea;
        this.colum = colum;
    }

    public int getValor(){
        return this.valor;
    }
}
