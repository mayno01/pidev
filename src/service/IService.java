/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/**
 *
 * @author Saida
 */
public interface IService <T> {
    void add (T entity);
    void update (T entity);
    void Delete (int id);
    List <T> Show();
    T getById(int id);
   
    
    
}
