package com.arthur.sistemabancario.controllers;

import com.arthur.sistemabancario.model.Cliente;
import com.arthur.sistemabancario.services.AgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RestController
@RequestMapping("api/v1/clientes")
public class AgenciaController {

    @Autowired
    private AgenciaService agenciaService;

    @GetMapping
    public List<Cliente> findAllClientes() throws IOException {
         agenciaService.initializeClientes();
         return agenciaService.getClientes();
    }

    @PostMapping
    public Cliente addCliente(@RequestBody Cliente cliente) throws IOException {
        return agenciaService.saveCliente(cliente);
    }

    @GetMapping("{id}")
    public Cliente findClienteById(@PathVariable int id) {
        return agenciaService.getClienteById(id);
    }

    @PostMapping("/depositar")
    @ResponseBody
    public String depositar(@RequestParam String id, @RequestParam String valor) throws IOException {
        agenciaService.depositar(Integer.parseInt(id), Integer.parseInt(valor));
        return "Depositado " + valor + " " +  "para " + id;
    }


}
