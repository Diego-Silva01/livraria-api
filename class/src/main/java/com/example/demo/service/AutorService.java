package com.example.demo.service;

import com.example.demo.dtos.AutorDTO;
import com.example.demo.entidys.Autor;
import com.example.demo.exception.AutorJaCadastradoException;
import com.example.demo.exception.AutorNaoEncontradoException;
import com.example.demo.exception.AutorPossuiLivrosException;
import com.example.demo.repository.AutorRepository;
import com.example.demo.repository.LivroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutorService {
    private final AutorRepository autorRepository;
    private final LivroRepository livroRepository;

    public AutorService(AutorRepository autorRepository, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
    }

    public AutorDTO converteParaDTO(Autor autor){
        AutorDTO autorDTO = new AutorDTO();
        autorDTO.setNome(autor.getNome());
        autorDTO.setId(autor.getId());
        return autorDTO;
    }
    public AutorDTO salvar(AutorDTO autorDTO) {
        Autor autor = new Autor();
        autor.setNome(autorDTO.getNome());
        if (autorRepository.existsByNome(autor.getNome())){
            throw new AutorJaCadastradoException("Autor ja cadastrado");


        }
        Autor salvo = autorRepository.save(autor);

        return converteParaDTO(salvo);
    }

    public List<AutorDTO> buscar() {
        List<Autor> autoresCadastrados = autorRepository.findAll();
        List<AutorDTO> autorDTOS = new ArrayList<>();

        for (Autor autor : autoresCadastrados) {
            autorDTOS.add(converteParaDTO(autor));
        }

        return autorDTOS;
    }

    public AutorDTO buscarPorId(Long id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(()  -> new AutorNaoEncontradoException("Autor não encontrado!"));

        return  converteParaDTO(autor);

    }

public AutorDTO updateAutor(Long id, AutorDTO autorDTO) {
        Autor autorexiteste = autorRepository.findById(id).orElseThrow(()-> new AutorNaoEncontradoException("Este autor não existe!"));
    if (autorRepository.existsByNomeAndIdNot(autorDTO.getNome(),autorexiteste.getId())){
        throw new AutorJaCadastradoException("Autor ja cadastrado");
    }
        autorexiteste.setNome(autorDTO.getNome());
    Autor autor = autorRepository.save(autorexiteste);
       return converteParaDTO(autor);
}
public void deleteAutor(Long id){
    autorRepository.findById(id)
            .orElseThrow(() ->
                    new AutorNaoEncontradoException(
                            "Autor não encontrado, impossível deletá-lo!"
                    )
            );    if (livroRepository.existsByAutorId(id)) {
        throw new AutorPossuiLivrosException(
                "O autor possui livros cadastrados, impossível deletar!"
        );
    }
       autorRepository.deleteById(id);

}
public void deletaTodos(){
        autorRepository.deleteAll();
}
    public Page<AutorDTO> buscarPorFiltro(int pagina, int iten) {
        // 1. Cria o objeto de paginação
        Pageable pageable = PageRequest.of(pagina, iten);

        // 2. Busca os dados paginados direto no repositório (retorna Page<Autor>)
        Page<Autor> autoresPage = autorRepository.findAll(pageable);

        // 3. Aplica o .map() para converter cada Autor em AutorDTO
        Page<AutorDTO> autoresDtoPage = autoresPage.map(autor -> converteParaDTO(autor));

        // 4. Retorna o Page já convertido
        return autoresDtoPage;
    }
}




