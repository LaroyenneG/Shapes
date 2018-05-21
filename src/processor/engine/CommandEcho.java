package processor.engine;

public class CommandEcho extends Command {

    private static final String OPTION_1 = "-n";

    public CommandEcho() {
        super("echo");
    }

    @Override
    public void execute(Processor processor, String[] args) {

        for (int i = 0; i < args.length; i++) {

            processor.out().print(args[i]);

            if (i + 1 < args.length) {
                processor.out().print(' ');
            }
        }

        if (!(args.length > 1 && args[0].equals(OPTION_1))) {
            processor.out().println();
        }
    }

    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer();

        buffer.append(super.toString());

        buffer.append("<string>...");

        return new String(buffer);
    }

    @Override
    public String man() {
        return "ECHO(1)                            User Commands                           \n" +
                "ECHO(1)\n" +
                "\n" +
                "NAME\n" +
                "       echo - display a line of text\n" +
                "\n" +
                "SYNOPSIS\n" +
                "       echo [SHORT-OPTION]... [STRING]...\n" +
                "       echo LONG-OPTION\n" +
                "\n" +
                "DESCRIPTION\n" +
                "       Echo the STRING(s) to standard output.\n" +
                "\n" +
                "       -n     do not output the trailing newline\n" +
                "COPYRIGHT\n" +
                "       Copyright  ©  2017  Free Software Foundation, Inc.  License GPLv3+: GNU GPL\n" +
                "       version 3 or later <https://gnu.org/licenses/gpl.html>.\n" +
                "       This is free software: you are free to change and redistribute  it. ";
    }
}
