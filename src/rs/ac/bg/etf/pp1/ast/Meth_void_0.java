// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class Meth_void_0 extends Method_void_decl {

    private Method_void_name method_void_name;
    private Method_parameters method_parameters;
    private Var_declaration_list var_declaration_list;
    private Statement_list statement_list;

    public Meth_void_0 (Method_void_name method_void_name, Method_parameters method_parameters, Var_declaration_list var_declaration_list, Statement_list statement_list) {
        this.method_void_name=method_void_name;
        if(method_void_name!=null) method_void_name.setParent(this);
        this.method_parameters=method_parameters;
        if(method_parameters!=null) method_parameters.setParent(this);
        this.var_declaration_list=var_declaration_list;
        if(var_declaration_list!=null) var_declaration_list.setParent(this);
        this.statement_list=statement_list;
        if(statement_list!=null) statement_list.setParent(this);
    }

    public Method_void_name getMethod_void_name() {
        return method_void_name;
    }

    public void setMethod_void_name(Method_void_name method_void_name) {
        this.method_void_name=method_void_name;
    }

    public Method_parameters getMethod_parameters() {
        return method_parameters;
    }

    public void setMethod_parameters(Method_parameters method_parameters) {
        this.method_parameters=method_parameters;
    }

    public Var_declaration_list getVar_declaration_list() {
        return var_declaration_list;
    }

    public void setVar_declaration_list(Var_declaration_list var_declaration_list) {
        this.var_declaration_list=var_declaration_list;
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
        if(method_void_name!=null) method_void_name.accept(visitor);
        if(method_parameters!=null) method_parameters.accept(visitor);
        if(var_declaration_list!=null) var_declaration_list.accept(visitor);
        if(statement_list!=null) statement_list.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(method_void_name!=null) method_void_name.traverseTopDown(visitor);
        if(method_parameters!=null) method_parameters.traverseTopDown(visitor);
        if(var_declaration_list!=null) var_declaration_list.traverseTopDown(visitor);
        if(statement_list!=null) statement_list.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(method_void_name!=null) method_void_name.traverseBottomUp(visitor);
        if(method_parameters!=null) method_parameters.traverseBottomUp(visitor);
        if(var_declaration_list!=null) var_declaration_list.traverseBottomUp(visitor);
        if(statement_list!=null) statement_list.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Meth_void_0(\n");

        if(method_void_name!=null)
            buffer.append(method_void_name.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(method_parameters!=null)
            buffer.append(method_parameters.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(var_declaration_list!=null)
            buffer.append(var_declaration_list.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(statement_list!=null)
            buffer.append(statement_list.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Meth_void_0]");
        return buffer.toString();
    }
}
