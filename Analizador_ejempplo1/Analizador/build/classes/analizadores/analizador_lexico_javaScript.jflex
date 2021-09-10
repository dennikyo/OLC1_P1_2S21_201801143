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
%class Analizador_lexico_js
%cupsym Simbolosjs
%cup
%char
%column
%full
%line
%unicode

//------> Expresiones Regulares
cadena              = [\"][^\"\n]+[\"]
id                  = [A-z0-9]+("_"|[A-z]|[0-9])*
decimal             = [0-9]+("."[0-9]*)
digito              = [0-9]+([.][0-9]+)?

//Comentarios

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

comentariosimple    = "//" {InputCharacter}* {LineTerminator}?
comentarioMulti     =  "/*" [^/] ~"*/"

//------> Estados

%%

/*------------------------------------------------
  ------------  3ra Area: Reglas Lexicas ---------
  ------------------------------------------------*/

//-----> Simbolos

";"         { System.out.println("Reconocio "+yytext()+" pyc"); return new Symbol(Simbolosjs.pyc, yycolumn, yyline, yytext()); }
"("         { System.out.println("Reconocio "+yytext()+" para"); return new Symbol(Simbolosjs.para, yycolumn, yyline, yytext()); }
")"         { System.out.println("Reconocio "+yytext()+" parc"); return new Symbol(Simbolosjs.parc, yycolumn, yyline, yytext()); }
"="         { System.out.println("Reconocio "+yytext()+" igual"); return new Symbol(Simbolosjs.igual, yycolumn, yyline,yytext());}
"{"         { System.out.println("Reconocio "+yytext()+" llavea"); return new Symbol(Simbolosjs.llavea, yycolumn, yyline,yytext());}
"}"         { System.out.println("Reconocio "+yytext()+" llavec"); return new Symbol(Simbolosjs.llavec, yycolumn, yyline,yytext());}
","         { System.out.println("Reconocio "+yytext()+" coma"); return new Symbol(Simbolosjs.coma, yycolumn, yyline, yytext()); }
"=="         { System.out.println("Reconocio "+yytext()+" iguall"); return new Symbol(Simbolosjs.iguall, yycolumn, yyline, yytext()); }
"!="         { System.out.println("Reconocio "+yytext()+" dif"); return new Symbol(Simbolosjs.dif, yycolumn, yyline, yytext()); }
"<"         { System.out.println("Reconocio "+yytext()+" menor"); return new Symbol(Simbolosjs.menor, yycolumn, yyline, yytext()); }
">"         { System.out.println("Reconocio "+yytext()+" mayor"); return new Symbol(Simbolosjs.mayor, yycolumn, yyline, yytext()); }
"<="         { System.out.println("Reconocio "+yytext()+" menig"); return new Symbol(Simbolosjs.menig, yycolumn, yyline, yytext()); }
">="         { System.out.println("Reconocio "+yytext()+" mayig"); return new Symbol(Simbolosjs.mayig, yycolumn, yyline, yytext()); }
"&&"         { System.out.println("Reconocio "+yytext()+" and"); return new Symbol(Simbolosjs.and, yycolumn, yyline, yytext()); }
"||"         { System.out.println("Reconocio "+yytext()+" or"); return new Symbol(Simbolosjs.or, yycolumn, yyline, yytext()); }
"!"         { System.out.println("Reconocio "+yytext()+" not"); return new Symbol(Simbolosjs.not, yycolumn, yyline, yytext()); }
"+"         { System.out.println("Reconocio "+yytext()+" mas"); return new Symbol(Simbolosjs.mas, yycolumn, yyline, yytext()); }
"-"         { System.out.println("Reconocio "+yytext()+" menos"); return new Symbol(Simbolosjs.menos, yycolumn, yyline, yytext()); }
"*"         { System.out.println("Reconocio "+yytext()+" por"); return new Symbol(Simbolosjs.por, yycolumn, yyline, yytext()); }
"/"         { System.out.println("Reconocio "+yytext()+" div"); return new Symbol(Simbolosjs.div, yycolumn, yyline, yytext()); }
"**"         { System.out.println("Reconocio "+yytext()+" pote"); return new Symbol(Simbolosjs.pote, yycolumn, yyline, yytext()); }
"%"         { System.out.println("Reconocio "+yytext()+" modu"); return new Symbol(Simbolosjs.modu, yycolumn, yyline, yytext()); }
":"         { System.out.println("Reconocio "+yytext()+" dosp"); return new Symbol(Simbolosjs.dosp, yycolumn, yyline, yytext()); }






//-----> Palabras reservadas

"class" { System.out.println("Reconocio "+yytext()+" clase"); return new Symbol(Simbolosjs.clase, yycolumn, yyline, yytext()); }
"var" { System.out.println("Reconocio "+yytext()+" vare"); return new Symbol(Simbolosjs.vare, yycolumn, yyline, yytext()); }
"let" { System.out.println("Reconocio "+yytext()+" lete"); return new Symbol(Simbolosjs.lete, yycolumn, yyline, yytext()); }
"const" { System.out.println("Reconocio "+yytext()+" conste"); return new Symbol(Simbolosjs.conste, yycolumn, yyline, yytext()); }
"if" { System.out.println("Reconocio "+yytext()+" ife"); return new Symbol(Simbolosjs.ife, yycolumn, yyline, yytext()); }
"else" { System.out.println("Reconocio "+yytext()+" elsi"); return new Symbol(Simbolosjs.elsi, yycolumn, yyline, yytext()); }
"for" { System.out.println("Reconocio "+yytext()+" fory"); return new Symbol(Simbolosjs.fory, yycolumn, yyline, yytext()); }
"while" { System.out.println("Reconocio "+yytext()+" whi"); return new Symbol(Simbolosjs.whi, yycolumn, yyline, yytext()); }
"do" { System.out.println("Reconocio "+yytext()+" du"); return new Symbol(Simbolosjs.du, yycolumn, yyline, yytext()); }
"switch" { System.out.println("Reconocio "+yytext()+" swi"); return new Symbol(Simbolosjs.swi, yycolumn, yyline, yytext()); }
"console.log" { System.out.println("Reconocio "+yytext()+" consi"); return new Symbol(Simbolosjs.consi, yycolumn, yyline, yytext()); }
"break" { System.out.println("Reconocio "+yytext()+" bre"); return new Symbol(Simbolosjs.bre, yycolumn, yyline, yytext()); }
"require" { System.out.println("Reconocio "+yytext()+" req"); return new Symbol(Simbolosjs.req, yycolumn, yyline, yytext()); }
"case" { System.out.println("Reconocio "+yytext()+" keis"); return new Symbol(Simbolosjs.keis, yycolumn, yyline, yytext()); }
"default" { System.out.println("Reconocio "+yytext()+" defi"); return new Symbol(Simbolosjs.defi, yycolumn, yyline, yytext()); }




//-------> Simbolos ER
{cadena}    { System.out.println("Reconocio "+yytext()+" cadena"); return new Symbol(Simbolosjs.cadena, yycolumn, yyline, yytext()); }
{id}    { System.out.println("Reconocio "+yytext()+" id"); return new Symbol(Simbolosjs.id, yycolumn, yyline, yytext()); }
{decimal}    { System.out.println("Reconocio "+yytext()+" deci"); return new Symbol(Simbolosjs.deci, yycolumn, yyline, yytext()); }
{digito}    { System.out.println("Reconocio "+yytext()+" digito"); return new Symbol(Simbolosjs.digito, yycolumn, yyline, yytext()); }

//------> Espacios
{comentariosimple}      {System.out.println("Comentario Simple: "+yytext()); }
{comentarioMulti}       {System.out.println("Comentario Multiple: "+yytext()); }
[ \t\r\n\f]             {/* Espacios en blanco, se ignoran */}

//-------> Errores Lexicos 
.           {System.out.println("Error Lexico " + yytext() + "Linea: " + yyline + "Columna: " + yycolumn); }

