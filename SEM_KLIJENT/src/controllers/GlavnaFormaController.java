/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import forme.GlavnaForma;
import model.Prodavac;
/**
 *
 * @author Milosh
 */
public class GlavnaFormaController {
    private final GlavnaForma gf;

    public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;
        addActionListeners();//za sve dugmice cu praviti posle TO DO
    }

    private void addActionListeners() {
        
    }

    public void otvoriFormu(Prodavac ulogovani) {
        gf.setVisible(true);
        gf.getjLabelUlogovan().setText(ulogovani.getIme()+" "+ulogovani.getPrezime());
    }
    
    
}
