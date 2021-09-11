/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventana_compi;

import Reportes.Puntajes;
import analizadores.Analizador_Lexico_FCA;
import analizadores.Analizador_lexico;
import analizadores.Analizador_lexico_js;
import analizadores.Sintactico;
import analizadores.Sintactico_FCA;
import analizadores.Sintacticojs;
import java.io.BufferedReader;
import java.io.StringReader;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 50235
 */
public class interfaz extends javax.swing.JFrame {

    /**
     * Creates new form interfaz
     */
    
    //Creamos las listas que vamos a utilizar 
    static LinkedList<Object> instruccionesFCA = new LinkedList<Object>();
    public static LinkedList<Errores> lista_errores = new LinkedList<>();
    LinkedList<Archivo> datos_archivos = new LinkedList<>();
    
    public interfaz() {
        initComponents();
    }

    // METODO UTILIZADO PARA COMPARAR ARCHIVOS 
     public void comparar_archivos_proyectos(String ruta_proy1, String ruta_proy2){
        
        try{
            DirectoryStream<Path> stream_p1 = Files.newDirectoryStream(Paths.get(ruta_proy1), "*.js");
            
           //Recorremos los archivos del proyecto 1
            for (Path file_p1: stream_p1) {
                DirectoryStream<Path> stream_p2 = Files.newDirectoryStream(Paths.get(ruta_proy2), "*.js");
                
                String nombre_archivo1 = file_p1.getFileName().toString(); 
                File archivo = new File (file_p1.toString());
                FileReader fr = new FileReader (archivo);
                Archivo nuevo_archivo1 = null;
                Archivo nuevo_archivo2 = null;
                //Vamos a comparar el archivo del proyecto1 con los archivos de la carpeta2 para saber si se llaman igual 
                for(Path file_p2 : stream_p2){
                    String nombre_archivo2 = file_p2.getFileName().toString();
                    File archivo2 = new File (file_p2.toString());
                    FileReader fr2 = new FileReader (archivo2);
                    //Si se llaman igual se comienza el proceso para analizar copias
                    if(nombre_archivo1.equals(nombre_archivo2)){
                        System.out.println("Los nombres son iguales, vamos a comparar --> " + file_p1.getFileName());
                        //--> 1ero vamos a analizar el archivo1 del proyecto 1
                        try{
                            
                            System.out.println("----------- " + nombre_archivo1 + " en PROYECTO 1 ----------- ");
                            Nodo raiz = null;
                            //Mandamos a analizar el archivo del proyecto 1 
                            Sintacticojs parse = new Sintacticojs(new Analizador_lexico_js(new BufferedReader(fr)));
                            parse.parse();

                            raiz = parse.getRaiz();
                            if(raiz == null){
                                System.out.println("No se genero bien el arbol");
                            }else{
                                
                                nuevo_archivo1 = new Archivo(nombre_archivo1, new LinkedList<>(), new LinkedList<>(), new LinkedList<>(), new LinkedList<>(),  new LinkedList<>());
                                //--> vamos a guardar las variables encontradas en el archivo
                                encontrar_variables(raiz,nuevo_archivo1.variables ); 
                                //--> vamos a guardar los metodos del programa
                                encontrar_funciones(raiz, nuevo_archivo1.funciones);
                                //--> vamos a guardad las clases del programa
                                encontrar_clases(raiz, nuevo_archivo1.clases);
                                //-->agregamos los comentarios encontrados (la lista se lleno en el archivo A_Lexico_FCA.jflex)
                                /*for(String comment : lista_comentarios){
                                    nuevo_archivo1.comentarios.add(comment);
                                }*/
                                //-->agregamos los errores encontrados (la lista se lleno en los archivos A_Lexico_FCA.jflex y A_sintacticos_FCA.cup)
                                for(Errores error : lista_errores){
                                    //--> guardamos los errore indicando el nombre del archivo
                                    Errores nuevo_error = new Errores(error.tipo, error.valor, nuevo_archivo1.nombre_archivo, error.fila, error.columna);
                                    nuevo_archivo1.lista_errores.add(nuevo_error);
                                }
                                //Arbol arbol = new Arbol(raiz);
                                //arbol.GraficarSintactico();
                                
                                //-->guardamos el archivo en una lista
                                this.datos_archivos.add(nuevo_archivo1);
                                //-->limpiamos variables
                                lista_errores.clear();
                                //lista_comentarios.clear();
                                
                            }
                        }catch(Exception ex){
                            System.out.println("Error en analizar el archivo del proyecto.");
                            System.out.println("Causa: "+ex.getCause());
                        }
                        //--> 2do vamos a analizar el archivo2
                        try{
                            
                            System.out.println("----------- " + nombre_archivo2 + " en PROYECTO 2----------- ");
                            Nodo raiz = null;
                            //Mandamos a analizar el archivo del proyecto 2
                            Sintacticojs parse = new Sintacticojs(new Analizador_lexico_js(new BufferedReader(fr2)));
                            parse.parse();

                            raiz = parse.getRaiz();
                            if(raiz == null){
                                System.out.println("No se genero bien el arbol");
                            }else{
                                
                                nuevo_archivo2 = new Archivo(nombre_archivo2, new LinkedList<>(), new LinkedList<>(),new LinkedList<>(), new LinkedList<>(),  new LinkedList<>());
                                //--> vamos a guardar las variables encontradas en el archivo
                                encontrar_variables(raiz, nuevo_archivo2.variables); 
                                //--> vamos a guardar los metodos del programa
                                encontrar_funciones(raiz, nuevo_archivo2.funciones);
                                //--> vamos a guardad las clases del programa
                                encontrar_clases(raiz, nuevo_archivo2.clases);
                                //-->agregamos los comentarios encontrados (la lista se lleno en el archivo A_Lexico_FCA.jflex)
                                /*for(String comment : lista_comentarios){
                                    nuevo_archivo2.comentarios.add(comment);
                                }*/
                                //-->agregamos los errores encontrados (la lista se lleno en los archivos A_Lexico_FCA.jflex y A_sintacticos_FCA.cup)
                                for(Errores error : lista_errores){
                                    //--> guardamos los errore indicando el nombre del archivo
                                    Errores nuevo_error = new Errores(error.tipo, error.valor, nuevo_archivo2.nombre_archivo, error.fila, error.columna);
                                    nuevo_archivo2.lista_errores.add(nuevo_error);
                                }
                                //Arbol arbol = new Arbol(raiz);
                                //arbol.GraficarSintactico();
                                //-->guardamos el archivo en una lista
                                this.datos_archivos.add(nuevo_archivo2);
                                //-->limpiamos variables
                                lista_errores.clear();
                                //lista_comentarios.clear();
                                
                            }
                        }catch(Exception ex){
                            System.out.println("Error en analizar el archivo del proyecto.");
                            System.out.println("Causa: "+ex.getCause());
                        }
                        
                        //--> detectamos copias entre estos dos archivos 
                        if(nuevo_archivo1 != null && nuevo_archivo2 != null){
                            variables_repetidas(nuevo_archivo1, nuevo_archivo2);
                            funciones_repetidas(nuevo_archivo1, nuevo_archivo2);
                            clases_repetidas(nuevo_archivo1, nuevo_archivo2);
                            //comentarios_repetidos(nuevo_archivo1, nuevo_archivo2);
                        }
                    }
                }
            }
        } catch (IOException | DirectoryIteratorException ex) {
		    System.err.println(ex);
		}
    }
    
