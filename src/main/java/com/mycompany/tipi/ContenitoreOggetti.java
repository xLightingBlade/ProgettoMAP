/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tipi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class ContenitoreOggetti extends Oggetto implements Serializable 
{
    private List<Oggetto> list = new ArrayList<>();

    
    public ContenitoreOggetti(int id) 
    {
        super(id);
    }

    
    public ContenitoreOggetti(int id, String nome) 
    {
        super(id, nome);
    }

    
    public ContenitoreOggetti(int id, String nome, String descrizione) 
    {
        super(id, nome, descrizione);
    }

    
    public ContenitoreOggetti(int id, String nome, String descrizione, Set<String> alias) 
    {
        super(id, nome, descrizione, alias);
    }

    
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
