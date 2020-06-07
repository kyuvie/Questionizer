package b1u3.app.questionize;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AnalyzerTest {
    static final Logger logger = LoggerFactory.getLogger(Main.class);
    @Test
    void testAnalyzeLineWithSpaces() {
        Analyzer an = null;
        try {
            an = Analyzer.getInstance("b1u3.app.questionize.SimpleAnalyzer");
        } catch (RuntimeException e) {
            logger.info(e.getMessage());
        }
        ArrayList al = an.analyzeLine(" a   - bb    - ccc    ", "-");
        assertEquals(al.size(), 3);
        assertEquals(al.get(0), "a");
        assertEquals(al.get(1), "bb");
        assertEquals(al.get(2), "ccc");
    }

    @Test
    void testAnalyzeLineWithFullSpacesAndJapanese() {
        Analyzer an = null;
        try {
            an = Analyzer.getInstance("b1u3.app.questionize.SimpleAnalyzer");
        } catch (RuntimeException e) {
            logger.info(e.getMessage());
        }
            
        ArrayList al = an.analyzeLine("　　あ　-　　いい　　　-　ううう　　　", "-");
        assertEquals(al.size(), 3);
        assertEquals(al.get(0), "あ");
        assertEquals(al.get(1), "いい");
        assertEquals(al.get(2), "ううう");
    }

    @Test
    void testAnalyzeAll() {
        Analyzer an = null;
        try {
            an = Analyzer.getInstance("b1u3.app.questionize.SimpleAnalyzer");
        } catch (RuntimeException e) {
            logger.info(e.getMessage());
        }
        ArrayList<ArrayList<String>> al = an.analyzeAll("あ-い-う-え\nああ-いい-うう-ええ\n啞-　 居-右-絵", "-");
        assertEquals(al.size(), 3);
        assertEquals(al.get(2).get(1), "居");
    }
}

