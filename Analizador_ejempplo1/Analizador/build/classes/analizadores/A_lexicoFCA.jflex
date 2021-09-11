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
%class Analizador_Lexico_FCA
%cupsym Simbolos_FCA
%cup
%char
%column
%full
%ignorecase
%line
%unicode

//------> Expresiones Regulares

cadena              = [\"][^\"\n]+[\"]
id                  = [A-z0-9]+("_"|[A-z]|[0-9])*
decimal             = [0-9]+("."[0-9]*)
digito              = [0-9]+

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

comentariosimple    = "##" {InputCharacter}* {LineTerminator}?

//------> Estados

%%

/*------------------------------------------------
  ------------  3ra Area: Reglas Lexicas ---------
  ------------------------------------------------*/

//-----> Simbolos

","         { System.out.println("Reconocio "+yytext()+" coma"); return new Symbol(Simbolos_FCA.coma, yycolumn, yyline, yytext()); }
"{"         { System.out.println("Reconocio "+yytext()+" llavea"); return new Symbol(Simbolos_FCA.llavea, yycolumn, yyline, yytext()); }
"}"         { System.out.println("Reconocio "+yytext()+" llavec"); return new Symbol(Simbolos_FCA.llavec, yycolumn, yyline, yytext()); }
"("         { System.out.println("Reconocio "+yytext()+" para"); return new Symbol(Simbolos_FCA.para, yycolumn, yyline, yytext()); }
")"         { System.out.println("Reconocio "+yytext()+" parc"); return new Symbol(Simbolos_FCA.parc, yycolumn, yyline, yytext()); }
";"         { System.out.println("Reconocio "+yytext()+" pyc"); return new Symbol(Simbolos_FCA.pyc, yycolumn, yyline, yytext()); }
":"         { System.out.println("Reconocio "+yytext()+" dosp"); return new Symbol(Simbolos_FCA.dosp, yycolumn, yyline, yytext()); }
"="         { System.out.println("Reconocio "+yytext()+" igual"); return new Symbol(Simbolos_FCA.igual, yycolumn, yyline,yytext());}
"+"         { System.out.println("Reconocio "+yytext()+" mas"); return new Symbol(Simbolos_FCA.mas, yycolumn, yyline, yytext()); }
"-"         { System.out.println("Reconocio "+yytext()+" menos"); return new Symbol(Simbolos_FCA.menos, yycolumn, yyline, yytext()); }
"*"         { System.out.println("Reconocio "+yytext()+" por"); return new Symbol(Simbolos_FCA.por, yycolumn, yyline, yytext()); }
"/"         { System.out.println("Reconocio "+yytext()+" div"); return new Symbol(Simbolos_FCA.div, yycolumn, yyline, yytext()); }
"["         { System.out.println("Reconocio "+yytext()+" cora"); return new Symbol(Simbolos_FCA.cora, yycolumn, yyline, yytext()); }
"]"         { System.out.println("Reconocio "+yytext()+" corc"); return new Symbol(Simbolos_FCA.corc, yycolumn, yyline, yytext()); }
"$"         { System.out.println("Reconocio "+yytext()+" dola"); return new Symbol(Simbolos_FCA.dola, yycolumn, yyline, yytext()); }

//-----> Palabras reservadas
"GenerarReporteEstadistico"         { System.out.println("Reconocio "+yytext()+" gre"); return new Symbol(Simbolos_FCA.gre, yycolumn, yyline, yytext()); }
"Compare"         { System.out.println("Reconocio "+yytext()+" comp"); return new Symbol(Simbolos_FCA.comp, yycolumn, yyline, yytext()); }
"DefinirGlobales" { System.out.println("Reconocio "+yytext()+" glob"); return new Symbol(Simbolos_FCA.glob, yycolumn, yyline, yytext()); }

"GenerarReporteEstadistico" { System.out.println("Reconocio "+yytext()+" esta"); return new Symbol(Simbolos_FCA.esta, yycolumn, yyline, yytext()); }

"GraficaBarras" { System.out.println("Reconocio "+yytext()+" graf"); return new Symbol(Simbolos_FCA.graf, yycolumn, yyline, yytext()); }

"Titulo" { System.out.println("Reconocio "+yytext()+" tit"); return new Symbol(Simbolos_FCA.tit, yycolumn, yyline, yytext()); }

"EjeX" { System.out.println("Reconocio "+yytext()+" ejex"); return new Symbol(Simbolos_FCA.ejex, yycolumn, yyline, yytext()); }

"Valores" { System.out.println("Reconocio "+yytext()+" val"); return new Symbol(Simbolos_FCA.val, yycolumn, yyline, yytext()); }

"TituloX" { System.out.println("Reconocio "+yytext()+" titx"); return new Symbol(Simbolos_FCA.titx, yycolumn, yyline, yytext()); }

"TituloY" { System.out.println("Reconocio "+yytext()+" tity"); return new Symbol(Simbolos_FCA.tity, yycolumn, yyline, yytext()); }

"GraficaPie" { System.out.println("Reconocio "+yytext()+" pie"); return new Symbol(Simbolos_FCA.pie, yycolumn, yyline, yytext()); }

"Archivo" { System.out.println("Reconocio "+yytext()+" arch"); return new Symbol(Simbolos_FCA.arch, yycolumn, yyline, yytext()); }

"string"         { System.out.println("Reconocio "+yytext()+" str"); return new Symbol(Simbolos_FCA.str, yycolumn, yyline, yytext()); }

"double"         { System.out.println("Reconocio "+yytext()+" dou"); return new Symbol(Simbolos_FCA.dou, yycolumn, yyline, yytext()); }

"PuntajeEspecifico"         { System.out.println("Reconocio "+yytext()+" pesp"); return new Symbol(Simbolos_FCA.pesp, yycolumn, yyline, yytext()); }
 

//-------> Simbolos ER
{cadena}    { System.out.println("Reconocio "+yytext()+" cadena"); return new Symbol(Simbolos_FCA.cadena, yycolumn, yyline, yytext()); }
{id}    { System.out.println("Reconocio "+yytext()+" id"); return new Symbol(Simbolos_FCA.id, yycolumn, yyline, yytext()); }
{decimal}    { System.out.println("Reconocio "+yytext()+" deci"); return new Symbol(Simbolos_FCA.deci, yycolumn, yyline, yytext()); }
{digito}    { System.out.println("Reconocio "+yytext()+" digito"); return new Symbol(Simbolos_FCA.digito, yycolumn, yyline, yytext()); }


//------> Espacios
{comentariosimple}      {System.out.println("Comentario: "+yytext()); }
[ \t\r\n\f]             {/* Espacios en blanco, se ignoran */}

//------> Errores Lexicos
.                       { System.out.println("Error Lexico"+yytext()+" Linea "+yyline+" Columna "+yycolumn);}
