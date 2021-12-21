// generated with ast extension for cup
// version 0.8
// 21/11/2021 2:56:53


package rs.ac.bg.etf.pp1.ast;

public class CharVal extends ConstInit {

    private Character charValue;

    public CharVal (Character charValue) {
        this.charValue=charValue;
    }

    public Character getCharValue() {
        return charValue;
    }

    public void setCharValue(Character charValue) {
        this.charValue=charValue;
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
        buffer.append("CharVal(\n");

        buffer.append(" "+tab+charValue);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CharVal]");
        return buffer.toString();
    }
}
