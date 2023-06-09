package ast.Statement;

import ast.Expression.Expression;
import visitor.Visitor;

import java.util.List;

public class Iterative implements Statement {
    public Expression expression;
    public List<Statement> whileBody;

    public int line;
    public int column;

    public Iterative(int line, int column, Expression expression, List<Statement> statements){
        this.line=line;
        this.column=column;
        this.expression=expression;
        this.whileBody=statements;
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
        return v.visit(this,p);
    }
}
