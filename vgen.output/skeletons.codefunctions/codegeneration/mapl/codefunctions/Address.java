// Generated with VGen 2.0.0

package codegeneration.mapl.codefunctions;

import ast.expression.*;
import codegeneration.mapl.*;


public class Address extends AbstractCodeFunction {

    public Address(MaplCodeSpecification specification) {
        super(specification);
    }


	// class IntLiteral(int intValue)
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(IntLiteral intLiteral, Object param) {

		out("<instruction>");

		return null;
	}

	// class FloatLiteral(float floatValue)
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(FloatLiteral floatLiteral, Object param) {

		out("<instruction>");

		return null;
	}

	// class StringLiteral(String name)
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(StringLiteral stringLiteral, Object param) {

		out("<instruction>");

		return null;
	}

	// class CharLiteral(String name)
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(CharLiteral charLiteral, Object param) {

		out("<instruction>");

		return null;
	}

	// class ArrayAccess(Expression expr1, Expression expr2)
	// phase Identification { VarDefinition varDefinition }
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(ArrayAccess arrayAccess, Object param) {

		// value(arrayAccess.getExpr1());
		// address(arrayAccess.getExpr1());

		// value(arrayAccess.getExpr2());
		// address(arrayAccess.getExpr2());

		out("<instruction>");

		return null;
	}

	// class FieldAccess(Expression expr, String name)
	// phase Identification { AttrDefinition attrDefinition }
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(FieldAccess fieldAccess, Object param) {

		// value(fieldAccess.getExpr());
		// address(fieldAccess.getExpr());

		out("<instruction>");

		return null;
	}

	// class Not(Expression expression)
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(Not not, Object param) {

		// value(not.getExpression());
		// address(not.getExpression());

		out("<instruction>");

		return null;
	}

	// class Logic(Expression left, String operator, Expression right)
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(Logic logic, Object param) {

		// value(logic.getLeft());
		// address(logic.getLeft());

		// value(logic.getRight());
		// address(logic.getRight());

		out("<instruction>");

		return null;
	}

	// class Arithmetic(Expression left, String operator, Expression right)
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(Arithmetic arithmetic, Object param) {

		// value(arithmetic.getLeft());
		// address(arithmetic.getLeft());

		// value(arithmetic.getRight());
		// address(arithmetic.getRight());

		out("<instruction>");

		return null;
	}

	// class Variable(String name)
	// phase Identification { VarDefinition varDefinition }
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(Variable variable, Object param) {

		out("<instruction>");

		return null;
	}

	// class Cast(Type type, Expression expression)
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(Cast cast, Object param) {

		// value(cast.getExpression());
		// address(cast.getExpression());

		out("<instruction>");

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
