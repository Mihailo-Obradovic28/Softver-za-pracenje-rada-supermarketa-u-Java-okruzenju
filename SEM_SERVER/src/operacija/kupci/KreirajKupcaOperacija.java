/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kupci;

import model.Kupac;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Milosh
 */
public class KreirajKupcaOperacija extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Kupac)){
            throw new Exception("Sistem ne moze da kreira kupca");
        }
        Kupac k = (Kupac)param;
        if(k.getIme()==null || k.getIme().isEmpty() || k.getIme().length()<3){
            throw new Exception("Greska ime");
        }
        if(k.getPrezime()==null || k.getPrezime().isEmpty() || k.getPrezime().length()<3){
            throw new Exception("Greska prezime");
        }
        if(k.getEmail()==null || k.getEmail().isEmpty() || !k.getEmail().contains("@")
                //|| !(k.getEmail().matches(".*@.*\\..*"))
                ){
            throw new Exception("Greska email");
        }
        if(k.getGodine()<=0){
          throw new Exception("Greska godine");  
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((Kupac)param);
        
        
    }
    
}
