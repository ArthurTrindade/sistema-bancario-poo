package com.arthur.sistemabancario.services;

import java.io.IOException;
import java.util.List;

import com.arthur.sistemabancario.model.Agencia;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class JsonFileReaderService {
    private final ResourceLoaderService resourceLoaderService;

    public JsonFileReaderService(ResourceLoaderService resourceLoaderService) {
        this.resourceLoaderService = resourceLoaderService;
    }

    public List<Agencia> readJsonFile(String path) throws IOException {
        Resource resource = resourceLoaderService.getResource(path);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(resource.getInputStream(), new TypeReference<List<Agencia>>() {});
    }
}
