/**
 * MLang. Programming Language Design Tutorial
 * @author Ra√∫l Izquierdo (raul@uniovi.es)
 */

package semantic;

import java.util.Optional;

import ast.*;
import ast.definition.FunctionDefinition;
import ast.definition.StructDefinition;
import ast.definition.VarDefinition;
import ast.expression.Arithmetic;
import ast.expression.ArrayAccess;
import ast.expression.Cast;
import ast.expression.CharLiteral;
import ast.expression.Expression;
import ast.expression.FieldAccess;
import ast.expression.FunctionCallExpression;
import ast.expression.IntLiteral;
import ast.expression.Not;
import ast.expression.RealLiteral;
import ast.expression.Variable;
import ast.statement.Assignment;
import ast.statement.FunctionCallStatement;
import ast.statement.Ifelse;
import ast.statement.Print;
import ast.statement.Println;
import ast.statement.Printsp;
import ast.statement.Read;
import ast.statement.Return;
import ast.statement.While;
import ast.type.ArrayType;
import ast.type.CharType;
import ast.type.FloatType;
import ast.type.IdentType;
import ast.type.IntType;
import ast.type.Type;
import ast.type.VoidType;
import main.ErrorManager;
import visitor.DefaultVisitor;

// This class will be implemented in type checking phase

public class TypeChecking extends DefaultVisitor {

    private ErrorManager errorManager;

    public TypeChecking(ErrorManager errorManager) {
        this.errorManager = errorManager;
    }

    public void process(AST ast) {
        ast.accept(this, null);
    }

 // class Program(List<Definition> definitions)
 	@Override
 	public Object visit(Program program, Object param) {

 		// program.getDefinitions().forEach(definition -> definition.accept(this, param));
 		super.visit(program, param);

 		return null;
 	}

 	// class VarDefinition(String name, Type type)
 	@Override
 	public Object visit(VarDefinition varDefinition, Object param) {

 		// varDefinition.getType().accept(this, param);
 		super.visit(varDefinition, param);

 		return null;
 	}

 	// class StructDefinition(String name, List<AttrDefinition> attrDefinitions)
 	@Override
 	public Object visit(StructDefinition structDefinition, Object param) {

 		// structDefinition.getAttrDefinitions().forEach(attrDefinition -> attrDefinition.accept(this, param));
 		super.visit(structDefinition, param);

 		return null;
 	}

 	// class FunctionDefinition(String name, List<Param> params, Optional<Type> type, List<VarDefinition> varDefinitions, List<Statement> statements)
 	@Override
 	public Object visit(FunctionDefinition functionDefinition, Object param) {

 		//functionDefinition.getParams().forEach(param_ -> param_.accept(this, param));
 		//functionDefinition.getType().ifPresent(type -> type.accept(this, param));
 		//functionDefinition.getVarDefinitions().forEach(varDefinition -> varDefinition.accept(this, param));
 		//functionDefinition.getStatements().forEach(statement -> statement.accept(this, param));
 		super.visit(functionDefinition, param);

 		predicate(primitiveOrVoid(functionDefinition.getType()), "ERROR. Tipo de retorno debe ser simple o void", functionDefinition);
 		
 		return null;
 	}

 	// class Param(String name, Type type)
 	@Override
 	public Object visit(Param param_, Object param) {

 		// param_.getType().accept(this, param);
 		super.visit(param_, param);

 		predicate(primitiveType(param_.getType()), "ERROR. Tipo del par·metro debe ser simple", param_);
 		
 		return null;
 	}

 	// class AttrDefinition(String name, Type type)
 	@Override
 	public Object visit(AttrDefinition attrDefinition, Object param) {

 		// attrDefinition.getType().accept(this, param);
 		super.visit(attrDefinition, param);

 		return null;
 	}

 	// class Read(Expression expression)
 	@Override
 	public Object visit(Read read, Object param) {

 		// read.getExpression().accept(this, param);
 		super.visit(read, param);

 		return null;
 	}

 	// class Print(List<Expression> expressions)
 	@Override
 	public Object visit(Print print, Object param) {

 		// print.getExpressions().forEach(expression -> expression.accept(this, param));
 		super.visit(print, param);

 		return null;
 	}

 	// class Println(List<Expression> expressions)
 	@Override
 	public Object visit(Println println, Object param) {

 		// println.getExpressions().forEach(expression -> expression.accept(this, param));
 		super.visit(println, param);

 		return null;
 	}

 	// class Printsp(List<Expression> expressions)
 	@Override
 	public Object visit(Printsp printsp, Object param) {

 		// printsp.getExpressions().forEach(expression -> expression.accept(this, param));
 		super.visit(printsp, param);

 		return null;
 	}

 	// class Return(Optional<Expression> expression)
 	@Override
 	public Object visit(Return returnValue, Object param) {

 		// returnValue.getExpression().ifPresent(expression -> expression.accept(this, param));
 		super.visit(returnValue, param);
 		
 		return null;
 	}

 	// class Assignment(Expression left, Expression right)
 	@Override
 	public Object visit(Assignment assignment, Object param) {

 		// assignment.getLeft().accept(this, param);
 		// assignment.getRight().accept(this, param);
 		super.visit(assignment, param);

 		return null;
 	}

 	// class While(Expression expression, List<Statement> statements)
 	@Override
 	public Object visit(While whileValue, Object param) {

 		// whileValue.getExpression().accept(this, param);
 		// whileValue.getStatements().forEach(statement -> statement.accept(this, param));
 		super.visit(whileValue, param);

 		return null;
 	}

