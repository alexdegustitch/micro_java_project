// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class More_term_part implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Add_op add_op;
    private Term term;

    public More_term_part (Add_op add_op, Term term) {
        this.add_op=add_op;
        if(add_op!=null) add_op.setParent(this);
        this.term=term;
        if(term!=null) term.setParent(this);
    }

    public Add_op getAdd_op() {
        return add_op;
    }

    public void setAdd_op(Add_op add_op) {
        this.add_op=add_op;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term=term;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(add_op!=null) add_op.accept(visitor);
        if(term!=null) term.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(add_op!=null) add_op.traverseTopDown(visitor);
        if(term!=null) term.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(add_op!=null) add_op.traverseBottomUp(visitor);
        if(term!=null) term.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("More_term_part(\n");

        if(add_op!=null)
            buffer.append(add_op.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(term!=null)
            buffer.append(term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [More_term_part]");
        return buffer.toString();
    }
}
