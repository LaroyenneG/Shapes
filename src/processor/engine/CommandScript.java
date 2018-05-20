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

            File file = new File(processor.fetch());

            FileInputStream inputStream = new FileInputStream(file);

            processor.setIn(inputStream);

            String line = null;

            while ((line = processor.fetch()) != null) {
                processor.interpretLine(line);
            }

            inputStream.close();

        } catch (ProcessorException | NumberFormatException | IOException e) {
            processor.err().println(e.getMessage());
            e.printStackTrace();
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
