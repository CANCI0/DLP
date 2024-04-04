// Generated with VGen 2.0.0

package ast.expression;

import ast.type.*;
import ast.*;
import org.antlr.v4.runtime.Token;
import visitor.Visitor;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	fieldAccess: expression -> expr:expression name:string
	expression -> 
	
	PHASE Identification
	fieldAccess -> attrDefinition:attrDefinition
	
	PHASE TypeChecking
	expression -> expressionType:type
	expression -> lvalue:boolean
*/
public class FieldAccess extends AbstractExpression  {

    // ----------------------------------
    // Instance Variables

	// fieldAccess: expression -> expr:expression string
	private Expression expr;
	private String name;

    // PHASE Identification
	private AttrDefinition attrDefinition;

    // ----------------------------------
    // Constructors

	public FieldAccess(Expression expr, String name) {
		super();

		if (expr == null)
			throw new IllegalArgumentException("Parameter 'expr' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.expr = expr;

		if (name == null)
			throw new IllegalArgumentException("Parameter 'name' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.name = name;

		updatePositions(expr, name);
	}

	public FieldAccess(Object expr, Object name) {
		super();

        if (expr == null)
            throw new IllegalArgumentException("Parameter 'expr' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.expr = (Expression) expr;

        if (name == null)
            throw new IllegalArgumentException("Parameter 'name' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.name = (name instanceof Token) ? ((Token) name).getText() : (String) name;

		updatePositions(expr, name);
	}


    // ----------------------------------
    // fieldAccess: expression -> expr:expression string

	// Child 'expr:expression' 

	public void setExpr(Expression expr) {
		if (expr == null)
			throw new IllegalArgumentException("Parameter 'expr' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.expr = expr;

	}

    public Expression getExpr() {
        return expr;
    }


	// Child 'string' 

	public void setName(String name) {
		if (name == null)
			throw new IllegalArgumentException("Parameter 'name' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.name = name;

	}

    public String getName() {
        return name;
    }



    // --------------------------------
    // PHASE Identification

	// Attribute 'attrDefinition' 

	public void setAttrDefinition(AttrDefinition attrDefinition) {
		if (attrDefinition == null)
			throw new IllegalArgumentException("Parameter 'attrDefinition' can't be null. Pass a non-null value or use 'attrDefinition?' in the abstract grammar");
		this.attrDefinition = attrDefinition;

	}

    public AttrDefinition getAttrDefinition() {
        return attrDefinition;
    }


    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "FieldAccess{" + " expr=" + this.getExpr() + " name=" + this.getName() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
