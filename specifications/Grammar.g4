grammar Grammar;

import Lexer;

program
    : (definicion | funcion)* EOF
    ;

definicion
    : 'var' IDENT ':' tipo ';'
    | 'struct' IDENT '{' (definicion_tipo ';')* '}'
    ;

definicion_tipo
    : IDENT ':' tipo
    ;

funcion
    : IDENT '(' parametros? ')' (':' tipo)? bloque
    ;

parametros
    : definicion_tipo (',' definicion_tipo)*
    ;

defVars
	: defVar*
	;

defVar
    : 'var' IDENT ':' tipo ';'
    ;

bloque
    : '{' defVars sentencias '}'
    ;

sentencias
	: sentencia*
	;

sentencia
    : 'read' defAccess ';'
	| 'print' expr? ';'
	| 'println' expr? ';'
	| 'printsp' expr? ';'
	| 'return' expr? ';'
	| invoke ';'?
    | asign
    | 'while' '(' expr ')' bloque
	| 'if' '(' expr ')' bloque ('else' bloque)?
    ;

asign
	: defAccess '=' expr ';'
	;

defAccess
	: IDENT
	| defAccess '[' expr ']'
	| defAccess '.' IDENT
	;

expr
    : literal
    | defAccess
	| invoke
	| expr ('*' | '/') expr
	| expr ('+' | '-') expr
	| expr ('>=' | '<=' | '>' | '<') expr
	| expr ('==' | '!=' ) expr
	| expr ('&&') expr
	| expr ('||') expr
	| cast? '(' expr ')'
    ;

invoke 
	: IDENT '(' invokeParams? ')'
	;

invokeParams
	: expr (',' expr)*
	;

cast
	: '<' tipo '>'
	;

literal
    : INT_LITERAL
    | REAL_LITERAL
    | CHAR_LITERAL
    ;

tipo
	: 'int'
	| 'float'
	| 'char'
	| '[' INT_LITERAL ']' tipo
	| IDENT
	;
