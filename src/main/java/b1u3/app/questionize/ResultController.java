package b1u3.app.questionize;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import java.io.IOException;

public class ResultController implements Initializable {
    private State state;
    private ObservableList<String> results;
    @FXML ListView resultList;
    @FXML Label resultText;
    @FXML Button ok;

    public ResultController () {
        this.state = State.getInstance();
        this.results = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // init button action
        this.ok.setOnMouseClicked(this::goToInitial);
        var list = this.state.results;
        for(String s: list) {
            this.results.add(s);
        }
        this.resultList.setItems(this.results);
        this.resultText.setText(this.state.correct + "/" + this.state.all);
    }
    

    private void goToInitial(MouseEvent e) {
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            try {
                State.getInstance().reset();
                Parent root = FXMLLoader.load(getClass().getResource("/layout/initial.fxml"));
                stage.setScene(new Scene(root));
            } catch (IOException ex) {
                // TODO: Error handling
                System.err.println(ex.getMessage());
                System.exit(0);
            }
    }
}