    public void encontrar_variables(Nodo nodo, LinkedList<String> variables_archivo){

        for(Nodo instruccion : nodo.hijos){

            if(instruccion.token == "DECLARACION"){
                for(Nodo declaracion : instruccion.hijos){
                    if(instruccion.hijos.size() >= 4 && !(instruccion.hijos.get(3).token == "pyc") ){
                        if(declaracion.token == "id"){
                        variables_archivo.add(declaracion.lexema);
                    }
                    }
                    
                }
            }

            if(instruccion.lexema == "" ){
                encontrar_variables(instruccion, variables_archivo);
            }
        }
    }
     
    public void encontrar_funciones(Nodo nodo, LinkedList<Metodos> funciones_archivo){
        
        
        for(Nodo instruccion : nodo.hijos){

            if(instruccion.token == "FUNCION"){
                int cont_params = 0;
                boolean parametros = false;
                Metodos metodos = new Metodos("",0,0);
                int linea_inicio = 0;
                int linea_fin = 0;
                boolean yaid = false; 
                boolean noesllamada = false;
                
                for(Nodo funcion : instruccion.hijos){
                    
                    if(funcion.token == "id" && parametros == false && yaid == false ){
                        metodos.id = funcion.lexema;
                        yaid = true; 
                    }else if(funcion.token == "para"){
                        parametros = true;
                    }else if(funcion.token == "id" && parametros == true){
                        cont_params = cont_params + 1;
                    }else if(funcion.token == "parc"){
                        parametros = false;
                    }else if(funcion.token == "llavea"){
                        linea_inicio = funcion.linea;
                        noesllamada = true;
                    }else if(funcion.token == "llavec"){
                        linea_fin = funcion.linea;
                    }
                    
                }
                
                int cant_lineas = linea_fin - linea_inicio;
                metodos.cant_lineas = cant_lineas;
                metodos.cant_params = cont_params;
                
                if(metodos.id != "" && noesllamada){ 
                    funciones_archivo.add(metodos);
                }
            }

            if(instruccion.lexema == "" ){
                encontrar_funciones(instruccion, funciones_archivo);
            }
        }
    }
    
