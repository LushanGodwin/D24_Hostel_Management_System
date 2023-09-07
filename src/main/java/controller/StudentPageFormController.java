package controller;

import bo.BOFactory;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tm.StudentTM;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class StudentPageFormController implements Initializable {
    @FXML
    private TableView<StudentTM> studentTbl;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colGender;
    @FXML
    private TextField txtId;

    @FXML
    private TextField txtType;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private DatePicker date;

    @FXML
    private RadioButton maleBtn;

    @FXML
    private RadioButton femaleBtn;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXButton deleteBtn;


    @FXML
    private JFXTextField txtSearch;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        StudentTM selectedItem = studentTbl.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            boolean isDeleted = studentBO.deleteStudent(selectedItem.getStudent_id());
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student deleted!...").show();
                getAll();
                refreshTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Student not deleted   !...").show();
            }
        }
    }

    private void getAll() {
    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {
        StudentDTO studentDto = new StudentDTO();
        studentDto.setStudent_id(txtId.getText());
        studentDto.setName(txtType.getText());
        studentDto.setAddress(txtAddress.getText());
        studentDto.setGender((maleBtn.isSelected()) ? "Male" : "Female");
        studentDto.setContact_no(txtContact.getText());
        studentDto.setDate(Date.valueOf(date.getValue()));

        studentBO.saveStudent(studentDto);
        new Alert(Alert.AlertType.INFORMATION, "Student Added").show();
        clearAll();
        refreshTable();
        generateNextId();
    }

    private void refreshTable() {
        ObservableList<StudentTM> observableList = FXCollections.observableArrayList();
        List<StudentDTO> customerDTOList = studentBO.getAllStudent();
        for (StudentDTO studentDTO : customerDTOList) {
            observableList.add(new StudentTM(
                            studentDTO.getStudent_id(),
                            studentDTO.getName(),
                            studentDTO.getAddress(),
                            studentDTO.getContact_no(),
                            studentDTO.getDate(),
                            studentDTO.getGender()
                    )
            );
        }
        studentTbl.setItems(observableList);
    }

    private void clearAll() {
        txtId.clear();
        txtType.clear();
        txtAddress.clear();
        txtContact.clear();
        date.setValue(null);
        maleBtn.setSelected(false);
        femaleBtn.setSelected(false);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshTable();
        setCellValueFactory();
        generateNextId();
        getAll();
    }

    private void generateNextId() {
        String nextId = studentBO.generatenextStudentId();
        txtId.setText(nextId);
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudent_id(txtId.getText());
        studentDTO.setName(txtType.getText());
        studentDTO.setAddress(txtAddress.getText());
        studentDTO.setContact_no(txtContact.getText());
        studentDTO.setDate(Date.valueOf(date.getValue()));
        studentDTO.setGender((maleBtn.isSelected()) ? "Male" : "Female");

        boolean isUpdated = studentBO.updateStudent(studentDTO);
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "Student updated!...").show();
            refreshTable();
            clearAll();
        } else {
            new Alert(Alert.AlertType.ERROR, "Student not updated!...").show();
        }
    }

    @FXML
    void studentTblOnMouseClicked(MouseEvent event) {
        StudentTM selectedItem = studentTbl.getSelectionModel().getSelectedItem();
        try {
            if (selectedItem != null) {
                deleteBtn.setDisable(false);
                txtId.setText(selectedItem.getStudent_id());
                txtType.setText(selectedItem.getName());
                txtAddress.setText(selectedItem.getAddress());
                if (selectedItem.getGender().equals("Male")) {
                    maleBtn.setSelected(true);
                } else {
                    femaleBtn.setSelected(true);
                }
                txtContact.setText(selectedItem.getContact_no());
                date.setValue(selectedItem.getDob().toLocalDate());
//                saveBtn.setText("Update");
//                saveBtn.setStyle("-fx-background-color: #1A5D1A");
            }

        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String studentId = txtSearch.getText();
        StudentDTO studentDTO = studentBO.searchStudent(studentId);
        if (studentDTO != null){
            fillDate(studentDTO);
        }else {
            new Alert(Alert.AlertType.WARNING,"heee");
        }
    }

    private void fillDate(StudentDTO studentDTO) {
        txtId.setText(studentDTO.getStudent_id());
        txtType.setText(studentDTO.getName());
        txtAddress.setText(studentDTO.getAddress());
        txtContact.setText(studentDTO.getContact_no());
        date.setValue(studentDTO.getDate().toLocalDate());
        if ("Male".equals(studentDTO.getGender())){
            maleBtn.setSelected(true);
            femaleBtn.setSelected(false);
        }else{
            maleBtn.setSelected(false);
            femaleBtn.setSelected(true);
        }
    }

}
