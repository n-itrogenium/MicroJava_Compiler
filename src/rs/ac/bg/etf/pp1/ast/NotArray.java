// generated with ast extension for cup
// version 0.8
// 19/8/2021 4:25:48


package rs.ac.bg.etf.pp1.ast;

public class NotArray extends ArrayOptional {

    public NotArray () {
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
        buffer.append("NotArray(\n");

        buffer.append(tab);
        buffer.append(") [NotArray]");
        return buffer.toString();
    }
}
