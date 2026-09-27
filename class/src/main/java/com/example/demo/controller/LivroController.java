package com.example.demo.controller;

import com.example.demo.dtos.LivroDTO;
import com.example.demo.entidys.Autor;
import com.example.demo.entidys.Livro;
import com.example.demo.service.AutorService;
import com.example.demo.service.LivroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LivroController {
    private final AutorService autorService;
    public final LivroService livroService;
    public LivroController(AutorService autorService, LivroService livroService){
        this.autorService = autorService;
        this.livroService = livroService;
    }
    @PostMapping("/livro")
    public Livro salvar(@RequestBody LivroDTO livroDTO){
       return livroService.salvar(livroDTO);
    }
    @GetMapping("/livro")
    public List<LivroDTO> buscarTodosLivros(){
        return livroService.buscarTodos() ;
    }
    @PutMapping("/livro/{id}")
    public LivroDTO autualizarLivro(@RequestBody LivroDTO livroDTO, @PathVariable Long id){
        return livroService.atualizarLivro(livroDTO,id);
    }
}
