package com.arthur.sistemabancario.controllers;

import com.arthur.sistemabancario.model.Cliente;
import com.arthur.sistemabancario.services.AgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/clientes")
public class AgenciaController {

    @Autowired
    private AgenciaService agenciaService;

    @PostMapping
    public Cliente addProduct(@RequestBody Cliente cliente) throws IOException {
        return agenciaService.saveProduct(cliente);
    }

    @GetMapping
    public List<Cliente> findAllProducts() throws IOException {
        // clienteService.initializeProducts();
        return agenciaService.getProducts();
    }

    @GetMapping("{id}")
    public Cliente findClienteById(@PathVariable int id) {
        return agenciaService.getClienteById(id);
    }

    @PostMapping("/depositar")
    @ResponseBody
    public String depositar(@RequestParam String id, @RequestParam String valor) {
        agenciaService.depositar(Integer.parseInt(id), Integer.parseInt(valor));
        return "Depositado" + valor + " " +  "para " + id;
    }


}
