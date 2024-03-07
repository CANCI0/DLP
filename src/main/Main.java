package main;

import java.io.*;

import org.antlr.v4.runtime.*;

import ast.*;
import visitor.*;

import parser.*;
import semantic.*;
import codegeneration.*;

/**
 * Class that starts the compiler and invokes all its phases.
 *
 * Normally, it won't be needed to modify this file. Instead, modify
 * the files for each phase (which are called from here):
 * - For Lexical Analysis, change 'Lexicon.g4'.
 * - For Syntax Analysis, change 'Grammar.g4'.
 * - For Semantic Analysis, change 'Identification.java' and 'TypeChecking.java'.
 * - For Code Generation, change 'MemoryAllocation.java' and 'MaplCodeSpecification.java'.
 */

public class Main {
    public static final String TEST_FILE = "3. Variables.txt"; // Used only in development
    public static final String OUTPUT_FILE = "output.txt";

    public static void main(String[] args) throws Exception {
        ErrorManager errorManager = new ErrorManager();

        var inputFile = TEST_FILE; // TODO: Replace `TEST_FILE` with `args[0]` in the final version

        System.out.println("\nCompiler started.\n");

        AST ast = compile(inputFile, errorManager);
        if (errorManager.errorsCount() > 0)
            System.out.println("\n" + errorManager.errorsCount() + " errors detected.");

        System.out.println("\nCompiler finished.\n");

        AstPrinter.toHtml(inputFile, ast, "AST"); // Class generated by VGen (optional)
        //AstCode.toPrint(inputFile, ast);
    }

    // Method that coordinates all compiler phases
    public static AST compile(String sourceName, ErrorManager errorManager) throws IOException {

        //$ 1. Lexical and Syntax analysis -----------------------------
        GrammarLexer lexer = new GrammarLexer(CharStreams.fromFileName(sourceName));
        GrammarParser parser = new GrammarParser(new CommonTokenStream(lexer));

        AST ast = null;
        // IMPORTANT: When the AST has been generated, swap the following two lines of code.
        // parser.program();
        ast = parser.program().ast;

        if (parser.getNumberOfSyntaxErrors() > 0) { // Syntax error detected (ANTLR omits lexical errors)
            errorManager.notify("Compilaton finished due to syntax errors.");
            return null;
        }

        //$ 2. Semantic Analysis -----------------------------
        var analyzer = new SemanticAnalisys(errorManager);
        analyzer.analize(ast);
        if (errorManager.errorsCount() > 0)
            return ast;

        //$ 3. Code Generation -----------------------------------
        File sourceFile = new File(sourceName);
        Writer out = new FileWriter(new File(sourceFile.getParent(), OUTPUT_FILE));

        var generator = new CodeGeneration();
        generator.generate(sourceFile.getName(), ast, out);
        out.close();

        return ast;
    }
}
