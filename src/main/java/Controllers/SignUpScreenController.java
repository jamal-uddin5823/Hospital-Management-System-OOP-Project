package Controllers;

import Models.Receptionist;
import Models.ManagementUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpScreenController implements Initializable {



    @FXML
    private Button logInScreenButton;
    @FXML
    private Button signUpButton;
    @FXML
    private TextField signUpEmail;

    @FXML
    private PasswordField signUpPass;
    @FXML
    private PasswordField signUpConfirmPass;
    @FXML
    private Label errorMessage;


    Receptionist receptionist = new Receptionist();
    boolean successfulSignUp = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        logInScreenButton.setOnAction( e -> {
            ManagementUtils.changeScence(e,"LogInScreen.fxml","Login");
        });

        signUpButton.setOnAction( e -> {
            successfulSignUp = receptionist.signUp(signUpEmail,signUpPass,signUpConfirmPass,errorMessage);
            ManagementUtils.changeScence(e,"PatientTypeScreen.fxml","Reception");
        });
    }
}
