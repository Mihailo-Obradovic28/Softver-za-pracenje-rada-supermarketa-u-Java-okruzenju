/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.TipKupca;

/**
 *
 * @author Milosh
 */
public class ModelTabeleTipKupca extends AbstractTableModel{
    List<TipKupca> lista ;
    String kolone[] = {"idTipKupca","opis"};

    public ModelTabeleTipKupca(List<TipKupca> lista) {
        this.lista = lista;
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
        TipKupca tk = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return tk.getIdTipKupca();
            case 1:
                return tk.getOpis();
            default:
                return "N/A";
        }
    }
    
}
