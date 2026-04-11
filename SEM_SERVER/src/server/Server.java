/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author Milosh
 */
public class Server extends Thread{
    boolean kraj=false;
    ServerSocket serverSocket;
    //ako zelim da moze odjednom se nakaci vise klijenata treba dodam ovde listu

    @Override
    public void run() {
        pokreniServer();
    }
    
    
    
    public void pokreniServer() {
        try{
            serverSocket = new ServerSocket(9000);
            while(!kraj){
                Socket s = serverSocket.accept();
                System.out.println("POVEZAN KLIJENT");

                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
                //ovde punim listu
                okz.start();
            }
        }catch (IOException ex) {
            // Ako smo kliknuli ZAUSTAVI, kraj je true i ovaj Exception je NORMALAN
            if (kraj) {
                System.out.println("Serverski soket je uspešno zatvoren (Stop dugme).");
            } else {
                // Ako je kraj false, a desio se Exception, onda je stvarna greška
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, "Neočekivana greška na soketu!", ex);
        }
        
        }
    }
    public void zaustaviServer(){
        kraj=true;
        try {
            serverSocket.close();
            //for petljom da prodjem kroz listu da prekinem za svakog klijenta
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
