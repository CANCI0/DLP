lexer grammar Lexer;

MAS 
	: '+'	
	;

MENOS 
	: '-'
	;

POR 
	: '*'
	;

ENTRE 
	: '/'
	;

IGUAL 
	: '='
	;

DESIGUAL 
	: '!='
	;

P
	: '.'
	;

COMA
	: ','
	;
	
PCOMA 
	: ';'
	;

PP
	: ':'
	;

READ 
	: 'read'
	;

VAR
	: 'var'
	; 

PRINT 
	: 'printsp'
	;

INT 
	: 'int'
	;

FLOAT 
	: 'float'
	;

IF 
	: 'if'
	;

ELSE 
	: 'else'
	;

AND 
	: '&&'
	;

OR 
	: '||'
	;

C_START 
	: '['
	;

C_END
	: ']'
	;

P_START 
	: '('
	;

P_END
	: ')'
	;

L_START 
	: '{'
	;

L_END
	: '}'
	;

F_START 
	: '<'
	;

F_END
	: '>'
	;

INT_LITERAL
	: [0-9]+
	;

REAL_LITERAL 
	: [0-9]+'.'[0-9]+
	;

CHAR_LITERAL
	: '\'' ~[\r\n] '\''
	| ' \' \\n \' '
	;

IDENT 
	: [a-zA-Z][a-zA-Z0-9_]*
	;

LINE_COMMENT
	: '//' .*? ('\n' | EOF) -> skip
	;

MULTILINE_COMMENT
	: '/*' .*? '*/' -> skip
	;

WHITESPACE
	: [ \t\r\n]+ -> skip
	;
