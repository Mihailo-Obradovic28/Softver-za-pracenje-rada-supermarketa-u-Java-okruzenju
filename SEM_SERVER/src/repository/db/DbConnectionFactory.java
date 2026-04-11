/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Milosh
 */
public class DbConnectionFactory {
    private static DbConnectionFactory instance;
    private Connection connection;
    
    private DbConnectionFactory() {
        
        
    }

    public static DbConnectionFactory getInstance() {
        if(instance==null){
            instance=new DbConnectionFactory();
        }
        return instance;
    }
    
    //synchronized ako treba visenitnost
    public Connection getConnection() {
        try {
            if(connection==null || connection.isClosed()){
                String url = konfiguracija.Konfiguracija.getInstance().getURL();
                String username=konfiguracija.Konfiguracija.getInstance().getUser();
                String password = konfiguracija.Konfiguracija.getInstance().getPassword();
                connection = DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
    
    
}
