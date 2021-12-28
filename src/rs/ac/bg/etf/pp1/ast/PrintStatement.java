// generated with ast extension for cup
// version 0.8
// 28/11/2021 11:49:54


package rs.ac.bg.etf.pp1.ast;

public class PrintStatement extends SingleStatement {

    private Expression Expression;
    private PrintParamOpt PrintParamOpt;

    public PrintStatement (Expression Expression, PrintParamOpt PrintParamOpt) {
        this.Expression=Expression;
        if(Expression!=null) Expression.setParent(this);
        this.PrintParamOpt=PrintParamOpt;
        if(PrintParamOpt!=null) PrintParamOpt.setParent(this);
    }

    public Expression getExpression() {
        return Expression;
    }

    public void setExpression(Expression Expression) {
        this.Expression=Expression;
    }

    public PrintParamOpt getPrintParamOpt() {
        return PrintParamOpt;
    }

    public void setPrintParamOpt(PrintParamOpt PrintParamOpt) {
        this.PrintParamOpt=PrintParamOpt;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expression!=null) Expression.accept(visitor);
        if(PrintParamOpt!=null) PrintParamOpt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expression!=null) Expression.traverseTopDown(visitor);
        if(PrintParamOpt!=null) PrintParamOpt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expression!=null) Expression.traverseBottomUp(visitor);
        if(PrintParamOpt!=null) PrintParamOpt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintStatement(\n");

        if(Expression!=null)
            buffer.append(Expression.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(PrintParamOpt!=null)
            buffer.append(PrintParamOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintStatement]");
        return buffer.toString();
    }
}
