/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kupci;

import java.util.List;
import model.TipKupca;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Milosh
 */
public class UcitajTipoveKupacaSO extends ApstraktnaGenerickaOperacija {
    private List<TipKupca> listaTipova;

    public List<TipKupca> getListaTipova() {
        return listaTipova;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        //TO DO
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        listaTipova = broker.getAll(new TipKupca(), null);
        if(listaTipova==null)
            System.out.println("PRAZNA LISTA U U UCITAJTIPOVE SO");
        else
            System.out.println("Lista tipova: "+listaTipova);
    }
    
}
