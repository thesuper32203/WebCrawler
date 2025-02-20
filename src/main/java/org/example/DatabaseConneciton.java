package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConneciton {

    String variable = System.getenv("password");
    //To get the value of the above example

    static Connection connection = null;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        if(connection!= null){
            return connection;
        }
        String user = "root";
        String pwd = variable;
        String db = "searchenginapp";
        return getConnection(user,pwd,db);
    }

    private Connection getConnection(String userName, String passWord, String db) {
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           connection = DriverManager.getConnection("jdbc:mysql://localhost/" + db + "?user=" + userName + "&password=" + passWord);

       } catch (SQLException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException classNotFoundException) {
           classNotFoundException.printStackTrace();
       }
        return connection;

    }
}
