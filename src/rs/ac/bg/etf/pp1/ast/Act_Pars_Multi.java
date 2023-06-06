// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class Act_Pars_Multi extends Act_pars {

    private Act_pars act_pars;
    private Expr expr;

    public Act_Pars_Multi (Act_pars act_pars, Expr expr) {
        this.act_pars=act_pars;
        if(act_pars!=null) act_pars.setParent(this);
        this.expr=expr;
        if(expr!=null) expr.setParent(this);
    }

    public Act_pars getAct_pars() {
        return act_pars;
    }

    public void setAct_pars(Act_pars act_pars) {
        this.act_pars=act_pars;
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
        if(act_pars!=null) act_pars.accept(visitor);
        if(expr!=null) expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(act_pars!=null) act_pars.traverseTopDown(visitor);
        if(expr!=null) expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(act_pars!=null) act_pars.traverseBottomUp(visitor);
        if(expr!=null) expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Act_Pars_Multi(\n");

        if(act_pars!=null)
            buffer.append(act_pars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(expr!=null)
            buffer.append(expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Act_Pars_Multi]");
        return buffer.toString();
    }
}