    public void encontrar_clases(Nodo nodo, LinkedList<Clase> lista_clases){
        
         for(Nodo instruccion : nodo.hijos){

            if(instruccion.token == "CLASE"){
                Clase nueva_clase = new Clase("", new LinkedList<>(), 0);
                int linea_inicial = 0;
                int linea_final = 0;
                
                for(Nodo clase : instruccion.hijos){
                    
                    if(clase.token == "id"){
                        nueva_clase.id = clase.lexema;
                    }else if(clase.token == "llavea"){
                        linea_inicial = clase.linea;
                    }else if(clase.token == "llavec"){
                        linea_final = clase.linea;
                    }else{
                        encontrar_funciones(clase, nueva_clase.metodos_repetidos);
                    }
                }
                
                nueva_clase.cant_lineas =  linea_final - linea_inicial;
                
                lista_clases.add(nueva_clase);
                
            }else if(instruccion.lexema == "" ){
                encontrar_clases(instruccion, lista_clases);
            }
         }
    }
   
     
    public void variables_repetidas(Archivo archivo1, Archivo archivo2){
        //dar el punteo. 
        for(String id_variable_arch1 : archivo1.variables){
            for(String id_variable_arch2 : archivo2.variables){
                if(id_variable_arch1.equals(id_variable_arch2)){
                    jTextArea2.append("Variable repetida \"" + id_variable_arch1 +"\" en archivos " + archivo1.nombre_archivo + "\n");
                    //this.cont_variables_repetidas++;
                    //this.lista_puntajesEspecificos.add(new Puntajes(archivo1.getNombreArchivo(), "variable",id_variable_arch1,  1));
                }
            }
        }
    }
    
    public void funciones_repetidas(Archivo archivo1, Archivo archivo2){
        for(Metodos funcion1 : archivo1.funciones){
            for(Metodos funcion2 : archivo2.funciones){
                double puntaje_metodo = 0;
                
                if(funcion1.id.equals(funcion2.id) && !funcion1.id.equals("")){
                    //caso id repetido
                    puntaje_metodo = puntaje_metodo + 0.4;
                    if(funcion1.cant_params == funcion2.cant_params){
                          puntaje_metodo = puntaje_metodo + 0.3;
                        if(funcion1.cant_lineas == funcion2.cant_lineas){
                              puntaje_metodo = puntaje_metodo + 0.3;
                            jTextArea2.append("Funciones repetida \"" + funcion1.id +"\" en archivos " + archivo1.nombre_archivo + "\n");
                        }
                    }
                }else{
                    //caso id diferentes
                    if(funcion1.cant_lineas == funcion2.cant_lineas){
                         puntaje_metodo = puntaje_metodo + 0.3;
                        if(funcion1.cant_params == funcion2.cant_params){
                             puntaje_metodo = puntaje_metodo + 0.3;
                            jTextArea2.append("Funciones repetida \"" + funcion1.id + " y " + funcion2.id +"\" en archivos " + archivo1.nombre_archivo + "\n");
                        }
                    }
                }
                 //this.lista_puntajesEspecificos.add(new Puntajes(archivo1.getNombreArchivo(), "metodo",funcion1.id,  puntaje_metodo));
            }
        }
    }
    
