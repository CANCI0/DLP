package codegeneration;

import java.util.List;

import ast.*;
import ast.definition.FunctionDefinition;
import ast.definition.StructDefinition;
import ast.definition.VarDefinition;
import ast.type.Type;
import visitor.DefaultVisitor;

// This class will be implemented in memory allocation phase

public class MemoryAllocation extends DefaultVisitor {

    public void process(AST ast) {
        ast.accept(this, null);
    }

    // class Program(List<Definition> definitions)
    @Override
    public Object visit(Program program, Object param) {
    	super.visit(program, param);
    	
    	int currentAddress = 0;
    	
        for (var definition : program.getDefinitions()) {
        	definition.accept(this, param);
        	if(definition instanceof VarDefinition) {
            	VarDefinition def = (VarDefinition) definition;
            	def.setAddress(currentAddress);
                currentAddress += def.getType().getSize();
        	}
        }
        return null;
    }
    
	// class StructDefinition(String name, List<AttrDefinition> attrDefinitions)
	@Override
	public Object visit(StructDefinition structDefinition, Object param) {
		super.visit(structDefinition, param);
		
		int currentAddress = 0;
		
		for(AttrDefinition attr : structDefinition.getAttrDefinitions()) {
			attr.setAddress(currentAddress);
			currentAddress += attr.getType().getSize();
		}

		return null;
	}

	// class FunctionDefinition(String name, List<VarDefinition> params, Optional<Type> type, List<VarDefinition> definitions, List<Statement> statements)
	@Override
	public Object visit(FunctionDefinition functionDefinition, Object param) {
		super.visit(functionDefinition, param);
		
		int definitionsAddress = 0;
		
		for(VarDefinition def : functionDefinition.getDefinitions()) {
			definitionsAddress -= def.getType().getSize();
			def.setAddress(definitionsAddress);			
		}
		
		//Se reserva memoria para la direcci�n de retorno
		
		int paramsAddress = 4;
		
		List<VarDefinition> params = functionDefinition.getParams();
		for(int i = params.size() - 1 ; i >= 0 ; i--) {
			VarDefinition param_ = params.get(i);
			param_.setAddress(paramsAddress);
			paramsAddress += param_.getType().getSize();
		}
		
		return null;
	}

}
