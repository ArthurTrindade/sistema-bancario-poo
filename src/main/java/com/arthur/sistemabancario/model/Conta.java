package com.arthur.sistemabancario.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Conta.
 */
@Data
@AllArgsConstructor
public class Conta implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private int numero;
    private int saldo;
    private Date dataDeCriacao;
    private List<Transacao> transacaoList;

    /**
     * Instantiates a new Conta.
     */
    public Conta() {
        this.saldo = 0;
        this.dataDeCriacao = new Date();
        this.transacaoList = new LinkedList<>();
    }

    /**
     * Add transacao.
     *
     * @param t the t
     */
    public void addTransacao(Transacao t) {
        transacaoList.add(t);
    }

}
