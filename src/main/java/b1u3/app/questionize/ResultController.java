package b1u3.app.questionize;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ResultController implements Initializable {
    private State state;
    private ObservableList<String> results;
    @FXML ListView resultList;
    @FXML Label resultText;

    public ResultController () {
        this.state = State.getInstance();
        this.results = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var list = this.state.results;
        for(String s: list) {
            this.results.add(s);
            System.out.println(s);
        }
        this.resultList.setItems(this.results);
        this.resultText.setText(this.state.correct + "/" + this.state.all);
    }
}

