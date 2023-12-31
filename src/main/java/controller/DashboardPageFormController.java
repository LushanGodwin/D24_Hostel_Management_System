package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashboardPageFormController {
    @FXML
    private JFXButton btnStudent;

    @FXML
    private JFXButton btnRoom;

    @FXML
    private AnchorPane root;

    @FXML
    void btnRoomOnAction(ActionEvent event) throws IOException {
        AnchorPane load= FXMLLoader.load(getClass().getResource("/view/rooms_page_form.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) throws IOException {
        AnchorPane load= FXMLLoader.load(getClass().getResource("/view/student_page_form.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void btnUserOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane load= FXMLLoader.load(getClass().getResource("/view/user_page_form.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    public void btnReservationOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane load= FXMLLoader.load(getClass().getResource("/view/reservation_page_form.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }
}
