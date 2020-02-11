package cs5391;

// TokenizerText.java
import java.io.*;

public class TokenizerText {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java TokenizerTest <src>");
            return;
        }
        try {
            StreamTokenizer stok = new StreamTokenizer(new
                    FileReader("resources/test.txt"));
            stok.wordChars(0, ' ');

            // Declare ; to be the only separator char.
            stok.whitespaceChars(';', ';');
            int token;

            // token is filled with a code indicating what type of item was just read.
            while ((token = stok.nextToken()) != stok.TT_EOF) {
                switch (token) {
                    case StreamTokenizer.TT_NUMBER:

                        // If a number is read, the value is placed in the double variable nval.
                        System.out.println("Number: " + stok.nval);
                        break;
                    case StreamTokenizer.TT_WORD:

                        // If a word is read, the value is placed in the String variable sval.
                        System.out.println("Word: " + stok.sval);
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println(e);
            return;
        }
    }
}
