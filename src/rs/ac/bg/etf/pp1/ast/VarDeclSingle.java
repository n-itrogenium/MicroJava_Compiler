// generated with ast extension for cup
// version 0.8
// 19/8/2021 4:25:48


package rs.ac.bg.etf.pp1.ast;

public class VarDeclSingle implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String varName;
    private ArrayOptional ArrayOptional;

    public VarDeclSingle (String varName, ArrayOptional ArrayOptional) {
        this.varName=varName;
        this.ArrayOptional=ArrayOptional;
        if(ArrayOptional!=null) ArrayOptional.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public ArrayOptional getArrayOptional() {
        return ArrayOptional;
    }

    public void setArrayOptional(ArrayOptional ArrayOptional) {
        this.ArrayOptional=ArrayOptional;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ArrayOptional!=null) ArrayOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ArrayOptional!=null) ArrayOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ArrayOptional!=null) ArrayOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclSingle(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(ArrayOptional!=null)
            buffer.append(ArrayOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclSingle]");
        return buffer.toString();
    }
}
