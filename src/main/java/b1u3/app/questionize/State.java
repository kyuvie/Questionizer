package b1u3.app.questionize;


import java.util.ArrayList;


public final class State {
    // Omission singleton
    private static State state;
    public Questionizer qn;
    public int all;
    public int correct;
    // result information
    public ArrayList<String> results;

    private State() {
        this.reset();
    }

    public static State getInstance() {
        if (State.state == null) {
            State.state = new State();
        }
        return State.state;
    }

    public void reset() {
        this.all = 0;
        this.correct = 0;
        this.results = new ArrayList<>();
    }
}

