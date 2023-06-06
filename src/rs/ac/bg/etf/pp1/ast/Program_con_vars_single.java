// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:6


package rs.ac.bg.etf.pp1.ast;

public class Program_con_vars_single extends Program_con_vars {

    private Program_con_var program_con_var;

    public Program_con_vars_single (Program_con_var program_con_var) {
        this.program_con_var=program_con_var;
        if(program_con_var!=null) program_con_var.setParent(this);
    }

    public Program_con_var getProgram_con_var() {
        return program_con_var;
    }

    public void setProgram_con_var(Program_con_var program_con_var) {
        this.program_con_var=program_con_var;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(program_con_var!=null) program_con_var.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(program_con_var!=null) program_con_var.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(program_con_var!=null) program_con_var.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Program_con_vars_single(\n");

        if(program_con_var!=null)
            buffer.append(program_con_var.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Program_con_vars_single]");
        return buffer.toString();
    }
}
