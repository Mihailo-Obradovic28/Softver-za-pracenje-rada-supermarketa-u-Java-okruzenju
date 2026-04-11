/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kordinator;

import controllers.DodajKupcaController;
import controllers.DodajTerminDezurstvaController;
import controllers.GlavnaFormaController;
import controllers.LoginController;
import controllers.PrikazKupacaController;
import controllers.PrikazTipKupcaController;
import forme.DodajKupcaForma;
import forme.DodajTerminDezurstvaForma;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazKupacaForma;
import forme.PrikazTipKupcaForma;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import model.Prodavac;
import forme.FormaMod;

/**
 *
 * @author Milosh
 */
public class Kordinator {
    private static Kordinator instance;
    
    private Prodavac ulogovan;
    //instanciran svaki kontroler
    private LoginController loginController;
    private GlavnaFormaController glavnaFormaController;
    private PrikazKupacaController prikazKupacaController;
    private DodajKupcaController dodajKupcaController;
    private DodajTerminDezurstvaController dodajTerminDezurstvaController;
    
    private PrikazTipKupcaController prikaziTipKupcaController;
    
    private Map<String,Object> parametri;
    
    
    private Kordinator() {
        parametri = new HashMap<>();
    }

    public static Kordinator getInstance() {
        if(instance==null){
            instance=new Kordinator();
        }
        return instance;
    }
    
    public Prodavac getUlogovan() {
        return ulogovan;
    }

    public void setUlogovan(Prodavac ulogovan) {
        this.ulogovan = ulogovan;
    }
    
    public void otvoriLoginFormu() {
        loginController = new LoginController(new LoginForma());
        loginController.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
            Prodavac ulogovani = getInstance().getUlogovan();
            glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
            glavnaFormaController.otvoriFormu(ulogovani);
    }
    
    public void otvoriPrikazKupacaFormu() throws SocketException {
        prikazKupacaController = new PrikazKupacaController(new PrikazKupacaForma());
        prikazKupacaController.otvoriFormu();
    }

    public void otvoriPrikazTipKupcaFormu() throws SocketException {
        prikaziTipKupcaController = new PrikazTipKupcaController(new PrikazTipKupcaForma());
        prikaziTipKupcaController.otvoriFormu();
    }

    public void otvoriDodajKupcaFormu() throws SocketException {
        dodajKupcaController = new DodajKupcaController(new DodajKupcaForma());
        dodajKupcaController.otvoriFormu(FormaMod.DODAJ);
    }

    
    public void dodajParam(String s, Object o){
        parametri.put(s, o);
    }
    public Object vratiParam(String s){
        return parametri.get(s);
    }

    public void otvoriIzmeniKupcaFormu() throws SocketException {
        dodajKupcaController = new DodajKupcaController(new DodajKupcaForma());
        dodajKupcaController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziPregledKupcaForma() throws SocketException {
        prikazKupacaController.osveziFormu();
    }

    public void otvoriDodajTerminDezurstvaFormu() {
        dodajTerminDezurstvaController = new DodajTerminDezurstvaController(new DodajTerminDezurstvaForma());
        dodajTerminDezurstvaController.otvoriFormu();
    }
      
  
    
}
