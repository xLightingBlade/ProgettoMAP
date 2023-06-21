/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.parser;

import com.mycompany.avventura.Utils;
import com.mycompany.tipi.Oggetto;
import com.mycompany.tipi.Comando;
import java.util.List;
import java.util.Set;

/**
 *
 * @author pierpaolo
 */
public class Parser {

    private final Set<String> stopwords;

    public Parser(Set<String> stopwords) {
        this.stopwords = stopwords;
    }

    private int checkComando(String token, List<Comando> comandi) {
        for (int i = 0; i < comandi.size(); i++) {
            if (comandi.get(i).getNome().equals(token) || comandi.get(i).getAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }

    private int checkOggetto(String token, List<Oggetto> oggetti) {
        for (int i = 0; i < oggetti.size(); i++) {
            if (oggetti.get(i).getNome().equals(token) || oggetti.get(i).getAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }

    /* ATTENZIONE: il parser è implementato in modo abbastanza independete dalla lingua, ma riconosce solo 
    * frasi semplici del tipo <azione> <oggetto> <oggetto>. Eventuali articoli o preposizioni vengono semplicemente
    * rimossi.
     */
    public ParserOutput parse(String comando, List<Comando> comandi, List<Oggetto> oggetti, List<Oggetto> inventario) {
        List<String> tokens = Utils.parseString(comando, stopwords);
        if (!tokens.isEmpty()) {
            int idComando = checkComando(tokens.get(0), comandi);
            if (idComando > -1) {
                if (tokens.size() > 1) {
                    int idOggetto = checkOggetto(tokens.get(1), oggetti);
                    int idOggettoInventario = -1;
                    if (idOggetto < 0 && tokens.size() > 2) {
                        idOggetto = checkOggetto(tokens.get(2), oggetti);
                    }
                    if (idOggetto < 0) {
                        idOggettoInventario = checkOggetto(tokens.get(1), inventario);
                        if (idOggettoInventario < 0 && tokens.size() > 2) {
                            idOggettoInventario = checkOggetto(tokens.get(2), inventario);
                        }
                    }
                    if (idOggetto > -1 && idOggettoInventario > -1) {
                        return new ParserOutput(comandi.get(idComando), oggetti.get(idOggetto), inventario.get(idOggettoInventario));
                    } else if (idOggetto > -1) {
                        return new ParserOutput(comandi.get(idComando), oggetti.get(idOggetto), null);
                    } else if (idOggettoInventario > -1) {
                        return new ParserOutput(comandi.get(idComando), null, inventario.get(idOggettoInventario));
                    } else {
                        return new ParserOutput(comandi.get(idComando), null, null);
                    }
                } else {
                    return new ParserOutput(comandi.get(idComando), null);
                }
            } else {
                return new ParserOutput(null, null);
            }
        } else {
            return null;
        }
    }

}
