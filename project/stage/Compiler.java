/*
 * Compiler.java
 *
 * A starting place for the unamed language compiler for CSC 435/535
 *
 */

import org.antlr.runtime.*;
import java.io.*;

import AST.Program;
import AST.Visitor;
import AST.TypeCheckVisitor;
import IR.IRGenerator;
import IR.IR2Jasmin;

public class Compiler {
	public static void main (String[] args) throws Exception {
		ANTLRInputStream input;

		if (args.length == 0 ) {
			System.out.println("Usage: Test filename.ul");
			return;
		} else {
			input = new ANTLRInputStream(new FileInputStream(args[0]));
		}

		boolean printIR = false;
		if (args.length == 2) {
			printIR = args[1].equals("-ir");
		}

		// The name of the grammar here is "ulNoActions",
		// so ANTLR generates ulNoActionsLexer and ulNoActionsParser
		ulNoActionsLexer lexer = new ulNoActionsLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ulNoActionsParser parser = new ulNoActionsParser(tokens);

		IRGenerator irGen = new IRGenerator();
		IR2Jasmin jasminGen = new IR2Jasmin();
		try {
			Program p = parser.program();
			p.accept(new TypeCheckVisitor(p));
			p.accept(irGen);
			if (!printIR) {
				irGen.getIRProgram().accept(jasminGen);
			}
		}
		catch (RecognitionException e )	{
			// A lexical or parsing error occured.
			// ANTLR will have already printed information on the
			// console due to code added to the grammar.  So there is
			// nothing to do here.
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		if (printIR) {
			irGen.printIRProgram();
		}
	}
}
