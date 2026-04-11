/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import forme.DodajTerminDezurstvaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import model.TerminDezurstva; 
       
/**
 *
 * @author Milosh
 */
public class DodajTerminDezurstvaController {
    private final DodajTerminDezurstvaForma dtf;
    
    DateTimeFormatter dtfVreme = DateTimeFormatter.ofPattern("H:mm");
    
    public DodajTerminDezurstvaController(DodajTerminDezurstvaForma dtf) {
        this.dtf=dtf;
        addActionListeners();
    }

    private void addActionListeners() {
        dtf.dodajAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajDezurstvo(e);
            }

            private void dodajDezurstvo(ActionEvent e) {
                try{
                    LocalTime vremeP =  LocalTime.parse(dtf.getjTextFieldVremePocetka().getText().trim(),dtfVreme);
                    LocalTime vremeZ = LocalTime.parse(dtf.getjTextFieldVremeZavrsetka().getText().trim(),dtfVreme);
                    TerminDezurstva td = new TerminDezurstva(-1,vremeP,vremeZ);
                
                    komunikacija.Komunikacija.getInstance().dodajTerminDezurstva(td);
                    JOptionPane.showMessageDialog(dtf, "Sistem je zapamtio termin dezurstva","Uspeh",JOptionPane.INFORMATION_MESSAGE);
                    dtf.dispose();
                }catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(dtf, "Sistem ne moze da zapamti termin dezurstva",
                            "Format vremena mora biti H:mm (npr. 08:00 ili 8:00)", JOptionPane.ERROR_MESSAGE);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(dtf, "Sistem ne moze da zapamti termin dezurstva",ex.getMessage(),JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
    }

    public void otvoriFormu() {
        dtf.setVisible(true);
    }
    
}
