package b1u3.app.questionize;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Iterator;
import java.io.IOException;
import java.util.ArrayList;


// ReadAll -> generate Question -> List<Question>

public class QuestionizerTest {
    final Logger logger = LoggerFactory.getLogger(QuestionizerTest.class);

    @Test
    public void testReadAllLinesFromPath() {
        Path path = Paths.get("sample.txt");
        // logger.info(path.toAbsolutePath().toString());
        try {
            Questionizer qn = null;
            try {
                qn = new SimpleQuestionizer(path);
            } catch (TableFormatException e) {
            }
            Iterator<Question> qit = qn.iterator();
            assertEquals(qn.questions.size(), 3);
            int i = 1;
            while (qit.hasNext()) {
                Question q = qit.next();
                assertEquals("key" + i, q.getStatement());
                i++;
            }
        } catch (IOException e) {
            fail("Simple Questionizer constructor has occured IOException");
        }
    }

    @Test
    public void testWrongAns() {
        Path path = Paths.get("sample.txt");
        try {
            Questionizer qn = null;
            try {
                qn = new SimpleQuestionizer(path);
            } catch (TableFormatException e) {
            }
            Iterator<Question> qit = qn.iterator();
            if (!qit.hasNext()) {
                fail("no questions, maybe input text file is wrong");
            }
            Question q = qit.next();
            // decide user input
            ArrayList<String> answers = q.getChoices();
            int userInput = -1;
            for (int i = 0; i < answers.size(); i++) {
                if (answers.get(i) != qn.hm.get(q.getStatement())) {
                    userInput = i;
                }
            }
            if (userInput == -1) {
                fail("can't decide user input");
            }
            assertTrue(!qn.ans(q, userInput));
        } catch (IOException e) {
            fail("SimpleQuestionizer constructor has occured IOException");
        }
    }

    @Test
    public void testRightAns() {
        Path path = Paths.get("sample.txt");
        try {
            Questionizer qn = null;
            try {
                qn = new SimpleQuestionizer(path);
            } catch (TableFormatException e) {
            }
            Iterator<Question> qit = qn.iterator();
            if (!qit.hasNext()) {
                fail("no questions, maybe input text file is wrong");
            }
            Question q = qit.next();
            // decide user input
            ArrayList<String> answers = q.getChoices();
            int userInput = -1;
            for (int i = 0; i < answers.size(); i++) {
                if (answers.get(i) == qn.hm.get(q.getStatement())) {
                    userInput = i;
                    break;
                }
            }
            if (userInput == -1) {
                fail("can't decide user input");
            }
            assertTrue(qn.ans(q, userInput));
        } catch (IOException e) {
            fail("SimpleQuestionizer constructor has occured IOException");
        }
    }
}


