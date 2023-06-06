// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class Add_op_minus extends Add_op {

    public Add_op_minus () {
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
        buffer.append("Add_op_minus(\n");

        buffer.append(tab);
        buffer.append(") [Add_op_minus]");
        return buffer.toString();
    }
}
