// generated with ast extension for cup
// version 0.8
// 28/11/2021 11:49:54


package rs.ac.bg.etf.pp1.ast;

public class ExprList extends ExpressionList {

    private ExpressionList ExpressionList;
    private ExprSingle ExprSingle;

    public ExprList (ExpressionList ExpressionList, ExprSingle ExprSingle) {
        this.ExpressionList=ExpressionList;
        if(ExpressionList!=null) ExpressionList.setParent(this);
        this.ExprSingle=ExprSingle;
        if(ExprSingle!=null) ExprSingle.setParent(this);
    }

    public ExpressionList getExpressionList() {
        return ExpressionList;
    }

    public void setExpressionList(ExpressionList ExpressionList) {
        this.ExpressionList=ExpressionList;
    }

    public ExprSingle getExprSingle() {
        return ExprSingle;
    }

    public void setExprSingle(ExprSingle ExprSingle) {
        this.ExprSingle=ExprSingle;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExpressionList!=null) ExpressionList.accept(visitor);
        if(ExprSingle!=null) ExprSingle.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExpressionList!=null) ExpressionList.traverseTopDown(visitor);
        if(ExprSingle!=null) ExprSingle.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExpressionList!=null) ExpressionList.traverseBottomUp(visitor);
        if(ExprSingle!=null) ExprSingle.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprList(\n");

        if(ExpressionList!=null)
            buffer.append(ExpressionList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprSingle!=null)
            buffer.append(ExprSingle.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprList]");
        return buffer.toString();
    }
}
