package monkey;

public class Lexer {
    private String input;
    private int position;
    private int readPosition;
    private char ch;
    public Lexer (String input){
        this.input = input;
        this.position = 0;
        this.readPosition = 0;
        this.ch =0;
        readChar();
    }
    /*
    The function gets the next charater and shifts the index of the pointers
    - readPosition always points to the next position where
         we’re going to read from next and l.position always points
          to the position where we last read.
     */

    public Token nextToken() {
        skipWhitespace();
        Token tok = newToken(Token.TokenType.ILLEGAL, String.valueOf(ch));
        switch (ch) {
            case '=' -> {
                if(peakChar() == '='){
                    char currentChar = ch;
                    readChar();//advances the nextchar to inlude it in the string literal
                    String literal = String.valueOf(currentChar) + ch;//ch got shifted by the readchar
                    tok = new Token(Token.TokenType.EQ,literal);
                }else {
                    tok = new Token(Token.TokenType.ASSIGN, String.valueOf(ch));
                }
                break;
            }
            case ';' -> tok = new Token(Token.TokenType.SEMICOLON, String.valueOf(ch));
            case '(' -> tok =new Token(Token.TokenType.LPAREN, String.valueOf(ch));
            case ')' -> tok =new Token(Token.TokenType.RPAREN, String.valueOf(ch));
            case ',' -> tok = new Token(Token.TokenType.COMMA, String.valueOf(ch));
            case '+' -> tok =new Token(Token.TokenType.PLUS, String.valueOf(ch));
            case '{' -> tok =new Token(Token.TokenType.LBRACE, String.valueOf(ch));
            case '}' -> tok =new Token(Token.TokenType.RBRACE, String.valueOf(ch));
            case '-' -> tok =new Token(Token.TokenType.MINUS, String.valueOf(ch));
            case '!' -> {
                if(peakChar() == '='){
                    char currentChar = ch;
                    readChar();
                    String literal = String.valueOf(currentChar) + ch;
                    tok = new Token (Token.TokenType.NOT_EQ,literal);
                }else{
                    tok =new Token(Token.TokenType.BANG, String.valueOf(ch));
                }
                break;
            }
            case '/' -> tok =new Token(Token.TokenType.SLASH, String.valueOf(ch));
            case '*' -> tok =new Token(Token.TokenType.ASTERISK, String.valueOf(ch));
            case '<' -> tok =new Token(Token.TokenType.LT, String.valueOf(ch));
            case '>' -> tok =new Token(Token.TokenType.GT, String.valueOf(ch));
            case '\u0000' -> tok =new Token(Token.TokenType.EOF, "");
            default -> {
                if (isLetter(ch)) {
                    //checkin if the identifier is a keyword
                    String identifier = readIdentifier();
                    Token.TokenType type = Token.lookupIdent(identifier);
                    tok = new Token(type, identifier);
                    return tok;
                } else if (Character.isDigit(ch)){
                    tok = new Token(Token.TokenType.INT , readNumber());
                    return tok;
                }else {
                    tok = new Token(Token.TokenType.ILLEGAL, String.valueOf(ch));
                }
            }
        };
        readChar();
        return tok;
    }

    private void readChar(){
        if(readPosition >= input.length()){
            ch = '\u0000';//end of char in java representing end of the file
        }else
            ch = input.charAt(readPosition);//next char to read
        position = readPosition;
        readPosition += 1;//lead pointer
    }
    private char peakChar(){
        if(readPosition >= input.length()){
            return '\u0000';
        }else{
            return input.charAt(readPosition);
        }
    }
    private void skipWhitespace(){
        while(ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r'){
            readChar();
        }
    }
    private String readIdentifier(){
        int startPosition = position;
        while(Character.isLetter(ch)){
            readChar();
        }
        String currIdent = input.substring(startPosition,position);
        return currIdent;
    }
    private String readNumber(){
        int startPosition = position;
        while(Character.isDigit(ch)){
            readChar();
        }
        return input.substring(startPosition,position);//position pointer gets updated in readChar()
    }
    private Token newToken(Token.TokenType type, String literal){
        return new Token(type, literal);
    }
    private boolean isLetter(char ch){
        return Character.isLetter(ch) || ch == '_' || ch == '$' || ch == '@' || ch == '#' || ch == '!';
    }
}//end of class
