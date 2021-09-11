/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadores;
import java.io.File;

/**
 *
 * @author 50235
 */
public class GLexico {
    public static void main(String[] args)
    {
        String path="src/analizadores/A_lexicoFCA.jflex";
        generarLexer(path);
    }
    public static void generarLexer(String path)
    {
        File file = new File(path);
        jflex.Main.generate(file);
    }
    
}
