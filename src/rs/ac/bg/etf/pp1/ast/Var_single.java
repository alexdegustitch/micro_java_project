// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:6


package rs.ac.bg.etf.pp1.ast;

public class Var_single extends Var_list {

    private Var_part var_part;

    public Var_single (Var_part var_part) {
        this.var_part=var_part;
        if(var_part!=null) var_part.setParent(this);
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
        if(var_part!=null) var_part.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(var_part!=null) var_part.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(var_part!=null) var_part.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Var_single(\n");

        if(var_part!=null)
            buffer.append(var_part.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Var_single]");
        return buffer.toString();
    }
}
