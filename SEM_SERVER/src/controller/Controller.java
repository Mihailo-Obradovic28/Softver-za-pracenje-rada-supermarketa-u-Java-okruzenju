/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import operacija.kupci.UcitajTipoveKupacaSO;
import operacija.kupci.UcitajKupceOperacija;
import java.util.ArrayList;
import java.util.List;
import model.Kupac;
import model.Prodavac;
import model.Racun;
import model.Roba;
import model.StavkaRacuna;
import model.TerminDezurstva;
import model.TipKupca;
import operacija.login.LoginOperacija;
import operacija.*;
import operacija.kupci.AzurirajKupcaOperacija;
import operacija.kupci.KreirajKupcaOperacija;
import operacija.kupci.ObrisiKupcaOperacija;
import operacija.kupci.PretraziKupceOperacija;
import operacija.prodavci.UcitajProdavceOperacija;
import operacija.racun.KreirajRacunOperacija;
import operacija.racun.PretraziRacunOperacija;
import operacija.racun.PromeniRacunOperacija;
import operacija.racun.UcitajRacuneOperacija;
import operacija.racun.UcitajRacunOperacija;
import operacija.roba.UcitajRobuOperacija;
import operacija.terminDezurstva.DodajTerminDezurstvaOperacija;

/**
 *
 * @author Milosh
 */
public class Controller {
    private static Controller instance;

    private Controller() {
    }

    public static Controller getInstance() {
        if(instance==null){
            instance=new Controller();
        }
        return instance;
    }

    public Prodavac login(Prodavac p) throws Exception {
        LoginOperacija operacija = new LoginOperacija();
        operacija.izvrsi(p, null);
        System.out.println("KLASA KONTROLER: "+operacija.getProdavac());
        return operacija.getProdavac();
    }

    public List<Kupac> ucitajKupce() throws Exception {
        List<Kupac> listaKupaca = new ArrayList<>();
        UcitajKupceOperacija ukOperacija = new UcitajKupceOperacija();
        ukOperacija.izvrsi(null, null);
        System.out.println("KLASA KONTROLER: "+ukOperacija.getListaKupaca());
        listaKupaca=ukOperacija.getListaKupaca();
        return listaKupaca;
    }

    public List<TipKupca> ucitajTipoveKupca() throws Exception {
        UcitajTipoveKupacaSO utkOperacija = new UcitajTipoveKupacaSO();
        utkOperacija.izvrsi(null, null);
        System.out.println("KONTROLER: "+utkOperacija.getListaTipova());
        return utkOperacija.getListaTipova();
    }

    public void obrisiKupca(Kupac k) throws Exception {
        ObrisiKupcaOperacija oko = new ObrisiKupcaOperacija();
        oko.izvrsi(k, null);
    }

    public void dodajKupca(Kupac k) throws Exception {
        KreirajKupcaOperacija kko = new KreirajKupcaOperacija();
        kko.izvrsi(k, null);
    }

    public void azurirajKupca(Kupac k) throws Exception {
        AzurirajKupcaOperacija ako = new AzurirajKupcaOperacija();
        ako.izvrsi(k,null);
    }

    public void dodajDezurstvo(TerminDezurstva td) throws Exception {
        DodajTerminDezurstvaOperacija dtdo = new DodajTerminDezurstvaOperacija();
        dtdo.izvrsi(td, null);
    }

    public List<Roba> ucitajRobu() throws Exception {
        UcitajRobuOperacija uroOperacija = new UcitajRobuOperacija();
        uroOperacija.izvrsi(null, null);
        
        System.out.println("KONTROLER: "+uroOperacija.getListaRobe());
        return uroOperacija.getListaRobe();
    }

    public void ubaciRacun(Racun racun) throws Exception {
        KreirajRacunOperacija kro = new KreirajRacunOperacija();
        kro.izvrsi(racun, null);
    }

    public List<Prodavac> ucitajProdavce() throws Exception {
        UcitajProdavceOperacija upo = new UcitajProdavceOperacija();
        upo.izvrsi(null, null);
        return upo.getListaProdavaca();
    }

    public List<Racun> ucitajRacune() throws Exception {
        UcitajRacuneOperacija uro = new UcitajRacuneOperacija();
        uro.izvrsi(null, null);
        return uro.getListaRacuna();
    }

    public List<Racun> pretraziRacune(String uslov) throws Exception {
        PretraziRacunOperacija pro = new PretraziRacunOperacija();
         pro.izvrsi(null, uslov);
        return pro.getListaRacuna();
    }

    public List<StavkaRacuna> ucitajStavkeRacuna(Racun r) throws Exception {
        UcitajRacunOperacija uso = new UcitajRacunOperacija();
        uso.izvrsi(r, null);
        return uso.getListaStavki();
    }

    public List<Kupac> pretraziKupce(String uslov) throws Exception {
        PretraziKupceOperacija pko = new PretraziKupceOperacija();
        pko.izvrsi(null, uslov);
        return pko.getListaKupaca();
    }

    public void promeniRacun(Racun promenjen) throws Exception {
        PromeniRacunOperacija pro = new PromeniRacunOperacija();
        pro.izvrsi(promenjen, null);
        
    }
    
    
}
