/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tipi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabri
 */
public class Inventario implements Serializable 
{

    private List<Oggetto> list = new ArrayList<>();

    
    public List<Oggetto> getList() 
    {
        return list;
    }

    
    public void setList(List<Oggetto> list) 
    {
        this.list = list;
    }

    
    public void add(Oggetto o) 
    {
        list.add(o);
    }

    
    public void remove(Oggetto o)
    {
        list.remove(o);
    }
}
