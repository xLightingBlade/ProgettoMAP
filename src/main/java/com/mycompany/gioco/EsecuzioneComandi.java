/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gioco;
import com.mycompany.avventura.CaricamentoDati;
import com.mycompany.avventura.LoaderPrinterCharacterStream;
import com.mycompany.swing.DocumentFrame;
import com.mycompany.swing.TastierinoJFrame;
import com.mycompany.tipi.ContenitoreOggetti;
import com.mycompany.tipi.OggettoImmagine;
import com.mycompany.tipi.Oggetto;
import com.mycompany.tipi.OggettoFoglietto;
import com.mycompany.tipi.Stanza;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author gabri
 */

public class EsecuzioneComandi implements Serializable
{ 
    void chiudiPartita() {
        System.out.println("Partita terminata");
        System.exit(0);
    }
    
    
    //controllo se ci sono oggetti nell'inventario che possano far luce. Nel caso ce ne sia uno, lo accendo.
    void accendiQualcosa(Stanza stanzaCorrente, List<Oggetto> inventarioGiocatore, Oggetto oggetto) 
    {
        
        //se la stanza è al buio
        if(stanzaCorrente.isVisibile() == false) 
        {
            //se l'oggetto scritto nel comando si trova nell'inventario
            if(inventarioGiocatore.contains(oggetto))
            {
                //controlla se l'oggetto si può accendere
                if(oggetto.isAccendibile())
                {
                    stanzaCorrente.setVisibile(true);
                    System.out.println("Hai acceso l'oggetto " + oggetto.getNome() + " ,adesso riesci a vedere cosa c'è nella stanza");
                }
                else
                {
                    System.out.println("Questo oggetto non può essere acceso");
                }
            }
            else
            {
                System.out.println("Devi prima raccogliere questo oggetto");
            }
        }
        else 
        {
            System.out.println("La stanza è già abbastanza illuminata");
        }
    }
    
    
    //Questo metodo simula l'attacco nel gioco
    void attacca(Stanza stanzacorrente) throws UnsupportedEncodingException
    {
        //diloghi attacco
        if(stanzacorrente.getNome().equalsIgnoreCase("Stanza Zattera")) 
        {
            BufferedReader fileIn = null;
            CaricamentoDati loader_introduzione = null;

            try 
            {     
                //dialoghi post attacco di Joel                   
                fileIn = new BufferedReader(new InputStreamReader(new FileInputStream(".//the_last_of_us(storia)//Dialoghi//joel_attacca.txt"), "UTF-8"));
                loader_introduzione = new CaricamentoDati(fileIn);
                loader_introduzione.start();//caricamento e visualizzazione e dialoghi con thread

                loader_introduzione.join();
            } 
            catch(FileNotFoundException ex)
            {
                System.out.println("""
                    Errore nel caricamento dati.
                    Riavvia il gioco.""");
                System.exit(0);
            } 
            catch (IllegalArgumentException ex) 
            {
                System.out.println("Errore nel caricamento dati. Riavvia il gioco.");
                System.exit(0);
            } 
            catch (InterruptedException ex)
            {
                System.err.println(ex);
                System.exit(0);
            }       
        } 
        else 
        {
            System.out.println("Non c'è bisogno di attaccare qui.\n");
        }
    }
    
    
    //per ora si nasconde solo dietro la roccia
    void nasconditi(Stanza stanzacorrente) throws UnsupportedEncodingException 
    {
        if(stanzacorrente.getOggetti().contains(new Oggetto(11))) 
        {
            System.out.println("Ti sei nascosto, attendi il momento migliore per fuggire.");
            BufferedReader fileIn = null;
            CaricamentoDati loader_introduzione = null;

            try 
            {     
                //dialoghi dietro la roccia                    
                fileIn = new BufferedReader(new InputStreamReader(new FileInputStream(".//the_last_of_us(storia)//Dialoghi//Nascondiglio_roccia.txt"), "UTF-8"));
                loader_introduzione = new CaricamentoDati(fileIn);
                loader_introduzione.start();//caricamento e visualizzazione e dialoghi con thread

                //usato per simulare il fatto che le guardie stanno passando.
                //Una volta finiti i dialoghi vuol dire che le guardie sono passate e partono gli altri dialoghi.
                loader_introduzione.join();
                //dialoghi appena le guardie sono passate                    
                fileIn = new BufferedReader(new InputStreamReader(new FileInputStream(".//the_last_of_us(storia)//Dialoghi//Dopo_nascondiglio_roccia.txt"), "UTF-8"));
                loader_introduzione = new CaricamentoDati(fileIn);
                loader_introduzione.start();//caricamento e visualizzazione e dialoghi con thread

                //usato per simulare il fatto che le guardie sono passate.
                loader_introduzione.join();
            } 
            catch(FileNotFoundException ex)
            {
                System.out.println("""
                    Errore nel caricamento dati.
                    Riavvia il gioco.""");
                System.exit(0);
            } 
            catch (IllegalArgumentException ex) 
            {
                System.out.println("Errore nel caricamento dati. Riavvia il gioco.");
                System.exit(0);
            } 
            catch (InterruptedException ex)
            {
                System.err.println(ex);
                System.exit(0);
            }       
        } 
        else 
        {
            System.out.println("Non c'è bisogno di nascondersi qui.\n");
        }
    }
    
