// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:6


package rs.ac.bg.etf.pp1.ast;

public class Part_const_decl extends Program_con_var {

    private Const_declaration const_declaration;

    public Part_const_decl (Const_declaration const_declaration) {
        this.const_declaration=const_declaration;
        if(const_declaration!=null) const_declaration.setParent(this);
    }

    public Const_declaration getConst_declaration() {
        return const_declaration;
    }

    public void setConst_declaration(Const_declaration const_declaration) {
        this.const_declaration=const_declaration;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(const_declaration!=null) const_declaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(const_declaration!=null) const_declaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(const_declaration!=null) const_declaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Part_const_decl(\n");

        if(const_declaration!=null)
            buffer.append(const_declaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Part_const_decl]");
        return buffer.toString();
    }
}
