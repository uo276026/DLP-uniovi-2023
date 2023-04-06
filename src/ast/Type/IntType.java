package ast.Type;

import visitor.Visitor;

public class IntType extends AbstractType {

    public int line, column;

    public IntType(int line, int column){
        this.line=line;
        this.column=column;
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

    @Override
    public Type arithmetic(Type other, int line, int column){
        //Si es Int o Error, devolvemos other
        if(other instanceof IntType || other instanceof ErrorType)
            return other;
        //Si es double o char, debe dar error
        return super.arithmetic(other, line, column);
    }

    @Override
    public Type unaryminus(int line, int column){
        return this;
    }

    @Override
    public Type asLogical(int line, int column) {
       return this;
    }

    @Override
    public Type canBeCastTo(Type other, int line, int column){
        if(other instanceof IntType || other instanceof DoubleType || other instanceof CharType || other instanceof ErrorType)
            return this;
        return super.arithmetic(this, line, column);
    }

    @Override
    public Type unarynot(int line, int column) {
        return this;
    }
}
