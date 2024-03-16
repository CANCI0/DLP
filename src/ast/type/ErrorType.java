// Generated with VGen 2.0.0

package ast.type;

import org.antlr.v4.runtime.Token;
import visitor.Visitor;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------


/*
	errorType: type -> msg:string
	type -> 
*/
public class ErrorType extends AbstractType  {

    // ----------------------------------
    // Instance Variables

	// errorType: type -> msg:string
	private String msg;

    // ----------------------------------
    // Constructors

	public ErrorType() {
		
	}
	
	public ErrorType(String msg) {
		super();

		if (msg == null)
			throw new IllegalArgumentException("Parameter 'msg' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.msg = msg;

		updatePositions(msg);
	}

	public ErrorType(Object msg) {
		super();

        if (msg == null)
            throw new IllegalArgumentException("Parameter 'msg' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.msg = (msg instanceof Token) ? ((Token) msg).getText() : (String) msg;

		updatePositions(msg);
	}


    // ----------------------------------
    // errorType: type -> msg:string

	// Child 'msg:string' 

	public void setMsg(String msg) {
		if (msg == null)
			throw new IllegalArgumentException("Parameter 'msg' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.msg = msg;

	}

    public String getMsg() {
        return msg;
    }


    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "ErrorType{" + " msg=" + this.getMsg() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------

}
