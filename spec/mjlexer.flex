package rs.ac.bg.etf.pp1;
import org.apache.log4j.*;
import java_cup.runtime.*;
%%

%{
	private Logger log = Logger.getLogger(getClass());

	public int getLine() {
		return yyline;
	}
	
	public int getColumnn() {
		return yycolumn;
	}
	
	public Symbol getSymbol(int type, Object value){
		return new Symbol(type, getLine() + 1, getColumnn() + 1, value);
	}
%}

%function next_token
%type java_cup.runtime.Symbol

%state COMMENT

// na kraju fajla vrati EOF
%eofval{
return new Symbol(sym.EOF);
%eofval}

%class MJLexer
%cup
%line
%column

%%
<COMMENT> {
 "\r\n" {yybegin(YYINITIAL);}
 . {yybegin(COMMENT);}
}
<YYINITIAL> {
" " {}
"\b" {}
"\t" {}
"\r\n" {}
"\f" {}
"true" | "false" {return getSymbol (sym.BOOL_CONST, new Boolean(yytext()));}
";" {return getSymbol(sym.SEMICOLON, yytext());}
"=" {return getSymbol(sym.ASSIGNMENT, yytext());}
"++" {return getSymbol(sym.INCREMENT, yytext());}
"--" {return getSymbol(sym.DECREMENT, yytext());}
"(" {return getSymbol(sym.LEFT_PARENTHESIS, yytext());}
")" {return getSymbol(sym.RIGHT_PARENTHESIS, yytext());}
"read" {return getSymbol(sym.READ, yytext());}
"print" {return getSymbol(sym.PRINT, yytext());}
"," {return getSymbol(sym.COMMA, yytext());}
"new" {return getSymbol(sym.NEW, yytext());}
">" {return getSymbol(sym.GREATER, yytext());}
"==" {return getSymbol(sym.EQUAL, yytext());}
"<" {return getSymbol(sym.LESSER, yytext());}
">=" {return getSymbol(sym.GREATER_EQUAL, yytext());}
"<=" {return getSymbol(sym.LESSER_EQUAL, yytext());}
"!=" {return getSymbol(sym.NOT_EQUAL, yytext());}
"+" {return getSymbol(sym.PLUS, yytext());}
"-" {return getSymbol(sym.MINUS, yytext());}
"*" {return getSymbol(sym.MULTIPLICATION, yytext());}
"/" {return getSymbol(sym.DIVISION, yytext());}
"%" {return getSymbol(sym.MODULO, yytext());}
"[" {return getSymbol(sym.LEFT_SQUARE, yytext());}
"]" {return getSymbol(sym.RIGHT_SQUARE, yytext());}
"if" {return getSymbol(sym.IF, yytext());}
"else" {return getSymbol(sym.ELSE, yytext());}
"do" {return getSymbol(sym.DO, yytext());}
"while" {return getSymbol(sym.WHILE, yytext());}
"break" {return getSymbol(sym.BREAK, yytext());}
"continue" {return getSymbol(sym.CONTINUE, yytext());}
"program" { return getSymbol(sym.PROGRAM, yytext());}
"const" { return getSymbol(sym.CONST, yytext());}
"void" { return getSymbol(sym.VOID, yytext());}
"return" {return getSymbol(sym.RETURN, yytext());}
"{" {return getSymbol(sym.LEFT_BRACE, yytext());}
"}" {return getSymbol(sym.RIGHT_BRACE, yytext());}
"||" {return getSymbol(sym.OR, yytext());}
"&&" {return getSymbol(sym.AND, yytext());}
"//" {yybegin(COMMENT);}
"~"|"!"|"?"|"^"|"$"|"#"|"&"|"|"|([0-9]+[a-z|A-Z|_])|"`"|":"|"@"|"\\" {log.error("Leksicka greska za simbol \"" + yytext() + "\" na liniji " + (getLine() + 1) + ", koloni " + (getColumnn() + 1) + "!");}
([a-z]|[A-Z])[a-z|A-Z|0-9|_]* {return getSymbol(sym.IDENT, yytext());}
[0-9]+ {return getSymbol(sym.NUM_CONST, new Integer(yytext()));}
"'"[\040-\176]"'" {return getSymbol(sym.CHAR_CONST, new Character(yytext().charAt(1)));}
}