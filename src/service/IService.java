/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wiemhjiri
 */
public interface IService<T> {
    void insert(T t);
  
    void delete(int id);
    T readById(int id);
    ArrayList<T>readAll();
        void add (T entity);
    void update (T entity);
    void Delete (int id);
    List <T> Show();
    T getById(int id);
   
    
}
