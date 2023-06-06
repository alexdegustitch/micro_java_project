// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:6


package rs.ac.bg.etf.pp1.ast;

public class Var_multi extends Var_list {

    private Var_list var_list;
    private Var_part var_part;

    public Var_multi (Var_list var_list, Var_part var_part) {
        this.var_list=var_list;
        if(var_list!=null) var_list.setParent(this);
        this.var_part=var_part;
        if(var_part!=null) var_part.setParent(this);
    }

    public Var_list getVar_list() {
        return var_list;
    }

    public void setVar_list(Var_list var_list) {
        this.var_list=var_list;
    }

    public Var_part getVar_part() {
        return var_part;
    }

    public void setVar_part(Var_part var_part) {
        this.var_part=var_part;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(var_list!=null) var_list.accept(visitor);
        if(var_part!=null) var_part.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(var_list!=null) var_list.traverseTopDown(visitor);
        if(var_part!=null) var_part.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(var_list!=null) var_list.traverseBottomUp(visitor);
        if(var_part!=null) var_part.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Var_multi(\n");

        if(var_list!=null)
            buffer.append(var_list.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(var_part!=null)
            buffer.append(var_part.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Var_multi]");
        return buffer.toString();
    }
}
