package com.example.demo.controller;

import com.example.demo.dtos.LivroDTO;
import com.example.demo.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
@Tag(name = "Livros", description = "Gerenciador de livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @Operation(summary = "Cadastrar livro", description = "Cadastra um novo livro no banco de dados")
    @PostMapping
    public ResponseEntity<LivroDTO> salvar(@RequestBody @Valid LivroDTO livroDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.salvar(livroDTO));
    }

    @Operation(summary = "Buscar todos os livros", description = "Retorna uma lista de todos os livros cadastrados")
    @GetMapping
    public ResponseEntity<List<LivroDTO>> buscarTodosLivros() {
        return ResponseEntity.ok(livroService.buscarTodos());
    }

    @Operation(summary = "Buscar livro por ID", description = "Busca um livro específico pelo seu ID")
    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> buscarLivroPorId(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.buscarLivroPorID(id));
    }

    @Operation(summary = "Atualizar livro", description = "Atualiza os dados de um livro passando o ID")
    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> atualizarLivro(@PathVariable Long id, @RequestBody @Valid LivroDTO livroDTO) {
        return ResponseEntity.ok(livroService.atualizarLivro(livroDTO, id));
    }

    @Operation(summary = "Deletar livro", description = "Remove um livro do banco de dados pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}