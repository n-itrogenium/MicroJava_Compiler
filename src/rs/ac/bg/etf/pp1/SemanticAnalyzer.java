package rs.ac.bg.etf.pp1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.ac.bg.etf.pp1.CompilerExt;
import rs.ac.bg.etf.pp1.test.CompilerError;


public class SemanticAnalyzer extends VisitorAdaptor {

	boolean errorDetected = false;
	int printCallCount = 0;
	Obj currentMethod = null;
	Struct currentType = SymbolTable.noType;
	boolean returnFound = false;
	int nVars;
	HashMap<SyntaxNode, String> pendingJumps = new HashMap<SyntaxNode, String>();

	Logger log = Logger.getLogger(getClass());
	

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
		CompilerExt.errors.add(new CompilerError(line, msg.toString(), CompilerError.CompilerErrorType.SEMANTIC_ERROR));
	}
	

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	
	// PROGRAM
	public void visit(Program program) {
		Obj mainMethod = SymbolTable.find("main");
		
		if (mainMethod == SymbolTable.noObj) 
			report_error("Nije pronadjena main metoda", program);
		else if (!mainMethod.getType().equals(SymbolTable.noType) || mainMethod.getLevel() > 0)
				report_error("Neispravan potpis main metode", program);
		
		nVars = SymbolTable.currentScope.getnVars();
		SymbolTable.chainLocalSymbols(program.getProgName().obj);
		SymbolTable.closeScope();
	}
	
	public void visit(ProgName progName) {
		progName.obj = SymbolTable.insert(Obj.Prog, progName.getPName(), SymbolTable.noType);
		SymbolTable.openScope();     	
	}
	
	// DEFINICIJA LABELE
	public void visit(Label label) {
		String labelName = label.getLabelName();
		Obj labelObj = SymbolTable.find(labelName);
		if (labelObj != SymbolTable.noObj) {
			report_error("Labela '" + labelName + "' je vec definisana", label);
		}
		else { 
			SymbolTable.insert(Obj.Con, labelName, SymbolTable.noType).setAdr(-1);
			pendingJumps.values().remove(labelName);
			report_info("Definisana labela '" + labelName + "'", label);
		}
	}
	
	// SKOK NA LABELU
	public void visit(GotoStatement gotoStatement) {
		String label = gotoStatement.getLabelName();
		Obj labelObj = SymbolTable.find(label);
		if (labelObj == SymbolTable.noObj && !pendingJumps.values().contains(label)) {
			pendingJumps.put(gotoStatement, label);
		}
	}
	

	// TIP
	public void visit(Type type) {
		Obj typeNode = SymbolTable.find(type.getTypeName());
		if (typeNode == SymbolTable.noObj) {
			report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola", type);
			type.struct = SymbolTable.noType;
		} 
		else {
			if (Obj.Type == typeNode.getKind()) {
				type.struct = typeNode.getType();
			} 
			else {
				report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip ", type);
				type.struct = SymbolTable.noType;
			}
		}  
		currentType = type.struct;
	}
	
	
	// PROMENLJIVA
	public void visit(VarDeclSingle varDecl) {
		Obj obj;
		if (varDecl.getArrayOptional() instanceof Array) {
			obj = SymbolTable.insert(Obj.Var, varDecl.getVarName(), new Struct(Struct.Array));
			obj.getType().setElementType(currentType);
		}
		else
			obj = SymbolTable.insert(Obj.Var, varDecl.getVarName(), currentType);
		report_info("Deklarisana promenljiva " + varDecl.getVarName() + " tipa " + obj.getType().getKind(), varDecl);
		/*Struct type = (varDecl.getArrayOptional() instanceof Array) ? 
				new Struct(Struct.Array, currentType) : currentType;
		
		SymbolTable.insert(Obj.Var, varDecl.getVarName(), type);*/
	}
	
	
	// KONSTANTA	
	public void visit(ConstDeclSingle constDecl) {
		int kind = currentType.getKind();
		if (kind != Struct.Char &&
			kind != Struct.Int &&
			kind != Struct.Bool) {
			report_error("Nije moguce definisati konstantu ovog tipa", constDecl);
		} 
		else {
			Obj cnst = SymbolTable.find(constDecl.getConstName());
			if (cnst != SymbolTable.noObj) {
				report_error("Simbol '" + constDecl.getConstName() + "' je vec definisan", constDecl);
			}
			else {
				int initType = Struct.None;
				int value = 0;
				ConstInit init = constDecl.getConstInit();
				if (init instanceof CharVal) {
					initType = Struct.Char; 
					value = ((CharVal) init).getCharValue();
				}
				else if (init instanceof NumberVal) {
					initType = Struct.Int;
					value = ((NumberVal) init).getNumValue();
				}
				else if (init instanceof BooleanVal) {
					initType = Struct.Bool;
					value = ((BooleanVal) init).getBoolValue();
				}
				
				if (kind != initType) {
					report_error("Dodeljena vrednost nije istog tipa kao konstanta", constDecl);
				} else {
					SymbolTable.insert(Obj.Con, constDecl.getConstName(), currentType).setAdr(value);
				}				
			}
		}
	}

	
	// POTPIS MAIN METODE
	public void visit(MethodName methodName) {
		methodName.obj = SymbolTable.insert(Obj.Meth, "main", SymbolTable.noType);
		currentMethod = methodName.obj;
		SymbolTable.openScope();
	}
	
	// MAIN METODA
	public void visit(MethodDecl methodDecl) {
		SymbolTable.chainLocalSymbols(currentMethod);
		SymbolTable.closeScope();
		currentMethod = null;
		pendingJumps.forEach((statement, label) -> report_error("Labela '" + label + "' nije definisana", statement));
	}
	

	// DODELA VREDNOSTI
	public void visit(AssignExpr assignment) {
		Designator designator = assignment.getDesignator();
		Expression expression = assignment.getExpression();
		
		Obj desObj = SymbolTable.find(designator.getDesignatorName());
		if ((desObj.getKind() != Obj.Var) && (desObj.getKind() != Obj.Elem))
			report_error("Vrednost izraza je moguce dodeliti samo promenljivoj ili elementu niza", assignment);
		
		if (!expression.struct.assignableTo(desObj.getType())) {
			if (expression.struct.getKind() == Struct.Array) {
				//if (!expression.struct.getElemType().assignableTo(desObj.getType()))
				if (desObj.getType().getKind() != Struct.Array)
					report_error("Nekompatibilni tipovi u dodeli vrednosti ", assignment);
				else if (!expression.struct.getElemType().assignableTo(desObj.getType().getElemType()))
					report_error("Nekompatibilni tipovi u dodeli vrednosti ", assignment);
			}
			else if (desObj.getType().getKind() == Struct.Array) {
				if (!(desObj.getFpPos() == 1 && expression.struct.assignableTo(desObj.getType().getElemType())))
					report_error("Nekompatibilni tipovi u dodeli vrednosti ", assignment);
			}
			else
				report_error("Nekompatibilni tipovi u dodeli vrednosti ", assignment);
		}
	}
	
	
	// INKREMENTIRANJE
	public void visit(IncExpr increment) {
		Obj designator = increment.getDesignator().obj;
		if ((designator.getKind() != Obj.Var) && (designator.getKind() != Obj.Elem)
				|| designator.getType().getKind() != Struct.Int) {
			if (!(designator.getType().getKind() == Struct.Array
					&& designator.getType().getElemType().getKind() == Struct.Int
					&& designator.getFpPos() == 1))
				report_error("Moguce je inkrementirati samo promenljivu ili element niza celobrojnog tipa", increment);
		}
	}
	
	
	// DEKREMENTIRANJE
	public void visit(DecExpr decrement) {
		Obj designator = decrement.getDesignator().obj;
		if ((designator.getKind() != Obj.Var) && (designator.getKind() != Obj.Elem)
				|| designator.getType().getKind() != Struct.Int) {
			if (!(designator.getType().getKind() == Struct.Array
					&& designator.getType().getElemType().getKind() == Struct.Int
					&& designator.getFpPos() == 1))
				report_error("Moguce je inkrementirati samo promenljivu ili element niza celobrojnog tipa", decrement);
		}
	}
	

	// ISPISIVANJE
	public void visit(PrintStatement printStmt){
		int exprType = printStmt.getExpression().struct.getKind();
		if ((exprType != Struct.Int) && (exprType != Struct.Char) && (exprType != Struct.Bool)) {
			report_error("Nije moguce vrsiti ispis zadatog tipa", printStmt);
		}
		printCallCount++;
	}
	
	
	// CITANJE
	public void visit(ReadStatement readStmt) {
		Designator designator = readStmt.getDesignator();
		if ((designator.obj.getKind() != Obj.Var) && (designator.obj.getKind() != Obj.Elem)) {
			report_error("Upis je moguce vrsiti samo u promenljivu ili element niza", readStmt);
		}
		if ((designator.obj.getType().getKind() != Struct.Int) &&
			(designator.obj.getType().getKind() != Struct.Char) &&
			(designator.obj.getType().getKind() != Struct.Bool) &&
			!(designator.obj.getType().getKind() == Struct.Array && designator.obj.getFpPos() == 1)) {
			report_error("Upis je moguce vrsiti samo u celobrojni, znakovni ili logicki tip", readStmt);
		}
	}
 
	
	// DESIGNATOR
	public void visit(Designator designator){
		Obj obj = SymbolTable.find(designator.getDesignatorName());
		if (obj == SymbolTable.noObj) { 
			report_error("Ime " + designator.getDesignatorName() + " nije deklarisano! ", designator);
		}
		designator.obj = obj;
		if (designator.getArrayElement() instanceof ArrayElem) // koristimo polje druge namene za ove potrebe
			designator.obj.setFpPos(1);
		else
			designator.obj.setFpPos(0);
	}
	
	
	// DISJUNKCIJA
	public void visit(CondOr condition) {
		if (condition.getCondTerm().struct.getKind() != Struct.Bool) { 
			report_error("Uslov mora biti logickog tipa", condition);
		}
		condition.struct = condition.getCondTerm().struct;
	}
	
	
	// KONJUNKCIJA
	public void visit(CondAnd condition) {
		if (condition.getCondFact().obj.getType().getKind() != Struct.Bool) { 
			report_error("Uslov mora biti logickog tipa", condition);
		}
		condition.struct = condition.getCondFact().obj.getType(); 
	}
	
	
	// COND TERM
	public void visit(CondTermSingle condition) {
		if (condition.getCondTerm().struct.getKind() != Struct.Bool) { 
			report_error("Uslov mora biti logickog tipa", condition);
		}
		condition.struct = condition.getCondTerm().struct;
	}
	
	
	// POJEDINACNI USLOV
	public void visit(CondFactSingle condition) {
		condition.struct = condition.getCondFact().obj.getType(); 
	}
	
	
	// USLOV SA RELACIONIM OPERATOROM
	public void visit(CondFactRelOp condition) {
		Struct expr1Type = condition.getExpression().struct;
		Struct expr2Type = condition.getExpression1().struct;
		condition.obj = new Obj(0, "", SymbolTable.boolType);
		
		if (!expr1Type.compatibleWith(expr2Type)) { 
			report_error("Tipovi izraza se ne poklapaju", condition);
		}
		
		if ((expr1Type.getKind() == Struct.Array && expr2Type.getKind() == Struct.Array
				&& !(condition.getRelOp() instanceof EqualRelOp || condition.getRelOp() instanceof NotEqualRelOp))) {
			report_error("Neodgovarajuci tip relacionog operatora", condition);
		}
	}
	
	
	// PROST USLOV
	public void visit(CondFactNoRelOp condition) {
		Struct exprType = condition.getExpression().struct;
		condition.obj = new Obj(0, "", SymbolTable.boolType);
		
		if (exprType != SymbolTable.boolType) {
			report_error("Tip izraza mora biti logicki", condition);
		}
	}
	
	
	// NEGATIVAN BROJ
	public void visit(NegativeTerm expression) {
		Struct type = expression.getTerm().struct;
		if (type.getKind() != Struct.Int) {
			//if (!(type.getKind() == Struct.Array && type.getElemType().getKind() == Struct.Int))
				report_error("Tip izraza mora biti celobrojni", expression);
		}
		expression.struct = expression.getTerm().struct;
	}
	
	
	// POZITIVAN BROJ
	public void visit(PositiveTerm expression) {
		expression.struct = expression.getTerm().struct;
	}
	
	
	// ZBIR I RAZLIKA
	public void visit(NotFirstTerm expression) {
		Struct expressionType = expression.getExpression().struct;
		Struct termType = expression.getTerm().struct;
		expression.struct = expression.getTerm().struct;
		if (!expressionType.compatibleWith(termType)) {
			report_error("Tipovi izraza nisu kompatibilni", expression);
		}
		if (!(expressionType.getKind() == Struct.Int && termType.getKind() == Struct.Int)) {
			//if (!(expressionType.getKind() == Struct.Array && expressionType.getElemType().getKind() == Struct.Int) ||
			//		!(termType.getKind() == Struct.Array && termType.getElemType().getKind() == Struct.Int))
				report_error("Tipovi izraza moraju biti celobrojni", expression);
		}
	}
	
	
	public void visit(TermFactor term) {
		term.struct = term.getFactor().struct;    	
	}
	
	
	public void visit(TermExpr termExpr) {
		termExpr.struct = termExpr.getFactor().struct;
		Struct termType = termExpr.getTerm().struct;
		if (!(termExpr.struct.getKind() == Struct.Int && termType.getKind() == Struct.Int)) {
			//if (!(termExpr.struct.getKind() == Struct.Array && termExpr.struct.getElemType().getKind() == Struct.Int) ||
			//		!(termType.getKind() == Struct.Array && termType.getElemType().getKind() == Struct.Int))
				report_error("Tipovi izraza moraju biti celobrojni", termExpr);
		}
	}
	
	
	public void visit(NewArrayFactor newArray) {
		Struct type = newArray.getExpression().struct;
		if (type.getKind() != Struct.Int) {
			//if (!(type.getKind() == Struct.Array && type.getElemType().getKind() == Struct.Int) )
				report_error("Tipovi izraza moraju biti celobrojni", newArray);
		}
		newArray.struct = new Struct(Struct.Array, newArray.getType().struct);
		//report_info("New Array Factor tip: " + newArray.struct.getKind(), newArray);
	}
	
	
	public void visit(ExprFactor exprFactor) {
		exprFactor.struct = exprFactor.getExpression().struct;
	}
	
	
	public void visit(DesFactor desFactor) {
		//desFactor.struct = desFactor.getDesignator().obj.getType();
		Obj designator = desFactor.getDesignator().obj;
		desFactor.struct = (designator.getFpPos() == 1) ?
				designator.getType().getElemType() : designator.getType();
	}
	
	
	public void visit(ConstFactor constFactor) {
		ConstInit constInit = constFactor.getConstInit();
		if (constInit instanceof NumberVal)
			constInit.struct = SymbolTable.find("int").getType();
		else if (constInit instanceof CharVal)
			constInit.struct = SymbolTable.find("char").getType();
		else if (constInit instanceof BooleanVal)
			constInit.struct = SymbolTable.find("bool").getType();
		constFactor.struct = constInit.struct;
	}
	
	
	// [IZRAZ]
	public void visit(ArrayElem arrayElement) {
		Expression expression = arrayElement.getExpression();
		int expressionType = arrayElement.getExpression().struct.getKind();
		if (expressionType != Struct.Int) {
			//if (!(expressionType == Struct.Array && expression.struct.getElemType().getKind() == Struct.Int)) {
				report_error("Tipovi izraza moraju biti celobrojni, tip" + expressionType, arrayElement);
			//}
		}
	}
	
	
	public boolean passed() {
		return !errorDetected;
	}
	
}

