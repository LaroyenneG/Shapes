package processor.engine;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

public class Processor {

    private static final String DEFAULT_PROMPT = "[shapes]$";

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
        addNewCommand(new CommandMan());
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
            throw new ProcessorException("Invalid command : " + name);
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

    public List<Command> getCommands() {

        List<Command> list = new ArrayList<>(proc.values());

        list.sort(new Comparator<Command>() {
            @Override
            public int compare(Command c1, Command c2) {
                return c1.getName().compareTo(c2.getName());
            }
        });

        return list;
    }

    @Override
    public String toString() {

        StringBuilder string = new StringBuilder();
        string.append("Processor(");
        for (Command c : proc.values()) {
            string.append(c);
            string.append(' ');
        }
        string.append(")");

        return new String(string);
    }
}
