// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class More_factor_single extends More_factor_list {

    private More_factor_part more_factor_part;

    public More_factor_single (More_factor_part more_factor_part) {
        this.more_factor_part=more_factor_part;
        if(more_factor_part!=null) more_factor_part.setParent(this);
    }

    public More_factor_part getMore_factor_part() {
        return more_factor_part;
    }

    public void setMore_factor_part(More_factor_part more_factor_part) {
        this.more_factor_part=more_factor_part;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(more_factor_part!=null) more_factor_part.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(more_factor_part!=null) more_factor_part.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(more_factor_part!=null) more_factor_part.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("More_factor_single(\n");

        if(more_factor_part!=null)
            buffer.append(more_factor_part.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [More_factor_single]");
        return buffer.toString();
    }
}
