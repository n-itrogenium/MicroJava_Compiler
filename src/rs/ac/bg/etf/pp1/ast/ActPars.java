// generated with ast extension for cup
// version 0.8
// 21/11/2021 2:56:53


package rs.ac.bg.etf.pp1.ast;

public class ActPars extends ActParsOpt {

    private ExpressionList ExpressionList;

    public ActPars (ExpressionList ExpressionList) {
        this.ExpressionList=ExpressionList;
        if(ExpressionList!=null) ExpressionList.setParent(this);
    }

    public ExpressionList getExpressionList() {
        return ExpressionList;
    }

    public void setExpressionList(ExpressionList ExpressionList) {
        this.ExpressionList=ExpressionList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExpressionList!=null) ExpressionList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExpressionList!=null) ExpressionList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExpressionList!=null) ExpressionList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActPars(\n");

        if(ExpressionList!=null)
            buffer.append(ExpressionList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActPars]");
        return buffer.toString();
    }
}
