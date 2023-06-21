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
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author gabri
 */
public class Engine {

    private final StrutturaGioco gioco;

    private Parser parser;

    public Engine(StrutturaGioco game) {
        this.gioco = game;
        try {
            this.gioco.init();
        } catch (Exception ex) {
            System.err.println(ex);
        }
        try {
            Set<String> stopwords = Utils.caricaStopwords(new File("./resources/stopwords"));
            parser = new Parser(stopwords);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public void esegui() {
        System.out.println("================================");
        System.out.println("* Adventure v. 0.3 - 2021-2022 *");
        System.out.println("================================");
        System.out.println(gioco.getStanzaCorrente().getNome());
        System.out.println();
        System.out.println(gioco.getStanzaCorrente().getDescrizione());
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            ParserOutput p = parser.parse(command, gioco.getComandi(), gioco.getStanzaCorrente().getOggetti(), gioco.getInventario());
            if (p == null || p.getComando() == null) {
                System.out.println("Non capisco quello che mi vuoi dire.");
            } else if (p.getComando() != null && p.getComando().getTipo() == TipoComando.FINE) {
                System.out.println("Addio!");
                break;
            } else {
                gioco.prossimaMossa(p, System.out);
                System.out.println();
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Engine engine = new Engine(new Avventura());
        engine.esegui();
    }

}
