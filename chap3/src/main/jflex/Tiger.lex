package com.example.chap3.Parse;

import com.example.chap3.ErrorMsg.ErrorMsg;

%%

%implements Lexer
%function nextToken
%type java_cup.runtime.Symbol
%char

%{
private void newline() {
  errorMsg.newline(yychar);
}

private void err(long pos, String s) {
  errorMsg.error(pos,s);
}

private void err(String s) {
  err(yychar,s);
}

private java_cup.runtime.Symbol tok(int kind, Object value) {
    return new java_cup.runtime.Symbol(kind, (int)yychar, (int)(yychar+yylength()), value);
}

private ErrorMsg errorMsg;

Yylex(java.io.Reader s, ErrorMsg e) {
  this(s);
  errorMsg=e;
}

%}

%eofval{
	{
	 return tok(sym.EOF, null);
        }
%eofval}


%%
" "	{}
\n	{newline();}
","	{return tok(sym.COMMA, null);}
"var"	{return tok(sym.VAR, null);}
"type"	{return tok(sym.TYPE, null);}
"function"	{return tok(sym.FUNCTION, null);}
"break"	{return tok(sym.BREAK, null);}
"of"	{return tok(sym.OF, null);}
"end"	{return tok(sym.END, null);}
"in"	{return tok(sym.IN, null);}
"nil"	{return tok(sym.NIL, null);}
"let"	{return tok(sym.LET, null);}
"do"	{return tok(sym.DO, null);}
"to"	{return tok(sym.TO, null);}
"for"	{return tok(sym.FOR, null);}
"while"	{return tok(sym.WHILE, null);}
"else"	{return tok(sym.ELSE, null);}
"then"	{return tok(sym.THEN, null);}
"if"	{return tok(sym.IF, null);}
"array"	{return tok(sym.ARRAY, null);}
":="	{return tok(sym.ASSIGN, null);}
"|"	{return tok(sym.OR, null);}
"&"	{return tok(sym.AND, null);}
">="	{return tok(sym.GE, null);}
">"	{return tok(sym.GT, null);}
"<="	{return tok(sym.LE, null);}
"<"	{return tok(sym.LT, null);}
"<>"	{return tok(sym.NEQ, null);}
"="	{return tok(sym.EQ, null);}
"/"	{return tok(sym.DIVIDE, null);}
"*"	{return tok(sym.TIMES, null);}
"-"	{return tok(sym.MINUS, null);}
"+"	{return tok(sym.PLUS, null);}
"."	{return tok(sym.DOT, null);}
"}"	{return tok(sym.RBRACE, null);}
"{"	{return tok(sym.LBRACE, null);}
"]"	{return tok(sym.RBRACK, null);}
"["	{return tok(sym.LBRACK, null);}
")"	{return tok(sym.RPAREN, null);}
"("	{return tok(sym.LPAREN, null);}
";"	{return tok(sym.SEMICOLON, null);}
":"	{return tok(sym.COLON, null);}
[a-zA-Z][a-zA-Z0-9_]*	{return tok(sym.ID, yytext());}
[0-9]+	{return tok(sym.INT, Integer.valueOf(yytext()));}
\"[^\"]*\"	{return tok(sym.STRING, yytext());}
.       {err("Illegal character: " + yytext());}
