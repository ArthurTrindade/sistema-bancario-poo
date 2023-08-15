package com.arthur.sistemabancario.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class Transacao implements Serializable {
    private int id;
    private Date data;
    private int valor;

    public Transacao(int id, Date data, int valor) {
        this.id = id;
        this.data = data;
        this.valor = valor;
    }

    public Transacao() {
    }

    public Transacao(int valor) {
        this.data = new Date();
        this.valor = valor;
    }

    
}
