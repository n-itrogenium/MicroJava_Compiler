// generated with ast extension for cup
// version 0.8
// 21/11/2021 2:56:53


package rs.ac.bg.etf.pp1.ast;

public class DesSingle extends Designator {

    private String designatorName;
    private ArrayElement ArrayElement;

    public DesSingle (String designatorName, ArrayElement ArrayElement) {
        this.designatorName=designatorName;
        this.ArrayElement=ArrayElement;
        if(ArrayElement!=null) ArrayElement.setParent(this);
    }

    public String getDesignatorName() {
        return designatorName;
    }

    public void setDesignatorName(String designatorName) {
        this.designatorName=designatorName;
    }

    public ArrayElement getArrayElement() {
        return ArrayElement;
    }

    public void setArrayElement(ArrayElement ArrayElement) {
        this.ArrayElement=ArrayElement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ArrayElement!=null) ArrayElement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ArrayElement!=null) ArrayElement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ArrayElement!=null) ArrayElement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesSingle(\n");

        buffer.append(" "+tab+designatorName);
        buffer.append("\n");

        if(ArrayElement!=null)
            buffer.append(ArrayElement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesSingle]");
        return buffer.toString();
    }
}
