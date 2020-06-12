package b1u3.app.questionize;


import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

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
            b.setOnMouseClicked(e->System.out.println("Button pressed"));
            this.choiceContainer.getChildren().add(b);
            this.choiceContainer.setHgrow(b, Priority.ALWAYS);
        }
    }
}
