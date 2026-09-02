/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import forme.PretraziRacunForma;
import forme.model.ModelTabeleRacun;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Kupac;
import model.Prodavac;
import model.Racun;
import model.Roba;

/**
 *
 * @author Milosh
 */
public class PretraziRacunController {
    private final PretraziRacunForma prf;

    public PretraziRacunController(PretraziRacunForma prf) {
        this.prf = prf;
        addActionListeners();
    }

    private void addActionListeners() {
        prf.addBtnPretraziActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                pretrazi(e);
            }

            private void pretrazi(ActionEvent e) {
                Kupac k = (Kupac) prf.getjComboBoxKupac().getSelectedItem();
                Prodavac p = (Prodavac) prf.getjComboBoxProdavac().getSelectedItem();
                Roba r = (Roba) prf.getjComboBoxRoba().getSelectedItem();
                
                String uslov = "JOIN Kupac ON Racun.idKupac=Kupac.idKupac " +
                   "JOIN Prodavac ON Racun.idProdavac=Prodavac.idProdavac ";
    
                if(r != null) {
                    uslov += "JOIN StavkaRacuna ON Racun.idRacun=StavkaRacuna.idRacun ";
                }

                boolean imaUslov = false;
                if(k != null) {
                    uslov += "WHERE Kupac.idKupac=" + k.getIdKupac() + " ";
                    imaUslov = true;
                }
                if(p != null) {
                    uslov += (imaUslov ? "AND " : "WHERE ") + "Prodavac.idProdavac=" + p.getIdProdavac() + " ";
                    imaUslov = true;
                }
                if(r != null) {
                    uslov += (imaUslov ? "AND " : "WHERE ") + "StavkaRacuna.idRoba=" + r.getIdRoba();
                }
                
                //ModelTabeleRacun mtr = (ModelTabeleRacun) prf.getjTableRacun().getModel();
                //mtr.pretrazi(k,p);
                try{
                    List<Racun> listaRacuna = komunikacija.Komunikacija.getInstance().pretraziRacune(uslov);
                    ModelTabeleRacun mtr = new ModelTabeleRacun(listaRacuna);
                    prf.getjTableRacun().setModel(mtr);
                    if(mtr.getRowCount()==0){
                        JOptionPane.showMessageDialog(prf, "Sistem ne moze da nadje racune po zadatim kriterijumima",
                            "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    }else
                        JOptionPane.showMessageDialog(prf, "Sistem je nasao racune po zadatim kriterijumima",
                            "Uspeh",JOptionPane.INFORMATION_MESSAGE);
                }catch(Exception ex){
                    Logger.getLogger(PretraziRacunController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }  
        });
        prf.addBtnResetujActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                resetuj(e);
            }

            private void resetuj(ActionEvent e) {
                pripremiFormu();
            }

        });
        prf.addBtnPromeniRacunActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovanRed = prf.getjTableRacun().getSelectedRow();
                if(selektovanRed==-1){
                    JOptionPane.showMessageDialog(prf,"Nije selektovan red","Greska",JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleRacun mtr = (ModelTabeleRacun) prf.getjTableRacun().getModel();
                    Racun r = mtr.getListaRacuna().get(selektovanRed);
                    kordinator.Kordinator.getInstance().dodajParam("racun",r);
                    JOptionPane.showMessageDialog(prf, "Sistem je nasao racun","Uspeh",JOptionPane.INFORMATION_MESSAGE);
                    try{
                        kordinator.Kordinator.getInstance().otvoriPromeniRacunFormu();
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(prf, "Sistem ne moze da nadje racun", "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
    }

    public void otvoriFormu() {
        pripremiFormu();
        prf.setVisible(true);
    }

    private void pripremiFormu() {
        try {
            pripremiComboBox();
            List<Racun> listaRacuna = komunikacija.Komunikacija.getInstance().ucitajRacune();
            ModelTabeleRacun mtr = new ModelTabeleRacun(listaRacuna);
            prf.getjTableRacun().setModel(mtr);
            
        } catch (SocketException ex) {
            Logger.getLogger(PretraziRacunController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void pripremiComboBox() throws SocketException {
        prf.getjComboBoxKupac().removeAllItems();
        List<Kupac> listaKupaca = komunikacija.Komunikacija.getInstance().ucitajKupce();
        for (Kupac kupac : listaKupaca) {
            prf.getjComboBoxKupac().addItem(kupac);
        }
        prf.getjComboBoxKupac().setSelectedItem(null);
        
        prf.getjComboBoxRoba().removeAllItems();
        List<Roba> listaRobe = komunikacija.Komunikacija.getInstance().ucitajRobu();
        for(Roba roba:listaRobe){
            prf.getjComboBoxRoba().addItem(roba);
        }
        prf.getjComboBoxRoba().setSelectedItem(null);
        try {
            prf.getjComboBoxProdavac().removeAllItems();
            List<Prodavac> listaProdavaca = komunikacija.Komunikacija.getInstance().ucitajProdavce();
            for (Prodavac prodavac : listaProdavaca) {
                prf.getjComboBoxProdavac().addItem(prodavac);
            }
            prf.getjComboBoxProdavac().setSelectedItem(null);
        } catch (Exception ex) {
            Logger.getLogger(PretraziRacunController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void osveziFormu() {
        try {
            List<Racun> listaRacuna = komunikacija.Komunikacija.getInstance().ucitajRacune();
            ModelTabeleRacun mtr = new ModelTabeleRacun(listaRacuna);
            prf.getjTableRacun().setModel(mtr);
        } catch (SocketException ex) {
            Logger.getLogger(PretraziRacunController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
