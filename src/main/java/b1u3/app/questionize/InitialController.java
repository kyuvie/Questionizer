package b1u3.app.questionize;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


public class InitialController implements Initializable {
    public ObservableList<String> availableFilePathes;
    public HashMap<String, Path> filenameToPath;


    public InitialController() {
        this.availableFilePathes = FXCollections.observableArrayList();
        this.filenameToPath = new HashMap<>();
    }

    @FXML
    protected ListView availableFiles;

    @FXML
    protected void pushOkButton(ActionEvent e) {
        State s = State.getInstance();
        try {
            s.qn = new SimpleQuestionizer(getSelectedAbsolutePath());
        } catch (IOException ex) {
            this.updateFileList();
            return;
        }

        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/layout/question.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            // TODO: implements error handling
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    @FXML
    protected void pushQuitButton(ActionEvent e) {
        System.exit(0);
    }

    public void initialize(URL location, ResourceBundle resources) {
        // initialize 
        this.updateFileList();

    }

    private Path getSelectedAbsolutePath() {
        Path p = this.filenameToPath.get(this.availableFiles.<String> getFocusModel().getFocusedItem());
        if (p == null) {
            // TODO: warning
        }
        return p;
    }

    private void updateFileList() {
        URL homeDirUrl = this.getClass().getResource("/text");
        // homeDir does not exist
        if (homeDirUrl == null) {
            return;
        }
        File homeDir = new File(homeDirUrl.getPath());
        try {
            File[] homeDirFiles = homeDir.listFiles();
            for (File f: homeDirFiles) {
                this.availableFilePathes.addAll(f.getAbsolutePath());
                this.filenameToPath.put(f.getAbsolutePath(), f.toPath());
            }
            this.availableFiles.setItems(this.availableFilePathes);
        } catch (SecurityException e) {
            // TODO: implements Error Handling
            System.er
            System.exit(1);
        }
    }
}
