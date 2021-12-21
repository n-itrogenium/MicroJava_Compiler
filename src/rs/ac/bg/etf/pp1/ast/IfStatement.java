// generated with ast extension for cup
// version 0.8
// 21/11/2021 2:56:53


package rs.ac.bg.etf.pp1.ast;

public class IfStatement extends SingleStatement {

    private IfStart IfStart;
    private Condition Condition;
    private IfEnd IfEnd;
    private ThenStatement ThenStatement;
    private ElseOpt ElseOpt;

    public IfStatement (IfStart IfStart, Condition Condition, IfEnd IfEnd, ThenStatement ThenStatement, ElseOpt ElseOpt) {
        this.IfStart=IfStart;
        if(IfStart!=null) IfStart.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.IfEnd=IfEnd;
        if(IfEnd!=null) IfEnd.setParent(this);
        this.ThenStatement=ThenStatement;
        if(ThenStatement!=null) ThenStatement.setParent(this);
        this.ElseOpt=ElseOpt;
        if(ElseOpt!=null) ElseOpt.setParent(this);
    }

    public IfStart getIfStart() {
        return IfStart;
    }

    public void setIfStart(IfStart IfStart) {
        this.IfStart=IfStart;
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public IfEnd getIfEnd() {
        return IfEnd;
    }

    public void setIfEnd(IfEnd IfEnd) {
        this.IfEnd=IfEnd;
    }

    public ThenStatement getThenStatement() {
        return ThenStatement;
    }

    public void setThenStatement(ThenStatement ThenStatement) {
        this.ThenStatement=ThenStatement;
    }

    public ElseOpt getElseOpt() {
        return ElseOpt;
    }

    public void setElseOpt(ElseOpt ElseOpt) {
        this.ElseOpt=ElseOpt;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfStart!=null) IfStart.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
        if(IfEnd!=null) IfEnd.accept(visitor);
        if(ThenStatement!=null) ThenStatement.accept(visitor);
        if(ElseOpt!=null) ElseOpt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfStart!=null) IfStart.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(IfEnd!=null) IfEnd.traverseTopDown(visitor);
        if(ThenStatement!=null) ThenStatement.traverseTopDown(visitor);
        if(ElseOpt!=null) ElseOpt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfStart!=null) IfStart.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(IfEnd!=null) IfEnd.traverseBottomUp(visitor);
        if(ThenStatement!=null) ThenStatement.traverseBottomUp(visitor);
        if(ElseOpt!=null) ElseOpt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfStatement(\n");

        if(IfStart!=null)
            buffer.append(IfStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IfEnd!=null)
            buffer.append(IfEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ThenStatement!=null)
            buffer.append(ThenStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ElseOpt!=null)
            buffer.append(ElseOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfStatement]");
        return buffer.toString();
    }
}
