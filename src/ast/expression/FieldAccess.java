// Generated with VGen 2.0.0

package ast.expression;

import ast.definition.*;
import org.antlr.v4.runtime.Token;
import visitor.Visitor;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	fieldAccess: expression -> expr:expression name:string
	expression -> 
	
	PHASE Identification
	fieldAccess -> structDefinition:structDefinition
*/
public class FieldAccess extends AbstractExpression  {

    // ----------------------------------
    // Instance Variables

	// fieldAccess: expression -> expr:expression string
	private Expression expr;
	private String name;

    // PHASE Identification
	private StructDefinition structDefinition;

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

	// Attribute 'structDefinition' 

	public void setStructDefinition(StructDefinition structDefinition) {
		if (structDefinition == null)
			throw new IllegalArgumentException("Parameter 'structDefinition' can't be null. Pass a non-null value or use 'structDefinition?' in the abstract grammar");
		this.structDefinition = structDefinition;

	}

    public StructDefinition getStructDefinition() {
        return structDefinition;
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
