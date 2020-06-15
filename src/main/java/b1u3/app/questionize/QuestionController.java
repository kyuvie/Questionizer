package b1u3.app.questionize;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;


public final class QuestionController implements Initializable {
    // 適当
    final static int num = 3;
    private HashMap<Button, Integer> buttonToNum;
    private Question q;
    private Iterator<Question> it;
    // state というより record
    private State state;

    public QuestionController() {
        this.buttonToNum = new HashMap<>();
        this.state = State.getInstance();

    }

    @FXML
    private HBox choiceContainer;

    @FXML
    private Label statement;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.it = State.getInstance().qn.iterator();
        if (this.it.hasNext()) {
            this.q = it.next();
            this.q.shuffle();
        } else {
            // TODO: エラーウィンドウの表示からの initial.fxml に飛ぶ
            System.exit(1);
        }
        this.changeChoices();
    }

    public void buttonClicked(MouseEvent e) {
        // check answer
        Button btn = (Button) e.getSource();
        int ans = buttonToNum.get(btn);
        this.state.all++;
        if (State.getInstance().qn.ans(this.q, ans)) {
            // correct
            this.state.correct++;
        } else {
            this.state.results.add(this.state.all + ". " + this.q.getStatement() + " : wrong");
        }
        if (this.it.hasNext()) {
            this.q = this.it.next();
            this.choiceContainer.getChildren().clear();
            this.changeChoices();
        } else {
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/layout/result.fxml"));
                stage.setScene(new Scene(root));
            } catch (IOException ex) {
                // TODO: Error handling
                System.exit(0);
            }
        }
    }

    // call after setting this.q.
    private void changeChoices() {
        this.statement.setText(this.q.getStatement());
        for (int i = 0; i < this.q.getChoices().size(); i++) {
            String c = this.q.getChoices().get(i);
            Button b = new Button(this.q.getChoices().get(i));
            this.buttonToNum.put(b, i);
            // set options for new button
            b.setMnemonicParsing(false);
            b.setMaxWidth(1.7976931348623157E308);
            // should use setOnMouseReleased?
            b.setOnMouseClicked(this::buttonClicked);
            this.choiceContainer.getChildren().add(b);
            this.choiceContainer.setHgrow(b, Priority.ALWAYS);
        }
    }
}

