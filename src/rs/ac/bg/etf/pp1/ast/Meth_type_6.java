// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class Meth_type_6 extends Method_type_decl {

    private Method_type_name method_type_name;
    private Statement_list statement_list;

    public Meth_type_6 (Method_type_name method_type_name, Statement_list statement_list) {
        this.method_type_name=method_type_name;
        if(method_type_name!=null) method_type_name.setParent(this);
        this.statement_list=statement_list;
        if(statement_list!=null) statement_list.setParent(this);
    }

    public Method_type_name getMethod_type_name() {
        return method_type_name;
    }

    public void setMethod_type_name(Method_type_name method_type_name) {
        this.method_type_name=method_type_name;
    }

    public Statement_list getStatement_list() {
        return statement_list;
    }

    public void setStatement_list(Statement_list statement_list) {
        this.statement_list=statement_list;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(method_type_name!=null) method_type_name.accept(visitor);
        if(statement_list!=null) statement_list.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(method_type_name!=null) method_type_name.traverseTopDown(visitor);
        if(statement_list!=null) statement_list.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(method_type_name!=null) method_type_name.traverseBottomUp(visitor);
        if(statement_list!=null) statement_list.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Meth_type_6(\n");

        if(method_type_name!=null)
            buffer.append(method_type_name.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(statement_list!=null)
            buffer.append(statement_list.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Meth_type_6]");
        return buffer.toString();
    }
}
