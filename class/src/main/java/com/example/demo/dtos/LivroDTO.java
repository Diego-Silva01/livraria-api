    package com.example.demo.dtos;

    import java.util.Date;

    public class LivroDTO {
        public Long getId() {
            return id;
        }

        public String getAutorNome() {
            return autorNome;
        }

        public void setAutorNome(String autorNome) {
            this.autorNome = autorNome;
        }

        private String autorNome;

        public void setId(Long id) {
            this.id = id;
        }

        private Long id;
        public Date getData() {
            return data;
        }

        public void setData(Date data) {
            this.data = data;
        }

        public Long getAutorId() {
            return autorId;
        }

        public void setAutorId(Long autorId) {
            this.autorId = autorId;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        private String nome;
        private Date data;
        private Long autorId;
    }
