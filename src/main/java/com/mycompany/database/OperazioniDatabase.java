/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.database;

import com.mycompany.tipi.Comando;
import com.mycompany.tipi.TipoComando;
import com.mycompany.tipi.ContenitoreOggetti;
import com.mycompany.tipi.Documento;
import com.mycompany.tipi.OggettoImmagine;
import com.mycompany.tipi.Oggetto;
import com.mycompany.tipi.OggettoFoglietto;
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
    private static List<String> tipoComandi = new ArrayList<>();
    private static List<String> nomeComandi = new ArrayList<>();
    private static List<Comando> comandi = new ArrayList<>();
    
    
    public static void connettiDatabase() throws SQLException {
        OperazioniDatabase.con = DatabaseInit.getConnection();
    }
    
    
    public static void creaTabelle() throws SQLException {
        String query = "create table if not exists STANZE " + "(ID_STANZA int NOT NULL, " +
                "NOME varchar(50), " + "DESCRIZIONE varchar(1000), " + "OSSERVA varchar(3000), " +
                "PRIMARY KEY(ID_STANZA)) as select ID,NOME,DESCRIZIONE,OSSERVA from csvread('./resources/stanze.csv')";
        
        String query2 = "create table if not exists OGGETTI " + "(ID_OGGETTO int NOT NULL, " +
                "NOME varchar(50), " + "DESCRIZIONE varchar(200), " + "CONTENUTO varchar(1000), STANZA int, " +
                "PRIMARY KEY(ID_OGGETTO), FOREIGN KEY(STANZA) REFERENCES STANZE(ID_STANZA) ) " +
                "as select ID,NOME,DESCRIZIONE,CONTENUTO,STANZA from csvread('./resources/oggetti.csv')";
        
        String query3 = "create table if not exists COMANDI " + "(TIPO_COMANDO varchar(50) NOT NULL, " +
                "NOME varchar(50), PRIMARY KEY(TIPO_COMANDO)) as select TIPO,NOME  from csvread('./resources/comandi.csv')";
        
        try(Statement stmt = con.createStatement()) {
            stmt.executeUpdate(query);
            stmt.executeUpdate(query2);
            stmt.executeUpdate(query3);
        } catch (SQLException ex) {
            System.err.println("Errore creazione tabelle:\n");
            System.err.print(ex.getErrorCode() + "\n");
            System.err.print(ex.getSQLState() +"\n");
            System.err.print(ex.getMessage()+ "\n");
        }
    }
    
    
    public static void caricaDati() throws SQLException {
        String query = "select ID_STANZA, NOME, DESCRIZIONE, OSSERVA from STANZE";
        String query2 = "select ID_OGGETTO, NOME, DESCRIZIONE, CONTENUTO, STANZA from OGGETTI";
        String query3 = "select TIPO_COMANDO, NOME from COMANDI";
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
            System.err.println("Errore caricamento dati oggetti");
            System.err.print(ex.getErrorCode());
            System.err.print(ex.getSQLState());
            System.err.print(ex.getMessage());
        }
        
        try(Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query3);
            while(rs.next()) {
                tipoComandi.add(rs.getString("TIPO_COMANDO"));
                nomeComandi.add(rs.getString("NOME"));
            }
        } catch(SQLException ex) {
            System.err.println("Errore caricamento dati comandi");
            System.err.print(ex.getErrorCode());
            System.err.print(ex.getSQLState());
            System.err.print(ex.getMessage());
        }
    }
    
    
    public static void creaStanze() 
    {
        //stanze casa di Joel. richiesti cibo, colpi, pistola e coltello        
        Stanza soggiornoCasa = new Stanza(idStanze.get(0), nomiStanze.get(0), descrizioniStanze.get(0));
        soggiornoCasa.setOsservazione(osservazioni.get(0));
        
        Stanza bagnoCasa = new Stanza(idStanze.get(1), nomiStanze.get(1), descrizioniStanze.get(1));
        bagnoCasa.setOsservazione(osservazioni.get(1));
        
        Stanza ripostiglioCasa = new Stanza(idStanze.get(2), nomiStanze.get(2), descrizioniStanze.get(2));
        ripostiglioCasa.setOsservazione(osservazioni.get(2));       
        //
        
        
        //Stanze passaggio segreto. Spingere leva per la corrente al tastierino e combinazione al tastierino per uscire       
        Stanza corridoioPassaggio = new Stanza(idStanze.get(3), nomiStanze.get(3), descrizioniStanze.get(3));
        corridoioPassaggio.setOsservazione(osservazioni.get(3));   
        corridoioPassaggio.getOggettiNecessari().add(new Oggetto(0));
        corridoioPassaggio.getOggettiNecessari().add(new Oggetto(1));
        corridoioPassaggio.getOggettiNecessari().add(new Oggetto(3));
        corridoioPassaggio.getOggettiNecessari().add(new Oggetto(9));
        
        Stanza cancello = new Stanza(idStanze.get(4), nomiStanze.get(4), descrizioniStanze.get(4));
        cancello.setOsservazione(osservazioni.get(4));
        
        Stanza stanzaQuadroElettrico = new Stanza(idStanze.get(5), nomiStanze.get(5), descrizioniStanze.get(5));
        stanzaQuadroElettrico.setOsservazione(osservazioni.get(5));
        
        Stanza uscitaPassaggio = new Stanza(idStanze.get(17), nomiStanze.get(17), descrizioniStanze.get(17));
        uscitaPassaggio.getOggettiNecessari().add(new Oggetto(14));
        uscitaPassaggio.getOggettiNecessari().add(new Oggetto(25));
        uscitaPassaggio.setOsservazione(osservazioni.get(17));      
        //      
        
        /*
        Stanze metropolitana allagata. 
        Torcia richiesta per capire che nella stanza della zattera c'è la zattera.
        Zattera richiesta per uscire dalla metro.
        */    
        Stanza ingressoMetro = new Stanza(idStanze.get(6), nomiStanze.get(6), descrizioniStanze.get(6));
        ingressoMetro.setOsservazione(osservazioni.get(6));
       
        Stanza binariMetro = new Stanza(idStanze.get(7), nomiStanze.get(7), descrizioniStanze.get(7));
        binariMetro.setOsservazione(osservazioni.get(7));
        
        Stanza stanzaZattera = new Stanza(idStanze.get(8), nomiStanze.get(8), descrizioniStanze.get(8));
        stanzaZattera.setOsservazione(osservazioni.get(8));
        stanzaZattera.setVisibile(false);
        
        Stanza uscitaMetro = new Stanza(idStanze.get(18), nomiStanze.get(18), descrizioniStanze.get(18));
        uscitaMetro.setOsservazione(osservazioni.get(18));
        uscitaMetro.getOggettiNecessari().add(new Oggetto(17));       
        //
        
        //Stanze ospedale San Maries   
        Stanza ingressoOspedale = new Stanza(idStanze.get(9), nomiStanze.get(9), descrizioniStanze.get(9));
        ingressoOspedale.setOsservazione(osservazioni.get(9));
        
        Stanza dentroOspedale = new Stanza(idStanze.get(10), nomiStanze.get(10), descrizioniStanze.get(10));
        dentroOspedale.setOsservazione(osservazioni.get(10));
        
        Stanza magazzino = new Stanza(idStanze.get(11), nomiStanze.get(11), descrizioniStanze.get(11));
        magazzino.setOsservazione(osservazioni.get(11));
        
        Stanza infermeria = new Stanza(idStanze.get(12), nomiStanze.get(12), descrizioniStanze.get(12));
        infermeria.setOsservazione(osservazioni.get(12));
        //tesserino richiesto
        infermeria.getOggettiNecessari().add(new Oggetto(20));
        
        Stanza pianoSalaOperatoria = new Stanza(idStanze.get(13), nomiStanze.get(13), descrizioniStanze.get(13));
        pianoSalaOperatoria.setOsservazione(osservazioni.get(13));
        pianoSalaOperatoria.getOggettiNecessari().add(new Oggetto(24));
        
         //cacciavite richiesto per aprire il condotto e andare verso nord, nella sala operatoria.
        Stanza condotto = new Stanza(idStanze.get(14), nomiStanze.get(14), descrizioniStanze.get(14));
        condotto.setOsservazione(osservazioni.get(14));
        
        Stanza stanzaCacciavite = new Stanza(idStanze.get(15), nomiStanze.get(15), descrizioniStanze.get(15));
        stanzaCacciavite.setOsservazione(osservazioni.get(15));
        stanzaCacciavite.setVisibile(false);
        
        Stanza salaOperatoria = new Stanza(idStanze.get(16), nomiStanze.get(16), descrizioniStanze.get(16));
        salaOperatoria.setOsservazione(osservazioni.get(16));
        salaOperatoria.getOggettiNecessari().add(new Oggetto(21));
      
        Stanza finale = new Stanza(idStanze.get(19), nomiStanze.get(19), descrizioniStanze.get(19));
        finale.setOsservazione(osservazioni.get(19));          
        //
        
        
        //Setta mappa
        
        //casa di Joel
        soggiornoCasa.setEst(bagnoCasa);
        soggiornoCasa.setSud(ripostiglioCasa);
        soggiornoCasa.setNord(corridoioPassaggio);
        bagnoCasa.setOvest(soggiornoCasa);
        ripostiglioCasa.setNord(soggiornoCasa);
        //
        
        //passaggio segreto
        corridoioPassaggio.setNord(cancello);      
        cancello.setSud(corridoioPassaggio);
        cancello.setEst(stanzaQuadroElettrico);
        cancello.setNord(uscitaPassaggio);//Su questo va fatto un check se il cancello è stato aperto
        stanzaQuadroElettrico.setOvest(cancello);
        uscitaPassaggio.setNord(ingressoMetro);
        //
        
        //metropolitana allagata
        ingressoMetro.setNord(binariMetro); 
        binariMetro.setSud(ingressoMetro);
        binariMetro.setOvest(stanzaZattera);
        binariMetro.setNord(uscitaMetro);
        stanzaZattera.setEst(binariMetro);
        uscitaMetro.setNord(ingressoOspedale);
        //
        
        ingressoOspedale.setNord(dentroOspedale);
        
        //ospedale piano terra
        dentroOspedale.setOvest(magazzino);
        dentroOspedale.setEst(infermeria);
        dentroOspedale.setNord(pianoSalaOperatoria);
        magazzino.setEst(dentroOspedale);    
        infermeria.setOvest(dentroOspedale);
        //
        
        //ospedale (mdificare)
        pianoSalaOperatoria.setEst(condotto);
        pianoSalaOperatoria.setOvest(stanzaCacciavite);
        condotto.setOvest(pianoSalaOperatoria);
        condotto.setNord(salaOperatoria);//dal condotto, diciamo, se hai il cacciavite, puoi entrarci dentro ed arrivare alla sala operatoria   
        stanzaCacciavite.setEst(pianoSalaOperatoria);
        salaOperatoria.setNord(finale); //per ora, a fini di testing
        //
        
        
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
        stanze.add(uscitaPassaggio);
        stanze.add(uscitaMetro);
        stanze.add(finale);    
    }
    
    
    public static List<Stanza> creaOggetti() 
    {
        //Oggetti casa Joel
        
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

        Documento foto = new OggettoImmagine(idOggetti.get(4), nomiOggetti.get(4), descrizioniOggetti.get(4));
        foto.setAlias(new String[]{"immagine"});
        foto.setPrendibile(true);
        foto.setPathDocumento(".//resources/img/fotoSoggiorno960x660.jpg");
        stanze.get(0).getOggetti().add(foto);
        
        Oggetto garza = new Oggetto(idOggetti.get(6), nomiOggetti.get(6), descrizioniOggetti.get(6));
        garza.setAlias(new String[]{"garze"});        
        
        Oggetto alcol = new Oggetto(idOggetti.get(7), nomiOggetti.get(7), descrizioniOggetti.get(7));
        alcol.setAlias(new String[]{"alcol etilico", "etilico", "alcool"});
        
        Oggetto forbici = new Oggetto(idOggetti.get(8), nomiOggetti.get(8), descrizioniOggetti.get(8));
        forbici.setAlias(new String[]{"forbice"});
        
        ContenitoreOggetti mobileBagno = new ContenitoreOggetti(idOggetti.get(5), nomiOggetti.get(5), descrizioniOggetti.get(5));
        mobileBagno.setAlias(new String[]{"mobiletto"});
        mobileBagno.setApribile(true);
        mobileBagno.setPrendibile(false);
        mobileBagno.setAperto(false);
        stanze.get(1).getOggetti().add(mobileBagno);
        mobileBagno.add(garza);
        mobileBagno.add(alcol);
        mobileBagno.add(forbici);
        
        Oggetto munizioni = new Oggetto(idOggetti.get(9), nomiOggetti.get(9), descrizioniOggetti.get(9));
        munizioni.setAlias(new String[]{"colpi", "pacco", "pacchetto"});
        stanze.get(2).getOggetti().add(munizioni);

        Oggetto batterie = new Oggetto(idOggetti.get(10), nomiOggetti.get(10), descrizioniOggetti.get(10));
        batterie.setAlias(new String[]{"batteria"});
        stanze.get(2).getOggetti().add(batterie);
        
        //
        
        
        //Oggetti passaggio segreto
        
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
        levaCorrente.setSpingibile(true);
        levaCorrente.setInvisibile(true);
        stanze.get(5).getOggetti().add(levaCorrente);
        
        //
        
        
        //Oggetti metropolitana allagata
        
        Oggetto torcia = new Oggetto(idOggetti.get(15), nomiOggetti.get(15), descrizioniOggetti.get(15));
        torcia.setAlias(new String[]{});
        torcia.setAccendibile(true);
        stanze.get(6).getOggetti().add(torcia);
 
        Oggetto documentoMetro = new Oggetto(idOggetti.get(16), nomiOggetti.get(16), descrizioniOggetti.get(16));
        documentoMetro.setAlias(new String[]{"documento", "documento metro", "lettera"});
        documentoMetro.setLeggibile(true);
        documentoMetro.setContenuto("CONTENUTO DOCUMENTO METROPOLITANA");
        stanze.get(7).getOggetti().add(documentoMetro);
        

        Oggetto zattera = new Oggetto(idOggetti.get(17), nomiOggetti.get(17), descrizioniOggetti.get(17));
        zattera.setAlias(new String[]{"legno"});
        stanze.get(8).getOggetti().add(zattera);
        
        //
        
        
        //Oggetti Sala d'attesa ospedale
        
        stanze.get(12).getOggetti().add(alcol);
        stanze.get(12).getOggetti().add(garza);
        
        Oggetto chiaveArmadietto = new Oggetto(idOggetti.get(18), nomiOggetti.get(18), descrizioniOggetti.get(18));
        chiaveArmadietto.setAlias(new String[]{"chiave", "chiave armadietto"});
        stanze.get(10).getOggetti().add(chiaveArmadietto);
        
        Oggetto tesserino = new Oggetto(idOggetti.get(20), nomiOggetti.get(20), descrizioniOggetti.get(20));
        tesserino.setAlias(new String[]{"tessera"});

        ContenitoreOggetti armadietto = new ContenitoreOggetti(idOggetti.get(19), nomiOggetti.get(19), descrizioniOggetti.get(19));
        armadietto.setAlias(new String[]{});
        armadietto.setApribile(false);
        armadietto.setPrendibile(false);
        armadietto.setAperto(false);
        armadietto.add(forbici);
        armadietto.add(tesserino);        
        stanze.get(11).getOggetti().add(armadietto);

        Oggetto cacciavite = new Oggetto(idOggetti.get(21), nomiOggetti.get(21), descrizioniOggetti.get(21));
        cacciavite.setAlias(new String[]{});
        stanze.get(15).getOggetti().add(cacciavite);
        
        //
        
        
        //Oggetti piano sala operatoria
        
        Documento documentoMedico = new OggettoFoglietto(idOggetti.get(22), nomiOggetti.get(22), descrizioniOggetti.get(22));
        documentoMedico.setAlias(new String[]{"referto", "documento medico","documento"});
        documentoMedico.setLeggibile(true);
        documentoMedico.setPrendibile(true);
        documentoMedico.setContenuto("CONTENUTO DOCUMENTO MEDICO OSPEDALE");
        documentoMedico.setPathDocumento(".//the_last_of_us(storia)//documenti_gioco(da raccogliere)//Documento_cordyceps.txt");
        stanze.get(13).getOggetti().add(documentoMedico);
       
        Oggetto grata = new Oggetto(idOggetti.get(23), nomiOggetti.get(23), descrizioniOggetti.get(23));
        grata.setAlias(new String[]{});
        grata.setPrendibile(false);
        grata.setSpingibile(false);
        grata.setApribile(false);
        stanze.get(14).getOggetti().add(grata);

        
        //Aggiungo tutti gli oggetti alla lista degli oggetti del gioco
        
//<<<<<<< HEAD
//=======
        /*
        Oggetto curato = new Oggetto(idOggetti.get(24), nomiOggetti.get(24), descrizioniOggetti.get(24));
        curato.setAlias(new String[]{});        
        curato.setPrendibile(false);
        curato.setSpingibile(false);
        curato.setApribile(false);
        curato.setInvisibile(true);
        */
        Oggetto fittizio = new Oggetto(idOggetti.get(25), nomiOggetti.get(25), descrizioniOggetti.get(25));
        fittizio.setInvisibile(true);
        
//>>>>>>> main
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

/*        oggetti.add(grata);
<<<<<<< HEAD
=======
        
        oggetti.add(curato);
        oggetti.add(fittizio);
>>>>>>> main
*/
        
        return stanze;        
    }
    
    
    public static List<Comando> creaComandi() throws SQLException {
        Comando nord = new Comando(TipoComando.valueOf(tipoComandi.get(0)), nomeComandi.get(0));
        nord.setAlias(new String[]{"n", "N", "Nord", "NORD"});
        
        Comando inventario = new Comando(TipoComando.valueOf(tipoComandi.get(1)), nomeComandi.get(1));
        inventario.setAlias(new String[]{"inv"});
        
        Comando sud = new Comando(TipoComando.valueOf(tipoComandi.get(2)), nomeComandi.get(2));
        sud.setAlias(new String[]{"s", "S", "Sud", "SUD"});
        
        Comando est = new Comando(TipoComando.valueOf(tipoComandi.get(3)), nomeComandi.get(3));
        est.setAlias(new String[]{"e", "E", "Est", "EST"});
        
        Comando ovest = new Comando(TipoComando.valueOf(tipoComandi.get(4)), nomeComandi.get(4));
        ovest.setAlias(new String[]{"o", "O", "Ovest", "OVEST"});
        
        Comando fine = new Comando(TipoComando.valueOf(tipoComandi.get(5)), nomeComandi.get(5));
        fine.setAlias(new String[]{"end", "fine", "esci", "muori", "ammazzati", "ucciditi", "suicidati", "exit"});
        
        Comando osserva = new Comando(TipoComando.valueOf(tipoComandi.get(6)), nomeComandi.get(6));
        osserva.setAlias(new String[]{"guarda", "vedi", "trova", "cerca", "descrivi", "scruta"});
        
        Comando prendi = new Comando(TipoComando.valueOf(tipoComandi.get(7)), nomeComandi.get(7));
        prendi.setAlias(new String[]{"prendi"});
        
        Comando apri = new Comando(TipoComando.valueOf(tipoComandi.get(8)), nomeComandi.get(8));
        apri.setAlias(new String[]{});
        
        Comando spingi = new Comando(TipoComando.valueOf(tipoComandi.get(9)), nomeComandi.get(9));
        spingi.setAlias(new String[]{"spingi", "attiva"});
        
        Comando leggi = new Comando(TipoComando.valueOf(tipoComandi.get(10)), nomeComandi.get(10));
        leggi.setAlias(new String[]{"sfoglia"});
        
        Comando accendi = new Comando(TipoComando.valueOf(tipoComandi.get(11)), nomeComandi.get(11));
        accendi.setAlias(new String[]{});
        
        Comando salva = new Comando(TipoComando.valueOf(tipoComandi.get(12)), nomeComandi.get(12));
        salva.setAlias(new String[]{"salvataggio","salva partita"});
        
        Comando nasconditi = new Comando(TipoComando.valueOf(tipoComandi.get(13)), nomeComandi.get(13));
        nasconditi.setAlias(new String[]{});
        
        Comando curati = new Comando(TipoComando.valueOf(tipoComandi.get(14)), nomeComandi.get(14));
        curati.setAlias(new String[]{"risanati"});
        
        Comando usa = new Comando(TipoComando.valueOf(tipoComandi.get(15)), nomeComandi.get(15));
        usa.setAlias(new String[]{});
        
        comandi.add(nord);
        comandi.add(inventario);
        comandi.add(sud);
        comandi.add(est);
        comandi.add(ovest);
        comandi.add(fine);
        comandi.add(osserva);
        comandi.add(prendi);
        comandi.add(apri);
        comandi.add(spingi);
        comandi.add(leggi);
        comandi.add(accendi);
        comandi.add(salva);
        comandi.add(nasconditi);
        comandi.add(curati);
        comandi.add(usa);
        
        return comandi;
    }
    
    public static void resetDatabase() throws SQLException {
        try(Statement stmt = con.createStatement()) {
            stmt.executeUpdate("DROP ALL OBJECTS");
        } catch(SQLException ex) {
            System.err.println("Errore reset database\n");
            System.err.print(ex.getErrorCode());
            System.err.print(ex.getSQLState());
            System.err.print(ex.getMessage());
        }
    }
}
