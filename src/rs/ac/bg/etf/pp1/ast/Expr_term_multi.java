// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class Expr_term_multi extends Expr {

    private Term term;
    private More_term_list more_term_list;

    public Expr_term_multi (Term term, More_term_list more_term_list) {
        this.term=term;
        if(term!=null) term.setParent(this);
        this.more_term_list=more_term_list;
        if(more_term_list!=null) more_term_list.setParent(this);
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term=term;
    }

    public More_term_list getMore_term_list() {
        return more_term_list;
    }

    public void setMore_term_list(More_term_list more_term_list) {
        this.more_term_list=more_term_list;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(term!=null) term.accept(visitor);
        if(more_term_list!=null) more_term_list.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(term!=null) term.traverseTopDown(visitor);
        if(more_term_list!=null) more_term_list.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(term!=null) term.traverseBottomUp(visitor);
        if(more_term_list!=null) more_term_list.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Expr_term_multi(\n");

        if(term!=null)
            buffer.append(term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(more_term_list!=null)
            buffer.append(more_term_list.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Expr_term_multi]");
        return buffer.toString();
    }
}
