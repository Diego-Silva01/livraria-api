package com.example.demo.entidys;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    private Date data;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
    public Livro(){

    }
    public Livro(String nome, Date data, Autor autor){
        this.nome = nome;
        this.data = data;
        this.autor = autor;
    }
}
