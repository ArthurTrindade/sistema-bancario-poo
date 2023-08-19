package com.arthur.sistemabancario.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * The type Transacao.
 */
@Data
public class Transacao implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private Date data;
    private int valor;

    /**
     * Instantiates a new Transacao.
     *
     * @param data  the data
     * @param valor the valor
     */
    public Transacao(Date data, int valor) {
        this.data = data;
        this.valor = valor;
    }

    /**
     * Instantiates a new Transacao.
     */
    public Transacao() {
    }

    /**
     * Instantiates a new Transacao.
     *
     * @param valor the valor
     */
    public Transacao(int valor) {
        this.data = new Date();
        this.valor = valor;
    }

}
