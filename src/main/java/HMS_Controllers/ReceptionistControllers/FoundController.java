package HMS_Controllers.ReceptionistControllers;

import HMS_Classes.Doctor;
import HMS_Classes.Receptionist;
import HMS_Controllers.Hospital_Administrator;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public  class FoundController {
    Hospital_Administrator hospitalAdministrator=new Hospital_Administrator();
    @FXML
    private TableColumn<Receptionist, Integer> ageColumn;
    @FXML
    private TableView<Receptionist> receptionistFoundTable;
    @FXML
    private TableColumn<Receptionist, String> genderColumn;
    @FXML
    private TableColumn<Receptionist, String> passwordColumn;
    @FXML
    private TableColumn<Receptionist, String> idColumn;
    @FXML
    private TableColumn<Receptionist, String> nameColumn;
    @FXML
    private TableColumn<Receptionist, String> phoneColumn;
    @FXML
    private TableColumn<Receptionist, Double> salaryColumn;

    @FXML
    private void initialize(){
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStaffId()));
        genderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGender()));
        ageColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getAge()));
        phoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
        passwordColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassword()));
        salaryColumn.setCellValueFactory(param -> new SimpleDoubleProperty(param.getValue().getSalary()).asObject());
        try {
            receptionistFoundTable.setItems(hospitalAdministrator.getFoundReceptionistList());
        }catch (RuntimeException e){
            e.printStackTrace();
        }

    }
    @FXML
    void goBackOnAction(ActionEvent event) {
        try {
            Hospital_Administrator.switcher='R';
            hospitalAdministrator.switchScene("ReceptionistManagementScene.fxml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}