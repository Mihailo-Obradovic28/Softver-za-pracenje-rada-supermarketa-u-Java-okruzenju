/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.login;


import java.util.List;
import model.Prodavac;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Milosh
 */
public class LoginOperacija extends ApstraktnaGenerickaOperacija {
    
    private Prodavac prodavac;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Prodavac)){
            throw new Exception("Ne moze da se otvori glavna forma");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<Prodavac> sviProdavci = broker.getAll((Prodavac)param, null);
        System.out.println("KLASA LoginOperacija SO: "+sviProdavci);
        
        for (Prodavac p : sviProdavci) {
            if(p.equals((Prodavac)param)){
                prodavac=p;
                
                return;
            }
        }
        System.out.println("Prodavac ne postoji");
        prodavac=null;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }
    
}
