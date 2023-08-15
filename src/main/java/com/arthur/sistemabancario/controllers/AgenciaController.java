package com.arthur.sistemabancario.controllers;

import com.arthur.sistemabancario.model.Cliente;
import com.arthur.sistemabancario.services.AgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class AgenciaController {

    @Autowired
    private AgenciaService agenciaService;

    @GetMapping("/clients")
    public String findAllClientes(Model model) throws IOException {
        List<Cliente> clientes = agenciaService.getClientes();
        model.addAttribute("clientes", clientes);
        return "clients";
    }

    @GetMapping("/add-client")
    public String showAddClienteForm(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "add-client";
    }

    @PostMapping("/add-client")
    public String addCliente(Model model, @ModelAttribute("cliente") Cliente cliente) throws IOException {
        agenciaService.saveCliente(cliente);
        return "redirect:/clients";
    }

    @GetMapping("/deposit")
    public String showDepositForm() {
        return "deposit";
    }

    @PostMapping("/deposit")
    public String depositar(@RequestParam String id, @RequestParam String valor) throws IOException {
        agenciaService.depositar(Integer.parseInt(id), Integer.parseInt(valor));
        return "redirect:/clients";
    }
}
