/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.avventura;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author gabri
 * 
 * Questa classe rappresenta una utility che è in grado di :
 *    1) caricare la lista delle stopwords da file
 *,   2) restituire la lista delle stopwords in output, sotto forma di set di stringhe
 *    3) pulire il contenuto della stringa passata in input al metodo parseString dalle stopwords 
 * 
 */
public class Utils implements Serializable 
{
    //Carica la lista dele stopwords da file e la restituisce sotto forma di set di stringhe
    public static Set<String> caricaStopwords(File file) throws IOException 
    {
        Set<String> set = new HashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        while (reader.ready()) 
        {
            set.add(reader.readLine().trim().toLowerCase());
        }
        reader.close();
        return set;
    }

    //Questo metodo si occupa di pulire il contenuto della stringa passata in input dalle stopwords
    public static List<String> parseString(String string, Set<String> stopwords) 
    {
        List<String> tokens = new ArrayList<>();
        
        //suddivide la stringa contenuta in string quando trova uno spazio
        String[] split = string.toLowerCase().split("\\s+");
        
        //quando una stringa nel vettore di stringhe split non è una stopword viene inserita nella lista tokens
        for (String t : split)
        {
            if (!stopwords.contains(t)) 
            {
                tokens.add(t);
            }
        }
        
        return tokens;
    }
}
