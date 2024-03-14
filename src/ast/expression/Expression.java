// Generated with VGen 2.0.0

package ast.expression;

import ast.*;
import ast.type.*;
import org.antlr.v4.runtime.Token;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	expression -> 
	
	PHASE TypeChecking
	expression -> expressionType:type
	expression -> lvalue:boolean
*/
public interface Expression extends AST {




    // --------------------------------
    // PHASE TypeChecking

	// Attribute 'expressionType:type' 

	public void setExpressionType(Type expressionType);
	public Type getExpressionType();

	// Attribute 'lvalue:boolean' 

	public void setLvalue(boolean lvalue);
	public boolean isLvalue();


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
