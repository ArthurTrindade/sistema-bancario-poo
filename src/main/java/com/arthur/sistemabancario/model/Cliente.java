package com.arthur.sistemabancario.model;

import lombok.*;

//@EqualsAndHashCode(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Pessoa {
    private static int total = 0;

    private Conta conta;

    public void createId() {
        setId(total);
        total++;
    }

}
