/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kupci;

import java.util.ArrayList;
import java.util.List;
import model.Kupac;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Milosh
 */
public class UcitajKupceOperacija extends ApstraktnaGenerickaOperacija {
    
    private List<Kupac> listaKupaca = new ArrayList<>();
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        listaKupaca = broker.getAll(new Kupac(), "JOIN TipKupca ON Kupac.idTipKupca = TipKupca.idTipKupca");

    }

    public List<Kupac> getListaKupaca() {
        return listaKupaca;
    }
    
}
