/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import forme.PrikazTipKupcaForma;
import forme.model.ModelTabeleTipKupca;
import java.net.SocketException;
import java.util.List;
import model.TipKupca;
/**
 *
 * @author Milosh
 */
public class PrikazTipKupcaController {
    private final PrikazTipKupcaForma ptkf;

    public PrikazTipKupcaController(PrikazTipKupcaForma ptkf) {
        this.ptkf = ptkf;
    }

    public void otvoriFormu() throws SocketException {
        pripremiFormu();
        ptkf.setVisible(true);
    }

    private void pripremiFormu() throws SocketException {
        List<TipKupca> lista = komunikacija.Komunikacija.getInstance().ucitajTipKupaca();
        ModelTabeleTipKupca mttk = new ModelTabeleTipKupca(lista);
        ptkf.getjTableTipKupca().setModel(mttk);
    }
    
}
