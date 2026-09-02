/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import forme.FormaMod;
import forme.UbaciRacunForma;
import forme.model.ModelTabeleStavkeRacuna;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.Kupac;
import model.Prodavac;
import model.Racun;
import model.Roba;
import model.StavkaRacuna;

/**
 *
 * @author Milosh
 */
public class UbaciRacunController {
    private final UbaciRacunForma urf;

    public UbaciRacunController(UbaciRacunForma urf) {
        this.urf = urf;
        addActionListeners();
    }

    private void addActionListeners() {
        urf.dodajStavkuAddActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ModelTabeleStavkeRacuna model = (ModelTabeleStavkeRacuna) urf.getjTableStavke().getModel();
            model.addRow();
        }
        
        });
        urf.kreirajRacunAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                kreirajRacun(e);
            }

            private void kreirajRacun(ActionEvent e) {
                Kupac kupac = (Kupac) urf.getjComboBoxKupac().getSelectedItem();
                
                Prodavac prodavac = (Prodavac) urf.getjComboBoxProdavac().getSelectedItem();
                
                ModelTabeleStavkeRacuna mtsr = (ModelTabeleStavkeRacuna) urf.getjTableStavke().getModel();
                List<StavkaRacuna> stavke = mtsr.getStavke();
                
                
                BigDecimal ukupno = BigDecimal.ZERO;
                for (StavkaRacuna sr : stavke) {
                  if(sr.getIznos() != null)
                        ukupno = ukupno.add(sr.getIznos());
                }
                
                Racun racun = new Racun(-1, LocalDateTime.now(), ukupno, kupac, prodavac);
                racun.setStavke(stavke);
                
                try{
                    komunikacija.Komunikacija.getInstance().ubaciRacun(racun);
                    JOptionPane.showMessageDialog(urf, "Sistem je zapamtio racun", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    urf.dispose();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(urf, "Sistem ne moze da zapamti racun", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        urf.obrisiStavkuRacunaAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiStavkuRacuna(e);
            }

            private void obrisiStavkuRacuna(ActionEvent e) {
                int selektovanRed = urf.getjTableStavke().getSelectedRow();
                if(selektovanRed==-1){
                    JOptionPane.showMessageDialog(urf,"Nije selektovan red","Greska",JOptionPane.ERROR_MESSAGE);
                }else{
                    if (urf.getjTableStavke().isEditing()) {
                        urf.getjTableStavke().getCellEditor().stopCellEditing();
                    }
                    
                    
                    ModelTabeleStavkeRacuna mtsr = (ModelTabeleStavkeRacuna) urf.getjTableStavke().getModel();
                    mtsr.removeRow(selektovanRed);
                    
                }
            }
        });
        urf.sacuvajPromenuRacuna(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvajPromene(e);
            }

            private void sacuvajPromene(ActionEvent e) {
                Kupac k = (Kupac) urf.getjComboBoxKupac().getSelectedItem();
                
                Prodavac p = (Prodavac) urf.getjComboBoxProdavac().getSelectedItem();
                
                ModelTabeleStavkeRacuna mtsr = (ModelTabeleStavkeRacuna) urf.getjTableStavke().getModel();
                List<StavkaRacuna> stavke = mtsr.getStavke();
                
                BigDecimal ukupno = BigDecimal.ZERO;
                for(StavkaRacuna sr:stavke){
                    if(sr.getIznos() != null) 
                        ukupno =ukupno.add(sr.getIznos());
                }
                Racun original = (Racun) kordinator.Kordinator.getInstance().vratiParam("racun");
                Racun promenjen = new Racun(original.getIdRacun(),original.getDatumIzdavanja(), ukupno, k, p);
                promenjen.setStavke(stavke);
                try{
                    komunikacija.Komunikacija.getInstance().promeniRacun(promenjen);
                    JOptionPane.showMessageDialog(urf, "Sistem je zapamtio racun", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    kordinator.Kordinator.getInstance().osveziPretraziRacunFormu();
                    urf.dispose();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(urf, "Sistem ne moze da zapamti racun",ex.getMessage() , JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void otvoriFormu(Prodavac ulogovani,FormaMod mod) {
        try {
            pripremiFormu(ulogovani, mod);
            urf.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(urf,ex.getMessage(),"Greska",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pripremiFormu(Prodavac ulogovani,FormaMod mod) throws Exception {
        try {
            urf.getjLabelUlogovanProdavac().setText(ulogovani.getIme()+" "+ulogovani.getPrezime());
            
            List<Prodavac> prodavci = komunikacija.Komunikacija.getInstance().ucitajProdavce();
            for (Prodavac prodavac : prodavci) {
                urf.getjComboBoxProdavac().addItem(prodavac);
            }
             urf.getjComboBoxProdavac().setSelectedItem(ulogovani);
            
            List<Kupac> kupci = komunikacija.Komunikacija.getInstance().ucitajKupce();
            System.out.println(kupci);
            for (Kupac kupac : kupci) {
                urf.getjComboBoxKupac().addItem(kupac);
            }
            urf.getjComboBoxKupac().setSelectedItem(null);
            
            List<StavkaRacuna> praznaLista = new ArrayList<>();
            ModelTabeleStavkeRacuna model = new ModelTabeleStavkeRacuna(praznaLista);
            urf.getjTableStavke().setModel(model);
            
            postaviEditorISirinuKolona();
            
            
            switch(mod){
                case DODAJ:
                    urf.getjButtonKreirajRacun().setVisible(true);
                    urf.getjButtonSacuvajIzmene().setVisible(false);
                    break;
                case IZMENI:
                    urf.getjButtonKreirajRacun().setVisible(false);
                    urf.getjButtonSacuvajIzmene().setVisible(true);
                    Racun r = (Racun) kordinator.Kordinator.getInstance().vratiParam("racun");
                    urf.getjComboBoxKupac().setSelectedItem(r.getKupac());//TO DO
                    urf.getjComboBoxProdavac().setSelectedItem(r.getProdavac());
                    List<StavkaRacuna>stavke =komunikacija.Komunikacija.getInstance().ucitajStavkeRacuna(r);
                    ModelTabeleStavkeRacuna modelIzmeni = new ModelTabeleStavkeRacuna(stavke);
                    urf.getjTableStavke().setModel(modelIzmeni);
                    postaviEditorISirinuKolona();
                    break;
                    
            }
            
        } catch (SocketException ex) {
            Logger.getLogger(UbaciRacunController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void postaviEditorISirinuKolona() throws SocketException{
        
        urf.getjTableStavke().getColumnModel().getColumn(0).setPreferredWidth(30);  
        urf.getjTableStavke().getColumnModel().getColumn(1).setPreferredWidth(200); 
        urf.getjTableStavke().getColumnModel().getColumn(2).setPreferredWidth(60);  
        urf.getjTableStavke().getColumnModel().getColumn(3).setPreferredWidth(80);  
        urf.getjTableStavke().getColumnModel().getColumn(4).setPreferredWidth(80);
        
        List<Roba> listaRobe = komunikacija.Komunikacija.getInstance().ucitajRobu();
        JComboBox<Roba> robaCombo = new JComboBox<>();
        //robaCombo.addItem(null);
        for (Roba roba : listaRobe) {
            robaCombo.addItem(roba);
        }
        
        //TO DO proba sa COmboBoxem
        DefaultCellEditor robaEditor = new DefaultCellEditor(robaCombo){
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                JComboBox combo = (JComboBox) super.getTableCellEditorComponent(table, value, isSelected, row, column);
                combo.setSelectedItem(value);
        
                return combo;
            }
        };
        robaEditor.setClickCountToStart(2);
        
        //robaCombo.setSelectedItem(null);
        //urf.getjTableStavke().getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(robaCombo));
        urf.getjTableStavke().getColumnModel().getColumn(1).setCellEditor(robaEditor);
        urf.getjTableStavke().putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
    }


    
}
