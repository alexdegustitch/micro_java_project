// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class Meth_void_decl extends Method_declaration {

    private Method_void_decl method_void_decl;

    public Meth_void_decl (Method_void_decl method_void_decl) {
        this.method_void_decl=method_void_decl;
        if(method_void_decl!=null) method_void_decl.setParent(this);
    }

    public Method_void_decl getMethod_void_decl() {
        return method_void_decl;
    }

    public void setMethod_void_decl(Method_void_decl method_void_decl) {
        this.method_void_decl=method_void_decl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(method_void_decl!=null) method_void_decl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(method_void_decl!=null) method_void_decl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(method_void_decl!=null) method_void_decl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Meth_void_decl(\n");

        if(method_void_decl!=null)
            buffer.append(method_void_decl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Meth_void_decl]");
        return buffer.toString();
    }
}
