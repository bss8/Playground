package streams;

import java.io.IOException;

/**
 * The {@link #close()} method of an {@code AutoCloseable} object is called automatically when exiting a {@code
 * try}-with-resources block for which the object has been declared in the resource specification header.
 */
public class MyAutoCloseable implements AutoCloseable {
    public void saySomething() throws IOException {
        throw new IOException("Exception from saySomething");
//        System.out.println("from saySomething()");
    }
    @Override
    public void close() throws IOException {
        throw new IOException("Exception from close");
//        System.out.println("close");
    }
}
