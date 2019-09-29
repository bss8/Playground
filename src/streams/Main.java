package streams;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    private static final String INPUT_FILE = "resources/file.txt";
    private static final String OUTPUT_FILE = "resources/file1.txt";

    public static void main(String... args) {

        doTryCatchFinaly();
        doTryWithResources();
        doTryWithResourcesMulti();
        doCloseThing();
    }

    private static void doCloseThing() {
        try (MyAutoCloseable autoCloseable = new MyAutoCloseable()) {
            autoCloseable.saySomething();
        } catch (IOException e) {
            e.printStackTrace();
            for (Throwable t : e.getSuppressed())
                System.out.println("Suppressed: " + t.getMessage());
        }
    }

    /**
     * Because we declare the reader as a resource in try (resource), closing it is handled for us
     */
    private static void doTryWithResources() {
        System.out.println("\ntry-with-resources");
        char[] buff = new char[8];
        int length;

        try (Reader reader = Files.newBufferedReader(Paths.get(INPUT_FILE))) {

            while ((length = reader.read(buff)) >= 0) {
                printBuffer(buff, length);
            }
        } catch (IOException e) {
            printExceptionDetails(e);
        }
    }

    private static void doTryWithResourcesMulti() {
        System.out.println("\ntry-with-multiple-resources");
        char[] buff = new char[8];
        int length;

        try (Reader reader = Files.newBufferedReader(Paths.get(INPUT_FILE));
             Writer writer = Files.newBufferedWriter(Paths.get(OUTPUT_FILE))) {
            while ((length = reader.read(buff)) >= 0) {
                System.out.println("\nlength: " + length);
                writer.write(buff, 0, length);
            }
        } catch (IOException e) {
            printExceptionDetails(e);
        }
    }

    private static void doTryCatchFinaly() {
        System.out.println("\ntry-catch-finally");
        char[] buff = new char[8];
        int length;
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(INPUT_FILE));
            while ((length = reader.read(buff)) >= 0) {
                printBuffer(buff, length);
            }
        } catch (IOException e) {
            printExceptionDetails(e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                printExceptionDetails(e);
            }
        }
    }

    private static void printBuffer(char[] buff, int length) {
        System.out.println("\nlength: " + length);
        for (int i = 0; i < length; i++)
            System.out.print(buff[i]);
    }

    private static void printExceptionDetails(IOException e) {
        e.printStackTrace();
        System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
    }

}
