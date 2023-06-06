// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:6


package rs.ac.bg.etf.pp1.ast;

public class Const_declaration implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type type;
    private Const_list const_list;

    public Const_declaration (Type type, Const_list const_list) {
        this.type=type;
        if(type!=null) type.setParent(this);
        this.const_list=const_list;
        if(const_list!=null) const_list.setParent(this);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type=type;
    }

    public Const_list getConst_list() {
        return const_list;
    }

    public void setConst_list(Const_list const_list) {
        this.const_list=const_list;
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
        if(const_list!=null) const_list.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(type!=null) type.traverseTopDown(visitor);
        if(const_list!=null) const_list.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(type!=null) type.traverseBottomUp(visitor);
        if(const_list!=null) const_list.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Const_declaration(\n");

        if(type!=null)
            buffer.append(type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(const_list!=null)
            buffer.append(const_list.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Const_declaration]");
        return buffer.toString();
    }
}
