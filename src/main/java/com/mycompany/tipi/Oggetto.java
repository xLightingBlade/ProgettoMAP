/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and aperto the template in the editor.
 */
package com.mycompany.tipi;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author gabri
 * Questa classe modella la struttura che un oggetto del gioco (utilizzato dall'utente) deve avere
 */
public class Oggetto implements Serializable {

    private final int id;

    private String nome;

    private String descrizione;
    
    private Set<String> alias;//un insieme di alis, cioè di nomi simili dell'oggetto.
    //Non è detto che l'utente fornirà in input esattamente il nome dell'oggetto che vuole usare,
    //ma magari potrebbe fornire un nome simile(presente tra gli alias) e il gioco continuerà a funzionare correttmente.
    
    private String contenuto;

    private boolean apribile = false;

    private boolean prendibile = true;

    private boolean spingibile = false;
    
    private boolean leggibile = false;

    private boolean aperto = false;

    private boolean spinto = false;
    
    private boolean invisibile = false;

    
    //Costruttori
    public Oggetto(int id) {
        this.id = id;
    }

    public Oggetto(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Oggetto(int id, String nome, String descrizione) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public Oggetto(int id, String nome, String descrizione, Set<String> alias) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.alias = alias;
    }
    
    public Oggetto(int id, String nome, String descrizione, Set<String> alias, String contenuto) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.alias = alias;
        this.contenuto = contenuto;
    }
    //
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getContenuto() {
        return contenuto;
    }
    
    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }
    
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    //Usato per capire se un oggetto si può aprire
    public boolean isApribile() {
        return apribile;
    }

    public void setApribile(boolean apribile) {
        this.apribile = apribile;
    }
    
    //Usato per capire se un oggetto si può prendere
    public boolean isPrendibile() {
        return prendibile;
    }

    public void setPrendibile(boolean prendibile) {
        this.prendibile = prendibile;
    }
    
    //Usato per capire se un oggetto si può spingere
    public boolean isSpingibile() {
        return spingibile;
    }
    
    public void setSpingibile(boolean spingibile) {
        this.spingibile = spingibile;
    }
    
    //Usato per capire se un oggetto si può leggere
    public boolean isLeggibile() {
        return leggibile;
    }
    
    public void setLeggibile(boolean leggibile) {
        this.leggibile = leggibile;
    }
    
    public boolean isInvisibile() {
        return invisibile;
    }
    
    public void setInvisibile(boolean invisibile) {
        this.invisibile = invisibile;
    }
    
    //Usato per capire se un oggetto è aperto
    public boolean isAperto() {
        return aperto;
    }

    public void setAperto(boolean aperto) {
        this.aperto = aperto;
    }
    
    //Usato per capire se un oggetto è stato spinto
    public boolean isSpinto() {
        return spinto;
    }

    public void setSpinto(boolean spinto) {
        this.spinto = spinto;
    }

    public Set<String> getAlias() {
        return alias;
    }

    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }
    
    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
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
        final Oggetto other = (Oggetto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
