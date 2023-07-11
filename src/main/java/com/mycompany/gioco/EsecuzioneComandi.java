/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gioco;
import com.mycompany.avventura.LoaderPrinterCharacterStream;
import static com.mycompany.gioco.BehaviourController.mostraDialogoStanza;
import com.mycompany.swing.DocumentFrame;
import com.mycompany.swing.TastierinoJFrame;
import com.mycompany.tipi.ContenitoreOggetti;
import com.mycompany.tipi.OggettoImmagine;
import com.mycompany.tipi.Oggetto;
import com.mycompany.tipi.OggettoFoglietto;
import com.mycompany.tipi.Stanza;
import java.awt.Font;
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
    void chiudiPartita() 
    {
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
        //attacco nella stanza della zattera
        if(stanzacorrente.getNome().equalsIgnoreCase("Stanza Zattera")) 
        {
            //diloghi attacco
            mostraDialogoStanza(".//the_last_of_us(storia)//Dialoghi//joel_attacca.txt");
        }
        //..altri casi
        else 
        {
            System.out.println("Non c'è bisogno di attaccare qui.\n");
        }
    }
    
    
    //Questo metodo simula il nascondersi nel gioco
    void nasconditi(Stanza stanzacorrente) throws UnsupportedEncodingException 
    {
        //nascondiglio nel passaggio segreto
        if(stanzacorrente.getNome().equalsIgnoreCase("Corridoio passaggio segreto")) 
        {
            System.out.println("Ti sei nascosto, attendi il momento migliore per fuggire.");
            
            //dialoghi dietro la roccia 
            mostraDialogoStanza(".//the_last_of_us(storia)//Dialoghi//Nascondiglio_roccia.txt");                   

            //dialoghi appena le guardie sono passate
            mostraDialogoStanza(".//the_last_of_us(storia)//Dialoghi//Dopo_nascondiglio_roccia.txt");     
        }
        //..altri casi
        else 
        {
            System.out.println("Non c'è bisogno di nascondersi qui.\n");
        }
    }

    
    boolean checkNordAccess(Stanza stanzacorrente, List<Oggetto> inventarioGiocatore, List<Stanza> listaStanze)
    {
        return BehaviourController.checkAccessoStanza(listaStanze.get(stanzacorrente.getNord().getId()), inventarioGiocatore);    
    }
    
    
    boolean checkSudAccess(Stanza stanzacorrente, List<Oggetto> inventarioGiocatore, List<Stanza> listaStanze)
    {
        return BehaviourController.checkAccessoStanza(listaStanze.get(stanzacorrente.getSud().getId()), inventarioGiocatore);    
    }
    
    
    boolean checkEstAccess(Stanza stanzacorrente, List<Oggetto> inventarioGiocatore, List<Stanza> listaStanze)
    {
        return BehaviourController.checkAccessoStanza(listaStanze.get(stanzacorrente.getEst().getId()), inventarioGiocatore);    
    }
    
    
    boolean checkWestAccess(Stanza stanzacorrente, List<Oggetto> inventarioGiocatore, List<Stanza> listaStanze)
    {
        return BehaviourController.checkAccessoStanza(listaStanze.get(stanzacorrente.getOvest().getId()), inventarioGiocatore);    
    }
    
    
    void stampaContenutoInventario(List<Oggetto> inventarioGiocatore)
    {
        System.out.println("\nNel tuo inventario ci sono:");
        
        for (Oggetto o : inventarioGiocatore) 
        {
            if(!o.isInvisibile()) 
            {
                System.out.println(o.getNome() + " : " + o.getDescrizione());
            }
        }
        System.out.println("\n");
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
        if(oggetto != null)
        {
            if (oggetto.isSpingibile()) 
            {
               //se l'oggetto è una leva
               if (oggetto.getId() == 14) 
               {
                   //e la leve non è stata già spinta
                   if(!inventarioGiocatore.contains(oggetto))
                   {
                       System.out.println("Hai premuto: " + oggetto.getNome());
                       inventarioGiocatore.add(oggetto);
                       stanzaCorrente.getOggetti().remove(oggetto);
                   }

                   for(Oggetto o : stanzaCorrente.getOvest().getOggetti()) 
                   {
                       if (o.getNome().equalsIgnoreCase("tastierino")) 
                       {
                           o.setUsabile(true);//puoi usare il tastierino perchè hai spinto la leva
                       }
                   }
               }
               //..altri oggetti spingibili
            } 
            else 
            {
                System.out.println("Non ci sono oggetti che puoi premere qui.");
            }
        }
        else
        {
            System.out.println("Nessun Oggetto da spingere.");
        }
    }
    
    
    void leggiOggetto(Oggetto oggetto)
    {
        if(oggetto != null)
        {
            if (oggetto.isLeggibile())
            {
                    System.out.print(oggetto.getContenuto());

            } 
            else 
            {
                System.out.println("Non ci sono oggetti che puoi leggere qui.");
            }
        }
        else
        {
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
    
    
    //questo metodo è usato per aprire il manuale utente quando il giocatore lancia il comando help
    void help()
    {
        LoaderPrinterCharacterStream loader = new LoaderPrinterCharacterStream();
        DocumentFrame manualeUtente = new DocumentFrame("Manuale utente",loader.ottieniComeTesto(".//resources//istruzioniGioco.txt"));
        manualeUtente.getTextLabel().setFont(new Font("Press Gothic", Font.BOLD, 15));
        manualeUtente.setVisible(true);
    }
    
    //Questo metodo permette di gestire il comando usa NomeoOggetto
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
                        boolean tastierinoUsato = false;
                        
                        //ricerco nell'inventario l'oggetto che indica che ho usato il tastierino
                        for(Oggetto o: inventarioGiocatore)
                        {
                            //se presente
                            if(o.getNome().equalsIgnoreCase("tastierinoUsato"))
                            {
                                tastierinoUsato = true;
                            }
                        }
                        
                        //se non hai mai indovinato la combinazione del tastierino
                        if(!tastierinoUsato)
                        {
                            //prova ad indovinare la combinazione
                            if(usaTastierino()) 
                            {
                                System.out.println("dddd");
                                Oggetto o = new Oggetto(25);
                                o.setNome("tastierinoUsato");
                                o.setAlias(new String[]{""});
                                o.setInvisibile(true);
                                inventarioGiocatore.add(o);

                                System.out.println("Joel:  Bene, ora potrò oltrepassare il cancello\n");
                            }
                        }
                        else
                        {
                            System.out.println("Il cancello è già aperto, prosegui.\n");
                        }                      
                    }
                    
                    
                    //cacciavite
                    case 21 ->
                    {
                        boolean cacciaviteUsato = false;
                        
                        //ricerco nell'inventario l'oggetto che indica che ho usato il tastierino
                        for(Oggetto o: inventarioGiocatore)
                        {
                            //se presente
                            if(o.getNome().equalsIgnoreCase("cacciaviteUsato"))
                            {
                                cacciaviteUsato = true;
                            }
                        }
                        
                        //se il cacciavite non è stato mai usato
                        if(!cacciaviteUsato)
                        {
                            //verifca se l'inventario contiene il cacciavite
                            if(inventarioGiocatore.contains(oggetto))
                            {
                                //se l'utente vuole usare il cacciavite nel sistema di ventilazione
                                if(stanzacorrente.getNome().equalsIgnoreCase("Sistema di ventilazione"))
                                {
                                    //apri la grata nel sistema di ventilazione
                                    Oggetto o = new Oggetto(26);
                                    o.setNome("cacciaviteUsato");
                                    o.setAlias(new String[]{""});
                                    inventarioGiocatore.add(o);

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
                        else
                        {
                            System.out.println("La grata è già aperta, prosegui.");
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
