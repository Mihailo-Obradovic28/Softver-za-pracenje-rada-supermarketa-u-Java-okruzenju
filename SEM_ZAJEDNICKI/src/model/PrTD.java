/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Milosh
 */
public class PrTD implements ApstraktniDomenskiObjekat{
    private LocalDate datumDezurstva;
    private Prodavac prodavac;
    private TerminDezurstva termin;

    public PrTD() {
    }

    public PrTD(LocalDate datumDezurstva, Prodavac prodavac, TerminDezurstva termin) {
        this.datumDezurstva = datumDezurstva;
        this.prodavac = prodavac;
        this.termin = termin;
    }

    public LocalDate getDatumDezurstva() {
        return datumDezurstva;
    }

    public void setDatumDezurstva(LocalDate datumDezurstva) {
        this.datumDezurstva = datumDezurstva;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public TerminDezurstva getTermin() {
        return termin;
    }

    public void setTermin(TerminDezurstva termin) {
        this.termin = termin;
    }

    @Override
    public String toString() {
        return prodavac+"radi";
    }

    @Override
    public String vratiNazivTabele() {
        return "PrTD";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idProdavac,idTerminDezurstva,datumDezurstva";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return prodavac.getIdProdavac()+","+termin.getIdTerminDezurstva()+",'"+datumDezurstva+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "prtd.idProdavac="+prodavac.getIdProdavac()+" AND prtd.idTerminDezurstva="+termin.getIdTerminDezurstva()
                +" AND prtd.datumDezurstva= '"+datumDezurstva+"'";
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        //nemam sta da menjam
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
