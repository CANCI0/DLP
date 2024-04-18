// Generated with VGen 2.0.0

package codegeneration.mapl.codefunctions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import ast.AttrDefinition;
import ast.Program;
import ast.definition.FunctionDefinition;
import ast.definition.StructDefinition;
import ast.definition.VarDefinition;
import ast.expression.*;
import ast.statement.Assignment;
import ast.statement.FunctionCallStatement;
import ast.statement.Ifelse;
import ast.statement.Print;
import ast.statement.Println;
import ast.statement.Printsp;
import ast.statement.Read;
import ast.statement.Return;
import ast.statement.While;
import ast.type.ArrayType;
import ast.type.CharType;
import ast.type.ErrorType;
import ast.type.FloatType;
import ast.type.IntType;
import ast.type.StructType;
import ast.type.VoidType;
import codegeneration.mapl.*;

public class Value extends AbstractCodeFunction {

	public Map<String, String> logicOperators = new HashMap<String, String>();
	public Map<String, String> arithmeticOperators = new HashMap<String, String>();

	public Value(MaplCodeSpecification specification) {
		super(specification);

		logicOperators.put(">=", "ge");
		logicOperators.put("<=", "le");
		logicOperators.put(">", "gt");
		logicOperators.put("<", "lt");
		logicOperators.put("==", "eq");
		logicOperators.put("!=", "ne");
		logicOperators.put("&&", "and");
		logicOperators.put("||", "or");

		arithmeticOperators.put("+", "add");
		arithmeticOperators.put("-", "sub");
		arithmeticOperators.put("*", "mul");
		arithmeticOperators.put("/", "div");
	}

	// class IntLiteral(int intValue)
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(IntLiteral intLiteral, Object param) {

		out("pushi " + intLiteral.getIntValue());

		return null;
	}

	// class FloatLiteral(float floatValue)
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(FloatLiteral floatLiteral, Object param) {

		out("pushf " + floatLiteral.getFloatValue());

		return null;
	}

	// class CharLiteral(String name)
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(CharLiteral charLiteral, Object param) {

		int character = charLiteral.getName().charAt(1);
		
		out("pushb " + character);

		return null;
	}

	// class ArrayAccess(Expression expr1, Expression expr2)
	// phase Identification { VarDefinition varDefinition }
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(ArrayAccess arrayAccess, Object param) {

		address(arrayAccess);
		out("load", arrayAccess.getExpressionType());

		return null;
	}

	// class FieldAccess(Expression expr, String name)
	// phase Identification { AttrDefinition attrDefinition }
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(FieldAccess fieldAccess, Object param) {

		address(fieldAccess);
		out("load", fieldAccess.getAttrDefinition().getType());

		return null;
	}

	// class Not(Expression expression)
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(Not not, Object param) {
		value(not.getExpression());
		out("not");

		return null;
	}

	// class Logic(Expression left, String operator, Expression right)
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(Logic logic, Object param) {

		value(logic.getLeft());
		value(logic.getRight());
		
		String operator = logic.getOperator();

		if(operator.equals("&&") || operator.equals("||")){
			out(logicOperators.get(operator));
		} else {
			out(logicOperators.get(operator), logic.getExpressionType());
		}

		return null;
	}

	// class Arithmetic(Expression left, String operator, Expression right)
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(Arithmetic arithmetic, Object param) {

		value(arithmetic.getLeft());
		value(arithmetic.getRight());
		out(arithmeticOperators.get(arithmetic.getOperator()), arithmetic.getExpressionType());

		return null;
	}

	// class Variable(String name)
	// phase Identification { VarDefinition varDefinition }
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(Variable variable, Object param) {

		// Si es global la dirección es absoluta
		address(variable);
		out("load", variable.getExpressionType());

		return null;
	}

