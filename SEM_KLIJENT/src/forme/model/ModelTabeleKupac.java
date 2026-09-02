/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import model.Kupac;
import model.TipKupca;

/**
 *
 * @author Milosh
 */
public class ModelTabeleKupac extends AbstractTableModel{
    //da ne radim preko filtriranja liste vec da pravim isto sa upitom ko u racunu
    List<Kupac> lista = new ArrayList<>();
    String [] kolone = {"idKupac","ime","prezime","email","godine","TipKupca"};
    
    private  List<Kupac> original = new ArrayList<>();
    
    public ModelTabeleKupac(List<Kupac> lista) {
        if (lista != null) {
            this.lista = lista;
            this.original = new ArrayList<>(this.lista);
        }
        
    }
    
    
    
    @Override
    public int getRowCount() {
        return lista.size();
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
        Kupac kupac = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return kupac.getIdKupac();
            case 1:
                return kupac.getIme();
            case 2:
                return kupac.getPrezime();
            case 3:
                return kupac.getEmail();
            case 4:
                return kupac.getGodine();
            case 5:
                return kupac.getTip().getOpis();//TO DO pogledaj
            default:
                return "Greska u switch ModelTabeleKupac";
        }
    }

    public List<Kupac> getLista() {
        return lista;
    }

    public void pretrazi(String name, String lastname, String email, TipKupca tip) {
        List<Kupac> filteredList = original.stream()
                .filter(k ->(name==null || name.isEmpty() || k.getIme().toLowerCase().contains(name.toLowerCase())))
                .filter(k -> (lastname==null || lastname.isEmpty() || k.getPrezime().toLowerCase().contains(lastname.toLowerCase())))
                .filter(k -> (email==null || email.isEmpty() || k.getEmail().toLowerCase().contains(email.toLowerCase())))
                .filter(k -> (tip==null || tip.getOpis()==null || tip.getOpis().isEmpty()
                        || k.getTip().getOpis().toLowerCase().contains(tip.getOpis().toLowerCase())))
                .collect(Collectors.toList());
        this.lista = filteredList;
        fireTableDataChanged();
    }
    
    
}
