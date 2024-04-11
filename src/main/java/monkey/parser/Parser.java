package monkey.parser;

import monkey.Lexer;
import monkey.Token;
import monkey.ast.*;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private Lexer lexer;
    private Token currToken;
    private Token peekToken;
    private List<String> errors;

    public Parser(Lexer lexer){
        this.lexer = lexer;
        errors = new ArrayList<>();
        //Read two tokens, so cirToken and peekToken can get set
        nextToken();
        nextToken();
    }
    public List<String> getErrors(){
        return errors;
    }
    private void nextToken(){
        this.currToken = this.peekToken;//Move peek Token to currtoken
        this.peekToken = lexer.nextToken();//loads up next token into peek
    }
    public void peekError(Token.TokenType t){
        String msg = String.format("Expected next token to be %s and got %s instead",t,peekToken.getType());
        errors.add(msg);
    }
    public Program parseProgram(){
        Program program = new Program();//root node of the Abstract Syntax Tree
        List<Statement> statements = program.getStatements();
        while(currToken.getType() != Token.TokenType.EOF){
            Statement stmt = parseStatement();
            if(stmt != null ){
                statements.add(stmt);
            }
            nextToken();
        }
        return program;
    }
    private Statement parseStatement(){
        switch(currToken.getType()){
            case LET -> {return parseLetStatement();}
            case RETURN -> {return parseReturnStatement();}
            default -> {
                return null;
            }
        }
    }

    private Statement parseReturnStatement() {
        ReturnStatement returnStatement = new ReturnStatement(currToken);
        nextToken();
        //TODO: skippping expressions until we encounter a semicoloon
        while(!curTokenIs(Token.TokenType.SEMICOLON)){
            nextToken();
        }
        return returnStatement;
    }

    private Statement parseLetStatement(){
        LetStatement statement = new LetStatement(currToken);

        if(!expectPeek(Token.TokenType.IDENT)){
            return null;
        }

        statement.setName(new Identifier(currToken,currToken.getLiteral()));
        if(!expectPeek(Token.TokenType.ASSIGN)){
            return null;
        }
        while(!curTokenIs(Token.TokenType.SEMICOLON)){
            nextToken();
        }
        return statement;
    }

    private boolean curTokenIs(Token.TokenType tokenType) {
        return currToken.getType() == tokenType;
    }
    private boolean peekTokenIs(Token.TokenType tokenType){
        return peekToken.getType() == tokenType;
    }

    //This method is used to check if the next token is of the expected type
    private boolean expectPeek(Token.TokenType tokenType) {
       if(peekTokenIs(tokenType)){
           nextToken();
           return true;
       }else{
           peekError(tokenType);
           return false;
       }
    }
}






















