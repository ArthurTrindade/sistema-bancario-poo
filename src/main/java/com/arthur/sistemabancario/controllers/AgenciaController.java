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

@Controller()
public class AgenciaController {

    @Autowired
    private AgenciaService agenciaService;

    @GetMapping("dashboard/clientes")
    public String findAllClientes(Model model) throws IOException {
        List<Cliente> clientes = agenciaService.getClientes();
        model.addAttribute("clientes", clientes);
        return "clientes";
    }

    @GetMapping("dashboard/clientes/{id}")
    public String getClienteById(Model model, @PathVariable int id) throws IOException {
        Cliente cliente = agenciaService.getClienteById(id);
        model.addAttribute("cliente", cliente);
        return "cliente";
    }

    @GetMapping("dashboard/cadastro")
    public String showAddClienteForm(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "cadastro";
    }

    @PostMapping("dashboard/cadastro")
    public String addCliente(Model model, @ModelAttribute("cliente") Cliente cliente) throws IOException {
        Cliente c =  agenciaService.saveCliente(cliente);
        model.addAttribute("cliente", cliente);
        return "redirect:/clientes/" + String.valueOf(c.getId());
    }

    @GetMapping("dashboard/deposit")
    public String showDepositForm() {
        return "deposit";
    }

    @PostMapping("dashboard/deposit")
    public String depositar(@RequestParam String id, @RequestParam String valor) throws IOException {
        agenciaService.depositar(Integer.parseInt(id), Integer.parseInt(valor));
        return "redirect:/clientes/" + id;
    }

    @DeleteMapping("dashboard/clientes/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable int id) throws IOException {
        return agenciaService.deletarCliente(id)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("dashboard/login")
    public String mostraLoginCliente(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "login2";
    }

    @PostMapping("dashboard/login")
    public String loginCliente(Model model, @ModelAttribute("cliente") Cliente cliente) throws IOException {
        Cliente c = agenciaService.login(cliente);
        System.out.println(c);
        model.addAttribute("cliente", c);
        return "redirect:/dashboard/clientes/" + String.valueOf(c.getId());
    }

}
