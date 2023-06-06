// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:6


package rs.ac.bg.etf.pp1.ast;

public class Program_2 extends Program {

    private Program_name program_name;
    private Program_methods program_methods;

    public Program_2 (Program_name program_name, Program_methods program_methods) {
        this.program_name=program_name;
        if(program_name!=null) program_name.setParent(this);
        this.program_methods=program_methods;
        if(program_methods!=null) program_methods.setParent(this);
    }

    public Program_name getProgram_name() {
        return program_name;
    }

    public void setProgram_name(Program_name program_name) {
        this.program_name=program_name;
    }

    public Program_methods getProgram_methods() {
        return program_methods;
    }

    public void setProgram_methods(Program_methods program_methods) {
        this.program_methods=program_methods;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(program_name!=null) program_name.accept(visitor);
        if(program_methods!=null) program_methods.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(program_name!=null) program_name.traverseTopDown(visitor);
        if(program_methods!=null) program_methods.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(program_name!=null) program_name.traverseBottomUp(visitor);
        if(program_methods!=null) program_methods.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Program_2(\n");

        if(program_name!=null)
            buffer.append(program_name.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(program_methods!=null)
            buffer.append(program_methods.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Program_2]");
        return buffer.toString();
    }
}
