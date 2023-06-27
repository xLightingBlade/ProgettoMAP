/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exception;

/**
 *
 * @author santo
 */
public class DimException extends Exception{
    @Override
    public String getMessage() {
        return "la dimensione dell'immagine non è conforme con i criteri di dimensioni che accetta questa classe,\n"
                + "puoi accedere alle costanti per capire quali sono i formati accettati.";
    }
}
