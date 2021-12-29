package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import rs.ac.bg.etf.pp1.SymbolTable;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {
	
	private int varCount;
	private int mainPc;
	private int previousPC = 0;
	
	private Stack<ArrayList<Integer>> thenJumps = new Stack<>();
	private Stack<ArrayList<Integer>> elseJumps = new Stack<>();
	private HashMap<String, List<Integer>> gotoJumps = new HashMap<String, List<Integer>>();
	
	public int getMainPc() {
		return mainPc;
	}
	
	// MAIN METODA
	public void visit(MethodName method) {
		if ("main".equalsIgnoreCase(method.getMethodName())) {
			mainPc = Code.pc;
		}
		method.obj.setAdr(Code.pc);
		
		// Generate the entry.
		Code.put(Code.enter);
		Code.put(method.obj.getLevel());
		Code.put(method.obj.getLocalSymbols().size());
	}
	
	
	
	// DEFINICIJA LABELE
	public void visit(LabeledStmt labeledStmt) { 
		String labelName = labeledStmt.getLabel().getLabelName();
		SymbolTable.find(labelName).setAdr(previousPC);
		System.out.println("Adresa labele: " + previousPC);
		if (gotoJumps.containsKey(labelName)) {
			List<Integer> addrList = gotoJumps.get(labelName);
			for (Integer i : addrList) { 
				System.out.println("Adresa na kojoj se vrsi fixup: " + i);
				Code.fixup(i); 
				System.out.println("Novi sadrzaj: " + Code.get(i)+Code.get(i+1));
			}
			gotoJumps.remove(labelName);
		}
		previousPC = Code.pc;
		System.out.println("PC = " + Code.pc);
		/*gotoJumps.forEach((address, label) -> {
				if (label == labelName)
					Code.fixup(address);
			}
		)*/
	}
	
	public void visit(SingleStmt stmt) {
		previousPC = Code.pc;
		System.out.println("PC = " + Code.pc);
	}
	
	// SKOK NA LABELU
	public void visit(GotoStatement gotoStatement) { 
		String label = gotoStatement.getLabelName();
		int address = SymbolTable.find(label).getAdr();
		if (!gotoJumps.containsKey(label))
			gotoJumps.put(label, new LinkedList<Integer>());
		if (address == -1) {
			Code.putJump(0);
			gotoJumps.get(label).add(Code.pc - 2);
		}
		else
			Code.putJump(address);
		System.out.println("Adresa labele " + gotoStatement.getLabelName() + ": " + address);
	}
	
	
	// DEKLARACIJA PROMENLJIVE
	public void visit(VarDecl VarDecl) {
		varCount++;
	}
	
	
	// DEKLARACIJA METODE
	public void visit(MethodDecl MethodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	
	// RETURN SA IZRAZOM
	public void visit(ReturnExpr ReturnExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	
	// RETURN
	public void visit(NoExpression ReturnNoExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	
	// DES FACTOR
	public void visit(DesFactor expression) {
		Obj obj = expression.getDesignator().obj;
		if (expression.getDesignator().getArrayElement() instanceof ArrayElem) {
			Code.load(obj);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
			if (obj.getType().getElemType().getKind() == Struct.Int)
				Code.put(Code.aload);
			else
				Code.put(Code.baload);
			
		} else
			Code.load(obj);
	}
	
	
	// DODELA VREDNOSTI
	public void visit(AssignExpr assignment) {
		Obj obj = assignment.getDesignator().obj;
		if (assignment.getDesignator().getArrayElement() instanceof ArrayElem) {
			Code.load(obj);
			Code.put(Code.dup_x2);
			Code.put(Code.pop);
			if (obj.getType().getElemType().getKind() == Struct.Int)
				Code.put(Code.astore);
			else
				Code.put(Code.bastore);
			
		} else
			Code.store(obj);
	}
	
	
	// INKREMENTIRANJE
	public void visit(IncExpr inc) {
		Code.load(inc.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(inc.getDesignator().obj);
	}
	
	
	// DEKREMENTIRANJE
	public void visit(DecExpr dec) {
		Code.load(dec.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(dec.getDesignator().obj);
	}
	
	
	// NUMERICKA KONSTANTA
	public void visit(NumberVal numberVal) {
		/*Obj obj = SymbolTable.insert(Obj.Con, "$", numberVal.struct);
		obj.setAdr(numberVal.getNumValue());
		obj.setLevel(0);
		Code.load(obj);*/
		//Code.load(new Obj(Obj.Con, "$", numberVal.struct, numberVal.getNumValue(), 0));
		Code.loadConst(numberVal.getNumValue());
	}
	
	
	// KARAKTER KONSTANTA
	public void visit(CharVal charVal) {
		//Code.load(new Obj(Obj.Con, "$", charVal.struct, charVal.getCharValue(), 0));
		Code.loadConst(charVal.getCharValue());
	}
	
	
	// LOGICKA KONSTANTA
	public void visit(BooleanVal booleanVal) {
		//Code.load(new Obj(Obj.Con, "$", booleanVal.struct, booleanVal.getBoolValue(), 0));
		Code.loadConst(booleanVal.getBoolValue());
	}
	
	
	// DESIGNATOR
	/*public void visit(Designator designator) {
		SyntaxNode parent = designator.getParent();
		if (AssignExpr.class != parent.getClass()) {
			Code.load(designator.obj);
		}
	}*/
	
	
	// NEGATIVAN IZRAZ
	public void visit(NegativeTerm expression) {
		Code.put(Code.neg);
	}
	
	
	// ZBIR I RAZLIKA
	public void visit(NotFirstTerm expression) {
		if (expression.getAddOp() instanceof Plus)
			Code.put(Code.add);
		if (expression.getAddOp() instanceof Minus)
			Code.put(Code.sub);
	}
	
	
	// MNOZENJE I DELJENJE
	public void visit(TermExpr expression) {
		if (expression.getMulOp() instanceof MulOperator)
			Code.put(Code.mul);
		if (expression.getMulOp() instanceof DivOperator)
			Code.put(Code.div);
		if (expression.getMulOp() instanceof ModOperator)
			Code.put(Code.rem);
	}
	
	
	// ISPIS
	public void visit(PrintStatement print) {
		int width = 1;
		if (print.getExpression().struct.getKind() == Struct.Int || print.getExpression().struct.getKind() == Struct.Bool)
			width = 5;
		if (print.getPrintParamOpt() instanceof PrintParam)
			width = ((PrintParam) print.getPrintParamOpt()).getPrintWidth();
		Code.loadConst(width);
		
		if (print.getExpression().struct.getKind() == Struct.Int || print.getExpression().struct.getKind() == Struct.Bool)
			// da li width treba da bude 5?
			Code.put(Code.print);
		else 
			Code.put(Code.bprint);
	}
	
	
	// CITANJE
	public void visit(ReadStatement read) {
		if (read.getDesignator().obj.getType().getKind() == Struct.Int)
			Code.put(Code.read);
		else
			Code.put(Code.bread);
		Code.store(read.getDesignator().obj);
	}
	
	
	// NOVI NIZ
	public void visit(NewArrayFactor newArray) {
		Code.put(Code.newarray);
		Code.put((newArray.getType().struct.getKind() == Struct.Int) ? 1 : 0);
	}
	
	/*
	
	// POCETAK IF USLOVA
	public void visit(IfStartStmt ifStart) {
		thenJumps.add(new ArrayList<>());
		elseJumps.add(new ArrayList<>());
	}
	
	
	// KRAJ IF USLOVA
	public void visit(IfEndStmt ifEnd) {
		//while (!thenJumps.peek().isEmpty())
		//	Code.fixup(thenJumps.peek().remove(0));
		thenJumps.pop().forEach(Code::fixup);
	}
	
	
	// USLOV SA RELACIONIM OPERATOROM
	 public void visit(CondFactRelOp condition) {
		RelOp relOp = condition.getRelOp();
		
		elseJumps.peek().add(Code.pc + 1);
		
		if (relOp instanceof EqualRelOp)
			Code.putFalseJump(Code.eq, 0);
		
		if (relOp instanceof NotEqualRelOp)
			Code.putFalseJump(Code.ne, 0);
		
		if (relOp instanceof GreaterRelOp)
			Code.putFalseJump(Code.gt, 0);
		
		if (relOp instanceof GreaterEqRelOp)
			Code.putFalseJump(Code.ge, 0);
		
		if (relOp instanceof LessRelOp)
			Code.putFalseJump(Code.lt, 0);
		
		if (relOp instanceof LessEqRelOp)
			Code.putFalseJump(Code.le, 0);
		
		
	} 
	
	
	// USLOV BEZ RELACIONOG OPERATORA
	public void visit(CondFactNoRelOp condition) {
		Code.loadConst(0);
		elseJumps.peek().add(Code.pc + 1);
		Code.putFalseJump(Code.ne, 0);
	}
	
	
	// DISJUNKCIJA
	public void visit(OrOp orOp) {
		thenJumps.peek().add(Code.pc + 1);
		Code.putJump(0);
		//while (!elseJumps.peek().isEmpty())
		//	Code.fixup(elseJumps.peek().remove(0));
		elseJumps.peek().forEach(Code::fixup);
		elseJumps.peek().clear();
	}
	
	
	// THEN GRANA
	public void visit(ThenStatement thenStmt) {
		if (((IfStatement) thenStmt.getParent()).getElseOpt() instanceof ElseStatement) {
			thenJumps.add(new ArrayList<>());
			thenJumps.peek().add(Code.pc + 1);
			Code.putJump(0);
		}
		//while (!elseJumps.peek().isEmpty())
		//	Code.fixup(elseJumps.peek().remove(0));
		elseJumps.pop().forEach(Code::fixup);
	} 
	
	// ELSE GRANA
	public void visit(ElseStatement elseStmt) {
		//while (!thenJumps.peek().isEmpty())
		//	Code.fixup(thenJumps.peek().remove(0));
		thenJumps.pop().forEach(Code::fixup);
	}
	
	*/
	
}
