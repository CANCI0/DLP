// Generated with VGen 2.0.0

package codegeneration.mapl.codefunctions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import ast.expression.*;
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

		out("pushb " + charLiteral);

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

		out(logicOperators.get(logic.getOperator()), logic.getExpressionType());

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

		address(variable);
		out("load", variable.getExpressionType());

		return null;
	}

	// class Cast(Type type, Expression expression)
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(Cast cast, Object param) {

		// value(cast.getExpression());
		// address(cast.getExpression());

		if(!Objects.equals(cast.getType().getClass(), cast.getExpressionType().getClass())) {
			out(suffixFor(cast.getExpressionType()) + "2" + suffixFor(cast.getType()));
		}
		
		return null;
	}

	// class FunctionCallExpression(String name, List<Expression> expressions)
	// phase Identification { FunctionDefinition functionDefinition }
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(FunctionCallExpression functionCallExpression, Object param) {

		// value(functionCallExpression.expressions());
		// address(functionCallExpression.expressions());

		out("<instruction>");

		return null;
	}

}
