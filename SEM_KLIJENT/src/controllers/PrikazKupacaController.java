/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import forme.PrikazKupacaForma;
import java.util.List;
import model.Kupac;
import forme.model.ModelTabeleKupac;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JOptionPane;
import model.TipKupca;
/**
 *
 * @author Milosh
 */
public class PrikazKupacaController {
    private final PrikazKupacaForma pkf;

    public PrikazKupacaController(PrikazKupacaForma pkf) {
        this.pkf = pkf;
        try {
            popuniCombo();
        } catch (SocketException ex) {
            Logger.getLogger(PrikazKupacaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        addActionListeners();
    }

    private void addActionListeners() {
        /*pkf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovanRed = pkf.getjTableKupci().getSelectedRow();
                if(selektovanRed==-1){
                    JOptionPane.showMessageDialog(pkf,"Sistem ne moze da nadje kupca","Greska",JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleKupac mtk = (ModelTabeleKupac) pkf.getjTableKupci().getModel();
                    Kupac k = mtk.getLista().get(selektovanRed);
                    try {
                        komunikacija.Komunikacija.getInstance().obrisiKupca(k);
                        JOptionPane.showMessageDialog(pkf,"Sistem je obrisao kupca","USPEH",JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    } catch (SocketException ex) {
                        JOptionPane.showMessageDialog(pkf, "Sistem ne moze da obrise kupca","Greska",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });*/
        pkf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovanRed = pkf.getjTableKupci().getSelectedRow();
                if(selektovanRed==-1){
                    JOptionPane.showMessageDialog(pkf,"Nije selektovan red","Greska",JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleKupac mtk = (ModelTabeleKupac) pkf.getjTableKupci().getModel();
                    Kupac k = mtk.getLista().get(selektovanRed);
                    kordinator.Kordinator.getInstance().dodajParam("kupac", k);
                    
                    JOptionPane.showMessageDialog(pkf, "Sistem je nasao kupca", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    
                    try {
                        kordinator.Kordinator.getInstance().otvoriIzmeniKupcaFormu();//ista forma kao i dodaj
                        
                    } catch (SocketException ex) {
                        JOptionPane.showMessageDialog(pkf, "Sistem ne moze da azurira kupca","Greska",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        pkf.addBtnPretraziActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                pretrazi(e);
            }

            private void pretrazi(ActionEvent e) {
                /*String name = pkf.getjTextFieldIme().getText().trim();
                String lastname = pkf.getjTextFieldPrezime().getText().trim();
                String email = pkf.getjTextFieldEmail().getText().trim();
                TipKupca tip = (TipKupca) pkf.getjComboBoxTipKupca().getSelectedItem();
                
                ModelTabeleKupac mtk = (ModelTabeleKupac) pkf.getjTableKupci().getModel();
                mtk.pretrazi(name,lastname,email,tip);
                if(mtk.getRowCount() == 0) { 
                    JOptionPane.showMessageDialog(pkf, "Sistem nije nasao kupce po zadatim kriterijumima", 
                        "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    
                }else{
                    JOptionPane.showMessageDialog(pkf, "Sistem je nasao kupce po zadatim kriterijumima","Uspeh",JOptionPane.INFORMATION_MESSAGE);
                }*/
                String uslov = "JOIN TipKupca ON Kupac.idTipKupca=TipKupca.idTipKupca ";
                boolean imaUslov = false;
                TipKupca tip = (TipKupca) pkf.getjComboBoxTipKupca().getSelectedItem();
                if(pkf.getjTextFieldIme() != null &&
                        !pkf.getjTextFieldIme().getText().trim().isEmpty()) {
                    uslov += "WHERE Kupac.ime LIKE '%" + pkf.getjTextFieldIme().getText().trim() + "%' ";
                    imaUslov = true;
                }
                if(pkf.getjTextFieldPrezime() != null &&
                        !pkf.getjTextFieldPrezime().getText().trim().isEmpty()) {
                    uslov += (imaUslov ? "AND " : "WHERE ") + "kupac.prezime LIKE '%" + pkf.getjTextFieldPrezime().getText().trim() + "%' ";
                    imaUslov = true;
                }
                if(pkf.getjTextFieldEmail()!= null &&
                        !pkf.getjTextFieldEmail().getText().trim().isEmpty()) {
                    uslov += (imaUslov ? "AND " : "WHERE ") + "kupac.email LIKE '%" + pkf.getjTextFieldEmail().getText().trim()+ "%' ";
                    imaUslov = true;
                }
                if(tip!=null){
                    uslov+=(imaUslov ? "AND ": "WHERE ") + "kupac.IdTipKupca="+tip.getIdTipKupca();
                }
                
                try{
                    List<Kupac> listaKupaca = komunikacija.Komunikacija.getInstance().pretraziKupce(uslov);
                    ModelTabeleKupac mtk = new ModelTabeleKupac(listaKupaca);
                    pkf.getjTableKupci().setModel(mtk);
                    if(mtk.getRowCount()==0){
                        JOptionPane.showMessageDialog(pkf, "Sistem ne moze da nadje kupce po zadatim kriterijumima",
                            "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    }else
                        JOptionPane.showMessageDialog(pkf, "Sistem je nasao kupce po zadatim kriterijumima",
                            "Uspeh",JOptionPane.INFORMATION_MESSAGE);
                ;
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(pkf, "Greska pri komunikaciji sa serverom.", "Greska", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(PretraziRacunController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        pkf.addBtnResetujActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                resetuj(e);
            }

            private void resetuj(ActionEvent e) {
                pkf.getjTextFieldIme().setText("");
                pkf.getjTextFieldPrezime().setText("");
                pkf.getjTextFieldEmail().setText("");
                pkf.getjComboBoxTipKupca().setSelectedItem(null);
                try {
                    osveziFormu();
                } catch (SocketException ex) {
                    Logger.getLogger(PrikazKupacaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
        });
    }

    public void otvoriFormu() throws SocketException {
        pripremiFormu();
        pkf.setVisible(true);
    }

    private void pripremiFormu() throws SocketException {
        
        
        List<Kupac> listaKupaca = komunikacija.Komunikacija.getInstance().ucitajKupce();
        ModelTabeleKupac mtk = new ModelTabeleKupac(listaKupaca);
        pkf.getjTableKupci().setModel(mtk);
    }

    public void osveziFormu() throws SocketException {
        pripremiFormu();
    }
    public void popuniCombo() throws SocketException{
        List<TipKupca> lista = komunikacija.Komunikacija.getInstance().ucitajTipKupaca();
        System.out.println(lista);
        for (TipKupca tip : lista) {
            pkf.getjComboBoxTipKupca().addItem(tip);
        }
        pkf.getjComboBoxTipKupca().setSelectedItem(null);
    }
    
}
