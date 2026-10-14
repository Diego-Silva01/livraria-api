package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import com.example.demo.dtos.AutorDTO;
import com.example.demo.service.AutorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autor") // Define a rota base para todos os endpoints desta classe
@Tag(name = "Autor", description = "Gerenciador de autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @Operation(summary = "Cadastrar autor", description = "Cadastra um novo autor no banco de dados")
    @PostMapping
    public AutorDTO salvar(@RequestBody @Valid AutorDTO autorDTO) {
        return autorService.salvar(autorDTO);
    }

    @Operation(summary = "Buscar todos os autores", description = "Retorna uma lista de todos os autores cadastrados")
    @GetMapping
    public List<AutorDTO> buscar() {
        return autorService.buscar();
    }

    @Operation(summary = "Buscar autor por ID", description = "Busca um autor específico pelo seu ID")
    @GetMapping("/{id}")
    public AutorDTO buscarPorId(@PathVariable Long id) {
        return autorService.buscarPorId(id);
    }

    @Operation(summary = "Atualizar autor", description = "Atualiza os dados de um autor passando o ID na requisição")
    @PutMapping("/{id}")
    public AutorDTO updateAutor(@PathVariable Long id, @RequestBody @Valid AutorDTO autorDTO) {
        return autorService.updateAutor(id, autorDTO);
    }

    @Operation(summary = "Deletar autor", description = "Remove um autor do banco de dados pelo ID")
    @DeleteMapping("/{id}")
    public void deleteAutor(@PathVariable Long id) {

        autorService.deleteAutor(id);
    }

    @Operation(summary = "Deletar todos os autores", description = "Remove todos os registros de autores do banco de dados")
    @DeleteMapping("/todos")
    public void deletaTodos() {
        autorService.deletaTodos();
    }
}