/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.database;

import com.mycompany.tipi.ContenitoreOggetti;
import com.mycompany.tipi.Oggetto;
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
    private static List<Integer> idStanze = new ArrayList<>();
    private static List<String> nomiStanze = new ArrayList<>();
    private static List<String> descrizioniStanze = new ArrayList<>();
    private static List<String> osservazioni = new ArrayList<>();
    private static List<Stanza> stanze = new ArrayList<>();
    private static List<Integer> idOggetti = new ArrayList<>();
    private static List<String> nomiOggetti = new ArrayList<>();
    private static List<String> descrizioniOggetti = new ArrayList<>();
    private static List<String> contenutoOggetti = new ArrayList<>();
    private static List<Oggetto> oggetti = new ArrayList<>();
    private static List<Integer> stanzaOggetto = new ArrayList<>();
    
    public static void connettiDatabase() throws SQLException {
        OperazioniDatabase.con = DatabaseInit.getConnection();
    }
    
    public static void creaTabelle() throws SQLException {
        String query = "create table if not exists STANZE " + "(ID_STANZA int NOT NULL, " +
                "NOME varchar(50), " + "DESCRIZIONE varchar(1000), " + "OSSERVA varchar(3000), " +
                "PRIMARY KEY(ID_STANZA))";
        
        String query2 = "create table if not exists OGGETTI " + "(ID_OGGETTO int NOT NULL, " +
                "NOME varchar(50), " + "DESCRIZIONE varchar(200), " + "CONTENUTO varchar(1000), STANZA int, " +
                "PRIMARY KEY(ID_OGGETTO), FOREIGN KEY(STANZA) REFERENCES STANZE(ID_STANZA) )";
        
        try(Statement stmt = con.createStatement()) {
            stmt.executeUpdate(query);
            stmt.executeUpdate(query2);
        } catch (SQLException ex) {
            System.err.println("Errore creazione tabelle:\n");
            System.err.print(ex.getErrorCode() + "\n");
            System.err.print(ex.getSQLState() +"\n");
            System.err.print(ex.getMessage()+ "\n");
        }
    }

    public static void popolaTabellaStanze() throws SQLException {
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
                               insert into STANZE values(4, 'Cancello', 'La stanza del cancello, l''uscita dalla ZQ', 'Affianco al cancello c''è un tastierino numerico, sembra avrai bisogno di un qualche codice. Il cancello è inoltre privo di corrente.
                                Guardando ad est vedi una porta aperta verso una stanza. Dietro di te c''è il corridoio')""");
            
            stmt.executeUpdate("""
                               insert into STANZE values(5, 'QuadroElettrico', 'Una stanza con un quadro elettrico ed una leva abbassata', 'Osservi che forse qui potresti far tornare la corrente al cancello. Ma non è tutto, perchè guardando bene noti che attaccato alla parete superiore del quadro elettrico, quasi nascosto, c''è un foglietto con una scritta.
                                Andando ad ovest torneresti al cancello.')""");
            
            stmt.executeUpdate("""
                               insert into STANZE values(6, 'IngressoMetro', 'L''ingresso della metropolitana', 'Noti il corpo esanime di una guardia, ormai consumato dal tempo. Sembra avere qualcosa addosso...
                               Guardando bene noti che addosso ha una torcia con delle pile.
                                Andando avanti a nord si scende giù')""");
            
            stmt.executeUpdate("""
                               insert into STANZE values(7, 'BinariMetro', 'I binari della metropolitana, c''è un problema però...', 'La metropolitana è completamente allagata e l''acqua ti arriva ad altezza petto.
                               Ad ovest sembra esserci una stanzetta.')""");
            
            stmt.executeUpdate("insert into STANZE " + 
                               "values(8, 'StanzaZattera', 'Uno stanzino della metropolitana, completamente buio', 'Non si vede niente!')");
            
            stmt.executeUpdate("insert into STANZE " + 
                               "values(9, 'IngressoOspedale', 'L''ingresso del Saint Mary''s Hospital, QG delle Luci', ' ')");
            
            stmt.executeUpdate("""
                               insert into STANZE values(10, 'DentroOspedale', 'Una stanza dentro l''ospedale..', 'Il corpo morto di Marlene giace per terra. Nello scontro ha lasciato cadere una chiave.
                                Sia ad est che ad ovest ci sono delle stanze.')""");
            
            stmt.executeUpdate("""
                               insert into STANZE values(11, 'Magazzino', 'Una stanza usata come magazzino.', 'Noti un armadietto chiuso a chiave, appoggiato ad una parete.
                                Andando ad est torneresti nella stanza precedente.')""");
            
            stmt.executeUpdate("""
                               insert into STANZE values(12, 'Infermeria', 'Un''infermeria un po'' spoglia', 'Ci sono due tavoli e uno scaffale, ma sono praticamente vuoti.
                                Ci trovi solamente una bottiglia d''alcol e delle garze.
                                Andando ad ovest torneresti nella stanza precedente.')""");
            
            stmt.executeUpdate("""
                               insert into STANZE values(13, 'PianoSala', 'Il piano dell''ospedale dove c''è la sala operatoria', 'Davanti a te c''è un corridoio con una guardia ben armata, non c''è modo di attraversarlo senza farsi vedere.
                               Però ad est c''è qualcosa di interessante, mentre ad ovest una stanzina aperta.
                               Inoltre, per terra trovi un documento medico.')""");
            
            stmt.executeUpdate("""
                               insert into STANZE values(14, 'Condotto', 'C''è quella che sembra una grata di un condotto dell''aria molto largo', 'La grata è fermamente salda, ci sono delle viti.
                               Andando ad ovest torneresti nella stanza precedente.')""");
            
            stmt.executeUpdate("insert into STANZE " + 
                               "values(15, 'StanzaCacciavite', 'Uno stanzino buio', 'Non si vede niente')");
            
            stmt.executeUpdate("insert into STANZE " + 
                               "values(16, 'SalaOperatoria', 'La sala operatoria, c''è un tavolo operatorio e dei dottori al lavoro', 'Guardi bene il tavolo e.... è Ellie!')");
        } catch (SQLException ex) {
            System.err.println("\nErrore popolamento tabella stanze\n");
            System.err.print(ex.getErrorCode() + "\n");
            System.err.print(ex.getSQLState() + "\n");
            System.err.print(ex.getMessage() + "\n");
        }
    }
    
    public static void popolaTabellaOggetti() throws SQLException {
        //manca l'alcol e le garze in infermeria
        try(Statement stmt = con.createStatement()) {
            stmt.executeUpdate("insert into OGGETTI " +
                               "values(0, 'pistola', 'Una pistola 9mm', null, 0)");
            
            stmt.executeUpdate("insert into OGGETTI " +
                               "values(1, 'coltello', 'Un coltello da caccia', null, 0)");
            
            stmt.executeUpdate("insert into OGGETTI " +
                               "values(2, 'bottiglia', 'Una bottiglia di vetro vuota', null, 0)");
            
            stmt.executeUpdate("insert into OGGETTI " +
                               "values(3, 'cibo', 'Una scatoletta di cibo, ancora buono(forse)', null, 0)");
            
            stmt.executeUpdate("insert into OGGETTI " +
                               "values(4, 'foto', 'Una foto di te con tua figlia. Un ricordo di ciò che non c''è più', null, 0)");
            
            stmt.executeUpdate("insert into OGGETTI " +
                               "values(5, 'mobile', 'Un mobiletto da bagno. Chissà cosa c''è dentro..', null, 1)");
            
            stmt.executeUpdate("insert into OGGETTI " +
                               "values(6, 'garza', 'Una garza sterile(più o meno)', null, null)");
            
            stmt.executeUpdate("insert into OGGETTI " +
                               "values(7, 'alcol', 'Una bottiglia di alcol etilico', null, null)");
            
            stmt.executeUpdate("insert into OGGETTI " +
                               "values(8, 'forbici', 'Un paio di forbici dalla punta decisamente non arrotondata', null, null)");
            
            stmt.executeUpdate("insert into OGGETTI " +
                               "values(9, 'munizioni', 'Un pacco di munizioni 9mm per la pistola. Io le prenderei..', null, 2)");
            
            stmt.executeUpdate("insert into OGGETTI " +
                               "values(10, 'batterie', 'Un pacco di batterie, forse per una torcia', null, 2)");
            
            stmt.executeUpdate("insert into OGGETTI " +
                               "values(11, 'roccia', 'Una grande roccia, più grande di te', null, 3)");
            
            stmt.executeUpdate("insert into OGGETTI " +
                               "values(12, 'tastierino', 'Il tastierino numerico per aprire il cancello', null, 4)");
            
            stmt.executeUpdate("insert into OGGETTI " +
                               "values(13, 'fogliettoQuadroElettrico', 'Un foglio con sopra un enigma riguardante un codice', 'CONTENUTO FOGLIO ENIGMA', 5)");
            
            stmt.executeUpdate("insert into OGGETTI " +
                               "values(14, 'leva', 'Una leva, forse per riattivare il quadro elettrico', null, 5)");
            
            stmt.executeUpdate("insert into OGGETTI " +
                               "values(15, 'torcia', 'Una torcia, tornerà utile prima o poi', null, 6)");
            
            stmt.executeUpdate("insert into OGGETTI " +
                               "values(16, 'documentoMetro', 'Un documento', 'CONTENUTO DOCUMENTO METROPOLITANA', 7)");
            
            stmt.executeUpdate("insert into OGGETTI " +
                               "values(17, 'zattera', 'Assi di legno a mo'' di zattera. Abbastanza da reggere una ragazzina', null, 8)");
            
            stmt.executeUpdate("insert into OGGETTI " +
                               "values(18, 'chiaveArmadietto', 'Una chiave, non sai bene cosa apre', null, 10)");
            
            stmt.executeUpdate("insert into OGGETTI " +
                               "values(19, 'armadietto', 'Un armadietto chiuso a chiave', null, 11)");
            
            stmt.executeUpdate("insert into OGGETTI " +
                               "values(20, 'tesserino', 'Un tesserino con scritto ''Infermeria''', null, null)");
            
            stmt.executeUpdate("insert into OGGETTI " +
                               "values(21, 'cacciavite', 'Un cacciavite', null, 15)");
            
            stmt.executeUpdate("insert into OGGETTI " +
                               "values(22, 'documentoMedico', 'Un documento medico', 'CONTENUTO DOCUMENTO MEDICO OSPEDALE', 13)");
            
            stmt.executeUpdate("insert into OGGETTI " +
                               "values(23, 'grata', 'Una grata, chiusa con delle viti', null, 14)");
        }
    }
    
    public static void caricaDati() throws SQLException {
        String query = "select ID_STANZA, NOME, DESCRIZIONE, OSSERVA from STANZE";
        String query2 = "select ID_OGGETTO, NOME, DESCRIZIONE, CONTENUTO, STANZA from OGGETTI";
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
        
        try(Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query2);
            while(rs.next()) {
                idOggetti.add(rs.getInt("ID_OGGETTO"));
                nomiOggetti.add(rs.getString("NOME"));
                descrizioniOggetti.add(rs.getString("DESCRIZIONE"));
                contenutoOggetti.add(rs.getString("CONTENUTO"));
                stanzaOggetto.add(rs.getInt("STANZA"));
            }
        } catch(SQLException ex) {
            System.out.println("Errore caricamento dati oggetti");
        }
    }
    
    public static void creaStanze() {
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
        
    }
    
    public static List<Stanza> creaOggetti() {
        //IN ATTESA DI TROVARE UN METODO MIGLIORE PER METTERE GLI OGGETTI NELLE STANZE
        //forse cambiare l'index in stanze.get mettendoci la colonna 'stanza' della tabella oggetti pigliandola con qualche query
        //insomma, qualcosa che non sia mettere manualmente l'index della stanza in stanze.get
        Oggetto pistola = new Oggetto(idOggetti.get(0), nomiOggetti.get(0), descrizioniOggetti.get(0));
        pistola.setAlias(new String[] {"arma"});
        stanze.get(0).getOggetti().add(pistola);
        
        Oggetto coltello = new Oggetto(idOggetti.get(1), nomiOggetti.get(1), descrizioniOggetti.get(1));
        coltello.setAlias(new String[]{"lama"});
        stanze.get(0).getOggetti().add(coltello);

        Oggetto bottigliaVuota = new Oggetto(idOggetti.get(2), nomiOggetti.get(2), descrizioniOggetti.get(2));
        bottigliaVuota.setAlias(new String[]{});
        stanze.get(0).getOggetti().add(bottigliaVuota);

        Oggetto scatolettaCibo = new Oggetto(idOggetti.get(3), nomiOggetti.get(3), descrizioniOggetti.get(3));
        scatolettaCibo.setAlias(new String[] {"scatoletta", "lattina"});
        stanze.get(0).getOggetti().add(scatolettaCibo);

        Oggetto foto = new Oggetto(idOggetti.get(4), nomiOggetti.get(4), descrizioniOggetti.get(4));
        foto.setAlias(new String[]{"immagine"});
        foto.setPrendibile(false);
        stanze.get(0).getOggetti().add(foto);

        ContenitoreOggetti mobileBagno = new ContenitoreOggetti(idOggetti.get(5), nomiOggetti.get(5), descrizioniOggetti.get(5));
        mobileBagno.setAlias(new String[]{"mobiletto"});
        mobileBagno.setApribile(true);
        mobileBagno.setPrendibile(false);
        mobileBagno.setAperto(false);
        stanze.get(1).getOggetti().add(mobileBagno);

        Oggetto garza = new Oggetto(idOggetti.get(6), nomiOggetti.get(6), descrizioniOggetti.get(6));
        garza.setAlias(new String[]{"garze"});        
        Oggetto alcol = new Oggetto(idOggetti.get(7), nomiOggetti.get(7), descrizioniOggetti.get(7));
        alcol.setAlias(new String[]{"alcol etilico", "etilico", "alcool"});
        Oggetto forbici = new Oggetto(idOggetti.get(8), nomiOggetti.get(8), descrizioniOggetti.get(8));
        forbici.setAlias(new String[]{"forbice"});
        mobileBagno.add(garza);
        mobileBagno.add(alcol);
        mobileBagno.add(forbici);
        Oggetto munizioni = new Oggetto(idOggetti.get(9), nomiOggetti.get(9), descrizioniOggetti.get(9));
        munizioni.setAlias(new String[]{"colpi", "pacco", "pacchetto"});
        stanze.get(2).getOggetti().add(munizioni);

        Oggetto batterie = new Oggetto(idOggetti.get(10), nomiOggetti.get(10), descrizioniOggetti.get(10));
        batterie.setAlias(new String[]{"batteria"});
        stanze.get(2).getOggetti().add(batterie);

        Oggetto roccia = new Oggetto(idOggetti.get(11), nomiOggetti.get(11), descrizioniOggetti.get(11));
        roccia.setAlias(new String[]{"masso"});
        roccia.setPrendibile(false);
        stanze.get(3).getOggetti().add(roccia);

        Oggetto tastierino = new Oggetto(idOggetti.get(12), nomiOggetti.get(12), descrizioniOggetti.get(12));
        tastierino.setAlias(new String[]{});        
        tastierino.setPrendibile(false);
        stanze.get(4).getOggetti().add(tastierino);

        Oggetto foglioQuadro = new Oggetto(idOggetti.get(13), nomiOggetti.get(13), descrizioniOggetti.get(13));
        foglioQuadro.setLeggibile(true);
        foglioQuadro.setAlias(new String[]{"foglio enigma", "foglietto", "enigma"});
        foglioQuadro.setContenuto("CONTENUTO FOGLIO ENIGMA");
        stanze.get(5).getOggetti().add(foglioQuadro);

        Oggetto levaCorrente = new Oggetto(idOggetti.get(14), nomiOggetti.get(14), descrizioniOggetti.get(14));
        levaCorrente.setAlias(new String[]{});
        levaCorrente.setPrendibile(false);
        stanze.get(5).getOggetti().add(levaCorrente);

        Oggetto torcia = new Oggetto(idOggetti.get(15), nomiOggetti.get(15), descrizioniOggetti.get(15));
        torcia.setAlias(new String[]{});
        stanze.get(6).getOggetti().add(torcia);

        Oggetto documentoMetro = new Oggetto(idOggetti.get(16), nomiOggetti.get(16), descrizioniOggetti.get(16));
        documentoMetro.setAlias(new String[]{"documento", "documento metro", "lettera"});
        documentoMetro.setLeggibile(true);
        documentoMetro.setContenuto("CONTENUTO DOCUMENTO METROPOLITANA");
        stanze.get(7).getOggetti().add(documentoMetro);

        Oggetto zattera = new Oggetto(idOggetti.get(17), nomiOggetti.get(17), descrizioniOggetti.get(17));
        zattera.setAlias(new String[]{"legno"});
        stanze.get(8).getOggetti().add(zattera);

        Oggetto chiaveArmadietto = new Oggetto(idOggetti.get(18), nomiOggetti.get(18), descrizioniOggetti.get(18));
        chiaveArmadietto.setAlias(new String[]{"chiave", "chiave armadietto"});
        stanze.get(10).getOggetti().add(chiaveArmadietto);

        ContenitoreOggetti armadietto = new ContenitoreOggetti(idOggetti.get(19), nomiOggetti.get(19), descrizioniOggetti.get(19));
        armadietto.setAlias(new String[]{});
        armadietto.setApribile(false);
        armadietto.setPrendibile(false);
        armadietto.setAperto(false);
        armadietto.add(forbici);
        Oggetto tesserino = new Oggetto(idOggetti.get(20), nomiOggetti.get(20), descrizioniOggetti.get(20));
        tesserino.setAlias(new String[]{"tessera"});
        armadietto.add(tesserino);
        stanze.get(11).getOggetti().add(armadietto);

        Oggetto cacciavite = new Oggetto(idOggetti.get(21), nomiOggetti.get(21), descrizioniOggetti.get(21));
        cacciavite.setAlias(new String[]{});
        stanze.get(15).getOggetti().add(cacciavite);

        Oggetto documentoMedico = new Oggetto(idOggetti.get(22), nomiOggetti.get(22), descrizioniOggetti.get(22));
        documentoMedico.setAlias(new String[]{"referto", "documento medico"});
        documentoMedico.setLeggibile(true);
        documentoMedico.setContenuto("CONTENUTO DOCUMENTO MEDICO OSPEDALE");
        stanze.get(13).getOggetti().add(documentoMedico);

        Oggetto grata = new Oggetto(idOggetti.get(23), nomiOggetti.get(23), descrizioniOggetti.get(23));
        grata.setAlias(new String[]{});
        grata.setPrendibile(false);
        grata.setSpingibile(false);
        grata.setApribile(false);
        stanze.get(14).getOggetti().add(grata);
        
        oggetti.add(pistola);
        oggetti.add(coltello);
        oggetti.add(bottigliaVuota);
        oggetti.add(scatolettaCibo);
        oggetti.add(foto);
        oggetti.add(mobileBagno);
        oggetti.add(garza);
        oggetti.add(alcol);
        oggetti.add(forbici);
        oggetti.add(munizioni);
        oggetti.add(batterie);
        oggetti.add(roccia);
        oggetti.add(tastierino);
        oggetti.add(foglioQuadro);
        oggetti.add(levaCorrente);
        oggetti.add(torcia);
        oggetti.add(documentoMetro);
        oggetti.add(zattera);
        oggetti.add(chiaveArmadietto);
        oggetti.add(tesserino);
        oggetti.add(cacciavite);
        oggetti.add(documentoMedico);
        oggetti.add(grata);
        
        return stanze;        
    }
    
    public static void resetDatabase() throws SQLException {
        try(Statement stmt = con.createStatement()) {
            stmt.executeUpdate("DROP ALL OBJECTS");
        } catch(SQLException ex) {
            System.err.println("Errore reset database\n");
            System.err.print(ex.getErrorCode());
            System.err.print(ex.getMessage());
        }
    }
}
