package br.com.alura.comex.categoria;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    @Enumerated(EnumType.STRING)
    private CategoriaStatus status;

    public Categoria() {
    }

    public Categoria(Long id, String nome, CategoriaStatus status) {
        this.id = id;
        this.nome = nome;
        this.status = status;
    }

    public static Categoria fromRecord(CategoriaDTO record) {
        // enviando ID null para contornar o erro 'Row was updated or deleted by another
        // transaction (or unsaved-value mapping was incorrect)'
        return new Categoria(null, record.nome(), CategoriaStatus.ATIVA);
    }

    public void update(CategoriaUpdateRequest request) {
        this.nome = request.nome();
    }

    public void delete() {
        this.status = CategoriaStatus.DESATIVADA;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CategoriaStatus getStatus() {
        return status;
    }

    public void setStatus(CategoriaStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}