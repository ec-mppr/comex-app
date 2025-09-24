package br.com.alura.comex.categoria;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public record CategoriaDTO(
        @NotBlank(message = "A categoria precisa ter um nome") @Length(min = 2, message = "O nome da categoria precisa ter no mínimo 2 caracteres") String nome,
        Long id) {

    public CategoriaDTO(Categoria categoria) {
        this(categoria.getNome(), categoria.getId());
    }
}
