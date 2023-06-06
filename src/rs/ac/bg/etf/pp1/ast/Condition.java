// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class Condition implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private Cond cond;

    public Condition (Cond cond) {
        this.cond=cond;
        if(cond!=null) cond.setParent(this);
    }

    public Cond getCond() {
        return cond;
    }

    public void setCond(Cond cond) {
        this.cond=cond;
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
        if(cond!=null) cond.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(cond!=null) cond.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(cond!=null) cond.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Condition(\n");

        if(cond!=null)
            buffer.append(cond.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Condition]");
        return buffer.toString();
    }
}
