// generated with ast extension for cup
// version 0.8
// 10/0/2022 11:11:11


package rs.ac.bg.etf.pp1.ast;

public class ExpressionSingle extends ExpressionList {

    private ExprSingle ExprSingle;

    public ExpressionSingle (ExprSingle ExprSingle) {
        this.ExprSingle=ExprSingle;
        if(ExprSingle!=null) ExprSingle.setParent(this);
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
        if(ExprSingle!=null) ExprSingle.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprSingle!=null) ExprSingle.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprSingle!=null) ExprSingle.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExpressionSingle(\n");

        if(ExprSingle!=null)
            buffer.append(ExprSingle.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExpressionSingle]");
        return buffer.toString();
    }
}
