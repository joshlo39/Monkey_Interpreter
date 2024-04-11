package monkey;

import java.util.Scanner;

public class Repl{
    private static final String PROMPT = ">> ";

    private static void start(java.io.InputStream in, java.io.PrintStream out){
        Scanner scanner = new Scanner(in);

        while(true){
            out.println(PROMPT);
            if(!scanner.hasNext()){
                return;
            }

            String line = scanner.nextLine();
            Lexer lexer = new Lexer(line);
            System.out.println("Hello this is the Monkey programming Language!");
            System.out.println("Write some code!");
            for (Token tok = lexer.nextToken(); tok.getType() != Token.TokenType.EOF; tok = lexer.nextToken()){
                System.out.println("Token:" + tok.getLiteral() + " Type: " + tok.getType());
            }
        }
    }
    public static void main(String[] args){
        start(System.in,System.out);
    }
}