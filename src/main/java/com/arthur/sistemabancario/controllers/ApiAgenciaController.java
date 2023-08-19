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

/**
 * The type Api agencia controller.
 */
@Controller
@RestController
@RequestMapping("api/v1/clientes")
public class ApiAgenciaController {

    @Autowired
    private AgenciaService agenciaService;


    /**
     * Find all clientes list.
     *
     * @return List<Cliente>  list
     * @throws IOException the io exception
     */
    @GetMapping
    public List<Cliente> findAllClientes() throws IOException {
        return agenciaService.getClientes();
    }

    /**
     * Add cliente cliente.
     *
     * @param cliente the cliente
     * @return the cliente
     * @throws IOException the io exception
     */
    @PostMapping
    public Cliente addCliente(@RequestBody Cliente cliente) throws IOException {
        return agenciaService.saveCliente(cliente);
    }

    /**
     * Find cliente by id cliente.
     *
     * @param id the id
     * @return the cliente
     * @throws IOException the io exception
     */
    @GetMapping("{id}")
    public Cliente findClienteById(@PathVariable int id) throws IOException {
        return agenciaService.getClienteById(id);
    }

    /**
     * Depositar string.
     *
     * @param id    the id
     * @param valor the valor
     * @return the string
     * @throws IOException the io exception
     */
    @PostMapping("/depositar")
    @ResponseBody
    public String depositar(@RequestParam String id, @RequestParam String valor) throws IOException {
        agenciaService.depositar(Integer.parseInt(id), Integer.parseInt(valor));
        return "Depositado " + valor + " " +  "para " + id;
    }

    /**
     * Login cliente.
     *
     * @param cliente the cliente
     * @return the cliente
     * @throws IOException the io exception
     */
    @PostMapping("/login")
    public Cliente login(@RequestBody Cliente cliente) throws IOException {
        return agenciaService.login(cliente);
    }

    /**
     * Delete cliente response entity.
     *
     * @param id the id
     * @return the response entity
     * @throws IOException the io exception
     */
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable int id) throws IOException {
        return agenciaService.deletarCliente(id)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
