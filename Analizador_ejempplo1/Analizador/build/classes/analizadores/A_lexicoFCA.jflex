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
"{"         { System.out.println("Reconocio "+yytext()+" llava"); return new Symbol(Simbolos_FCA.llava, yycolumn, yyline, yytext()); }
"}"         { System.out.println("Reconocio "+yytext()+" llavc"); return new Symbol(Simbolos_FCA.llavc, yycolumn, yyline, yytext()); }
"("         { System.out.println("Reconocio "+yytext()+" para"); return new Symbol(Simbolos_FCA.para, yycolumn, yyline, yytext()); }
")"         { System.out.println("Reconocio "+yytext()+" parc"); return new Symbol(Simbolos_FCA.parc, yycolumn, yyline, yytext()); }
";"         { System.out.println("Reconocio "+yytext()+" pyc"); return new Symbol(Simbolos_FCA.pyc, yycolumn, yyline, yytext()); }
":"         { System.out.println("Reconocio "+yytext()+" dosp"); return new Symbol(Simbolos.dosp, yycolumn, yyline, yytext()); }
"="         { System.out.println("Reconocio "+yytext()+" igual"); return new Symbol(Simbolos.igual, yycolumn, yyline,yytext());}
"+"         { System.out.println("Reconocio "+yytext()+" mas"); return new Symbol(Simbolos.mas, yycolumn, yyline, yytext()); }
"-"         { System.out.println("Reconocio "+yytext()+" menos"); return new Symbol(Simbolos.menos, yycolumn, yyline, yytext()); }
"*"         { System.out.println("Reconocio "+yytext()+" por"); return new Symbol(Simbolos.por, yycolumn, yyline, yytext()); }
"/"         { System.out.println("Reconocio "+yytext()+" div"); return new Symbol(Simbolos.div, yycolumn, yyline, yytext()); }
"["         { System.out.println("Reconocio "+yytext()+" cora"); return new Symbol(Simbolos.cora, yycolumn, yyline, yytext()); }
"]"         { System.out.println("Reconocio "+yytext()+" corc"); return new Symbol(Simbolos.corc, yycolumn, yyline, yytext()); }

//-----> Palabras reservadas
"GenerarReporteEstadistico"         { System.out.println("Reconocio "+yytext()+" gre"); return new Symbol(Simbolos_FCA.gre, yycolumn, yyline, yytext()); }
"Compare"         { System.out.println("Reconocio "+yytext()+" comp"); return new Symbol(Simbolos_FCA.comp, yycolumn, yyline, yytext()); }
 

//-------> Simbolos ER
{cadena}    { System.out.println("Reconocio "+yytext()+" cadena"); return new Symbol(Simbolos_FCA.cadena, yycolumn, yyline, yytext()); }
{id}    { System.out.println("Reconocio "+yytext()+" id"); return new Symbol(Simbolos.id, yycolumn, yyline, yytext()); }
{decimal}    { System.out.println("Reconocio "+yytext()+" deci"); return new Symbol(Simbolos.deci, yycolumn, yyline, yytext()); }


//------> Espacios
{comentariosimple}      {System.out.println("Comentario: "+yytext()); }
[ \t\r\n\f]             {/* Espacios en blanco, se ignoran */}

//------> Errores Lexicos
.                       { System.out.println("Error Lexico"+yytext()+" Linea "+yyline+" Columna "+yycolumn);}
