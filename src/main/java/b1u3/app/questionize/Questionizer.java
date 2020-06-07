package b1u3.app.questionize;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Questionizer implements Iterable<Question> {
    public ArrayList<Question> questions;
    public HashMap<String, String> hm;
    public abstract Iterator<Question> iterator();
    public abstract boolean ans(Question q, int ans);
}
