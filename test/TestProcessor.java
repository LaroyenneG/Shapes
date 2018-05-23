import org.junit.jupiter.api.Test;
import processor.engine.Processor;
import processor.engine.ProcessorException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestProcessor {

    @Test
    public void testCleanLine() {

        String line = " eroigurne oin ";

        assertEquals("eroigurne oin", Processor.cleanLine(line));


        line = "";
        assertEquals("", Processor.cleanLine(line));

        line = "a";

        assertEquals("a", Processor.cleanLine(line));

        line = " t";

        assertEquals("t", Processor.cleanLine(line));

        line = "e ";
        assertEquals("e", Processor.cleanLine(line));

        line = " ";
        assertEquals("", Processor.cleanLine(line));
    }

    @Test
    public void testArgs() throws ProcessorException {


        String[] inArgs = {" ", " ", "\"", "toto", "\"", " ", " titi "};

        String[] outArgs = {"toto", "titi"};

        assertArrayEquals(outArgs, Processor.prepareArgsToCommand(inArgs));

    }
}
