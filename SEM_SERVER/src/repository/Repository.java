/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;

/**
 *
 * @author Milosh
 */
//crud operacije svih tabela

public interface Repository <T>{
    List<T> getAll(T param,String uslov) throws Exception;//kada radim pretragu
    void add(T param) throws Exception;
    void edit(T param) throws Exception;
    void delete(T param) throws Exception;
    List<T> getAll();
    int addAndReturnId(T param) throws Exception;
}
