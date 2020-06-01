package b1u3.app.questionize;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class QuestionTest {
    @Test
    public void choiseIsLessThanSize() {
        HashMap<String, String> column = new HashMap<>();
        String key = "3";
        for (int i = 0; i < 10; i++) {
            column.put(String.valueOf(i), String.valueOf(i));
        }
        Question q = new Question(key, column, 12);
        Assertions.assertEquals(10, q.getChoices().size());
    }

    @Test
    public void greaterThanSize() {
        HashMap<String, String> column = new HashMap<>();
        String key = "5";
        for (int i = 0; i < 15; i++) {
            column.put(String.valueOf(i), String.valueOf(i));
        }
        Question q = new Question(key, column, 10);
        Assertions.assertEquals(10, q.getChoices().size());
    }

    @Test
    public void shuffleChoices() {
        HashMap<String, String> column = new HashMap<>();
        String key = "3";
        for (int i = 0; i < 100; i++) {
            column.put(String.valueOf(i), String.valueOf(i));
        }
        Question q = new Question(key, column, 50);
        Assertions.assertEquals(50, q.getChoices().size());
        List<String> choices = q.getChoices();
        ArrayList<String> prev = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            prev.add(choices.get(i));
        }
        q.shuffle();
        // ひとつでも順番が違ってたらおっけい
        boolean ok = false;
        for (int i = 0; i < 50; i++) {
            if (!q.getChoices().get(i).equals(prev.get(i))) {
                ok = true;
                break;
            }
        }
        if (!ok) {
            Assertions.fail();
        }
    }

    @Test
    public void shuffleChoicesAndCheck() {
        HashMap<String, String> column = new HashMap<>();
        String key = "3";
        for (int i = 0; i < 5; i++) {
            column.put(String.valueOf(i), String.valueOf(i));
        }
        Question q = new Question(key, column, 3);
        Assertions.assertEquals(3, q.getChoices().size());
        Collections.sort(q.getChoices());
        ArrayList<String> prev = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            prev.add(q.getChoices().get(i));
        }
        q.shuffle();
        Collections.sort(q.getChoices());
        for (int i = 0; i < 3; i++) {
            Assertions.assertEquals(q.getChoices().get(i), prev.get(i));
        }
    }
}

