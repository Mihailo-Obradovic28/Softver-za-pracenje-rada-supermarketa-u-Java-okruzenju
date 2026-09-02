/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.roba;

import java.util.ArrayList;
import java.util.List;
import model.Roba;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Milosh
 */
public class UcitajRobuOperacija extends ApstraktnaGenerickaOperacija {
    
    List<Roba> listaRobe = new ArrayList<>();
    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        listaRobe = broker.getAll(new Roba(),null);
    }

    public List<Roba> getListaRobe() {
        return listaRobe;
    }
    
}
