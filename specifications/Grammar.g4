grammar Grammar;

import Lexer;

program
    : (def_type | function)* EOF
    ;

def_type
    : 'var' IDENT ':' type ';'
    | 'struct' IDENT '{' defs '}'
    ;

defs
	: def*
	;

def
    : IDENT ':' type ';'
    ;

function
    : IDENT '(' params ')' (':' type)? '{' block '}' 
    ;

params
    : (param (',' param)*)?  
	;

param
    : IDENT ':' type
	;

block
    :  defVars sentences 
    ;

defVars
	: defVar*
	;

defVar
    : 'var' IDENT ':' type ';'
    ;

sentences
	: sentence*
	;

sentence
    : 'read' expr ';'
	| 'print' expr? ';'
	| 'println' expr? ';'
	| 'printsp' expr? ';'
	| 'return' expr? ';'
	| invoke ';'?
    | expr '=' expr ';'
    | 'while' '(' expr ')' '{' block '}' 
	| 'if' '(' expr ')' '{' block '}' ('else' '{' block '}')?
    ;

expr
    : INT_LITERAL
    | REAL_LITERAL
    | CHAR_LITERAL
	| invoke
	| IDENT
	| expr '[' expr ']'
	| expr '.' IDENT
	| expr ('*' | '/') expr
	| expr ('+' | '-') expr
	| expr ('>=' | '<=' | '>' | '<') expr
	| expr ('==' | '!=' ) expr
	| expr ('&&') expr
	| expr ('||') expr
	| ('<' type '>')? '(' expr ')'
    ;

invoke 
	: IDENT '(' invokeParams ')'
	;

invokeParams
	: (expr (',' expr)*)?
	;

type
	: 'int'
	| 'float'
	| 'char'
	| '[' INT_LITERAL ']' type
	| IDENT
	;
