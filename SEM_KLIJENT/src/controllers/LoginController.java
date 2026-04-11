/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;



import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Prodavac;
import kordinator.Kordinator;

/**
 *
 * @author Milosh
 */
public class LoginController {
    private final LoginForma lf;

    public LoginController(LoginForma lf) {
        this.lf = lf;
        Komunikacija.getInstance().konekcija();
        addActionListeners();
    }
    
    
    
    private void addActionListeners() {
        lf.loginAddActionListener(new ActionListener() {
            @Override
            //ovde pisem kod koji zelim se izvrsi kad korisnik klikne neko dugme
            public void actionPerformed(ActionEvent e) {
                try {
                    prijava(e);
                } catch (SocketException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void prijava(ActionEvent e) throws SocketException {
                String username = lf.getjTextFieldUserName().getText().trim();
                String password = String.valueOf(lf.getjPasswordField1().getPassword()).trim();
                
                //Komunikacija.getInstance().konekcija();
                Prodavac ulogovan =Komunikacija.getInstance().login(username,password);
                if(ulogovan==null){
                    
                    JOptionPane.showMessageDialog(lf, "Korisnicko ime i sifra nisu ispravni","Greska",JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(lf, "Korisnicko ime i sifra su ispravni","Uspeh",JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Ulogovao si se");
                    Kordinator.getInstance().setUlogovan(ulogovan);
                    Kordinator.getInstance().otvoriGlavnuFormu();
                    
                    lf.dispose();
                }
            }
        });
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }
    
    
    
}
