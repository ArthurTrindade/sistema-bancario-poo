package com.arthur.sistemabancario.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Transacao implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private Date data;
    private int valor;

    public Transacao(Date data, int valor) {
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
