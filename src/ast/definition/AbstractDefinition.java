// Generated with VGen 2.0.0

package ast.definition;

import ast.*;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	definition -> 
	
	PHASE MemoryAllocation
	definition -> address:int
*/
public abstract class AbstractDefinition extends AbstractAST implements Definition {

    // ----------------------------------
    // Instance Variables


    // PHASE MemoryAllocation
	private int address;



    // --------------------------------
    // PHASE MemoryAllocation

	// Attribute 'address:int' 

	public void setAddress(int address) {
		this.address = address;

	}

    public int getAddress() {
        return address;
    }



    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
