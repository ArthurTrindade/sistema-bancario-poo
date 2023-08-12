package com.arthur.sistemabancario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Pessoa {
    private int id;
    private String nome;
    private String cpf;
    private String phone;
}
