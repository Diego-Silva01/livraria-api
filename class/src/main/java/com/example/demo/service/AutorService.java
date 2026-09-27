package com.example.demo.service;

import com.example.demo.dtos.AutorDTO;
import com.example.demo.entidys.Autor;
import com.example.demo.exception.AutorJaCadastradoException;
import com.example.demo.exception.AutorNaoEncontradoException;
import com.example.demo.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutorService {
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public AutorDTO salvar(AutorDTO autorDTO) {
        Autor autor = new Autor();
        autor.setNome(autorDTO.getNome());
        if (autorRepository.existsByNome(autor.getNome())){
            throw new AutorJaCadastradoException("Autor ja cadastrado");


        }
        Autor salvo = autorRepository.save(autor);
        AutorDTO resposta = new AutorDTO();
        resposta.setNome(salvo.getNome());
        resposta.setId(salvo.getId());
        return resposta;
    }

    public List<AutorDTO> buscar() {
      List<Autor> autoresCadastrado = autorRepository.findAll();
      List<AutorDTO> autorDTOS = new ArrayList<>();
      for (Autor autor:autoresCadastrado){
          AutorDTO dto = new AutorDTO();
          dto.setNome(autor.getNome());
          dto.setId(autor.getId());
          autorDTOS.add(dto);
      }
        return autorDTOS;
    }

    public AutorDTO buscarPorId(Long id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(()  -> new AutorNaoEncontradoException("Autor não encontrado!"));
        AutorDTO autorDTO = new AutorDTO();
        autorDTO.setNome(autor.getNome());
        autorDTO.setId(autor.getId());
        return autorDTO;

    }

public AutorDTO updateAutor(Long id, AutorDTO autorDTO) {
        Autor autorexiteste = autorRepository.findById(id).orElseThrow(()-> new AutorNaoEncontradoException("Este autor não existe!"));
    if (autorRepository.existsByNomeAndIdNot(autorDTO.getNome(),autorexiteste.getId())){
        throw new AutorJaCadastradoException("Autor ja cadastrado");
    }
        autorexiteste.setNome(autorDTO.getNome());
    Autor autor = autorRepository.save(autorexiteste);
    AutorDTO salvo = new AutorDTO();
    salvo.setNome(autor.getNome());
    salvo.setId(autor.getId());
       return salvo;
}
public void deleteAutor(Long id){
Autor autorExist = autorRepository.findById(id).orElseThrow(()-> new AutorNaoEncontradoException("Autor não encontrado, impossivel deleta-lo!"));
       autorRepository.deleteById(id);

}
public void deletaTodos(){
        autorRepository.deleteAll();
}

    }


