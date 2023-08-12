package com.arthur.sistemabancario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Pessoa implements Serializable {
    private int id;
    private String nome;
    private String cpf;
    private String phone;
}
