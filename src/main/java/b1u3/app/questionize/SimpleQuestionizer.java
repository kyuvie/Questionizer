package b1u3.app.questionize;

import java.util.Iterator;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.stream.Collectors;
import static java.nio.charset.StandardCharsets.UTF_8;

public class SimpleQuestionizer extends Questionizer {
    // src_path is absolute path
    private Path src_path;
    private Analyzer an;
    public static final String sep = "-";

    public SimpleQuestionizer(Path path) throws IOException, TableFormatException {
        if (!path.isAbsolute()) {
            this.src_path = path.toAbsolutePath();
        } else {
            this.src_path = path;
        }
        this.an = Analyzer.getInstance("b1u3.app.questionize.SimpleAnalyzer");
        this.questions = new ArrayList<Question>();
        String file_content = Files.lines(this.src_path, UTF_8).collect(Collectors.joining(System.getProperty("line.separator")));

        ArrayList<ArrayList<String>> all = this.an.analyzeAll(file_content, sep);
        hm = new HashMap<>();
        if (all.size() > 0 || all.size() >= 2) {
            for (ArrayList<String> al: all) {
                // al が al[1]をもっているとは限らない
                hm.put(al.get(0), al.get(1));
            }
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

    @Override
    public boolean ans(Question q, int ans) {
        return this.hm.get(q.getStatement()) == q.getChoices().get(ans);
    }

}
