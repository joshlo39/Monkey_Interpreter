import monkey.Token;
import monkey.ast.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AstTest {
    @Test
    public void testSting(){
        Program program = new Program();

        LetStatement letStmt = new LetStatement(
                new Token(Token.TokenType.LET, "let"),
                new Identifier(new Token(Token.TokenType.IDENT, "myVar"), "myVar"),
                new Identifier(new Token(Token.TokenType.IDENT, "anotherVar"), "anotherVar")
        );
        assertEquals("let myVar = anotherVar;",program.string());
    }
}
