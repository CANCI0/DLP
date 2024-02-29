// Generated with VGen 2.0.0

/**
* El objetivo de esta clase DefaultVisitor es ser la implementación base de la que
* deriven todos los demás visitor.
*
* Sus métodos visit recorren los hijos del nodo que se está visitando. Por tanto,
* todo visitor que derive de este ya tendrá implementado todo el código de
* recorrido del AST.
*
* Para crear nuevos visitor, en vez de modificar esta clase, se recomienda copiar
* 'SkeletonForNewVisitors.java' o, mejor aún, definir una nueva PHASE en la gramática
* y usar el visitor generado para la misma.
*/

package visitor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ast.*;
import ast.definition.*;
import ast.statement.*;
import ast.expression.*;
import ast.type.*;

// %% User Declarations -------------

    // Declarations (e.g. imports) in this section will be preserved. Delete if not needed

// %% -------------------------------


public class AstCode implements Visitor {
	
	private AstCode(List<String> sourceLines) {
        this.sourceLines = sourceLines;
    }
	
	public static void toPrint(String sourceFile, AST root) {
		if (root != null) {
            AstCode printer = new AstCode(loadLines(sourceFile));
            printer.printNodes(root);
        } else
            System.out.println("root == null");
	}
	
	public void printNodes(AST root) {
        root.accept(this, Integer.valueOf(0));
    }
	
	private static List<String> loadLines(String sourceFile) {
        if (sourceFile == null)
            return java.util.Collections.emptyList();

        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFile))) {
            String line;
            while ((line = br.readLine()) != null)
                lines.add(line);

        } catch (FileNotFoundException e) {
            System.out.println("Warning. No se pudo encontrar el fichero fuente '" + sourceFile
                    + "'. No se mostrará información de posición.");
        } catch (IOException e) {
            System.out.println("Warning. Error al leer del fichero fuente '" + sourceFile
                    + "'. No se mostrará información de posición.");
        }
        return lines;
    }
	
	@Override
	public Object visit(Program program, Object param) {
		System.out.println(program);
		program.getDefinitions().forEach(definition -> definition.accept(this, param));
		return null;
	}

	@Override
	public Object visit(VarDefinition varDefinition, Object param) {
		System.out.println(varDefinition);
		varDefinition.getType().accept(this, param);
		return null;
	}

	@Override
	public Object visit(StructDefinition structDefinition, Object param) {
		System.out.println(structDefinition);
		structDefinition.getAttrDefinitions().forEach(attrDefinition -> attrDefinition.accept(this, param));
		return null;
	}

	@Override
	public Object visit(FunctionDefinition functionDefinition, Object param) {
		System.out.println(functionDefinition);
		functionDefinition.getParams().forEach(param_ -> param_.accept(this, param));
		functionDefinition.getType().ifPresent(type -> type.accept(this, param));
		functionDefinition.getVarDefinitions().forEach(varDefinition -> varDefinition.accept(this, param));
		functionDefinition.getStatements().forEach(statement -> statement.accept(this, param));
		return null;
	}

	@Override
	public Object visit(Param param_, Object param) {
		System.out.println(param_);
		param_.getType().accept(this, param);
		return null;
	}

	@Override
	public Object visit(AttrDefinition attrDefinition, Object param) {
		System.out.println(attrDefinition);
		attrDefinition.getType().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Read read, Object param) {
		System.out.println(read);
		read.getExpression().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Print print, Object param) {
		System.out.println(print);
		print.getExpressions().forEach(expression -> expression.accept(this, param));
		return null;
	}

	@Override
	public Object visit(Println println, Object param) {
		System.out.println(println);
		println.getExpressions().forEach(expression -> expression.accept(this, param));
		return null;
	}

	@Override
	public Object visit(Printsp printsp, Object param) {
		System.out.println(printsp);
		printsp.getExpressions().forEach(expression -> expression.accept(this, param));
		return null;
	}

	@Override
	public Object visit(Return returnValue, Object param) {
		System.out.println(returnValue);
		returnValue.getExpression().ifPresent(expression -> expression.accept(this, param));
		return null;
	}

	@Override
	public Object visit(Assignment assignment, Object param) {
		System.out.println(assignment);
		assignment.getLeft().accept(this, param);
		assignment.getRight().accept(this, param);
		return null;
	}

	@Override
	public Object visit(While whileValue, Object param) {
		System.out.println(whileValue);
		whileValue.getExpression().accept(this, param);
		whileValue.getStatements().forEach(statement -> statement.accept(this, param));
		return null;
	}

	@Override
	public Object visit(Ifelse ifelse, Object param) {
		System.out.println(ifelse);
		ifelse.getCond().accept(this, param);
		ifelse.getTr().forEach(statement -> statement.accept(this, param));
		ifelse.getFs().forEach(statement -> statement.accept(this, param));
		return null;
	}

	@Override
	public Object visit(FunctionCallStatement functionCallStatement, Object param) {
		System.out.println(functionCallStatement);
		functionCallStatement.getExpressions().forEach(expression -> expression.accept(this, param));
		return null;
	}

	@Override
	public Object visit(IntLiteral intLiteral, Object param) {
		System.out.println(intLiteral);
		return null;
	}

	@Override
	public Object visit(RealLiteral realLiteral, Object param) {
		System.out.println(realLiteral);
		return null;
	}

	@Override
	public Object visit(CharLiteral charLiteral, Object param) {
		System.out.println(charLiteral);
		return null;
	}

	@Override
	public Object visit(ArrayAccess arrayAccess, Object param) {
		System.out.println(arrayAccess);
		arrayAccess.getExpr1().accept(this, param);
		arrayAccess.getExpr2().accept(this, param);
		return null;
	}

	@Override
	public Object visit(FieldAccess fieldAccess, Object param) {
		System.out.println(fieldAccess);
		fieldAccess.getExpr().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Not not, Object param) {
		System.out.println(not);
		not.getExpression().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Arithmetic arithmetic, Object param) {
		System.out.println(arithmetic);
		arithmetic.getLeft().accept(this, param);
		arithmetic.getRight().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Variable variable, Object param) {
		System.out.println(variable);
		return null;
	}

	@Override
	public Object visit(Cast cast, Object param) {
		System.out.println(cast);
		cast.getType().accept(this, param);
		cast.getExpression().accept(this, param);
		return null;
	}

	@Override
	public Object visit(FunctionCallExpression functionCallExpression, Object param) {
		System.out.println(functionCallExpression);
		functionCallExpression.getExpressions().forEach(expression -> expression.accept(this, param));
		return null;
	}

	@Override
	public Object visit(IntType intType, Object param) {
		System.out.println("int");
		return null;
	}

	@Override
	public Object visit(RealType realType, Object param) {
		System.out.println("real");
		return null;
	}

	@Override
	public Object visit(CharType charType, Object param) {
		System.out.println("char");
		return null;
	}

	@Override
	public Object visit(ArrayType arrayType, Object param) {
		System.out.println(arrayType);
		return null;
	}

	@Override
	public Object visit(IdentType identType, Object param) {
		System.out.println(identType.getName());
		return null;
	}

    // %% User Members -------------------------

        // Methods/attributes in this section will be preserved. Delete if not needed

    // %% --------------------------------------
	private List<String> sourceLines;
}
