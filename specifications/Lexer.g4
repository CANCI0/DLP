lexer grammar Lexer;

INT_LITERAL
	: [0-9]+
	;

REAL_LITERAL 
	: [0-9]+'.'[0-9]+
	;

CHAR_LITERAL
	: '\''~[\r\n]'\''
	| '\'\\n\''
	;

BOOL_LITERAL	
	: 'true'
	| 'false'
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
