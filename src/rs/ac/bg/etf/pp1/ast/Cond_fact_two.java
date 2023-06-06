// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class Cond_fact_two extends Cond_fact {

    private Expr expr;
    private Rel_op rel_op;
    private Expr expr1;

    public Cond_fact_two (Expr expr, Rel_op rel_op, Expr expr1) {
        this.expr=expr;
        if(expr!=null) expr.setParent(this);
        this.rel_op=rel_op;
        if(rel_op!=null) rel_op.setParent(this);
        this.expr1=expr1;
        if(expr1!=null) expr1.setParent(this);
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr=expr;
    }

    public Rel_op getRel_op() {
        return rel_op;
    }

    public void setRel_op(Rel_op rel_op) {
        this.rel_op=rel_op;
    }

    public Expr getExpr1() {
        return expr1;
    }

    public void setExpr1(Expr expr1) {
        this.expr1=expr1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(expr!=null) expr.accept(visitor);
        if(rel_op!=null) rel_op.accept(visitor);
        if(expr1!=null) expr1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(expr!=null) expr.traverseTopDown(visitor);
        if(rel_op!=null) rel_op.traverseTopDown(visitor);
        if(expr1!=null) expr1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(expr!=null) expr.traverseBottomUp(visitor);
        if(rel_op!=null) rel_op.traverseBottomUp(visitor);
        if(expr1!=null) expr1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Cond_fact_two(\n");

        if(expr!=null)
            buffer.append(expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(rel_op!=null)
            buffer.append(rel_op.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(expr1!=null)
            buffer.append(expr1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Cond_fact_two]");
        return buffer.toString();
    }
}
