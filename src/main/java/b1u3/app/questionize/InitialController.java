package b1u3.app.questionize;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;
import javafx.scene.control.Alert;

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
    protected TextField choosedDir;

    @FXML
    protected void pushOkButton(ActionEvent e) {
        State s = State.getInstance();
        try {
            try {
                Path p = getSelectedAbsolutePath();
                if (p == null) {
                    return;
                }
                s.qn = new SimpleQuestionizer(p);
            } catch (TableFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
                return;
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
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

    @FXML
    protected void pushChooseDir(ActionEvent e) {
        var chooser = new DirectoryChooser();
        Window win = ((Node)e.getSource()).getScene().getWindow();
        File f = chooser.showDialog(win);
        if (f == null) {
            return;
        }
        this.choosedDir.setText(f.toPath().toAbsolutePath().toString());
        this.updateFileList(f);
    }

    public void initialize(URL location, ResourceBundle resources) {
        // initialize 
    }

    private Path getSelectedAbsolutePath() {
        try {
            Path p = this.filenameToPath.get(this.availableFiles.<String> getFocusModel().getFocusedItem());
            if (p == null) {
                // TODO: warning
            }
            return p;
        } catch (NullPointerException e) {
            return null;
        }
    }

    // setPropertyでデバッグか配布か指定 -> getResourceで取って、相対パス../../..でテキストを置く
    private void updateFileList(File homeDir) {
        try {
            File[] homeDirFiles = homeDir.listFiles();
            if (homeDir.isDirectory() == false) {
                System.err.println("/test is not dir");
            }
            if (homeDirFiles == null) {
                System.err.println("Can't get files in /text/");
                return;
            }
            for (File f: homeDirFiles) {
                this.availableFilePathes.addAll(f.getAbsolutePath());
                this.filenameToPath.put(f.getAbsolutePath(), f.toPath());
            }
            this.availableFiles.setItems(this.availableFilePathes);
        } catch (SecurityException e) {
            // TODO: implements Error Handling
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
