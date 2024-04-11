package monkey.ast;

import monkey.Token;

public class LetStatement implements Statement{
    private Token token;
    private Identifier name;
    private Expression value;

    public LetStatement(Token token){
        this.token = token;
    }

    public LetStatement(Token token, Identifier name, Expression value) {
        this.token = token;
        this.name = name;
        this.value = value;
    }

    @Override
    public void statementNode(){

    }
    @Override
    public String string(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(token.getLiteral()).append(" ");
        stringBuilder.append(name.toString());
        stringBuilder.append("=");

        if (value != null){
            stringBuilder.append(value.toString());
        }
        stringBuilder.append(";");

        return stringBuilder.toString();
    }
    @Override
    public String tokenLiteral(){
        return token.getLiteral();
    }
    public Token getToken(){
        return token;
    }
    public Identifier getName(){
        return name;
    }
    public Expression getValue(){
        return value;
    }
    public void setToken(Token token){
        this.token = token;
    }
    public void setName(Identifier name){
        this.name = name;
    }
    public void setValue(Expression value){
        this.value = value;
    }

}
