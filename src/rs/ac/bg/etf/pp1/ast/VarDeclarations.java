// generated with ast extension for cup
// version 0.8
// 27/11/2021 14:54:35


package rs.ac.bg.etf.pp1.ast;

public class VarDeclarations extends RecVarDecl {

    private RecVarDecl RecVarDecl;
    private VarDecl VarDecl;

    public VarDeclarations (RecVarDecl RecVarDecl, VarDecl VarDecl) {
        this.RecVarDecl=RecVarDecl;
        if(RecVarDecl!=null) RecVarDecl.setParent(this);
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
    }

    public RecVarDecl getRecVarDecl() {
        return RecVarDecl;
    }

    public void setRecVarDecl(RecVarDecl RecVarDecl) {
        this.RecVarDecl=RecVarDecl;
    }

    public VarDecl getVarDecl() {
        return VarDecl;
    }

    public void setVarDecl(VarDecl VarDecl) {
        this.VarDecl=VarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(RecVarDecl!=null) RecVarDecl.accept(visitor);
        if(VarDecl!=null) VarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(RecVarDecl!=null) RecVarDecl.traverseTopDown(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(RecVarDecl!=null) RecVarDecl.traverseBottomUp(visitor);
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclarations(\n");

        if(RecVarDecl!=null)
            buffer.append(RecVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclarations]");
        return buffer.toString();
    }
}
