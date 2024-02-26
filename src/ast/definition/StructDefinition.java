// Generated with VGen 2.0.0

package ast.definition;

import ast.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.antlr.v4.runtime.Token;
import visitor.Visitor;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------

/*
	structDefinition: definition -> name:string attrDefinitions:attrDefinition*
	definition -> 
*/
public class StructDefinition extends AbstractDefinition  {

    // ----------------------------------
    // Instance Variables

	// structDefinition: definition -> string attrDefinition*
	private String name;
	private List<AttrDefinition> attrDefinitions;

    // ----------------------------------
    // Constructors

	public StructDefinition(String name, List<AttrDefinition> attrDefinitions) {
		super();

		if (name == null)
			throw new IllegalArgumentException("Parameter 'name' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.name = name;

		if (attrDefinitions == null)
			attrDefinitions = new ArrayList<>();
		this.attrDefinitions = attrDefinitions;

		updatePositions(name, attrDefinitions);
	}

	public StructDefinition(Object name, Object attrDefinitions) {
		super();

        if (name == null)
            throw new IllegalArgumentException("Parameter 'name' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.name = (name instanceof Token) ? ((Token) name).getText() : (String) name;

        this.attrDefinitions = castList(attrDefinitions, unwrapIfContext.andThen(AttrDefinition.class::cast));
		updatePositions(name, attrDefinitions);
	}


    // ----------------------------------
    // structDefinition: definition -> string attrDefinition*

	// Child 'string' 

	public void setName(String name) {
		if (name == null)
			throw new IllegalArgumentException("Parameter 'name' can't be null. Pass a non-null value or use 'string?' in the abstract grammar");
		this.name = name;

	}

    public String getName() {
        return name;
    }


	// Child 'attrDefinition*' 

	public void setAttrDefinitions(List<AttrDefinition> attrDefinitions) {
		if (attrDefinitions == null)
			attrDefinitions = new ArrayList<>();
		this.attrDefinitions = attrDefinitions;

	}

    public List<AttrDefinition> getAttrDefinitions() {
        return attrDefinitions;
    }

    public Stream<AttrDefinition> attrDefinitions() {
        return attrDefinitions.stream();
    }


    // ----------------------------------
    // Helper methods

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "StructDefinition{" + " name=" + this.getName() + " attrDefinitions=" + this.getAttrDefinitions() + "}";
    }


    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
}
