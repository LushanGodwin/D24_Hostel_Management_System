package controller;

import bo.BOFactory;
import bo.custom.UserBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDTO;
import dto.UserDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tm.StudentTM;
import tm.UserTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserPageFormController implements Initializable {
    @FXML
    private JFXTextField txtUserId;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtHint;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private TableView<UserTM> tblUser;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colRePassword;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        UserTM selectedItem = tblUser.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            boolean isDeleted = userBO.deleteStudent(selectedItem.getUserId());
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student deleted!...").show();
                getAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "Student not deleted   !...").show();
            }
        }
    }

    private void getAll() {
        ObservableList<UserTM> observableList = FXCollections.observableArrayList();
        List<UserDTO> customerDTOList = userBO.getAllStudent();
        for (UserDTO userDTO : customerDTOList) {
            observableList.add(new UserTM(
                            userDTO.getUserId(),
                            userDTO.getUserName(),
                            userDTO.getPassword(),
                            userDTO.getPasswordHint()
                    )
            );
        }
        tblUser.setItems(observableList);
    }

    @FXML
    void tblUserOnMouseClicked(MouseEvent event) {
        UserTM selectedItem = tblUser.getSelectionModel().getSelectedItem();
        try {
            if (selectedItem != null) {
                deleteBtn.setDisable(false);
                txtUserId.setText(selectedItem.getUserId());
                txtUserName.setText(selectedItem.getUserName());
                txtPassword.setText(selectedItem.getPasswordHint());
            }
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colRePassword.setCellValueFactory(new PropertyValueFactory<>("passwordHint"));
    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(txtUserId.getText());
        userDTO.setUserName(txtUserName.getText());
        userDTO.setPassword(txtPassword.getText());
        userDTO.setPasswordHint(txtHint.getText());

        boolean isSaved = userBO.saveUser(userDTO);

        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION, "User saved successfully!...").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "User not saved!...").show();
        }
    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(txtUserId.getText());
        userDTO.setUserName(txtUserName.getText());
        userDTO.setPassword(txtPassword.getText());
        userDTO.setPasswordHint(txtHint.getText());

        boolean isUpdated = userBO.updateUser(userDTO);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
    }
}
