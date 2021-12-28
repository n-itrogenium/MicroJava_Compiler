// generated with ast extension for cup
// version 0.8
// 28/11/2021 11:49:54


package rs.ac.bg.etf.pp1.ast;

public class NoPrintParam extends PrintParamOpt {

    public NoPrintParam () {
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
        buffer.append("NoPrintParam(\n");

        buffer.append(tab);
        buffer.append(") [NoPrintParam]");
        return buffer.toString();
    }
}
