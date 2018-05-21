package processor.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CommandScript extends Command {

    public CommandScript() {
        super("script");
    }

    @Override
    public void execute(Processor processor, String[] args) {


        InputStream lastInputStream = processor.in();

        try {
            if (args.length != 1) {
                throw new ProcessorException("invalid argument number");
            }

            File file = new File(args[0]);

            FileInputStream inputStream = new FileInputStream(file);

            processor.setIn(inputStream);

            String line = null;

            while ((line = processor.fetch()) != null) {
                processor.interpretLine(line);
            }

            inputStream.close();

        } catch (ProcessorException | NumberFormatException | IOException e) {
            processor.err().println(e.getMessage());
        } finally {
            processor.setIn(lastInputStream);
        }
    }

    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer();

        buffer.append(super.toString());
        buffer.append(' ');
        buffer.append("<file>");

        return new String(buffer);
    }
}
