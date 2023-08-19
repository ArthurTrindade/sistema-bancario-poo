package com.arthur.sistemabancario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The type Agencia.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agencia {
    private int numero;
    private List<Cliente> clienteList;
    private Gerente gerente;
}