 	// class Ifelse(Expression cond, List<Statement> tr, List<Statement> fs)
 	@Override
 	public Object visit(Ifelse ifelse, Object param) {

 		// ifelse.getCond().accept(this, param);
 		// ifelse.getTr().forEach(statement -> statement.accept(this, param));
 		// ifelse.getFs().forEach(statement -> statement.accept(this, param));
 		super.visit(ifelse, param);

 		return null;
 	}

 	// class FunctionCallStatement(String name, List<Expression> expressions)
 	// phase Identification { FunctionDefinition functionDefinition }
 	@Override
 	public Object visit(FunctionCallStatement functionCallStatement, Object param) {

 		// functionCallStatement.getExpressions().forEach(expression -> expression.accept(this, param));
 		super.visit(functionCallStatement, param);

 		return null;
 	}

 	// class IntLiteral(int intValue)
 	@Override
 	public Object visit(IntLiteral intLiteral, Object param) {

 		return null;
 	}

 	// class RealLiteral(float floatValue)
 	@Override
 	public Object visit(RealLiteral realLiteral, Object param) {

 		return null;
 	}

 	// class CharLiteral(String name)
 	@Override
 	public Object visit(CharLiteral charLiteral, Object param) {

 		return null;
 	}

 	// class ArrayAccess(Expression expr1, Expression expr2)
 	@Override
 	public Object visit(ArrayAccess arrayAccess, Object param) {

 		// arrayAccess.getExpr1().accept(this, param);
 		// arrayAccess.getExpr2().accept(this, param);
 		super.visit(arrayAccess, param);

 		return null;
 	}

 	// class FieldAccess(Expression expr, String name)
 	// phase Identification { StructDefinition structDefinition }
 	@Override
 	public Object visit(FieldAccess fieldAccess, Object param) {

 		// fieldAccess.getExpr().accept(this, param);
 		super.visit(fieldAccess, param);

 		return null;
 	}

 	// class Not(Expression expression)
 	@Override
 	public Object visit(Not not, Object param) {

 		// not.getExpression().accept(this, param);
 		super.visit(not, param);

 		return null;
 	}

 	// class Arithmetic(Expression left, String operator, Expression right)
 	@Override
 	public Object visit(Arithmetic arithmetic, Object param) {

 		// arithmetic.getLeft().accept(this, param);
 		// arithmetic.getRight().accept(this, param);
 		super.visit(arithmetic, param);

 		return null;
 	}

 	// class Variable(String name)
 	// phase Identification { VarDefinition varDefinition }
 	@Override
 	public Object visit(Variable variable, Object param) {

 		return null;
 	}

 	// class Cast(Type type, Expression expression)
 	@Override
 	public Object visit(Cast cast, Object param) {

 		// cast.getType().accept(this, param);
 		// cast.getExpression().accept(this, param);
 		super.visit(cast, param);

 		return null;
 	}

 	// class FunctionCallExpression(String name, List<Expression> expressions)
 	// phase Identification { FunctionDefinition functionDefinition }
 	@Override
 	public Object visit(FunctionCallExpression functionCallExpression, Object param) {

 		// functionCallExpression.getExpressions().forEach(expression -> expression.accept(this, param));
 		super.visit(functionCallExpression, param);

 		return null;
 	}

 	// class IntType()
 	@Override
 	public Object visit(IntType intType, Object param) {

 		return null;
 	}

 	// class RealType()
 	@Override
 	public Object visit(FloatType floatType, Object param) {

 		return null;
 	}

 	// class CharType()
 	@Override
 	public Object visit(CharType charType, Object param) {

 		return null;
 	}

 	// class ArrayType(int intValue, Type type)
 	@Override
 	public Object visit(ArrayType arrayType, Object param) {

 		// arrayType.getType().accept(this, param);
 		super.visit(arrayType, param);

 		return null;
 	}

 	// class IdentType(String name)
 	@Override
 	public Object visit(IdentType identType, Object param) {

 		return null;
 	}
    //# ----------------------------------------------------------
    //# Auxiliary methods (optional)

 	private boolean primitiveType(Type type) {
 		return (type.getClass() == IntType.class) 
 				|| (type.getClass() == CharType.class)
 				|| (type.getClass() == FloatType.class);
 	}
 	
 	private boolean primitiveOrVoid(Optional<Type> optional) {
 		if(optional.isEmpty()) {
 			return true;
 		} else if(primitiveType(optional.get())) {
 			return true;
 		} else {
 			return false;
 		}
 	}
 	
    private void notifyError(String errorMessage, Position position) {
        errorManager.notify("Type Checking", errorMessage, position);
    }

    private void notifyError(String msg) {
        errorManager.notify("Type Checking", msg);
    }

    /**
     * predicate. Auxiliary method to implement predicates. Delete if not needed.
     *
     * Usage examples:
     *
     *    predicate(expr.type != null), "Type cannot be null", expr.start());
     *
     *    predicate(expr.type != null), "Type cannot be null", expr);       // expr.start() is assumed
     *
     * The start() method (example 1) indicates the position in the file where the node was. If VGen is used, this method
     * will have been generated in all AST nodes.
     *
     * @param condition     Must be met to avoid an error
     * @param errorMessage  Printed if the condition is not met
     * @param errorPosition Row and column in the file where the error occurred.
     * @return true if the condition is met
     */

    private boolean predicate(boolean condition, String errorMessage, Position position) {
        if (!condition) {
            notifyError(errorMessage, position);
            return false;
        }

        return true;
    }

    private boolean predicate(boolean condition, String errorMessage, AST node) {
        return predicate(condition, errorMessage, node.start());
    }

}
