package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import DTO.CurrentUserDTO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import domain.DataUser;
import helpers.UserHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import static helpers.UserHelper.UpdateCurrentUserFunds;


public class UserTablesController implements Initializable {



    private CurrentUserDTO currentUser;
    @FXML
    private JFXTextArea taTask;
    @FXML
    private JFXButton btnLogOut;
    @FXML
    private JFXTextField tfUserName;
    @FXML
    private JFXTextField tfName;
    @FXML
    private JFXTextField tfSurname;
    @FXML
    private JFXTextField tfAge;
    @FXML
    private JFXTextField tfRole;


    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        if(event.getSource() == btnLogOut){
            logOutButton();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //showData();
        currentUser = UserHelper.getCurrentUser();
        try {
            UpdateCurrentUserFunds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        UpdateMainLabel();

    }
    private void UpdateMainLabel()
    {
        tfUserName.setText("Username: " + currentUser.userName);
        tfName.setText("Name: " + currentUser.name);
        tfSurname.setText("Surname: " + currentUser.surname);
        tfAge.setText("Age: " + currentUser.age);
        tfRole.setText("Role: " + currentUser.role);
        taTask.setText(currentUser.task);
    }

    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskmanagerdatabase", "root","toor");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    private void logOutButton() throws IOException {
        Main m = new Main();
        m.changeScene("loginScreen.fxml");
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


}