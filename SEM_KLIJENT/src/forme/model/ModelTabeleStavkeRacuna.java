/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Roba;
import model.StavkaRacuna;

/**
 *
 * @author Milosh
 */
public class ModelTabeleStavkeRacuna extends AbstractTableModel {
    
    List<StavkaRacuna> stavke = new ArrayList<>();
    String kolone[] = {"rb", "roba", "kolicina", "jedinicnaCena", "iznos"};
    
    public ModelTabeleStavkeRacuna(List<StavkaRacuna> stavke){
        if(stavke!=null){
            this.stavke=stavke;
        }
    }
    
    @Override
    public int getRowCount() {
        return stavke.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

   @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaRacuna sr = stavke.get(rowIndex);
        switch(columnIndex){
            case 0:
                return sr.getRb();
            case 1:
                return sr.getRoba() != null ? sr.getRoba(): null;
            case 2:
                return sr.getKolicina();
            case 3:
                return sr.getJedinicnaCena() != null ? sr.getJedinicnaCena() : "";
            case 4:
                return sr.getIznos() != null ? sr.getIznos() : "";
            default:
                return "Greska u switch ModelTabeleStavkeRacuna";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
         return columnIndex == 1 || columnIndex == 2;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        /*StavkaRacuna sr = stavke.get(rowIndex);
        switch(columnIndex) {
            case 1:
                if(aValue instanceof Roba) {
                    sr.setRoba((Roba) aValue);
                    sr.setJedinicnaCena(sr.getRoba().getCena());
                }
                break;
            case 2:
                sr.setKolicina(Integer.parseInt(aValue.toString()));
                break;
        }
        if(sr.getRoba() != null && sr.getKolicina() > 0) {
            sr.setIznos(sr.getJedinicnaCena().multiply(new BigDecimal(sr.getKolicina())));
        }
        fireTableDataChanged();*/
        if (rowIndex < 0 || rowIndex >= stavke.size()) {
            return;
        }
        StavkaRacuna sr = stavke.get(rowIndex);
        switch(columnIndex){
            case 1:
                if(aValue instanceof Roba){
                    sr.setRoba((Roba) aValue);
                    sr.setJedinicnaCena(sr.getRoba().getCena());
                    
                }
                break;
            case 2:
                sr.setKolicina(Integer.parseInt(aValue.toString()));
                proveriISpoji(rowIndex);
                /*if(sr.getRoba() != null) {
                for(int i = 0; i < stavke.size(); i++) {
                    if(i != rowIndex && stavke.get(i).getRoba() != null
                       && stavke.get(i).getRoba().getIdRoba() == sr.getRoba().getIdRoba()) {
                        
                        stavke.get(i).setKolicina(stavke.get(i).getKolicina() + sr.getKolicina());
                        stavke.get(i).setIznos(stavke.get(i).getJedinicnaCena()
                            .multiply(new BigDecimal(stavke.get(i).getKolicina())));
                        stavke.remove(rowIndex);
                        for(int j = 0; j < stavke.size(); j++) {
                            stavke.get(j).setRb(j + 1);
                        }
                        fireTableDataChanged();
                        return;
                    }
                }
            }*/
            break;
        }
        if(sr.getRoba() != null && sr.getKolicina() > 0) {
            sr.setIznos(sr.getJedinicnaCena().multiply(new BigDecimal(sr.getKolicina())));
        }
        fireTableDataChanged();
    }

    

    public List<StavkaRacuna> getStavke() {
        return stavke;
    }
    public void addRow() {
        StavkaRacuna nova = new StavkaRacuna();
        int maxRb = stavke.stream().mapToInt(StavkaRacuna::getRb).max().orElse(0);
        nova.setRb(maxRb+1);
        stavke.add(nova);
        fireTableDataChanged();
    }

    public void removeRow(int selektovanRed) {
        stavke.remove(selektovanRed);
        /*int brojac = 1;
        for(StavkaRacuna stavka: stavke){
            stavka.setRb(brojac);
            brojac++;
        }*/
        fireTableDataChanged();
    }

    private void proveriISpoji(int rowIndex) {
    StavkaRacuna aktuelna = stavke.get(rowIndex);
    if (aktuelna.getRoba() == null) return;

    for (int i = 0; i < stavke.size(); i++) {
        
        if (i != rowIndex && stavke.get(i).getRoba() != null 
            && stavke.get(i).getRoba().getIdRoba() == aktuelna.getRoba().getIdRoba()) {
            
            StavkaRacuna postojeca = stavke.get(i);
            postojeca.setKolicina(postojeca.getKolicina() + aktuelna.getKolicina());
            postojeca.setIznos(postojeca.getJedinicnaCena().multiply(new BigDecimal(postojeca.getKolicina())));
            stavke.remove(rowIndex);
            
            fireTableDataChanged();
            return; 
        }
    }
}
    
}
