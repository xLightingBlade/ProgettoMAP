/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tipi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabri
 * Questa classe modella la struttura che ogni stanza deve avere.
 */
public class Stanza implements Serializable {

    private final int id;

    private String nome;

    private String descrizione;//una breve descrizione della stanza

    private String osservazione;

    private boolean visibile = true;//definisce se la stanza sarà visibile nella mappa o no.

    //definiamo cosa c'è a nord, sud, est e ovest della stanza
    private Stanza sud = null;

    private Stanza nord = null;

    private Stanza est = null;

    private Stanza ovest = null;
    
    
    private List<Oggetto> oggettiNecessariPerAccedere = new ArrayList<>();
    
    private final List<Oggetto> oggetti=new ArrayList<>();//la lista degli oggetti che si trovano nella stanza

    //costruttori
    public Stanza(int id) {
        this.id = id;
    }

    public Stanza(int id, String nome, String descrizione) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
    }
    //
    
    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    
    public List<Oggetto> getOggettiNecessari() {
        return oggettiNecessariPerAccedere;
    }
    
    //utilizzato per capire se una stanza deve essere visibile nella mappa o no
    public boolean isVisibile() {
        return visibile;
    }

    public void setVisibile(boolean visibile) {
        this.visibile = visibile;
    }

    public Stanza getSud() {
        return sud;
    }

    public void setSud(Stanza sud) {
        this.sud = sud;
    }

    public Stanza getNord() {
        return nord;
    }

    public void setNord(Stanza nord) {
        this.nord = nord;
    }

    public Stanza getEst() {
        return est;
    }

    public void setEst(Stanza est) {
        this.est = est;
    }

    public Stanza getOvest() {
        return ovest;
    }

    public void setOvest(Stanza ovest) {
        this.ovest = ovest;
    }

    public List<Oggetto> getOggetti() {
        return oggetti;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Stanza other = (Stanza) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public String getOsservazione() {
        return osservazione;
    }

    public void setOsservazione(String osservazione) {
        this.osservazione = osservazione;
    }

}
