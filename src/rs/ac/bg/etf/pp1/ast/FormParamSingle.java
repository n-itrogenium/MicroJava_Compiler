// generated with ast extension for cup
// version 0.8
// 29/11/2021 13:28:36


package rs.ac.bg.etf.pp1.ast;

public class FormParamSingle extends FormPars {

    private FormalParSingle FormalParSingle;

    public FormParamSingle (FormalParSingle FormalParSingle) {
        this.FormalParSingle=FormalParSingle;
        if(FormalParSingle!=null) FormalParSingle.setParent(this);
    }

    public FormalParSingle getFormalParSingle() {
        return FormalParSingle;
    }

    public void setFormalParSingle(FormalParSingle FormalParSingle) {
        this.FormalParSingle=FormalParSingle;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormalParSingle!=null) FormalParSingle.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormalParSingle!=null) FormalParSingle.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormalParSingle!=null) FormalParSingle.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormParamSingle(\n");

        if(FormalParSingle!=null)
            buffer.append(FormalParSingle.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormParamSingle]");
        return buffer.toString();
    }
}
