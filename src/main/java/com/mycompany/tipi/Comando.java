/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tipi;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author pierpaolo
 */
public class Comando {

    private final TipoComando tipo;

    private final String nome;

    private Set<String> alias;

    public Comando(TipoComando tipo, String nome) {
        this.tipo = tipo;
        this.nome = nome;
    }

    public Comando(TipoComando tipo, String nome, Set<String> alias) {
        this.tipo = tipo;
        this.nome = nome;
        this.alias = alias;
    }

    public String getNome() {
        return nome;
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

    public TipoComando getTipo() {
        return tipo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.tipo);
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
        final Comando other = (Comando) obj;
        if (this.tipo != other.tipo) {
            return false;
        }
        return true;
    }

}
