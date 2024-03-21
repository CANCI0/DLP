// Generated with VGen 2.0.0

package ast.definition;

import ast.type.*;
import ast.statement.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.Optional;
import org.antlr.v4.runtime.Token;
import visitor.Visitor;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	functionDefinition: definition -> name:string params:varDefinition* type:type? definitions:varDefinition* statements:statement*
	definition -> 
*/
public class FunctionDefinition extends AbstractDefinition  {

    // ----------------------------------
    // Instance Variables

	// functionDefinition: definition -> string params:varDefinition* type? definitions:varDefinition* statement*
	private String name;
	private List<VarDefinition> params;
	private Optional<Type> type;
	private List<VarDefinition> definitions;
	private List<Statement> statements;

    // ----------------------------------
    // Constructors

	public FunctionDefinition(String name, List<VarDefinition> params, Optional<Type> type, List<VarDefinition> definitions, List<Statement> statements) {
		super();

		if (name == null)
			throw new IllegalArgumentException("Parameter 'name' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.name = name;

		if (params == null)
			params = new ArrayList<>();
		this.params = params;

		if (type == null)
			type = Optional.empty();
		this.type = type;

		if (definitions == null)
			definitions = new ArrayList<>();
		this.definitions = definitions;

		if (statements == null)
			statements = new ArrayList<>();
		this.statements = statements;

		updatePositions(name, params, type, definitions, statements);
	}

	public FunctionDefinition(Object name, Object params, Object type, Object definitions, Object statements) {
		super();

        if (name == null)
            throw new IllegalArgumentException("Parameter 'name' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.name = (name instanceof Token) ? ((Token) name).getText() : (String) name;

        this.params = castList(params, unwrapIfContext.andThen(VarDefinition.class::cast));
        this.type = castOptional(type, Type.class);
        this.definitions = castList(definitions, unwrapIfContext.andThen(VarDefinition.class::cast));
        this.statements = castList(statements, unwrapIfContext.andThen(Statement.class::cast));
		updatePositions(name, params, type, definitions, statements);
	}


    // ----------------------------------
    // functionDefinition: definition -> string params:varDefinition* type? definitions:varDefinition* statement*

	// Child 'string' 

	public void setName(String name) {
		if (name == null)
			throw new IllegalArgumentException("Parameter 'name' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.name = name;

	}

    public String getName() {
        return name;
    }


	// Child 'params:varDefinition*' 

	public void setParams(List<VarDefinition> params) {
		if (params == null)
			params = new ArrayList<>();
		this.params = params;

	}

    public List<VarDefinition> getParams() {
        return params;
    }

    public Stream<VarDefinition> params() {
        return params.stream();
    }


	// Child 'type?' 

	public void setType(Optional<Type> type) {
		if (type == null)
			type = Optional.empty();
		this.type = type;

	}

    public Optional<Type> getType() {
        return type;
    }


	// Child 'definitions:varDefinition*' 

	public void setDefinitions(List<VarDefinition> definitions) {
		if (definitions == null)
			definitions = new ArrayList<>();
		this.definitions = definitions;

	}

    public List<VarDefinition> getDefinitions() {
        return definitions;
    }

    public Stream<VarDefinition> definitions() {
        return definitions.stream();
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
        return "FunctionDefinition{" + " name=" + this.getName() + " params=" + this.getParams() + " type=" + this.getType() + " definitions=" + this.getDefinitions() + " statements=" + this.getStatements() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
