package processor.engine;

import processor.shapescommands.CommandClearShape;

import java.io.*;
import java.util.*;

public class Processor {


    private static final char COMMAND_CHAR = '#';
    private static final char SEP_CHAR_1 = '(';
    private static final char SEP_CHAR_2 = ')';
    private static final char VARIABLE_CHAR = '$';

    private HashMap<String, String> variables;

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
        variables = new HashMap<>();

        addNewCommand(new CommandQuit());
        addNewCommand(new CommandMan());
        addNewCommand(new CommandMenu());
        addNewCommand(new CommandScript());
        addNewCommand(new CommandClearShape());
    }

    public String fetch() throws IOException {

        return reader.readLine();
    }

    public void interpretLine(String line) throws ProcessorException {


        if (line.length() < 1 || line.charAt(0) == COMMAND_CHAR) {
            return;
        }


        String oldLine = null;

        do {

            oldLine = line;

            int a = -1;
            int b = -1;

            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == SEP_CHAR_1) {
                    a = i + 1;
                    for (int j = line.length() - 1; j >= a; j--) {
                        if (line.charAt(j) == SEP_CHAR_2) {
                            b = j;
                            break;
                        }
                    }
                    break;
                }
            }


            if (a >= 0 && b >= 0 && a < b) {

                String subString = line.substring(a, b);

                OutputStream saveOut = out();

                try {
                    final PipedOutputStream output = new PipedOutputStream();
                    final PipedInputStream input;

                    input = new PipedInputStream(output);

                    final BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                    setOut(new PrintStream(output));

                    interpretLine(subString);

                    out().println();
                    output.flush();

                    String result = reader.readLine();

                    line = line.substring(0, a - 1) + result + line.substring(b + 1, line.length());

                    output.close();
                    input.close();

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    setOut(new PrintStream(saveOut));
                }
            }

        } while (!oldLine.equals(line));


        /* Variable manager */

        if (line.charAt(0) == VARIABLE_CHAR) {

            /* Affectation */

            StringBuilder varName = new StringBuilder();
            StringBuilder varValue = new StringBuilder();
            for (int i = 1; i < line.length(); i++) {
                if (line.charAt(i) == '=') {
                    for (int j = i + 1; j < line.length(); j++) {
                        varValue.append(line.charAt(j));
                    }
                    break;
                }
                varName.append(line.charAt(i));
            }

            if (varName.length() >= 1 && !varName.toString().contains(" ") && varValue.length() >= 1 && !varValue.toString().contains(" ")) {
                variables.put(varName.toString(), varValue.toString());
                return;
            }
        }

        /* Reader */

        do {
            oldLine = line;
            for (String key : variables.keySet()) {
                String value = variables.get(key);
                String name = VARIABLE_CHAR + key;
                line = line.replace(name, value);
            }
        } while (!oldLine.equals(line));


        String[] args = line.split(" ");

        String[] cmdArgs = new String[args.length - 1];

        System.arraycopy(args, 1, cmdArgs, 0, args.length - 1);

        execute(decode(args[0]), cmdArgs);

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
