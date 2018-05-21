package processor.engine;

public class CommandEcho extends Command {


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

        processor.out().println();
    }
}
