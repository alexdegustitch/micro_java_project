// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class Condition_two extends Cond {

    private Cond cond;
    private Cond_term cond_term;

    public Condition_two (Cond cond, Cond_term cond_term) {
        this.cond=cond;
        if(cond!=null) cond.setParent(this);
        this.cond_term=cond_term;
        if(cond_term!=null) cond_term.setParent(this);
    }

    public Cond getCond() {
        return cond;
    }

    public void setCond(Cond cond) {
        this.cond=cond;
    }

    public Cond_term getCond_term() {
        return cond_term;
    }

    public void setCond_term(Cond_term cond_term) {
        this.cond_term=cond_term;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(cond!=null) cond.accept(visitor);
        if(cond_term!=null) cond_term.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(cond!=null) cond.traverseTopDown(visitor);
        if(cond_term!=null) cond_term.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(cond!=null) cond.traverseBottomUp(visitor);
        if(cond_term!=null) cond_term.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Condition_two(\n");

        if(cond!=null)
            buffer.append(cond.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(cond_term!=null)
            buffer.append(cond_term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Condition_two]");
        return buffer.toString();
    }
}
