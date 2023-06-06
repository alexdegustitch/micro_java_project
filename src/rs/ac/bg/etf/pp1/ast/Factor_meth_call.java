// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class Factor_meth_call extends Factor {

    private Designator designator;
    private Act_pars act_pars;

    public Factor_meth_call (Designator designator, Act_pars act_pars) {
        this.designator=designator;
        if(designator!=null) designator.setParent(this);
        this.act_pars=act_pars;
        if(act_pars!=null) act_pars.setParent(this);
    }

    public Designator getDesignator() {
        return designator;
    }

    public void setDesignator(Designator designator) {
        this.designator=designator;
    }

    public Act_pars getAct_pars() {
        return act_pars;
    }

    public void setAct_pars(Act_pars act_pars) {
        this.act_pars=act_pars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(designator!=null) designator.accept(visitor);
        if(act_pars!=null) act_pars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(designator!=null) designator.traverseTopDown(visitor);
        if(act_pars!=null) act_pars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(designator!=null) designator.traverseBottomUp(visitor);
        if(act_pars!=null) act_pars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Factor_meth_call(\n");

        if(designator!=null)
            buffer.append(designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(act_pars!=null)
            buffer.append(act_pars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Factor_meth_call]");
        return buffer.toString();
    }
}
