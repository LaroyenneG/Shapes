package processor.engine;

import processor.shapescommands.CommandClearShape;

import java.io.*;
import java.util.*;

public class Processor {


    public static final char COMMAND_CHAR = '#';
    public static final char SEP_CHAR_1 = '{';
    public static final char SEP_CHAR_2 = '}';
    public static final char VARIABLE_CHAR = '$';
    public static final char QUOTES = '"';
    public static final char COMMAND_AFFECTATION = '=';

    private HashMap<String, String> variables;

    private static final String DEFAULT_PROMPT = "[shapes]$";

    private PrintStream out;
    private PrintStream err;
    private InputStream in;
    private BufferedReader reader;

    private Object system;

    private TreeMap<String, Command> proc;

    private boolean terminated;


    private static Processor INSTANCE = null;

    private Processor() {

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
        addNewCommand(new CommandEcho());
    }

    public static synchronized Processor getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Processor();
        }
        return INSTANCE;
    }

    public synchronized String fetch() throws IOException {

        return reader.readLine();
    }

    /*
    Reformat all argument. Interpret quotes and clean space.
     */
    public static String[] prepareArgsToCommand(String[] args) throws ProcessorException {

        List<String> argsList = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {

            StringBuilder string = new StringBuilder(args[i]);

            if (new String(string).contains(String.valueOf(QUOTES))) {

                for (i = i + 1; i < args.length; i++) {

                    string.append(" ").append(args[i]);

                    if (args[i].contains(String.valueOf(QUOTES))) {
                        break;
                    }

                    if (i + 1 >= args.length) {
                        throw new ProcessorException("invalid quotes");
                    }
                }

                string = new StringBuilder(new String(string).replaceAll(String.valueOf(QUOTES), ""));
            }

            String cString = cleanLine(new String(string));
            if (cString.length() > 0) {
                argsList.add(cleanLine(cString));
            }
        }

        return argsList.toArray(new String[0]);
    }


    /*
    Clean all spaces before and after the line.
     */
    public static String cleanLine(String line) {

        int cursor_1 = 0;

        while (cursor_1 < line.length() && line.charAt(cursor_1) == ' ') {
            cursor_1++;
        }

        int cursor_2 = line.length() - 1;
        while ((cursor_2 >= 0 && line.charAt(cursor_2) == ' ')) {
            cursor_2--;
        }

        if (cursor_1 > cursor_2) {
            return "";
        }

        return line.substring(cursor_1, cursor_2 + 1);
    }

    public synchronized void interpretLine(String line) throws ProcessorException {

        line = cleanLine(line);

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

                    output.flush();
                    output.close();

                    int l = -1;
                    StringBuilder result = new StringBuilder();
                    while ((l = reader.read()) > 0) {
                        result.append((char) l);
                    }

                    line = line.substring(0, a - 1) + result + line.substring(b + 1, line.length());

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
                if (line.charAt(i) == COMMAND_AFFECTATION) {
                    for (int j = i + 1; j < line.length(); j++) {
                        varValue.append(line.charAt(j));
                    }
                    break;
                }
                varName.append(line.charAt(i));
            }

            if (varName.length() >= 1 && !varName.toString().contains(" ") && varValue.length() >= 1) {
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

        args = prepareArgsToCommand(args);

        String[] cmdArgs = new String[args.length - 1];

        System.arraycopy(args, 1, cmdArgs, 0, args.length - 1);

        execute(decode(args[0]), cmdArgs);
    }

    public synchronized Command decode(String name) throws ProcessorException {

        Command command = proc.get(name);

        if (command == null) {
            throw new ProcessorException("invalid command : " + name);
        }

        return command;
    }

    public void printPrompt() {
        out.print(DEFAULT_PROMPT);
    }

    public synchronized void start() {
        terminated = false;
    }

    public synchronized void execute(Command command, String[] args) {

        command.execute(this, args);
    }

    public synchronized boolean isTerminated() {
        return terminated;
    }

    public synchronized void terminated() {
        terminated = true;
    }

    public synchronized PrintStream out() {
        return out;
    }

    public synchronized PrintStream err() {
        return err;
    }

    public synchronized InputStream in() {
        return in;
    }

    public synchronized void setIn(InputStream inputStream) {
        in = inputStream;
        reader = new BufferedReader(new InputStreamReader(in));
    }

    public synchronized void addNewCommand(Command command) {
        proc.put(command.getName(), command);
    }

    public synchronized void setOut(PrintStream printStream) {
        out = printStream;
    }

    public synchronized void setErr(PrintStream printStream) {
        err = printStream;
    }

    public synchronized BufferedReader reader() {
        return reader;
    }

    public synchronized Object getSystem() {
        return system;
    }

    public synchronized void setSystem(Object system) {
        this.system = system;
    }

    public synchronized List<Command> getCommands() {

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
