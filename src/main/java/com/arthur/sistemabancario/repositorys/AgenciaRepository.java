package com.arthur.sistemabancario.repositorys;

import com.arthur.sistemabancario.model.Cliente;
import com.arthur.sistemabancario.model.Conta;
import com.arthur.sistemabancario.model.Transacao;
import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AgenciaRepository {

    private final String path = "items.txt";

    public List<Cliente> getAllClientes() throws IOException {
        return lerArquivo();
    }

    public Cliente save(Cliente c) throws IOException {

        List<Cliente> list = getAllClientes();

        Cliente cliente = new Cliente();
        Long proximoId = list.isEmpty() ? 1L : list.get(list.size() - 1).getId() + 1;

        cliente.setId(proximoId);
        cliente.setNome(c.getNome());
        cliente.setCpf(c.getCpf());
        cliente.setTelefone(c.getTelefone());
        cliente.setSenha(c.getSenha());
        
        Conta conta = new Conta();
        cliente.setConta(conta);

        list.add(cliente);

        escerverNoArquivo(list);

        return cliente;
    }


    public boolean deleteCliente(int id) throws IOException {
        List<Cliente> clientes = getAllClientes();
        boolean removed = clientes.removeIf(item -> item.getId() == (id));
        if (removed) {
            escerverNoArquivo(clientes);
        }
        return removed;
    }
    
    public Cliente findById(int id) throws IOException {
        return getAllClientes().stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Cliente findByCpf(String cpf) throws IOException {
        return getAllClientes().stream()
                .filter(c -> c.getCpf() == cpf)
                .findFirst()
                .orElse(null);
    }

    public void depositar(int id, int valor) throws IOException {
        List<Cliente> list = getAllClientes();

        for (Cliente cliente : list) {
            if (cliente.getId() == id) {
                Conta conta = cliente.getConta();
                Transacao t = new Transacao(valor);
                conta.addTransacao(t);
                conta.setSaldo(conta.getSaldo() + valor);
                escerverNoArquivo(list);
            }
        }
    }

    public Cliente login(Cliente cliente) throws IOException {
        List<Cliente> list = getAllClientes();
        
        for (Cliente c : list) {
            if (c.getCpf().equals(cliente.getCpf())) {
                return c;
            }
        }

        // if (cliente == null || cliente.getSenha().equals(c.getSenha())) {
        //     // excessão
        // }

        return null;
    }

    private List<Cliente> lerArquivo() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path))) {
            return (List<Cliente>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void escerverNoArquivo(List<Cliente> clienteList) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            outputStream.writeObject(clienteList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
