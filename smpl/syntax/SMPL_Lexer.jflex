/* Specification for ArithExp tokens */

// user customisations
package smpl.syntax;

import java_cup.runtime.*;
import java.io.IOException;
import smpl.sys.TokenException;

// JFlex directives
%%
    
%cup
%public

%class SmplLexer
%throws TokenException

%type java_cup.runtime.Symbol

%eofval{
	return new Symbol(sym.EOF);
%eofval}

%eofclose false

%char
%line

%{
    public int getChar() {
	return yychar + 1;
    }

    public int getColumn() {
    	return yycolumn + 1;
    }

    public int getLine() {
	return yyline + 1;
    }

    public String getText() {
	return yytext();
    }
%}


nl = [\n\r]

cc = [\b\f]|{nl}

ws = {cc}|[\t ]

num = [0-9]

alpha = [a-zA-Z_]

alphanum = {alpha}|{num}

%%

<YYINITIAL>	{ws}	{/* ignore whitespace */}
<YYINITIAL>	{nl}	{
                        //skip newline, but reset char counter
			yychar = 0;
			}
<YYINITIAL>	"+"	{return new Symbol(sym.PLUS);}
<YYINITIAL>	"-"	{return new Symbol(sym.MINUS);}
<YYINITIAL>	"*"	{return new Symbol(sym.MUL);}
<YYINITIAL>	"/"	{return new Symbol(sym.DIV);}
<YYINITIAL>	"%"	{return new Symbol(sym.MOD);}
<YYINITIAL>	":="	{return new Symbol(sym.ASSIGN);}
<YYINITIAL>	"def"	{return new Symbol(sym.FUNDEF);}
<YYINITIAL>	"proc" {return new Symbol(sym.PROC);}

<YYINITIAL>	"("	{return new Symbol(sym.LPAREN);}
<YYINITIAL>	")"	{return new Symbol(sym.RPAREN);}

<YYINITIAL>	","	{return new Symbol(sym.COMMA);}
<YYINITIAL>	";"	{return new Symbol(sym.SEMI);}

<YYINITIAL>	"let"	{return new Symbol(sym.LET);}
<YYINITIAL>	"if"	{return new Symbol(sym.IF);}
<YYINITIAL>	"else" {return new Symbol(sym.ELSE);}
<YYINITIAL>	"then" {return new Symbol(sym.THEN);}
<YYINITIAL>	"call" {return new Symbol(sym.CALL);}
<YYINITIAL>	"lazy" {return new Symbol(sym.LAZY);}

<YYINITIAL>	"}"	{return new Symbol(sym.RBRACE);}
<YYINITIAL>	"{"	{return new Symbol(sym.LBRACE);}

<YYINITIAL>	"=="	{return new Symbol(sym.EQ);}
<YYINITIAL>	"<"	{return new Symbol(sym.LT);}
<YYINITIAL>	">"	{return new Symbol(sym.GT);}
<YYINITIAL>	"<="	{return new Symbol(sym.LE);}
<YYINITIAL>	">="	{return new Symbol(sym.GE);}

<YYINITIAL>    {num}+ {
	       // INTEGER
	       return new Symbol(sym.INTEGER, 
				 new Integer(yytext()));
	       }

<YYINITIAL>    {alpha}{alphanum}* {
	       // VARIABLE
	       return new Symbol(sym.VARIABLE, yytext());
	       }
	       
<YYINITIAL>    .        {
               // get here only if symbol has no matching rule
               // DO NOT add any rules below this one
               throw new TokenException(yytext());
               }

