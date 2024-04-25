// Generated with VGen 2.0.0

package ast.definition;

import ast.*;
import org.antlr.v4.runtime.Token;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	definition -> 
	
	PHASE MemoryAllocation
	definition -> address:int
*/
public interface Definition extends AST {




    // --------------------------------
    // PHASE MemoryAllocation

	// Attribute 'address:int' 

	public void setAddress(int address);
	public int getAddress();


    // %% User Members -------------------------

    // %% --------------------------------------
}
