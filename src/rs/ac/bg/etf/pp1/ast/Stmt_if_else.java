// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class Stmt_if_else extends Statement {

    private Stmt_kw_if stmt_kw_if;
    private Stmt_if_body stmt_if_body;
    private Statement statement;

    public Stmt_if_else (Stmt_kw_if stmt_kw_if, Stmt_if_body stmt_if_body, Statement statement) {
        this.stmt_kw_if=stmt_kw_if;
        if(stmt_kw_if!=null) stmt_kw_if.setParent(this);
        this.stmt_if_body=stmt_if_body;
        if(stmt_if_body!=null) stmt_if_body.setParent(this);
        this.statement=statement;
        if(statement!=null) statement.setParent(this);
    }

    public Stmt_kw_if getStmt_kw_if() {
        return stmt_kw_if;
    }

    public void setStmt_kw_if(Stmt_kw_if stmt_kw_if) {
        this.stmt_kw_if=stmt_kw_if;
    }

    public Stmt_if_body getStmt_if_body() {
        return stmt_if_body;
    }

    public void setStmt_if_body(Stmt_if_body stmt_if_body) {
        this.stmt_if_body=stmt_if_body;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement=statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(stmt_kw_if!=null) stmt_kw_if.accept(visitor);
        if(stmt_if_body!=null) stmt_if_body.accept(visitor);
        if(statement!=null) statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(stmt_kw_if!=null) stmt_kw_if.traverseTopDown(visitor);
        if(stmt_if_body!=null) stmt_if_body.traverseTopDown(visitor);
        if(statement!=null) statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(stmt_kw_if!=null) stmt_kw_if.traverseBottomUp(visitor);
        if(stmt_if_body!=null) stmt_if_body.traverseBottomUp(visitor);
        if(statement!=null) statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Stmt_if_else(\n");

        if(stmt_kw_if!=null)
            buffer.append(stmt_kw_if.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(stmt_if_body!=null)
            buffer.append(stmt_if_body.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(statement!=null)
            buffer.append(statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Stmt_if_else]");
        return buffer.toString();
    }
}