	// class Cast(Type type, Expression expression)
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(Cast cast, Object param) {

		value(cast.getExpression());

		if (!Objects.equals(cast.getType().getClass(), cast.getExpression().getExpressionType().getClass())) {
			out(suffixFor(cast.getExpression().getExpressionType()) + "2" + suffixFor(cast.getType()));
		}

		return null;
	}

	// class FunctionCallExpression(String name, List<Expression> expressions)
	// phase Identification { FunctionDefinition functionDefinition }
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(FunctionCallExpression functionCallExpression, Object param) {

		value(functionCallExpression.expressions());

		out("call " + functionCallExpression.getName());

		return null;
	}

	// class Program(List<Definition> definitions)
	@Override
	public Object visit(Program program, Object param) {

		return null;
	}

	// class VarDefinition(String name, Type type)
	// phase Identification { int scope }
	// phase MemoryAllocation { int address }
	@Override
	public Object visit(VarDefinition varDefinition, Object param) {

		return null;
	}

	// class StructDefinition(String name, List<AttrDefinition> attrDefinitions)
	// phase MemoryAllocation { int address }
	@Override
	public Object visit(StructDefinition structDefinition, Object param) {

		return null;
	}

	// class FunctionDefinition(String name, List<VarDefinition> params,
	// Optional<Type> type, List<VarDefinition> definitions, List<Statement>
	// statements)
	// phase MemoryAllocation { int address }
	@Override
	public Object visit(FunctionDefinition functionDefinition, Object param) {

		return null;
	}

	// class AttrDefinition(String name, Type type)
	// phase Identification { StructDefinition structDefinition }
	// phase MemoryAllocation { int address }
	@Override
	public Object visit(AttrDefinition attrDefinition, Object param) {

		return null;
	}

	// class Read(Expression expression)
	@Override
	public Object visit(Read read, Object param) {

		return null;
	}

	// class Print(List<Expression> expressions)
	@Override
	public Object visit(Print print, Object param) {

		return null;
	}

	// class Println(List<Expression> expressions)
	@Override
	public Object visit(Println println, Object param) {

		return null;
	}

	// class Printsp(List<Expression> expressions)
	@Override
	public Object visit(Printsp printsp, Object param) {

		return null;
	}

	// class Return(Optional<Expression> expression)
	@Override
	public Object visit(Return returnValue, Object param) {

		if (returnValue.getExpression().isPresent()) {
			value(returnValue.getExpression().get());
		}

		return null;
	}

	// class Assignment(Expression left, Expression right)
	@Override
	public Object visit(Assignment assignment, Object param) {

		return null;
	}

	// class While(Expression expression, List<Statement> statements)
	@Override
	public Object visit(While whileValue, Object param) {

		return null;
	}

	// class Ifelse(Expression cond, List<Statement> tr, List<Statement> fs)
	@Override
	public Object visit(Ifelse ifelse, Object param) {

		return null;
	}

	// class FunctionCallStatement(String name, List<Expression> expressions)
	// phase Identification { FunctionDefinition functionDefinition }
	@Override
	public Object visit(FunctionCallStatement functionCallStatement, Object param) {

		value(functionCallStatement.expressions());

		out("call " + functionCallStatement.getName());

		return null;
	}

	// class IntType()
	@Override
	public Object visit(IntType intType, Object param) {

		return null;
	}

	// class FloatType()
	@Override
	public Object visit(FloatType floatType, Object param) {

		return null;
	}

	// class CharType()
	@Override
	public Object visit(CharType charType, Object param) {

		return null;
	}

	// class ArrayType(int intValue, Type type)
	@Override
	public Object visit(ArrayType arrayType, Object param) {

		return null;
	}

	// class StructType(String name)
	// phase Identification { StructDefinition structDefinition }
	@Override
	public Object visit(StructType structType, Object param) {

		return null;
	}

	// class VoidType()
	@Override
	public Object visit(VoidType voidType, Object param) {

		return null;
	}

	// class ErrorType()
	@Override
	public Object visit(ErrorType errorType, Object param) {

		return null;
	}

}
