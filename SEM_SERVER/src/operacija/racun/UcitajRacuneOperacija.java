/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racun;

import java.util.ArrayList;
import java.util.List;
import model.Racun;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Milosh
 */
public class UcitajRacuneOperacija extends ApstraktnaGenerickaOperacija{
    List<Racun> listaRacuna = new ArrayList<>();

    public List<Racun> getListaRacuna() {
        return listaRacuna;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        listaRacuna = broker.getAll(new Racun(),
                "JOIN kupac on racun.idKupac=kupac.idKupac "+
                "JOIN prodavac on racun.idProdavac=prodavac.idProdavac");
    }
    
}
