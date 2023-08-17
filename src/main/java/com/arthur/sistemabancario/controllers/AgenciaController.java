package com.arthur.sistemabancario.controllers;

import com.arthur.sistemabancario.model.Cliente;
import com.arthur.sistemabancario.services.AgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class AgenciaController {

    @Autowired
    private AgenciaService agenciaService;

    @GetMapping("/clientes")
    public String findAllClientes(Model model) throws IOException {
        List<Cliente> clientes = agenciaService.getClientes();
        model.addAttribute("clientes", clientes);
        return "clientes";
    }

    @GetMapping("clientes/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable int id) throws IOException {
        Cliente item = agenciaService.getClienteById(id);
        return item != null
                ? new ResponseEntity<>(item, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/cadastro")
    public String showAddClienteForm(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "cadastro";
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
        return "redirect:/clientes";
    }

    @DeleteMapping("clientes/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable int id) throws IOException {
        return agenciaService.deletarCliente(id)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
