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

/**
 * Classe AgenciaRepository: que faz as lógicas das operações com o banco de dados
 */
@Repository
public class AgenciaRepository {

    private final String path = "clientes.txt";

    /**
     * Gets all clientes.
     *
     * @return List<Cliente>  all clientes
     * @throws IOException the io exception
     */
    public List<Cliente> getAllClientes() throws IOException {
        return lerArquivo();
    }


    /**
     * Save cliente.
     *
     * @param c the c
     * @return Cliente cliente
     * @throws IOException the io exception
     */
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
        conta.setId(proximoId);
        cliente.setConta(conta);

        list.add(cliente);

        escerverNoArquivo(list);

        return cliente;
    }

    /**
     * Delete cliente boolean.
     *
     * @param id the id
     * @return boolean boolean
     * @throws IOException the io exception
     */
    public boolean deleteCliente(int id) throws IOException {
        List<Cliente> clientes = getAllClientes();
        boolean removed = clientes.removeIf(item -> item.getId() == (id));
        if (removed) {
            escerverNoArquivo(clientes);
        }
        return removed;
    }


    /**
     * Find by id cliente.
     *
     * @param id the id
     * @return Cliente cliente
     * @throws IOException the io exception
     */
    public Cliente findById(int id) throws IOException {
        return getAllClientes().stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Busca cliente por CPF
     *
     * @param cpf the cpf
     * @return retorna o cliente
     * @throws IOException the io exception
     */
    public Cliente findByCpf(String cpf) throws IOException {
        return getAllClientes().stream()
                .filter(c -> c.getCpf() == cpf)
                .findFirst()
                .orElse(null);
    }

    /**
     * Entrontra cliente por id e faz o deposito
     *
     * @param id    the id
     * @param valor the valor
     * @throws IOException the io exception
     */
    public void depositar(int id, int valor) throws IOException {
        List<Cliente> list = getAllClientes();

        for (Cliente cliente : list) {
            if (cliente.getId() == id) {
                Conta conta = cliente.getConta();
                List<Transacao> transacoes = conta.getTransacaoList();
                Long proximoId = transacoes.isEmpty() ? 1L : list.get(list.size() - 1).getId() + 1;
                Transacao t = new Transacao(valor);
                t.setId(proximoId);
                conta.addTransacao(t);
                conta.setSaldo(conta.getSaldo() + valor);
                escerverNoArquivo(list);
            }
        }
    }

    /**
     * Encontra cliente por id e faz o saque
     *
     * @param id    the id
     * @param valor the valor
     * @throws IOException the io exception
     */
    public void sacar(int id, int valor) throws IOException {
        List<Cliente> list = getAllClientes();

        for (Cliente cliente : list) {
            if (cliente.getId() == id) {
                Conta conta = cliente.getConta();
                List<Transacao> transacoes = conta.getTransacaoList();
                Long proximoId = transacoes.isEmpty() ? 1L : list.get(list.size() - 1).getId() + 1;
                Transacao t = new Transacao(valor);
                t.setId(proximoId);
                conta.addTransacao(t);
                conta.setSaldo(conta.getSaldo() - valor);
                escerverNoArquivo(list);
            }
        }
    }

    /**
     * Usa as funções de saque e deposito para fazer transferencia
     *
     * @param id1   the id 1
     * @param id2   the id 2
     * @param valor the valor
     * @throws IOException the io exception
     */
    public void transferencia(int id1, int id2, int valor) throws IOException {
        sacar(id1, valor);
        depositar(id2, valor);
    }

    /**
     * Verifica se o cliente está cadastrado e se a senha está correta
     *
     * @param cliente the cliente
     * @return cliente cliente
     * @throws IOException the io exception
     */
    public Cliente login(Cliente cliente) throws IOException {
        List<Cliente> list = getAllClientes();
        Cliente c = new Cliente();
        for (Cliente i : list) {
            if (i.getCpf().equals(cliente.getCpf())) {
                c = i;
            }
        }

        if (cliente.getSenha().equals(c.getSenha())) {
            return c;
        }

        return null;
    }

    /**
     * Ler arquivo que contem objetos Cliente 
     * @return
     */
    private List<Cliente> lerArquivo() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path))) {
            return (List<Cliente>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Escreve grava clientList no arquivo path
     * @param clienteList
     */
    private void escerverNoArquivo(List<Cliente> clienteList) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            outputStream.writeObject(clienteList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
