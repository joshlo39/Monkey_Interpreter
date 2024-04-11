package monkey.ast;

import monkey.Token;

public class ReturnStatement implements Statement {
    private Token token;
    private Expression returnValue;
    public ReturnStatement(Token token) {
        this.token = token;
    }

    public void statementNode() {}

    @Override
    public String string(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(tokenLiteral()).append(" ");
        if(returnValue != null){
            stringBuilder.append(returnValue.toString());
        }
        stringBuilder.append(";");
        return stringBuilder.toString();
    }
    @Override
    public String tokenLiteral() {
        return token.getLiteral() ;
    }


    //Getters and setters
    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Expression getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Expression returnValue) {
        this.returnValue = returnValue;
    }



}