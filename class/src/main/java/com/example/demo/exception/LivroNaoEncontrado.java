package com.example.demo.exception;

public class LivroNaoEncontrado extends RuntimeException{
    public LivroNaoEncontrado(String mensagem){
        super(mensagem);
    }
}
