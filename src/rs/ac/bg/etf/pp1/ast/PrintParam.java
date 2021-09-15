// generated with ast extension for cup
// version 0.8
// 16/8/2021 0:10:13


package rs.ac.bg.etf.pp1.ast;

public class PrintParam extends PrintParamOpt {

    private Integer printWidth;

    public PrintParam (Integer printWidth) {
        this.printWidth=printWidth;
    }

    public Integer getPrintWidth() {
        return printWidth;
    }

    public void setPrintWidth(Integer printWidth) {
        this.printWidth=printWidth;
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
        buffer.append("PrintParam(\n");

        buffer.append(" "+tab+printWidth);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintParam]");
        return buffer.toString();
    }
}
