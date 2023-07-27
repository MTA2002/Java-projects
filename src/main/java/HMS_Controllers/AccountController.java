package HMS_Controllers;

import HMS_Classes.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.function.UnaryOperator;

public class AccountController {

    Hospital_Administrator hospitalAdministrator = new Hospital_Administrator();
    @FXML
    private ChoiceBox<Integer> ageChoiceBox;
    @FXML
    private TextField nameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField phoneTextField;

    @FXML
    void cancelOnAction(ActionEvent event) {

        try {
            hospitalAdministrator.switchScene("login.fxml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) throws Exception {

        String name = nameTextField.getText();
        Integer age = ageChoiceBox.getValue();
        String phone = phoneTextField.getText();
        String password = passwordTextField.getText();
        if (name.length() == 0 || age == null || phone.length() == 0 || password.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("DATA MISSING");
            alert.setHeaderText("SOME DATA IS MISSING");
            alert.setContentText("YOU HAVE TO PROVIDE ALL DATA'S\nMAKE SURE EVERYTHING IS NOT EMPTY!!");
            alert.showAndWait();
        } else {
            Admin admin = new Admin(name.trim(), age, phone.trim(),"Male", password.trim());
            Hospital_Administrator.admins.add(admin);

            try {
                Hospital_Administrator.switcher='A';
                hospitalAdministrator.switchScene("login.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void initialize() {
        for (int i = 21; i <= 80; i++) {
            ageChoiceBox.getItems().add(i);
        }
        TextFormatter<String> textFormatter = new TextFormatter<String>(change -> {
            if (!change.getText().matches("[a-z A-Z]+")) {
                change.setText("");
            }
            return change;
        });

        nameTextField.setTextFormatter(textFormatter);
        UnaryOperator<TextFormatter.Change> phoneFilter = change -> {
            String text = change.getText();
            if (text.matches("[+0-9]*") && change.getControlText().length() + text.length() <= 13) {
                return change;
            }
            return null;
        };
        TextFormatter<String> phoneFormatter = new TextFormatter<>(phoneFilter);
        phoneTextField.setTextFormatter(phoneFormatter);
    }

}
