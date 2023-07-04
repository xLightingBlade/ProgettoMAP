package com.mycompany.tipi;

import java.util.Set;

/**
 *
 * @author santo
 */
public class ImgOggetto extends Oggetto {
        
    private String pathImg;

    public ImgOggetto(int id, String nome, String descrizione) {
        super(id, nome, descrizione);
    }
    
    public void setImgPath(String relativePathImg) {
        this.pathImg = relativePathImg;
    }
    
    public String getPathImg() {
        return this.pathImg;
    }
    
}