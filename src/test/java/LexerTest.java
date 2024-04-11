import monkey.Lexer;
import monkey.Token;
import org.junit.Test;
import static org.junit.Assert.*;

public class LexerTest{
    @Test
    public void testNextToken1(){
        String input = "=+(){},;";
        Token[] tests1 = new Token[] {
                new Token(Token.TokenType.ASSIGN, "="),
                new Token(Token.TokenType.PLUS, "+"),
                new Token(Token.TokenType.LPAREN, "("),
                new Token(Token.TokenType.RPAREN, ")"),
                new Token(Token.TokenType.LBRACE, "{"),
                new Token(Token.TokenType.RBRACE, "}"),
                new Token(Token.TokenType.COMMA, ","),
                new Token(Token.TokenType.SEMICOLON, ";"),
                new Token(Token.TokenType.EOF, "")
        };
        Lexer lexer = new Lexer(input);
        for(int i =0; i < tests1.length; i++){
            Token tok = lexer.nextToken();
            assertEquals(String.format("Tests [%d] - TokenType Wrong",i),tests1[i].getType(),tok.getType());
            assertEquals(String.format("Tests [%d] - Literal Wrong",i),tests1[i].getLiteral(),tok.getLiteral());
        }
    }
    @Test
    public void testNextToken(){
        String input2 = "let five = 5;\n" +
                "let ten = 10;\n" +
                "   let add = fn(x, y) {\n" +
                "     x + y;\n" +
                "};\n" +
                "   let result = add(five, ten);\n";

        Token[] tests = new Token[] {
                new Token(Token.TokenType.LET, "let"),
                new Token(Token.TokenType.IDENT, "five"),
                new Token(Token.TokenType.ASSIGN, "="),
                new Token(Token.TokenType.INT, "5"),
                new Token(Token.TokenType.SEMICOLON, ";"),
                new Token(Token.TokenType.LET, "let"),
                new Token(Token.TokenType.IDENT, "ten"),
                new Token(Token.TokenType.ASSIGN, "="),
                new Token(Token.TokenType.INT, "10"),
                new Token(Token.TokenType.SEMICOLON, ";"),
                new Token(Token.TokenType.LET, "let"),
                new Token(Token.TokenType.IDENT, "add"),
                new Token(Token.TokenType.ASSIGN, "="),
                new Token(Token.TokenType.FUNCTION, "fn"),
                new Token(Token.TokenType.LPAREN, "("),
                new Token(Token.TokenType.IDENT, "x"),
                new Token(Token.TokenType.COMMA, ","),
                new Token(Token.TokenType.IDENT, "y"),
                new Token(Token.TokenType.RPAREN, ")"),
                new Token(Token.TokenType.LBRACE, "{"),
                new Token(Token.TokenType.IDENT, "x"),
                new Token(Token.TokenType.PLUS, "+"),
                new Token(Token.TokenType.IDENT, "y"),
                new Token(Token.TokenType.SEMICOLON, ";"),
                new Token(Token.TokenType.RBRACE, "}"),
                new Token(Token.TokenType.SEMICOLON, ";"),
                new Token(Token.TokenType.LET, "let"),
                new Token(Token.TokenType.IDENT, "result"),
                new Token(Token.TokenType.ASSIGN, "="),
                new Token(Token.TokenType.IDENT, "add"),
                new Token(Token.TokenType.LPAREN, "("),
                new Token(Token.TokenType.IDENT, "five"),
                new Token(Token.TokenType.COMMA, ","),
                new Token(Token.TokenType.IDENT, "ten"),
                new Token(Token.TokenType.RPAREN, ")"),
                new Token(Token.TokenType.SEMICOLON, ";"),
                new Token(Token.TokenType.EOF, "")
        };
        Lexer lexer = new Lexer(input2);
        for(int i =0; i < tests.length; i++){
            //shifts the input string pointer to point to the next token
            Token tok = lexer.nextToken();
            //checks what the test got compared to the actual token
            assertEquals(String.format("Tests [%d] - TokenType Wrong",i),tests[i].getType(),tok.getType());
            assertEquals(String.format("Tests [%d] - Literal Wrong",i),tests[i].getLiteral(),tok.getLiteral());
        }
    }
    @Test
    public void testNextToken3(){
        String input = "let five = 5;\n" +
                "let ten = 10;\n" +
                "   let add = fn(x, y) {\n" +
                "     x + y;\n" +
                "};\n" +
                "   let result = add(five, ten);\n" +
                "   !-/*5;\n" +
                "   5 < 10 > 5;\n" +
                "   if (5 < 10) {\n" +
                "       return true;\n" +
                "   } else {\n" +
                "       return false;\n" +
                "}\n" +
                "10 == 10; 10 != 9;";

        Token[] tests = new Token[] {
                new Token(Token.TokenType.LET, "let"),
                new Token(Token.TokenType.IDENT, "five"),
                new Token(Token.TokenType.ASSIGN, "="),
                new Token(Token.TokenType.INT, "5"),
                new Token(Token.TokenType.SEMICOLON, ";"),
                new Token(Token.TokenType.LET, "let"),
                new Token(Token.TokenType.IDENT, "ten"),
                new Token(Token.TokenType.ASSIGN, "="),
                new Token(Token.TokenType.INT, "10"),
                new Token(Token.TokenType.SEMICOLON, ";"),
                new Token(Token.TokenType.LET, "let"),
                new Token(Token.TokenType.IDENT, "add"),
                new Token(Token.TokenType.ASSIGN, "="),
                new Token(Token.TokenType.FUNCTION, "fn"),
                new Token(Token.TokenType.LPAREN, "("),
                new Token(Token.TokenType.IDENT, "x"),
                new Token(Token.TokenType.COMMA, ","),
                new Token(Token.TokenType.IDENT, "y"),
                new Token(Token.TokenType.RPAREN, ")"),
                new Token(Token.TokenType.LBRACE, "{"),
                new Token(Token.TokenType.IDENT, "x"),
                new Token(Token.TokenType.PLUS, "+"),
                new Token(Token.TokenType.IDENT, "y"),
                new Token(Token.TokenType.SEMICOLON, ";"),
                new Token(Token.TokenType.RBRACE, "}"),
                new Token(Token.TokenType.SEMICOLON, ";"),
                new Token(Token.TokenType.LET, "let"),
                new Token(Token.TokenType.IDENT, "result"),
                new Token(Token.TokenType.ASSIGN, "="),
                new Token(Token.TokenType.IDENT, "add"),
                new Token(Token.TokenType.LPAREN, "("),
                new Token(Token.TokenType.IDENT, "five"),
                new Token(Token.TokenType.COMMA, ","),
                new Token(Token.TokenType.IDENT, "ten"),
                new Token(Token.TokenType.RPAREN, ")"),
                new Token(Token.TokenType.SEMICOLON, ";"),
                new Token(Token.TokenType.BANG, "!"),
                new Token(Token.TokenType.MINUS, "-"),
                new Token(Token.TokenType.SLASH, "/"),
                new Token(Token.TokenType.ASTERISK, "*"),
                new Token(Token.TokenType.INT, "5"),
                new Token(Token.TokenType.SEMICOLON, ";"),
                new Token(Token.TokenType.INT, "5"),
                new Token(Token.TokenType.LT, "<"),
                new Token(Token.TokenType.INT, "10"),
                new Token(Token.TokenType.GT, ">"),
                new Token(Token.TokenType.INT, "5"),
                new Token(Token.TokenType.SEMICOLON, ";"),
                new Token(Token.TokenType.IF, "if"),
                new Token(Token.TokenType.LPAREN, "("),
                new Token(Token.TokenType.INT, "5"),
                new Token(Token.TokenType.LT, "<"),
                new Token(Token.TokenType.INT, "10"),
                new Token(Token.TokenType.RPAREN, ")"),
                new Token(Token.TokenType.LBRACE, "{"),
                new Token(Token.TokenType.RETURN, "return"),
                new Token(Token.TokenType.TRUE, "true"),
                new Token(Token.TokenType.SEMICOLON, ";"),
                new Token(Token.TokenType.RBRACE, "}"),
                new Token(Token.TokenType.ELSE, "else"),
                new Token(Token.TokenType.LBRACE, "{"),
                new Token(Token.TokenType.RETURN, "return"),
                new Token(Token.TokenType.FALSE, "false"),
                new Token(Token.TokenType.SEMICOLON, ";"),
                new Token(Token.TokenType.RBRACE, "}"),
                new Token(Token.TokenType.INT, "10"),
                new Token(Token.TokenType.EQ, "=="),
                new Token(Token.TokenType.INT, "10"),
                new Token(Token.TokenType.SEMICOLON, ";"),
                new Token(Token.TokenType.INT, "10"),
                new Token(Token.TokenType.NOT_EQ, "!="),
                new Token(Token.TokenType.INT, "9"),
                new Token(Token.TokenType.SEMICOLON, ";"),
                new Token(Token.TokenType.EOF, "")
        };

        Lexer lexer = new Lexer(input);
        for(int i = 0; i < tests.length; i++){
            Token tok = lexer.nextToken();
            assertEquals(String.format("Tests [%d] - TokenType Wrong",i),tests[i].getType(),tok.getType());
            assertEquals(String.format("Tests [%d] - Literal Wrong",i),tests[i].getLiteral(),tok.getLiteral());
        }

    }
}
