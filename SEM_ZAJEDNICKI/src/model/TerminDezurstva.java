/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Milosh
 */
public class TerminDezurstva implements ApstraktniDomenskiObjekat{
    private int idTerminDezurstva;
    private LocalTime vremePocetka;
    private LocalTime vremeZavrsetka;

    public TerminDezurstva() {
    }

    public TerminDezurstva(int idTerminDezurstva, LocalTime vremePocetka, LocalTime vremeZavrsetka) {
        this.idTerminDezurstva = idTerminDezurstva;
        this.vremePocetka = vremePocetka;
        this.vremeZavrsetka = vremeZavrsetka;
    }

    public int getIdTerminDezurstva() {
        return idTerminDezurstva;
    }

    public void setIdTerminDezurstva(int idTerminDezurstva) {
        this.idTerminDezurstva = idTerminDezurstva;
    }

    public LocalTime getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(LocalTime vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public LocalTime getVremeZavrsetka() {
        return vremeZavrsetka;
    }

    public void setVremeZavrsetka(LocalTime vremeZavrsetka) {
        this.vremeZavrsetka = vremeZavrsetka;
    }

    @Override
    public String vratiNazivTabele() {
        return "TerminDezurstva";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        //moze da baci null exception za datume
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int idTermin = rs.getInt("termindezurstva.idTerminDezurstva");
            LocalTime vremeOd = rs.getTime("termindezurstva.vremePocetka").toLocalTime();
            LocalTime vremeDo = rs.getTime("termindezurstva.vremeZavrsetka").toLocalTime();
            TerminDezurstva td =new TerminDezurstva(idTermin, vremeOd, vremeDo);
            lista.add(td);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "vremePocetka,vremeZavrsetka";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+vremePocetka+"','"+vremeZavrsetka+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "termindezurstva.idTerminDezurstva="+idTerminDezurstva;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "vremePocetka='"+vremePocetka+"', vremeZavrsetka='"+vremeZavrsetka+"'";
    }
    
}
