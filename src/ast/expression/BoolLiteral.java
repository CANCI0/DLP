// Generated with VGen 2.0.0

package ast.expression;

import ast.type.*;
import org.antlr.v4.runtime.Token;
import visitor.Visitor;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	boolLiteral: expression -> booleanValue:boolean
	expression -> 
	
	PHASE TypeChecking
	expression -> expressionType:type
	expression -> lvalue:boolean
*/
public class BoolLiteral extends AbstractExpression  {

    // ----------------------------------
    // Instance Variables

	// boolLiteral: expression -> boolean
	private boolean booleanValue;

    // ----------------------------------
    // Constructors

	public BoolLiteral(boolean booleanValue) {
		super();

		this.booleanValue = booleanValue;

		updatePositions(booleanValue);
	}

	public BoolLiteral(Object booleanValue) {
		super();

        if (booleanValue == null)
            throw new IllegalArgumentException("Parameter 'booleanValue' can't be null. Pass a non-null value or use 'boolean?' in the abstract grammar");
        var booleanValue_temp = booleanValue;
        if (booleanValue_temp instanceof Token)
            booleanValue_temp = ((Token) booleanValue_temp).getText();
        if (booleanValue_temp instanceof String)
            booleanValue_temp = Boolean.valueOf((String) booleanValue_temp);
        this.booleanValue = (boolean) booleanValue_temp;

		updatePositions(booleanValue);
	}


    // ----------------------------------
    // boolLiteral: expression -> boolean

	// Child 'boolean' 

	public void setBooleanValue(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

    public boolean isBooleanValue() {
        return booleanValue;
    }


    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "BoolLiteral{" + " booleanValue=" + this.isBooleanValue() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
