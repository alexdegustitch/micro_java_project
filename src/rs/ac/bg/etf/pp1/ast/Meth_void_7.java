// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class Meth_void_7 extends Method_void_decl {

    private Method_void_name method_void_name;

    public Meth_void_7 (Method_void_name method_void_name) {
        this.method_void_name=method_void_name;
        if(method_void_name!=null) method_void_name.setParent(this);
    }

    public Method_void_name getMethod_void_name() {
        return method_void_name;
    }

    public void setMethod_void_name(Method_void_name method_void_name) {
        this.method_void_name=method_void_name;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(method_void_name!=null) method_void_name.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(method_void_name!=null) method_void_name.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(method_void_name!=null) method_void_name.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Meth_void_7(\n");

        if(method_void_name!=null)
            buffer.append(method_void_name.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Meth_void_7]");
        return buffer.toString();
    }
}
