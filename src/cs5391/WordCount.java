package cs5391;

/**

 This is a stand-alone Java program that reads the standard input
 then prints the number of newline characters, the number of
 white-space separated character sequences, and the number of
 bytes read.

 */

public class WordCount {

    // Static Data

    static int lines = 0;
    static int words = 0;
    static int characters = -1;

    // `characters' is initialized to -1 because it is incremented with
    // each read, including the final read executed at end-of-file and
    // that final read does not actually add a character.


    // whiteSpace returns true iff its argument is a
    // space, newline, formfeed, tab, or carriage return.

    public static boolean whiteSpace(char c) {
        switch (c) {
            case ' ':
            case '\n':
            case '\f':
            case '\t':
            case '\r':
                return true;
            default:
                return false;
        }
    }


    public static int getNext() throws java.io.IOException {
        characters++;
        return System.in.read();
    }


    // The main method is invoked when this program is interpreted with
    // the java interpreter.

    public static void main(String argv[]) throws java.io.IOException {

        int i = getNext();
        int charCount = 0; // holds a word's character count
        String largestWord = null;

        while (i != -1) {	// Repeat until end-of-file is reached.

            if ( !whiteSpace((char)i)) {
                StringBuilder stringBuilder = new StringBuilder(100);

                //
                // Word state
                //
                words++;		// We've seen another word.
                do {			// Skip to the next white space character.
                    stringBuilder.append((char)i);  // build the word, so we can get the length
                    i = getNext();
                } while (i != -1 && !whiteSpace((char)i));

                 // We check if the word is bigger (but not equal) than what we previously found.
                 // (if we have multiple of the same length, we just want the first).
                if (stringBuilder.length() > charCount) {
                    charCount = stringBuilder.length();
                    largestWord = stringBuilder.toString();
                }


            } else {
                //
                // whiteSpace state
                //
                do {
                    if ((char)i == '\n') {
                        lines++;		// We've seen another line;
                    }
                    i = getNext();
                } while (whiteSpace((char)i));
            }
        }
        System.out.println(" " + largestWord + " " +  lines + " " + words + " " + characters);
    }
}
