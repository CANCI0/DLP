// Generated with VGen 2.0.0

package ast.expression;

import ast.type.*;
import ast.definition.*;
import org.antlr.v4.runtime.Token;
import visitor.Visitor;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	arrayAccess: expression -> expr1:expression expr2:expression
	expression -> 
	
	PHASE Identification
	arrayAccess -> varDefinition:varDefinition
	
	PHASE TypeChecking
	expression -> expressionType:type
	expression -> lvalue:boolean
*/
public class ArrayAccess extends AbstractExpression  {

    // ----------------------------------
    // Instance Variables

	// arrayAccess: expression -> expr1:expression expr2:expression
	private Expression expr1;
	private Expression expr2;

    // PHASE Identification
	private VarDefinition varDefinition;

    // ----------------------------------
    // Constructors

	public ArrayAccess(Expression expr1, Expression expr2) {
		super();

		if (expr1 == null)
			throw new IllegalArgumentException("Parameter 'expr1' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.expr1 = expr1;

		if (expr2 == null)
			throw new IllegalArgumentException("Parameter 'expr2' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.expr2 = expr2;

		updatePositions(expr1, expr2);
	}

	public ArrayAccess(Object expr1, Object expr2) {
		super();

        if (expr1 == null)
            throw new IllegalArgumentException("Parameter 'expr1' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.expr1 = (Expression) expr1;

        if (expr2 == null)
            throw new IllegalArgumentException("Parameter 'expr2' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.expr2 = (Expression) expr2;

		updatePositions(expr1, expr2);
	}


    // ----------------------------------
    // arrayAccess: expression -> expr1:expression expr2:expression

	// Child 'expr1:expression' 

	public void setExpr1(Expression expr1) {
		if (expr1 == null)
			throw new IllegalArgumentException("Parameter 'expr1' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.expr1 = expr1;

	}

    public Expression getExpr1() {
        return expr1;
    }


	// Child 'expr2:expression' 

	public void setExpr2(Expression expr2) {
		if (expr2 == null)
			throw new IllegalArgumentException("Parameter 'expr2' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.expr2 = expr2;

	}

    public Expression getExpr2() {
        return expr2;
    }



    // --------------------------------
    // PHASE Identification

	// Attribute 'varDefinition' 

	public void setVarDefinition(VarDefinition varDefinition) {
		if (varDefinition == null)
			throw new IllegalArgumentException("Parameter 'varDefinition' can't be null. Pass a non-null value or use 'varDefinition?' in the abstract grammar");
		this.varDefinition = varDefinition;

	}

    public VarDefinition getVarDefinition() {
        return varDefinition;
    }


    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "ArrayAccess{" + " expr1=" + this.getExpr1() + " expr2=" + this.getExpr2() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
