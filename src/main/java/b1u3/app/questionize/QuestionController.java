package b1u3.app.questionize;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;


public class QuestionController implements Initializable {
    // 適当
    final static int num = 3;

    @FXML
    private HBox choiceContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int i = 0; i < QuestionController.num; i++) {
            Button b = new Button("Choice"+i);
            // set options for new button
            b.setMnemonicParsing(false);
            b.setMaxWidth(1.7976931348623157E308);
            // should use setOnMouseReleased?
            b.setOnMouseClicked(this::goToResult);
            this.choiceContainer.getChildren().add(b);
            this.choiceContainer.setHgrow(b, Priority.ALWAYS);
        }
    }

    public void goToResult(MouseEvent e) {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/layout/result.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            System.out.println("ここか？");
            System.exit(0);
        }
    }
}
