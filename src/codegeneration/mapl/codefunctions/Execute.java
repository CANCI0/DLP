// Generated with VGen 2.0.0

package codegeneration.mapl.codefunctions;

import ast.AST;
import ast.Position;
import ast.definition.FunctionDefinition;
import ast.definition.StructDefinition;
import ast.definition.VarDefinition;
import ast.expression.FunctionCallExpression;
import ast.statement.*;
import codegeneration.mapl.*;

public class Execute extends AbstractCodeFunction {
	
	private int actualLabel;
	
	public Execute(MaplCodeSpecification specification) {
		super(specification);
		
		actualLabel = 1;
	}

	@Override
	public Object visit(FunctionDefinition functionDefinition, Object param) {
		execute(functionDefinition.definitions());

		out(functionDefinition.getName() + ":");

		// Return type size
		int cte1 = 0;
		if (functionDefinition.getType().isPresent()) {
			cte1 = functionDefinition.getType().get().getSize();
		}

		// Local Variables size
		int cte2 = functionDefinition.definitions().mapToInt(varDef -> varDef.getType().getSize()).sum();

		// Function parameters size
		int cte3 = functionDefinition.params().mapToInt(paramDef -> paramDef.getType().getSize()).sum();

		out("enter " + cte2);
		
		int[] cts = new int[] {cte1, cte2, cte3};
		execute(functionDefinition.statements(), cts);

		if(cte1 == 0)out("ret " + cte1 + "," + cte2 + "," + cte3);

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

		address(read.getExpression());
		out("in", read.getExpression().getExpressionType());
		out("store", read.getExpression().getExpressionType());
		
		return null;
	}

	// class Print(List<Expression> expressions)
	@Override
	public Object visit(Print print, Object param) {

		line(print);

		value(print.expressions());

		print.getExpressions().stream().forEach(x -> {
			out("out", x.getExpressionType());
		});

		return null;
	}

	// class Println(List<Expression> expressions)
	@Override
	public Object visit(Println println, Object param) {

		line(println);

		value(println.expressions());

		println.getExpressions().stream().forEach(x -> {
			out("out", x.getExpressionType());
		});

		return null;
	}

	// class Printsp(List<Expression> expressions)
	@Override
	public Object visit(Printsp printsp, Object param) {

		line(printsp);

		value(printsp.expressions());

		printsp.getExpressions().stream().forEach(x -> {
			out("out", x.getExpressionType());
		});

		return null;
	}

	// class Return(Optional<Expression> expression)
	@Override
	public Object visit(Return returnValue, Object param) {

		int[] ctes = (int[]) param;
		line(returnValue);

		if(ctes[0] != 0) {
			value(returnValue.getExpression());
			out("ret " + ctes[0] + "," + ctes[1] + "," + ctes[2]);
		}
	
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
		String exit = "label" + actualLabel++;
		String loop = "label" + actualLabel++;
		
		line(whileValue);
		
		out(loop + ":");
		value(whileValue.getExpression());
		out("jz " + exit);
		
		execute(whileValue.statements(), param);
		
		out("jmp " + loop);
		out(exit + ":");

		return null;
	}

	// class Ifelse(Expression cond, List<Statement> tr, List<Statement> fs)
	@Override
	public Object visit(Ifelse ifelse, Object param) {

		String jzLabel = "label" + actualLabel++;
		String jmpLabel = "label" + actualLabel++;
		
		line(ifelse);

		value(ifelse.getCond());
		
		out("jz " + jzLabel);
		execute(ifelse.tr(), param);
		out("jmp " + jmpLabel);
		out(jzLabel + ":");
		execute(ifelse.fs(), param);
		out(jmpLabel + ":");
				
		return null;
	}

	// class FunctionCallStatement(String name, List<Expression> expressions)
	// phase Identification { FunctionDefinition functionDefinition }
	@Override
	public Object visit(FunctionCallStatement functionCallStatement, Object param) {

		line(functionCallStatement);
		
		value(functionCallStatement);

		return null;
	}

	// class FunctionCallExpression(String name, List<Expression> expressions)
	// phase Identification { FunctionDefinition functionDefinition }
	// phase TypeChecking { Type expressionType, boolean lvalue }
	@Override
	public Object visit(FunctionCallExpression functionCallExpression, Object param) {
		
		line(functionCallExpression);
	
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
