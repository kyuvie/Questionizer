package b1u3.app.questionize;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Iterator;
import java.io.IOException;


// ReadAll -> generate Question -> List<Question>

public class QuestionizerTest {
    final Logger logger = LoggerFactory.getLogger(QuestionizerTest.class);

    @Test
    public void testReadAllLinesFromPath() {
        Path path = Paths.get("sample.txt");
        logger.info(path.toAbsolutePath().toString());
        try {
        Questionizer qn = new Questionizer(path);
        Iterator<Question> qit = qn.iterator();
        while (qit.hasNext()) {
            Question q = qit.next();
            assertEquals("abcdefg", q.getStatement());
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


