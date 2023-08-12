package com.arthur.sistemabancario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data

@AllArgsConstructor
public class Conta {
    private static int totalDeContas = 0;

    private int numero;
    private int saldo;
    private Date dataDeCriacao;

    public Conta() {
        this.numero = totalDeContas;
        this.saldo = 0;
        this.dataDeCriacao = new Date();
        totalDeContas++;
    }



}
