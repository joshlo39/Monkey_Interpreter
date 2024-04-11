import monkey.Lexer;
import monkey.ast.LetStatement;
import monkey.ast.Program;
import monkey.ast.ReturnStatement;
import monkey.ast.Statement;
import monkey.parser.Parser;
import org.junit.Test;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.util.List;

import static org.junit.Assert.*;

public class ParserTest {
    @Test
    public void testReturnStatements(){
        String input = """
            return 5;
            return 10;
            return 993322;
            """;

        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);
        Program program = parser.parseProgram();

        checkParserErrors(parser);

        List<Statement> stmts = program.getStatements();
        assertEquals(3,stmts.size());

        for(Statement stmt : stmts) {
            System.out.println("Statement" + stmt.tokenLiteral());
            assertTrue(stmt instanceof ReturnStatement);
            ReturnStatement returnStatement = (ReturnStatement) stmt;
            assertEquals("return", returnStatement.tokenLiteral());
        }


    }
    @Test
    public void testLetStatements(){
        String input = "let x = 5;\n" +
                "   let y = 10;\n" +
                "   let foobar = 838383;";

        Lexer l = new Lexer(input);
        Parser p = new Parser(l);
        Program program = p.parseProgram();

        checkParserErrors(p);
        assertNotNull("Parse Program() returned null",program);
        assertEquals("program.Statements does not contain 3 statements",3, program.getStatements().size());

        String[] testIdentifiers = {"x","y","foobar"};

        for (int i = 0; i < testIdentifiers.length; i++){
            Statement stmt = program.getStatements().get(i);
            System.out.println();
            testLetStatement(stmt,testIdentifiers[i]);
        }
    }

    private void checkParserErrors(Parser p) {
        List<String> errors = p.getErrors();
        if(errors.isEmpty()){
            return;
        }
        StringBuilder errormsg = new StringBuilder();
        errormsg.append("parser has ").append(errors.size()).append(" errors:\n");
        for(String msg : errors){
            errormsg.append("parser error: ").append(msg).append("\n");
        }
        fail(errormsg.toString());
    }


    private void testLetStatement(Statement statement, String name){
        assertEquals("s.TokenLiteral not 'let'. ", "let", statement.tokenLiteral());
        assertTrue("s not instance of LetStatement", statement instanceof LetStatement);

        LetStatement letstmnt = (LetStatement) statement;
        System.out.println(String.format("Expected Value: %s, Value: %s", name, letstmnt.getName().getValue()));
        System.out.println(String.format("Expected Value: %s, Value: %s", name, letstmnt.getName().tokenLiteral()));
        assertEquals("letStmt.Name.Value not '" + name + "'.", name,letstmnt.getName().getValue());
        assertEquals("letStmt.Name.TokenLiteral() not '" + name + "'.", name,letstmnt.getName().tokenLiteral());
    }
}
