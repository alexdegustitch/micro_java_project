// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class Expr_term_single extends Expr {

    private Term term;

    public Expr_term_single (Term term) {
        this.term=term;
        if(term!=null) term.setParent(this);
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term=term;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(term!=null) term.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(term!=null) term.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(term!=null) term.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Expr_term_single(\n");

        if(term!=null)
            buffer.append(term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Expr_term_single]");
        return buffer.toString();
    }
}
