// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class More_factor_part implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Mul_op mul_op;
    private Factor factor;

    public More_factor_part (Mul_op mul_op, Factor factor) {
        this.mul_op=mul_op;
        if(mul_op!=null) mul_op.setParent(this);
        this.factor=factor;
        if(factor!=null) factor.setParent(this);
    }

    public Mul_op getMul_op() {
        return mul_op;
    }

    public void setMul_op(Mul_op mul_op) {
        this.mul_op=mul_op;
    }

    public Factor getFactor() {
        return factor;
    }

    public void setFactor(Factor factor) {
        this.factor=factor;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(mul_op!=null) mul_op.accept(visitor);
        if(factor!=null) factor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(mul_op!=null) mul_op.traverseTopDown(visitor);
        if(factor!=null) factor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(mul_op!=null) mul_op.traverseBottomUp(visitor);
        if(factor!=null) factor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("More_factor_part(\n");

        if(mul_op!=null)
            buffer.append(mul_op.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(factor!=null)
            buffer.append(factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [More_factor_part]");
        return buffer.toString();
    }
}
