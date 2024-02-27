// Generated with VGen 2.0.0

package ast.statement;

import ast.expression.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import visitor.Visitor;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	if: statement -> expression:expression tr:statement*
	statement -> 
*/
public class If extends AbstractStatement  {

    // ----------------------------------
    // Instance Variables

	// if: statement -> expression tr:statement*
	private Expression expression;
	private List<Statement> tr;

    // ----------------------------------
    // Constructors

	public If(Expression expression, List<Statement> tr) {
		super();

		if (expression == null)
			throw new IllegalArgumentException("Parameter 'expression' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.expression = expression;

		if (tr == null)
			tr = new ArrayList<>();
		this.tr = tr;

		updatePositions(expression, tr);
	}

	public If(Object expression, Object tr) {
		super();

        if (expression == null)
            throw new IllegalArgumentException("Parameter 'expression' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.expression = (Expression) expression;

        this.tr = castList(tr, unwrapIfContext.andThen(Statement.class::cast));
		updatePositions(expression, tr);
	}


    // ----------------------------------
    // if: statement -> expression tr:statement*

	// Child 'expression' 

	public void setExpression(Expression expression) {
		if (expression == null)
			throw new IllegalArgumentException("Parameter 'expression' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.expression = expression;

	}

    public Expression getExpression() {
        return expression;
    }


	// Child 'tr:statement*' 

	public void setTr(List<Statement> tr) {
		if (tr == null)
			tr = new ArrayList<>();
		this.tr = tr;

	}

    public List<Statement> getTr() {
        return tr;
    }

    public Stream<Statement> tr() {
        return tr.stream();
    }


    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "If{" + " expression=" + this.getExpression() + " tr=" + this.getTr() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
