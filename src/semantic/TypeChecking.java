/**
 * MLang. Programming Language Design Tutorial
 * @author Raúl Izquierdo (raul@uniovi.es)
 */

package semantic;

import java.util.List;
import java.util.Objects;
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
import ast.expression.FloatLiteral;
import ast.expression.FunctionCallExpression;
import ast.expression.IntLiteral;
import ast.expression.Logic;
import ast.expression.Not;
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
import ast.type.ErrorType;
import ast.type.FloatType;
import ast.type.IdentType;
import ast.type.IntType;
import ast.type.StructType;
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

		// program.getDefinitions().forEach(definition -> definition.accept(this,
		// param));
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

		// structDefinition.getAttrDefinitions().forEach(attrDefinition ->
		// attrDefinition.accept(this, param));
		super.visit(structDefinition, param);

		return null;
	}

	// class FunctionDefinition(String name, List<Param> params, Optional<Type>
	// type, List<VarDefinition> varDefinitions, List<Statement> statements)
	@Override
	public Object visit(FunctionDefinition functionDefinition, Object param) {
		super.visit(functionDefinition, functionDefinition.getType());

		predicate(primitiveOrVoid(functionDefinition.getType()), "ERROR. Tipo de retorno debe ser simple o void",
				functionDefinition);

		return null;
	}

	// class Param(String name, Type type)
	@Override
	public Object visit(Param param_, Object param) {

		// param_.getType().accept(this, param);
		super.visit(param_, param);

		predicate(primitiveType(param_.getType()), "ERROR. Tipo del par�metro debe ser simple", param_);

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
		predicate(primitiveType(read.getExpression().getExpressionType()), "ERROR: no es un tipo simple", read);
		predicate(read.getExpression().isLvalue(), "ERROR: no es LValue", read);
		
		return null;
	}

	// class Print(List<Expression> expressions)
	@Override
	public Object visit(Print print, Object param) {

		// print.getExpressions().forEach(expression -> expression.accept(this, param));
		super.visit(print, param);
		primitiveTypes(print.getExpressions());
		
		return null;
	}

	// class Println(List<Expression> expressions)
	@Override
	public Object visit(Println println, Object param) {

		// println.getExpressions().forEach(expression -> expression.accept(this,
		// param));
		super.visit(println, param);
		primitiveTypes(println.getExpressions());
		
		return null;
	}

	// class Printsp(List<Expression> expressions)
	@Override
	public Object visit(Printsp printsp, Object param) {

		// printsp.getExpressions().forEach(expression -> expression.accept(this,
		// param));
		super.visit(printsp, param);
		primitiveTypes(printsp.getExpressions());

		return null;
	}

	// class Return(Optional<Expression> expression)
	@Override
	public Object visit(Return returnValue, Object param) {
		super.visit(returnValue, param);
		Optional<Type> functionReturnType = (Optional<Type>) param;
		Type type = functionReturnType.orElse(new VoidType());
		
		if(type instanceof VoidType) {
			if(returnValue.getExpression().isPresent()) {
				notifyError("ERROR: Una función con tipo de retorno void no puede devolver nada", returnValue.start());
			}
		} else {
			if(returnValue.getExpression().isEmpty()) {
				notifyError("ERROR: Una función con tipo de retorno debe devolver un valor", returnValue.start());
			}
		}

		return null;
	}

	// class Assignment(Expression left, Expression right)
	@Override
	public Object visit(Assignment assignment, Object param) {
		// assignment.getLeft().accept(this, param);
		// assignment.getRight().accept(this, param);
		super.visit(assignment, param);
		predicate(primitiveType(assignment.getLeft().getExpressionType()),
				"ERROR: el de la izquierda no es un tipo simple", assignment);
		predicate(areTypesEqual(assignment.getLeft().getExpressionType(), assignment.getRight().getExpressionType()),
				"ERROR: no son del mismo tipo", assignment);
		predicate(assignment.getLeft().isLvalue(), "ERROR: No es LValue", assignment);
		
		return null;
	}

	// class While(Expression expression, List<Statement> statements)
	@Override
	public Object visit(While whileValue, Object param) {
		super.visit(whileValue, param);

		predicate(isInt(whileValue.getExpression()), "ERROR: La condición debe ser int", whileValue);
		
		return null;
	}

	// class Ifelse(Expression cond, List<Statement> tr, List<Statement> fs)
	@Override
	public Object visit(Ifelse ifelse, Object param) {
		super.visit(ifelse, param);

		predicate(isInt(ifelse.getCond()), "ERROR: La condición debe ser int", ifelse);
		
		return null;
	}

	// class FunctionCallStatement(String name, List<Expression> expressions)
	// phase Identification { FunctionDefinition functionDefinition }
	@Override
	public Object visit(FunctionCallStatement functionCallStatement, Object param) {

		// functionCallStatement.getExpressions().forEach(expression ->
		// expression.accept(this, param));
		super.visit(functionCallStatement, param);

		checkArgumentTypes(functionCallStatement);
		
		return null;
	}

	// class IntLiteral(int intValue)
	@Override
	public Object visit(IntLiteral intLiteral, Object param) {
		intLiteral.setExpressionType(new IntType());
		intLiteral.setLvalue(false);
		
		return null;
	}

	// class FloatLiteral(float floatValue)
	@Override
	public Object visit(FloatLiteral floatLiteral, Object param) {
		floatLiteral.setExpressionType(new FloatType());
		floatLiteral.setLvalue(false);
		
		return null;
	}

	// class CharLiteral(String name)
	@Override
	public Object visit(CharLiteral charLiteral, Object param) {
		charLiteral.setExpressionType(new CharType());
		charLiteral.setLvalue(false);
		
		return null;
	}

	// class ArrayAccess(Expression expr1, Expression expr2)
	// var v:[10]int;
	// v[0] = 3;
	@Override
	public Object visit(ArrayAccess arrayAccess, Object param) {

		// arrayAccess.getExpr1().accept(this, param);
		// arrayAccess.getExpr2().accept(this, param);
		super.visit(arrayAccess, param);

		Type type = arrayAccess.getExpr1().getExpressionType();
		if (type instanceof ArrayType) {
			ArrayType at = (ArrayType) type;
			arrayAccess.setExpressionType(at.getType());
		} else if (isAccesible(arrayAccess) && isInt(arrayAccess.getExpr2())) {
			arrayAccess.setExpressionType(arrayAccess.getExpr1().getExpressionType());
		} else {
			arrayAccess.setExpressionType(new ErrorType());
		}
		predicate(isAccesible(arrayAccess), "ERROR: acceso a no-array", arrayAccess);
		predicate(isInt(arrayAccess.getExpr2()), "ERROR: acceso a array con indice no entero", arrayAccess);
		arrayAccess.setLvalue(true);
		
		return null;
	}

	// class FieldAccess(Expression expr, String name)
	// phase Identification { StructDefinition structDefinition }
	@Override
	public Object visit(FieldAccess fieldAccess, Object param) {
		// fieldAccess.getExpr().accept(this, param);
		super.visit(fieldAccess, param);

		Type type = fieldAccess.getExpr().getExpressionType();
		if (type instanceof StructType) {
			StructType structType = (StructType) type;
			StructDefinition sd = structType.getStructDefinition();
			List<AttrDefinition> attrdefs = sd.getAttrDefinitions();
			for (AttrDefinition def : attrdefs) {
				if (def.getName().equals(fieldAccess.getName())) {
					fieldAccess.setAttrDefinition(def);
					fieldAccess.setExpressionType(def.getType());
				}
			}
		}

		boolean isStruct = isStruct(fieldAccess);
		boolean hasProperty = false;
		predicate(isStruct, "ERROR: Se está intentando acceder a un campo de una expresión que no es un struct", fieldAccess);
		
		if(isStruct) {
			hasProperty = hasProperty(fieldAccess);
			predicate(hasProperty, "ERROR: La propiedad del struct no existe", fieldAccess);
		}

		if(!isStruct || !hasProperty) {
			fieldAccess.setExpressionType(new ErrorType());
		} 
		fieldAccess.setLvalue(true);
		
		return null;
	}

	// class Not(Expression expression)
	@Override
	public Object visit(Not not, Object param) {

		// not.getExpression().accept(this, param);
		super.visit(not, param);
		predicate(isInt(not.getExpression()), "ERROR: El operando debe ser entero", not);
		not.setExpressionType(new IntType());
		not.setLvalue(false);
		
		return null;
	}

	// class Arithmetic(Expression left, String operator, Expression right)
	@Override
	public Object visit(Arithmetic arithmetic, Object param) {

		// arithmetic.getLeft().accept(this, param);
		// arithmetic.getRight().accept(this, param);
		super.visit(arithmetic, param);
		checkOperation(arithmetic);
		predicate(areTypesEqual(arithmetic.getLeft().getExpressionType(), arithmetic.getRight().getExpressionType()), "ERROR: ambos operandos tienen que ser del mismo tipo", arithmetic);
		if(!areTypesEqual(arithmetic.getLeft().getExpressionType(), arithmetic.getRight().getExpressionType())) {
			arithmetic.setExpressionType(new ErrorType());
		}
		arithmetic.setLvalue(false);
		
		return null;
	}

	@Override
	public Object visit(Logic logic, Object param) {
		super.visit(logic, param);
		predicate(isInt(logic.getLeft()) || isFloat(logic.getLeft()), "ERROR: El primer operando debe ser entero o float", logic);
		predicate(isInt(logic.getRight()) || isFloat(logic.getRight()), "ERROR: El segundo operando debe ser entero o float", logic);
		
		if(logic.getLeft().getExpressionType() instanceof IntType && logic.getRight().getExpressionType() instanceof IntType) {
			logic.setExpressionType(new IntType());
		} else if(logic.getLeft().getExpressionType() instanceof FloatType && logic.getRight().getExpressionType() instanceof FloatType) {
			logic.setExpressionType(new IntType());
		} else {
			logic.setExpressionType(new ErrorType());
		}
		logic.setLvalue(false);
		
		return null;
	}

	// class Variable(String name)
	// phase Identification { VarDefinition varDefinition }
	@Override
	public Object visit(Variable variable, Object param) {
		variable.setExpressionType(variable.getVarDefinition().getType());
		variable.setLvalue(true);
		
		return null;
	}

	// class Cast(Type type, Expression expression)
	@Override
	public Object visit(Cast cast, Object param) {

		// cast.getType().accept(this, param);
		// cast.getExpression().accept(this, param);
		super.visit(cast, param);
		
		isCastable(cast);
		predicate(!areTypesEqual(cast.getType(), cast.getExpression().getExpressionType()), "ERROR: el tipo de la expresión y el tipo destino deben ser distintos", cast);
		
		cast.setExpressionType(cast.getType());
		cast.setLvalue(false);
		
		return null;
	}

	// class FunctionCallExpression(String name, List<Expression> expressions)
	// phase Identification { FunctionDefinition functionDefinition }
	@Override
	public Object visit(FunctionCallExpression functionCallExpression, Object param) {

		// functionCallExpression.getExpressions().forEach(expression ->
		// expression.accept(this, param));
		super.visit(functionCallExpression, param);
		
		functionCallExpression.setExpressionType(functionCallExpression.getFunctionDefinition().getType().orElse(new VoidType()));
		
		List<Param> paramDefinitions = functionCallExpression.getFunctionDefinition().getParams();
		List<Expression> params = functionCallExpression.getExpressions();
		if(paramDefinitions.size() != params.size()) {
			notifyError("ERROR: número erróneo de argumentos", functionCallExpression.start());
			return null;
		}
		
		for(int i = 0 ; i < params.size() ; i++) {
			if(!areTypesEqual(params.get(i).getExpressionType(), paramDefinitions.get(i).getType())) {
				notifyError("ERROR: ERROR: argument type", functionCallExpression.start());
			}
		}
		functionCallExpression.setLvalue(false);
		
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

	// # ----------------------------------------------------------
	// # Auxiliary methods (optional)

	private void primitiveTypes(List<Expression> list) {
		for(Expression e : list) {
			if(!primitiveType(e.getExpressionType())) {
				notifyError("ERROR: Se esperaba un tipo simple", e.start());
			}
		}
	}
	
	private boolean primitiveType(Type type) {
		return (type.getClass() == IntType.class)
				|| (type.getClass() == CharType.class)
				|| (type.getClass() == FloatType.class);
	}

	private boolean primitiveOrVoid(Optional<Type> optional) {
		if (optional.isEmpty()) {
			return true;
		} else if (primitiveType(optional.get())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean areTypesEqual(Type type1, Type type2) {
		// Si los tipos son nulos, no son iguales
		if (type1 == null || type2 == null) {
			return false;
		}

		// Si los tipos son ArrayType, comprobar recursivamente los tipos internos
		if (type1 instanceof ArrayType && type2 instanceof ArrayType) {
			ArrayType arrayType1 = (ArrayType) type1;
			ArrayType arrayType2 = (ArrayType) type2;
			return areTypesEqual(arrayType1.getType(), arrayType2.getType());
		}

		// Si los tipos son StructType, comprobar recursivamente los atributos
		if (type1 instanceof StructType && type2 instanceof StructType) {
			StructType structType1 = (StructType) type1;
			StructType structType2 = (StructType) type2;
			return areStructTypesEqual(structType1.getStructDefinition(), structType2.getStructDefinition());
		}

		// Comprobar si los tipos son iguales directamente
		if (type1.getClass() == type2.getClass()) {
			return true;
		}

		// En cualquier otro caso, los tipos no son iguales
		return false;
	}

	private boolean areStructTypesEqual(StructDefinition struct1, StructDefinition struct2) {
		// Si los structs son nulos, no son iguales
		if (struct1 == null || struct2 == null) {
			return false;
		}

		// Comprobar si los structs son el mismo objeto
		if (struct1.equals(struct2)) {
			return true;
		}

		// Comprobar si tienen el mismo número de atributos
		if (struct1.getAttrDefinitions().size() != struct2.getAttrDefinitions().size()) {
			return false;
		}

		// Comprobar si los nombres y tipos de los atributos coinciden
		for (int i = 0; i < struct1.getAttrDefinitions().size(); i++) {
			AttrDefinition attr1 = struct1.getAttrDefinitions().get(i);
			AttrDefinition attr2 = struct2.getAttrDefinitions().get(i);
			if (!attr1.getName().equals(attr2.getName()) || !areTypesEqual(attr1.getType(), attr2.getType())) {
				return false;
			}
		}

		// Si todas las comprobaciones pasan, los structs son iguales
		return true;
	}

	private boolean checkLValueSimpleType(Assignment assignment) {
		Expression left = assignment.getLeft();
		if (primitiveType(left.getExpressionType())) {
			return true;
		}
		return false;
	}
	
	private boolean hasProperty(FieldAccess fieldAccess) {
		if(fieldAccess.getAttrDefinition() == null) {
			return false;
		} else {
			return true;
		}
	}

	private boolean isStruct(FieldAccess fieldAccess) {
		if (fieldAccess.getExpr().getExpressionType() instanceof StructType) {
			return true;
		}
		return false;
	}

	private boolean isAccesible(ArrayAccess arrayAccess) {
		if (arrayAccess.getExpr1().getExpressionType() instanceof ArrayType) {
			return true;
		}
		return false;
	}

	private boolean isInt(Expression expr) {
		if (expr.getExpressionType() instanceof IntType) {
			return true;
		}
		return false;
	}

	private boolean isFloat(Expression expr) {
		if (expr.getExpressionType() instanceof FloatType) {
			return true;
		}
		return false;
	}

	private void checkOperation(Arithmetic arithmetic) {
		switch (arithmetic.getOperator()) {
			case "*":
			case "/":
			case "+":
			case "-":
				if (!(arithmetic.getLeft().getExpressionType() instanceof IntType || arithmetic.getLeft().getExpressionType() instanceof FloatType) ||
						!(arithmetic.getRight().getExpressionType() instanceof IntType || arithmetic.getRight().getExpressionType() instanceof FloatType)) {
					notifyError("ERROR: ambos operandos tienen que ser enteros o float", arithmetic.start());
					arithmetic.setExpressionType(new ErrorType());
				} else {
					arithmetic.setExpressionType(arithmetic.getLeft().getExpressionType());
				}
				break;
			case "%":
				if(!(arithmetic.getLeft().getExpressionType() instanceof IntType
						&& arithmetic.getRight().getExpressionType() instanceof IntType)) {
					notifyError("ERROR: ambos operandos tienen que ser enteros", arithmetic.start());
					arithmetic.setExpressionType(new ErrorType());
				} else {
					arithmetic.setExpressionType(arithmetic.getLeft().getExpressionType());
				}
				break;
		}
	}
	
	private void isCastable(Cast cast) {
		if(cast.getType() instanceof ArrayType) {
			notifyError("ERROR: no se puede hacer cast a tipo array", cast.start());
		} else if (cast.getType() instanceof StructType) {
			notifyError("ERROR: no se puede hacer cast a tipo struct", cast.start());
		}
		
		if(cast.getExpression().getExpressionType() instanceof ArrayType) {
			notifyError("ERROR: no se puede convertir un array", cast.start());
		} else if (cast.getExpression().getExpressionType() instanceof StructType) {
			notifyError("ERROR: no se puede convertir un struct", cast.start());
		}
	}
	
	private void checkArgumentTypes(FunctionCallStatement functionCallStatement) {
		super.visit(functionCallStatement, null);
		
		List<Param> paramDefinitions = functionCallStatement.getFunctionDefinition().getParams();
		List<Expression> params = functionCallStatement.getExpressions();
		if(paramDefinitions.size() != params.size()) {
			notifyError("ERROR: número erróneo de argumentos", functionCallStatement.start());
			return;
		}
		
		for(int i = 0 ; i < params.size() ; i++) {
			if(!areTypesEqual(params.get(i).getExpressionType(), paramDefinitions.get(i).getType())) {
				notifyError("ERROR: ERROR: argument type", functionCallStatement.start());
			}
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
	 * predicate(expr.type != null), "Type cannot be null", expr.start());
	 *
	 * predicate(expr.type != null), "Type cannot be null", expr); // expr.start()
	 * is assumed
	 *
	 * The start() method (example 1) indicates the position in the file where the
	 * node was. If VGen is used, this method
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
