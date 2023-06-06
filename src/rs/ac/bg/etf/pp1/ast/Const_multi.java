// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:6


package rs.ac.bg.etf.pp1.ast;

public class Const_multi extends Const_list {

    private Const_list const_list;
    private Const_part const_part;

    public Const_multi (Const_list const_list, Const_part const_part) {
        this.const_list=const_list;
        if(const_list!=null) const_list.setParent(this);
        this.const_part=const_part;
        if(const_part!=null) const_part.setParent(this);
    }

    public Const_list getConst_list() {
        return const_list;
    }

    public void setConst_list(Const_list const_list) {
        this.const_list=const_list;
    }

    public Const_part getConst_part() {
        return const_part;
    }

    public void setConst_part(Const_part const_part) {
        this.const_part=const_part;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(const_list!=null) const_list.accept(visitor);
        if(const_part!=null) const_part.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(const_list!=null) const_list.traverseTopDown(visitor);
        if(const_part!=null) const_part.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(const_list!=null) const_list.traverseBottomUp(visitor);
        if(const_part!=null) const_part.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Const_multi(\n");

        if(const_list!=null)
            buffer.append(const_list.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(const_part!=null)
            buffer.append(const_part.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Const_multi]");
        return buffer.toString();
    }
}
