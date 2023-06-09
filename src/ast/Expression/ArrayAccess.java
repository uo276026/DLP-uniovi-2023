package ast.Expression;

import ast.ASTNode;
import visitor.Visitor;

public class ArrayAccess extends AbstractExpression implements ASTNode, Expression {
    public Expression name; //a              //a[b]
    public Expression value; //b
    public int line, column;

    public ArrayAccess(int line, int column, Expression value, Expression name) {
        this.line=line;
        this.column=column;
        this.value=value;
        this.name=name;
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> v, TP p) {
        return v.visit(this, p);
    }



}
