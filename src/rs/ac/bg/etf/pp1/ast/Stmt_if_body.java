// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class Stmt_if_body implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Condition condition;
    private Statement statement;

    public Stmt_if_body (Condition condition, Statement statement) {
        this.condition=condition;
        if(condition!=null) condition.setParent(this);
        this.statement=statement;
        if(statement!=null) statement.setParent(this);
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition=condition;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement=statement;
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
        if(condition!=null) condition.accept(visitor);
        if(statement!=null) statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(condition!=null) condition.traverseTopDown(visitor);
        if(statement!=null) statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(condition!=null) condition.traverseBottomUp(visitor);
        if(statement!=null) statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Stmt_if_body(\n");

        if(condition!=null)
            buffer.append(condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(statement!=null)
            buffer.append(statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Stmt_if_body]");
        return buffer.toString();
    }
}
