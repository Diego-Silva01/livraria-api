package com.example.demo.controller;

import com.example.demo.dtos.LivroDTO;
import com.example.demo.entidys.Autor;
import com.example.demo.entidys.Livro;
import com.example.demo.service.AutorService;
import com.example.demo.service.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LivroController {
    public final LivroService livroService;
    public LivroController(LivroService livroService){

        this.livroService = livroService;
    }
    @PostMapping("/livro")
    public ResponseEntity<LivroDTO> salvar(@RequestBody LivroDTO livroDTO){
       return ResponseEntity.status(HttpStatus.CREATED).body(livroService.salvar(livroDTO));
    }
    @GetMapping("/livro")
    public ResponseEntity <List<LivroDTO>> buscarTodosLivros(){
        return ResponseEntity.ok(livroService.buscarTodos());
    }
    @PutMapping("/livro/{id}")
    public ResponseEntity<LivroDTO> autualizarLivro(@RequestBody LivroDTO livroDTO, @PathVariable Long id){
        return ResponseEntity.ok(livroService.atualizarLivro(livroDTO, id));
    }
    @DeleteMapping("/livro/{id}")
    public ResponseEntity<Void>  deletarAutor(@PathVariable Long id){
        livroService.deletar(id);
     return ResponseEntity.noContent().build();

    }
    @GetMapping("/livro/{id}")
    public ResponseEntity<LivroDTO>  buscarLivroPorId(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.buscarLivroPorID(id));
    }
}
