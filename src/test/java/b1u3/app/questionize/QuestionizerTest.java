package b1u3.app.questionize;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuestionizerTest {
    final Logger logger = LoggerFactory.getLogger(QuestionizerTest.class);

    @Test
    public void testReadAllLinesFromPath() {
        logger.info("This is logger sample");
        System.out.println("This is System.out.println");
        assertEquals(1, 1);
    }
}


