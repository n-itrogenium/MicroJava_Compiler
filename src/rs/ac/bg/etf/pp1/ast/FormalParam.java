// generated with ast extension for cup
// version 0.8
// 10/0/2022 11:11:11


package rs.ac.bg.etf.pp1.ast;

public class FormalParam extends FormalParSingle {

    private Type Type;
    private String formParamName;
    private ArrayOptional ArrayOptional;

    public FormalParam (Type Type, String formParamName, ArrayOptional ArrayOptional) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.formParamName=formParamName;
        this.ArrayOptional=ArrayOptional;
        if(ArrayOptional!=null) ArrayOptional.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getFormParamName() {
        return formParamName;
    }

    public void setFormParamName(String formParamName) {
        this.formParamName=formParamName;
    }

    public ArrayOptional getArrayOptional() {
        return ArrayOptional;
    }

    public void setArrayOptional(ArrayOptional ArrayOptional) {
        this.ArrayOptional=ArrayOptional;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(ArrayOptional!=null) ArrayOptional.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ArrayOptional!=null) ArrayOptional.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ArrayOptional!=null) ArrayOptional.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormalParam(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+formParamName);
        buffer.append("\n");

        if(ArrayOptional!=null)
            buffer.append(ArrayOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormalParam]");
        return buffer.toString();
    }
}
