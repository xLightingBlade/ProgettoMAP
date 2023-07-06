/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package com.mycompany.gioco;

import java.io.BufferedReader;
import java.io.FileReader;
import com.mycompany.avventura.CaricamentoDati;
import com.mycompany.avventura.StrutturaGioco;
import com.mycompany.database.OperazioniDatabase;
import com.mycompany.openweatherAPI.MeteoAPI;
import com.mycompany.parser.ParserOutput;
import com.mycompany.tipi.Comando;
import com.mycompany.tipi.Oggetto;
import com.mycompany.tipi.TipoComando;
import com.mycompany.tipi.Stanza;
import static com.mycompany.tipi.TipoComando.APRI;
import static com.mycompany.tipi.TipoComando.EST;
import static com.mycompany.tipi.TipoComando.GUARDA;
import static com.mycompany.tipi.TipoComando.INVENTARIO;
import static com.mycompany.tipi.TipoComando.LEGGI;
import static com.mycompany.tipi.TipoComando.NORD;
import static com.mycompany.tipi.TipoComando.OVEST;
import static com.mycompany.tipi.TipoComando.PRENDI;
import static com.mycompany.tipi.TipoComando.SPINGI;
import static com.mycompany.tipi.TipoComando.SUD;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * ATTENZIONE: La descrizione del gioco è fatta in modo che qualsiasi gioco
 * debba estendere la classe StrutturaGioco. L'Engine è fatto in modo che possa
 * eseguire qualsiasi gioco che estende StrutturaGioco, in questo modo si
 * possono creare più gioci utilizzando lo stesso Engine.
 *
 * Diverse migliorie possono essere applicate: - la descrizione del gioco
 * potrebbe essere caricate da file o da DBMS in modo da non modificare il
 * codice sorgente - l'utilizzo di file e DBMS non è semplice poiché all'interno
 * del file o del DBMS dovrebbe anche essere codificata la logica del gioco
 * (prossimaMossa) oltre alla descrizione di stanze, oggetti, ecc...
 *
 * @author gabri
 */
public class Avventura extends StrutturaGioco implements Serializable 
{
    private static final long serialVersionUID = -4185062833257302102L;
    boolean haAccessoAllaStanza = false;
    boolean assenzaStanza = false;
    boolean usaTimer = true;
    static Integer idMeteo = 0;
    
    @Override
    public void init() throws Exception 
    {
        OperazioniDatabase.connettiDatabase();
        OperazioniDatabase.resetDatabase();
        OperazioniDatabase.creaTabelle();
        OperazioniDatabase.caricaDati();
        OperazioniDatabase.creaStanze();
        
        setComandi(OperazioniDatabase.creaComandi());
        setStanze(OperazioniDatabase.creaOggetti());
        //Stanza attuale
        setStanzaCorrente(getStanze().get(0));
          
    }
    
    /** Per info sui possibili valori dell'id meteo:
     *  https://openweathermap.org/weather-conditions 
    */
    
