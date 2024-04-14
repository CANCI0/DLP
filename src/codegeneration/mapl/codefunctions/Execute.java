// Generated with VGen 2.0.0

package codegeneration.mapl.codefunctions;

import ast.AST;
import ast.Position;
import ast.definition.FunctionDefinition;
import ast.definition.StructDefinition;
import ast.definition.VarDefinition;
import ast.statement.*;
import codegeneration.mapl.*;


public class Execute extends AbstractCodeFunction {

    public Execute(MaplCodeSpecification specification) {
        super(specification);
    }
    
    @Override
    public Object visit(FunctionDefinition functionDefinition, Object param) {
    	execute(functionDefinition.getDefinitions().stream());
    	
    	out(functionDefinition.getName() + ":");
    	
    	//Return type size
    	int cte1 = 0;    	
    	if(functionDefinition.getType().isPresent()) {
    		cte1 = functionDefinition.getType().get().getSize();
    	}

    	//Local Variables size
    	int cte2 = functionDefinition.getDefinitions().stream()
			.mapToInt(varDef -> varDef.getType().getSize())
			.sum();
    	
    	//Function parameters size
    	int cte3 = functionDefinition.getParams().stream()
            .mapToInt(paramDef -> paramDef.getType().getSize())
            .sum();
    	
    	execute(functionDefinition.getStatements().stream());
    	
    	out("ret " + cte1 + "," + cte2 + "," + cte3);
    	
    	return null;
    }
    
    @Override
    public Object visit(StructDefinition structDefinition, Object param) {
    	 	
    	return null;
    }
    
    @Override
    public Object visit(VarDefinition varDefinition, Object param) {
    	
    	return null;
    }

	// class Read(Expression expression)
	@Override
	public Object visit(Read read, Object param) {

		// value(read.getExpression());
		// address(read.getExpression());

		out("<instruction>");

		return null;
	}

	// class Print(List<Expression> expressions)
	@Override
	public Object visit(Print print, Object param) {

		line(print);
		
		value(print.expressions());

		for(int i = 0 ; i < print.getExpressions().size() ; i++) {
			out("out");
		}
		
		return null;
	}

	// class Println(List<Expression> expressions)
	@Override
	public Object visit(Println println, Object param) {

		// value(println.expressions());
		// address(println.expressions());

		out("<instruction>");

		return null;
	}

	// class Printsp(List<Expression> expressions)
	@Override
	public Object visit(Printsp printsp, Object param) {

		// value(printsp.expressions());
		// address(printsp.expressions());

		out("<instruction>");

		return null;
	}

	// class Return(Optional<Expression> expression)
	@Override
	public Object visit(Return returnValue, Object param) {

		// value(returnValue.getExpression());
		// address(returnValue.getExpression());

		out("<instruction>");

		return null;
	}

	// class Assignment(Expression left, Expression right)
	@Override
	public Object visit(Assignment assignment, Object param) {

		line(assignment);

        address(assignment.getLeft());
        value(assignment.getRight());
        out("store", assignment.getLeft().getExpressionType());

		return null;
	}

	// class While(Expression expression, List<Statement> statements)
	@Override
	public Object visit(While whileValue, Object param) {

		// value(whileValue.getExpression());
		// address(whileValue.getExpression());

		// execute(whileValue.statements());

		out("<instruction>");

		return null;
	}

	// class Ifelse(Expression cond, List<Statement> tr, List<Statement> fs)
	@Override
	public Object visit(Ifelse ifelse, Object param) {

		// value(ifelse.getCond());
		// address(ifelse.getCond());

		// execute(ifelse.tr());

		// execute(ifelse.fs());

		out("<instruction>");

		return null;
	}

	// class FunctionCallStatement(String name, List<Expression> expressions)
	// phase Identification { FunctionDefinition functionDefinition }
	@Override
	public Object visit(FunctionCallStatement functionCallStatement, Object param) {

		// value(functionCallStatement.expressions());
		// address(functionCallStatement.expressions());

		out("<instruction>");

		return null;
	}
	
	// Auxiliary methods for the generation of code
    private void line(AST node) {
        line(node.end());
    }

    private void line(Position pos) {
        if (pos != null)
            out("\n#line " + pos.getLine());
        else
            System.out.println("#line no generado. Se ha pasado una Position null. Falta información en el AST");
    }

}
