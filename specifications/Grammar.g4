grammar Grammar;

import Lexer;

program returns[Program ast]
    : definitions EOF 																		{ $ast = new Program($definitions.list); }
    ;

definitions	returns[List<Definition> list = new ArrayList<Definition>()]
	: (definition { $list.add($definition.ast); })*
	;

definition returns[Definition ast]
    : varDefinition																			{ $ast = $varDefinition.ast}
    | 'struct' IDENT '{' attrDefinitions '}'												{ $ast = new StructDefinition($IDENT, $attrDefinitions.list)}
	| IDENT '(' params ')' (':' type)? '{' varDefinitions statements '}' 					{ $ast = new FunctionDefinition($IDENT, $params.list, $type.ast?, $varDefinitions.list, $statements.list); }
    ;

attrDefinitions returns[List<AttrDefinition> list = new ArrayList<AttrDefinition>()]
	: (attrDefinition { $list.add($attrDefinition.ast); })*
	;

attrDefinition returns[AttrDefinition ast]
    : IDENT ':' type ';'																	{ $ast = new AttrDefinition($IDENT, $type.ast); }
    ;

params returns[List<Param> list = new ArrayList<Param>()]
    : (param { $list.add($param.ast); } (',' param { $list.add($param.ast); })*)?
	;

param returns[Param ast]
    : IDENT ':' type																		{ $ast = new Param($IDENT, $type.ast)}
	;

varDefinitions returns[List<VarDefinition> list = new ArrayList<VarDefinition>()]
	: (varDefinition { $list.add($varDefinition.ast); })*
	;

varDefinition returns[VarDefinition ast]
    : 'var' IDENT ':' type ';'																{ $ast = new VarDefinition($IDENT, $type.ast); }
    ;

statements returns[List<Statement> list = new ArrayList<Statement>()]
	: (statement { $list.add($statement.ast); })*
	;

statement returns[Statement ast]
    : 'read' expression ';'																	{ $ast = new Read($expression.ast); }
	| 'print' expression? ';'																{ $ast = new Print($expression.ctx != null ? $expression.ast : null); }
	| 'println' expression? ';'																{ $ast = new Println($expression.ctx != null ? $expression.ast : null); }
	| 'printsp' expression? ';'																{ $ast = new Printsp($expression.ctx != null ? $expression.ast : null); }
	| 'return' expression? ';'																{ $ast = new Return($expression.ctx != null ? $expression.ast : null); }
	| functionCall ';'?																		{ $ast = $functionCall.ast}	
    | left=expression '=' right=expression ';'												{ $ast = new Assignment($left.ast, $right.ast); }
    | 'while' '(' expression ')' '{' statements '}' 										{ $ast = new While($expression.ast, $statements.list); }
	| 'if' '(' expression ')' '{' tr=statements '}' ('else' '{' fs=statements '}')?			{ $ast = new If($expression.ast, $tr.list, $fs.list); }
    ;

expression returns[Expression ast]
    : INT_LITERAL																			{ $ast = new IntLiteral($INT_LITERAL); }
    | REAL_LITERAL																			{ $ast = new RealLiteral($REAL_LITERAL); }
    | CHAR_LITERAL																			{ $ast = new CharLiteral($CHAR_LITERAL); }
	| functionCall																			{ $ast = $functionCall.ast; }
	| IDENT																					{ $ast = new Variable($IDENT); }
	| expression1=expression '[' expression2=expression ']'									{ $ast = new ArrayAccess($expression1.ast, $expression2.ast); }
	| expression '.' IDENT																	{ $ast = new FieldAccess($expression.ast, $IDENT); }
	| '!' expression																		{ $ast = new Not($expression.ast); }
	| left=expression op=('*' | '/' | '%') right=expression									{ $ast = new Arithmetic($left.ast, $op, $right.ast); }
	| left=expression op=('+' | '-') right=expression										{ $ast = new Arithmetic($left.ast, $op, $right.ast); }
	| left=expression op=('>=' | '<=' | '>' | '<') right=expression							{ $ast = new Arithmetic($left.ast, $op, $right.ast); }
	| left=expression op=('==' | '!=' )right=expression										{ $ast = new Arithmetic($left.ast, $op, $right.ast); }
	| left=expression op='&&' right=expression												{ $ast = new Arithmetic($left.ast, $op, $right.ast); }
	| left=expression op='||' right=expression												{ $ast = new Arithmetic($left.ast, $op, $right.ast); }
	| '(' expression ')'																	{ $ast = $expression.ast; }
	| '<' type '>' '(' expression ')'														{ $ast = new Cast($type.ast, $expression.ast); }
    ;

functionCall returns[functionCall ast]
	: IDENT '(' functionCallParams ')'														{$ast = new functionCall($IDENT, $functionCallParams.list)}	
	;

functionCallParams returns[List<Expression> list = new ArrayList<Expression>()]
	: (expression { $list.add($expression.ast); } (',' expression { $list.add($expression.ast); })*)?							
	;

type returns[Type ast]
	: 'int'																					{ $ast = new IntType(); }
	| 'real'																				{ $ast = new RealType(); }
	| 'char'																				{ $ast = new CharType(); }
	| '[' INT_LITERAL ']' type																{ $ast = new ArrayType(); }
	| IDENT																					{ $ast = new IdentType(); }
	;
