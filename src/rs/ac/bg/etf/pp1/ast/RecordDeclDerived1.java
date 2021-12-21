// generated with ast extension for cup
// version 0.8
// 21/11/2021 2:56:53


package rs.ac.bg.etf.pp1.ast;

public class RecordDeclDerived1 extends RecordDecl {

    private String recordName;
    private RecVarDecl RecVarDecl;

    public RecordDeclDerived1 (String recordName, RecVarDecl RecVarDecl) {
        this.recordName=recordName;
        this.RecVarDecl=RecVarDecl;
        if(RecVarDecl!=null) RecVarDecl.setParent(this);
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName=recordName;
    }

    public RecVarDecl getRecVarDecl() {
        return RecVarDecl;
    }

    public void setRecVarDecl(RecVarDecl RecVarDecl) {
        this.RecVarDecl=RecVarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(RecVarDecl!=null) RecVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(RecVarDecl!=null) RecVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(RecVarDecl!=null) RecVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("RecordDeclDerived1(\n");

        buffer.append(" "+tab+recordName);
        buffer.append("\n");

        if(RecVarDecl!=null)
            buffer.append(RecVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [RecordDeclDerived1]");
        return buffer.toString();
    }
}
