/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import java.awt.Color;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import kordinator.Kordinator;
/**
 *
 * @author Milosh
 */
public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.put("Button.background", new Color(120, 190, 240));
            UIManager.put("Button.foreground", Color.WHITE);
            UIManager.put("Button.font", new Font("Arial", Font.BOLD, 12));
            UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 12));
            
            UIManager.put("Table.background", new Color(245, 245, 245));
            UIManager.put("Table.alternateRowColor", new Color(220, 235, 245)); // naizmenicne boje redova
            UIManager.put("Table.selectionBackground", new Color(70, 130, 180));
            UIManager.put("Table.selectionForeground", Color.WHITE);
            UIManager.put("TableHeader.background", new Color(70, 130, 180));
            UIManager.put("TableHeader.foreground", Color.WHITE);
            UIManager.put("Panel.background", new Color(250, 250, 250));
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Kordinator.getInstance().otvoriLoginFormu();
    }
}
