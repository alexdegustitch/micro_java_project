// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:6


package rs.ac.bg.etf.pp1.ast;

public class Program_methods_multi extends Program_methods {

    private Program_methods program_methods;
    private Method_declaration method_declaration;

    public Program_methods_multi (Program_methods program_methods, Method_declaration method_declaration) {
        this.program_methods=program_methods;
        if(program_methods!=null) program_methods.setParent(this);
        this.method_declaration=method_declaration;
        if(method_declaration!=null) method_declaration.setParent(this);
    }

    public Program_methods getProgram_methods() {
        return program_methods;
    }

    public void setProgram_methods(Program_methods program_methods) {
        this.program_methods=program_methods;
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
        if(program_methods!=null) program_methods.accept(visitor);
        if(method_declaration!=null) method_declaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(program_methods!=null) program_methods.traverseTopDown(visitor);
        if(method_declaration!=null) method_declaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(program_methods!=null) program_methods.traverseBottomUp(visitor);
        if(method_declaration!=null) method_declaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Program_methods_multi(\n");

        if(program_methods!=null)
            buffer.append(program_methods.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(method_declaration!=null)
            buffer.append(method_declaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Program_methods_multi]");
        return buffer.toString();
    }
}
