// Generated with VGen 2.0.0

package codegeneration.mapl.codefunctions;

import ast.*;
import ast.definition.*;
import ast.type.CharType;
import ast.type.FloatType;
import ast.type.IntType;
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
        metadata(program.getDefinitions().stream());

        return null;
    }
    
    // class Program(List<VarDefinition> varDefinitions, List<Statement> statements)
    @Override
    public Object visit(FunctionDefinition functionDefinition, Object param) {

        out("#func " + functionDefinition.getName());
        out("#ret " + getTypeName(functionDefinition.getType().orElse(new VoidType())));

        return null;
    }
    
    // class VarDefinition(Type type, String name)
    // phase MemoryAllocation { int address }
    @Override
    public Object visit(VarDefinition varDefinition, Object param) {

        out("#GLOBAL " + varDefinition.getName() + ":" + getTypeName(varDefinition.getType()));

        return null;
    }
    
    //# ------------------------------------------------------------------
    //# Auxiliary methods

    private String getTypeName(Type type) {
        if (type instanceof IntType)
            return "int";
        if (type instanceof FloatType)
            return "float";
        if(type instanceof CharType)
        	return "char";
        if(type instanceof VoidType)
        	return "void";

        // Sealed classes + pattern matching would avoid this situation. Those features were not
        // finished when this code was implemented
        throw new IllegalArgumentException("Unknown Type: " + type);
    }
    

}
