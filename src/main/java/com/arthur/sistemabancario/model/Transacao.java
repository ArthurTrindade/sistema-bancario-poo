package com.arthur.sistemabancario.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Transacao {
    private Date data;
    private int valor;

    public Transacao(int valor) {
        this.data = new Date();
        this.valor = valor;
    }

}
