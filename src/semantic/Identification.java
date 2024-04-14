package semantic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import ast.*;
import ast.definition.*;
import ast.expression.*;
import ast.statement.*;
import ast.type.*;
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
        	varDefinition.setScope(variables.getActualScope());
            variables.put(varDefinition.getName(), varDefinition);
        varDefinition.getType().accept(this, param);
		return null;
    }

    @Override
    public Object visit(StructDefinition structDefinition, Object param) {
    	super.visit(structDefinition, param);
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
        		def.setStructDefinition(structDefinition);
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
        
        super.visit(functionDefinition, param);
        
        Set<String> params = new HashSet<String>();
        //Comprobamos que no haya parametros repetidos y que sean de tipo simple
        for(VarDefinition attr : functionDefinition.getParams()) {
        	if(params.contains(attr.getName())) {
        		notifyError("Param already defined: " + attr.getName(), attr);
        	}else {
        		params.add(attr.getName());
        	}
        }
        
        //Comprobamos que las varDefinitions de la funcion no coincidan con ningun parametro
        for(var varDefinition : functionDefinition.getDefinitions()) {
        	if(functionDefinition.getParams().stream().anyMatch(x -> Objects.equals(x.getName(), varDefinition.getName()))) {
        		notifyError("Variable already defined in params: " + varDefinition.getName(), varDefinition);
        	}
        }
        variables.reset();
        return null;
    }

	@Override
	public Object visit(FunctionCallStatement functionCallStatement, Object param) {
		super.visit(functionCallStatement, param);
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
    public Object visit(Variable variable, Object param) {
		super.visit(variable, param);
        var definition = variables.getFromAny(variable.getName());
        if (definition == null)
            notifyError("Undefined variable: " + variable.getName(), variable);
        else
            variable.setVarDefinition(definition);
        return null;
    }

	@Override
	public Object visit(FunctionCallExpression functionCallExpression, Object param) {
		super.visit(functionCallExpression, param);
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
	public Object visit(StructType structType, Object param) {
		StructDefinition struct = structs.get(structType.getName());
		if(struct == null) {
			notifyError("Undefined struct: " + structType.getName(), structType);
		} else {
			structType.setStructDefinition(struct);
		}
		return null;
	}
    
    // private void notifyError(String msg) {
    //     errorManager.notify("Identification", msg);
    // }

    private void notifyError(String msg, Position position) {
        errorManager.notify("Identification", msg, position);
    }

    private void notifyError(String msg, AST node) {
        notifyError(msg, node.start());
    }

}
