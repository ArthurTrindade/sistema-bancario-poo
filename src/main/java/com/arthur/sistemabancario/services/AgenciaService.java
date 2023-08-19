package com.arthur.sistemabancario.services;

import com.arthur.sistemabancario.model.Cliente;
import com.arthur.sistemabancario.repositorys.AgenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * The type Agencia service.
 */
@Service
public class AgenciaService {
    @Autowired
    private AgenciaRepository agenciaRepository;

    /**
     * Gets clientes.
     *
     * @return List<Cliente>  clientes
     * @throws IOException the io exception
     */
    public List<Cliente> getClientes() throws IOException {
        return agenciaRepository.getAllClientes();
    }

    /**
     * Save cliente cliente.
     *
     * @param cliente the cliente
     * @return the cliente
     * @throws IOException the io exception
     */
    public Cliente saveCliente(Cliente cliente) throws IOException {
        return agenciaRepository.save(cliente);
    }

    /**
     * Gets cliente by id.
     *
     * @param id the id
     * @return the cliente by id
     * @throws IOException the io exception
     */
    public Cliente getClienteById(int id) throws IOException {
        return agenciaRepository.findById(id);
    }

    /**
     * Deletar cliente boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws IOException the io exception
     */
    public boolean deletarCliente(int id) throws IOException {
        return agenciaRepository.deleteCliente(id);
    }

    /**
     * Depositar.
     *
     * @param id    the id
     * @param valor the valor
     * @throws IOException the io exception
     */
    public void depositar(int id, int valor) throws IOException {
        agenciaRepository.depositar(id, valor);
    }

    /**
     * Sacar.
     *
     * @param id    the id
     * @param valor the valor
     * @throws IOException the io exception
     */
    public void sacar(int id, int valor) throws IOException {
        agenciaRepository.sacar(id, valor);
    }

    /**
     * Transferencia.
     *
     * @param id1   the id 1
     * @param id2   the id 2
     * @param valor the valor
     * @throws IOException the io exception
     */
    public void transferencia(int id1, int id2, int valor) throws IOException {
        agenciaRepository.transferencia(id1, id2, valor);
    }

    /**
     * Login cliente.
     *
     * @param c the c
     * @return the cliente
     * @throws IOException the io exception
     */
    public Cliente login(Cliente c) throws IOException {
        return agenciaRepository.login(c);
    }
}
