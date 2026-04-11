/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Milosh
 */
public class Konfiguracija {
    private static Konfiguracija instance;
    private Properties konfiguracija;

    private Konfiguracija() {
        try {
            konfiguracija=new Properties();
            konfiguracija.load(new FileInputStream("config\\config.properties"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Konfiguracija getInstance() {
        if(instance==null){
            instance = new Konfiguracija();
        }
        return instance;
    }

    public String getURL(){
        return konfiguracija.getProperty("url");
    }
    
    public String getUser(){
        return konfiguracija.getProperty("username");
    }
    
    public String getPassword(){
        return konfiguracija.getProperty("password");
    }
    public String getPort(){
        return konfiguracija.getProperty("port");
    }
    public String setUrl(String value){
        return (String) konfiguracija.setProperty("url", value);  
    }
    public String setUser(String value){
        return (String) konfiguracija.setProperty("username", value);  
    }
    public String setPassword(String value){
        return (String) konfiguracija.setProperty("password", value);  
    }
    public String setPort(String value){
        return (String) konfiguracija.setProperty("port", value+"");  
    }
    public void sacuvajIzmene(){
        try {
            konfiguracija.store(new FileOutputStream("config\\config.properties"), null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
