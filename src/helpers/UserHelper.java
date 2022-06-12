package helpers;

import DTO.CurrentUserDTO;
import connectivity.ConnectionClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserHelper {
    private static CurrentUserDTO loggedUser;

    public static void createLoggedUser(String userName, String name, String surname, int age, String role, String task)
    {
        loggedUser = new CurrentUserDTO(userName, name, surname, age, role, task);
    }

    public static CurrentUserDTO getCurrentUser()
    {
        return loggedUser;
    }

    public static void UpdateCurrentUserFunds() throws SQLException, ClassNotFoundException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql = "SELECT * FROM userdata WHERE username=?";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, loggedUser.userName);
        ResultSet rs = pst.executeQuery();
        rs.next();

        String currentuserTasks = rs.getString("Task");

        loggedUser.task = currentuserTasks;

        connection.close();
    }
}
