// generated with ast extension for cup
// version 0.8
// 27/11/2021 14:54:35


package rs.ac.bg.etf.pp1.ast;

public class ReturnStatement extends SingleStatement {

    private ReturnOpt ReturnOpt;

    public ReturnStatement (ReturnOpt ReturnOpt) {
        this.ReturnOpt=ReturnOpt;
        if(ReturnOpt!=null) ReturnOpt.setParent(this);
    }

    public ReturnOpt getReturnOpt() {
        return ReturnOpt;
    }

    public void setReturnOpt(ReturnOpt ReturnOpt) {
        this.ReturnOpt=ReturnOpt;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ReturnOpt!=null) ReturnOpt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ReturnOpt!=null) ReturnOpt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ReturnOpt!=null) ReturnOpt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ReturnStatement(\n");

        if(ReturnOpt!=null)
            buffer.append(ReturnOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ReturnStatement]");
        return buffer.toString();
    }
}
