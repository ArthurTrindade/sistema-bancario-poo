package com.arthur.sistemabancario.repositorys;

import com.arthur.sistemabancario.model.Cliente;
import com.arthur.sistemabancario.model.Conta;
import com.arthur.sistemabancario.model.Transacao;
import com.arthur.sistemabancario.services.JsonFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class AgenciaRepository {

    @Autowired
    private JsonFileService jsonFileService;

    private List<Cliente> clienteList;

    public void initializeData() throws IOException {
        String pathLeitura = "classpath:data.json";
        clienteList = jsonFileService.readJsonFile(pathLeitura);
    }

    public List<Cliente> getAllClientes() throws IOException {
        String pathLeitura = "classpath:data.json";
        clienteList = jsonFileService.readJsonFile(pathLeitura);
        return clienteList;
    }

    public Cliente save(Cliente c) throws IOException {
        Cliente cliente = new Cliente();
        cliente.createId();
        cliente.setNome(c.getNome());
        cliente.setCpf(c.getCpf());
        cliente.setPhone(c.getPhone());

        cliente.setConta(new Conta());

        clienteList.add(cliente);

        String pathEscrita = "src/main/resources/data.json";
        jsonFileService.writeJsonFile(clienteList, pathEscrita);

        return cliente;
    }

    public Cliente findById(int id) {
        for (Cliente i : clienteList) {
            if (i.getId() == (id)) {
                return i;
            }
        }
        return null;
    }

    public void depositar(int id, int valor) throws IOException {
        Cliente cliente = findById(id);
        Conta conta = cliente.getConta();
        Transacao t = new Transacao(valor);
        conta.addTransacao(t);
        conta.setSaldo(conta.getSaldo() + valor);

        String pathEscrita = "src/main/resources/data.json";
        jsonFileService.writeJsonFile(clienteList, pathEscrita);
    }
}
