package semantic;
import ast.Definition;
import ast.Expression.Variable;
import ast.FunctionDefinition;
import ast.Statement.Statement;
import ast.Type.ErrorType;
import ast.VariableDefinition;
import visitor.AbstractVisitor;
import symboltable.SymbolTable;

public class IdentificationVisitor extends AbstractVisitor<Void, Void> {

    SymbolTable st = new SymbolTable();

    @Override
    public Void visit(VariableDefinition variableDefinition, Void p) {
        variableDefinition.getType().accept(this,p);
        Definition def;
        def = st.findInCurrentScope(variableDefinition.getName());
        if (def == null) { //Si no la encuentra en el global, no existe, puede definirla
            st.insert(variableDefinition);
        } else { //error, ya existe, no se puede volver a definir
            ErrorType et = new ErrorType(variableDefinition.getLine(), variableDefinition.getColumn(),
                    "ERROR in line " + variableDefinition.getLine() + ": Variable '" + variableDefinition.getName()
                            + "' is already defined");
        }

        return null;
    }

    @Override
    public Void visit(FunctionDefinition f, Void p) {
        if(st.find(f.getName())==null){
            st.insert(f);
            st.set();
            f.getFunctionType().accept(this,p);
            for(Statement s: f.statements)
                s.accept(this,p);
            st.reset();
        } else{
            ErrorType et = new ErrorType(f.getLine(), f.getColumn(),
                    "ERROR in line " + f.getLine() + ": Function '" + f.getName()
                            + "' is already defined");
        }

        return null;
    }



    @Override
    public Void visit(Variable var, Void p) {
        Definition def = st.findInCurrentScope(var.getName());
        if(def==null)
            def= st.find(var.getName());
        if(def==null) {
            ErrorType et = new ErrorType(var.getLine(), var.getColumn(),
                    "ERROR in line " + var.getLine() + ": Variable '" + var.getName()
                            + "' is not defined");
        }
        var.def=def;
        return null;
    }


}


