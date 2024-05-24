// Generated with VGen 2.0.0

/**
* Este interfaz es el resultado de la aplicación del patrón Visitor a los nodos del AST.
*/

package visitor;

import ast.*;
import ast.definition.*;
import ast.statement.*;
import ast.expression.*;
import ast.type.*;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------


public interface Visitor {
	public Object visit(Program program, Object param);

	public Object visit(VarDefinition varDefinition, Object param);

	public Object visit(StructDefinition structDefinition, Object param);

	public Object visit(FunctionDefinition functionDefinition, Object param);

	public Object visit(AttrDefinition attrDefinition, Object param);

	public Object visit(Read read, Object param);

	public Object visit(Print print, Object param);

	public Object visit(Println println, Object param);

	public Object visit(Printsp printsp, Object param);

	public Object visit(Return returnValue, Object param);

	public Object visit(Assignment assignment, Object param);

	public Object visit(While whileValue, Object param);

	public Object visit(Ifelse ifelse, Object param);

	public Object visit(Switch switchValue, Object param);

	public Object visit(Case caseValue, Object param);

	public Object visit(FunctionCallStatement functionCallStatement, Object param);

	public Object visit(IntLiteral intLiteral, Object param);

	public Object visit(FloatLiteral floatLiteral, Object param);

	public Object visit(CharLiteral charLiteral, Object param);

	public Object visit(ArrayAccess arrayAccess, Object param);

	public Object visit(FieldAccess fieldAccess, Object param);

	public Object visit(Not not, Object param);

	public Object visit(Logic logic, Object param);

	public Object visit(Arithmetic arithmetic, Object param);

	public Object visit(Variable variable, Object param);

	public Object visit(Cast cast, Object param);

	public Object visit(FunctionCallExpression functionCallExpression, Object param);

	public Object visit(IntType intType, Object param);

	public Object visit(FloatType floatType, Object param);

	public Object visit(CharType charType, Object param);

	public Object visit(ArrayType arrayType, Object param);

	public Object visit(StructType structType, Object param);

	public Object visit(VoidType voidType, Object param);

	public Object visit(ErrorType errorType, Object param);

	Object visit();

    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
