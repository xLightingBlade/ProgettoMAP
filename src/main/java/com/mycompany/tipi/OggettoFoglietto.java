/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tipi;

import com.mycompany.avventura.LoaderPrinterCharacterStream;
import com.mycompany.swing.DocumentFrame;

/**
 *
 * @author Antonio
 */
public class OggettoFoglietto extends Documento
{

    public OggettoFoglietto(int id, String nome, String descrizione) 
    {
        super(id, nome, descrizione);
    }
    
    //Questo metodo si occupa della visualizzazione di un documento di gioco con una interfaccia grafica.
    //Qualora in futuro non si fosse più soddisfatti del metodo di visualizzazione della foto,
    //basterà cambiare unicamente il codice di questo metodo.
    @Override
    public void visualizza()
    {
        LoaderPrinterCharacterStream loader = new LoaderPrinterCharacterStream();
        DocumentFrame documentFrame = new DocumentFrame("documento", loader.ottieniComeTesto(pathDocumento));
        documentFrame.setVisible(true);
    }
}
