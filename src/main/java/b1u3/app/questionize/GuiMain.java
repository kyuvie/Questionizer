package b1u3.app.questionize;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuiMain extends Application {
    final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("B1u3 Questionizer");
        try {
            // FXMLのレイアウトをロード
            Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));

            // タイトルセット
            primaryStage.setTitle("JavaFXSample");

            // シーン生成
            Scene scene = new Scene(root);

            // scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
        primaryStage.show();
    }

    public static void tmpMain(String[] args) {
        launch(args);
    }
}

