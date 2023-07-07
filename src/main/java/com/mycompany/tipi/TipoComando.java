/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tipi;

import java.io.Serializable;

/**
 *
 * @author gabri
 * Questa classe enumerativa descrivi quali tipi di comando sono definiti nel gioco.
 */
public enum TipoComando implements Serializable {
    FINE, INVENTARIO, NORD, SUD, EST, OVEST, APRI, CHIUDI, SPINGI, TIRA, CAMMINA, PRENDI, PARLA, DAI, USA, GUARDA, ACCENDI, SPEGNI, LEGGI, SALVA, CARICA, NASCONDITI, CURATI, HELP
}
