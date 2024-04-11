package monkey;

import java.util.HashMap;
import java.util.Map;
public class Token {
    private TokenType type;
    private String literal;

    public enum TokenType {
        ILLEGAL, EOF, IDENT, INT, ASSIGN, PLUS, COMMA, SEMICOLON,
        LPAREN, RPAREN, LBRACE, RBRACE, FUNCTION, LET, BANG, MINUS, SLASH, ASTERISK, LT, GT
        ,TRUE,FALSE,IF,ELSE,RETURN,EQ,NOT_EQ
    }

    public Token(TokenType type, String literal) {
        this.type = type;
        this.literal = literal;
    }

    public TokenType getType() {
        return type;
    }

    public String getLiteral() {
        return literal;
    }

    private static final Map<String, TokenType> keywords = new HashMap<>();
    static {
        keywords.put("fn", TokenType.FUNCTION);
        keywords.put("let", TokenType.LET);
        keywords.put("true", TokenType.TRUE);
        keywords.put("false", TokenType.FALSE);
        keywords.put("if",TokenType.IF);
        keywords.put("else",TokenType.ELSE);
        keywords.put("return",TokenType.RETURN);
    }
    public static TokenType lookupIdent(String ident) {
        //if the identifier is found in the hashmap then it return the corresponding token type
        //else it returns the default tokn type which is IDENT meaning it is a user defined variable
        return keywords.getOrDefault(ident, TokenType.IDENT);
    }
}
