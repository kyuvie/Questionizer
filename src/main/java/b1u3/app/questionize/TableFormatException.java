package b1u3.app.questionize;


public class TableFormatException extends Exception {
    public TableFormatException(String num) {
        super(num + ": error format");
    }
}

