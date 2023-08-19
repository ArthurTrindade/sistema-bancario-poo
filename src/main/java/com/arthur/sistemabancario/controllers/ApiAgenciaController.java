package com.arthur.sistemabancario.controllers;

import com.arthur.sistemabancario.model.Cliente;
import com.arthur.sistemabancario.services.AgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RestController
@RequestMapping("api/v1/clientes")
public class ApiAgenciaController {

    @Autowired
    private AgenciaService agenciaService;

    
    /** 
     * @return List<Cliente>
     * @throws IOException
     */
    @GetMapping
    public List<Cliente> findAllClientes() throws IOException {
        return agenciaService.getClientes();
    }

    @PostMapping
    public Cliente addCliente(@RequestBody Cliente cliente) throws IOException {
        return agenciaService.saveCliente(cliente);
    }

    @GetMapping("{id}")
    public Cliente findClienteById(@PathVariable int id) throws IOException {
        return agenciaService.getClienteById(id);
    }

    @PostMapping("/depositar")
    @ResponseBody
    public String depositar(@RequestParam String id, @RequestParam String valor) throws IOException {
        agenciaService.depositar(Integer.parseInt(id), Integer.parseInt(valor));
        return "Depositado " + valor + " " +  "para " + id;
    }

    @PostMapping("/login")
    public Cliente login(@RequestBody Cliente cliente) throws IOException {
        return agenciaService.login(cliente);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable int id) throws IOException {
        return agenciaService.deletarCliente(id)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
