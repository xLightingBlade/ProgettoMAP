package com.mycompany.avventura;


import java.util.List;
import java.lang.IllegalArgumentException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Antonio
 */
public class VisualizzatoreFrasi 
{
    //Attributi
    private final List<String> frasi;
    
    //Costruttori
    public VisualizzatoreFrasi(List<String> frasi)
    {
        this.frasi = frasi;
    }
    
    public void mostraFrasi(Integer secondiAttesa) throws InterruptedException, IllegalArgumentException
    {
        if(secondiAttesa >= 0 && secondiAttesa <= 3)
        {
            for(String s : frasi)
            {
                System.out.println(s);
                Thread.sleep(secondiAttesa*1000);
            }
        }
        else
        {
            throw new IllegalArgumentException(); 
        }
    }
    
}