    /**
     * Serie di metodi 'check___Access' per verificare di avere gli oggetti necessari a proseguire nella direzione chiesta.
     * @param stanzacorrente
     * @param inventarioGiocatore 
     */
    boolean checkNordAccess(Stanza stanzacorrente, List<Oggetto> inventarioGiocatore, List<Stanza> listaStanze)
    {
        if(BehaviourController.checkAccessoStanza(listaStanze.get(stanzacorrente.getNord().getId()), inventarioGiocatore))
        {
            return true;
        }
        else
        {
            return false;
        }    
    }
    
    
    boolean checkSudAccess(Stanza stanzacorrente, List<Oggetto> inventarioGiocatore, List<Stanza> listaStanze)
    {
        if(BehaviourController.checkAccessoStanza(listaStanze.get(stanzacorrente.getSud().getId()), inventarioGiocatore))
        {
            return true;
        }
        else
        {
            return false;
        }    
    }
    
    
    boolean checkEstAccess(Stanza stanzacorrente, List<Oggetto> inventarioGiocatore, List<Stanza> listaStanze)
    {
        if(BehaviourController.checkAccessoStanza(listaStanze.get(stanzacorrente.getEst().getId()), inventarioGiocatore))
        {
            return true;
        }
        else
        {
            return false;
        }    
    }
    
    
    boolean checkWestAccess(Stanza stanzacorrente, List<Oggetto> inventarioGiocatore, List<Stanza> listaStanze)
    {
        if(BehaviourController.checkAccessoStanza(listaStanze.get(stanzacorrente.getOvest().getId()), inventarioGiocatore))
        {
            return true;
        }
        else
        {
            return false;
        }    
    }
    
    
    void stampaContenutoInventario(List<Oggetto> inventarioGiocatore)
    {
        System.out.println("Nel tuo inventario ci sono:");
        for (Oggetto o : inventarioGiocatore) 
        {
            if(!o.isInvisibile()) 
            {
                System.out.println(o.getNome() + ": " + o.getDescrizione());
            }
        }
    }
    
    
    void stampaOsservazione(Stanza stanzaCorrente)
    {   
        if (stanzaCorrente.getOsservazione() != null)
        {
            if(stanzaCorrente.isVisibile() == true) 
            {
               System.out.println(stanzaCorrente.getOsservazione());

               if(stanzaCorrente.haInfoMeteo() && stanzaCorrente.getCitta() != null) 
               {
                   Avventura.dialoghiMeteoCitta(stanzaCorrente.getCitta());
               }
            } 
            else 
            {
               System.out.println("Non si vede niente!");
            }    
        } 
        else 
        {
            System.out.println("Non c'è niente di interessante da osserva qui.");
        }
    }
    
    
    /*ATTENZIONE: quando un oggetto contenitore viene aperto, tutti gli oggetti contenuti
    * vengono inseriti nella stanza o nell'inventario a seconda di dove si trova l'oggetto contenitore.
    * 
    */
    void apriOggetto(Oggetto oggetto, Oggetto oggettoInventario, Stanza stanzaCorrente, List<Oggetto> inventarioGiocatore )
    {
        if (oggetto == null && oggettoInventario == null) 
        {
            System.out.println("Non c'è niente da aprire qui.");
        } 
        else 
        {
            if (oggetto != null) 
            {
                //imposta l'armadietto come apribile se hai la sua chiave
                if(oggetto.getNome().equals("armadietto") && inventarioGiocatore.contains( ( new Oggetto(18))))
                {
                    oggetto.setApribile(true);
                }
                
                if (oggetto.isApribile() && oggetto.isAperto() == false)
                {
                    if (oggetto instanceof ContenitoreOggetti)
                    {
                        System.out.println("Hai aperto: " + oggetto.getNome());
                        ContenitoreOggetti c = (ContenitoreOggetti) oggetto;
                        
                        if (!c.getList().isEmpty()) 
                        {
                            System.out.print(c.getNome() + " contiene:");
                            Iterator<Oggetto> it = c.getList().iterator();

                            while (it.hasNext()) 
                            {
                                Oggetto next = it.next();
                                stanzaCorrente.getOggetti().add(next);
                                System.out.print(" " + next.getNome());
                                it.remove();
                            }
                            System.out.println();
                        }
                        
                        oggetto.setAperto(true);
                        
                    } 
                    else 
                    {
                        System.out.println("Hai aperto: " + oggetto.getNome());
                        oggetto.setAperto(true);
                    }
                } 
                else 
                {
                    System.out.println("Non puoi aprire questo oggetto.");
                }
            }
                    
            if (oggettoInventario != null) 
            {
                if (oggettoInventario.isApribile() && oggettoInventario.isAperto() == false) 
                {
                    if (oggettoInventario instanceof ContenitoreOggetti)
                    {
                        ContenitoreOggetti c = (ContenitoreOggetti) oggettoInventario;

                        if (!c.getList().isEmpty()) 
                        {
                            System.out.print(c.getNome() + " contiene:");
                            Iterator<Oggetto> it = c.getList().iterator();

                            while (it.hasNext()) 
                            {
                                Oggetto next = it.next();
                                inventarioGiocatore.add(next);
                                System.out.print(" " + next.getNome());
                                it.remove();
                            }
                            System.out.println();
                        }

                        oggettoInventario.setAperto(true);

                    } 
                    else 
                    {
                        oggettoInventario.setAperto(true);
                    }

                    System.out.println("Hai aperto nel tuo inventario: " + oggettoInventario.getNome());
                } 
                else 
                {
                    System.out.println("Non puoi aprire questo oggetto.");
                }
            }
        }
    }
    
    
    void prendiOggetto(Oggetto oggetto, List<Oggetto> inventarioGiocatore, Stanza stanzaCorrente) 
    {
        if (oggetto != null) 
        {
            if (oggetto.isPrendibile()) 
            {
                
                if(oggetto instanceof OggettoImmagine) 
                {
                    ((OggettoImmagine) oggetto).visualizza();
                    System.out.println("Stai guardando: " + oggetto.getDescrizione());
                    
                }
                else if(oggetto instanceof OggettoFoglietto)
                {
                    ((OggettoFoglietto) oggetto).visualizza();
                    System.out.println("Stai guardando: " + oggetto.getDescrizione());
                }
                else
                {
                    System.out.println("Hai raccolto: " + oggetto.getDescrizione());
                    inventarioGiocatore.add(oggetto);
                    stanzaCorrente.getOggetti().remove(oggetto);
                }
            }
            else 
            {
                System.out.println("Non puoi raccogliere questo oggetto.");
            }
        }
        else 
        {
            System.out.println("\nQuesto oggetto non è presente in questa stanza,\no forse non c'è niente da raccogliere qui.");
        }
    }
    
    
    void spingiOggetto(Oggetto oggetto, List<Oggetto> inventarioGiocatore, Stanza stanzaCorrente){
        if(oggetto != null){
            if (oggetto.isSpingibile()) {
                       System.out.println("Hai premuto: " + oggetto.getNome());
                       //id 14 = leva. Leva premuta crea oggetto fittizio nell'inventario
                       if (oggetto.getId() == 14) {
                           inventarioGiocatore.add(oggetto);
                           stanzaCorrente.getOggetti().remove(oggetto);
                           for(Oggetto o : stanzaCorrente.getOvest().getOggetti()) {
                               if (o.getNome().equalsIgnoreCase("tastierino")) {
                                   o.setUsabile(true);
                               }
                           }
                       }
            } else {
                System.out.println("Non ci sono oggetti che puoi premere qui.");
            }
        }else{
            System.out.println("Nessun Oggetto da spingere.");
        }
    }
    
    
    void leggiOggetto(Oggetto oggetto){
        if(oggetto != null){
            if (oggetto.isLeggibile()) {
                    System.out.print(oggetto.getContenuto());

            } else {
                System.out.println("Non ci sono oggetti che puoi leggere qui.");
            }
        }else{
            System.out.println("Nessun Oggetto da Leggere.");
        }
    }

    
    void curati(List<Oggetto> inventarioGiocatore) 
    {
        if (BehaviourController.controllaInventarioPerCura(inventarioGiocatore))
        {
            System.out.println("Ti sei curato");
            inventarioGiocatore.remove(new Oggetto(6, "garza"));
            inventarioGiocatore.remove(new Oggetto(7, "alcol"));
            
            //Questo oggetto serve per indicare che joel si è curato.
            Oggetto curato = new Oggetto(24, "curato");
            curato.setAlias(new String[]{"salute"});        
            curato.setPrendibile(false);
            curato.setSpingibile(false);
            curato.setApribile(false);
            curato.setInvisibile(true);
            inventarioGiocatore.add(curato);          
        } 
        else 
        {
            System.out.println("Non possiedi ciò che ti serve per curarti, cerca meglio");
        }
    }
    
    
    
