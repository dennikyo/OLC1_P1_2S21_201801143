/*--------------------------------------------------
 ------------  1ra Area: Codigo de Usuario ---------
 ---------------------------------------------------*/

//------> Paquetes,importaciones
package analizadores;
import java_cup.runtime.*;
import javax.swing.JOptionPane;

/*----------------------------------------------------------
  ------------  2da Area: Opciones y Declaraciones ---------
  ----------------------------------------------------------*/
%%
%{
    //----> Codigo de usuario en sintaxis java
%}

//-------> Directivas
%public 
%class Analizador_lexico
%cupsym Simbolos
%cup
%char
%column
%full
%ignorecase     
%line
%unicode

//------> Expresiones Regulares
digito              = [0-9]+
cadena              = [\"][^\"\n]+[\"]

//------> Estados

%%

/*------------------------------------------------
  ------------  3ra Area: Reglas Lexicas ---------
  ------------------------------------------------*/

//-----> Simbolos

"+"         { System.out.println("Reconocio "+yytext()+" mas"); return new Symbol(Simbolos.mas, yycolumn, yyline, yytext()); }
"-"         { System.out.println("Reconocio "+yytext()+" menos"); return new Symbol(Simbolos.menos, yycolumn, yyline, yytext()); }
"*"         { System.out.println("Reconocio "+yytext()+" por"); return new Symbol(Simbolos.por, yycolumn, yyline, yytext()); }
"/"         { System.out.println("Reconocio "+yytext()+" div"); return new Symbol(Simbolos.div, yycolumn, yyline, yytext()); }

";"         { System.out.println("Reconocio "+yytext()+" pyc"); return new Symbol(Simbolos.pyc, yycolumn, yyline, yytext()); }
"["         { System.out.println("Reconocio "+yytext()+" cora"); return new Symbol(Simbolos.cora, yycolumn, yyline, yytext()); }
"]"         { System.out.println("Reconocio "+yytext()+" corc"); return new Symbol(Simbolos.corc, yycolumn, yyline, yytext()); }
"("         { System.out.println("Reconocio "+yytext()+" para"); return new Symbol(Simbolos.para, yycolumn, yyline, yytext()); }
")"         { System.out.println("Reconocio "+yytext()+" parc"); return new Symbol(Simbolos.parc, yycolumn, yyline, yytext()); }
"="         { System.out.println("Reconocio "+yytext()+" igual"); return new Symbol(Simbolos.igual, yycolumn, yyline,yytext());}
"{"         { System.out.println("Reconocio "+yytext()+" llavea"); return new Symbol(Simbolos.llavea, yycolumn, yyline,yytext());}
"}"         { System.out.println("Reconocio "+yytext()+" llavec"); return new Symbol(Simbolos.llavec, yycolumn, yyline,yytext());}
":"         { System.out.println("Reconocio "+yytext()+" dosp"); return new Symbol(Simbolos.dosp, yycolumn, yyline, yytext()); }





//-----> Palabras reservadas

"DefinirGlobales" { System.out.println("Reconocio "+yytext()+" glob"); return new Symbol(Simbolos.glob, yycolumn, yyline, yytext()); }

"GenerarReporteEstadistico" { System.out.println("Reconocio "+yytext()+" esta"); return new Symbol(Simbolos.esta, yycolumn, yyline, yytext()); }

"GraficaBarras" { System.out.println("Reconocio "+yytext()+" graf"); return new Symbol(Simbolos.graf, yycolumn, yyline, yytext()); }

"Titulo" { System.out.println("Reconocio "+yytext()+" tit"); return new Symbol(Simbolos.tit, yycolumn, yyline, yytext()); }

"EjeX" { System.out.println("Reconocio "+yytext()+" ejex"); return new Symbol(Simbolos.ejex, yycolumn, yyline, yytext()); }

"Valores" { System.out.println("Reconocio "+yytext()+" val"); return new Symbol(Simbolos.val, yycolumn, yyline, yytext()); }

"TituloX" { System.out.println("Reconocio "+yytext()+" titx"); return new Symbol(Simbolos.titx, yycolumn, yyline, yytext()); }

"TituloY" { System.out.println("Reconocio "+yytext()+" tity"); return new Symbol(Simbolos.tity, yycolumn, yyline, yytext()); }

"GraficaPie" { System.out.println("Reconocio "+yytext()+" pie"); return new Symbol(Simbolos.pie, yycolumn, yyline, yytext()); }

"Archivo" { System.out.println("Reconocio "+yytext()+" arch"); return new Symbol(Simbolos.arch, yycolumn, yyline, yytext()); }

"evaluar"         { System.out.println("Reconocio "+yytext()+" eval"); return new Symbol(Simbolos.eval, yycolumn, yyline, yytext()); }

//-------> Simbolos ER
{digito}    { System.out.println("Reconocio "+yytext()+" digito"); return new Symbol(Simbolos.digito, yycolumn, yyline, yytext()); }
{cadena}    { System.out.println("Reconocio "+yytext()+" cadena"); return new Symbol(Simbolos.cadena, yycolumn, yyline, yytext()); }

//------> Espacios
[ \t\r\n\f]             {/* Espacios en blanco, se ignoran */}

//-------> Errores Lexicos 
.           {System.out.println("Error Lexico " + yytext() + "Linea: " + yyline + "Columna: " + yycolumn); }
