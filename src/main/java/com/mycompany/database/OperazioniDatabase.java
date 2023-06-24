/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.database;

import com.mycompany.tipi.Stanza;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabri
 */
public class OperazioniDatabase {
    private static Connection con;
    private static final List<Integer> idStanze = new ArrayList<>();
    private static final List<String> nomiStanze = new ArrayList<>();
    private static final List<String> descrizioniStanze = new ArrayList<>();
    private static final List<String> osservazioni = new ArrayList<>();
    private static final List<Stanza> stanze = new ArrayList<>();
    
    public static void connettiDatabase() throws SQLException {
        OperazioniDatabase.con = DatabaseInit.getConnection();
    }
    
    public static void creaTabella() throws SQLException {
        String query = "create table STANZE " + "(ID_STANZA tinyint unsigned NOT NULL, " +
                "NOME varchar(50), " + "DESCRIZIONE varchar(1000), " + "OSSERVA varchar(3000), " +
                "PRIMARY KEY(ID_STANZA))";
        
        try(Statement stmt = con.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("Errore creazione tabella Stanze");
        }
    }

    public static void popolaTabella() throws SQLException {
        try(Statement stmt = con.createStatement()) {
            stmt.executeUpdate("""
                               insert into STANZE values(0, 'Soggiorno', 'Il soggiorno della casa di Joel. Uno dei pochi posti ancora sicuri', 'Sul tavolo del soggiorno puoi vedere una pistola, un coltello, una bottiglia di vetro e delle scatolette di cibo.\n
                                Inoltre non puoi fare a meno di notare una foto appoggiata sul mobile vicino.\n
                                Ad est c''è la porta del bagno, a sud quella del ripostiglio. A nord c''è la porta principale.')""");
            
            stmt.executeUpdate("""
                               insert into STANZE values(1, 'Bagno', 'Il bagno. Non in gran condizioni, ma potrebbe esserci qualcosa di utile', 'Sotto lo specchio sporco c''è un mobiletto.
                                Andando ad ovest torneresti nel soggiorno. ')""");
            
            stmt.executeUpdate("""
                               insert into STANZE values(2, 'Ripostiglio', 'Un ripostiglio impolverato.', 'Ci sono due scaffali, sopra di essi dei pacchetti di munizioni per la pistola e una confezione di batterie.
                                Andando a nord torneresti in soggiorno.')""");
            
            stmt.executeUpdate("""
                               insert into STANZE values(3, 'Corridoio', 'Un corridodio del passaggio segreto', 'Noti che nel corridoio c''è una guardia che pattuglia la zona. Attorno a te c''è un grande masso dietro cui nascondersi.
                               Noti dritto davanti a te, alla fine del corridoio, un cancello')""");
            
            stmt.executeUpdate("""
                               insert into STANZE values(4, 'Cancello', 'La stanza del cancello, l'uscita dalla ZQ', 'Affianco al cancello c''è un tastierino numerico, sembra avrai bisogno di un qualche codice. Il cancello è inoltre privo di corrente.
                                Guardando ad est vedi una porta aperta verso una stanza. Dietro di te c''è il corridoio')""");
            
            stmt.executeUpdate("""
                               insert into STANZE values(5, 'QuadroElettrico', 'Una stanza con un quadro elettrico ed una leva abbassata', 'Osservi che forse qui potresti far tornare la corrente al cancello. Ma non è tutto, perchè guardando bene noti che attaccato alla parete superiore del quadro elettrico, quasi nascosto, c''è un foglietto con una scritta.
                                Andando ad ovest torneresti al cancello.')""");
            
            stmt.executeUpdate("""
                               insert into STANZE values(6, 'IngressoMetro', 'L'ingresso della metropolitana', 'Noti il corpo esanime di una guardia, ormai consumato dal tempo. Sembra avere qualcosa addosso...
                               Guardando bene noti che addosso ha una torcia con delle pile.
                                Andando avanti a nord si scende giù')""");
            
            stmt.executeUpdate("""
                               insert into STANZE values(7, 'BinariMetro', 'I binari della metropolitana, c''è un problema però...', 'La metropolitana è completamente allagata e l''acqua ti arriva ad altezza petto.
                               Ad ovest sembra esserci una stanzetta.')""");
            
            stmt.executeUpdate("insert into STANZE " + 
                               "values(8, 'StanzaZattera', 'Uno stanzino della metropolitana, completamente buio', 'Non si vede niente!')");
            
            stmt.executeUpdate("insert into STANZE " + 
                               "values(9, 'IngressoOspedale', 'L'ingresso del Saint Mary's Hospital, QG delle Luci', ' ')");
            
            stmt.executeUpdate("""
                               insert into STANZE values(10, 'DentroOspedale', 'Una stanza dentro l'ospedale..', 'Il corpo morto di Marlene giace per terra. Nello scontro ha lasciato cadere una chiave.
                                Sia ad est che ad ovest ci sono delle stanze.')""");
            
            stmt.executeUpdate("""
                               insert into STANZE values(11, 'Magazzino', 'Una stanza usata come magazzino.', 'Noti un armadietto chiuso a chiave, appoggiato ad una parete.
                                Andando ad est torneresti nella stanza precedente.')""");
            
            stmt.executeUpdate("""
                               insert into STANZE values(12, 'Infermeria', 'Un'infermeria un po' spoglia', 'Ci sono due tavoli e uno scaffale, ma sono praticamente vuoti.
                                Ci trovi solamente una bottiglia d''alcol e delle garze.
                                Andando ad ovest torneresti nella stanza precedente.')""");
            
            stmt.executeUpdate("""
                               insert into STANZE values(13, 'PianoSala', 'Il piano dell'ospedale dove c''è la sala operatoria', 'Davanti a te c''è un corridoio con una guardia ben armata, non c''è modo di attraversarlo senza farsi vedere.
                               Però ad est c''è qualcosa di interessante, mentre ad ovest una stanzina aperta.
                               Inoltre, per terra trovi un documento medico.')""");
            
            stmt.executeUpdate("""
                               insert into STANZE values(14, 'Condotto', 'C''è quella che sembra una grata di un condotto dell'aria molto largo', 'La grata è fermamente salda, ci sono delle viti.
                               Andando ad ovest torneresti nella stanza precedente.')""");
            
            stmt.executeUpdate("insert into STANZE " + 
                               "values(15, 'StanzaCacciavite', 'Uno stanzino buio', 'Non si vede niente')");
            
            stmt.executeUpdate("insert into STANZE " + 
                               "values(16, 'SalaOperatoria', 'La sala operatoria, c'è un tavolo operatorio e dei dottori al lavoro', 'Guardi bene il tavolo e.... è Ellie!')");
        } catch (SQLException ex) {
            System.out.println("Errore popolamento tabella");
        }
    }
    
