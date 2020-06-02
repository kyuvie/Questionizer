package b1u3.app.questionize;

import java.util.Iterator;
import java.util.ArrayList;
import java.nio.file.Path;

public class Questionizer implements Iterable<Question> {
    public ArrayList<Question> questions;
    // src_path is absolute path
    private Path src_path;

    public Questionizer(Path path) {
        if (!path.isAbsolute()) {
            this.src_path = src_path.toAbsolutePath();
        } else {
            this.src_path = path;
        }
    }

    @Override
    public Iterator<Question> iterator () {
        return new QuestionIterator(this);
    }
}
