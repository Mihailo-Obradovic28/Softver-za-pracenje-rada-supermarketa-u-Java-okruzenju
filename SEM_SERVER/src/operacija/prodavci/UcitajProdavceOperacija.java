/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.prodavci;

import java.util.List;
import model.Prodavac;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Milosh
 */
public class UcitajProdavceOperacija extends ApstraktnaGenerickaOperacija {
    private List<Prodavac> listaProdavaca;

    public List<Prodavac> getListaProdavaca() {
        return listaProdavaca;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        listaProdavaca = broker.getAll(new Prodavac(),null);
        if(listaProdavaca==null)
            System.out.println("PRAZNA LISTA U U UCITAJPRODAVCE SO");
        else
            System.out.println("Lista prodavaca: "+listaProdavaca);
    }
    
}
