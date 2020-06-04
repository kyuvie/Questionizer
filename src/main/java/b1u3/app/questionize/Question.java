package b1u3.app.questionize;


import java.util.Random;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;

public class Question {
    private ArrayList<String> choices;
    private String statement;
    private Random rand = new Random();

    public Question(String key, HashMap<String, String> column, int choice) throws IllegalArgumentException {
        this.setStatement(key);
        if (choice <= 0 || !column.containsKey(key)) {
            throw new IllegalArgumentException("choice is Positive Integer, got=" + choice + "\n");
        } else if (choice >= column.size()) {
            Iterator it = column.values().iterator();
            while (it.hasNext()) {
                this.getChoices().add((String)it.next());
            }
        } else {
            ArrayList<String> al = new ArrayList<>();
            Iterator it = column.values().iterator();
            while (it.hasNext()) {
                al.add((String)it.next());
            }
            Collections.shuffle(al);
            this.getChoices().add((String)column.get(key));
            int i = 0;
            while (this.getChoices().size() < choice) {
                String g = (String)al.get(i);
                if (g != key) {
                    this.getChoices().add(g);
                }
                i++;
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(this.choices);
    }

    public ArrayList<String> getChoices() {
        if (this.choices == null) {
            this.choices = new ArrayList<>();
        }
        return this.choices;
    }

    protected void setStatement(String key) {
        this.statement = key;
    }

    public String getStatement() {
        return this.statement;
    }
}
