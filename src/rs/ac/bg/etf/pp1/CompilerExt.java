package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.etf.pp1.mj.runtime.Code;

import rs.ac.bg.etf.pp1.test.Compiler;
import rs.ac.bg.etf.pp1.test.CompilerError;

public class CompilerExt implements Compiler {
	public static List<CompilerError> errors;
	@Override
	public List<CompilerError> compile(String sourceFilePath, String outputFilePath) {
		Logger log = Logger.getLogger(MJParserTest.class);
		/*if (args.length < 2) {
			log.error("Not enough arguments supplied! Usage: MJParser <source-file> <obj-file> ");
			return;
		}*/
		errors = new LinkedList<CompilerError>();
		File sourceCode = new File(sourceFilePath);
		if (!sourceCode.exists()) {
			log.error("Source file [" + sourceCode.getAbsolutePath() + "] not found!");
			return null;
		}
			
		log.info("Compiling source file: " + sourceCode.getAbsolutePath());
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);
			MJParser p = new MJParser(lexer);
	        Symbol s = p.parse();  //pocetak parsiranja
	        SyntaxNode prog = (SyntaxNode)(s.value);
	        
			SymbolTable.init(); // Universe scope
			SemanticAnalyzer semanticCheck = new SemanticAnalyzer();
			prog.traverseBottomUp(semanticCheck);
			
	        log.info("Print calls = " + semanticCheck.printCallCount);
	        SymbolTable.dump();
	        
	        if (!p.errorDetected /*&& semanticCheck.passed()*/) {
	        	/*File objFile = new File(outputFilePath);
	        	log.info("Generating bytecode file: " + objFile.getAbsolutePath());
	        	if (objFile.exists())
	        		objFile.delete();
	        	
	        	// Code generation...
	        	CodeGenerator codeGenerator = new CodeGenerator();
	        	prog.traverseBottomUp(codeGenerator);
	        	Code.dataSize = semanticCheck.nVars;
	        	Code.mainPc = codeGenerator.getMainPc();
	        	Code.write(new FileOutputStream(objFile));*/
	     
	        	log.info("Parsiranje uspesno zavrseno!");
	        	
	        }
	        else {
	        	log.error("Parsiranje NIJE uspesno zavrseno!");
	        	return errors;
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e1) {
					log.error(e1.getMessage(), e1);
				}
		}
		return null;
	}

}
