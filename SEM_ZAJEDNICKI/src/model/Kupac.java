/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Milosh
 */
public class Kupac implements ApstraktniDomenskiObjekat{
    private int idKupac;
    private String ime;
    private String prezime;
    private String email;
    private int godine;
    private TipKupca tip;

    public Kupac() {
    }

    public Kupac(int idKupac, String ime, String prezime, String email, int godine, TipKupca tip) {
        this.idKupac = idKupac;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.godine = godine;
        this.tip = tip;
    }

    public int getIdKupac() {
        return idKupac;
    }

    public void setIdKupac(int idKupac) {
        this.idKupac = idKupac;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGodine() {
        return godine;
    }

    public void setGodine(int godine) {
        this.godine = godine;
    }

    public TipKupca getTip() {
        return tip;
    }

    public void setTip(TipKupca tip) {
        this.tip = tip;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kupac other = (Kupac) obj;
        return Objects.equals(this.email, other.email);
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
    }
    
    @Override
    public String vratiNazivTabele() {
        return "Kupac";
    }
    ////TO DO ovo sigurno nije dobro opis u tip nije podesen
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat>lista = new ArrayList<>();
        while(rs.next()){
            int kupacId = rs.getInt("kupac.IdKupac");
            String name = rs.getString("kupac.ime");
            String lastName = rs.getString("kupac.prezime");
            String mail =rs.getString("kupac.email");
            int god = rs.getInt("kupac.godine");
            
            int idTip = rs.getInt("tipKupca.idTipKupca");
            String opis = rs.getString("tipkupca.opis");
            TipKupca tk = new TipKupca(idTip, opis);
            
            
            
            Kupac k = new Kupac(kupacId, name,lastName, mail, god, tk);
            lista.add(k);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,email,godine,idTipKupca";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+ime+"','"+prezime+"','"+email+"',"+godine+","+tip.getIdTipKupca();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "kupac.idKupac="+idKupac;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "ime='"+ime+"', prezime='"+prezime+"', email='"+email+"', godine="+godine+", idTipKupca="+tip.getIdTipKupca();
    }

    
    
    
}
