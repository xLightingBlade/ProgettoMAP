/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package com.mycompany.gioco;

import java.io.BufferedReader;
import java.io.FileReader;
import com.mycompany.avventura.CaricamentoDati;
import com.mycompany.avventura.LoaderPrinterCharacterStream;
import com.mycompany.avventura.StrutturaGioco;
import com.mycompany.avventura.Utils;
import com.mycompany.database.OperazioniDatabase;
import com.mycompany.openweatherAPI.MeteoAPI;
import com.mycompany.parser.Parser;
import com.mycompany.parser.ParserOutput;
import com.mycompany.swing.DocumentFrame;
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
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
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
     * @param citta
    */
    
    public static void dialoghiMeteoSoggiorno(String citta) {
        try {
            idMeteo = MeteoAPI.getMeteoID(citta);
            if(idMeteo.toString().startsWith("80")) {
                CaricamentoDati leggiFile = new CaricamentoDati(new BufferedReader(new InputStreamReader(new FileInputStream(".//the_last_of_us(storia)//Dialoghi//soggiornoSoleggiato.txt"), "UTF-8")));
                leggiFile.start();
            } else if(idMeteo.toString().startsWith("5")) {
                CaricamentoDati leggiFile = new CaricamentoDati(new BufferedReader(new InputStreamReader(new FileInputStream(".//the_last_of_us(storia)//Dialoghi//soggiornoPiove.txt"), "UTF-8")));
                leggiFile.start();
            } else if(idMeteo.toString().startsWith("6")) {
                CaricamentoDati leggiFile = new CaricamentoDati(new BufferedReader(new InputStreamReader(new FileInputStream(".//the_last_of_us(storia)//Dialoghi//soggiornoNeve.txt"), "UTF-8")));
                leggiFile.start();  
            }
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(Avventura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void dialoghiMeteoCitta(String citta) {
        try {
            idMeteo = MeteoAPI.getMeteoID(citta);
            switch(citta) {
                case "Boston" -> {
                    if(idMeteo.toString().startsWith("80")) {
                        CaricamentoDati leggiFile = new CaricamentoDati(new BufferedReader(new InputStreamReader(new FileInputStream(".//the_last_of_us(storia)//Dialoghi//BostonSole.txt"), "UTF-8")));
                        leggiFile.start();
                    }else if(idMeteo.toString().startsWith("5")) {
                        CaricamentoDati leggiFile = new CaricamentoDati(new BufferedReader(new InputStreamReader(new FileInputStream(".//the_last_of_us(storia)//Dialoghi//BostonPioggia.txt"), "UTF-8")));
                        leggiFile.start();
                    }else if(idMeteo.toString().startsWith("6")) {
                        CaricamentoDati leggiFile = new CaricamentoDati(new BufferedReader(new InputStreamReader(new FileInputStream(".//the_last_of_us(storia)//Dialoghi//BostonNeve.txt"), "UTF-8")));
                        leggiFile.start();
                    }
                }
                case "Salt Lake City" -> {
                    if(idMeteo.toString().startsWith("80")) {
                        CaricamentoDati leggiFile = new CaricamentoDati(new BufferedReader(new InputStreamReader(new FileInputStream(".//the_last_of_us(storia)//Dialoghi//SaltLakeCitySole.txt"), "UTF-8")));
                        leggiFile.start();
                    }else if(idMeteo.toString().startsWith("5")) {
                        CaricamentoDati leggiFile = new CaricamentoDati(new BufferedReader(new InputStreamReader(new FileInputStream(".//the_last_of_us(storia)//Dialoghi//SaltLakeCityPioggia.txt"), "UTF-8")));
                        leggiFile.start();
                    }else if(idMeteo.toString().startsWith("6")) {
                        CaricamentoDati leggiFile = new CaricamentoDati(new BufferedReader(new InputStreamReader(new FileInputStream(".//the_last_of_us(storia)//Dialoghi//SaltLakeCityNeve.txt"), "UTF-8")));
                        leggiFile.start();
                    }
                }
            }
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(Avventura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
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
                
                case HELP -> 
                {
                    LoaderPrinterCharacterStream loader = new LoaderPrinterCharacterStream();
                    DocumentFrame manualeUtente = new DocumentFrame("Manuale utente",loader.ottieniComeTesto(".//resources//istruzioniGioco.txt"));
                    manualeUtente.getTextLabel().setFont(new Font("Press Gothic", Font.BOLD, 15));
                    manualeUtente.setVisible(true);
                }
                
                case ATTACCA ->
                {
                    esec.attacca(stanzacorrente);
                }
                        
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
                
                case USA -> 
                {
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
                    System.out.println("Nasconditi dalle guardie prima che ti trovino.\n Hai ancora pochi secondi per farlo.\n");
                    gestioneTimer("nasconditi","the_last_of_us(storia)//Dialoghi//guardie_prendono_Joel_e_Ellie.txt","the_last_of_us(storia)//Dialoghi//Passaggio_segreto.txt");
                }
                else if(getStanzaCorrente().getNome().equalsIgnoreCase("Stanza Zattera") && usaTimer)
                {   
                    //il timer viene disattivato, altrimenti l'utente potrebbe avanzare, tornare indietro e
                    //doversi nascondere di nuovo dalle guardie. Il che non avrebbe senso, visto che lo ha già fatto prima.
                    usaTimer = false;
                    
                    System.out.println("Attacca il clicker prima che ti uccida.\n Hai ancora pochi secondi per farlo.\n");
                    gestioneTimer("attacca","the_last_of_us(storia)//Dialoghi//infetti_uccidono_Joel.txt","the_last_of_us(storia)//Dialoghi//Stanza_della_zattera.txt");
                }
                
                //questo if serve per resettare il timer (disattivato prima) e renderlo utilizzabile nelle prossime fasi di gioco.
                else if(getStanzaCorrente().getNome().equalsIgnoreCase("Uscita Passaggio"))
                {
                    usaTimer = true;
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
    /**
     *
     * @param comando
     * @param fileFrasi
     * @param dialoghiTimerScaduto
     */
    //Questo metodo di occupa di avviare il timer e gestirlo in modo generico dalla situazione di gioco in cui viene usato.
    //Verranno caricati i dialoghi specific allo scadere del timer e finchè l'utente non inserisce il comando corretto
    //il timer si resetta e vengono ricaricati i dialoghi iniziali che venivano caricati all'ingresso della stanza
    @Override
    public void gestioneTimer(String comando,String fileFrasi,String dialoghiTimerScaduto)
    {
        boolean dentro = false;
        
        Timer timer = new Timer();
        
        //avvio del timer per la prima volta
        TimerGioco t = new TimerGioco(fileFrasi,dialoghiTimerScaduto);
        TimerTask tempoScaduto = t;
        timer.schedule(tempoScaduto, 20000);//attendi 11 secondi, poi hai perso
        
        do
        {          
            if(t.isTempoScaduto())
            {
                t = new TimerGioco(fileFrasi,dialoghiTimerScaduto);
                tempoScaduto = t;
                timer.schedule(tempoScaduto, 20000);//attendi 11 secondi, poi hai perso
            }

            //aspetta che l'utente faccia qualcosa
            Scanner scanner = new Scanner(System.in);
            if(scanner.hasNextLine()) 
            {
                if(scanner.nextLine().equalsIgnoreCase(comando))//comando preso in input dall'utente)
                {
                    timer.cancel();//annulla il timer

                    try
                    {
                        //caricamento delle stopwords in un set di stringhe.
                        Set<String> stopwords = Utils.caricaStopwords(new File("./resources/stopwords"));
                        Parser parser = new Parser(stopwords);//creazione del parser con le relative stopwords
                        ParserOutput p = parser.parse(comando, getComandi(), getStanzaCorrente().getOggetti(), getInventario());
                        prossimaMossa(p);
                    }
                    catch (IOException ex)
                    {
                        System.out.println("Errore nel caricamento dati. Riavvia il gioco.");
                        System.exit(0);
                    }
                   
                    dentro = true;                  
                }
                else
                {
                    System.out.println("Fai qualcosa prima che sia troppo tardi");
                }
            }           
        }
        while(dentro == false);
    }
}
