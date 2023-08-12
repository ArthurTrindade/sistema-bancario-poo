package com.arthur.sistemabancario.services;

import com.arthur.sistemabancario.model.Cliente;
import com.arthur.sistemabancario.repositorys.AgenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AgenciaService {
    @Autowired
    private AgenciaRepository agenciaRepository;

    public Cliente saveProduct(Cliente cliente) throws IOException {
        return agenciaRepository.save(cliente);
    }

    public List<Cliente> getProducts() throws IOException {
        return agenciaRepository.getAllClientes();
    }

    public Cliente getClienteById(int id) {
        return agenciaRepository.findById(id);
    }

    public void depositar(int id, int valor) {
        agenciaRepository.depositar(id, valor);
        return;
    }

}
