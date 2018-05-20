package processor.engine;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class Processor {

    private static final String DEFAULT_PROMPT = "[shapes]$";

    private PrintStream out;
    private PrintStream err;
    private InputStream in;
    private BufferedReader reader;

    private Object system;

    private TreeMap<String, Command> proc;

    private boolean terminated;

    public Processor() {

        out = null;
        in = null;
        err = null;
        reader = null;

        system = null;

        terminated = false;

        proc = new TreeMap<>();

        addNewCommand(new CommandQuit());
        addNewCommand(new CommandMan());
        addNewCommand(new CommandMenu());
        addNewCommand(new CommandScript());
    }

    public String fetch() throws IOException {

        return reader.readLine();
    }

    public void interpretLine(String line) throws ProcessorException, IOException {

        int a = -1;
        int b = -1;

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '{') {
                a = i;
                break;
            }
        }

        for (int j = line.length() - 1; j >= a; j++) {
            if (line.charAt(j) == '}') {
                b = j;
                break;
            }
        }


        if (a >= 0 && b >= 0) {

            String subString = line.substring(a, b);


            OutputStream saveOut = out();

            final PipedOutputStream output = new PipedOutputStream();
            final PipedInputStream input = new PipedInputStream(output);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            setOut(new PrintStream(output));

            interpretLine(subString);

            line = line.replaceAll(subString, reader.readLine());

            output.close();
            input.close();

            setOut(new PrintStream(saveOut));
        }

        String[] args = line.split(" ");

        String[] cmdArgs = new String[args.length - 1];

        System.arraycopy(args, 1, cmdArgs, 0, args.length);

        execute(decode(args[1]), cmdArgs);
    }

    public Command decode(String name) throws ProcessorException {

        Command command = proc.get(name);

        if (command == null) {
            throw new ProcessorException("invalid command : " + name);
        }

        return command;
    }

    public void printPrompt() {
        out.print(DEFAULT_PROMPT);
    }

    public void start() {
        terminated = false;
    }

    public void execute(Command command, String[] args) {

        command.execute(this, args);
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

    public PrintStream err() {
        return err;
    }

    public InputStream in() {
        return in;
    }

    public void setIn(InputStream inputStream) {
        in = inputStream;
        reader = new BufferedReader(new InputStreamReader(in));
    }

    public void addNewCommand(Command command) {
        proc.put(command.getName(), command);
    }

    public void setOut(PrintStream printStream) {
        out = printStream;
    }

    public void setErr(PrintStream printStream) {
        err = printStream;
    }

    public BufferedReader reader() {
        return reader;
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
