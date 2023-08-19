package com.arthur.sistemabancario.services;

import com.arthur.sistemabancario.model.Cliente;
import com.arthur.sistemabancario.repositorys.AgenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.CodeFlow.ClinitAdder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AgenciaService {
    @Autowired
    private AgenciaRepository agenciaRepository;

   
    public List<Cliente> getClientes() throws IOException {
        return agenciaRepository.getAllClientes();
    }

    public Cliente saveCliente(Cliente cliente) throws IOException {
        return agenciaRepository.save(cliente);
    }

    public Cliente getClienteById(int id) throws IOException {
        return agenciaRepository.findById(id);
    }

    public boolean deletarCliente(int id) throws IOException {
        return agenciaRepository.deleteCliente(id);
    }

    public void depositar(int id, int valor) throws IOException {
        agenciaRepository.depositar(id, valor);
    }

    public Cliente login(Cliente c) throws IOException {
        return agenciaRepository.login(c);
    }
}
