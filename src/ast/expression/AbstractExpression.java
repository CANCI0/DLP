// Generated with VGen 2.0.0

package ast.expression;

import ast.*;
import ast.type.*;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	expression -> 
	
	PHASE TypeChecking
	expression -> expressionType:type
	expression -> lvalue:boolean
*/
public abstract class AbstractExpression extends AbstractAST implements Expression {

    // ----------------------------------
    // Instance Variables


    // PHASE TypeChecking
	private Type expressionType;
	private boolean lvalue;



    // --------------------------------
    // PHASE TypeChecking

	// Attribute 'expressionType:type' 

	public void setExpressionType(Type expressionType) {
		if (expressionType == null)
			throw new IllegalArgumentException("Parameter 'expressionType' can't be null. Pass a non-null value or use 'type?' in the abstract grammar");
		this.expressionType = expressionType;

	}

    public Type getExpressionType() {
        return expressionType;
    }


	// Attribute 'lvalue:boolean' 

	public void setLvalue(boolean lvalue) {
		this.lvalue = lvalue;

	}

    public boolean isLvalue() {
        return lvalue;
    }



    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
