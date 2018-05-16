package processor.engine;

public class CommandMenu extends Command {


    public CommandMenu() {
        super("menu");
    }

    @Override
    public void execute(Processor processor) {

        processor.out().println("Shapes Editor Menu :\n");

    }
}
