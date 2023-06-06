// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class Designator_1 extends Designator {

    private String ID;

    public Designator_1 (String ID) {
        this.ID=ID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID=ID;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Designator_1(\n");

        buffer.append(" "+tab+ID);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Designator_1]");
        return buffer.toString();
    }
}
