package b1u3.app.questionize;

import java.util.Iterator;
import java.util.ArrayList;

public class Questionizer implements Iterable<Question> {
    public ArrayList<Question> questions;

    @Override
    public Iterator<Question> iterator () {
        return new QuestionIterator(this);
    }
}
