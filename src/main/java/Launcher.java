import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/loading_page_form.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("StartForm");
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }
}
