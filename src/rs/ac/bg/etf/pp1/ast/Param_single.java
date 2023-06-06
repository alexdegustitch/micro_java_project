// generated with ast extension for cup
// version 0.8
// 21/11/2019 5:14:7


package rs.ac.bg.etf.pp1.ast;

public class Param_single extends Method_parameters {

    private Method_parameter method_parameter;

    public Param_single (Method_parameter method_parameter) {
        this.method_parameter=method_parameter;
        if(method_parameter!=null) method_parameter.setParent(this);
    }

    public Method_parameter getMethod_parameter() {
        return method_parameter;
    }

    public void setMethod_parameter(Method_parameter method_parameter) {
        this.method_parameter=method_parameter;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(method_parameter!=null) method_parameter.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(method_parameter!=null) method_parameter.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(method_parameter!=null) method_parameter.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Param_single(\n");

        if(method_parameter!=null)
            buffer.append(method_parameter.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Param_single]");
        return buffer.toString();
    }
}
