/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Operacija;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import model.Kupac;
import model.Prodavac;
import model.TerminDezurstva;
import model.TipKupca;

/**
 *
 * @author Milosh
 */
public class ObradaKlijentskihZahteva extends Thread{
    
    Socket socket;
    Primalac primalac;
    Posiljalac posiljalac;
    boolean kraj = false;
    
    
    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket=socket;
        primalac = new Primalac(socket);
        posiljalac = new Posiljalac(socket);
        
    }
    
    
    
    @Override
public void run() {
    while(!kraj){
        try{
            Zahtev zahtev = (Zahtev) primalac.primi();
            
            if(zahtev == null) { 
                break;
            }
            
            Odgovor odgovor = new Odgovor();
            switch (zahtev.getOperacija()) {
                case LOGIN:
                    Prodavac p = (Prodavac) zahtev.getParametar();
                    p = controller.Controller.getInstance().login(p);
                    odgovor.setOdgovor(p);
                    break;
                case UCITAJ_KUPCE:
                    List<Kupac> kupci = controller.Controller.getInstance().ucitajKupce();
                    odgovor.setOdgovor(kupci);
                    break;
                case UCITAJ_TIPOVE:
                    List<TipKupca> tipovi = controller.Controller.getInstance().ucitajTipoveKupca();
                    odgovor.setOdgovor(tipovi);
                    break;
                case OBRISI_KUPCA:
                    try{
                        Kupac k = (Kupac) zahtev.getParametar();
                        controller.Controller.getInstance().obrisiKupca(k);
                        odgovor.setOdgovor(null);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    break;
                case DODAJ_KUPCA:
                    try{
                        Kupac k = (Kupac) zahtev.getParametar();
                        controller.Controller.getInstance().dodajKupca(k);
                        odgovor.setOdgovor(null);}
                    catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    break;
                case AZURIRAJ_KUPCA:
                    try{
                        Kupac promenaKupac = (Kupac) zahtev.getParametar();
                        controller.Controller.getInstance().azurirajKupca(promenaKupac);
                        odgovor.setOdgovor(null);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    break;
                case DODAJ_DEZURSTVO:
                    try {
                        TerminDezurstva td = (TerminDezurstva) zahtev.getParametar();
                        controller.Controller.getInstance().dodajDezurstvo(td);
                        odgovor.setOdgovor(null);
                    }catch(Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                default:
                    System.out.println("Greška. Operacija ne postoji.");
            }
            posiljalac.posalji(odgovor);
            
        } catch(SocketException s){ 
            this.prekiniNit();
            return;
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
    public void prekiniNit(){
        kraj=true;
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        interrupt();
    }
    
    
}
