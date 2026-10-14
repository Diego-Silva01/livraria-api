package com.example.demo.repository;

import com.example.demo.entidys.Autor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long>{
    boolean existsByNome(String nome);
   boolean existsByNomeAndIdNot(String nome, Long id);
   public Page<Autor> findAll(Pageable pageable);
}
