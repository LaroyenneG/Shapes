package processor.engine;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Processor {

    private static final String DEFAULT_PROMPT = "$";

    private PrintStream out;
    private InputStream in;
    private Scanner scanner;

    private Object system;

    private TreeMap<String, Command> proc;

    private boolean terminated;

    public Processor() {

        out = null;
        in = null;
        scanner = null;

        system = null;

        terminated = false;

        proc = new TreeMap<>();

        addNewCommand(new CommandQuit());
        addNewCommand(new CommandMenu());
        addNewCommand(new CommandScript());
    }

    public String fetch() {

        if (!scanner.hasNext()) {
            return null;
        }

        return scanner.next();
    }

    public Command decode(String name) throws ProcessorException {

        Command command = proc.get(name);

        if (command == null) {
            throw new ProcessorException("Bad processor.shapescommands : " + name);
        }

        return command;
    }

    public void printPrompt() {
        out.print(DEFAULT_PROMPT);
    }

    public void start() {
        terminated = false;
    }

    public void execute(Command command) {

        command.execute(this);
    }

    public boolean isTerminated() {
        return terminated;
    }

    public void terminated() {
        terminated = true;
    }

    public PrintStream out() {
        return out;
    }

    public InputStream in() {
        return in;
    }

    public void setIn(InputStream inputStream) {
        in = inputStream;
        scanner = new Scanner(in);
    }

    public void addNewCommand(Command command) {
        proc.put(command.getName(), command);
    }

    public void setOut(PrintStream printStream) {
        out = printStream;
    }

    public Scanner scanner() {
        return scanner;
    }

    public Object getSystem() {
        return system;
    }

    public void setSystem(Object system) {
        this.system = system;
    }


    @Override
    public String toString() {

        StringBuilder string = new StringBuilder();
        string.append("processor.engine.Processor(");
        for (Map.Entry<String, Command> c : proc.entrySet()) {
            string.append(c);
            string.append(" ");
        }
        string.append(")");

        return string.toString();
    }
}
