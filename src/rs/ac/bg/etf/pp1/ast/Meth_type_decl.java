// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class Meth_type_decl extends Method_declaration {

    private Method_type_decl method_type_decl;

    public Meth_type_decl (Method_type_decl method_type_decl) {
        this.method_type_decl=method_type_decl;
        if(method_type_decl!=null) method_type_decl.setParent(this);
    }

    public Method_type_decl getMethod_type_decl() {
        return method_type_decl;
    }

    public void setMethod_type_decl(Method_type_decl method_type_decl) {
        this.method_type_decl=method_type_decl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(method_type_decl!=null) method_type_decl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(method_type_decl!=null) method_type_decl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(method_type_decl!=null) method_type_decl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Meth_type_decl(\n");

        if(method_type_decl!=null)
            buffer.append(method_type_decl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Meth_type_decl]");
        return buffer.toString();
    }
}
