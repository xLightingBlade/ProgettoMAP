/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.avventura;

import com.mycompany.gioco.Avventura;
import com.mycompany.parser.Parser;
import com.mycompany.parser.ParserOutput;
import com.mycompany.tipi.TipoComando;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabri
 * Da questa classe partirà l'esecuzione del gioco ero e proprio. Essa è fatta in modo che possa
 * eseguire qualsiasi gioco che estende StrutturaGioco, in questo modo si
 * possono creare più gioci utilizzando lo stesso Engine.
 */
public class Engine
{

    private final StrutturaGioco gioco;

    private Parser parser;

    //Costruttore
    /*Il costruttore fa principalmente 2 cose:
        1)caricamento dei dati del gioco
        2)crezione del parser
        3)caricamento delle stopwords da file
    */
    public Engine(StrutturaGioco game) 
    {
        this.gioco = game;
        
        try
        {
            this.gioco.init();//Questa chiamata fa partire il caricamento dei dati del gioco
        }
        catch (Exception ex)
        {
            System.err.println(ex);
        }
        
        try
        {
            //caricamento delle stopwords in un set di stringhe.
            Set<String> stopwords = Utils.caricaStopwords(new File("./resources/stopwords"));
            parser = new Parser(stopwords);//creazione del parser con le relative stopwords
        }
        catch (IOException ex)
        {
            System.err.println(ex);
        }
    }
    //
    
    //Questo metodo fa partire il gioco, rendendolo giocabile all'utente
    public void esegui()
    {
        //frasi introduttive
        List<String> frasi = new ArrayList<>();
        frasi.add("Prod: Antonio Mennuni , Danilo Santo , Gabriele Nigro");
        frasi.add("================================");
        frasi.add("The last of us PART 1");
        frasi.add("================================");
        frasi.add("Siamo nel 2050 e 37 anni fa, esattamente il 26/09/2013, un fungo chiamato cordyceps");
        frasi.add("ha causato una pandemia globale che ha infettato il 90% della popolazione mondiale,");
        frasi.add("trasformandola in bestie assetate di sangue (simili a zombie) che attaccano e sbranano chiunque gli si ponga davanti.");
        frasi.add("Nel frattempo l'esercito ha creato delle ZQ, cioè delle aree murate in cui la popolazione ha ripreso a vivere");
        frasi.add("e in cui vige il pugno di ferro dell'esercito. Siamo nella ZQ di Boston e un contrabbandiere di nome Joel deve recuperare");
        frasi.add("un carico di armi che gli è stato rubato da un tale Robert, un truffatore noto nella ZQ. Dopo una iniziale colluttazione,");
        frasi.add("Robert confessa a Joel che le sue armi sono state vendute alle luci. Chi sono le luci? Le luci sono un gruppo di resistenza");
        frasi.add("(da molti definiti terroristi) che si oppone all'oppressione dell'esercito e cerca di trovare una cura per la pandemia causata dal fungo.");
        frasi.add("Saputo ciò, Joel incontra il capo delle luci, una tale Marlene, e spiega l'accaduto per riavere indietro le sue armi.");
        frasi.add("Marlene propone a Joel un patto: deve portare fuori dalla ZQ, in uno stabilimento delle luci a Nord, una bambina di 12 anni di nome Ellie.");
        frasi.add("Joel decide, dopo un iniziale rifiuto, di accettare il patto e organizza così il viaggio...");
        frasi.add("\n");
        
        frasi.add("Boston, 2050");
        frasi.add("\n");
        frasi.add(gioco.getStanzaCorrente().getNome());
        frasi.add(gioco.getStanzaCorrente().getDescrizione() + "\n");
        
        //Dialoghi iniziali
        frasi.add("Ellie:   Ho sentito una sparatoria, cosa è successo?");
        frasi.add("Joel :   Le luci..");
        frasi.add("Joel:    Come conosci il capo delle luci?");
        frasi.add("Ellie:   Marlene conosceva mia mamma e, si è presa cura di me.");
        frasi.add("Joel:    E così al posto di andare a scuola ti sei unita alle luci.");
        frasi.add("Ellie:   Non ti dirò perchè mi stai facendo uscire se è questo che vuoi sapere.");
        frasi.add("Joel:    Sai qual è il bello del mio lavoro? Che non devo sapere il perchè");
        frasi.add("Joel:    Non me ne frega niente del perchè devi uscire da qui.");
        frasi.add("Joel:    Muoviamoci, questa storia mi ha già stancato.\n");
        
        VisualizzatoreFrasi visual = new VisualizzatoreFrasi(frasi);
        
        try 
        {
            visual.mostraFrasi(1);
        }
        catch (InterruptedException ex) 
        {
            System.out.println("Riavvia il gioco\n");
            return;
        }
        
        
        /*
        Viene preso un comando in input, viene analizzato dal parser.
        In base all'output del parser si procede con il gioco.
        */    
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) 
        {
            String command = scanner.nextLine();//comando preso in input dall'utente
            
            //E' presente l'output del parser dopo aver processato il comando dell'utente
            ParserOutput p = parser.parse(command, gioco.getComandi(), gioco.getStanzaCorrente().getOggetti(), gioco.getInventario());
            
            if (p == null || p.getComando() == null)
            {
                System.out.println("Non capisco quello che mi vuoi dire.");
                System.out.println();
            }
            else if (p.getComando() != null && p.getComando().getTipo() == TipoComando.FINE)
            {
                System.out.println("Partita terminata");
                break;
            }
            else
            {
                gioco.prossimaMossa(p, System.out);//avanzo con il gioco
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Engine engine = new Engine(new Avventura());
        engine.esegui();//partenza del gioco
    }

}
