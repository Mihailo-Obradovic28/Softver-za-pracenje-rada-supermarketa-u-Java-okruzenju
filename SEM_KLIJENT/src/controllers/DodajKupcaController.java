/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import forme.DodajKupcaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Kupac;
import model.TipKupca;
import forme.FormaMod;

/**
 *
 * @author Milosh
 */
public class DodajKupcaController {
    private final DodajKupcaForma dkf;

    public DodajKupcaController(DodajKupcaForma dkf) {
        this.dkf = dkf;
        addActionListeners();
    }
    
    public void otvoriFormu(FormaMod mod) throws SocketException{
        pripremiFormu(mod);
        dkf.setVisible(true);
    }
    
    private void addActionListeners() {
        dkf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                String name = dkf.getjTextFieldIme().getText().trim();
                String lastname = dkf.getjTextFieldPrezime().getText().trim();
                String email = dkf.getjTextFieldEmail().getText().trim();
                int god = Integer.parseInt(dkf.getjTextFieldGodine().getText());
                TipKupca tip = (TipKupca) dkf.getjComboBoxTipKupca().getSelectedItem();
                
                Kupac k = new Kupac(-1,name,lastname,email,god,tip);
                
                try {
                    komunikacija.Komunikacija.getInstance().dodajKupca(k);
                    JOptionPane.showMessageDialog(dkf, "Uspeh u dodavanju kupca","Uspeh",JOptionPane.INFORMATION_MESSAGE);
                    dkf.dispose();
                   
                } catch (SocketException ex) {
                    JOptionPane.showMessageDialog(dkf, "Greska u dodavanju kupca","Greska",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dkf.azurirajAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                azuriraj(e);
            } 

            private void azuriraj(ActionEvent e) {
                Kupac original = (Kupac) kordinator.Kordinator.getInstance().vratiParam("kupac");
                String name = dkf.getjTextFieldIme().getText().trim();
                String lastname = dkf.getjTextFieldPrezime().getText().trim();
                String email = dkf.getjTextFieldEmail().getText().trim();
                int god = Integer.parseInt(dkf.getjTextFieldGodine().getText());
                TipKupca tip = (TipKupca) dkf.getjComboBoxTipKupca().getSelectedItem();
                
                Kupac k = new Kupac(original.getIdKupac(),name,lastname,email,god,tip);
                try {
                    komunikacija.Komunikacija.getInstance().azurirajKupca(k);
                    JOptionPane.showMessageDialog(dkf, "Sistem je zapamtio kupca","Uspeh (azuriranje)",JOptionPane.INFORMATION_MESSAGE);
                    
                } catch (SocketException ex) {
                    JOptionPane.showMessageDialog(dkf, "Greska u azuriranju kupca","Greska",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dkf.obrisiAddActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        Kupac k = (Kupac) kordinator.Kordinator.getInstance().vratiParam("kupac");
        try {
            komunikacija.Komunikacija.getInstance().obrisiKupca(k);
            JOptionPane.showMessageDialog(dkf, "Sistem je obrisao kupca", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            kordinator.Kordinator.getInstance().osveziPregledKupcaForma();
            dkf.dispose();
        } catch (SocketException ex) {
            JOptionPane.showMessageDialog(dkf, "Sistem ne moze da obrise kupca", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }
});
        
    }

    private void pripremiFormu(FormaMod mod) throws SocketException {
        List<TipKupca> lista = komunikacija.Komunikacija.getInstance().ucitajTipKupaca();
        System.out.println(lista);
        for (TipKupca tip : lista) {
            dkf.getjComboBoxTipKupca().addItem(tip);
        }
        
        switch (mod) {
            case DODAJ:
                dkf.getjButtonAzuriraj().setVisible(false);
                dkf.getjButtonDodajKupca().setVisible(true);
                dkf.getjButtonDodajKupca().setEnabled(true);
                dkf.getjButtonObrisi().setVisible(false);
                dkf.getjButtonObrisi().setEnabled(false);
                break;
            case IZMENI:
                dkf.getjButtonObrisi().setVisible(true);
                dkf.getjButtonObrisi().setEnabled(true);
                dkf.getjButtonDodajKupca().setVisible(false);
                dkf.getjButtonAzuriraj().setVisible(true);
                dkf.getjButtonAzuriraj().setEnabled(true);
                Kupac k = (Kupac) kordinator.Kordinator.getInstance().vratiParam("kupac");
                dkf.getjTextFieldIme().setText(k.getIme());
                dkf.getjTextFieldPrezime().setText(k.getPrezime());
                dkf.getjTextFieldEmail().setText(k.getEmail());
                dkf.getjTextFieldGodine().setText(k.getGodine()+"");
                dkf.getjComboBoxTipKupca().setSelectedItem(k.getTip());
                break;
            default:
                throw new AssertionError("Nepostojeca opcija forme mod");
        }
        
        
        
    }

    
    
}
