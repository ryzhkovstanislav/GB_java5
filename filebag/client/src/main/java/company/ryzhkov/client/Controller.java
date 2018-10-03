package company.ryzhkov.client;

import company.ryzhkov.common.AuthMessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Client client;

    @FXML
    VBox authBox;

    @FXML
    Button sendButton;

    @FXML
    TextField username;

    @FXML
    PasswordField password;

    public void sendUsernameAndPassword() {
        client.senMessage(new AuthMessage(username.getText(), password.getText()));
        sendButton.setStyle("-fx-background-color: #777777;");
        username.clear();
        password.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        client = new Client();
        client.runServer();
    }
}
