// generated with ast extension for cup
// version 0.8
// 29/11/2021 13:28:36


package rs.ac.bg.etf.pp1.ast;

public class NoExpr extends ExprSingle {

    public NoExpr () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NoExpr(\n");

        buffer.append(tab);
        buffer.append(") [NoExpr]");
        return buffer.toString();
    }
}
