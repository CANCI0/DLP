// Generated with VGen 2.0.0

package codegeneration.mapl.codefunctions;

import ast.*;
import ast.definition.*;
import ast.type.ArrayType;
import ast.type.BoolType;
import ast.type.CharType;
import ast.type.FloatType;
import ast.type.IntType;
import ast.type.StructType;
import ast.type.Type;
import ast.type.VoidType;
import codegeneration.mapl.*;


public class Metadata extends AbstractCodeFunction {

    public Metadata(MaplCodeSpecification specification) {
        super(specification);
    }


    // class Program(List<VarDefinition> varDefinitions, List<Statement> statements)
    @Override
    public Object visit(Program program, Object param) {

        out("#source \"" + getSpecification().getSourceFile() + "\"");
        metadata(program.definitions());

        return null;
    }
    
    // class Program(List<VarDefinition> varDefinitions, List<Statement> statements)
    @Override
    public Object visit(FunctionDefinition functionDefinition, Object param) {

        out("#func " + functionDefinition.getName());
        functionDefinition.params().forEach(x -> {
        	out("#param " + x.getName() + ":" + getTypeName(x.getType()));
        });
        metadata(functionDefinition.definitions());
        out("#ret " + getTypeName(functionDefinition.getType().orElse(new VoidType())));

        return null;
    }
    
    @Override 
    public Object visit(StructDefinition structDefinition, Object param) {
    
    	out("#type " + structDefinition.getName() + ": {");
    	
    	metadata(structDefinition.attrDefinitions());
    	
    	out("}");
    	
    	return null;
    }
    
    @Override 
    public Object visit(AttrDefinition attrDefinition, Object param){
    	
    	out("\t" + attrDefinition.getName() + ":" + getTypeName(attrDefinition.getType()));
    	
    	return null;
    }
    
    // class VarDefinition(Type type, String name)
    // phase MemoryAllocation { int address }
    @Override
    public Object visit(VarDefinition varDefinition, Object param) {

    	String scope = varDefinition.getScope() == 1? "#global " : "#local ";

        out(scope + varDefinition.getName() + ":" + getTypeName(varDefinition.getType()));

        return null;
    }
    
    //# ------------------------------------------------------------------
    //# Auxiliary methods

    private String getTypeName(Type type) {
        if (type instanceof IntType || type instanceof BoolType)
            return "int";
        if (type instanceof FloatType)
            return "float";
        if(type instanceof CharType)
        	return "char";
        if(type instanceof VoidType)
        	return "void";
        if(type instanceof ArrayType) {
        	ArrayType arrType = (ArrayType) type;
        	return arrType.getIntValue()  + " * " + getTypeName(arrType.getType());
        }
        if(type instanceof StructType) {
        	StructType stType = (StructType) type;
        	return stType.getName();
        }

        // Sealed classes + pattern matching would avoid this situation. Those features were not
        // finished when this code was implemented
        throw new IllegalArgumentException("Unknown Type: " + type);
    }
    

}
