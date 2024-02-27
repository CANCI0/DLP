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
	ifelse: statement -> cond:expression tr:statement* fs:statement*
	statement -> 
*/
public class Ifelse extends AbstractStatement  {

    // ----------------------------------
    // Instance Variables

	// ifelse: statement -> cond:expression tr:statement* fs:statement*
	private Expression cond;
	private List<Statement> tr;
	private List<Statement> fs;

    // ----------------------------------
    // Constructors

	public Ifelse(Expression cond, List<Statement> tr, List<Statement> fs) {
		super();

		if (cond == null)
			throw new IllegalArgumentException("Parameter 'cond' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.cond = cond;

		if (tr == null)
			tr = new ArrayList<>();
		this.tr = tr;

		if (fs == null)
			fs = new ArrayList<>();
		this.fs = fs;

		updatePositions(cond, tr, fs);
	}

	public Ifelse(Object cond, Object tr, Object fs) {
		super();

        if (cond == null)
            throw new IllegalArgumentException("Parameter 'cond' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.cond = (Expression) cond;

        this.tr = castList(tr, unwrapIfContext.andThen(Statement.class::cast));
        this.fs = castList(fs, unwrapIfContext.andThen(Statement.class::cast));
		updatePositions(cond, tr, fs);
	}


    // ----------------------------------
    // ifelse: statement -> cond:expression tr:statement* fs:statement*

	// Child 'cond:expression' 

	public void setCond(Expression cond) {
		if (cond == null)
			throw new IllegalArgumentException("Parameter 'cond' can't be null. Pass a non-null value or use 'expression?' in the abstract grammar");
		this.cond = cond;

	}

    public Expression getCond() {
        return cond;
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


	// Child 'fs:statement*' 

	public void setFs(List<Statement> fs) {
		if (fs == null)
			fs = new ArrayList<>();
		this.fs = fs;

	}

    public List<Statement> getFs() {
        return fs;
    }

    public Stream<Statement> fs() {
        return fs.stream();
    }


    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "Ifelse{" + " cond=" + this.getCond() + " tr=" + this.getTr() + " fs=" + this.getFs() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
