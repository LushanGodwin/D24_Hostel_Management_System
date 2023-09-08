package controller;

import bo.BOFactory;
import bo.custom.UserBO;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dto.UserDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class RegisterPageFormController {

    @FXML
    private AnchorPane registerPage;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXPasswordField txtRePassword;

    @FXML
    private Button btnRegister;

    @FXML
    private JFXTextField txtuserId;

    @FXML
    private JFXPasswordField txtPasswordHint;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws IOException {

        String id = txtuserId.getText();
        String name = txtUserName.getText();
        String password = txtPassword.getText();
        String passwordHint = txtPasswordHint.getText();

        UserDTO userDTO = new UserDTO(id,name,password,passwordHint);
        boolean isSaved = userBO.saveUser(userDTO);

        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION, "Registeration Successful!...").show();
            Parent load= FXMLLoader.load(getClass().getResource("/view/login_page_form.fxml"));
            registerPage.getChildren().clear();
            registerPage.getChildren().add(load);
        }else {
            new Alert(Alert.AlertType.ERROR,"User not Add!...").show();
        }
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        btnRegister.fire();
    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

    public void txtRePasswordOnAction(ActionEvent actionEvent) {

    }
}
