// generated with ast extension for cup
// version 0.8
// 29/11/2021 13:28:36


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(IfStart IfStart);
    public void visit(ArrayOptional ArrayOptional);
    public void visit(ActParsOpt ActParsOpt);
    public void visit(FormalParSingle FormalParSingle);
    public void visit(PrintParamOpt PrintParamOpt);
    public void visit(StatementList StatementList);
    public void visit(ExpressionList ExpressionList);
    public void visit(RecordDecl RecordDecl);
    public void visit(Factor Factor);
    public void visit(CondTerm CondTerm);
    public void visit(DeclList DeclList);
    public void visit(Term Term);
    public void visit(RetType RetType);
    public void visit(Condition Condition);
    public void visit(ArrayElement ArrayElement);
    public void visit(MulOp MulOp);
    public void visit(ConstDeclList ConstDeclList);
    public void visit(ExprSingle ExprSingle);
    public void visit(ReturnOpt ReturnOpt);
    public void visit(VarDeclOnly VarDeclOnly);
    public void visit(RelOp RelOp);
    public void visit(RecVarDecl RecVarDecl);
    public void visit(VarDeclList VarDeclList);
    public void visit(MethodTypeName MethodTypeName);
    public void visit(AddOp AddOp);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(Statement Statement);
    public void visit(Expression Expression);
    public void visit(VarDecl VarDecl);
    public void visit(ConstInit ConstInit);
    public void visit(CondFact CondFact);
    public void visit(Declaration Declaration);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(SingleStatement SingleStatement);
    public void visit(IfEnd IfEnd);
    public void visit(FormPars FormPars);
    public void visit(ElseOpt ElseOpt);
    public void visit(ModOperator ModOperator);
    public void visit(DivOperator DivOperator);
    public void visit(MulOperator MulOperator);
    public void visit(Minus Minus);
    public void visit(Plus Plus);
    public void visit(NoPrintParam NoPrintParam);
    public void visit(PrintParam PrintParam);
    public void visit(NoActPars NoActPars);
    public void visit(ActPars ActPars);
    public void visit(ExprFactor ExprFactor);
    public void visit(NewArrayFactor NewArrayFactor);
    public void visit(ConstFactor ConstFactor);
    public void visit(DesFactor DesFactor);
    public void visit(NoExpression NoExpression);
    public void visit(ReturnExpr ReturnExpr);
    public void visit(TermFactor TermFactor);
    public void visit(TermExpr TermExpr);
    public void visit(NotFirstTerm NotFirstTerm);
    public void visit(PositiveTerm PositiveTerm);
    public void visit(NegativeTerm NegativeTerm);
    public void visit(LessRelOp LessRelOp);
    public void visit(LessEqRelOp LessEqRelOp);
    public void visit(GreaterRelOp GreaterRelOp);
    public void visit(GreaterEqRelOp GreaterEqRelOp);
    public void visit(NotEqualRelOp NotEqualRelOp);
    public void visit(EqualRelOp EqualRelOp);
    public void visit(CondFactNoRelOp CondFactNoRelOp);
    public void visit(CondFactRelOp CondFactRelOp);
    public void visit(CondFactSingle CondFactSingle);
    public void visit(CondAnd CondAnd);
    public void visit(OrOp OrOp);
    public void visit(CondTermSingle CondTermSingle);
    public void visit(CondOr CondOr);
    public void visit(NoElseStatement NoElseStatement);
    public void visit(ElseStatement ElseStatement);
    public void visit(ThenStatement ThenStatement);
    public void visit(IfEndStmt IfEndStmt);
    public void visit(IfStartStmt IfStartStmt);
    public void visit(NoExpr NoExpr);
    public void visit(Expr Expr);
    public void visit(ExpressionSingle ExpressionSingle);
    public void visit(ExprList ExprList);
    public void visit(NotArrayElem NotArrayElem);
    public void visit(ArrayElem ArrayElem);
    public void visit(Designator Designator);
    public void visit(ErrorExpr ErrorExpr);
    public void visit(CallExpr CallExpr);
    public void visit(DecExpr DecExpr);
    public void visit(IncExpr IncExpr);
    public void visit(AssignExpr AssignExpr);
    public void visit(NoStmt NoStmt);
    public void visit(StmtListExpr StmtListExpr);
    public void visit(GotoStatement GotoStatement);
    public void visit(PrintStatement PrintStatement);
    public void visit(ReadStatement ReadStatement);
    public void visit(ReturnStatement ReturnStatement);
    public void visit(ContinueStatement ContinueStatement);
    public void visit(BreakStatement BreakStatement);
    public void visit(DoWhileStatement DoWhileStatement);
    public void visit(IfStatement IfStatement);
    public void visit(DesignatorStatementExpr DesignatorStatementExpr);
    public void visit(Label Label);
    public void visit(ListStatement ListStatement);
    public void visit(SingleStmt SingleStmt);
    public void visit(LabeledStmt LabeledStmt);
    public void visit(ReturnVoid ReturnVoid);
    public void visit(ReturnType ReturnType);
    public void visit(NoVars NoVars);
    public void visit(VarDeclOnlyExpr VarDeclOnlyExpr);
    public void visit(NoFormalParam NoFormalParam);
    public void visit(FormalParam FormalParam);
    public void visit(FormParamSingle FormParamSingle);
    public void visit(FormalParameters FormalParameters);
    public void visit(MethodName MethodName);
    public void visit(MethodDecl MethodDecl);
    public void visit(NoMethodDecl NoMethodDecl);
    public void visit(MethodDeclarations MethodDeclarations);
    public void visit(CharVal CharVal);
    public void visit(NumberVal NumberVal);
    public void visit(BooleanVal BooleanVal);
    public void visit(ConstDeclSingle ConstDeclSingle);
    public void visit(ConstDeclarationSingle ConstDeclarationSingle);
    public void visit(ConstDeclarationList ConstDeclarationList);
    public void visit(ConstDecl ConstDecl);
    public void visit(NotArray NotArray);
    public void visit(Array Array);
    public void visit(VarDeclSingle VarDeclSingle);
    public void visit(ErrorVarDeclList ErrorVarDeclList);
    public void visit(VarDeclarationSingle VarDeclarationSingle);
    public void visit(VarDeclarationList VarDeclarationList);
    public void visit(Type Type);
    public void visit(ErrorVarDecl ErrorVarDecl);
    public void visit(VarDeclExpr VarDeclExpr);
    public void visit(NoVarDecl NoVarDecl);
    public void visit(VarDeclarations VarDeclarations);
    public void visit(RecordDeclDerived1 RecordDeclDerived1);
    public void visit(ConstDeclaration ConstDeclaration);
    public void visit(VarDeclaration VarDeclaration);
    public void visit(RecordDeclaration RecordDeclaration);
    public void visit(NoDeclaration NoDeclaration);
    public void visit(DeclarationList DeclarationList);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
