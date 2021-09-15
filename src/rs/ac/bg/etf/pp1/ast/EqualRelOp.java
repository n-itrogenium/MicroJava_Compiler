// generated with ast extension for cup
// version 0.8
// 16/8/2021 0:10:13


package rs.ac.bg.etf.pp1.ast;

public class EqualRelOp extends RelOp {

    public EqualRelOp () {
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
        buffer.append("EqualRelOp(\n");

        buffer.append(tab);
        buffer.append(") [EqualRelOp]");
        return buffer.toString();
    }
}
