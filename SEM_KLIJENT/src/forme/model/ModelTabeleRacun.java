/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;
import model.Kupac;
import model.Prodavac;
import model.Racun;

/**
 *
 * @author Milosh
 */
public class ModelTabeleRacun extends AbstractTableModel {
    List<Racun> listaRacuna = new ArrayList<>();
    String kolone[] ={"idRacun", "datumIzdavanja", "ukupanIznos", "kupac", "prodavac"};
    
    public ModelTabeleRacun(List<Racun> listaRacuna){
        if(listaRacuna != null) {
            this.listaRacuna = listaRacuna;
        }
    }
    
    @Override
    public int getRowCount() {
        return listaRacuna.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Racun racun = listaRacuna.get(rowIndex);
        switch(columnIndex){
            case 0:
                return racun.getIdRacun();
            case 1:
                LocalDateTime datum = racun.getDatumIzdavanja();
                if(datum==null)return "";
                return datum.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));//mogu dodam mzd i sate min ako treba
            case 2:
                return racun.getUkupanIznos();
            case 3:
                return racun.getKupac().getIme()+" "+racun.getKupac().getPrezime();
            case 4:
                return racun.getProdavac().getIme()+" "+racun.getProdavac().getPrezime();
            default:
                return "Greska u switch ModelTabeleRacun";
        }
    }

    public List<Racun> getListaRacuna() {
        return listaRacuna;
    }

    /*public void pretrazi(Kupac k, Prodavac p) {
        List<Racun> filteredList = listaRacuna.stream()
                .filter(r-> (k==null || r.getKupac().getIdKupac() == k.getIdKupac()))
                .filter(r-> (p==null || r.getProdavac().getIdProdavac()==p.getIdProdavac()))
                .collect(Collectors.toList());
        this.listaRacuna = filteredList;
        fireTableDataChanged();
    }*/
    
    
}
