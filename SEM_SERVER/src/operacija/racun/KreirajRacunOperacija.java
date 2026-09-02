/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racun;

import model.Racun;
import model.Roba;
import model.StavkaRacuna;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Milosh
 */
public class KreirajRacunOperacija extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
            if(param == null || !(param instanceof Racun)){
                throw new Exception("Sistem ne moze da kreira racun");
            }
            Racun r = (Racun) param;
            if(r.getKupac() == null){
                throw new Exception("Kupac nije izabran");
            }
            if(r.getProdavac() == null){
                throw new Exception("Prodavac nije izabran");
            }
            if(r.getStavke() == null || r.getStavke().isEmpty()){
                throw new Exception("Racun mora imati bar jednu stavku");
            }
            for(StavkaRacuna stavka : r.getStavke()){
                if(stavka.getRoba() == null){
                    throw new Exception("Svaka stavka mora imati izabranu robu");
                }
                if(stavka.getKolicina() <= 0){
                    throw new Exception("Svaka stavka mora imati kolicinu vecu od 0");
                }
                if(stavka.getRoba().getStanjeZaliha() < stavka.getKolicina()){
                    throw new Exception("Nema dovoljno zaliha za robu: " + stavka.getRoba().getNaziv() 
                        + ". Na stanju: " + stavka.getRoba().getStanjeZaliha() 
                        + ", traženo: " + stavka.getKolicina());
                }
            }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Racun r = (Racun) param;
        int idR = broker.addAndReturnId(r);
        r.setIdRacun(idR);
        for (StavkaRacuna stavka : r.getStavke()) {
            stavka.setRacun(r);
            broker.add(stavka);
            
            broker.azurirajZalihe(stavka.getRoba(), -stavka.getKolicina());
        }
    }
    
}
