// Generated with VGen 2.0.0

grammar Grammar;

@header {
	    import ast.expression.*;
	    import ast.statement.*;
	    import ast.definition.*;
	    import ast.type.*;
	    import ast.*;
}

program returns[Program ast]
    : definitions+=definition*            { $ast = new Program($definitions); }                  
	;

definition returns[Definition ast]
    : name=IDENT type                     { $ast = new VarDefinition($name, $type.ast); }        
    | name=IDENT attrDefinitions+=attrDefinition* { $ast = new StructDefinition($name, $attrDefinitions); }
    | name=IDENT params+=varDefinition* type? definitions+=varDefinition* statements+=statement* { $ast = new FunctionDefinition($name, $params, ($type.ctx == null) ? null : $type.ast, $definitions, $statements); }
	;

type returns[Type ast]
    :                                     { $ast = new IntType(); }                              
    |                                     { $ast = new FloatType(); }                            
    |                                     { $ast = new CharType(); }                             
    | INT_LITERAL type                    { $ast = new ArrayType($INT_LITERAL, $type.ast); }     
    | name=IDENT                          { $ast = new StructType($name); }                      
    |                                     { $ast = new VoidType(); }                             
    | msg=IDENT                           { $ast = new ErrorType($msg); }                        
	;

attrDefinition returns[AttrDefinition ast]
    : name=IDENT type                     { $ast = new AttrDefinition($name, $type.ast); }       
	;

varDefinition returns[VarDefinition ast]
    : name=IDENT type                     { $ast = new VarDefinition($name, $type.ast); }        
	;

statement returns[Statement ast]
    : expression                          { $ast = new Read($expression.ast); }                  
    | expressions+=expression*            { $ast = new Print($expressions); }                    
    | expressions+=expression*            { $ast = new Println($expressions); }                  
    | expressions+=expression*            { $ast = new Printsp($expressions); }                  
    | expression?                         { $ast = new Return(($expression.ctx == null) ? null : $expression.ast); }
    | left=expression right=expression    { $ast = new Assignment($left.ast, $right.ast); }      
    | expression statements+=statement*   { $ast = new While($expression.ast, $statements); }    
    | expression tr+=statement* fs+=statement* { $ast = new Ifelse($expression.ast, $tr, $fs); }      
    | name=IDENT expressions+=expression* { $ast = new FunctionCallStatement($name, $expressions); }
	;

expression returns[Expression ast]
    : INT_LITERAL                         { $ast = new IntLiteral($INT_LITERAL); }               
    | FLOAT_LITERAL                       { $ast = new FloatLiteral($FLOAT_LITERAL); }           
    | name=IDENT                          { $ast = new CharLiteral($name); }                     
    | expr1=expression expr2=expression   { $ast = new ArrayAccess($expr1.ast, $expr2.ast); }    
    | expression name=IDENT               { $ast = new FieldAccess($expression.ast, $name); }    
    | expression                          { $ast = new Not($expression.ast); }                   
    | left=expression operator=IDENT right=expression { $ast = new Logic($left.ast, $operator, $right.ast); }
    | left=expression operator=IDENT right=expression { $ast = new Arithmetic($left.ast, $operator, $right.ast); }
    | name=IDENT                          { $ast = new Variable($name); }                        
    | type expression                     { $ast = new Cast($type.ast, $expression.ast); }       
    | name=IDENT expressions+=expression* { $ast = new FunctionCallExpression($name, $expressions); }
	;


// ---------------------------------------------------------------
// Tokens

IDENT: [a-zA-Z_][a-zA-Z0-9_]*;
FLOAT_LITERAL: [0-9]+ '.' [0-9]+;
INT_LITERAL: [0-9]+;
