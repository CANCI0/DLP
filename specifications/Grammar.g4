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
    : IDENT '(' parameters? ')' (':' type)? block
    ;

parameters
    : def_type (',' def_type)*
    ;

defVars
	: defVar*
	;

defVar
    : 'var' IDENT ':' type ';'
    ;

block
    : '{' defVars sentences '}'
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
    | asign
    | 'while' '(' expr ')' block
	| 'if' '(' expr ')' block ('else' block)?
    ;

asign
	: expr '=' expr ';'
	;

expr
    : literal
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
	| cast? '(' expr ')'
    ;

invoke 
	: IDENT '(' invokeParams? ')'
	;

invokeParams
	: expr (',' expr)*
	;

cast
	: '<' type '>'
	;

literal
    : INT_LITERAL
    | REAL_LITERAL
    | CHAR_LITERAL
    ;

type
	: 'int'
	| 'float'
	| 'char'
	| '[' INT_LITERAL ']' type
	| IDENT
	;
