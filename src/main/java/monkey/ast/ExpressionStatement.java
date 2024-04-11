package monkey.ast;

import monkey.Token;

public class ExpressionStatement implements Statement {

    private Token token;
    private Expression expression;
    public ExpressionStatement(Token token){
        this.token = token;
    }
    @Override
    public String string(){
        if (expression != null){
            return expression.toString();
        }
        return "";
    }

    @Override
    public void statementNode(){}
    public String tokenLiteral(){return token.getLiteral();}

}
