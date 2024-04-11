package monkey.ast;

import monkey.Token;

public class Identifier implements Expression{
    private Token token;
    private String value;
    public Identifier(Token token, String value){
        this.token = token;
        this.value = value;
    }
    @Override
    public void expressionNode(){
    }
    @Override
    public String string(){
        return value;
    }

    @Override
    public String tokenLiteral(){
        return token.getLiteral();
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
