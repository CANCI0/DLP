// Generated with VGen 2.0.0

package codegeneration.mapl.codefunctions;

import ast.*;
import codegeneration.mapl.*;


public class Run extends AbstractCodeFunction {

    public Run(MaplCodeSpecification specification) {
        super(specification);
    }


	// class Program(List<Definition> definitions)
	@Override
	public Object visit(Program program, Object param) {
		
		metadata(program);
        execute(program.getDefinitions().stream());
        out("halt");
        
		return null;
	}

}