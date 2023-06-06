// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class Var_decl_multi extends Var_declaration_list {

    private Var_declaration_list var_declaration_list;
    private Var_declaration var_declaration;

    public Var_decl_multi (Var_declaration_list var_declaration_list, Var_declaration var_declaration) {
        this.var_declaration_list=var_declaration_list;
        if(var_declaration_list!=null) var_declaration_list.setParent(this);
        this.var_declaration=var_declaration;
        if(var_declaration!=null) var_declaration.setParent(this);
    }

    public Var_declaration_list getVar_declaration_list() {
        return var_declaration_list;
    }

    public void setVar_declaration_list(Var_declaration_list var_declaration_list) {
        this.var_declaration_list=var_declaration_list;
    }

    public Var_declaration getVar_declaration() {
        return var_declaration;
    }

    public void setVar_declaration(Var_declaration var_declaration) {
        this.var_declaration=var_declaration;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(var_declaration_list!=null) var_declaration_list.accept(visitor);
        if(var_declaration!=null) var_declaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(var_declaration_list!=null) var_declaration_list.traverseTopDown(visitor);
        if(var_declaration!=null) var_declaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(var_declaration_list!=null) var_declaration_list.traverseBottomUp(visitor);
        if(var_declaration!=null) var_declaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Var_decl_multi(\n");

        if(var_declaration_list!=null)
            buffer.append(var_declaration_list.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(var_declaration!=null)
            buffer.append(var_declaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Var_decl_multi]");
        return buffer.toString();
    }
}
