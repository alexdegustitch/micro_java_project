// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:6


package rs.ac.bg.etf.pp1.ast;

public class Part_var_decl extends Program_con_var {

    private Var_declaration var_declaration;

    public Part_var_decl (Var_declaration var_declaration) {
        this.var_declaration=var_declaration;
        if(var_declaration!=null) var_declaration.setParent(this);
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
        if(var_declaration!=null) var_declaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(var_declaration!=null) var_declaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(var_declaration!=null) var_declaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Part_var_decl(\n");

        if(var_declaration!=null)
            buffer.append(var_declaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Part_var_decl]");
        return buffer.toString();
    }
}
