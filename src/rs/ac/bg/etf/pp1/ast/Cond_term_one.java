// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class Cond_term_one extends Cond_term {

    private Cond_fact cond_fact;

    public Cond_term_one (Cond_fact cond_fact) {
        this.cond_fact=cond_fact;
        if(cond_fact!=null) cond_fact.setParent(this);
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
        if(cond_fact!=null) cond_fact.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(cond_fact!=null) cond_fact.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(cond_fact!=null) cond_fact.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Cond_term_one(\n");

        if(cond_fact!=null)
            buffer.append(cond_fact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Cond_term_one]");
        return buffer.toString();
    }
}
