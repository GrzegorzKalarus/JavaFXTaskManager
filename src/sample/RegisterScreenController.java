package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import connectivity.ConnectionClass;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.*;
import java.util.UUID;

public class RegisterScreenController {
    public PreparedStatement pst;
    public RegisterScreenController(){
    }
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField password;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField surname;
    @FXML
    private JFXTextField age;
    @FXML
    private Label registerStatus;


    public void register(javafx.event.ActionEvent Event) throws SQLException, ClassNotFoundException {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        String sql = "INSERT INTO userdata(username,password,name,surname,age)"
                + "VALUES(?,?,?,?,?)";

        pst = connection.prepareStatement(sql);

        pst.setString(1,username.getText());
        pst.setString(2,password.getText());
        pst.setString(3,name.getText());
        pst.setString(4,surname.getText());
        pst.setString(5,age.getText());


        UUID accountNumber = java.util.UUID.randomUUID();

        Statement statement = connection.createStatement();
        pst.executeUpdate();
        registerStatus.setText("Registered Successfully! Click Cancel to go back and LogIn!");
    }

    public void goBack(javafx.event.ActionEvent Event) throws IOException {
        Main m = new Main();
        m.changeScene("loginScreen.fxml");
    }




}
