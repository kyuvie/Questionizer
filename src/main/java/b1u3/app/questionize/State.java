package b1u3.app.questionize;


public final class State {
    // Omission singleton
    private static State state;
    public Questionizer qn;

    public static State getInstance() {
        if (State.state == null) {
            State.state = new State();
        }
        return State.state;
    }
}


