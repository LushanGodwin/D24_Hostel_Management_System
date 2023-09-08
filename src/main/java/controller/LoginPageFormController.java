package controller;

import bo.BOFactory;
import bo.custom.UserBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPageFormController {
    @FXML
    private JFXTextField txtuserName;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private AnchorPane root;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws IOException {
        AnchorPane load= FXMLLoader.load(getClass().getResource("/view/register_page_form.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {

        String userName = txtuserName.getText();
        String password = txtPassword.getText();

        boolean isValid = userBO.checkUser(userName,password);
        if (isValid){
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_page_form.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(anchorPane));
            Stage stage1 = (Stage) root.getScene().getWindow();
            stage1.close();
            stage.setTitle("D24 Hostel Management System - Dashboard");
            stage.centerOnScreen();
            stage.show();
        }else{
            new Alert(Alert.AlertType.ERROR, "Username and Password incorrect!...").show();
        }
    }

    public void txtUserNameOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) {
        btnLogin.fire();
    }
}
