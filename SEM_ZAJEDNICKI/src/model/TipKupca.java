/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Milosh
 */
public class TipKupca implements ApstraktniDomenskiObjekat{
    private int idTipKupca;
    private String opis;

    public TipKupca() {
    }

    public TipKupca(int idTipKupca, String opis) {
        this.idTipKupca = idTipKupca;
        this.opis = opis;
    }

    public int getIdTipKupca() {
        return idTipKupca;
    }

    public void setIdTipKupca(int idTipKupca) {
        this.idTipKupca = idTipKupca;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return opis;//TO DO ,od kad sam dodao ne vraca mi nista u kupac pregled formi
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final TipKupca other = (TipKupca) obj;
        return this.idTipKupca == other.idTipKupca;
    }
    
    
    
    @Override
    public String vratiNazivTabele() {
        return "TipKupca";
    }
    
    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int tipKupcaId = rs.getInt("tipkupca.idTipKupca");
            String tip = rs.getString("tipkupca.opis");
            TipKupca tk = new TipKupca(tipKupcaId, tip);
            lista.add(tk);
        }
        if(lista.isEmpty())
            System.out.println("PRAZNA LISTA U VRATI LISTU TIPKUPCA");
        else
            System.out.println("LISTA: u TIPKUPCA: "+lista);
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "opis";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+opis+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "tipkupca.idTipKupca="+idTipKupca;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "opis='"+opis+"'";
    }
    
    
}
