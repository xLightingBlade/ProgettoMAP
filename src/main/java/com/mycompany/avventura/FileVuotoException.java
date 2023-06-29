/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.avventura;

/**
 *
 * @author Antonio
 */
public class FileVuotoException extends Exception
{
    @Override
    public String getMessage()
    {
        return "Impossibile caricare la partita. Ne hai salvata una ma è vuota.";
    }
}
