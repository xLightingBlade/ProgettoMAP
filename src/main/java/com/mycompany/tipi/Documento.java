/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tipi;

/**
 *
 * @author Antonio
 */
public abstract class Documento extends Oggetto 
{
    protected String pathDocumento;

    
    public Documento(int id, String nome, String descrizione) 
    {
        super(id, nome, descrizione);
    }
    
    
    public void setPathDocumento(String pathRelativoDocumento) 
    {
        this.pathDocumento = pathRelativoDocumento;

    }
    
    
    public String getPathDocumento() 
    {
        return this.pathDocumento;
    }   
    
    
    public abstract void visualizza();
}
