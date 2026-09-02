/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Kupac;
import model.Prodavac;
import model.Racun;
import model.Roba;
import model.StavkaRacuna;
import model.TerminDezurstva;
import model.TipKupca;

/**
 *
 * @author Milosh
 */
public class Komunikacija {
    private Socket socket;
    private static Komunikacija instance;
    private Posiljalac posiljalac;
    private Primalac primalac;
    
    private Komunikacija() {
    }

    public static Komunikacija getInstance() {
        if(instance==null){
            instance=new Komunikacija();
        }
        return instance;
    }
    public void konekcija() {
        if (socket != null && !socket.isClosed() && socket.isConnected()) {
             return; // IZAĐI ODMAH, već smo povezani!
        }
        try {
            socket=new Socket("localhost",9000);
            posiljalac = new Posiljalac(socket);
            primalac = new Primalac(socket);
        } catch (IOException ex) {
            System.out.println("SERVER NIJE POVEZAN");
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //ovo mi je ai predlozio zbg problema
    public void reconnektuj() {
    try {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        socket = new Socket("localhost", 9000);
        posiljalac = new Posiljalac(socket);
        primalac = new Primalac(socket);
    } catch (IOException ex) {
        System.out.println("SERVER NIJE POVEZAN");
        Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    public Prodavac login(String username, String password) throws SocketException {
        Prodavac p = new Prodavac();
        p.setKorisnickoIme(username);
        p.setSifra(password);
        Zahtev zahtev = new Zahtev(Operacija.LOGIN, p);
        
        posiljalac.posalji(zahtev);
        
        Object primljeniObjekat = primalac.primi(); // Prvo primi kao opšti Object
    
    if (primljeniObjekat == null) {
        System.out.println("Greska: Server nije vratio nista!");
        return null; 
    }
    
    
        
        //Odgovor odg = (Odgovor) primalac.primi();
        Odgovor odg = (Odgovor) primljeniObjekat;
        p = (Prodavac) odg.getOdgovor();
        return p;
    }

    public List<Kupac> ucitajKupce() throws SocketException {
        //reconnektuj();
        List<Kupac> listaKupaca = new ArrayList<>();
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_KUPCE, null);
        posiljalac.posalji(zahtev);
        
        Object primljeniObjekat = primalac.primi();
        if(primljeniObjekat==null){
            System.out.println("Greska: Server nije vratio nista!");
            return null; 
        }
        Odgovor odg = (Odgovor) primljeniObjekat;
        listaKupaca = (List<Kupac>) odg.getOdgovor();
        
        return listaKupaca;
    }

    public List<TipKupca> ucitajTipKupaca() throws SocketException {
        //reconnektuj();
        List<TipKupca> listaTipova=new ArrayList<>();
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_TIPOVE, null);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor)primalac.primi();
        
        listaTipova=(List<TipKupca>) odg.getOdgovor();
        return listaTipova; 
    }

    public void obrisiKupca(Kupac k) throws  Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_KUPCA, k);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }else {
            throw new Exception(((Exception)odg.getOdgovor()).getMessage()); 
        }
    }

    public void dodajKupca(Kupac k) throws  Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_KUPCA, k);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }else {
            throw new Exception(((Exception)odg.getOdgovor()).getMessage()); 
        }
    }

    public void azurirajKupca(Kupac k) throws  Exception {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_KUPCA, k);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
            kordinator.Kordinator.getInstance().osveziPregledKupcaForma();
        }else{
              throw new Exception(((Exception)odg.getOdgovor()).getMessage()); 
        }
    }

    public void dodajTerminDezurstva(TerminDezurstva td) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_DEZURSTVO, td);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }else{
            System.out.println("komunikacija :GRESKA");
            throw (Exception) odg.getOdgovor();
        }
    }

    public List<Roba> ucitajRobu() throws SocketException {
        List<Roba>listaRobe = new ArrayList<>();
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_ROBU,null);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        listaRobe = (List<Roba>) odg.getOdgovor();
        return listaRobe;
        
    }

    public void ubaciRacun(Racun racun) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.KREIRAJ_RACUN, racun);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if(odg.getOdgovor() == null){
            System.out.println("USPEH");
        }else {
            throw new Exception(((Exception)odg.getOdgovor()).getMessage()); 
        }
    }

    public List<Prodavac> ucitajProdavce() throws Exception {
        List<Prodavac>listaProdavaca = new ArrayList<>();
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_PRODAVCE, null);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        listaProdavaca = (List<Prodavac>) odg.getOdgovor();
        return listaProdavaca;
    }

    public List<Racun> ucitajRacune() throws SocketException {
        List<Racun> listaRacuna = new ArrayList<>();
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_RACUNE, null);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        listaRacuna = (List<Racun>) odg.getOdgovor();
        return listaRacuna;
    }

    public List<Racun> pretraziRacune(String uslov) throws SocketException {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_RACUNE, uslov);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        return (List<Racun>) odg.getOdgovor();
    }

    public List<StavkaRacuna> ucitajStavkeRacuna(Racun r) throws SocketException {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_STAVKE_RACUNA, r);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        return (List<StavkaRacuna>) odg.getOdgovor();
    }

    public List<Kupac> pretraziKupce(String uslov) throws SocketException {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_KUPCE, uslov);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        return (List<Kupac>) odg.getOdgovor();
    }

    public void promeniRacun(Racun promenjen) throws  Exception {
        Zahtev zahtev = new Zahtev(Operacija.PROMENI_RACUN, promenjen);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if(odg.getOdgovor()!=null){
            throw (Exception) odg.getOdgovor();
        }
    }
    
}
