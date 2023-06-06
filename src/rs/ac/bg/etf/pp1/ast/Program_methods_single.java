// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:6


package rs.ac.bg.etf.pp1.ast;

public class Program_methods_single extends Program_methods {

    private Method_declaration method_declaration;

    public Program_methods_single (Method_declaration method_declaration) {
        this.method_declaration=method_declaration;
        if(method_declaration!=null) method_declaration.setParent(this);
    }

    public Method_declaration getMethod_declaration() {
        return method_declaration;
    }

    public void setMethod_declaration(Method_declaration method_declaration) {
        this.method_declaration=method_declaration;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(method_declaration!=null) method_declaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(method_declaration!=null) method_declaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(method_declaration!=null) method_declaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Program_methods_single(\n");

        if(method_declaration!=null)
            buffer.append(method_declaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Program_methods_single]");
        return buffer.toString();
    }
}
