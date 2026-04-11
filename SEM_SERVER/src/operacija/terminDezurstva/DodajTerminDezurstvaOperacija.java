/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.terminDezurstva;

import model.TerminDezurstva;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Milosh
 */
public class DodajTerminDezurstvaOperacija extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof TerminDezurstva)){
        throw new Exception("Sistem ne moze da doda termin dezurstva");
        }
        TerminDezurstva td = (TerminDezurstva) param;
        if(td.getVremePocetka() == null){
            throw new Exception("Greska vreme pocetka");
        }
        if(td.getVremeZavrsetka() == null){
            throw new Exception("Greska vreme zavrsetka");
        }
        if(td.getVremePocetka().isAfter(td.getVremeZavrsetka())){
            throw new Exception("Vreme pocetka ne moze biti posle vremena zavrsetka");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((TerminDezurstva)param);
    }
    
}
