package com.mycompany.tipi;

import com.mycompany.exception.ImgException;
import com.mycompany.swing.ImgJFrame;

/**
 *
 * @author santo
 */
public class OggettoImmagine extends Documento 
{        
    public OggettoImmagine(int id, String nome, String descrizione) 
    {
        super(id, nome, descrizione);
    }
    
    //Questo metodo si occupa della visualizzazione di un'immagine di gioco con una interfaccia grafica.
    //Qualora in futuro non si fosse più soddisfatti del metodo di visualizzazione della foto,
    //basterà cambiare unicamente il codice di questo metodo.
    @Override
    public void visualizza()
    {     
        try
        {
            new ImgJFrame(getPathDocumento()).setVisible(true);
        }
        catch(ImgException e)
        {
            System.out.println(e.getMessage());
        }
    }   
}
