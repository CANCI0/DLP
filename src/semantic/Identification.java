package semantic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import ast.*;
import ast.definition.FunctionDefinition;
import ast.definition.StructDefinition;
import ast.definition.VarDefinition;
import ast.expression.Arithmetic;
import ast.expression.ArrayAccess;
import ast.expression.Cast;
import ast.expression.CharLiteral;
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
import ast.type.IdentType;
import ast.type.IntType;
import ast.type.RealType;
import main.ErrorManager;
import visitor.DefaultVisitor;

// This class will be implemented in identification phase

public class Identification extends DefaultVisitor {

    private ErrorManager errorManager;

    private ContextMap<VarDefinition> variables = new ContextMap<VarDefinition>();
    
    private Map<String, FunctionDefinition> functions = new HashMap<>();
    
    private Map<String, StructDefinition> structs = new HashMap<>();
    
    public Identification(ErrorManager errorManager) {
        this.errorManager = errorManager;
    }

    public void process(AST ast) {
        ast.accept(this, null);
    }

 // # ----------------------------------------------------------
    // class VarDefinition(Type type, String name)  
    @Override
	public Object visit(Program program, Object param) {

		program.getDefinitions().forEach(definition -> definition.accept(this, param));
		return null;
	}

    @Override
    public Object visit(VarDefinition varDefinition, Object param) {
        var definition = variables.getFromTop(varDefinition.getName());
        if (definition != null)
            notifyError("Variable already defined: " + varDefinition.getName(), varDefinition);
        else
            variables.put(varDefinition.getName(), varDefinition);
        varDefinition.getType().accept(this, param);
		return null;
    }

    @Override
    public Object visit(StructDefinition structDefinition, Object param) {
        var definition = structs.get(structDefinition.getName());
        if (definition != null)
        	notifyError("Struct already defined: " + structDefinition.getName(), structDefinition);
        else
        	structs.put(structDefinition.getName(), structDefinition);
        	
        Set<String> definitions = new HashSet<String>();	//Se comprueba que no se repitan los campos
        for(AttrDefinition def : structDefinition.getAttrDefinitions()) {	
        	if(definitions.contains(def.getName())) {
        		notifyError("Attribute definition already defined: " + def.getName(), def);
        	}else {
        		definitions.add(def.getName());
        	}
        }
        
        return null;
    }

    @Override
    public Object visit(FunctionDefinition functionDefinition, Object param) {
    	variables.set();
        var definition = functions.get(functionDefinition.getName());
        if (definition != null)
        	notifyError("Function already defined: " + functionDefinition.getName(), functionDefinition);
        else
        	functions.put(functionDefinition.getName(), functionDefinition);
        
        Set<String> params = new HashSet<String>();
        //Comprobamos que no haya parametros repetidos
        for(Param attr : functionDefinition.getParams()) {
        	if(params.contains(attr.getName())) {
        		notifyError("Param already defined: " + attr.getName(), attr);
        	}else {
        		params.add(attr.getName());
        	}
        }
        
        //Comprobamos que las varDefinitions de la funcion no coincidan con ningun parametro
        for(var varDefinition : functionDefinition.getVarDefinitions()) {
        	if(functionDefinition.getParams().stream().anyMatch(x -> Objects.equals(x.getName(), varDefinition.getName()))) {
        		notifyError("Variable already defined in params: " + varDefinition.getName(), varDefinition);
        	}
        	visit(varDefinition, null);
        }
        functionDefinition.getStatements().forEach(statement -> statement.accept(this, param));
        variables.reset();
        return null;
    }

	@Override
	public Object visit(Param param_, Object param) {

		param_.getType().accept(this, param);
		return null;
	}

	@Override
	public Object visit(AttrDefinition attrDefinition, Object param) {
		
		attrDefinition.getType().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Read read, Object param) {

		read.getExpression().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Print print, Object param) {

		print.getExpressions().forEach(expression -> expression.accept(this, param));
		return null;
	}

	@Override
	public Object visit(Println println, Object param) {

		println.getExpressions().forEach(expression -> expression.accept(this, param));
		return null;
	}

