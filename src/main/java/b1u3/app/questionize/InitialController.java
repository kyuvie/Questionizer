package b1u3.app.questionize;


import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import java.io.IOException;



public class InitialController {
    @FXML
    protected void pushOkButton(ActionEvent e) {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/layout/question.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            System.out.println("ここか？");
            System.exit(0);
        }
    }

    @FXML
    protected void pushQuitButton(ActionEvent e) {
    }
}
