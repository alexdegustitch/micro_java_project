// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:6


package rs.ac.bg.etf.pp1.ast;

public class Program_1 extends Program {

    private Program_name program_name;
    private Program_con_vars program_con_vars;

    public Program_1 (Program_name program_name, Program_con_vars program_con_vars) {
        this.program_name=program_name;
        if(program_name!=null) program_name.setParent(this);
        this.program_con_vars=program_con_vars;
        if(program_con_vars!=null) program_con_vars.setParent(this);
    }

    public Program_name getProgram_name() {
        return program_name;
    }

    public void setProgram_name(Program_name program_name) {
        this.program_name=program_name;
    }

    public Program_con_vars getProgram_con_vars() {
        return program_con_vars;
    }

    public void setProgram_con_vars(Program_con_vars program_con_vars) {
        this.program_con_vars=program_con_vars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(program_name!=null) program_name.accept(visitor);
        if(program_con_vars!=null) program_con_vars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(program_name!=null) program_name.traverseTopDown(visitor);
        if(program_con_vars!=null) program_con_vars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(program_name!=null) program_name.traverseBottomUp(visitor);
        if(program_con_vars!=null) program_con_vars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Program_1(\n");

        if(program_name!=null)
            buffer.append(program_name.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(program_con_vars!=null)
            buffer.append(program_con_vars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Program_1]");
        return buffer.toString();
    }
}
