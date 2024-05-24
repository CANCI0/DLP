// Generated with VGen 2.0.0

package ast.statement;

import ast.expression.*;
import ast.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import visitor.Visitor;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	switch: statement -> expression:expression cases:case*
	statement -> 
*/
public class Switch extends AbstractStatement  {

    // ----------------------------------
    // Instance Variables

	// switch: statement -> expression case*
	private Expression expression;
	private List<Case> cases;

    // ----------------------------------
    // Constructors

	public Switch(Expression expression, List<Case> cases) {
		super();

		if (expression == null)
			throw new IllegalArgumentException("Parameter 'expression' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.expression = expression;

		if (cases == null)
			cases = new ArrayList<>();
		this.cases = cases;

		updatePositions(expression, cases);
	}

	public Switch(Object expression, Object cases) {
		super();

        if (expression == null)
            throw new IllegalArgumentException("Parameter 'expression' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.expression = (Expression) expression;

        this.cases = castList(cases, unwrapIfContext.andThen(Case.class::cast));
		updatePositions(expression, cases);
	}


    // ----------------------------------
    // switch: statement -> expression case*

	// Child 'expression' 

	public void setExpression(Expression expression) {
		if (expression == null)
			throw new IllegalArgumentException("Parameter 'expression' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.expression = expression;

	}

    public Expression getExpression() {
        return expression;
    }


	// Child 'case*' 

	public void setCases(List<Case> cases) {
		if (cases == null)
			cases = new ArrayList<>();
		this.cases = cases;

	}

    public List<Case> getCases() {
        return cases;
    }

    public Stream<Case> cases() {
        return cases.stream();
    }


    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "Switch{" + " expression=" + this.getExpression() + " cases=" + this.getCases() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
