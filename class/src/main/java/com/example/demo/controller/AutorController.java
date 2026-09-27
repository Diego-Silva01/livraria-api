        package com.example.demo.controller;
        import jakarta.validation.Valid;
        import com.example.demo.dtos.AutorDTO;
        import com.example.demo.entidys.Autor;
        import com.example.demo.service.AutorService;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

    @RestController
        public class AutorController {
        private final AutorService autorService;

        public AutorController(AutorService autorService) {
            this.autorService = autorService;
        }

        @PostMapping("/autor")
        public AutorDTO salvar(@RequestBody @Valid AutorDTO autorDTO) {
            return autorService.salvar(autorDTO);
        }

        @GetMapping("/buscar")
        public List<AutorDTO> buscar() {
            return autorService.buscar();
        }

        @GetMapping("/autor/{id}")
        public AutorDTO buscarPorId(@PathVariable Long id) {
            return autorService.buscarPorId(id);
        }

        @PutMapping("/autor/{id}")
        public AutorDTO updateAutor(@PathVariable Long id, @RequestBody @Valid AutorDTO autorDTO) {
            return autorService.updateAutor(id, autorDTO);
        }

        @DeleteMapping("/autor/{id}")
        public void deleteAutor(@PathVariable Long id) {
            autorService.deleteAutor(id);

        }

        @DeleteMapping("/autor")
        public void deletaTodos() {
            autorService.deletaTodos();
        }

    }