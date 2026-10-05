package com.example.demo.service;

import com.example.demo.dtos.AutorDTO;
import com.example.demo.dtos.LivroDTO;
import com.example.demo.entidys.Autor;
import com.example.demo.entidys.Livro;
import com.example.demo.exception.AutorNaoEncontradoException;
import com.example.demo.exception.LivroNaoEncontrado;
import com.example.demo.repository.AutorRepository;
import com.example.demo.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LivroService {
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public LivroService(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public LivroDTO salvar(LivroDTO livroDTO) {
        Autor autor = autorRepository.findById(livroDTO.getAutorId())
                .orElseThrow(() -> new AutorNaoEncontradoException("Autor não encontrado"));

        Livro livro = new Livro(livroDTO.getNome(), livroDTO.getData(), autor);

        Livro livro1 = livroRepository.save(livro);

        LivroDTO livroDTORetorno = new LivroDTO();
        livroDTORetorno.setNome(livro1.getNome());
        livroDTORetorno.setData(livro1.getData());
        livroDTORetorno.setId(livro1.getId());
        livroDTORetorno.setAutorId(livro1.getAutor().getId());

        return livroDTORetorno;
    }

    public List<LivroDTO> buscarTodos() {
        List<Livro> livrosEncontrados = livroRepository.findAll();
        List<LivroDTO> listaDelivroDTOSRetornada = new ArrayList<>();
        for (Livro livro : livrosEncontrados) {

            LivroDTO livroDTO = new LivroDTO();
            livroDTO.setId(livro.getId());
            livroDTO.setNome(livro.getNome());
            livroDTO.setData(livro.getData());
            livroDTO.setAutorId(livro.getAutor().getId());
            livroDTO.setAutorNome(livro.getAutor().getNome());
            listaDelivroDTOSRetornada.add(livroDTO);


        }
        return listaDelivroDTOSRetornada;
    }

    public LivroDTO buscarLivroPorID(Long id) {
        Livro livro = livroRepository.findById(id).orElseThrow(() -> new LivroNaoEncontrado("Livro não encontrdo"));
        LivroDTO livroDTO1 = new LivroDTO();
        livroDTO1.setId(livro.getId());
        livroDTO1.setData(livro.getData());
        livroDTO1.setNome(livro.getNome());
        livroDTO1.setAutorId(livro.getAutor().getId());
return livroDTO1;
    }
    public LivroDTO atualizarLivro(LivroDTO livroDTO, Long id) {
        // 1. Busca o livro existente pelo ID da URL
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontrado("Livro não encontrado"));

        // 2. Busca o autor usando o ID que veio dentro do DTO
        Autor autor = autorRepository.findById(livroDTO.getAutorId())
                .orElseThrow(() -> new AutorNaoEncontradoException("Autor não encontrado"));

        // 3. Atualiza os dados da entidade Livro
        livro.setNome(livroDTO.getNome());
        livro.setData(livroDTO.getData());
        livro.setAutor(autor); // Associa o autor correto ao livro

        // 4. Salva o livro atualizado no banco
        Livro livro1Atualizado = livroRepository.save(livro);

        // 5. Converte a Entity atualizada de volta para DTO para retornar ao cliente
        LivroDTO objetoQueGuardaraOsdadosnovos = new LivroDTO();
        objetoQueGuardaraOsdadosnovos.setId(livro1Atualizado.getId());
        objetoQueGuardaraOsdadosnovos.setNome(livro1Atualizado.getNome());
        objetoQueGuardaraOsdadosnovos.setData(livro1Atualizado.getData());
        objetoQueGuardaraOsdadosnovos.setAutorId(livro1Atualizado.getAutor().getId()); // Retorna o ID do autor corretamente

        return objetoQueGuardaraOsdadosnovos;
    }
    public void deletar(Long id){
   livroRepository.findById(id).orElseThrow(()-> new LivroNaoEncontrado("Livro não encontrado"));
        livroRepository.deleteById(id);

    }
}




