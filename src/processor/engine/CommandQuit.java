package processor.engine;

public class CommandQuit extends Command {


    public CommandQuit() {
        super("quit");
    }

    @Override
    public void execute(Processor processor, String[] args) {

        processor.terminated();
    }
}
