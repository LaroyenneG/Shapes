package processor;

import processor.engine.Processor;
import processor.engine.ProcessorException;

public class App {


    public static void main(String[] args) {

        Processor processor = new Processor();

/*
        processor.addNewCommand(new CDcr());
        processor.addNewCommand(new CValue());
        processor.addNewCommand(new CInr());
                processor.setSystem(new Counter());
*/
        processor.setIn(System.in);
        processor.setOut(System.out);


        while (!processor.isTerminated()) {

            try {
                processor.printPrompt();
                processor.execute(processor.decode(processor.fetch()));
            } catch (ProcessorException e) {
                e.printStackTrace();
            }
        }
    }
}
