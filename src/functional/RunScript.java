package functional;

import java.io.FileReader;
import java.io.IOException;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import static java.lang.System.*;

public class RunScript {

    public static void main(String[] args) {

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("ECMAScript");

        try {
            engine.eval(new FileReader("resources/js/functional-example/script1.js"));
        } catch (ScriptException | IOException e) {
            err.println(e.getMessage());
        }
    }
}