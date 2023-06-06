// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class More_term_multi extends More_term_list {

    private More_term_list more_term_list;
    private More_term_part more_term_part;

    public More_term_multi (More_term_list more_term_list, More_term_part more_term_part) {
        this.more_term_list=more_term_list;
        if(more_term_list!=null) more_term_list.setParent(this);
        this.more_term_part=more_term_part;
        if(more_term_part!=null) more_term_part.setParent(this);
    }

    public More_term_list getMore_term_list() {
        return more_term_list;
    }

    public void setMore_term_list(More_term_list more_term_list) {
        this.more_term_list=more_term_list;
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
        if(more_term_list!=null) more_term_list.accept(visitor);
        if(more_term_part!=null) more_term_part.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(more_term_list!=null) more_term_list.traverseTopDown(visitor);
        if(more_term_part!=null) more_term_part.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(more_term_list!=null) more_term_list.traverseBottomUp(visitor);
        if(more_term_part!=null) more_term_part.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("More_term_multi(\n");

        if(more_term_list!=null)
            buffer.append(more_term_list.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(more_term_part!=null)
            buffer.append(more_term_part.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [More_term_multi]");
        return buffer.toString();
    }
}
