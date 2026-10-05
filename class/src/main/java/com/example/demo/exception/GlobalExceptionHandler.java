package com.example.demo.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AutorNaoEncontradoException.class)
public ResponseEntity<String>resposta(AutorNaoEncontradoException mensagem){
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem.getMessage());
    }
    @ExceptionHandler(AutorJaCadastradoException.class)
    public ResponseEntity<String> resposta(AutorJaCadastradoException mensagem){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(mensagem.getMessage());
    }
     @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> resposta(MethodArgumentNotValidException mensagem){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagem.getBindingResult().getFieldErrors().get(0).getDefaultMessage());}

    @ExceptionHandler(LivroNaoEncontrado.class)
    public ResponseEntity<String> resposta(LivroNaoEncontrado mensagem){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(mensagem.getMessage());
    }
}