    public static void dialoghiMeteoSoggiorno(String citta) {
        try {
            idMeteo = MeteoAPI.getMeteoID(citta);
            if(idMeteo.toString().startsWith("80")) {
                CaricamentoDati leggiFile = new CaricamentoDati(new BufferedReader(new FileReader(".//the_last_of_us(storia)//Dialoghi//soggiornoSoleggiato.txt")));
                leggiFile.start();
            } else if(idMeteo.toString().startsWith("5")) {
                CaricamentoDati leggiFile = new CaricamentoDati(new BufferedReader(new FileReader(".//the_last_of_us(storia)//Dialoghi//soggiornoPiove.txt")));
                leggiFile.start();
            } else if(idMeteo.toString().startsWith("6")) {
                CaricamentoDati leggiFile = new CaricamentoDati(new BufferedReader(new FileReader(".//the_last_of_us(storia)//Dialoghi//soggiornoNevica.txt")));
                leggiFile.start();  
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Avventura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Avventura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void dialoghiMeteoCitta(String citta) {
        try {
            idMeteo = MeteoAPI.getMeteoID(citta);
            //esempi, tanto per adesso non verranno mai chiamati. Nemmeno i file di testo esistono, questo è un esempio dell'idea.
            switch(citta) {
                case "Boston" -> {
                    if(idMeteo.toString().startsWith("80")) {
                        CaricamentoDati leggiFile = new CaricamentoDati(new BufferedReader(new FileReader(".//the_last_of_us(storia)//Dialoghi//BostonSole.txt")));
                        leggiFile.start();
                    }else if(idMeteo.toString().startsWith("5")) {
                        CaricamentoDati leggiFile = new CaricamentoDati(new BufferedReader(new FileReader(".//the_last_of_us(storia)//Dialoghi//BostonPioggia.txt")));
                        leggiFile.start();
                    }else if(idMeteo.toString().startsWith("6")) {
                        CaricamentoDati leggiFile = new CaricamentoDati(new BufferedReader(new FileReader(".//the_last_of_us(storia)//Dialoghi//BostonNeve.txt")));
                        leggiFile.start();
                    }
                }
                case "Salt Lake City" -> {
                    if(idMeteo.toString().startsWith("80")) {
                        CaricamentoDati leggiFile = new CaricamentoDati(new BufferedReader(new FileReader(".//the_last_of_us(storia)//Dialoghi//SaltLakeCitySole.txt")));
                        leggiFile.start();
                    }else if(idMeteo.toString().startsWith("5")) {
                        CaricamentoDati leggiFile = new CaricamentoDati(new BufferedReader(new FileReader(".//the_last_of_us(storia)//Dialoghi//SaltLakeCityPioggia.txt")));
                        leggiFile.start();
                    }else if(idMeteo.toString().startsWith("6")) {
                        CaricamentoDati leggiFile = new CaricamentoDati(new BufferedReader(new FileReader(".//the_last_of_us(storia)//Dialoghi//SaltLakeCityNeve.txt")));
                        leggiFile.start();
                    }
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Avventura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Avventura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    //Aggiungere comando per leggere
    //In questo metodo è racchiuso il cuore del gioco e il suo avanzare.
    public void prossimaMossa(ParserOutput p) 
    {
        if (p.getComando() == null) 
        {
            System.out.println("Non ho capito cosa devo fare! Prova con un altro comando.");
        } 
        else 
        {
            EsecuzioneComandi esec = new EsecuzioneComandi(this);
            this.haAccessoAllaStanza = false;
            this.assenzaStanza = false;
            Stanza stanzacorrente = getStanzaCorrente();
            List<Oggetto> inventarioGiocatore = getInventario();
            List<Stanza> stanze = getStanze();
            Oggetto oggetto = p.getOggetto();
            Oggetto oggettoInventario = p.getOggettoInventario();
            TipoComando comando = p.getComando().getTipo();
            
            switch(comando)
            {
                case NORD -> esec.checkNordAccess(stanzacorrente,inventarioGiocatore);
                
                case SUD -> esec.checkSudAccess( stanzacorrente,inventarioGiocatore);
                
                case EST -> esec.checkEstAccess(stanzacorrente, inventarioGiocatore);
                
                case OVEST -> esec.checkWestAccess(stanzacorrente,inventarioGiocatore);
                
                case INVENTARIO -> esec.printInventarioContent(inventarioGiocatore);
                
                case GUARDA -> esec.printOsservazione(stanzacorrente);
                
                case PRENDI -> esec.prendiOggetto( oggetto,inventarioGiocatore, stanzacorrente);
                
                case APRI -> esec.apriOggetto(oggetto, oggettoInventario, stanzacorrente, inventarioGiocatore );
                
                case SPINGI -> 
                {
                    if(oggetto!= null)
                    {
                        esec.spingiOggetto(oggetto, inventarioGiocatore, stanzacorrente);
                    }
                    else
                    {
                        esec.spingiOggetto(oggettoInventario, inventarioGiocatore, stanzacorrente);
                    }
                }
                
                case LEGGI -> 
                {
                    if(oggetto!= null)
                    {
                        esec.leggiOggetto(oggetto);
                    }
                    else
                    {
                        esec.leggiOggetto(oggettoInventario);
                    }
                }
                case FINE -> esec.chiudiPartita();
                
                case ACCENDI -> 
                {
                    if(oggetto!= null)
                        esec.accendiQualcosa(stanzacorrente, inventarioGiocatore, oggetto);
                    else
                        esec.accendiQualcosa(stanzacorrente, inventarioGiocatore, oggettoInventario);
                }
                
                case NASCONDITI -> 
                {       
                    esec.nasconditi(stanzacorrente);
                }

                case CURATI -> esec.curati(inventarioGiocatore);
                
                case USA -> {
                    if(oggetto!= null)
                    {
                        try 
                        {
                            esec.usaQualcosa(stanzacorrente, inventarioGiocatore, oggetto);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Avventura.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } 
                    else 
                    {
                        try 
                        {
                            esec.usaQualcosa(stanzacorrente, inventarioGiocatore, oggettoInventario);
                        } 
                        catch (InterruptedException ex) 
                        {
                            Logger.getLogger(Avventura.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                
                default -> 
                {
                    return;
                }
            }
            
            
            if (this.haAccessoAllaStanza) 
            {
                System.out.println();
                System.out.println(getStanzaCorrente().getNome());
                System.out.println("================================================");
                System.out.println(getStanzaCorrente().getDescrizione());
                System.out.println();
                
                BehaviourController.checkDialoghi(getStanzaCorrente());

                if(getStanzaCorrente().getNome().equalsIgnoreCase("Ingresso Ospedale"))
                {
                    prossimaMossa(new ParserOutput(new Comando(NORD,"nord"),null));
                }
                else if(getStanzaCorrente().getNome().equalsIgnoreCase("Sala Operatoria"))
                {
                    prossimaMossa(new ParserOutput(new Comando(NORD,"nord"),null));
                }
                else if(getStanzaCorrente().getNome().equalsIgnoreCase("Corridoio passaggio segreto") && usaTimer)
                {
                    usaTimer = false;
                    gestioneTimer();
                }                
            }
            
            if (this.assenzaStanza) 
            {
                System.out.println("Da quella parte non si può andare c'è un muro!\n");
            }
            
            if(getStanzaCorrente().getNome().equalsIgnoreCase("Salt Lake City, Utah"))
            {
                System.exit(0);
            }
        }
    }
    
    //gestione dei timer di gioco
    @Override
    public void gestioneTimer()
    {
        boolean dentro = false;
        
        Timer timer = new Timer();
        
        //avvio del timer per la prima volta
        TimerGioco t = new TimerGioco();
        TimerTask tempoScaduto = t;
        timer.schedule(tempoScaduto, 20000);//attendi 11 secondi, poi hai perso
        
        System.out.println("Nasconditi dalle guardie prima che ti trovino.\n Hai ancora pochi secondi per farlo.\n");
        
        do
        {
            
            if(t.isTempoScaduto())
            {
                t = new TimerGioco();
                tempoScaduto = t;
                timer.schedule(tempoScaduto, 20000);//attendi 11 secondi, poi hai perso
            }

            //aspetta che l'utente si nasconda
            Scanner scanner = new Scanner(System.in);
            if(scanner.hasNextLine()) 
            {
                if(scanner.nextLine().equalsIgnoreCase("nasconditi"))//comando preso in input dall'utente)
                {
                    timer.cancel();//annulla il timer
                    
                    EsecuzioneComandi esec = new EsecuzioneComandi(this);
                    esec.nasconditi(getStanzaCorrente());
                    dentro = true;                  
                }
                else
                {
                    System.out.println("Nasconditi, o i soldati ti prenderanno.");
                }
            }           
        }
        while(dentro == false);
    }
}