    void help() throws IOException
    {
        LoaderPrinterCharacterStream loader = new LoaderPrinterCharacterStream();
        DocumentFrame manualeUtente = new DocumentFrame("Manuale utente",loader.ottieniComeTesto(".//resources//istruzioniGioco.txt"));
        manualeUtente.getTextLabel().setFont(new Font("Press Gothic", Font.BOLD, 15));
        manualeUtente.setVisible(true);
    }
    

    void usaQualcosa(Stanza stanzacorrente, List<Oggetto> inventarioGiocatore, Oggetto oggetto) throws InterruptedException 
    {
        if(oggetto != null) 
        {
            if(oggetto.isUsabile()) 
            {
                switch(oggetto.getId()) 
                {
                    //tastierino
                    case 12 -> 
                    {
                        if(usaTastierino()) 
                        {
                            Oggetto o = new Oggetto(25);
                            o.setNome("");
                            o.setAlias(new String[]{""});
                            o.setInvisibile(true);
                            inventarioGiocatore.add(o);
                            
                            System.out.println("Joel:  Bene, ora potrò oltrepassare il cancello");
                        } 
                    }
                    
                    
                    //cacciavite
                    case 21 ->
                    {
                        //se l'inventario contiene il cacciavite
                        if(inventarioGiocatore.contains(oggetto))
                        {
                            //se l'utente vuole usare il cacciavite nel sistema di ventilazione
                            if(stanzacorrente.getNome().equalsIgnoreCase("Sistema di ventilazione"))
                            {
                                //apri la grata nel sistema di ventilazione
                                Oggetto o = new Oggetto(26);
                                o.setNome("");
                                o.setAlias(new String[]{""});
                                o.setInvisibile(true);
                                inventarioGiocatore.add(o);
                                
                                //questo non permette al giocatore di usare di nuovo il cacciavite
                                oggetto.setUsabile(false);
                                
                                System.out.println("Joel:  Bene, la grata è aperta.");
                                System.out.println("Joel:  Ora potrò oltrepassare le guardie in silenzio");
                            }
                            else
                            {
                                System.out.println("Non puoi usare questo oggetto in questa stanza");
                            }
                        }
                        else
                        {
                            System.out.println("Devi prima raccogliere quest oggetto");
                        }
                    }
                }
            }
            else 
            {
                System.out.println("Non puoi usare questo oggetto, oppure lo hai già usato");
            }
        }
        else 
        {
            System.out.println("Non esiste un tale oggetto, o forse devi ancora raccoglierlo");
        }
    }

    
    //Metodo per aggirare la non-modalità di un jframe, di modo da fermare il flusso d'esecuzione mentre si sta usando il tastierino
    private boolean usaTastierino() throws InterruptedException 
    {
        
        new TastierinoJFrame(4,8,0).setVisible(true);
        
        while(TastierinoJFrame.isAperto()) 
        {
            Thread.sleep(1000);
        }
        return TastierinoJFrame.isCorretto();
    }
}