    public void clases_repetidas(Archivo archivo1, Archivo archivo2){
        for(Clase clase1 : archivo1.clases){
            for(Clase clase2 : archivo2.clases){
                double puntaje_clase = 0;
                if(clase1.id.equals(clase2.id)){
                    //vamos a ver las funciones
                    // puntaje de id repetido = 0.2
                    puntaje_clase = puntaje_clase + 0.2;
                    int cant_metodos = clase1.metodos_repetidos.size();
                    int cant_metodos_repetidos = 0;
                     for(Metodos funcion1 : clase1.metodos_repetidos){
                        for(Metodos funcion2 : clase2.metodos_repetidos){
                            if(funcion1.id.equals(funcion2.id) ){
                                cant_metodos_repetidos = cant_metodos_repetidos +1;
                               
                            }
                        }
                     }
                    if(cant_metodos == cant_metodos_repetidos){
                        //puntaje para metodos repetidos = 0.4
                        puntaje_clase = puntaje_clase + 0.4;
                        if(clase1.cant_lineas == clase2.cant_lineas){
                            jTextArea2.append("Clase repetida \"" + clase1.id  +"\" en archivos " + archivo1.nombre_archivo + "\n");
                            //puntaje lineas repetidas = 0.4 
                            puntaje_clase = puntaje_clase + 0.4;
                        }
                    }
                }
                //this.lista_puntajesEspecificos.add(new Puntajes(archivo1.getNombreArchivo(), "clase",clase1.id,  puntaje_clase));
            }
        }
    }
    
