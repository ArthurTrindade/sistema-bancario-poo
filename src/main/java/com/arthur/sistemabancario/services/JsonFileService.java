package com.arthur.sistemabancario.services;

import java.io.IOException;
import java.util.List;
import java.io.File;

import com.arthur.sistemabancario.model.Cliente;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class JsonFileService {
    private final ResourceLoaderService resourceLoaderService;

    public JsonFileService(ResourceLoaderService resourceLoaderService) {
        this.resourceLoaderService = resourceLoaderService;
    }

    public List<Cliente> readJsonFile(String path) throws IOException {
        Resource resource = resourceLoaderService.getResource(path);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(resource.getInputStream(), new TypeReference<List<Cliente>>() {});
    }

    public void writeJsonFile(List<Cliente> clientes, String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(path), clientes);
    }

}
