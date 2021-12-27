// generated with ast extension for cup
// version 0.8
// 27/11/2021 14:54:35


package rs.ac.bg.etf.pp1.ast;

public class GreaterEqRelOp extends RelOp {

    public GreaterEqRelOp () {
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
        buffer.append("GreaterEqRelOp(\n");

        buffer.append(tab);
        buffer.append(") [GreaterEqRelOp]");
        return buffer.toString();
    }
}
