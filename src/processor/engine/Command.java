package processor.engine;

public abstract class Command {

    private String name;

    public Command(String name) {
        this.name = name;
    }

    public abstract void execute(Processor processor);

    public String getName() {
        return name;
    }

    public String man() {

        return "no information";
    }

    @Override
    public String toString() {
        return name;
    }
}
