package com.mycompany.avventura;


import java.util.List;

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
    
    public void mostraFrasi(Integer secondiAttesa) throws InterruptedException
    {
        for(String s : frasi)
        {
            System.out.println(s);
            Thread.sleep(secondiAttesa*1000);
        }
    }
    
}
