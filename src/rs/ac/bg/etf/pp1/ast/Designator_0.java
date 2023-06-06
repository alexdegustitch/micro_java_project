// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class Designator_0 extends Designator {

    private String ID;
    private Expr expr;

    public Designator_0 (String ID, Expr expr) {
        this.ID=ID;
        this.expr=expr;
        if(expr!=null) expr.setParent(this);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID=ID;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr=expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(expr!=null) expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(expr!=null) expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(expr!=null) expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Designator_0(\n");

        buffer.append(" "+tab+ID);
        buffer.append("\n");

        if(expr!=null)
            buffer.append(expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Designator_0]");
        return buffer.toString();
    }
}