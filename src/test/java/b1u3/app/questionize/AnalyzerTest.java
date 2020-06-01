package b1u3.app.questionize;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;


public class AnalyzerTest {
    @Test
    void testAnalyzeLineWithSpaces() {
        LineAnalyzer an = LineAnalyzer.getLineAnalyzer();
        ArrayList al = an.analyzeLine(" a   - bb    - ccc    ", "-");
        assertEquals(al.size(), 3);
        assertEquals(al.get(0), "a");
        assertEquals(al.get(1), "bb");
        assertEquals(al.get(2), "ccc");
    }

    @Test
    void testAnalyzeLineWithFullSpacesAndJapanese() {
        LineAnalyzer an = LineAnalyzer.getLineAnalyzer();
        ArrayList al = an.analyzeLine("　　あ　-　　いい　　　-　ううう　　　", "-");
        assertEquals(al.size(), 3);
        assertEquals(al.get(0), "あ");
        assertEquals(al.get(1), "いい");
        assertEquals(al.get(2), "ううう");
    }

    @Test
    void testAnalyzeAll() {
        LineAnalyzer an = LineAnalyzer.getLineAnalyzer();
        ArrayList<ArrayList<String>> al = an.analyzeAll("あ-い-う-え\nああ-いい-うう-ええ\n啞-　 居-右-絵", "-");
        assertEquals(al.size(), 3);
        assertEquals(al.get(2).get(1), "居");
    }
}

