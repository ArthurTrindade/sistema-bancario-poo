package com.arthur.sistemabancario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data

@AllArgsConstructor
public class Conta {
    private static int totalDeContas = 0;

    private int numero;
    private int saldo;
    private Date dataDeCriacao;
    private List<Transacao> transacaoList;

    public Conta() {
        this.numero = totalDeContas;
        this.saldo = 0;
        this.dataDeCriacao = new Date();
        transacaoList = new LinkedList<>();
        totalDeContas++;
    }

    public void addTransacao(Transacao t) {
        transacaoList.add(t);
    }


}
