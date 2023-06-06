// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class Cond_term_two extends Cond_term {

    private Cond_term cond_term;
    private Cond_fact cond_fact;

    public Cond_term_two (Cond_term cond_term, Cond_fact cond_fact) {
        this.cond_term=cond_term;
        if(cond_term!=null) cond_term.setParent(this);
        this.cond_fact=cond_fact;
        if(cond_fact!=null) cond_fact.setParent(this);
    }

    public Cond_term getCond_term() {
        return cond_term;
    }

    public void setCond_term(Cond_term cond_term) {
        this.cond_term=cond_term;
    }

    public Cond_fact getCond_fact() {
        return cond_fact;
    }

    public void setCond_fact(Cond_fact cond_fact) {
        this.cond_fact=cond_fact;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(cond_term!=null) cond_term.accept(visitor);
        if(cond_fact!=null) cond_fact.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(cond_term!=null) cond_term.traverseTopDown(visitor);
        if(cond_fact!=null) cond_fact.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(cond_term!=null) cond_term.traverseBottomUp(visitor);
        if(cond_fact!=null) cond_fact.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Cond_term_two(\n");

        if(cond_term!=null)
            buffer.append(cond_term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(cond_fact!=null)
            buffer.append(cond_fact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Cond_term_two]");
        return buffer.toString();
    }
}
