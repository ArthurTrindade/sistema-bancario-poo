package com.arthur.sistemabancario.model;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String nome;
    private String cpf;
    private String telefone;
    private Conta conta;
    private String senha;
}
