// Generated with VGen 2.0.0

package codegeneration.mapl.codefunctions;

import ast.statement.*;
import codegeneration.mapl.*;


public class Execute extends AbstractCodeFunction {

    public Execute(MaplCodeSpecification specification) {
        super(specification);
    }


	// class Read(Expression expression)
	@Override
	public Object visit(Read read, Object param) {

		// value(read.getExpression());
		// address(read.getExpression());

		out("<instruction>");

		return null;
	}

	// class Print(List<Expression> expressions)
	@Override
	public Object visit(Print print, Object param) {

		// value(print.expressions());
		// address(print.expressions());

		out("<instruction>");

		return null;
	}

	// class Println(List<Expression> expressions)
	@Override
	public Object visit(Println println, Object param) {

		// value(println.expressions());
		// address(println.expressions());

		out("<instruction>");

		return null;
	}

	// class Printsp(List<Expression> expressions)
	@Override
	public Object visit(Printsp printsp, Object param) {

		// value(printsp.expressions());
		// address(printsp.expressions());

		out("<instruction>");

		return null;
	}

	// class Return(Optional<Expression> expression)
	@Override
	public Object visit(Return returnValue, Object param) {

		// value(returnValue.getExpression());
		// address(returnValue.getExpression());

		out("<instruction>");

		return null;
	}

	// class Assignment(Expression left, Expression right)
	@Override
	public Object visit(Assignment assignment, Object param) {

		// value(assignment.getLeft());
		// address(assignment.getLeft());

		// value(assignment.getRight());
		// address(assignment.getRight());

		out("<instruction>");

		return null;
	}

	// class SumAssignment(Expression left, Expression right)
	@Override
	public Object visit(SumAssignment sumAssignment, Object param) {

		// value(sumAssignment.getLeft());
		// address(sumAssignment.getLeft());

		// value(sumAssignment.getRight());
		// address(sumAssignment.getRight());

		out("<instruction>");

		return null;
	}

	// class SubAssignment(Expression left, Expression right)
	@Override
	public Object visit(SubAssignment subAssignment, Object param) {

		// value(subAssignment.getLeft());
		// address(subAssignment.getLeft());

		// value(subAssignment.getRight());
		// address(subAssignment.getRight());

		out("<instruction>");

		return null;
	}

	// class Plus(Expression expression)
	@Override
	public Object visit(Plus plus, Object param) {

		// value(plus.getExpression());
		// address(plus.getExpression());

		out("<instruction>");

		return null;
	}

	// class While(Expression expression, List<Statement> statements)
	@Override
	public Object visit(While whileValue, Object param) {

		// value(whileValue.getExpression());
		// address(whileValue.getExpression());

		// execute(whileValue.statements());

		out("<instruction>");

		return null;
	}

	// class Ifelse(Expression cond, List<Statement> tr, List<Statement> fs)
	@Override
	public Object visit(Ifelse ifelse, Object param) {

		// value(ifelse.getCond());
		// address(ifelse.getCond());

		// execute(ifelse.tr());

		// execute(ifelse.fs());

		out("<instruction>");

		return null;
	}

	// class FunctionCallStatement(String name, List<Expression> expressions)
	// phase Identification { FunctionDefinition functionDefinition }
	@Override
	public Object visit(FunctionCallStatement functionCallStatement, Object param) {

		// value(functionCallStatement.expressions());
		// address(functionCallStatement.expressions());

		out("<instruction>");

		return null;
	}

}
