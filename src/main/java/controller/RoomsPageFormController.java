package controller;

import bo.BOFactory;
import bo.custom.RoomBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.RoomDTO;
import dto.StudentDTO;
import entity.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tm.RoomTM;
import tm.StudentTM;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class RoomsPageFormController implements Initializable {
    @FXML
    private JFXTextField txtSearch;

    @FXML
    private TextField txtRoomId;

    @FXML
    private TextField txtType;

    @FXML
    private TextField txtKeyMoney;

    @FXML
    private TextField txtQty;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private TableView<RoomTM> tblRoom;

    @FXML
    private TableColumn<?, ?> colRoomId;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colKeyMoney;

    @FXML
    private TableColumn<?, ?> colQty;


    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        RoomTM selectedItem = tblRoom.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            boolean isDeleted = roomBO.deleteRoom(selectedItem.getRoom_id());
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Room deleted!...").show();
                getAll();
                refreshTable();
                clearAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "Room not deleted   !...").show();
            }
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoom_type_id(txtRoomId.getText());
        roomDTO.setType(txtType.getText());
        roomDTO.setKey_money(Double.valueOf(txtKeyMoney.getText()));
        roomDTO.setQty(Integer.valueOf(txtQty.getText()));
        boolean isSaved = roomBO.saveRoom(roomDTO);
        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "Room saved!...").show();
            getAll();
        } else {
            new Alert(Alert.AlertType.ERROR, "Room not saved!...").show();
        }

    }

    private void generateNextId() {
        String nextId = roomBO.generatenextStudentId();
        txtRoomId.setText(nextId);
    }

    private void refreshTable() {
        ObservableList<RoomTM> observableList = FXCollections.observableArrayList();
        List<RoomDTO> roomDTOList = roomBO.getAllRoom();
        for (RoomDTO roomDTO : roomDTOList) {
            observableList.add(new RoomTM(
                           roomDTO.getRoom_type_id(),
                           roomDTO.getType(),
                           roomDTO.getKey_money(),
                           roomDTO.getQty()
                           )
            );
        }
        tblRoom.setItems(observableList);
    }

    private void clearAll() {
        txtRoomId.clear();
        txtType.clear();
        txtKeyMoney.clear();
        txtQty.clear();
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String roomId = txtSearch.getText();
        RoomDTO roomDTO = roomBO.searchRoom(roomId);
        if (roomDTO != null){
            fillDate(roomDTO);
        }else {
            new Alert(Alert.AlertType.WARNING,"heee");
        }
    }

    private void fillDate(RoomDTO roomDTO) {
        txtRoomId.setText(roomDTO.getRoom_type_id());
        txtType.setText(roomDTO.getType());
        txtKeyMoney.setText(String.valueOf(roomDTO.getKey_money()));
        txtQty.setText(String.valueOf(roomDTO.getQty()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshTable();
        setCellValueFactory();
        generateNextId();
        getAll();
    }

    private void getAll() {
    }

    private void setCellValueFactory() {
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("room_id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoom_type_id(txtRoomId.getText());
        roomDTO.setType(txtType.getText());
        roomDTO.setKey_money(Double.valueOf(txtKeyMoney.getText()));
        roomDTO.setQty(Integer.valueOf(txtQty.getText()));

        boolean isUpdated = roomBO.updateRoom(roomDTO);
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "Room updated!...").show();
            refreshTable();
            clearAll();
        } else {
            new Alert(Alert.AlertType.ERROR, "Room not updated!...").show();
        }
    }


    @FXML
    void roomTblOnMouseClicked(MouseEvent event) {
        RoomTM selectedItem = tblRoom.getSelectionModel().getSelectedItem();
        try {
            if (selectedItem != null) {
                btnDelete.setDisable(false);
                txtRoomId.setText(selectedItem.getRoom_id());
                txtType.setText(selectedItem.getType());
                txtKeyMoney.setText(String.valueOf(selectedItem.getKey_money()));
                txtQty.setText(String.valueOf(selectedItem.getQty()));
//                saveBtn.setText("Update");
//                saveBtn.setStyle("-fx-background-color: #1A5D1A");
            }

        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
