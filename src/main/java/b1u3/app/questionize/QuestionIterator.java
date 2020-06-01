package b1u3.app.questionize;

import java.util.Iterator;

public class QuestionIterator implements Iterator<Question> {
    private Integer i;
    private Questionizer qn;

    public QuestionIterator(Questionizer qn) {
        this.i = 0;
        this.qn = qn;
    }

    @Override
    public boolean hasNext() {
        if (this.i < qn.questions.size()) {
            return true;
        }
        return false;
    }

    @Override
    public Question next() {
        Question ret = this.qn.questions.get(i);
        this.i++;
        return ret;
    }
}
