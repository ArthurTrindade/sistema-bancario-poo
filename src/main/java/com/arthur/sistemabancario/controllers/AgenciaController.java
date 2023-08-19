package com.arthur.sistemabancario.controllers;

import com.arthur.sistemabancario.model.Cliente;
import com.arthur.sistemabancario.services.AgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Controller a aplicação
 */
@Controller()
public class AgenciaController {

    @Autowired
    private AgenciaService agenciaService;

    /**
     * Home string.
     *
     * @return the string
     */
    @GetMapping()
    public String home() {
        return "redirect:/login";
    }

    /**
     * Find all clientes string.
     *
     * @param model the model
     * @return String string
     * @throws IOException the io exception
     */
    @GetMapping("/clientes")
    public String findAllClientes(Model model) throws IOException {
        List<Cliente> clientes = agenciaService.getClientes();
        model.addAttribute("clientes", clientes);
        return "clientes";
    }

    /**
     * Gets cliente by id.
     *
     * @param model the model
     * @param id    the id
     * @return String cliente by id
     * @throws IOException the io exception
     */
    @GetMapping("/clientes/{id}")
    public String getClienteById(Model model, @PathVariable int id) throws IOException {
        Cliente cliente = agenciaService.getClienteById(id);
        model.addAttribute("cliente", cliente);
        return "cliente";
    }


    /**
     * Mostra a pagina de cadastro
     *
     * @param model the model
     * @return string string
     */
    @GetMapping("/cadastro")
    public String mostraCadastro(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "cadastro";
    }

    /**
     * Requisição que salva cliente no arquivo
     *
     * @param model   the model
     * @param cliente the cliente
     * @return string string
     * @throws IOException the io exception
     */
    @PostMapping("/cadastro")
    public String cadastro(Model model, @ModelAttribute("cliente") Cliente cliente) throws IOException {
        Cliente c = agenciaService.saveCliente(cliente);
        model.addAttribute("cliente", cliente);
        return "redirect:/clientes/" + String.valueOf(c.getId());
    }

    /**
     * Mostra pagina de deposito
     *
     * @return string string
     */
    @GetMapping("/deposito")
    public String mostrarDeposito() {
        return "deposito";
    }

    /**
     * Requisição para fazer deposito em um conta
     *
     * @param id    the id
     * @param valor the valor
     * @return string string
     * @throws IOException the io exception
     */
    @PostMapping("/deposito")
    public String depositar(@RequestParam String id, @RequestParam String valor) throws IOException {
        agenciaService.depositar(Integer.parseInt(id), Integer.parseInt(valor));
        return "redirect:/clientes/" + id;
    }

    /**
     * Mostra pagina de saque
     *
     * @return string string
     */
    @GetMapping("/saque")
    public String mostraSaque() {
        return "saque";
    }

    /**
     * Requisição para realizar o saque
     *
     * @param id    the id
     * @param valor the valor
     * @return string string
     * @throws IOException the io exception
     */
    @PostMapping("/saque")
    public String sacar(@RequestParam String id, @RequestParam String valor) throws IOException {
        agenciaService.sacar(Integer.parseInt(id), Integer.parseInt(valor));
        return "redirect:/clientes/" + id;
    }


    /**
     * Mostra pagina de delete com as informações do cliente
     *
     * @param model the model
     * @param id    the id
     * @return String string
     * @throws IOException the io exception
     */
    @GetMapping("/delete/{id}")
    public String deletarCliente(Model model, @PathVariable int id) throws IOException {
        Cliente cliente = agenciaService.getClienteById(id);
        model.addAttribute("cliente", cliente);
        return "delete";
    }


    /**
     * Deletar cliente string.
     *
     * @param id the id
     * @return String string
     * @throws IOException the io exception
     */
    @PostMapping("/delete/{id}")
    public String deletarCliente(@PathVariable int id) throws IOException {
        agenciaService.deletarCliente(id);
        return "redirect:/clientes";
    }

    /**
     * Pagina para login
     *
     * @param model the model
     * @return string string
     */
    @GetMapping("/login")
    public String mostraLoginCliente(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "login";
    }

    /**
     * Login cliente string.
     *
     * @param model   the model
     * @param cliente the cliente
     * @return string string
     * @throws IOException the io exception
     */
    @PostMapping("/login")
    public String loginCliente(Model model, @ModelAttribute("cliente") Cliente cliente) throws IOException {
        Cliente c = agenciaService.login(cliente);
        model.addAttribute("cliente", c);
        return "redirect:/clientes/" + String.valueOf(c.getId());
    }

    /**
     * Mostra pagina de transferencia
     *
     * @return string string
     */
    @GetMapping("/transferencia")
    public String mostraTransferencia() {
        return "transferencia";
    }

    /**
     * Requisição para realizar transferencia
     *
     * @param id1   the id 1
     * @param id2   the id 2
     * @param valor the valor
     * @return string string
     * @throws IOException the io exception
     */
    @PostMapping("/transferencia")
    public String transferir(@RequestParam String id1, @RequestParam String id2,  @RequestParam String valor) throws IOException {
        agenciaService.transferencia(Integer.parseInt(id1), Integer.parseInt(id2), Integer.parseInt(valor));
        return "redirect:/clientes";
    }

}
