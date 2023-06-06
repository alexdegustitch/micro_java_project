// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:6


package rs.ac.bg.etf.pp1.ast;

public class Var_declaration implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type type;
    private Var_list var_list;

    public Var_declaration (Type type, Var_list var_list) {
        this.type=type;
        if(type!=null) type.setParent(this);
        this.var_list=var_list;
        if(var_list!=null) var_list.setParent(this);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type=type;
    }

    public Var_list getVar_list() {
        return var_list;
    }

    public void setVar_list(Var_list var_list) {
        this.var_list=var_list;
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
        if(type!=null) type.accept(visitor);
        if(var_list!=null) var_list.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(type!=null) type.traverseTopDown(visitor);
        if(var_list!=null) var_list.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(type!=null) type.traverseBottomUp(visitor);
        if(var_list!=null) var_list.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Var_declaration(\n");

        if(type!=null)
            buffer.append(type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(var_list!=null)
            buffer.append(var_list.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Var_declaration]");
        return buffer.toString();
    }
}
