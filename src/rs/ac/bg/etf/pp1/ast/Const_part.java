// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:6


package rs.ac.bg.etf.pp1.ast;

public class Const_part implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String constName;
    private Value value;

    public Const_part (String constName, Value value) {
        this.constName=constName;
        this.value=value;
        if(value!=null) value.setParent(this);
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value=value;
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
        if(value!=null) value.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(value!=null) value.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(value!=null) value.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Const_part(\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        if(value!=null)
            buffer.append(value.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Const_part]");
        return buffer.toString();
    }
}
