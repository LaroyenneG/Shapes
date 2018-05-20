package processor.engine;

import processor.shapescommands.CommandShapesException;

public class CommandMan extends Command {

    public CommandMan() {
        super("man");
    }

    @Override
    public void execute(Processor processor, String[] args) {

        try {

            if (args.length != 1) {
                throw new CommandShapesException("invalid argument number");
            }

            String name = args[0];
            processor.out().println(processor.decode(name).man());

        } catch (ProcessorException e) {
            processor.err().println("Command not found");
        } catch (CommandShapesException e) {
            processor.err().println(e.getMessage());
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
