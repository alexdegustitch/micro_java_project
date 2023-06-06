// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class More_term_single extends More_term_list {

    private More_term_part more_term_part;

    public More_term_single (More_term_part more_term_part) {
        this.more_term_part=more_term_part;
        if(more_term_part!=null) more_term_part.setParent(this);
    }

    public More_term_part getMore_term_part() {
        return more_term_part;
    }

    public void setMore_term_part(More_term_part more_term_part) {
        this.more_term_part=more_term_part;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(more_term_part!=null) more_term_part.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(more_term_part!=null) more_term_part.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(more_term_part!=null) more_term_part.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("More_term_single(\n");

        if(more_term_part!=null)
            buffer.append(more_term_part.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [More_term_single]");
        return buffer.toString();
    }
}
