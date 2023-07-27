package HMS_Controllers.ReceptionistSideControllers;

import HMS_Classes.Patient;
import HMS_Controllers.Hospital_Administrator;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FoundController {
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();

    @FXML
    private TableColumn<Patient, Integer> ageColumn;

    @FXML
    private TableColumn<Patient, Double> billColumn;

    @FXML
    private TableColumn<Patient, String> checkInStatusColumn;

    @FXML
    private TableColumn<Patient, String> genderColumn;

    @FXML
    private TableColumn<Patient, String> idColumn;

    @FXML
    private TableColumn<Patient, String> nameColumn;

    @FXML
    private TableColumn<Patient, String> passwordColumn;

    @FXML
    private TableView<Patient> patientFoundTable;

    @FXML
    private TableColumn<Patient, String> phoneColumn;

    @FXML
    void backButtonOnAction(ActionEvent event) {
        Hospital_Administrator.switcher='r';
        try {
          hospitalAdministrator.switchScene("patientManagementScene.fxml");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    public void initialize(){
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPatientID()));
        ageColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getAge()));
        genderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGender()));
        phoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
        checkInStatusColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCheckInValue()));
        passwordColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassword()));
        billColumn.setCellValueFactory(param -> new SimpleDoubleProperty(param.getValue().getBill()).asObject());
        patientFoundTable.setItems(hospitalAdministrator.getFoundPatientList());
    }

}
