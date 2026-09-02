/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racun;

import java.util.ArrayList;
import java.util.List;
import model.Racun;
import model.StavkaRacuna;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Milosh
 */
public class UcitajRacunOperacija extends ApstraktnaGenerickaOperacija {
    private List<StavkaRacuna> listaStavki = new ArrayList<>();

    public List<StavkaRacuna> getListaStavki() {
        return listaStavki;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Racun r = (Racun) param;
      
        listaStavki = broker.getAll(new StavkaRacuna(),
                "JOIN roba on stavkaracuna.idRoba = roba.idRoba "+
                        "WHERE stavkaracuna.idRacun="+r.getIdRacun());
    }
    
}
