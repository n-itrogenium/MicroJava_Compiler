// generated with ast extension for cup
// version 0.8
// 15/8/2021 12:28:20


package rs.ac.bg.etf.pp1.ast;

public class Minus extends AddOp {

    public Minus () {
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
        buffer.append("Minus(\n");

        buffer.append(tab);
        buffer.append(") [Minus]");
        return buffer.toString();
    }
}
