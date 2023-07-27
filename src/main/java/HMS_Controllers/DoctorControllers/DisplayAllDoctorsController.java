package HMS_Controllers.DoctorControllers;

import HMS_Classes.Doctor;
import HMS_Controllers.Hospital_Administrator;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DisplayAllDoctorsController {
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();
    @FXML
    private TableColumn<Doctor, Integer> ageColumn=new TableColumn<>("Age");

    @FXML
    private TableView<Doctor> doctorTable;
    @FXML
    private TableColumn<Doctor, String> genderColumn;
    @FXML
    private TableColumn<Doctor, String> passwordColumn;
    @FXML
    private TableColumn<Doctor, String> idColumn;

    @FXML
    private TableColumn<Doctor, String> nameColumn;

    @FXML
    private TableColumn<Doctor, String> phoneColumn;

    @FXML
    private TableColumn<Doctor, Double> salaryColumn;

    @FXML
    private TableColumn<Doctor, String> specialityColumn;

    @FXML
    private void initialize(){

        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStaffId()));
        ageColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getAge()));
        genderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGender()));
        phoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
        salaryColumn.setCellValueFactory(param -> new SimpleDoubleProperty(param.getValue().getSalary()).asObject());
        specialityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSpeciality()));
        passwordColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassword()));
        doctorTable.setItems(hospitalAdministrator.getDoctorList());

    }
    @FXML
    public void backButtonOnAction(){
        try {
            Hospital_Administrator.switcher='D';
            hospitalAdministrator.switchScene("doctorManagementScene.fxml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

