package processor.engine;

public class CommandMan extends Command {

    public CommandMan() {
        super("man");
    }

    @Override
    public void execute(Processor processor) {

        String name = processor.scanner().next();

        try {

            processor.out().println(processor.decode(name).man());

        } catch (ProcessorException e) {
            processor.out().println("Command not found");
        }
    }

    @Override
    public String toString() {

        StringBuffer string = new StringBuffer();

        string.append(super.toString());
        string.append(" ");
        string.append("<command name>");

        return new String(string);
    }
}
