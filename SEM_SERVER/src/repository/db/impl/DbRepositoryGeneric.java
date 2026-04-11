/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;

import java.util.ArrayList;
import java.util.List;
import model.ApstraktniDomenskiObjekat;
import repository.db.DbRepository;
import java.sql.Statement;
import java.sql.ResultSet;
import repository.db.DbConnectionFactory;
/**
 *
 * @author Milosh
 */
public class DbRepositoryGeneric implements DbRepository<ApstraktniDomenskiObjekat> {

    @Override
    public List<ApstraktniDomenskiObjekat> getAll(ApstraktniDomenskiObjekat param,String uslov) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        String upit = "SELECT * FROM "+param.vratiNazivTabele();
        if(uslov!=null){
            upit=upit+" "+uslov;
        }
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        lista=param.vratiListu(rs);
        
        rs.close();
        st.close();
        return lista;
    }

    @Override
    public void add(ApstraktniDomenskiObjekat param) throws Exception {
        //insert into racun(kolone) values(vrednosti)
        String upit = "INSERT INTO "+param.vratiNazivTabele()+" ("+param.vratiKoloneZaUbacivanje()+") VALUES"
                + " ("+param.vratiVrednostiZaUbacivanje()+")";
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public void edit(ApstraktniDomenskiObjekat param) throws Exception {
        //update racun set vrednostiZaIzmenu
        String upit = "UPDATE "+param.vratiNazivTabele()+" SET "+param.vratiVrednostZaIzmenu()
                +" WHERE "+param.vratiPrimarniKljuc();
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public void delete(ApstraktniDomenskiObjekat param) throws Exception {
        //delete from racun
        String upit = "DELETE FROM "+param.vratiNazivTabele()+" WHERE "+param.vratiPrimarniKljuc();
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public List<ApstraktniDomenskiObjekat> getAll() {
        //TO DO
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int addAndReturnId(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "INSERT INTO "+param.vratiNazivTabele()+" ("+param.vratiKoloneZaUbacivanje()+") VALUES"
                +" ("+param.vratiVrednostiZaUbacivanje()+")";
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit,Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = st.getGeneratedKeys();
        int id=-1;
        if(rs.next()){
            id = rs.getInt(1);
        }
        rs.close();
        st.close();
        return id;
    }




    
}
