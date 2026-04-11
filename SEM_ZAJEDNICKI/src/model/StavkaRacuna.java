/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.List;
import java.math.BigDecimal;
/**
 *
 * @author Milosh
 */
public class StavkaRacuna implements ApstraktniDomenskiObjekat{
    private Racun racun;//ona nema ovo
    private int rb;
    private BigDecimal iznos;
    private int kolicina;
    private BigDecimal jedinicnaCena;
    
    private Roba roba;

    public StavkaRacuna() {
    }

    public StavkaRacuna(Racun racun, int rb, BigDecimal iznos, int kolicina, BigDecimal jedinicnaCena, Roba roba) {
        this.racun = racun;
        this.rb = rb;
        this.iznos = iznos;
        this.kolicina = kolicina;
        this.jedinicnaCena = jedinicnaCena;
        this.roba = roba;
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public BigDecimal getIznos() {
        return iznos;
    }

    public void setIznos(BigDecimal iznos) {
        this.iznos = iznos;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public BigDecimal getJedinicnaCena() {
        return jedinicnaCena;
    }

    public void setJedinicnaCena(BigDecimal jedinicnaCena) {
        this.jedinicnaCena = jedinicnaCena;
    }

    public Roba getRoba() {
        return roba;
    }

    public void setRoba(Roba roba) {
        this.roba = roba;
    }

    @Override
    public String vratiNazivTabele() {
        return "StavkaRacuna";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idRacun,rb,iznos,kolicina,jedinicnaCena,idRoba";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return racun.getIdRacun()+","+rb+","+iznos+","+kolicina+","+jedinicnaCena+","+roba.getIdRoba();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "stavkaracuna.idRacun="+racun.getIdRacun()+" AND stavkaracuna.rb="+rb;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "iznos="+iznos+", kolicina="+kolicina+", jedinicnaCena="+jedinicnaCena+", idRoba="+roba.getIdRoba();
    }
    
    
}
