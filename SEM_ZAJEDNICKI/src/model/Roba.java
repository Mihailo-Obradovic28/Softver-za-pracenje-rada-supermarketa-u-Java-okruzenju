/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author Milosh
 */
public class Roba implements ApstraktniDomenskiObjekat{
    private int idRoba;
    private String naziv;
    private int stanjeZaliha;
    private BigDecimal cena;

    public Roba() {
    }

    public Roba(int idRoba, String naziv, int stanjeZaliha, BigDecimal cena) {
        this.idRoba = idRoba;
        this.naziv = naziv;
        this.stanjeZaliha = stanjeZaliha;
        this.cena = cena;
    }

    
    
    public int getIdRoba() {
        return idRoba;
    }

    public void setIdRoba(int idRoba) {
        this.idRoba = idRoba;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getStanjeZaliha() {
        return stanjeZaliha;
    }

    public void setStanjeZaliha(int stanjeZaliha) {
        this.stanjeZaliha = stanjeZaliha;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return naziv+":"+stanjeZaliha;
    }

    @Override
    public String vratiNazivTabele() {
        return "Roba";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int robaId = rs.getInt("roba.idRoba");
            String name = rs.getString("roba.naziv");
            int stanje = rs.getInt("roba.stanjeZaliha");
            BigDecimal price= rs.getBigDecimal("roba.cena");
            Roba r = new Roba(robaId,name, stanje, price);
            
            lista.add(r);
        }
        return lista;
    
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,stanjeZaliha,cena";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+naziv+"',"+stanjeZaliha+","+cena;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "roba.idRoba="+idRoba;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "naziv='"+naziv+"', stanjeZaliha="+stanjeZaliha+", cena="+cena;
    }
    
    
}
