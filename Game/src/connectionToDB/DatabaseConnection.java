/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connectionToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    private String url="jdbc:mysql://localhost:3306/word_game";
    private String user="root";
    private String password="root";

    private DatabaseConnection() throws SQLException{
        try {
            this.connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if(instance==null)
            instance=new DatabaseConnection();
        else if(instance.getConnection().isClosed())
            instance=new DatabaseConnection();
        return instance;
    }
}
