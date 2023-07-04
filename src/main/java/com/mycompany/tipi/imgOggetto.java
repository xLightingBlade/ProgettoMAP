/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tipi;

import java.util.Set;

/**
 *
 * @author santo
 */
public class imgOggetto extends Oggetto {
    
    String pathImg;

    public imgOggetto(int id, String nome, String descrizione, Set<String> alias, String contenuto, String relativePathImg) {
        super(id, nome, descrizione, alias, contenuto);
        this.pathImg = relativePathImg;
    }
    
    public void setPathImg(String relativePathImg) {
        this.pathImg = relativePathImg;
    }
    
    public String getPathImg() {
        return this.pathImg;
    }
}
