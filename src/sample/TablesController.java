package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import domain.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


public class TablesController implements Initializable {


    @FXML
    private TextField tfId;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfSurname;
    @FXML
    private TextField tfAge;
    @FXML
    private TextField tfRole;
    @FXML
    private TextArea taTaskAdmin;


    @FXML
    private TableView<Data> tvData;

    @FXML
    private TableColumn<Data, Integer> colId;
    @FXML
    private TableColumn<Data, String> colUsername;
    @FXML
    private TableColumn<Data, String> colPassword;
    @FXML
    private TableColumn<Data, String> colName;
    @FXML
    private TableColumn<Data, String> colSurname;
    @FXML
    private TableColumn<Data, Integer> colAge;
    @FXML
    private TableColumn<Data, String> colRole;
    @FXML
    private TableColumn<Data, String> colTask;

    @FXML
    private JFXButton btnInsert;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnLogOut;


    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {

        if(event.getSource() == btnInsert){
            insertRecord();
        }else if (event.getSource() == btnUpdate){
            updateRecord();
        }else if(event.getSource() == btnDelete){
            deleteButton();
        }
        else if(event.getSource()==btnLogOut){
            logOutButton();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showData();
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

    public ObservableList<Data> getDataList(){
        ObservableList<Data> dataList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM userdata";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Data data;
            while(rs.next()){
                data = new Data(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("name"), rs.getString("surname"), rs.getInt("age"),rs.getString("role"),rs.getString("task"));
                dataList.add(data);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return dataList;
    }

    public void showData(){
        ObservableList<Data> list = getDataList();

        colId.setCellValueFactory(new PropertyValueFactory<Data, Integer>("id"));
        colUsername.setCellValueFactory(new PropertyValueFactory<Data, String>("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<Data, String>("password"));
        colName.setCellValueFactory(new PropertyValueFactory<Data, String>("name"));
        colSurname.setCellValueFactory(new PropertyValueFactory<Data, String>("surname"));
        colAge.setCellValueFactory(new PropertyValueFactory<Data, Integer>("age"));
        colRole.setCellValueFactory(new PropertyValueFactory<Data, String>("role"));
        colTask.setCellValueFactory(new PropertyValueFactory<Data, String>("task"));


        tvData.setItems(list);
    }
   // public PreparedStatement pst;
    private void insertRecord(){

        String query = "INSERT INTO userdata(id,username,password,name,surname,age,role,task) VALUES ('" + tfId.getText() + "','" + tfUsername.getText() + "','" + tfPassword.getText() + "','" + tfName.getText() + "','" + tfSurname.getText() + "','" + tfAge.getText() + "','"
                + tfRole.getText() +"','" + taTaskAdmin.getText() + "')";
        executeQuery(query);
        showData();
    }
    private void updateRecord(){
        String query = "UPDATE  userdata SET username  = '" + tfUsername.getText() + "', password = '" + tfPassword.getText()+ "', name = '" + tfName.getText() + "', surname = '" + tfSurname.getText()+ "', age = " +
                tfAge.getText() + ", role = '" + tfRole.getText() + "', task = '" + taTaskAdmin.getText()  + "' WHERE id = " + tfId.getText() + "";
        executeQuery(query);
        showData();
    }
    private void deleteButton(){
        String query = "DELETE FROM userdata WHERE id =" + tfId.getText() + "";
        executeQuery(query);
        showData();
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

    @FXML
    public void handleMouseAction(MouseEvent e){
        Data data = tvData.getSelectionModel().getSelectedItem();
        tfId.setText("" + data.getId());
        tfUsername.setText(data.getUsername());
        tfPassword.setText(data.getPassword());
        tfName.setText(data.getName());
        tfSurname.setText(data.getSurname());
        tfAge.setText("" + data.getAge());
        tfRole.setText(data.getRole());
        taTaskAdmin.setText(data.getTask());
    }




}