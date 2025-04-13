package org.example;

import org.jsoup.nodes.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Indexer {

    static Connection connection = null;
    Indexer(Document document, String url) throws SQLException, ClassNotFoundException {
        //Select important elements of document
        String title = document.title();
        String link = url;
        String text = document.text();
        //Save these elements to database
        try {
            connection = DatabaseConneciton.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into pages value(?, ?,?);");
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, url);
            preparedStatement.setString(3, text);
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
