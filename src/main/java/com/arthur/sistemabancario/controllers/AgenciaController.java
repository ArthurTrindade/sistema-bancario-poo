package com.arthur.sistemabancario.controllers;

import com.arthur.sistemabancario.model.Cliente;
import com.arthur.sistemabancario.services.AgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
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
        Cliente c = agenciaService.saveCliente(cliente);
        model.addAttribute("cliente", cliente);
        return "redirect:/dashboard/clientes/" + String.valueOf(c.getId());
    }

    @GetMapping("dashboard/deposito")
    public String showDepositForm() {
        return "deposito";
    }

    @PostMapping("dashboard/deposito")
    public String depositar(@RequestParam String id, @RequestParam String valor) throws IOException {
        agenciaService.depositar(Integer.parseInt(id), Integer.parseInt(valor));
        return "redirect:/dashboard/clientes/" + id;
    }

    @GetMapping("dashboard/saque")
    public String showsaque() {
        return "saque";
    }

    @PostMapping("dashboard/saque")
    public String sacar(@RequestParam String id, @RequestParam String valor) throws IOException {
        agenciaService.sacar(Integer.parseInt(id), Integer.parseInt(valor));
        return "redirect:/dashboard/clientes/" + id;
    }

    @GetMapping("/dashboard/delete/{id}")
    public String deleteTutorial(Model model, @PathVariable int id) throws IOException {
        Cliente cliente = agenciaService.getClienteById(id);
        model.addAttribute("cliente", cliente);
        return "delete";
    }

    @PostMapping("/dashboard/delete/{id}")
    public String deletarCliente(@PathVariable int id) throws IOException {
        agenciaService.deletarCliente(id);
        return "redirect:/dashboard/clientes";
    }

    @GetMapping("dashboard/login")
    public String mostraLoginCliente(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "login";
    }

    @PostMapping("dashboard/login")
    public String loginCliente(Model model, @ModelAttribute("cliente") Cliente cliente) throws IOException {
        Cliente c = agenciaService.login(cliente);
        System.out.println(c);
        model.addAttribute("cliente", c);
        return "redirect:/dashboard/clientes/" + String.valueOf(c.getId());
    }

}
