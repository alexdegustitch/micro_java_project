// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class Stmt_do_while extends Statement {

    private Stmt_kw_do stmt_kw_do;
    private Statement statement;
    private Condition condition;

    public Stmt_do_while (Stmt_kw_do stmt_kw_do, Statement statement, Condition condition) {
        this.stmt_kw_do=stmt_kw_do;
        if(stmt_kw_do!=null) stmt_kw_do.setParent(this);
        this.statement=statement;
        if(statement!=null) statement.setParent(this);
        this.condition=condition;
        if(condition!=null) condition.setParent(this);
    }

    public Stmt_kw_do getStmt_kw_do() {
        return stmt_kw_do;
    }

    public void setStmt_kw_do(Stmt_kw_do stmt_kw_do) {
        this.stmt_kw_do=stmt_kw_do;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement=statement;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition=condition;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(stmt_kw_do!=null) stmt_kw_do.accept(visitor);
        if(statement!=null) statement.accept(visitor);
        if(condition!=null) condition.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(stmt_kw_do!=null) stmt_kw_do.traverseTopDown(visitor);
        if(statement!=null) statement.traverseTopDown(visitor);
        if(condition!=null) condition.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(stmt_kw_do!=null) stmt_kw_do.traverseBottomUp(visitor);
        if(statement!=null) statement.traverseBottomUp(visitor);
        if(condition!=null) condition.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Stmt_do_while(\n");

        if(stmt_kw_do!=null)
            buffer.append(stmt_kw_do.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(statement!=null)
            buffer.append(statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(condition!=null)
            buffer.append(condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Stmt_do_while]");
        return buffer.toString();
    }
}
