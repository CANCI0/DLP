// Generated with VGen 2.0.0

package codegeneration.mapl.codefunctions;

import ast.expression.*;
import codegeneration.mapl.*;


public class Address extends AbstractCodeFunction {

    public Address(MaplCodeSpecification specification) {
        super(specification);
    }

	// class ArrayAccess(Expression expr1, Expression expr2)
	// phase Identification { VarDefinition varDefinition }
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(ArrayAccess arrayAccess, Object param) {

		// value(arrayAccess.getExpr1());
		address(arrayAccess.getExpr1());

		// value(arrayAccess.getExpr2());
		value(arrayAccess.getExpr2());

		out("pushi " + arrayAccess.getExpressionType().getSize());
		out("muli");
		out("addi");

		return null;
	}

	// class FieldAccess(Expression expr, String name)
	// phase Identification { AttrDefinition attrDefinition }
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(FieldAccess fieldAccess, Object param) {

		// value(fieldAccess.getExpr());
		address(fieldAccess.getExpr());

		out("pushi " + fieldAccess.getAttrDefinition().getAddress());
		out("addi");

		return null;
	}

	// class Variable(String name)
	// phase Identification { VarDefinition varDefinition }
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(Variable variable, Object param) {
		
		if(variable.getVarDefinition().getScope() == 1) {
			out("push " + variable.getVarDefinition().getAddress());
		} else {
			out("push bp");
			out("push " + variable.getVarDefinition().getAddress());
			out("addi");
		}
		
		return null;
	}
	
	@Override 
	public Object visit(AssignmentExpression assignment, Object param) {
		
		address(assignment.getRight());
		
		return null;
	}
	
	@Override
	public Object visit(IntLiteral variable, Object param) {
	
		return null;
	}

}
