/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racun;

import java.util.List;
import model.Racun;
import model.Roba;
import model.StavkaRacuna;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Milosh
 */
public class PromeniRacunOperacija extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if(param == null || !(param instanceof Racun)){
                throw new Exception("Sistem ne moze da promeni racun");
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
        Racun promenjenRacun = (Racun) param;
        //ucitam stare stavke
        List<StavkaRacuna>stareStavke = broker.getAll(new StavkaRacuna(),
                "JOIN roba on stavkaracuna.idRoba = roba.idRoba "+
                        "WHERE stavkaracuna.idRacun="+promenjenRacun.getIdRacun());
        
        for (StavkaRacuna novaStavka : promenjenRacun.getStavke()) {
            novaStavka.setRacun(promenjenRacun);
            
            
            
            StavkaRacuna staraStavka = stareStavke.stream()
                    .filter(s->s.getRoba().getIdRoba()==novaStavka.getRoba().getIdRoba())
                    .findFirst().orElse(null);
            
            if(staraStavka!=null){
                broker.edit(novaStavka);
                /*int azuriranoStanjeZaliha = staraStavka.getRoba().getStanjeZaliha()
                        +staraStavka.getKolicina()
                        -novaStavka.getKolicina();*/
                int azuriranoStanjeZaliha =staraStavka.getKolicina() - novaStavka.getKolicina();
                broker.azurirajZalihe(novaStavka.getRoba(), azuriranoStanjeZaliha);
            }else{
                broker.add(novaStavka);
                /*int azuriranoStanjeZaliha = novaStavka.getRoba().getStanjeZaliha()
                    - novaStavka.getKolicina();*/
                broker.azurirajZalihe(novaStavka.getRoba(), -novaStavka.getKolicina());
            }
        }
        
        for(StavkaRacuna stara:stareStavke){
            boolean zadrzana = promenjenRacun.getStavke().stream()
                    .anyMatch(s->s.getRoba().getIdRoba() == stara.getRoba().getIdRoba());
            if(!zadrzana){
                
                //int azuriranoStanjeZaliha = stara.getRoba().getStanjeZaliha()+stara.getKolicina();
                broker.azurirajZalihe(stara.getRoba(), stara.getKolicina());
                stara.setRacun(promenjenRacun);
                broker.delete(stara);
            }
        }
        broker.edit(promenjenRacun);
        
    }
    
}
