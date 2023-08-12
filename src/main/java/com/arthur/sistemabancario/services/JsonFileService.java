package com.arthur.sistemabancario.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import org.springframework.core.io.Resource;
import com.arthur.sistemabancario.model.Cliente;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class JsonFileService {

    private final ResourceLoaderService resourceLoaderService;

    public JsonFileService(ResourceLoaderService resourceLoaderService) {
        this.resourceLoaderService = resourceLoaderService;
    }

    public List<Cliente> readJsonFile(String location) throws IOException {
        Resource resource = resourceLoaderService.getResource(location);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(resource.getInputStream(), new TypeReference<List<Cliente>>() {});
    }

    public void writeJsonFile(List<Cliente> clientes, String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(filePath), clientes);
    }
}
