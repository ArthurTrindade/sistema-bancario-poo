package com.arthur.sistemabancario.controllers;

import com.arthur.sistemabancario.model.Cliente;
import com.arthur.sistemabancario.services.AgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