	@Override
	public Object visit(Printsp printsp, Object param) {

		printsp.getExpressions().forEach(expression -> expression.accept(this, param));
		return null;
	}

	@Override
	public Object visit(Return returnValue, Object param) {

		returnValue.getExpression().ifPresent(expression -> expression.accept(this, param));
		return null;
	}

	@Override
	public Object visit(Assignment assignment, Object param) {

		assignment.getLeft().accept(this, param);
		assignment.getRight().accept(this, param);
		return null;
	}

	@Override
	public Object visit(While whileValue, Object param) {

		whileValue.getExpression().accept(this, param);
		whileValue.getStatements().forEach(statement -> statement.accept(this, param));
		return null;
	}

	@Override
	public Object visit(Ifelse ifelse, Object param) {

		ifelse.getCond().accept(this, param);
		ifelse.getTr().forEach(statement -> statement.accept(this, param));
		ifelse.getFs().forEach(statement -> statement.accept(this, param));
		return null;
	}

	@Override
	public Object visit(FunctionCallStatement functionCallStatement, Object param) {
		FunctionDefinition functionDefinition = functions.get(functionCallStatement.getName());
		if(functionDefinition == null) {
			notifyError("Undefined function: " + functionCallStatement.getName(), functionCallStatement);
		}else {
			functionCallStatement.setFunctionDefinition(functionDefinition);
		}
		functionCallStatement.getExpressions().forEach(expression -> expression.accept(this, param));
		return null;
	}

	@Override
	public Object visit(IntLiteral intLiteral, Object param) {

		return null;
	}

	@Override
	public Object visit(RealLiteral realLiteral, Object param) {

		return null;
	}

	@Override
	public Object visit(CharLiteral charLiteral, Object param) {

		return null;
	}

	@Override
	public Object visit(ArrayAccess arrayAccess, Object param) {

		arrayAccess.getExpr1().accept(this, param);
		arrayAccess.getExpr2().accept(this, param);
		return null;
	}

	@Override
	public Object visit(FieldAccess fieldAccess, Object param) {
		fieldAccess.getExpr().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Not not, Object param) {

		not.getExpression().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Arithmetic arithmetic, Object param) {

		arithmetic.getLeft().accept(this, param);
		arithmetic.getRight().accept(this, param);
		return null;
	}

	@Override
    public Object visit(Variable variable, Object param) {
        var definition = variables.getFromAny(variable.getName());
        if (definition == null)
            notifyError("Undefined variable: " + variable.getName(), variable);
        else
            variable.setVarDefinition(definition);
        return null;
    }

	@Override
	public Object visit(Cast cast, Object param) {

		cast.getType().accept(this, param);
		cast.getExpression().accept(this, param);
		return null;
	}

	@Override
	public Object visit(FunctionCallExpression functionCallExpression, Object param) {
		FunctionDefinition functionDefinition = functions.get(functionCallExpression.getName());
		if(functionDefinition == null) {
			notifyError("Undefined function: " + functionCallExpression.getName(), functionCallExpression);
		} else {
			functionCallExpression.setFunctionDefinition(functionDefinition);
		}
		functionCallExpression.getExpressions().forEach(expression -> expression.accept(this, param));
		return null;
	}

	@Override
	public Object visit(IntType intType, Object param) {

		return null;
	}

	@Override
	public Object visit(RealType realType, Object param) {

		return null;
	}

	@Override
	public Object visit(CharType charType, Object param) {

		return null;
	}

	@Override
	public Object visit(ArrayType arrayType, Object param) {

		arrayType.getType().accept(this, param);
		return null;
	}

	@Override
	public Object visit(IdentType identType, Object param) {
		StructDefinition struct = structs.get(identType.getName());
		if(struct == null) {
			notifyError("Undefined struct: " + identType.getName(), identType);
		}
		return null;
	}
    
    private void notifyError(String msg) {
        errorManager.notify("Identification", msg);
    }

    private void notifyError(String msg, Position position) {
        errorManager.notify("Identification", msg, position);
    }

    private void notifyError(String msg, AST node) {
        notifyError(msg, node.start());
    }

}