    public static void caricaDatiStanze() throws SQLException {
        String query = "select ID_STANZA, NOME, DESCRIZIONE, OSSERVA from STANZE";
        try(Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                idStanze.add(rs.getInt("ID_STANZA"));
                nomiStanze.add(rs.getString("NOME"));
                descrizioniStanze.add(rs.getString("DESCRIZIONE"));
                osservazioni.add(rs.getString("OSSERVA"));
            }
        } catch(SQLException ex) {
            System.out.println("Errore caricamento dati stanze");
        }
    }
    
    public static List<Stanza> creaStanze() {
        Stanza soggiornoCasa = new Stanza(idStanze.get(0), nomiStanze.get(0), descrizioniStanze.get(0));
        soggiornoCasa.setOsservazione(osservazioni.get(0));
        
        Stanza bagnoCasa = new Stanza(idStanze.get(1), nomiStanze.get(1), descrizioniStanze.get(1));
        bagnoCasa.setOsservazione(osservazioni.get(1));
        
        Stanza ripostiglioCasa = new Stanza(idStanze.get(2), nomiStanze.get(2), descrizioniStanze.get(2));
        ripostiglioCasa.setOsservazione(osservazioni.get(2));
        
        Stanza corridoioPassaggio = new Stanza(idStanze.get(3), nomiStanze.get(3), descrizioniStanze.get(3));
        corridoioPassaggio.setOsservazione(osservazioni.get(3));
        
        Stanza cancello = new Stanza(idStanze.get(4), nomiStanze.get(4), descrizioniStanze.get(4));
        cancello.setOsservazione(osservazioni.get(4));
        
        Stanza stanzaQuadroElettrico = new Stanza(idStanze.get(5), nomiStanze.get(5), descrizioniStanze.get(5));
        stanzaQuadroElettrico.setOsservazione(osservazioni.get(5));
        
        Stanza ingressoMetro = new Stanza(idStanze.get(6), nomiStanze.get(6), descrizioniStanze.get(6));
        ingressoMetro.setOsservazione(osservazioni.get(6));
        
        Stanza binariMetro = new Stanza(idStanze.get(7), nomiStanze.get(7), descrizioniStanze.get(7));
        binariMetro.setOsservazione(osservazioni.get(7));
        
        Stanza stanzaZattera = new Stanza(idStanze.get(8), nomiStanze.get(8), descrizioniStanze.get(8));
        stanzaZattera.setOsservazione(osservazioni.get(8));
        stanzaZattera.setVisibile(false);
        
        Stanza ingressoOspedale = new Stanza(idStanze.get(9), nomiStanze.get(9), descrizioniStanze.get(9));
        ingressoOspedale.setOsservazione(osservazioni.get(9));
        
        Stanza dentroOspedale = new Stanza(idStanze.get(10), nomiStanze.get(10), descrizioniStanze.get(10));
        dentroOspedale.setOsservazione(osservazioni.get(10));
        
        Stanza magazzino = new Stanza(idStanze.get(11), nomiStanze.get(11), descrizioniStanze.get(11));
        magazzino.setOsservazione(osservazioni.get(11));
        
        Stanza infermeria = new Stanza(idStanze.get(12), nomiStanze.get(12), descrizioniStanze.get(12));
        infermeria.setOsservazione(osservazioni.get(12));
        
        Stanza pianoSalaOperatoria = new Stanza(idStanze.get(13), nomiStanze.get(13), descrizioniStanze.get(13));
        pianoSalaOperatoria.setOsservazione(osservazioni.get(13));
        
        Stanza condotto = new Stanza(idStanze.get(14), nomiStanze.get(14), descrizioniStanze.get(14));
        condotto.setOsservazione(osservazioni.get(14));
        
        Stanza stanzaCacciavite = new Stanza(idStanze.get(15), nomiStanze.get(15), descrizioniStanze.get(15));
        stanzaCacciavite.setOsservazione(osservazioni.get(15));
        stanzaCacciavite.setVisibile(false);
        
        Stanza salaOperatoria = new Stanza(idStanze.get(16), nomiStanze.get(16), descrizioniStanze.get(16));
        salaOperatoria.setOsservazione(osservazioni.get(16));
        
        soggiornoCasa.setEst(bagnoCasa);
        soggiornoCasa.setSud(ripostiglioCasa);
        bagnoCasa.setOvest(soggiornoCasa);
        ripostiglioCasa.setNord(soggiornoCasa);
        soggiornoCasa.setNord(corridoioPassaggio);
        corridoioPassaggio.setNord(cancello);
        cancello.setSud(corridoioPassaggio);
        cancello.setEst(stanzaQuadroElettrico);
        stanzaQuadroElettrico.setOvest(cancello);
        ingressoMetro.setNord(binariMetro);
        binariMetro.setSud(ingressoMetro);
        binariMetro.setOvest(stanzaZattera);
        stanzaZattera.setEst(binariMetro);
        dentroOspedale.setOvest(magazzino);
        magazzino.setEst(dentroOspedale);
        infermeria.setOvest(dentroOspedale);
        pianoSalaOperatoria.setEst(condotto);
        condotto.setOvest(pianoSalaOperatoria);
        pianoSalaOperatoria.setOvest(stanzaCacciavite);
        stanzaCacciavite.setEst(pianoSalaOperatoria);
        
        
        
        stanze.add(soggiornoCasa);
        stanze.add(bagnoCasa);
        stanze.add(ripostiglioCasa);
        stanze.add(corridoioPassaggio);
        stanze.add(cancello);
        stanze.add(stanzaQuadroElettrico);
        stanze.add(ingressoMetro);
        stanze.add(binariMetro);
        stanze.add(stanzaZattera);
        stanze.add(ingressoOspedale);
        stanze.add(dentroOspedale);
        stanze.add(magazzino);
        stanze.add(infermeria);
        stanze.add(pianoSalaOperatoria);
        stanze.add(condotto);
        stanze.add(stanzaCacciavite);
        stanze.add(salaOperatoria);
        
        return stanze;
    }
}
