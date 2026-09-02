/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
/**
 *
 * @author Milosh
 */
public class Racun implements ApstraktniDomenskiObjekat{
    private int idRacun;
    private LocalDateTime datumIzdavanja;
    private BigDecimal ukupanIznos;
    private Kupac kupac;
    private Prodavac prodavac;
    
    private List<StavkaRacuna>stavke = new ArrayList<>();

    public Racun() {
    }

    public Racun(int idRacun, LocalDateTime datumIzdavanja, BigDecimal ukupanIznos, Kupac kupac, Prodavac prodavac) {
        this.idRacun = idRacun;
        this.datumIzdavanja = datumIzdavanja;
        this.ukupanIznos = ukupanIznos;
        this.kupac = kupac;
        this.prodavac = prodavac;
    }

    public int getIdRacun() {
        return idRacun;
    }

    public void setIdRacun(int idRacun) {
        this.idRacun = idRacun;
    }

    public LocalDateTime getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(LocalDateTime datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public BigDecimal getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(BigDecimal ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public List<StavkaRacuna> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaRacuna> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String vratiNazivTabele() {
        return "Racun";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int idR = rs.getInt("racun.idRacun");
            LocalDateTime datum = rs.getTimestamp("racun.datumIzdavanja").toLocalDateTime();
            BigDecimal iznos = rs.getBigDecimal("racun.ukupanIznos");
            
            int idKupac = rs.getInt("kupac.idKupac");
            String imeKupac = rs.getString("kupac.ime");
            String prezimeKupac = rs.getString("kupac.prezime");
            Kupac k = new Kupac(idKupac, imeKupac, prezimeKupac, null, 0, null);
            
            int idProdavac = rs.getInt("prodavac.idProdavac");
            String imeProdavac = rs.getString("prodavac.ime");
            String prezimeProdavac = rs.getString("prodavac.prezime");
            Prodavac p = new Prodavac(idProdavac, imeProdavac, prezimeProdavac, null, null);
            
            Racun r = new Racun(idR, datum, iznos, k, p);
            lista.add(r);
            
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datumIzdavanja,ukupanIznos,idKupac,idProdavac";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+datumIzdavanja.toString().replace("T", " ")+"',"+ukupanIznos+","+kupac.getIdKupac()+","+prodavac.getIdProdavac();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "racun.idRacun="+idRacun;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "datumIzdavanja='"+datumIzdavanja+"', ukupanIznos="+ukupanIznos+", idKupac="+kupac.getIdKupac()
                +", idProdavac="+prodavac.getIdProdavac();
    }
    
    
}