    public void ReporteErrores(){
        
        LinkedList<Errores> Reporte_errores = new LinkedList<>();
        
        for(Archivo archivo : datos_archivos){
            Reporte_errores.addAll(archivo.lista_errores);
        }
        
        FileWriter fichero = null;
        PrintWriter pw = null;
                try {
                    fichero = new FileWriter("D:\\ReporteErrores.html");
                    pw = new PrintWriter(fichero); 
                String Html = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//ES\">\n\t"
               + "<HTML>\n\t"
               + "<HEAD>\n\t"
               + "<TITLE>REPORTE DE ERRORES</TITLE>\n\t"
               + "<style>\n\t"
               + "body {\n\t"
               + "background:#AAAA;\n\t"
               +/* el fondo de todo el cuerpo*/ "padding: 20px;\n\t"
               + /*el espacio entre el borde y su contenido*/ "}\n\t"
               + "h2 {\n\t"
               + "color: #5D6D7E;\n\t"
               + "font-family: Calibri;\n\t"
               + /*tipo de fuente*/ "}\n\t"
               + ".articulo {\n\t"
               + "font-size: 14px;\n\t"
               + "font-family: Calibri;\n\t"
               + "background: #7FB3D5;\n\t"
               + "border: 6px solid #2471A3;\n\t" //borde cuadro arriba
               + "color: #AAAAAFF;\n\t"
               + "padding: 13px;\n\t"
               + "}\n\t"
               + ".tabla {\n\t"
               + "font-size: 14px;\n\t"
               + "font-family: Century Gothic;\n\t"
               + "background: #AAAAA;\n\t"
               + "border: 6px solid #5D6D7E;\n\t" //borde cuadro abajo
               + "color: #000000;\n\t"
               + "padding: 13px;\n\t"
               + "}\n\t"
               + ".fin {\n\t"
               + "font-size: 14px;\n\t"
               + "font-family: Eras Light ITC;\n\t"
               + "background: #7FB3D5;\n\t"
               + "border: 6px solid #F74316;\n\t"
               + "color: #000000;\n\t" +//2939B5
               "padding: 13px;\n\t"
               + "}\n\t"
               + "</style>\n\t"
               + "</HEAD>\n\t"
               + "<BODY>\n\t"
               + "<div class=\"articulo\"><H3>Universidad de San Carlos de Guatemala<BR>Facultad de Ingenieria<BR>Escuela de Ciencias y Sistemas<BR>Reporte de Errores</H3><CENTER><H2>Organizacion de Lenguajes y Compiladores 1<BR>PROYECTO 1 [FIUSAC Copy Analyzer]<BR>REPORTE DE ERRORES</H2></CENTER></div>\n"
               + "<div class=\"tabla\"><UL>\n" +//No. Errores: 
               "<table style= border=3>\n\t"
               + "<tr align=\"center\" bottom=\"middle\">\n\t"
               + "<td>\n\t"
               + "<table border=2>\n\t"
               + "<tr align=\"center\" bottom=\"middle\">\n\t"
               + "<td><b>Tipo</b></td>\n\t"
               + "<td><b>Descripcion</b></td>\n\t"
               + "<td><b>Fila</b></td>\n\t"
               +  "<td><b>Columna</b></td>\n\t"
               +  "<td><b>Archivo</b></td>\n\t"
               + "</tr>\n\t";
               
                for(Errores error : Reporte_errores){
                    Html += "<tr align=\"center\" bottom=\"middle\">\n\t"
                    + "<td>" + error.tipo + "</td>"
                    + "<td>" + error.valor + "</td>"
                    + "<td>" + error.fila  + "</td>"
                    +  "<td>" + error.columna + "</td>"
                    +  "<td>" + error.archivo + "</td>"
                    + "</tr>\n\t";
                }  
                Html += "</tr></table></tr></table></UL></div>\n\t"
                +"</BODY>\n\t"
                + "</HTML>";
                pw.print(Html);
                    
                } catch (Exception e) {
                }finally{
                    if(null!=fichero){
                        try {
                            fichero.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "Reportes\\"+"ReporteErrores.html");
            //System.out.println("Final");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("FIUSAC COPY ANALIZER");

        jButton1.setText("ARCHIVO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("CREAR PESTAÑA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("ELIMINAR PESTAÑA");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("EJECUTAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("REPORTES");

        jLabel2.setText("EDITOR");

        jLabel3.setText("CONSOLA");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jButton1)
                        .addGap(81, 81, 81)
                        .addComponent(jButton2)
                        .addGap(72, 72, 72)
                        .addComponent(jButton3)
                        .addGap(57, 57, 57)
                        .addComponent(jButton4)
                        .addGap(55, 55, 55)
                        .addComponent(jButton5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(106, 106, 106)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(273, 273, 273)))))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(45, 45, 45)
                .addComponent(jLabel2)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            Sintacticojs sintactico = new Sintacticojs (new Analizador_lexico_js(new BufferedReader( new StringReader(jTextArea1.getText()))));
            sintactico.parse();
            
            
            
            System.out.println("Todo Correcto ");
            
           
            
        }catch (Exception ex){
            Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        JOptionPane.showMessageDialog(null, "Analizado con exito", "Informacion", JOptionPane.INFORMATION_MESSAGE);  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // VAMOS A EJECUTAR EL ARCHIVO .FCA
         try {
            Sintactico_FCA sintactico = new Sintactico_FCA(new Analizador_Lexico_FCA(new BufferedReader( new StringReader(jTextArea1.getText()))));
            sintactico.parse();
            instruccionesFCA = sintactico.instrucciones;
            
            //Recorre las instrucciones encontradas en el archivo FCA
            //--> ejecutamos primero el comando comparar 
            for(Object ins : instruccionesFCA){
                if(ins instanceof Comparar){
                    Comparar comp = (Comparar)ins;
                    comparar_archivos_proyectos(comp.getRuta1(), comp.getRuta2());
                    
                }
            }
            
            /*Sintacticojs sintactico = new Sintacticojs(new Analizador_lexico_js(new BufferedReader( new StringReader(textArea1.getText()))));
            sintactico.parse(); 

            raiz = sintactico.getRaiz();

            System.out.println("Todo Correcto ");*/



        }catch (Exception ex){
            Logger.getLogger(Ventana_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
