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
	for: statement -> init:statement cond:expression update:statement statements:statement*
	statement -> 
*/
public class For extends AbstractStatement  {

    // ----------------------------------
    // Instance Variables

	// for: statement -> init:statement cond:expression update:statement statement*
	private Statement init;
	private Expression cond;
	private Statement update;
	private List<Statement> statements;

    // ----------------------------------
    // Constructors

	public For(Statement init, Expression cond, Statement update, List<Statement> statements) {
		super();

		if (init == null)
			throw new IllegalArgumentException("Parameter 'init' can't be null. Pass a non-null value or use 'statement?' in the abstract grammar");
		this.init = init;

		if (cond == null)
			throw new IllegalArgumentException("Parameter 'cond' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.cond = cond;

		if (update == null)
			throw new IllegalArgumentException("Parameter 'update' can't be null. Pass a non-null value or use 'statement?' in the abstract grammar");
		this.update = update;

		if (statements == null)
			statements = new ArrayList<>();
		this.statements = statements;

		updatePositions(init, cond, update, statements);
	}

	public For(Object init, Object cond, Object update, Object statements) {
		super();

        if (init == null)
            throw new IllegalArgumentException("Parameter 'init' can't be null. Pass a non-null value or use 'statement?' in the abstract grammar");
		this.init = (Statement) init;

        if (cond == null)
            throw new IllegalArgumentException("Parameter 'cond' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.cond = (Expression) cond;

        if (update == null)
            throw new IllegalArgumentException("Parameter 'update' can't be null. Pass a non-null value or use 'statement?' in the abstract grammar");
		this.update = (Statement) update;

        this.statements = castList(statements, unwrapIfContext.andThen(Statement.class::cast));
		updatePositions(init, cond, update, statements);
	}


    // ----------------------------------
    // for: statement -> init:statement cond:expression update:statement statement*

	// Child 'init:statement' 

	public void setInit(Statement init) {
		if (init == null)
			throw new IllegalArgumentException("Parameter 'init' can't be null. Pass a non-null value or use 'statement?' in the abstract grammar");
		this.init = init;

	}

    public Statement getInit() {
        return init;
    }


	// Child 'cond:expression' 

	public void setCond(Expression cond) {
		if (cond == null)
			throw new IllegalArgumentException("Parameter 'cond' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.cond = cond;

	}

    public Expression getCond() {
        return cond;
    }


	// Child 'update:statement' 

	public void setUpdate(Statement update) {
		if (update == null)
			throw new IllegalArgumentException("Parameter 'update' can't be null. Pass a non-null value or use 'statement?' in the abstract grammar");
		this.update = update;

	}

    public Statement getUpdate() {
        return update;
    }


	// Child 'statement*' 

	public void setStatements(List<Statement> statements) {
		if (statements == null)
			statements = new ArrayList<>();
		this.statements = statements;

	}

    public List<Statement> getStatements() {
        return statements;
    }

    public Stream<Statement> statements() {
        return statements.stream();
    }


    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "For{" + " init=" + this.getInit() + " cond=" + this.getCond() + " update=" + this.getUpdate() + " statements=" + this.getStatements() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
