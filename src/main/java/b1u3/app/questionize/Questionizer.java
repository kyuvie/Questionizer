package b1u3.app.questionize;

import java.util.Iterator;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.stream.Collectors;
import static java.nio.charset.StandardCharsets.UTF_8;

public class Questionizer implements Iterable<Question> {
    public ArrayList<Question> questions;
    // src_path is absolute path
    private Path src_path;
    private LineAnalyzer an;
    public static final String sep = "-";

    public Questionizer(Path path) throws IOException {
        if (!path.isAbsolute()) {
            this.src_path = src_path.toAbsolutePath();
        } else {
            this.src_path = path;
        }
        this.an = LineAnalyzer.getLineAnalyzer();
        this.questions = new ArrayList<Question>();
        String file_content = Files.lines(this.src_path, UTF_8).collect(Collectors.joining(System.getProperty("line.separator")));
        ArrayList<ArrayList<String>> all = this.an.analyzeAll(file_content, sep);
        HashMap<String, String> hm = new HashMap<>();
        for (ArrayList<String> al: all) {
            hm.put(al.get(0), al.get(1));
        }

        // Generate question
        for (ArrayList<String> al: all) {
            this.questions.add(new Question(al.get(0), hm, 3));
        }
    }

    @Override
    public Iterator<Question> iterator () {
        return new QuestionIterator(this);
    }

}
