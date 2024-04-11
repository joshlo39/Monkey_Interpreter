package monkey.ast;

import java.util.ArrayList;
import java.util.List;

public class Program implements Node {
    private List<Statement> statements;

    public Program(){
        this.statements = new ArrayList<>();
    }
    public List<Statement> getStatements(){
        return statements;
    }

    @Override
    public String tokenLiteral(){
        if(!statements.isEmpty()){
            return statements.get(0).tokenLiteral();
        }else{
            return "";
        }
    }
    @Override
    public String string(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Statement s : statements){
            stringBuilder.append(s.toString());
        }
        return stringBuilder.toString();
    }
}
