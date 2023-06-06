// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class Term_multi extends Term {

    private Factor factor;
    private More_factor_list more_factor_list;

    public Term_multi (Factor factor, More_factor_list more_factor_list) {
        this.factor=factor;
        if(factor!=null) factor.setParent(this);
        this.more_factor_list=more_factor_list;
        if(more_factor_list!=null) more_factor_list.setParent(this);
    }

    public Factor getFactor() {
        return factor;
    }

    public void setFactor(Factor factor) {
        this.factor=factor;
    }

    public More_factor_list getMore_factor_list() {
        return more_factor_list;
    }

    public void setMore_factor_list(More_factor_list more_factor_list) {
        this.more_factor_list=more_factor_list;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(factor!=null) factor.accept(visitor);
        if(more_factor_list!=null) more_factor_list.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(factor!=null) factor.traverseTopDown(visitor);
        if(more_factor_list!=null) more_factor_list.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(factor!=null) factor.traverseBottomUp(visitor);
        if(more_factor_list!=null) more_factor_list.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Term_multi(\n");

        if(factor!=null)
            buffer.append(factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(more_factor_list!=null)
            buffer.append(more_factor_list.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Term_multi]");
        return buffer.toString();
    }
}
